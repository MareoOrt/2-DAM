codeunit 10750 "SII XML Creator"
{

    trigger OnRun()
    begin
    end;

    var
        CompanyInformation: Record "Company Information";
        SIISetup: Record "SII Setup";
        SIIManagement: Codeunit "SII Management";
        XMLDOMManagement: Codeunit "XML DOM Management";
        SoapenvTxt: Label 'http://schemas.xmlsoap.org/soap/envelope/', Locked = true;
        CompanyInformationMissingErr: Label 'Your company is not properly set up. Go to company information and complete your setup.';
        DataTypeManagement: Codeunit "Data Type Management";
        LastXMLNode: DotNet XmlNode;
        ErrorMsg: Text;
        DetailedLedgerEntryShouldBePaymentErr: Label 'Expected the detailed ledger entry to have a Payment document type, but got %1 instead.', Comment = '%1 is the actual value of the Detailed Ledger Entry document type';
        RegistroDelPrimerSemestreTxt: Label 'Registro del primer semestre';
        IsInitialized: Boolean;
        RetryAccepted: Boolean;
        SIISetupInitialized: Boolean;
        UploadTypeGlb: Option Regular,Intracommunity,RetryAccepted,"Collection In Cash";
        LCLbl: Label 'LC', Locked = true;
        SIIVersion: Option "1.1","1.0";
        SiiTxt: Text;
        SiiLRTxt: Text;

    [Scope('OnPrem')]
    procedure GenerateXml(LedgerEntry: Variant; var XMLDocOut: DotNet XmlDocument; UploadType: Option; IsCreditMemoRemoval: Boolean): Boolean
    var
        CustLedgerEntry: Record "Cust. Ledger Entry";
        VendorLedgerEntry: Record "Vendor Ledger Entry";
        DetailedCustLedgEntry: Record "Detailed Cust. Ledg. Entry";
        DetailedVendorLedgEntry: Record "Detailed Vendor Ledg. Entry";
        RecRef: RecordRef;
        IsHandled: Boolean;
        ResultValue: Boolean;
    begin
        IsHandled := false;
        OnBeforeGenerateXML(LedgerEntry, XMLDocOut, UploadType, IsCreditMemoRemoval, ResultValue, IsHandled);
        IF IsHandled THEN
            EXIT(ResultValue);

        GetSIISetup;
        SiiTxt := SIISetup."SuministroInformacion Schema";
        SiiLRTxt := SIISetup."SuministroLR Schema";
        if not IsInitialized then
            XMLDocOut := XMLDocOut.XmlDocument;

        RecRef.GetTable(LedgerEntry);
        case RecRef.Number of
            DATABASE::"Cust. Ledger Entry":
                begin
                    RecRef.SetTable(CustLedgerEntry);
                    if UploadType = UploadTypeGlb::"Collection In Cash" then
                        exit(CreateCollectionInCashXml(XMLDocOut, CustLedgerEntry, UploadType));
                    exit(CreateInvoicesIssuedLedgerXml(CustLedgerEntry, XMLDocOut, UploadType, IsCreditMemoRemoval));
                end;
            DATABASE::"Vendor Ledger Entry":
                begin
                    RecRef.SetTable(VendorLedgerEntry);
                    exit(CreateInvoicesReceivedLedgerXml(VendorLedgerEntry, XMLDocOut, UploadType, IsCreditMemoRemoval));
                end;
            DATABASE::"Detailed Cust. Ledg. Entry":
                begin
                    RecRef.SetTable(DetailedCustLedgEntry);
                    if DetailedCustLedgEntry."Document Type" <> DetailedCustLedgEntry."Document Type"::Payment then
                        ErrorMsg := StrSubstNo(DetailedLedgerEntryShouldBePaymentErr, Format(DetailedCustLedgEntry."Document Type"));
                    CustLedgerEntry.Get(DetailedCustLedgEntry."Cust. Ledger Entry No.");
                    exit(CreateReceivedPaymentsXml(CustLedgerEntry, XMLDocOut))
                end;
            DATABASE::"Detailed Vendor Ledg. Entry":
                begin
                    RecRef.SetTable(DetailedVendorLedgEntry);
                    if DetailedVendorLedgEntry."Document Type" <> DetailedVendorLedgEntry."Document Type"::Payment then
                        ErrorMsg := StrSubstNo(DetailedLedgerEntryShouldBePaymentErr, Format(DetailedVendorLedgEntry."Document Type"));
                    VendorLedgerEntry.Get(DetailedVendorLedgEntry."Vendor Ledger Entry No.");
                    exit(CreateEmittedPaymentsXml(VendorLedgerEntry, XMLDocOut))
                end
            else
                exit;
        end;
    end;

    local procedure CreateEmittedPaymentsXml(PurchaseVendorLedgerEntry: Record "Vendor Ledger Entry"; var XMLDocOut: DotNet XmlDocument): Boolean
    var
        Vendor: Record Vendor;
        SIIDocUploadState: Record "SII Doc. Upload State";
        TempXMLNode: DotNet XmlNode;
        XMLNode: DotNet XmlNode;
        PurchaseVendorLedgerEntryRecRef: RecordRef;
        DocumentType: Option Sales,Purchase,"Intra Community","Payment Received","Payment Sent","Collection In Cash";
        HeaderName: Text;
        HeaderVATNo: Text;
    begin
        if not CompanyInformation.Get then begin
            ErrorMsg := CompanyInformationMissingErr;
            exit;
        end;
        HeaderName := CompanyInformation.Name;
        HeaderVATNo := CompanyInformation."VAT Registration No.";

        PopulateXmlPrerequisites(
          XMLDocOut, XMLNode, DocumentType::"Payment Sent", HeaderName, HeaderVATNo, false, UploadTypeGlb::Regular);

        Vendor.Get(PurchaseVendorLedgerEntry."Vendor No.");
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'RegistroLRPagos', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDFactura', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDEmisorFactura', '', 'sii', SiiTxt, XMLNode);
        SIIDocUploadState.GetSIIDocUploadStateByVendLedgEntry(PurchaseVendorLedgerEntry);
        FillThirdPartyId(
          XMLNode,
          Vendor."Country/Region Code",
          Vendor.Name,
          Vendor."VAT Registration No.",
          Vendor."No.",
          true,
          SIIManagement.VendorIsIntraCommunity(Vendor."No."),
          false, SIIDocUploadState.IDType);

        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'NumSerieFacturaEmisor', PurchaseVendorLedgerEntry."External Document No.", 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(PurchaseVendorLedgerEntry."Document Date"), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Pagos', '', 'siiLR', SiiLRTxt, XMLNode);

        PurchaseVendorLedgerEntryRecRef.GetTable(PurchaseVendorLedgerEntry);
        AddEmittedPayments(XMLNode, PurchaseVendorLedgerEntryRecRef);
        exit(true);
    end;

    local procedure CreateReceivedPaymentsXml(CustLedgerEntry: Record "Cust. Ledger Entry"; var XMLDocOut: DotNet XmlDocument): Boolean
    var
        TempXMLNode: DotNet XmlNode;
        XMLNode: DotNet XmlNode;
        SalesCustLedgerEntryRecRef: RecordRef;
        DocumentType: Option Sales,Purchase,"Intra Community","Payment Received","Payment Sent","Collection In Cash";
        HeaderName: Text;
        HeaderVATNo: Text;
    begin
        if not CompanyInformation.Get then begin
            ErrorMsg := CompanyInformationMissingErr;
            exit;
        end;
        HeaderName := CompanyInformation.Name;
        HeaderVATNo := CompanyInformation."VAT Registration No.";

        PopulateXmlPrerequisites(
          XMLDocOut, XMLNode, DocumentType::"Payment Received", HeaderName, HeaderVATNo, false, UploadTypeGlb::Regular);

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'RegistroLRCobros', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDFactura', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDEmisorFactura', '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'NIF', CompanyInformation."VAT Registration No.", 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);

        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'NumSerieFacturaEmisor', CustLedgerEntry."Document No.", 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(CustLedgerEntry."Posting Date"), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Cobros', '', 'siiLR', SiiLRTxt, XMLNode);

        SalesCustLedgerEntryRecRef.GetTable(CustLedgerEntry);
        AddReceivedPayments(XMLNode, SalesCustLedgerEntryRecRef);
        exit(true);
    end;

    local procedure AddEmittedPayments(var XMLNode: DotNet XmlNode; PurchaseVendorLedgerEntryRecRef: RecordRef)
    var
        VendorLedgerEntry: Record "Vendor Ledger Entry";
        PaymentDetailedVendorLedgEntry: Record "Detailed Vendor Ledg. Entry";
    begin
        if SIIManagement.IsLedgerCashFlowBased(PurchaseVendorLedgerEntryRecRef) then begin
            PurchaseVendorLedgerEntryRecRef.SetTable(VendorLedgerEntry);
            VendorLedgerEntry.SetRange("Document Type", VendorLedgerEntry."Document Type");
            VendorLedgerEntry.SetRange("Document No.", VendorLedgerEntry."Document No.");
            if VendorLedgerEntry.FindSet then
                repeat
                    if SIIManagement.FindPaymentDetailedVendorLedgerEntries(PaymentDetailedVendorLedgEntry, VendorLedgerEntry) then
                        repeat
                            AddPayment(
                              XMLNode, 'Pago', PaymentDetailedVendorLedgEntry."Posting Date",
                              PaymentDetailedVendorLedgEntry.Amount, VendorLedgerEntry."Payment Method Code");
                        until PaymentDetailedVendorLedgEntry.Next = 0;
                until VendorLedgerEntry.Next = 0;
        end;
    end;

    local procedure AddReceivedPayments(var XMLNode: DotNet XmlNode; SalesCustLedgerEntryRecRef: RecordRef)
    var
        CustLedgerEntry: Record "Cust. Ledger Entry";
        PaymentDetailedCustLedgEntry: Record "Detailed Cust. Ledg. Entry";
    begin
        if SIIManagement.IsLedgerCashFlowBased(SalesCustLedgerEntryRecRef) then begin
            SalesCustLedgerEntryRecRef.SetTable(CustLedgerEntry);
            CustLedgerEntry.SetRange("Document Type", CustLedgerEntry."Document Type");
            CustLedgerEntry.SetRange("Document No.", CustLedgerEntry."Document No.");
            if CustLedgerEntry.FindSet then
                repeat
                    if SIIManagement.FindPaymentDetailedCustomerLedgerEntries(PaymentDetailedCustLedgEntry, CustLedgerEntry) then
                        repeat
                            AddPayment(
                              XMLNode, 'Cobro', PaymentDetailedCustLedgEntry."Posting Date",
                              PaymentDetailedCustLedgEntry.Amount, CustLedgerEntry."Payment Method Code");
                        until PaymentDetailedCustLedgEntry.Next = 0;
                until CustLedgerEntry.Next = 0;
        end;
    end;

    local procedure AddPayment(var XMLNode: DotNet XmlNode; PmtHeaderTxt: Text; PostingDate: Date; Amount: Decimal; PaymentMethodCode: Code[10])
    var
        TempXMLNode: DotNet XmlNode;
        BaseXMLNode: DotNet XmlNode;
    begin
        BaseXMLNode := XMLNode;
        XMLDOMManagement.AddElementWithPrefix(XMLNode, PmtHeaderTxt, '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Fecha', FormatDate(PostingDate), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Importe', FormatNumber(Abs(Amount)), 'sii', SiiTxt, TempXMLNode);
        InsertMedioNode(XMLNode, PaymentMethodCode);
        XMLNode := BaseXMLNode;
    end;

    local procedure CalculateNonExemptVATEntries(var TempVATEntryOut: Record "VAT Entry" temporary; TempVATEntry: Record "VAT Entry" temporary; SplitByEUService: Boolean; VATAmount: Decimal)
    begin
        TempVATEntryOut.SetRange("VAT %", TempVATEntry."VAT %");
        TempVATEntryOut.SetRange("EC %", TempVATEntry."EC %");
        if SplitByEUService then
            TempVATEntryOut.SetRange("EU Service", TempVATEntry."EU Service");
        if TempVATEntryOut.FindFirst then begin
            TempVATEntryOut.Amount += VATAmount;
            TempVATEntryOut.Base += TempVATEntry.Base + TempVATEntry."Unrealized Base";
            TempVATEntryOut.Modify();
        end else begin
            TempVATEntryOut.Init();
            TempVATEntryOut.Copy(TempVATEntry);
            TempVATEntryOut.Amount := VATAmount;
            TempVATEntryOut.Base := TempVATEntryOut.Base + TempVATEntryOut."Unrealized Base";
            TempVATEntryOut.Insert();
        end;
        TempVATEntryOut.SetRange("VAT %");
        TempVATEntryOut.SetRange("EC %");
        TempVATEntryOut.SetRange("EU Service");
    end;

    local procedure CreateInvoicesIssuedLedgerXml(CustLedgerEntry: Record "Cust. Ledger Entry"; var XMLDocOut: DotNet XmlDocument; UploadType: Option; IsCreditMemoRemoval: Boolean): Boolean
    var
        XMLNode: DotNet XmlNode;
        DocumentType: Option Sales,Purchase,"Intra Community","Payment Received","Payment Sent","Collection In Cash";
    begin
        if not CompanyInformation.Get then begin
            ErrorMsg := CompanyInformationMissingErr;
            exit(false);
        end;

        PopulateXmlPrerequisites(
          XMLDocOut, XMLNode, DocumentType::Sales, CompanyInformation.Name, CompanyInformation."VAT Registration No.",
          IsCreditMemoRemoval, UploadType);

        LastXMLNode := XMLNode;
        exit(PopulateXMLWithSalesInvoice(XMLNode, CustLedgerEntry));
    end;

    local procedure CreateInvoicesReceivedLedgerXml(VendorLedgerEntry: Record "Vendor Ledger Entry"; var XMLDocOut: DotNet XmlDocument; UploadType: Option; IsCreditMemoRemoval: Boolean): Boolean
    var
        XMLNode: DotNet XmlNode;
        DocumentType: Option Sales,Purchase,"Intra Community","Payment Received","Payment Sent","Collection In Cash";
    begin
        if not CompanyInformation.Get then begin
            ErrorMsg := CompanyInformationMissingErr;
            exit(false);
        end;

        PopulateXmlPrerequisites(
          XMLDocOut, XMLNode, DocumentType::Purchase, CompanyInformation.Name, CompanyInformation."VAT Registration No.",
          IsCreditMemoRemoval, UploadType);

        LastXMLNode := XMLNode;
        exit(PopulateXMLWithPurchInvoice(XMLNode, VendorLedgerEntry));
    end;

    local procedure CreateCollectionInCashXml(var XMLDocOut: DotNet XmlDocument; CustLedgEntry: Record "Cust. Ledger Entry"; UploadType: Option): Boolean
    var
        XMLNode: DotNet XmlNode;
        DocumentType: Option Sales,Purchase,"Intra Community","Payment Received","Payment Sent","Collection In Cash";
    begin
        if not CompanyInformation.Get then begin
            ErrorMsg := CompanyInformationMissingErr;
            exit(false);
        end;

        PopulateXmlPrerequisites(
          XMLDocOut, XMLNode, DocumentType::"Collection In Cash", CompanyInformation.Name, CompanyInformation."VAT Registration No.",
          false, UploadType);

        LastXMLNode := XMLNode;
        exit(PopulateXMLWithCollectionInCash(XMLNode, CustLedgEntry));
    end;

    local procedure FindCustLedgerEntryOfRefDocument(CustLedgerEntry: Record "Cust. Ledger Entry"; var OldCustLedgerEntry: Record "Cust. Ledger Entry"; CorrectedInvoiceNo: Code[20]): Boolean
    begin
        if CustLedgerEntry."Document Type" <> CustLedgerEntry."Document Type"::"Credit Memo" then
            exit(false);
        if CorrectedInvoiceNo = '' then
            exit(false);
        OldCustLedgerEntry.SetRange("Document No.", CorrectedInvoiceNo);
        OldCustLedgerEntry.SetRange("Document Type", CustLedgerEntry."Document Type"::Invoice);
        exit(OldCustLedgerEntry.FindFirst);
    end;

    local procedure FindVendorLedgerEntryOfRefDocument(VendorLedgerEntry: Record "Vendor Ledger Entry"; var OldVendorLedgerEntry: Record "Vendor Ledger Entry"; CorrectedInvoiceNo: Code[20]): Boolean
    begin
        if VendorLedgerEntry."Document Type" <> VendorLedgerEntry."Document Type"::"Credit Memo" then
            exit(false);
        if CorrectedInvoiceNo = '' then
            exit(false);
        OldVendorLedgerEntry.SetRange("Document No.", CorrectedInvoiceNo);
        OldVendorLedgerEntry.SetRange("Document Type", VendorLedgerEntry."Document Type"::Invoice);
        exit(OldVendorLedgerEntry.FindFirst);
    end;

    local procedure PopulateXmlPrerequisites(var XMLDoc: DotNet XmlDocument; var XMLNode: DotNet XmlNode; DocumentType: Option Sales,Purchase,"Intra Community","Payment Received","Payment Sent","Collection In Cash"; Name: Text; VATRegistrationNo: Text; IsCreditMemoRemoval: Boolean; UploadType: Option)
    var
        RootXMLNode: DotNet XmlNode;
        CurrentXMlNode: DotNet XmlNode;
        XMLNamespaceManager: DotNet XmlNamespaceManager;
        CurrentSIIVersion: Option "1.1","1.0";
    begin
        if IsInitialized then begin
            XMLNode := LastXMLNode;
            exit;
        end;
        IsInitialized := true;

        XMLDOMManagement.AddRootElementWithPrefix(XMLDoc, 'Envelope', 'soapenv', SoapenvTxt, RootXMLNode);
        XMLDOMManagement.AddAttribute(RootXMLNode, 'xmlns:sii', SiiTxt);
        XMLDOMManagement.AddAttribute(RootXMLNode, 'xmlns:siiLR', SiiLRTxt);
        XMLDOMManagement.AddDeclaration(XMLDoc, '1.0', 'UTF-8', '');
        XMLNamespaceManager := XMLNamespaceManager.XmlNamespaceManager(RootXMLNode.OwnerDocument.NameTable);
        XMLNamespaceManager.AddNamespace('siiLR', SiiLRTxt);
        XMLNamespaceManager.AddNamespace('sii', SiiTxt);

        XMLDOMManagement.AddElementWithPrefix(RootXMLNode, 'Header', '', 'soapenv', SoapenvTxt, CurrentXMlNode);
        XMLDOMManagement.AddElementWithPrefix(RootXMLNode, 'Body', '', 'soapenv', SoapenvTxt, CurrentXMlNode);
        case DocumentType of
            DocumentType::Sales:
                if IsCreditMemoRemoval then
                    XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'BajaLRFacturasEmitidas', '', 'siiLR', SiiLRTxt, CurrentXMlNode)
                else
                    XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'SuministroLRFacturasEmitidas', '', 'siiLR', SiiLRTxt, CurrentXMlNode);
            DocumentType::Purchase:
                if IsCreditMemoRemoval then
                    XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'BajaLRFacturasRecibidas', '', 'siiLR', SiiLRTxt, CurrentXMlNode)
                else
                    XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'SuministroLRFacturasRecibidas', '', 'siiLR', SiiLRTxt, CurrentXMlNode);
            DocumentType::"Intra Community":
                XMLDOMManagement.AddElementWithPrefix(
                  CurrentXMlNode, 'SuministroLRDetOperacionIntracomunitaria', '', 'siiLR', SiiLRTxt, CurrentXMlNode);
            DocumentType::"Payment Received":
                XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'SuministroLRCobrosEmitidas', '', 'siiLR', SiiLRTxt, CurrentXMlNode);
            DocumentType::"Payment Sent":
                XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'SuministroLRPagosRecibidas', '', 'siiLR', SiiLRTxt, CurrentXMlNode);
            DocumentType::"Collection In Cash":
                XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'SuministroLRCobrosMetalico', '', 'siiLR', SiiLRTxt, CurrentXMlNode);
        end;
        XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'Cabecera', '', 'sii', SiiTxt, CurrentXMlNode);
        CurrentSIIVersion := GetSIIVersionNo;
        XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'IDVersionSii', Format(CurrentSIIVersion), 'sii', SiiTxt, XMLNode); // API version
        XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'Titular', '', 'sii', SiiTxt, CurrentXMlNode);
        FillCompanyInfo(CurrentXMlNode, Name, VATRegistrationNo);
        XMLDOMManagement.FindNode(CurrentXMlNode, '..', CurrentXMlNode);

        if not (DocumentType in [DocumentType::"Payment Received", DocumentType::"Payment Sent"]) and not IsCreditMemoRemoval then
            if (UploadType = UploadTypeGlb::RetryAccepted) or RetryAccepted then
                XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'TipoComunicacion', 'A1', 'sii', SiiTxt, XMLNode)
            else
                XMLDOMManagement.AddElementWithPrefix(CurrentXMlNode, 'TipoComunicacion', 'A0', 'sii', SiiTxt, XMLNode);

        XMLDOMManagement.FindNode(CurrentXMlNode, '..', CurrentXMlNode);
        XMLNode := CurrentXMlNode;
    end;

    local procedure PopulateXMLWithSalesInvoice(XMLNode: DotNet XmlNode; CustLedgerEntry: Record "Cust. Ledger Entry"): Boolean
    var
        SIIDocUploadState: Record "SII Doc. Upload State";
        Customer: Record Customer;
        TempServVATEntryCalcNonExempt: Record "VAT Entry" temporary;
        TempGoodsVATEntryCalcNonExempt: Record "VAT Entry" temporary;
        TempXMLNode: DotNet XmlNode;
        DesgloseFacturaXMLNode: DotNet XmlNode;
        DesgloseTipoOperacionXMLNode: DotNet XmlNode;
        DomesticXMLNode: DotNet XmlNode;
        EUServiceXMLNode: DotNet XmlNode;
        NonEUServiceXMLNode: DotNet XmlNode;
        CustLedgerEntryRecRef: RecordRef;
        NonExemptTransactionType: array[2] of Option S1,S2,S3,Initial;
        ExemptionCausePresent: array[2, 10] of Boolean;
        ExemptExists: array[2] of Boolean;
        AddNodeForTotals: Boolean;
        ExemptionBaseAmounts: array[2, 10] of Decimal;
        TotalBase: Decimal;
        TotalNonExemptBase: Decimal;
        TotalVATAmount: Decimal;
        TotalAmount: Decimal;
        InvoiceType: Text;
        DomesticCustomer: Boolean;
        RegimeCode: Code[2];
    begin
        Customer.Get(SIIManagement.GetCustFromLedgEntryByGLSetup(CustLedgerEntry));
        DomesticCustomer := SIIManagement.IsDomesticCustomer(Customer);

        SIIDocUploadState.GetSIIDocUploadStateByCustLedgEntry(CustLedgerEntry);
        if IsSalesInvoice(InvoiceType, SIIDocUploadState) then begin
            InitializeSalesXmlBody(XMLNode, CustLedgerEntry."Posting Date");

            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'NumSerieFacturaEmisor', Format(CustLedgerEntry."Document No."), 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(CustLedgerEntry."Posting Date"), 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'FacturaExpedida', '', 'siiLR', SiiLRTxt, XMLNode);

            if InvoiceType = '' then
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoFactura', 'F1', 'sii', SiiTxt, TempXMLNode)
            else
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode, 'TipoFactura', InvoiceType, 'sii', SiiTxt, TempXMLNode);

            GenerateNodeForFechaOperacionSales(XMLNode, CustLedgerEntry);
            RegimeCode := GenerateClaveRegimenNodeSales(XMLNode, SIIDocUploadState, CustLedgerEntry, Customer);

            // 0) We may have both Services and Goods parts in the same document
            // 1) Build node for Services
            // 2) Build node for Goods
            if not DomesticCustomer then
                GetSourceForServiceOrGoods(
                  TempServVATEntryCalcNonExempt, ExemptionCausePresent[1], ExemptionBaseAmounts[1],
                  NonExemptTransactionType[1], ExemptExists[1], CustLedgerEntry, true, DomesticCustomer);
            GetSourceForServiceOrGoods(
              TempGoodsVATEntryCalcNonExempt, ExemptionCausePresent[2], ExemptionBaseAmounts[2],
              NonExemptTransactionType[2], ExemptExists[2], CustLedgerEntry, false, DomesticCustomer);

            AddNodeForTotals :=
              ((InvoiceType in ['F2', 'F4']) and
               (TempServVATEntryCalcNonExempt.Count + TempGoodsVATEntryCalcNonExempt.Count = 1)) or
              (SIIDocUploadState."Sales Special Scheme Code" in [SIIDocUploadState."Sales Special Scheme Code"::"03 Special System",
                                                                 SIIDocUploadState."Sales Special Scheme Code"::"05 Travel Agencies"]);
            DataTypeManagement.GetRecordRef(CustLedgerEntry, CustLedgerEntryRecRef);
            CalculateTotalVatAndBaseAmounts(CustLedgerEntryRecRef, TotalBase, TotalNonExemptBase, TotalVATAmount);
            if AddNodeForTotals then begin
                TotalAmount := -TotalBase - TotalVATAmount;
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode, 'ImporteTotal', FormatNumber(TotalAmount), 'sii', SiiTxt, TempXMLNode);
            end;
            FillBaseImponibleACosteNode(XMLNode, RegimeCode, -TotalNonExemptBase);

            FillOperationDescription(
              XMLNode, GetOperationDescriptionFromDocument(true, CustLedgerEntry."Document No."),
              CustLedgerEntry."Posting Date", CustLedgerEntry.Description);
            FillRefExternaNode(XMLNode, Format(SIIDocUploadState."Entry No"));
            FillSucceededCompanyInfo(XMLNode, SIIDocUploadState);
            if AddNodeForTotals then
                FillMacrodatoNode(XMLNode, TotalAmount);

            if IncludeContraparteNodeBySalesInvType(InvoiceType) then begin
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Contraparte', '', 'sii', SiiTxt, XMLNode);
                FillThirdPartyId(
                  XMLNode, Customer."Country/Region Code", Customer.Name, Customer."VAT Registration No.", Customer."No.", true,
                  SIIManagement.CustomerIsIntraCommunity(Customer."No."), Customer."Not in AEAT", SIIDocUploadState.IDType);
            end;

            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoDesglose', '', 'sii', SiiTxt, XMLNode);
            if DomesticCustomer then
                GenerateNodeForServicesOrGoodsDomesticCustomer(
                  TempGoodsVATEntryCalcNonExempt, TempServVATEntryCalcNonExempt, XMLNode, DesgloseFacturaXMLNode, DomesticXMLNode,
                  DesgloseTipoOperacionXMLNode, EUServiceXMLNode, NonEUServiceXMLNode, ExemptionCausePresent, ExemptionBaseAmounts,
                  NonExemptTransactionType, ExemptExists, CustLedgerEntry, DomesticCustomer, RegimeCode)
            else
                GenerateNodeForServicesOrGoodsForeignCustomer(
                  TempGoodsVATEntryCalcNonExempt, TempServVATEntryCalcNonExempt, XMLNode, DesgloseFacturaXMLNode, DomesticXMLNode,
                  DesgloseTipoOperacionXMLNode, EUServiceXMLNode, NonEUServiceXMLNode, ExemptionCausePresent, ExemptionBaseAmounts,
                  NonExemptTransactionType, ExemptExists, CustLedgerEntry, DomesticCustomer, RegimeCode);
            exit(true);
        end;

        exit(HandleCorrectiveInvoiceSales(XMLNode, SIIDocUploadState, CustLedgerEntry, Customer));
    end;

    local procedure PopulateXMLWithPurchInvoice(XMLNode: DotNet XmlNode; VendorLedgerEntry: Record "Vendor Ledger Entry"): Boolean
    var
        SIIDocUploadState: Record "SII Doc. Upload State";
        TempVATEntryNormalCalculated: Record "VAT Entry" temporary;
        TempVATEntryReverseChargeCalculated: Record "VAT Entry" temporary;
        VATEntry: Record "VAT Entry";
        Vendor: Record Vendor;
        TempXMLNode: DotNet XmlNode;
        VendorLedgerEntryRecRef: RecordRef;
        AddNodeForTotals: Boolean;
        CuotaDeducibleValue: Decimal;
        TotalBase: Decimal;
        TotalNonExemptBase: Decimal;
        TotalVATAmount: Decimal;
        TotalAmount: Decimal;
        InvoiceType: Text;
        RegimeCode: Code[2];
        VendNo: Code[20];
    begin
        Vendor.Get(VendorLedgerEntry."Vendor No.");
        DataTypeManagement.GetRecordRef(VendorLedgerEntry, VendorLedgerEntryRecRef);

        SIIDocUploadState.GetSIIDocUploadStateByVendLedgEntry(VendorLedgerEntry);
        if IsPurchInvoice(InvoiceType, SIIDocUploadState) then begin
            VendNo := SIIManagement.GetVendFromLedgEntryByGLSetup(VendorLedgerEntry);
            InitializePurchXmlBody(
              XMLNode, VendNo, VendorLedgerEntry."Posting Date", SIIDocUploadState.IDType);

            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'NumSerieFacturaEmisor', Format(VendorLedgerEntry."External Document No."), 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(VendorLedgerEntry."Document Date"), 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'FacturaRecibida', '', 'siiLR', SiiLRTxt, XMLNode);

            if InvoiceType = '' then
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoFactura', 'F1', 'sii', SiiTxt, TempXMLNode)
            else
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode, 'TipoFactura', InvoiceType, 'sii', SiiTxt, TempXMLNode);

            GenerateNodeForFechaOperacionPurch(XMLNode, VendorLedgerEntry);
            RegimeCode := GenerateClaveRegimenNodePurchases(XMLNode, SIIDocUploadState, VendorLedgerEntry, Vendor);
            if SIIManagement.FindVatEntriesFromLedger(VendorLedgerEntryRecRef, VATEntry) then begin
                repeat
                    CalculatePurchVATEntries(
                      TempVATEntryNormalCalculated, TempVATEntryReverseChargeCalculated,
                      CuotaDeducibleValue, VATEntry,
                      VendNo, VendorLedgerEntry."Posting Date");
                until VATEntry.Next = 0;
            end;

            AddNodeForTotals :=
              ((InvoiceType in ['F2', 'F4']) and
               (TempVATEntryNormalCalculated.Count + TempVATEntryReverseChargeCalculated.Count = 1)) or
              (SIIDocUploadState."Purch. Special Scheme Code" in [SIIDocUploadState."Purch. Special Scheme Code"::"03 Special System",
                                                                  SIIDocUploadState."Purch. Special Scheme Code"::"05 Travel Agencies"]);
            CalculateTotalVatAndBaseAmounts(VendorLedgerEntryRecRef, TotalBase, TotalNonExemptBase, TotalVATAmount);
            if AddNodeForTotals then begin
                TotalAmount := TotalBase + TotalVATAmount;
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode, 'ImporteTotal', FormatNumber(TotalAmount), 'sii', SiiTxt, TempXMLNode);
            end;
            FillBaseImponibleACosteNode(XMLNode, RegimeCode, TotalNonExemptBase);

            FillOperationDescription(
              XMLNode, GetOperationDescriptionFromDocument(false, VendorLedgerEntry."Document No."),
              VendorLedgerEntry."Posting Date", VendorLedgerEntry.Description);
            FillRefExternaNode(XMLNode, Format(SIIDocUploadState."Entry No"));
            FillSucceededCompanyInfo(XMLNode, SIIDocUploadState);
            if AddNodeForTotals then
                FillMacrodatoNode(XMLNode, TotalAmount);

            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DesgloseFactura', '', 'sii', SiiTxt, XMLNode);

            AddPurchVATEntriesWithElement(XMLNode, TempVATEntryReverseChargeCalculated, 'InversionSujetoPasivo', RegimeCode);
            FillNoTaxableVATEntriesPurch(TempVATEntryNormalCalculated, VendorLedgerEntry);
            AddPurchVATEntriesWithElement(XMLNode, TempVATEntryNormalCalculated, 'DesgloseIVA', RegimeCode);
            XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);

            AddPurchTail(
              XMLNode, VendorLedgerEntry."Posting Date", GetRequestDateOfSIIHistoryByVendLedgEntry(VendorLedgerEntry),
              VendNo, CuotaDeducibleValue, SIIDocUploadState.IDType, RegimeCode);

            exit(true);
        end;

        // corrective invoice
        exit(HandleCorrectiveInvoicePurchases(XMLNode, SIIDocUploadState, VendorLedgerEntry, Vendor));
    end;

    local procedure PopulateXMLWithCollectionInCash(XMLNode: DotNet XmlNode; CustLedgerEntry: Record "Cust. Ledger Entry"): Boolean
    var
        Customer: Record Customer;
        SIIDocUploadState: Record "SII Doc. Upload State";
        TempXMLNode: DotNet XmlNode;
    begin
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'RegistroLRCobrosMetalico', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, FillDocHeaderNode, '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Ejercicio', GetYear(CustLedgerEntry."Posting Date"), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Periodo', '0A', 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Contraparte', '', 'siiLR', SiiLRTxt, XMLNode);
        Customer.Get(SIIManagement.GetCustFromLedgEntryByGLSetup(CustLedgerEntry));
        SIIDocUploadState.GetSIIDocUploadStateByCustLedgEntry(CustLedgerEntry);
        FillThirdPartyId(
          XMLNode, Customer."Country/Region Code", Customer.Name, Customer."VAT Registration No.", Customer."No.", true,
          SIIManagement.CustomerIsIntraCommunity(Customer."No."), Customer."Not in AEAT", SIIDocUploadState.IDType);

        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'ImporteTotal', FormatNumber(CustLedgerEntry."Sales (LCY)"), 'siiLR', SiiLRTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
        exit(true);
    end;

    local procedure AddPurchVATEntriesWithElement(var XMLNode: DotNet XmlNode; var TempVATEntryCalculated: Record "VAT Entry" temporary; XMLNodeName: Text; RegimeCode: Code[2])
    begin
        if TempVATEntryCalculated.IsEmpty then
            exit;
        XMLDOMManagement.AddElementWithPrefix(XMLNode, XMLNodeName, '', 'sii', SiiTxt, XMLNode);
        AddPurchVATEntries(XMLNode, TempVATEntryCalculated, RegimeCode);
    end;

    [Scope('OnPrem')]
    procedure FormatDate(Value: Date): Text
    begin
        exit(Format(Value, 0, '<Day,2>-<Month,2>-<Year4>'));
    end;

    [Scope('OnPrem')]
    procedure FormatNumber(Number: Decimal): Text
    begin
        exit(Format(Number, 0, '<Precision,2:2><Standard Format,9>'));
    end;

    local procedure GetYear(Value: Date): Text
    begin
        exit(Format(Date2DMY(Value, 3)));
    end;

    local procedure InitializeCorrectiveRemovalXmlBody(var XMLNode: DotNet XmlNode; NewPostingDate: Date; IsSales: Boolean; SIIDocUploadState: Record "SII Doc. Upload State"; Name: Text; VATNo: Code[20]; CountryCode: Code[20]; ThirdPartyId: Code[20]; NotInAEAT: Boolean)
    var
        TempXMLNode: DotNet XmlNode;
        IssuerName: Text;
        IssuerVATNo: Code[20];
        IssuerCountryCode: Code[20];
        IssuerBackupVatNo: Code[20];
        IsIssuerIntraCommunity: Boolean;
    begin
        if IsSales then
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'RegistroLRBajaExpedidas', '', 'siiLR', SiiLRTxt, XMLNode)
        else
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'RegistroLRBajaRecibidas', '', 'siiLR', SiiLRTxt, XMLNode);

        XMLDOMManagement.AddElementWithPrefix(XMLNode, FillDocHeaderNode, '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'Ejercicio', GetYear(NewPostingDate), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'Periodo', Format(NewPostingDate, 0, '<Month,2>'), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDFactura', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDEmisorFactura', '', 'sii', SiiTxt, XMLNode);

        if IsSales then begin
            IssuerName := CompanyInformation.Name;
            IssuerVATNo := CompanyInformation."VAT Registration No.";
            IssuerCountryCode := CompanyInformation."Country/Region Code";
            IssuerBackupVatNo := CompanyInformation."VAT Registration No.";
            IsIssuerIntraCommunity := false;
        end else begin
            IssuerName := Name;
            IssuerVATNo := VATNo;
            IssuerCountryCode := CountryCode;
            IssuerBackupVatNo := ThirdPartyId;
            IsIssuerIntraCommunity := false;
        end;

        FillThirdPartyId(
          XMLNode,
          IssuerCountryCode,
          IssuerName,
          IssuerVATNo,
          IssuerBackupVatNo,
          not IsSales,
          IsIssuerIntraCommunity,
          NotInAEAT, SIIDocUploadState.IDType);

        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'NumSerieFacturaEmisor', Format(SIIDocUploadState."Corrected Doc. No."), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(SIIDocUploadState."Corr. Posting Date"), 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure InitializeSalesXmlBody(var XMLNode: DotNet XmlNode; PostingDate: Date)
    var
        TempXMLNode: DotNet XmlNode;
    begin
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'RegistroLRFacturasEmitidas', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, FillDocHeaderNode, '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'Ejercicio', GetYear(PostingDate), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'Periodo', Format(PostingDate, 0, '<Month,2>'), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDFactura', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDEmisorFactura', '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'NIF', CompanyInformation."VAT Registration No.", 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
    end;

    local procedure InitializePurchXmlBody(var XMLNode: DotNet XmlNode; VendorNo: Code[20]; PostingDate: Date; IDType: Integer)
    var
        Vendor: Record Vendor;
        TempXMLNode: DotNet XmlNode;
    begin
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'RegistroLRFacturasRecibidas', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, FillDocHeaderNode, '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'Ejercicio', GetYear(PostingDate), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'Periodo', Format(PostingDate, 0, '<Month,2>'), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDFactura', '', 'siiLR', SiiLRTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDEmisorFactura', '', 'sii', SiiTxt, XMLNode);
        Vendor.Get(VendorNo);
        FillThirdPartyId(
          XMLNode, Vendor."Country/Region Code", Vendor.Name, Vendor."VAT Registration No.", Vendor."No.", false,
          SIIManagement.VendorIsIntraCommunity(Vendor."No."), false, IDType);
    end;

    local procedure AddPurchVATEntries(var XMLNode: DotNet XmlNode; var TempVATEntry: Record "VAT Entry" temporary; RegimeCode: Code[2])
    begin
        TempVATEntry.Reset();
        TempVATEntry.SetCurrentKey("VAT %", "EC %");
        if TempVATEntry.FindSet then
            repeat
                FillDetalleIVANode(XMLNode, TempVATEntry, true, 1, true, 0, RegimeCode, 'CuotaSoportada');
            until TempVATEntry.Next = 0;
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
    end;

    local procedure AddPurchTail(var XMLNode: DotNet XmlNode; PostingDate: Date; RequestDate: Date; BuyFromVendorNo: Code[20]; CuotaDeducibleValue: Decimal; IDType: Integer; RegimeCode: Code[2])
    var
        Vendor: Record Vendor;
        TempXMLNode: DotNet XmlNode;
    begin
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Contraparte', '', 'sii', SiiTxt, XMLNode);
        Vendor.Get(BuyFromVendorNo);
        FillThirdPartyId(
          XMLNode, Vendor."Country/Region Code", Vendor.Name, Vendor."VAT Registration No.", Vendor."No.",
          true, SIIManagement.VendorIsIntraCommunity(Vendor."No."), false, IDType);

        FillFechaRegContable(XMLNode, PostingDate, RequestDate);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'CuotaDeducible', FormatNumber(CalcCuotaDeducible(PostingDate, RegimeCode, CuotaDeducibleValue)),
          'sii', SiiTxt, TempXMLNode);
    end;

    local procedure FillThirdPartyId(var XMLNode: DotNet XmlNode; CountryCode: Code[20]; Name: Text; VatNo: Code[20]; BackupVatId: Code[20]; NeedNombreRazon: Boolean; IsIntraCommunity: Boolean; IsNotInAEAT: Boolean; IDTypeInt: Integer)
    var
        TempXMLNode: DotNet XmlNode;
        IDType: Text[30];
    begin
        if VatNo = '' then
            VatNo := BackupVatId;
        IDType := GetIDTypeToExport(IDTypeInt);
        if SIIManagement.CountryAndVATRegNoAreLocal(CountryCode, VatNo) then begin
            if NeedNombreRazon then
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'NombreRazon', Name, 'sii', SiiTxt, TempXMLNode);
            if IsNotInAEAT then begin
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDOtro', '', 'sii', SiiTxt, XMLNode);
                // In case of self employment, we use '07' means "Unregistered"
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'CodigoPais', CountryCode, 'sii', SiiTxt, TempXMLNode);
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDType', IDType, 'sii', SiiTxt, TempXMLNode);
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'ID', VatNo, 'sii', SiiTxt, TempXMLNode);
                XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
            end else
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'NIF', VatNo, 'sii', SiiTxt, TempXMLNode);
        end else begin
            if NeedNombreRazon then
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'NombreRazon', Name, 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDOtro', '', 'sii', SiiTxt, XMLNode);
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'CodigoPais', CountryCode, 'sii', SiiTxt, TempXMLNode);

            if IsIntraCommunity then
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDType', IDType, 'sii', SiiTxt, TempXMLNode)
            else
                if IsNotInAEAT then
                    // In case of self employment, we use '07' means "Unregistered"
                    XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDType', IDType, 'sii', SiiTxt, TempXMLNode)
                else
                    XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDType', IDType, 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'ID', VatNo, 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
        end;
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
    end;

    local procedure AddTipoDesgloseDetailHeader(var TipoDesgloseXMLNode: DotNet XmlNode; var DesgloseFacturaXMLNode: DotNet XmlNode; var DomesticXMLNode: DotNet XmlNode; var DesgloseTipoOperacionXMLNode: DotNet XmlNode; var EUXMLNode: DotNet XmlNode; var VATXMLNode: DotNet XmlNode; EUService: Boolean; DomesticCustomer: Boolean; NoTaxableVAT: Boolean)
    var
        VATNodeName: Text;
    begin
        VATNodeName := GetVATNodeName(NoTaxableVAT);
        if DomesticCustomer then begin
            if IsNull(DesgloseFacturaXMLNode) then
                XMLDOMManagement.AddElementWithPrefix(TipoDesgloseXMLNode, 'DesgloseFactura', '', 'sii', SiiTxt, DesgloseFacturaXMLNode);
            if IsNull(DomesticXMLNode) then
                XMLDOMManagement.AddElementWithPrefix(DesgloseFacturaXMLNode, VATNodeName, '', 'sii', SiiTxt, DomesticXMLNode);
            VATXMLNode := DomesticXMLNode;
        end else begin
            if IsNull(DesgloseTipoOperacionXMLNode) then
                XMLDOMManagement.AddElementWithPrefix(
                  TipoDesgloseXMLNode, 'DesgloseTipoOperacion', '', 'sii', SiiTxt, DesgloseTipoOperacionXMLNode);
            if EUService then
                AddVATXMLNodeUnderParentNode(EUXMLNode, VATXMLNode, DesgloseTipoOperacionXMLNode, 'PrestacionServicios', VATNodeName)
            else
                AddVATXMLNodeUnderParentNode(EUXMLNode, VATXMLNode, DesgloseTipoOperacionXMLNode, 'Entrega', VATNodeName);
        end;
    end;

    local procedure AddVATXMLNodeUnderParentNode(var EUXMLNode: DotNet XmlNode; var VATXMLNode: DotNet XmlNode; DesgloseTipoOperacionXMLNode: DotNet XmlNode; ParentVATNodeName: Text; VATNodeName: Text)
    begin
        if IsNull(EUXMLNode) then
            XMLDOMManagement.AddElementWithPrefix(DesgloseTipoOperacionXMLNode, ParentVATNodeName, '', 'sii', SiiTxt, EUXMLNode);
        if IsNull(VATXMLNode) then
            XMLDOMManagement.AddElementWithPrefix(EUXMLNode, VATNodeName, '', 'sii', SiiTxt, VATXMLNode);
    end;

    local procedure FillSucceededCompanyInfo(var XMLNode: DotNet XmlNode; SIIDocUploadState: Record "SII Doc. Upload State")
    begin
        if (not IncludeChangesVersion11) or (SIIDocUploadState."Succeeded Company Name" = '') then
            exit;

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'EntidadSucedida', '', 'sii', SiiTxt, XMLNode);
        FillCompanyInfo(XMLNode, SIIDocUploadState."Succeeded Company Name", SIIDocUploadState."Succeeded VAT Registration No.");
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
    end;

    local procedure FillCompanyInfo(var XMLNode: DotNet XmlNode; Name: Text; VATRegistrationNo: Text)
    var
        TempXMLNode: DotNet XmlNode;
    begin
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'NombreRazon', Name, 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'NIF', VATRegistrationNo, 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure CalculateECAmount(Base: Decimal; ECPercentage: Decimal): Decimal
    var
        GeneralLedgerSetup: Record "General Ledger Setup";
    begin
        GeneralLedgerSetup.Get();
        exit(Round(Base * ECPercentage / 100, GeneralLedgerSetup."Amount Rounding Precision"));
    end;

    local procedure CalculateNonTaxableAmountVendor(VendLedgEntry: Record "Vendor Ledger Entry"): Decimal
    var
        NoTaxableEntry: Record "No Taxable Entry";
    begin
        if SIIManagement.NoTaxableEntriesExistPurchase(
             NoTaxableEntry,
             SIIManagement.GetVendFromLedgEntryByGLSetup(VendLedgEntry), VendLedgEntry."Document Type",
             VendLedgEntry."Document No.", VendLedgEntry."Posting Date")
        then begin
            NoTaxableEntry.CalcSums("Amount (LCY)");
            exit(NoTaxableEntry."Amount (LCY)");
        end;
        exit(0);
    end;

    local procedure CalcNonExemptVATEntriesWithCuotaDeducible(var TempVATEntry: Record "VAT Entry" temporary; var CuotaDeducible: Decimal; VendorLedgerEntry: Record "Vendor Ledger Entry"; Sign: Integer)
    var
        VATEntry: Record "VAT Entry";
        VendorLedgerEntryRecRef: RecordRef;
        VATAmount: Decimal;
    begin
        DataTypeManagement.GetRecordRef(VendorLedgerEntry, VendorLedgerEntryRecRef);
        if SIIManagement.FindVatEntriesFromLedger(VendorLedgerEntryRecRef, VATEntry) then
            repeat
                VATAmount := CalcVATAmountExclEC(VATEntry);
                CuotaDeducible += Sign * Abs(VATAmount);
                CalculateNonExemptVATEntries(TempVATEntry, VATEntry, true, VATAmount);
            until VATEntry.Next = 0;
    end;

    [Scope('OnPrem')]
    procedure GetLastErrorMsg(): Text
    begin
        exit(ErrorMsg);
    end;

    local procedure GetSourceForServiceOrGoods(var TempVATEntryCalculatedNonExempt: Record "VAT Entry" temporary; var ExemptionCausePresent: array[10] of Boolean; var ExemptionBaseAmounts: array[10] of Decimal; var NonExemptTransactionType: Option S1,S2,S3,Initial; var ExemptExists: Boolean; CustLedgerEntry: Record "Cust. Ledger Entry"; IsService: Boolean; DomesticCustomer: Boolean)
    var
        VATEntry: Record "VAT Entry";
        DataTypeManagement: Codeunit "Data Type Management";
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
        RecRef: RecordRef;
        ExemptionCode: Option;
    begin
        DataTypeManagement.GetRecordRef(CustLedgerEntry, RecRef);
        SIIManagement.FindVatEntriesFromLedger(RecRef, VATEntry);
        if not DomesticCustomer then
            VATEntry.SetRange("EU Service", IsService);
        if VATEntry.FindSet then begin
            if SIIInitialDocUpload.DateWithinInitialUploadPeriod(CustLedgerEntry."Posting Date") then
                NonExemptTransactionType := NonExemptTransactionType::S1
            else
                NonExemptTransactionType := NonExemptTransactionType::Initial;
            repeat
                BuildVATEntrySource(
                  ExemptExists, ExemptionCausePresent, ExemptionCode, ExemptionBaseAmounts,
                  TempVATEntryCalculatedNonExempt, NonExemptTransactionType, VATEntry, CustLedgerEntry."Posting Date", true);
            until VATEntry.Next = 0;
        end;
    end;

    local procedure GenerateNodeForServicesOrGoodsDomesticCustomer(var TempGoodsVATEntryCalcNonExempt: Record "VAT Entry" temporary; var TempServVATEntryCalcNonExempt: Record "VAT Entry" temporary; var XMLNode: DotNet XmlNode; var DesgloseFacturaXMLNode: DotNet XmlNode; var DomesticXMLNode: DotNet XmlNode; var DesgloseTipoOperacionXMLNode: DotNet XmlNode; var EUServiceXMLNode: DotNet XmlNode; var NonEUServiceXMLNode: DotNet XmlNode; ExemptionCausePresent: array[2, 10] of Boolean; ExemptionBaseAmounts: array[2, 10] of Decimal; NonExemptTransactionType: array[2] of Option S1,S2,S3,Initial; ExemptExists: array[2] of Boolean; CustLedgerEntry: Record "Cust. Ledger Entry"; DomesticCustomer: Boolean; RegimeCode: Code[2])
    begin
        GenerateNodeForServicesOrGoods(
          TempGoodsVATEntryCalcNonExempt, XMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
          EUServiceXMLNode, NonEUServiceXMLNode, ExemptionCausePresent[2], ExemptionBaseAmounts[2],
          NonExemptTransactionType[2], ExemptExists[2], CustLedgerEntry, false, DomesticCustomer, RegimeCode);
        GenerateNodeForServicesOrGoods(
          TempServVATEntryCalcNonExempt, XMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
          EUServiceXMLNode, NonEUServiceXMLNode, ExemptionCausePresent[1], ExemptionBaseAmounts[1],
          NonExemptTransactionType[1], ExemptExists[1], CustLedgerEntry, true, DomesticCustomer, RegimeCode);
    end;

    local procedure GenerateNodeForServicesOrGoodsForeignCustomer(var TempGoodsVATEntryCalcNonExempt: Record "VAT Entry" temporary; var TempServVATEntryCalcNonExempt: Record "VAT Entry" temporary; var XMLNode: DotNet XmlNode; var DesgloseFacturaXMLNode: DotNet XmlNode; var DomesticXMLNode: DotNet XmlNode; var DesgloseTipoOperacionXMLNode: DotNet XmlNode; var EUServiceXMLNode: DotNet XmlNode; var NonEUServiceXMLNode: DotNet XmlNode; ExemptionCausePresent: array[2, 10] of Boolean; ExemptionBaseAmounts: array[2, 10] of Decimal; NonExemptTransactionType: array[2] of Option S1,S2,S3,Initial; ExemptExists: array[2] of Boolean; CustLedgerEntry: Record "Cust. Ledger Entry"; DomesticCustomer: Boolean; RegimeCode: Code[2])
    begin
        GenerateNodeForServicesOrGoods(
          TempServVATEntryCalcNonExempt, XMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
          EUServiceXMLNode, NonEUServiceXMLNode, ExemptionCausePresent[1], ExemptionBaseAmounts[1],
          NonExemptTransactionType[1], ExemptExists[1], CustLedgerEntry, true, DomesticCustomer, RegimeCode);
        GenerateNodeForServicesOrGoods(
          TempGoodsVATEntryCalcNonExempt, XMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
          EUServiceXMLNode, NonEUServiceXMLNode, ExemptionCausePresent[2], ExemptionBaseAmounts[2],
          NonExemptTransactionType[2], ExemptExists[2], CustLedgerEntry, false, DomesticCustomer, RegimeCode);
    end;

    local procedure GenerateNodeForServicesOrGoods(var TempVATEntryCalculatedNonExempt: Record "VAT Entry" temporary; var TipoDesgloseXMLNode: DotNet XmlNode; var DesgloseFacturaXMLNode: DotNet XmlNode; var DomesticXMLNode: DotNet XmlNode; var DesgloseTipoOperacionXMLNode: DotNet XmlNode; var EUServiceXMLNode: DotNet XmlNode; var NonEUServiceXMLNode: DotNet XmlNode; ExemptionCausePresent: array[10] of Boolean; ExemptionBaseAmounts: array[10] of Decimal; NonExemptTransactionType: Option S1,S2,S3,Initial; ExemptExists: Boolean; CustLedgerEntry: Record "Cust. Ledger Entry"; IsService: Boolean; DomesticCustomer: Boolean; RegimeCode: Code[2])
    var
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
        TempXmlNode: DotNet XmlNode;
        BaseNode: DotNet XmlNode;
        VATXMLNode: DotNet XmlNode;
        EUXMLNode: DotNet XmlNode;
        NonTaxHandled: Boolean;
    begin
        BaseNode := TipoDesgloseXMLNode;

        if SIIInitialDocUpload.DateWithinInitialUploadPeriod(CustLedgerEntry."Posting Date") then begin
            MoveNonTaxableEntriesToTempVATEntryBuffer(TempVATEntryCalculatedNonExempt, CustLedgerEntry, IsService);
            NonTaxHandled := true;
        end;

        if ExemptExists then begin
            AddTipoDesgloseDetailHeader(
              TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode, EUXMLNode,
              VATXMLNode, IsService, DomesticCustomer, false);
            HandleExemptEntries(VATXMLNode, ExemptionCausePresent, ExemptionBaseAmounts);
        end;

        // Generating XML node for NonExempt part
        TempVATEntryCalculatedNonExempt.Reset();
        TempVATEntryCalculatedNonExempt.SetCurrentKey("VAT %", "EC %");
        if TempVATEntryCalculatedNonExempt.FindSet then begin
            AddTipoDesgloseDetailHeader(
              TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
              EUXMLNode, VATXMLNode, IsService, DomesticCustomer, false);
            XMLDOMManagement.AddElementWithPrefix(VATXMLNode, 'NoExenta', '', 'sii', SiiTxt, VATXMLNode);
            XMLDOMManagement.AddElementWithPrefix(VATXMLNode, 'TipoNoExenta', Format(NonExemptTransactionType), 'sii', SiiTxt, TempXmlNode);
            XMLDOMManagement.AddElementWithPrefix(VATXMLNode, 'DesgloseIVA', '', 'sii', SiiTxt, VATXMLNode);
            repeat
                FillDetalleIVANode(
                  VATXMLNode, TempVATEntryCalculatedNonExempt, true, -1, not IsService, NonExemptTransactionType, RegimeCode, 'CuotaRepercutida');
            until TempVATEntryCalculatedNonExempt.Next = 0;
        end;

        if not NonTaxHandled then begin
            Clear(DomesticXMLNode);
            Clear(EUServiceXMLNode);
            Clear(NonEUServiceXMLNode);
            HandleNonTaxableVATEntries(
              CustLedgerEntry,
              TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
              EUXMLNode, IsService, DomesticCustomer);
            Clear(DomesticXMLNode);
        end;

        TipoDesgloseXMLNode := BaseNode;
    end;

    local procedure GenerateNodeForNonTaxableVAT(NonTaxableAmount: Decimal; var XMLNode: DotNet XmlNode; XMLNodeName: Text)
    var
        BaseNode: DotNet XmlNode;
    begin
        BaseNode := XMLNode;

        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, XMLNodeName, FormatNumber(NonTaxableAmount), 'sii', SiiTxt, XMLNode);

        XMLNode := BaseNode;
    end;

    local procedure FillNoTaxableVATEntriesPurch(var TempVATEntryCalculated: Record "VAT Entry" temporary; VendLedgEntry: Record "Vendor Ledger Entry")
    var
        NonTaxableAmount: Decimal;
    begin
        NonTaxableAmount := CalculateNonTaxableAmountVendor(VendLedgEntry);
        if (NonTaxableAmount = 0) or
           ((VendLedgEntry."Document Type" = VendLedgEntry."Document Type"::Invoice) and (NonTaxableAmount < 0)) or
           ((VendLedgEntry."Document Type" = VendLedgEntry."Document Type"::"Credit Memo") and (NonTaxableAmount > 0))
        then
            exit;

        TempVATEntryCalculated.Reset();
        if TempVATEntryCalculated.FindLast then;

        TempVATEntryCalculated.Init();
        TempVATEntryCalculated."Entry No." += 1;
        TempVATEntryCalculated.Base := NonTaxableAmount;
        TempVATEntryCalculated.Insert();
    end;

    local procedure GetExemptionCode(VATEntry: Record "VAT Entry"; var ExemptionCode: Option): Boolean
    var
        VATPostingSetup: Record "VAT Posting Setup";
        VATClause: Record "VAT Clause";
    begin
        VATPostingSetup.Get(VATEntry."VAT Bus. Posting Group", VATEntry."VAT Prod. Posting Group");
        if VATPostingSetup."VAT Clause Code" <> '' then begin
            VATClause.Get(VATPostingSetup."VAT Clause Code");
            if VATClause."SII Exemption Code" = VATClause."SII Exemption Code"::" " then
                exit(false);
            ExemptionCode := VATClause."SII Exemption Code";
            exit(true);
        end
    end;

    local procedure CalculateExemptVATEntries(var ExemptionCausesPresent: array[10] of Boolean; var ExemptionBaseAmounts: array[10] of Decimal; TempVATEntry: Record "VAT Entry" temporary; ExemptionCode: Option)
    begin
        // We have 7 exemption codes: first is empty, the remaining are E1-E6.;
        // Options enumerated from 0, arrays - from 1.
        // We do not process "empty" exemption code here, thus no index modifications required.
        if ExemptionCausesPresent[ExemptionCode] then
            ExemptionBaseAmounts[ExemptionCode] += TempVATEntry.Base
        else begin
            ExemptionCausesPresent[ExemptionCode] := true;
            ExemptionBaseAmounts[ExemptionCode] := TempVATEntry.Base;
        end;
    end;

    local procedure CalculatePurchVATEntries(var TempVATEntryNormalCalculated: Record "VAT Entry" temporary; var TempVATEntryReverseChargeCalculated: Record "VAT Entry" temporary; var CuotaDeducibleValue: Decimal; VATEntry: Record "VAT Entry"; VendorNo: Code[20]; PostingDate: Date)
    var
        VATAmount: Decimal;
    begin
        VATAmount := VATEntry.Amount + VATEntry."Unrealized Amount";
        CuotaDeducibleValue += VATAmount;
        OnAfterCalculateCuotaDeducibleValue(CuotaDeducibleValue, VATAmount, VATEntry);

        if UseReverseChargeNotIntracommunity(VATEntry."VAT Calculation Type", VendorNo, PostingDate) then
            CalculateNonExemptVATEntries(TempVATEntryReverseChargeCalculated, VATEntry, true, VATAmount)
        else
            CalculateNonExemptVATEntries(TempVATEntryNormalCalculated, VATEntry, true, VATAmount);
    end;

    local procedure BuildNonExemptTransactionType(VATEntry: Record "VAT Entry"; var TransactionType: Option S1,S2,S3,Initial)
    begin
        // "Reverse Charge VAT" means S2
        if VATEntry."VAT Calculation Type" = VATEntry."VAT Calculation Type"::"Reverse Charge VAT" then begin
            if TransactionType = TransactionType::Initial then
                TransactionType := TransactionType::S2
            else
                if TransactionType = TransactionType::S1 then begin
                    TransactionType := TransactionType::S3
                end
        end else begin
            if TransactionType = TransactionType::Initial then
                TransactionType := TransactionType::S1
            else
                if TransactionType = TransactionType::S2 then begin
                    TransactionType := TransactionType::S3
                end
        end;
    end;

    local procedure BuildExemptionCodeString(ExemptionIndex: Integer): Text
    begin
        case ExemptionIndex of
            1:
                exit('E1');
            2:
                exit('E2');
            3:
                exit('E3');
            4:
                exit('E4');
            5:
                exit('E5');
            6:
                exit('E6');
        end;
    end;

    local procedure HandleCorrectiveInvoiceSales(var XMLNode: DotNet XmlNode; SIIDocUploadState: Record "SII Doc. Upload State"; CustLedgerEntry: Record "Cust. Ledger Entry"; Customer: Record Customer): Boolean
    var
        OldCustLedgerEntry: Record "Cust. Ledger Entry";
        SalesCrMemoHeader: Record "Sales Cr.Memo Header";
        TempXMLNode: DotNet XmlNode;
        CustLedgerEntryRecRef: RecordRef;
        TotalBase: Decimal;
        TotalNonExemptBase: Decimal;
        TotalVATAmount: Decimal;
        CorrectedInvoiceNo: Code[20];
        CorrectionType: Option;
    begin
        GetCorrectionInfoFromDocument(
          CustLedgerEntry."Document No.", CorrectedInvoiceNo, CorrectionType,
          CustLedgerEntry."Correction Type", CustLedgerEntry."Corrected Invoice No.");

        if FindCustLedgerEntryOfRefDocument(CustLedgerEntry, OldCustLedgerEntry, CorrectedInvoiceNo) then
            if CorrectionType = SalesCrMemoHeader."Correction Type"::Removal then begin
                InitializeCorrectiveRemovalXmlBody(
                  XMLNode, CustLedgerEntry."Posting Date", true, SIIDocUploadState,
                  Customer.Name, Customer."VAT Registration No.", Customer."Country/Region Code", Customer."No.", Customer."Not in AEAT");
                exit(true);
            end;
        InitializeSalesXmlBody(XMLNode, CustLedgerEntry."Posting Date");

        // calculate totals for current doc
        DataTypeManagement.GetRecordRef(CustLedgerEntry, CustLedgerEntryRecRef);
        CalculateTotalVatAndBaseAmounts(CustLedgerEntryRecRef, TotalBase, TotalNonExemptBase, TotalVATAmount);

        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'NumSerieFacturaEmisor', Format(CustLedgerEntry."Document No."), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(CustLedgerEntry."Posting Date"), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'FacturaExpedida', '', 'siiLR', SiiLRTxt, XMLNode);
        if SIIDocUploadState."Sales Cr. Memo Type" = 0 then
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoFactura', 'R1', 'sii', SiiTxt, TempXMLNode)
        else
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'TipoFactura', CopyStr(Format(SIIDocUploadState."Sales Cr. Memo Type"), 1, 2), 'sii', SiiTxt, TempXMLNode);

        if CorrectionType = SalesCrMemoHeader."Correction Type"::Replacement then
            HandleReplacementSalesCorrectiveInvoice(
              XMLNode, SIIDocUploadState, OldCustLedgerEntry, CustLedgerEntry, Customer, TotalBase, TotalNonExemptBase, TotalVATAmount)
        else
            CorrectiveInvoiceSalesDifference(
              XMLNode, SIIDocUploadState, OldCustLedgerEntry, CustLedgerEntry, Customer, TotalBase, TotalNonExemptBase, TotalVATAmount);

        exit(true);
    end;

    local procedure CorrectiveInvoiceSalesDifference(var XMLNode: DotNet XmlNode; SIIDocUploadState: Record "SII Doc. Upload State"; OldCustLedgerEntry: Record "Cust. Ledger Entry"; CustLedgerEntry: Record "Cust. Ledger Entry"; Customer: Record Customer; TotalBase: Decimal; TotalNonExemptBase: Decimal; TotalVATAmount: Decimal)
    var
        TempVATEntryPerPercent: Record "VAT Entry" temporary;
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
        TempXMLNode: DotNet XmlNode;
        TipoDesgloseXMLNode: DotNet XmlNode;
        DesgloseFacturaXMLNode: DotNet XmlNode;
        DomesticXMLNode: DotNet XmlNode;
        DesgloseTipoOperacionXMLNode: DotNet XmlNode;
        EUServiceXMLNode: DotNet XmlNode;
        NonEUServiceXMLNode: DotNet XmlNode;
        EUXMLNode: DotNet XmlNode;
        VATXMLNode: DotNet XmlNode;
        TotalAmount: Decimal;
        EUService: Boolean;
        EntriesFound: Boolean;
        DomesticCustomer: Boolean;
        NonTaxHandled: Boolean;
        RegimeCode: Code[2];
        NonExemptTransactionType: Option S1,S2,S3,Initial;
        ExemptExists: Boolean;
        ExemptionCausePresent: array[10] of Boolean;
        ExemptionBaseAmounts: array[10] of Decimal;
    begin
        DomesticCustomer := SIIManagement.IsDomesticCustomer(Customer);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoRectificativa', 'I', 'sii', SiiTxt, TempXMLNode);
        GenerateFacturasRectificadasNode(XMLNode, OldCustLedgerEntry."Document No.", OldCustLedgerEntry."Posting Date");
        RegimeCode := GenerateClaveRegimenNodeSales(XMLNode, SIIDocUploadState, CustLedgerEntry, Customer);

        TotalAmount := -TotalBase - TotalVATAmount;
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'ImporteTotal', FormatNumber(TotalAmount), 'sii', SiiTxt, TempXMLNode);
        FillBaseImponibleACosteNode(XMLNode, RegimeCode, -TotalNonExemptBase);
        FillOperationDescription(
          XMLNode, GetOperationDescriptionFromDocument(true, CustLedgerEntry."Document No."),
          CustLedgerEntry."Posting Date", CustLedgerEntry.Description);
        FillRefExternaNode(XMLNode, Format(SIIDocUploadState."Entry No"));
        FillSucceededCompanyInfo(XMLNode, SIIDocUploadState);
        FillMacrodatoNode(XMLNode, TotalAmount);

        if IncludeContraparteNodeByCrMemoType(SIIDocUploadState."Sales Cr. Memo Type") then begin
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Contraparte', '', 'sii', SiiTxt, XMLNode);
            FillThirdPartyId(
              XMLNode, Customer."Country/Region Code", Customer.Name, Customer."VAT Registration No.", Customer."No.", true,
              SIIManagement.CustomerIsIntraCommunity(Customer."No."), Customer."Not in AEAT", SIIDocUploadState.IDType);
        end;

        if SIIInitialDocUpload.DateWithinInitialUploadPeriod(CustLedgerEntry."Posting Date") then begin
            MoveNonTaxableEntriesToTempVATEntryBuffer(TempVATEntryPerPercent, CustLedgerEntry, false);
            NonTaxHandled := true;
        end;

        if DomesticCustomer then
            GetSourceForServiceOrGoods(
              TempVATEntryPerPercent, ExemptionCausePresent, ExemptionBaseAmounts,
              NonExemptTransactionType, ExemptExists, CustLedgerEntry, false, DomesticCustomer);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoDesglose', '', 'sii', SiiTxt, TipoDesgloseXMLNode);
        for EUService := true downto false do begin
            if not DomesticCustomer then
                GetSourceForServiceOrGoods(
                  TempVATEntryPerPercent, ExemptionCausePresent, ExemptionBaseAmounts,
                  NonExemptTransactionType, ExemptExists, CustLedgerEntry, EUService, DomesticCustomer);
            TempVATEntryPerPercent.SetCurrentKey("VAT %", "EC %");
            if not DomesticCustomer then
                TempVATEntryPerPercent.SetRange("EU Service", EUService);
            EntriesFound := TempVATEntryPerPercent.FindSet;
            if not EntriesFound then
                TempVATEntryPerPercent.Init();
            if EntriesFound or ExemptExists then begin
                AddTipoDesgloseDetailHeader(
                  TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
                  EUXMLNode, VATXMLNode, EUService, DomesticCustomer, false);
                if ExemptExists then begin
                    HandleExemptEntries(VATXMLNode, ExemptionCausePresent, ExemptionBaseAmounts);
                    ExemptExists := false;
                end;
                if EntriesFound then begin
                    XMLDOMManagement.AddElementWithPrefix(VATXMLNode, 'NoExenta', '', 'sii', SiiTxt, VATXMLNode);
                    XMLDOMManagement.AddElementWithPrefix(VATXMLNode, 'TipoNoExenta', Format(NonExemptTransactionType), 'sii', SiiTxt, TempXMLNode);
                    XMLDOMManagement.AddElementWithPrefix(VATXMLNode, 'DesgloseIVA', '', 'sii', SiiTxt, VATXMLNode);
                    repeat
                        FillDetalleIVANode(
                          VATXMLNode, TempVATEntryPerPercent, true, -1, true, NonExemptTransactionType, RegimeCode, 'CuotaRepercutida');
                    until TempVATEntryPerPercent.Next = 0;
                end;
            end;
            TempVATEntryPerPercent.DeleteAll();
            if not NonTaxHandled then begin
                Clear(DomesticXMLNode);
                Clear(EUServiceXMLNode);
                Clear(NonEUServiceXMLNode);
                HandleNonTaxableVATEntries(
                  CustLedgerEntry,
                  TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
                  EUXMLNode, EUService, DomesticCustomer);
                Clear(DomesticXMLNode);
            end;
            Clear(EUXMLNode);
            Clear(VATXMLNode);
        end;
    end;

    local procedure HandleCorrectiveInvoicePurchases(var XMLNode: DotNet XmlNode; SIIDocUploadState: Record "SII Doc. Upload State"; VendorLedgerEntry: Record "Vendor Ledger Entry"; Vendor: Record Vendor): Boolean
    var
        OldVendorLedgerEntry: Record "Vendor Ledger Entry";
        PurchCrMemoHdr: Record "Purch. Cr. Memo Hdr.";
        VendorLedgerEntryRecRef: RecordRef;
        TotalBase: Decimal;
        TotalNonExemptBase: Decimal;
        TotalVATAmount: Decimal;
        CorrectedInvoiceNo: Code[20];
        CorrectionType: Option;
        VendNo: Code[20];
    begin
        GetCorrectionInfoFromDocument(
          VendorLedgerEntry."Document No.", CorrectedInvoiceNo, CorrectionType,
          VendorLedgerEntry."Correction Type", VendorLedgerEntry."Corrected Invoice No.");
        if FindVendorLedgerEntryOfRefDocument(VendorLedgerEntry, OldVendorLedgerEntry, CorrectedInvoiceNo) then
            if CorrectionType = PurchCrMemoHdr."Correction Type"::Removal then begin
                InitializeCorrectiveRemovalXmlBody(XMLNode,
                  VendorLedgerEntry."Posting Date", false, SIIDocUploadState,
                  Vendor.Name, Vendor."VAT Registration No.", Vendor."Country/Region Code", Vendor."No.", false);
                exit(true);
            end;
        VendNo := SIIManagement.GetVendFromLedgEntryByGLSetup(VendorLedgerEntry);
        InitializePurchXmlBody(XMLNode, VendNo, VendorLedgerEntry."Posting Date", SIIDocUploadState.IDType);

        // calculate totals for current doc
        DataTypeManagement.GetRecordRef(VendorLedgerEntry, VendorLedgerEntryRecRef);
        CalculateTotalVatAndBaseAmounts(VendorLedgerEntryRecRef, TotalBase, TotalNonExemptBase, TotalVATAmount);

        if CorrectionType = PurchCrMemoHdr."Correction Type"::Replacement then
            HandleReplacementPurchCorrectiveInvoice(
              XMLNode, Vendor, SIIDocUploadState, OldVendorLedgerEntry, VendorLedgerEntry, TotalBase, TotalNonExemptBase, TotalVATAmount)
        else
            HandleNormalPurchCorrectiveInvoice(
              XMLNode, Vendor, SIIDocUploadState, OldVendorLedgerEntry, VendorLedgerEntry, TotalBase, TotalNonExemptBase, TotalVATAmount);
        exit(true);
    end;

    local procedure HandleReplacementPurchCorrectiveInvoice(var XMLNode: DotNet XmlNode; Vendor: Record Vendor; SIIDocUploadState: Record "SII Doc. Upload State"; OldVendorLedgerEntry: Record "Vendor Ledger Entry"; VendorLedgerEntry: Record "Vendor Ledger Entry"; TotalBase: Decimal; TotalNonExemptBase: Decimal; TotalVATAmount: Decimal)
    var
        TempVATEntryPerPercent: Record "VAT Entry" temporary;
        TempOldVATEntryPerPercent: Record "VAT Entry" temporary;
        TempXMLNode: DotNet XmlNode;
        OldVendorLedgerEntryRecRef: RecordRef;
        OldTotalBase: Decimal;
        OldTotalNonExemptBase: Decimal;
        OldTotalVATAmount: Decimal;
        BaseAmountDiff: Decimal;
        VATAmountDiff: Decimal;
        ECPercentDiff: Decimal;
        ECAmountDiff: Decimal;
        CuotaDeducibleDecValue: Decimal;
        TotalAmount: Decimal;
        RegimeCode: Code[2];
    begin
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'NumSerieFacturaEmisor', Format(VendorLedgerEntry."External Document No."), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(VendorLedgerEntry."Document Date"), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode); // exit ID factura node

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'FacturaRecibida', '', 'siiLR', SiiLRTxt, XMLNode);

        if SIIDocUploadState."Purch. Cr. Memo Type" = 0 then
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoFactura', 'R1', 'sii', SiiTxt, TempXMLNode)
        else
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'TipoFactura', CopyStr(Format(SIIDocUploadState."Purch. Cr. Memo Type"), 1, 2), 'sii', SiiTxt, TempXMLNode);

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoRectificativa', 'S', 'sii', SiiTxt, TempXMLNode);
        GenerateFacturasRectificadasNode(XMLNode, OldVendorLedgerEntry."External Document No.", OldVendorLedgerEntry."Posting Date");

        // calculate totals for old doc
        DataTypeManagement.GetRecordRef(OldVendorLedgerEntry, OldVendorLedgerEntryRecRef);
        CalculateTotalVatAndBaseAmounts(OldVendorLedgerEntryRecRef, OldTotalBase, OldTotalNonExemptBase, OldTotalVATAmount);

        // write totals amounts in XML
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'ImporteRectificacion', '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'BaseRectificada', FormatNumber(Abs(OldTotalBase)), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'CuotaRectificada', FormatNumber(Abs(OldTotalVATAmount)), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);

        RegimeCode := GenerateClaveRegimenNodePurchases(XMLNode, SIIDocUploadState, VendorLedgerEntry, Vendor);

        TotalAmount := OldTotalBase + OldTotalVATAmount + TotalBase + TotalVATAmount;
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'ImporteTotal', FormatNumber(TotalAmount), 'sii', SiiTxt,
          TempXMLNode);
        FillBaseImponibleACosteNode(XMLNode, RegimeCode, OldTotalNonExemptBase + TotalNonExemptBase);
        FillOperationDescription(
          XMLNode, GetOperationDescriptionFromDocument(false, VendorLedgerEntry."Document No."),
          VendorLedgerEntry."Posting Date", VendorLedgerEntry.Description);
        FillRefExternaNode(XMLNode, Format(SIIDocUploadState."Entry No"));
        FillSucceededCompanyInfo(XMLNode, SIIDocUploadState);
        FillMacrodatoNode(XMLNode, TotalAmount);

        // calculate Credit memo differences grouped by VAT %
        CalcNonExemptVATEntriesWithCuotaDeducible(TempVATEntryPerPercent, CuotaDeducibleDecValue, VendorLedgerEntry, 1);
        CuotaDeducibleDecValue := Abs(CuotaDeducibleDecValue);

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DesgloseFactura', '', 'sii', SiiTxt, XMLNode);

        // calculate old and new VAT totals grouped by VAT %
        CalcNonExemptVATEntriesWithCuotaDeducible(TempOldVATEntryPerPercent, CuotaDeducibleDecValue, OldVendorLedgerEntry, -1);
        CuotaDeducibleDecValue := Abs(CuotaDeducibleDecValue);

        // loop over and fill diffs
        TempVATEntryPerPercent.Reset();
        TempVATEntryPerPercent.SetCurrentKey("VAT %", "EC %");
        if TempVATEntryPerPercent.FindSet then begin
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DesgloseIVA', '', 'sii', SiiTxt, XMLNode);
            repeat
                CalcTotalDiffAmounts(
                  BaseAmountDiff, VATAmountDiff, ECPercentDiff, ECAmountDiff, TempOldVATEntryPerPercent, TempVATEntryPerPercent);

                // fill XML
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DetalleIVA', '', 'sii', SiiTxt, XMLNode);
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode, 'TipoImpositivo', FormatNumber(TempVATEntryPerPercent."VAT %"), 'sii', SiiTxt, TempXMLNode);
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'BaseImponible', FormatNumber(BaseAmountDiff), 'sii', SiiTxt, TempXMLNode);
                OnBeforeAddVATAmountPurchDiffElement(TempVATEntryPerPercent, VATAmountDiff);
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'CuotaSoportada', FormatNumber(VATAmountDiff), 'sii', SiiTxt, TempXMLNode);

                GenerateRecargoEquivalenciaNodes(XMLNode, ECPercentDiff, ECAmountDiff);
                XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
            until TempVATEntryPerPercent.Next = 0;
            XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
        end;
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Contraparte', '', 'sii', SiiTxt, XMLNode);
        FillThirdPartyId(
          XMLNode, Vendor."Country/Region Code", Vendor.Name, Vendor."VAT Registration No.", Vendor."No.", true,
          SIIManagement.VendorIsIntraCommunity(Vendor."No."), false, SIIDocUploadState.IDType);
        FillFechaRegContable(XMLNode, VendorLedgerEntry."Posting Date", GetRequestDateOfSIIHistoryByVendLedgEntry(VendorLedgerEntry));
        if CuotaDeducibleDecValue = 0 then
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'CuotaDeducible', FormatNumber(0), 'sii', SiiTxt, TempXMLNode)
        else begin
            CuotaDeducibleDecValue := CalcCuotaDeducible(VendorLedgerEntry."Posting Date", RegimeCode, CuotaDeducibleDecValue);
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'CuotaDeducible', FormatNumber(CuotaDeducibleDecValue), 'sii', SiiTxt, TempXMLNode);
        end;
    end;

    local procedure HandleNormalPurchCorrectiveInvoice(var XMLNode: DotNet XmlNode; Vendor: Record Vendor; SIIDocUploadState: Record "SII Doc. Upload State"; OldVendorLedgerEntry: Record "Vendor Ledger Entry"; VendorLedgerEntry: Record "Vendor Ledger Entry"; TotalBase: Decimal; TotalNonExemptBase: Decimal; TotalVATAmount: Decimal)
    var
        TempVATEntryNormalCalculated: Record "VAT Entry" temporary;
        TempVATEntryReverseChargeCalculated: Record "VAT Entry" temporary;
        VATEntry: Record "VAT Entry";
        TempXMLNode: DotNet XmlNode;
        VendorLedgerEntryRecRef: RecordRef;
        VATEntriesFound: Boolean;
        CuotaDeducibleDecValue: Decimal;
        TotalAmount: Decimal;
        RegimeCode: Code[2];
        VendNo: Code[20];
    begin
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'NumSerieFacturaEmisor', Format(VendorLedgerEntry."External Document No."), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(VendorLedgerEntry."Document Date"), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode); // exit ID factura node

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'FacturaRecibida', '', 'siiLR', SiiLRTxt, XMLNode);
        if SIIDocUploadState."Purch. Cr. Memo Type" = 0 then
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoFactura', 'R1', 'sii', SiiTxt, TempXMLNode)
        else
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'TipoFactura', CopyStr(Format(SIIDocUploadState."Purch. Cr. Memo Type"), 1, 2), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoRectificativa', 'I', 'sii', SiiTxt, TempXMLNode);
        GenerateFacturasRectificadasNode(XMLNode, OldVendorLedgerEntry."External Document No.", OldVendorLedgerEntry."Posting Date");
        SIIDocUploadState.GetSIIDocUploadStateByVendLedgEntry(VendorLedgerEntry);
        RegimeCode := GenerateClaveRegimenNodePurchases(XMLNode, SIIDocUploadState, VendorLedgerEntry, Vendor);
        VendNo := SIIManagement.GetVendFromLedgEntryByGLSetup(VendorLedgerEntry);

        if not TempVATEntryNormalCalculated.IsEmpty then
            TotalBase -= Abs(TempVATEntryNormalCalculated.Base);
        TotalAmount := TotalBase + TotalVATAmount;
        FillNoTaxableVATEntriesPurch(TempVATEntryNormalCalculated, VendorLedgerEntry);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'ImporteTotal', FormatNumber(TotalAmount), 'sii', SiiTxt, TempXMLNode);
        FillBaseImponibleACosteNode(XMLNode, RegimeCode, TotalNonExemptBase);
        FillOperationDescription(
          XMLNode, GetOperationDescriptionFromDocument(false, VendorLedgerEntry."Document No."),
          VendorLedgerEntry."Posting Date", VendorLedgerEntry.Description);
        FillRefExternaNode(XMLNode, Format(SIIDocUploadState."Entry No"));
        FillSucceededCompanyInfo(XMLNode, SIIDocUploadState);
        FillMacrodatoNode(XMLNode, TotalAmount);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DesgloseFactura', '', 'sii', SiiTxt, XMLNode);

        DataTypeManagement.GetRecordRef(VendorLedgerEntry, VendorLedgerEntryRecRef);
        VATEntriesFound := SIIManagement.FindVatEntriesFromLedger(VendorLedgerEntryRecRef, VATEntry);
        if VATEntriesFound or not TempVATEntryNormalCalculated.IsEmpty
        then begin
            if VATEntriesFound then
                repeat
                    CalculatePurchVATEntries(
                      TempVATEntryNormalCalculated, TempVATEntryReverseChargeCalculated, CuotaDeducibleDecValue,
                      VATEntry, VendNo, VendorLedgerEntry."Posting Date");
                until VATEntry.Next = 0;
            AddPurchVATEntriesWithElement(
              XMLNode, TempVATEntryReverseChargeCalculated, 'InversionSujetoPasivo', RegimeCode);
            AddPurchVATEntriesWithElement(
              XMLNode, TempVATEntryNormalCalculated, 'DesgloseIVA', RegimeCode);
            XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
            AddPurchTail(
              XMLNode, VendorLedgerEntry."Posting Date", GetRequestDateOfSIIHistoryByVendLedgEntry(VendorLedgerEntry),
              VendNo, CuotaDeducibleDecValue, SIIDocUploadState.IDType, RegimeCode);
        end;
        XMLDOMManagement.FindNode(XMLNode, '../..', XMLNode);
    end;

    local procedure HandleReplacementSalesCorrectiveInvoice(var XMLNode: DotNet XmlNode; SIIDocUploadState: Record "SII Doc. Upload State"; OldCustLedgerEntry: Record "Cust. Ledger Entry"; CustLedgerEntry: Record "Cust. Ledger Entry"; Customer: Record Customer; TotalBase: Decimal; TotalNonExemptBase: Decimal; TotalVATAmount: Decimal)
    var
        TempOldVATEntryPerPercent: Record "VAT Entry" temporary;
        OldVATEntry: Record "VAT Entry";
        NewVATEntry: Record "VAT Entry";
        TempVATEntryPerPercent: Record "VAT Entry" temporary;
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
        TempXMLNode: DotNet XmlNode;
        TipoDesgloseXMLNode: DotNet XmlNode;
        DesgloseFacturaXMLNode: DotNet XmlNode;
        DomesticXMLNode: DotNet XmlNode;
        DesgloseTipoOperacionXMLNode: DotNet XmlNode;
        EUXMLNode: DotNet XmlNode;
        OldCustLedgerEntryRecRef: RecordRef;
        CustLedgerEntryRecRef: RecordRef;
        RegimeCode: Code[2];
        ExemptionCode: Option;
        OldTotalBase: Decimal;
        OldTotalNonExemptBase: Decimal;
        OldTotalVATAmount: Decimal;
        TotalAmount: Decimal;
        BaseAmountDiff: Decimal;
        VATAmountDiff: Decimal;
        ECPercentDiff: Decimal;
        ECAmountDiff: Decimal;
        DomesticCustomer: Boolean;
        NormalVATEntriesFound: Boolean;
        i: Integer;
        NonExemptTransactionType: Option S1,S2,S3,Initial;
        ExemptExists: Boolean;
        ExemptionCausePresent: array[10] of Boolean;
        ExemptionBaseAmounts: array[10] of Decimal;
    begin
        DomesticCustomer := SIIManagement.IsDomesticCustomer(Customer);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoRectificativa', 'S', 'sii', SiiTxt, TempXMLNode);
        GenerateFacturasRectificadasNode(XMLNode, OldCustLedgerEntry."Document No.", OldCustLedgerEntry."Posting Date");

        // calculate totals for old doc
        DataTypeManagement.GetRecordRef(OldCustLedgerEntry, OldCustLedgerEntryRecRef);
        CalculateTotalVatAndBaseAmounts(OldCustLedgerEntryRecRef, OldTotalBase, OldTotalNonExemptBase, OldTotalVATAmount);

        // write totals amounts in XML
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'ImporteRectificacion', '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'BaseRectificada', FormatNumber(Abs(OldTotalBase)), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'CuotaRectificada', FormatNumber(Abs(OldTotalVATAmount)), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);

        RegimeCode := GenerateClaveRegimenNodeSales(XMLNode, SIIDocUploadState, CustLedgerEntry, Customer);

        TotalAmount := Abs(OldTotalBase + OldTotalVATAmount) - Abs(TotalBase + TotalVATAmount);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'ImporteTotal', FormatNumber(TotalAmount), 'sii', SiiTxt,
          TempXMLNode);

        FillBaseImponibleACosteNode(XMLNode, RegimeCode, Abs(OldTotalNonExemptBase) - Abs(TotalNonExemptBase));
        FillOperationDescription(
          XMLNode, GetOperationDescriptionFromDocument(true, CustLedgerEntry."Document No."),
          CustLedgerEntry."Posting Date", CustLedgerEntry.Description);
        FillRefExternaNode(XMLNode, Format(SIIDocUploadState."Entry No"));
        FillSucceededCompanyInfo(XMLNode, SIIDocUploadState);
        FillMacrodatoNode(XMLNode, TotalAmount);
        if IncludeContraparteNodeByCrMemoType(SIIDocUploadState."Sales Cr. Memo Type") then begin
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Contraparte', '', 'sii', SiiTxt, XMLNode);
            FillThirdPartyId(
              XMLNode, Customer."Country/Region Code", Customer.Name, Customer."VAT Registration No.", Customer."No.", true,
              SIIManagement.CustomerIsIntraCommunity(Customer."No."), Customer."Not in AEAT", SIIDocUploadState.IDType);
        end;

        DataTypeManagement.GetRecordRef(CustLedgerEntry, CustLedgerEntryRecRef);
        if SIIInitialDocUpload.DateWithinInitialUploadPeriod(CustLedgerEntry."Posting Date") then
            NonExemptTransactionType := NonExemptTransactionType::S1
        else
            NonExemptTransactionType := NonExemptTransactionType::Initial;
        if SIIManagement.FindVatEntriesFromLedger(CustLedgerEntryRecRef, NewVATEntry) then
            repeat
                BuildVATEntrySource(
                  ExemptExists, ExemptionCausePresent, ExemptionCode, ExemptionBaseAmounts,
                  TempVATEntryPerPercent, NonExemptTransactionType, NewVATEntry, CustLedgerEntry."Posting Date", not DomesticCustomer);
            until NewVATEntry.Next = 0;

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoDesglose', '', 'sii', SiiTxt, XMLNode);
        TipoDesgloseXMLNode := XMLNode;
        TempVATEntryPerPercent.Reset();
        TempVATEntryPerPercent.SetCurrentKey("VAT %", "EC %");
        NormalVATEntriesFound := TempVATEntryPerPercent.FindSet;
        if NormalVATEntriesFound or ExemptExists then
            AddTipoDesgloseDetailHeader(
              TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
              EUXMLNode, XMLNode, false, DomesticCustomer, false);

        if ExemptExists then begin
            for i := 1 to ArrayLen(ExemptionBaseAmounts) do
                ExemptionBaseAmounts[i] := -ExemptionBaseAmounts[i]; // reverse sign for replacement credit memo
            HandleExemptEntries(XMLNode, ExemptionCausePresent, ExemptionBaseAmounts);
        end;

        // calculate old VAT totals grouped by VAT %
        DataTypeManagement.GetRecordRef(OldCustLedgerEntry, OldCustLedgerEntryRecRef);
        if SIIManagement.FindVatEntriesFromLedger(OldCustLedgerEntryRecRef, OldVATEntry) then
            repeat
                if not GetExemptionCode(OldVATEntry, ExemptionCode) then
                    CalculateNonExemptVATEntries(TempOldVATEntryPerPercent, OldVATEntry, true, CalcVATAmountExclEC(OldVATEntry));
            until OldVATEntry.Next = 0;

        // loop over and fill diffs
        if NormalVATEntriesFound then begin
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'NoExenta', '', 'sii', SiiTxt, XMLNode);
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoNoExenta', Format(NonExemptTransactionType), 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DesgloseIVA', '', 'sii', SiiTxt, XMLNode);
            repeat
                CalcTotalDiffAmounts(
                  BaseAmountDiff, VATAmountDiff, ECPercentDiff, ECAmountDiff, TempOldVATEntryPerPercent, TempVATEntryPerPercent);

                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DetalleIVA', '', 'sii', SiiTxt, XMLNode);
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode, 'TipoImpositivo',
                  FormatNumber(CalcTipoImpositivo(NonExemptTransactionType, RegimeCode, BaseAmountDiff, TempVATEntryPerPercent."VAT %")),
                  'sii', SiiTxt, TempXMLNode);
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode, 'BaseImponible', FormatNumber(Abs(BaseAmountDiff)), 'sii', SiiTxt, TempXMLNode);

                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode, 'CuotaRepercutida', FormatNumber(Abs(VATAmountDiff) - Abs(ECAmountDiff)), 'sii', SiiTxt, TempXMLNode);

                GenerateRecargoEquivalenciaNodes(XMLNode, ECPercentDiff, ECAmountDiff);
                XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
            until TempVATEntryPerPercent.Next = 0;
        end;

        HandleReplacementNonTaxableVATEntries(
          CustLedgerEntry, OldCustLedgerEntry,
          TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
          EUXMLNode, false, DomesticCustomer);
    end;

    local procedure CalculateTotalVatAndBaseAmounts(LedgerEntryRecRef: RecordRef; var TotalBaseAmount: Decimal; var TotalNonExemptVATBaseAmount: Decimal; var TotalVATAmount: Decimal)
    var
        VATEntry: Record "VAT Entry";
    begin
        TotalBaseAmount := 0;
        TotalVATAmount := 0;

        if SIIManagement.FindVatEntriesFromLedger(LedgerEntryRecRef, VATEntry) then begin
            repeat
                TotalBaseAmount += VATEntry.Base;
                if VATEntry."VAT %" <> 0 then
                    TotalNonExemptVATBaseAmount += VATEntry.Base;
                if VATEntry."VAT Calculation Type" <> VATEntry."VAT Calculation Type"::"Reverse Charge VAT" then
                    TotalVATAmount += VATEntry.Amount;
            until VATEntry.Next = 0;
        end;
    end;

    local procedure GenerateFacturasRectificadasNode(var XMLNode: DotNet XmlNode; DocNo: Code[35]; PostingDate: Date)
    var
        TempXMLNode: DotNet XmlNode;
    begin
        if DocNo = '' then
            exit;

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'FacturasRectificadas', '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'IDFacturaRectificada', '', 'sii', SiiTxt, XMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'NumSerieFacturaEmisor', DocNo, 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'FechaExpedicionFacturaEmisor', FormatDate(PostingDate), 'sii', SiiTxt, TempXMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
    end;

    local procedure GenerateClaveRegimenNodeSales(var XMLNode: DotNet XmlNode; SIIDocUploadState: Record "SII Doc. Upload State"; CustLedgerEntry: Record "Cust. Ledger Entry"; Customer: Record Customer) RegimeCode: Code[2]
    var
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
        TempXMLNode: DotNet XmlNode;
        CustLedgerEntryRecRef: RecordRef;
    begin
        if SIIInitialDocUpload.DateWithinInitialUploadPeriod(CustLedgerEntry."Posting Date") then
            RegimeCode := '16'
        else begin
            DataTypeManagement.GetRecordRef(CustLedgerEntry, CustLedgerEntryRecRef);
            if SIIManagement.IsLedgerCashFlowBased(CustLedgerEntryRecRef) then
                RegimeCode := '07'
            else
                if SIIDocUploadState."Sales Special Scheme Code" <> 0 then
                    RegimeCode := CopyStr(Format(SIIDocUploadState."Sales Special Scheme Code"), 1, 2)
                else
                    if SIIManagement.CountryIsLocal(Customer."Country/Region Code") then
                        RegimeCode := '01'
                    else
                        RegimeCode := '02';
        end;
        if RegimeCode <> '' then
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'ClaveRegimenEspecialOTrascendencia', RegimeCode, 'sii', SiiTxt, TempXMLNode);
        exit(RegimeCode);
    end;

    local procedure GenerateClaveRegimenNodePurchases(var XMLNode: DotNet XmlNode; SIIDocUploadState: Record "SII Doc. Upload State"; VendorLedgerEntry: Record "Vendor Ledger Entry"; Vendor: Record Vendor) RegimeCode: Code[2]
    var
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
        TempXMLNode: DotNet XmlNode;
        VendorLedgerEntryRecRef: RecordRef;
    begin
        if SIIInitialDocUpload.DateWithinInitialUploadPeriod(VendorLedgerEntry."Posting Date") then
            RegimeCode := '14'
        else begin
            DataTypeManagement.GetRecordRef(VendorLedgerEntry, VendorLedgerEntryRecRef);
            if SIIManagement.IsLedgerCashFlowBased(VendorLedgerEntryRecRef) then
                RegimeCode := '07'
            else
                if SIIDocUploadState."Purch. Special Scheme Code" <> 0 then
                    RegimeCode := CopyStr(Format(SIIDocUploadState."Purch. Special Scheme Code"), 1, 2)
                else
                    if SIIManagement.VendorIsIntraCommunity(Vendor."No.") then
                        RegimeCode := '09'
                    else
                        RegimeCode := '01';
        end;
        if RegimeCode <> '' then
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'ClaveRegimenEspecialOTrascendencia', RegimeCode, 'sii', SiiTxt, TempXMLNode);
        exit(RegimeCode);
    end;

    local procedure GenerateNodeForFechaOperacionSales(var XMLNode: DotNet XmlNode; CustLedgerEntry: Record "Cust. Ledger Entry")
    var
        SalesInvoiceHeader: Record "Sales Invoice Header";
        SalesInvoiceLine: Record "Sales Invoice Line";
        LastShipDate: Date;
    begin
        if CustLedgerEntry."Document Type" = CustLedgerEntry."Document Type"::Invoice then
            if SalesInvoiceHeader.Get(CustLedgerEntry."Document No.") then begin
                SalesInvoiceLine.SetRange("Document No.", CustLedgerEntry."Document No.");
                SalesInvoiceLine.SetFilter(Quantity, '>%1', 0);
                if SalesInvoiceLine.FindSet then
                    repeat
                        if SalesInvoiceLine."Shipment Date" > LastShipDate then
                            LastShipDate := SalesInvoiceLine."Shipment Date";
                    until SalesInvoiceLine.Next = 0;
                FillFechaOperacion(XMLNode, LastShipDate, SalesInvoiceHeader."Posting Date");
            end;
    end;

    local procedure GenerateNodeForFechaOperacionPurch(var XMLNode: DotNet XmlNode; VendorLedgerEntry: Record "Vendor Ledger Entry")
    var
        PurchInvHeader: Record "Purch. Inv. Header";
        PurchInvLine: Record "Purch. Inv. Line";
        PurchRcptLine: Record "Purch. Rcpt. Line";
        LastRcptDate: Date;
    begin
        if VendorLedgerEntry."Document Type" = VendorLedgerEntry."Document Type"::Invoice then
            if PurchInvHeader.Get(VendorLedgerEntry."Document No.") then begin
                PurchInvLine.SetRange("Document No.", VendorLedgerEntry."Document No.");
                if PurchInvLine.FindSet then
                    repeat
                        if PurchInvLine."Receipt No." <> '' then
                            if PurchRcptLine.Get(PurchInvLine."Receipt No.", PurchInvLine."Receipt Line No.") then
                                if PurchRcptLine."Posting Date" > LastRcptDate then
                                    LastRcptDate := PurchRcptLine."Posting Date"
                    until PurchInvLine.Next = 0;
                FillFechaOperacion(XMLNode, LastRcptDate, PurchInvHeader."Posting Date");
            end;
    end;

    local procedure GenerateRecargoEquivalenciaNodes(var XMLNode: DotNet XmlNode; ECPercent: Decimal; ECAmount: Decimal)
    var
        TempXMLNode: DotNet XmlNode;
    begin
        if ECPercent <> 0 then begin
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'TipoRecargoEquivalencia', FormatNumber(ECPercent), 'sii', SiiTxt, TempXMLNode);
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'CuotaRecargoEquivalencia', FormatNumber(ECAmount), 'sii', SiiTxt,
              TempXMLNode);
        end;
    end;

    local procedure GetOperationDescriptionFromDocument(IsSales: Boolean; DocumentNo: Code[35]): Text
    var
        SalesInvoiceHeader: Record "Sales Invoice Header";
        SalesCrMemoHeader: Record "Sales Cr.Memo Header";
        PurchInvHeader: Record "Purch. Inv. Header";
        PurchCrMemoHdr: Record "Purch. Cr. Memo Hdr.";
        ServiceInvoiceHeader: Record "Service Invoice Header";
        ServiceCrMemoHeader: Record "Service Cr.Memo Header";
    begin
        if IsSales then begin
            if SalesInvoiceHeader.Get(DocumentNo) then
                exit(SalesInvoiceHeader."Operation Description" + SalesInvoiceHeader."Operation Description 2");
            if SalesCrMemoHeader.Get(DocumentNo) then
                exit(SalesCrMemoHeader."Operation Description" + SalesCrMemoHeader."Operation Description 2");
            if ServiceInvoiceHeader.Get(DocumentNo) then
                exit(ServiceInvoiceHeader."Operation Description" + ServiceInvoiceHeader."Operation Description 2");
            if ServiceCrMemoHeader.Get(DocumentNo) then
                exit(ServiceCrMemoHeader."Operation Description" + ServiceCrMemoHeader."Operation Description 2");
        end else begin
            if PurchInvHeader.Get(DocumentNo) then
                exit(PurchInvHeader."Operation Description" + PurchInvHeader."Operation Description 2");
            if PurchCrMemoHdr.Get(DocumentNo) then
                exit(PurchCrMemoHdr."Operation Description" + PurchCrMemoHdr."Operation Description 2");
        end;
    end;

    local procedure GetCorrectionInfoFromDocument(DocumentNo: Code[20]; var CorrectedInvoiceNo: Code[20]; var CorrectionType: Option; EntryCorrType: Option; EntryCorrInvNo: Code[20])
    var
        PurchCrMemoHdr: Record "Purch. Cr. Memo Hdr.";
        SalesCrMemoHeader: Record "Sales Cr.Memo Header";
        ServiceCrMemoHeader: Record "Service Cr.Memo Header";
    begin
        case true of
            PurchCrMemoHdr.Get(DocumentNo):
                begin
                    CorrectedInvoiceNo := PurchCrMemoHdr."Corrected Invoice No.";
                    CorrectionType := PurchCrMemoHdr."Correction Type";
                end;
            SalesCrMemoHeader.Get(DocumentNo):
                begin
                    CorrectedInvoiceNo := SalesCrMemoHeader."Corrected Invoice No.";
                    CorrectionType := SalesCrMemoHeader."Correction Type";
                end;
            ServiceCrMemoHeader.Get(DocumentNo):
                begin
                    CorrectedInvoiceNo := ServiceCrMemoHeader."Corrected Invoice No.";
                    CorrectionType := ServiceCrMemoHeader."Correction Type";
                end
            else begin
                    CorrectedInvoiceNo := EntryCorrInvNo;
                    CorrectionType := EntryCorrType;
                end;
        end;
    end;

    local procedure GetRequestDateOfSIIHistoryByVendLedgEntry(VendorLedgerEntry: Record "Vendor Ledger Entry"): Date
    var
        SIIDocUploadState: Record "SII Doc. Upload State";
        SIIHistory: Record "SII History";
    begin
        SIIDocUploadState.GetSIIDocUploadStateByVendLedgEntry(VendorLedgerEntry);
        SIIHistory.SetRange("Document State Id", SIIDocUploadState.Id);
        SIIHistory.FindLast;
        exit(DT2Date(SIIHistory."Request Date"));
    end;

    local procedure GetSIISetup()
    begin
        if SIISetupInitialized then
            exit;

        SIISetup.Get();
        SIISetup.TestField("Invoice Amount Threshold");
        SIISetup.TestField("SuministroInformacion Schema");
        SIISetup.TestField("SuministroLR Schema");
        SIISetupInitialized := true;
    end;

    local procedure GetIDTypeToExport(IDType: Integer): Text[30]
    var
        SIIDocUploadState: Record "SII Doc. Upload State";
    begin
        case IDType of
            SIIDocUploadState.IDType::"02-VAT Registration No.":
                exit('02');
            SIIDocUploadState.IDType::"03-Passport":
                exit('03');
            SIIDocUploadState.IDType::"04-ID Document":
                exit('04');
            SIIDocUploadState.IDType::"05-Certificate Of Residence":
                exit('05');
            SIIDocUploadState.IDType::"06-Other Probative Document":
                exit('06');
            SIIDocUploadState.IDType::"07-Not On The Census":
                exit('07');
        end;
    end;

    local procedure GetVATNodeName(NoTaxableVAT: Boolean): Text
    begin
        if NoTaxableVAT then
            exit('NoSujeta');
        exit('Sujeta');
    end;

    [Scope('OnPrem')]
    procedure SetIsRetryAccepted(NewRetryAccepted: Boolean)
    begin
        RetryAccepted := NewRetryAccepted;
    end;

    [Scope('OnPrem')]
    procedure SetSIIVersionNo(NewSIIVersion: Option)
    begin
        SIIVersion := NewSIIVersion;
    end;

    [Scope('OnPrem')]
    procedure GetSIIVersionNo(): Integer
    begin
        if SIIVersion = 0 then
            exit(SIIVersion::"1.1");
        exit(SIIVersion);
    end;

    local procedure IsREAGYPSpecialSchemeCode(VATEntry: Record "VAT Entry"; RegimeCode: Code[2]): Boolean
    begin
        exit((VATEntry.Type = VATEntry.Type::Purchase) and (RegimeCode = '02'));
    end;

    local procedure BuildVATEntrySource(var ExemptExists: Boolean; var ExemptionCausePresent: array[10] of Boolean; var ExemptionCode: Option; var ExemptionBaseAmounts: array[10] of Decimal; var VATEntryPerPercent: Record "VAT Entry"; var NonExemptTransactionType: Option S1,S2,S3,Initial; var VATEntry: Record "VAT Entry"; PostingDate: Date; SplitByEUService: Boolean)
    var
        VATPostingSetup: Record "VAT Posting Setup";
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
    begin
        VATPostingSetup.Get(VATEntry."VAT Bus. Posting Group", VATEntry."VAT Prod. Posting Group");
        if VATPostingSetup."No Taxable Type" <> 0 then
            exit;

        if GetExemptionCode(VATEntry, ExemptionCode) then begin
            CalculateExemptVATEntries(ExemptionCausePresent, ExemptionBaseAmounts, VATEntry, ExemptionCode);
            if SIIInitialDocUpload.DateWithinInitialUploadPeriod(PostingDate) then
                MoveExemptEntriesToTempVATEntryBuffer(VATEntryPerPercent, ExemptionCausePresent, ExemptionBaseAmounts)
            else
                ExemptExists := true;
        end else begin
            CalculateNonExemptVATEntries(VATEntryPerPercent, VATEntry, SplitByEUService, VATEntry.Amount + VATEntry."Unrealized Amount");
            BuildNonExemptTransactionType(VATEntry, NonExemptTransactionType);
        end
    end;

    local procedure HandleExemptEntries(var XMLNode: DotNet XmlNode; ExemptionCausePresent: array[10] of Boolean; ExemptionBaseAmounts: array[10] of Decimal)
    var
        TempXmlNode: DotNet XmlNode;
        StopExemptLoop: Boolean;
        BaseAmount: Decimal;
        ExemptionEntryIndex: Integer;
    begin
        for ExemptionEntryIndex := 1 to ArrayLen(ExemptionCausePresent) do
            if ExemptionCausePresent[ExemptionEntryIndex] and (not StopExemptLoop) then begin
                StopExemptLoop := false;

                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Exenta', '', 'sii', SiiTxt, XMLNode);
                if IncludeChangesVersion11 then
                    XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DetalleExenta', '', 'sii', SiiTxt, XMLNode);

                // The first exemption does not have specific cause, it's because of zero VAT %
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode,
                  'CausaExencion',
                  BuildExemptionCodeString(ExemptionEntryIndex),
                  'sii',
                  SiiTxt,
                  TempXmlNode);
                BaseAmount := -ExemptionBaseAmounts[ExemptionEntryIndex];
                XMLDOMManagement.AddElementWithPrefix(
                  XMLNode,
                  'BaseImponible',
                  FormatNumber(BaseAmount),
                  'sii',
                  SiiTxt, TempXmlNode);
                XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
                XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
            end;
    end;

    local procedure CalcTotalDiffAmounts(var TotalBaseAmountDiff: Decimal; var TotalVATAmountDiff: Decimal; var TotalECPercentDiff: Decimal; var TotalECAmountDiff: Decimal; var TempOldVATEntryPerPercent: Record "VAT Entry" temporary; var TempVATEntryPerPercent: Record "VAT Entry" temporary)
    var
        BaseAmountDiff: Decimal;
        VATAmountDiff: Decimal;
        ECPercentDiff: Decimal;
        ECAmountDiff: Decimal;
    begin
        TotalBaseAmountDiff := 0;
        TotalVATAmountDiff := 0;
        TotalECPercentDiff := 0;
        TotalECAmountDiff := 0;
        TempVATEntryPerPercent.SetRange("VAT %", TempVATEntryPerPercent."VAT %");
        TempVATEntryPerPercent.SetRange("EC %", TempVATEntryPerPercent."EC %");
        repeat
            CalcDiffAmounts(BaseAmountDiff, VATAmountDiff, ECPercentDiff, ECAmountDiff, TempOldVATEntryPerPercent, TempVATEntryPerPercent);
            TotalBaseAmountDiff += BaseAmountDiff;
            TotalVATAmountDiff += VATAmountDiff;
            TotalECPercentDiff += ECPercentDiff;
            TotalECAmountDiff += ECAmountDiff;
        until TempVATEntryPerPercent.Next = 0;
        TempVATEntryPerPercent.SetRange("VAT %");
        TempVATEntryPerPercent.SetRange("EC %");
    end;

    local procedure CalcDiffAmounts(var BaseAmountDiff: Decimal; var VATAmountDiff: Decimal; var ECPercentDiff: Decimal; var ECAmountDiff: Decimal; var TempOldVATEntryPerPercent: Record "VAT Entry" temporary; TempVATEntryPerPercent: Record "VAT Entry" temporary)
    begin
        TempOldVATEntryPerPercent.SetRange("VAT %", TempVATEntryPerPercent."VAT %");
        TempOldVATEntryPerPercent.SetRange("EC %", TempVATEntryPerPercent."EC %");
        if TempOldVATEntryPerPercent.FindFirst then begin
            BaseAmountDiff := TempVATEntryPerPercent.Base + TempOldVATEntryPerPercent.Base;
            VATAmountDiff := TempVATEntryPerPercent.Amount + TempOldVATEntryPerPercent.Amount;
            ECPercentDiff := TempVATEntryPerPercent."EC %" - TempOldVATEntryPerPercent."EC %";
            ECAmountDiff := CalculateECAmount(TempVATEntryPerPercent.Base, TempVATEntryPerPercent."EC %") +
              CalculateECAmount(TempOldVATEntryPerPercent.Base, TempOldVATEntryPerPercent."EC %");
        end else begin
            BaseAmountDiff := TempVATEntryPerPercent.Base;
            VATAmountDiff := TempVATEntryPerPercent.Amount;
            ECPercentDiff := TempVATEntryPerPercent."EC %";
            ECAmountDiff := CalculateECAmount(TempVATEntryPerPercent.Base, TempVATEntryPerPercent."EC %");
        end;
    end;

    local procedure CalcVATAmountExclEC(VATEntry: Record "VAT Entry"): Decimal
    var
        VATEntryAmount: Decimal;
        VATEntryBase: Decimal;
    begin
        if VATEntry.Amount = 0 then
            VATEntryAmount := VATEntry."Unrealized Amount"
        else
            VATEntryAmount := VATEntry.Amount;
        if VATEntry."EC %" = 0 then
            exit(VATEntryAmount);
        if VATEntry.Base = 0 then
            VATEntryBase := VATEntry."Unrealized Base"
        else
            VATEntryBase := VATEntry.Base;
        exit(VATEntryAmount - CalculateECAmount(VATEntryBase, VATEntry."EC %"));
    end;

    local procedure CalcTipoImpositivo(NonExemptTransactionType: Option S1,S2,S3,Initial; RegimeCode: Code[2]; VATAmount: Decimal; Amount: Decimal): Decimal
    begin
        if (Format(NonExemptTransactionType) = 'S1') and (RegimeCode in ['03', '05', '09']) and (VATAmount = 0) then
            exit(0);
        exit(Amount);
    end;

    local procedure CalcCuotaDeducible(PostingDate: Date; RegimeCode: Code[2]; Amount: Decimal): Decimal
    var
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
    begin
        if SIIInitialDocUpload.DateWithinInitialUploadPeriod(PostingDate) or (RegimeCode = '13') then
            exit(0);
        exit(Amount);
    end;

    local procedure UseReverseChargeNotIntracommunity(VATCalcType: Option; VendNo: Code[20]; PostingDate: Date): Boolean
    var
        VATEntry: Record "VAT Entry";
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
    begin
        exit(
          (VATCalcType = VATEntry."VAT Calculation Type"::"Reverse Charge VAT") and
          (not SIIManagement.VendorIsIntraCommunity(VendNo)) and
          (not SIIInitialDocUpload.DateWithinInitialUploadPeriod(PostingDate)));
    end;

    local procedure HandleNonTaxableVATEntries(CustLedgerEntry: Record "Cust. Ledger Entry"; var TipoDesgloseXMLNode: DotNet XmlNode; var DesgloseFacturaXMLNode: DotNet XmlNode; var DomesticXMLNode: DotNet XmlNode; var DesgloseTipoOperacionXMLNode: DotNet XmlNode; var EUXMLNode: DotNet XmlNode; IsService: Boolean; DomesticCustomer: Boolean)
    var
        CustNo: Code[20];
        Amount: array[2] of Decimal;
        HasEntries: array[2] of Boolean;
        IsLocalRule: Boolean;
        i: Integer;
    begin
        CustNo := SIIManagement.GetCustFromLedgEntryByGLSetup(CustLedgerEntry);
        for IsLocalRule := false to true do begin
            i += 1;
            HasEntries[i] :=
              SIIManagement.GetNoTaxableSalesAmount(
                Amount[i], CustNo, CustLedgerEntry."Document Type", CustLedgerEntry."Document No.",
                CustLedgerEntry."Posting Date", IsService, true, IsLocalRule);
        end;
        ExportNonTaxableVATEntries(
          TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode,
          DesgloseTipoOperacionXMLNode, EUXMLNode, IsService, DomesticCustomer, HasEntries, Amount);
    end;

    local procedure HandleReplacementNonTaxableVATEntries(CustLedgerEntry: Record "Cust. Ledger Entry"; OldCustLedgerEntry: Record "Cust. Ledger Entry"; var TipoDesgloseXMLNode: DotNet XmlNode; var DesgloseFacturaXMLNode: DotNet XmlNode; var DomesticXMLNode: DotNet XmlNode; var DesgloseTipoOperacionXMLNode: DotNet XmlNode; var EUXMLNode: DotNet XmlNode; IsService: Boolean; DomesticCustomer: Boolean)
    var
        CustNo: Code[20];
        OldAmount: Decimal;
        Amount: Decimal;
        ReplacementAmount: array[2] of Decimal;
        HasEntries: array[2] of Boolean;
        IsLocalRule: Boolean;
        i: Integer;
    begin
        CustNo := SIIManagement.GetCustFromLedgEntryByGLSetup(CustLedgerEntry);
        for IsLocalRule := false to true do begin
            i += 1;
            HasEntries[i] :=
              SIIManagement.GetNoTaxableSalesAmount(
                OldAmount, CustNo, CustLedgerEntry."Document Type", CustLedgerEntry."Document No.",
                CustLedgerEntry."Posting Date", IsService, true, IsLocalRule) or
              SIIManagement.GetNoTaxableSalesAmount(
                Amount, CustNo, OldCustLedgerEntry."Document Type", OldCustLedgerEntry."Document No.",
                OldCustLedgerEntry."Posting Date", IsService, true, IsLocalRule);
            ReplacementAmount[i] := Abs(OldAmount + Amount);
        end;
        ExportNonTaxableVATEntries(
          TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode, EUXMLNode, IsService, DomesticCustomer,
          HasEntries, ReplacementAmount);
    end;

    local procedure ExportNonTaxableVATEntries(var TipoDesgloseXMLNode: DotNet XmlNode; var DesgloseFacturaXMLNode: DotNet XmlNode; var DomesticXMLNode: DotNet XmlNode; var DesgloseTipoOperacionXMLNode: DotNet XmlNode; var EUXMLNode: DotNet XmlNode; IsService: Boolean; DomesticCustomer: Boolean; HasEntries: array[2] of Boolean; Amount: array[2] of Decimal)
    begin
        if HasEntries[1] then
            InsertNoTaxableNode(
              TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
              EUXMLNode, IsService, DomesticCustomer,
              'ImportePorArticulos7_14_Otros', Amount[1]);

        if HasEntries[2] then
            InsertNoTaxableNode(
              TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
              EUXMLNode, IsService, DomesticCustomer,
              'ImporteTAIReglasLocalizacion', Amount[2]);
    end;

    local procedure MoveExemptEntriesToTempVATEntryBuffer(var TempVATEntryPerPercent: Record "VAT Entry" temporary; ExemptionCausePresent: array[10] of Boolean; ExemptionBaseAmounts: array[10] of Decimal)
    var
        VATEntry: Record "VAT Entry";
        StopExemptLoop: Boolean;
        ExemptionEntryIndex: Integer;
        EntryNo: Integer;
    begin
        VATEntry.FindLast;
        EntryNo := VATEntry."Entry No." + +2000000; // Choose Entry No. to avoid conflict with real VAT Entries
        for ExemptionEntryIndex := 1 to ArrayLen(ExemptionCausePresent) do
            if not StopExemptLoop and ExemptionCausePresent[ExemptionEntryIndex] then begin
                StopExemptLoop := false;
                EntryNo += 1;
                TempVATEntryPerPercent."Entry No." := EntryNo;
                TempVATEntryPerPercent.Base := ExemptionBaseAmounts[ExemptionEntryIndex];
                TempVATEntryPerPercent.Insert();
            end;
        Clear(ExemptionCausePresent);
        Clear(ExemptionBaseAmounts);
    end;

    local procedure MoveNonTaxableEntriesToTempVATEntryBuffer(var TempVATEntryCalculatedNonExempt: Record "VAT Entry" temporary; CustLedgerEntry: Record "Cust. Ledger Entry"; IsService: Boolean)
    var
        NoTaxableEntry: Record "No Taxable Entry";
        VATEntry: Record "VAT Entry";
        EntryNo: Integer;
    begin
        VATEntry.FindLast;
        EntryNo := VATEntry."Entry No." + 3000000;
        if SIIManagement.NoTaxableEntriesExistSales(
             NoTaxableEntry,
             SIIManagement.GetCustFromLedgEntryByGLSetup(CustLedgerEntry), CustLedgerEntry."Document Type", CustLedgerEntry."Document No.",
             CustLedgerEntry."Posting Date", IsService, false, false)
        then begin
            if NoTaxableEntry.FindSet then
                repeat
                    EntryNo += 1;
                    TempVATEntryCalculatedNonExempt.TransferFields(NoTaxableEntry);
                    TempVATEntryCalculatedNonExempt."Entry No." := EntryNo;
                    TempVATEntryCalculatedNonExempt.Amount := 0;
                    TempVATEntryCalculatedNonExempt.Insert();
                until NoTaxableEntry.Next = 0;
        end;
    end;

    local procedure IsPurchInvoice(var InvoiceType: Text; SIIDocUploadState: Record "SII Doc. Upload State") IsInvoice: Boolean
    begin
        IsInvoice := false;
        case SIIDocUploadState."Document Type" of
            SIIDocUploadState."Document Type"::Invoice:
                begin
                    if SIIDocUploadState."Purch. Invoice Type" =
                       SIIDocUploadState."Purch. Invoice Type"::"Customs - Complementary Liquidation"
                    then
                        InvoiceType := LCLbl
                    else
                        InvoiceType := CopyStr(Format(SIIDocUploadState."Purch. Invoice Type"), 1, 2);
                    IsInvoice := true;
                end;
            SIIDocUploadState."Document Type"::"Credit Memo":
                begin
                    IsInvoice :=
                      SIIDocUploadState."Purch. Cr. Memo Type" in [SIIDocUploadState."Purch. Cr. Memo Type"::"F1 Invoice",
                                                                   SIIDocUploadState."Purch. Cr. Memo Type"::"F2 Simplified Invoice"];
                    if IsInvoice then
                        InvoiceType := CopyStr(Format(SIIDocUploadState."Purch. Cr. Memo Type"), 1, 2);
                end;
        end;
        exit(IsInvoice);
    end;

    local procedure IsSalesInvoice(var InvoiceType: Text; SIIDocUploadState: Record "SII Doc. Upload State") IsInvoice: Boolean
    begin
        IsInvoice := false;
        case SIIDocUploadState."Document Type" of
            SIIDocUploadState."Document Type"::Invoice:
                begin
                    InvoiceType := CopyStr(Format(SIIDocUploadState."Sales Invoice Type"), 1, 2);
                    IsInvoice := true;
                end;
            SIIDocUploadState."Document Type"::"Credit Memo":
                begin
                    IsInvoice :=
                      SIIDocUploadState."Sales Cr. Memo Type" in [SIIDocUploadState."Sales Cr. Memo Type"::"F1 Invoice",
                                                                  SIIDocUploadState."Sales Cr. Memo Type"::"F2 Simplified Invoice"];
                    if IsInvoice then
                        InvoiceType := CopyStr(Format(SIIDocUploadState."Sales Cr. Memo Type"), 1, 2);
                end;
        end;
        exit(IsInvoice);
    end;

    local procedure InsertNoTaxableNode(var TipoDesgloseXMLNode: DotNet XmlNode; var DesgloseFacturaXMLNode: DotNet XmlNode; var DomesticXMLNode: DotNet XmlNode; var DesgloseTipoOperacionXMLNode: DotNet XmlNode; var EUXMLNode: DotNet XmlNode; EUService: Boolean; DomesticCustomer: Boolean; NodeName: Text; NonTaxableAmount: Decimal)
    var
        VATXMLNode: DotNet XmlNode;
    begin
        AddTipoDesgloseDetailHeader(
          TipoDesgloseXMLNode, DesgloseFacturaXMLNode, DomesticXMLNode, DesgloseTipoOperacionXMLNode,
          EUXMLNode, VATXMLNode, EUService, DomesticCustomer, true);
        GenerateNodeForNonTaxableVAT(NonTaxableAmount, VATXMLNode, NodeName);
    end;

    local procedure InsertMedioNode(var XMLNode: DotNet XmlNode; PaymentMethodCode: Code[10])
    var
        PaymentMethod: Record "Payment Method";
        TempXMLNode: DotNet XmlNode;
        MedioValue: Text;
    begin
        MedioValue := '04';
        if PaymentMethodCode <> '' then begin
            PaymentMethod.Get(PaymentMethodCode);
            if PaymentMethod."SII Payment Method Code" <> 0 then
                MedioValue := Format(PaymentMethod."SII Payment Method Code");
        end;
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Medio', MedioValue, 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure FillDetalleIVANode(var XMLNode: DotNet XmlNode; var TempVATEntry: Record "VAT Entry" temporary; UseSign: Boolean; Sign: Integer; FillEUServiceNodes: Boolean; NonExemptTransactionType: Option S1,S2,S3,Initial; RegimeCode: Code[2]; AmountNodeName: Text)
    var
        TempXmlNode: DotNet XmlNode;
        Base: Decimal;
        Amount: Decimal;
        ECPercent: Decimal;
        ECAmount: Decimal;
        VATPctText: Text;
    begin
        TempVATEntry.SetRange("VAT %", TempVATEntry."VAT %");
        TempVATEntry.SetRange("EC %", TempVATEntry."EC %");
        repeat
            if UseSign then begin
                Base += TempVATEntry.Base * Sign;
                Amount += TempVATEntry.Amount * Sign;
            end else begin
                Base += Abs(TempVATEntry.Base);
                Amount += Abs(TempVATEntry.Amount);
            end;
        until TempVATEntry.Next = 0;
        if TempVATEntry."EC %" <> 0 then begin
            ECPercent := TempVATEntry."EC %";
            ECAmount += CalculateECAmount(Base, TempVATEntry."EC %");
            Amount := Amount - ECAmount;
        end;

        TempVATEntry.SetRange("VAT %");
        TempVATEntry.SetRange("EC %");

        VATPctText :=
          FormatNumber(CalcTipoImpositivo(NonExemptTransactionType, RegimeCode, Base, TempVATEntry."VAT %"));

        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DetalleIVA', '', 'sii', SiiTxt, XMLNode);
        if not IsREAGYPSpecialSchemeCode(TempVATEntry, RegimeCode) then
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'TipoImpositivo', VATPctText, 'sii', SiiTxt, TempXmlNode);
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'BaseImponible', FormatNumber(Base), 'sii', SiiTxt, TempXmlNode);
        if IsREAGYPSpecialSchemeCode(TempVATEntry, RegimeCode) then begin
            XMLDOMManagement.AddElementWithPrefix(XMLNode, 'PorcentCompensacionREAGYP', VATPctText, 'sii', SiiTxt, TempXmlNode);
            AmountNodeName := 'ImporteCompensacionREAGYP';
        end;
        OnBeforeAddLineAmountElement(TempVATEntry, AmountNodeName, Amount);
        XMLDOMManagement.AddElementWithPrefix(XMLNode, AmountNodeName, FormatNumber(Amount), 'sii', SiiTxt, TempXmlNode);
        if (ECPercent <> 0) and FillEUServiceNodes then
            GenerateRecargoEquivalenciaNodes(XMLNode, ECPercent, ECAmount);
        XMLDOMManagement.FindNode(XMLNode, '..', XMLNode);
    end;

    local procedure FillOperationDescription(var XMLNode: DotNet XmlNode; OperationDescription: Text; PostingDate: Date; LedgerEntryDescription: Text)
    var
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
        TempXMLNode: DotNet XmlNode;
    begin
        if OperationDescription <> '' then
            XMLDOMManagement.AddElementWithPrefix(
              XMLNode, 'DescripcionOperacion', OperationDescription, 'sii', SiiTxt, TempXMLNode)
        else
            if SIIInitialDocUpload.DateWithinInitialUploadPeriod(PostingDate) then
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DescripcionOperacion', RegistroDelPrimerSemestreTxt, 'sii', SiiTxt, TempXMLNode)
            else
                XMLDOMManagement.AddElementWithPrefix(XMLNode, 'DescripcionOperacion', LedgerEntryDescription, 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure FillFechaRegContable(var XMLNode: DotNet XmlNode; PostingDate: Date; RequestDate: Date)
    var
        SIIInitialDocUpload: Codeunit "SII Initial Doc. Upload";
        TempXMLNode: DotNet XmlNode;
        NodePostingDate: Date;
    begin
        if SIIInitialDocUpload.DateWithinInitialUploadPeriod(PostingDate) then
            NodePostingDate := WorkDate
        else
            NodePostingDate := RequestDate;
        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'FechaRegContable', FormatDate(NodePostingDate), 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure FillFechaOperacion(var XMLNode: DotNet XmlNode; LastShptRcptDate: Date; PostingDate: Date)
    var
        TempXMLNode: DotNet XmlNode;
    begin
        if (LastShptRcptDate = 0D) or (LastShptRcptDate = PostingDate) then
            exit;

        if LastShptRcptDate > WorkDate then
            LastShptRcptDate := PostingDate;
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'FechaOperacion', FormatDate(LastShptRcptDate), 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure FillMacrodatoNode(var XMLNode: DotNet XmlNode; TotalAmount: Decimal)
    var
        TempXMLNode: DotNet XmlNode;
        Value: Text;
    begin
        if not IncludeChangesVersion11 then
            exit;
        if Abs(TotalAmount) > SIISetup."Invoice Amount Threshold" then
            Value := 'S'
        else
            Value := 'N';
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'Macrodato', Value, 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure FillDocHeaderNode(): Text
    begin
        if IncludeChangesVersion11 then
            exit('PeriodoLiquidacion');
        exit('PeriodoImpositivo');
    end;

    local procedure FillRefExternaNode(var XMLNode: DotNet XmlNode; Value: Text)
    var
        TempXMLNode: DotNet XmlNode;
    begin
        if not IncludeChangesVersion11 then
            exit;
        XMLDOMManagement.AddElementWithPrefix(XMLNode, 'RefExterna', Value, 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure FillBaseImponibleACosteNode(var XMLNode: DotNet XmlNode; RegimeCode: Code[2]; TotalBase: Decimal)
    var
        TempXMLNode: DotNet XmlNode;
    begin
        if RegimeCode <> SIIManagement.GetBaseImponibleACosteRegimeCode then
            exit;

        XMLDOMManagement.AddElementWithPrefix(
          XMLNode, 'BaseImponibleACoste', FormatNumber(TotalBase), 'sii', SiiTxt, TempXMLNode);
    end;

    local procedure IncludeContraparteNodeBySalesInvType(InvoiceType: Text): Boolean
    begin
        exit(InvoiceType in ['F1', 'F3', 'F4']);
    end;

    local procedure IncludeContraparteNodeByCrMemoType(CrMemoType: Option): Boolean
    var
        SIIDocUploadState: Record "SII Doc. Upload State";
    begin
        exit(
          CrMemoType in [SIIDocUploadState."Sales Cr. Memo Type"::"R1 Corrected Invoice",
                         SIIDocUploadState."Sales Cr. Memo Type"::"R2 Corrected Invoice (Art. 80.3)",
                         SIIDocUploadState."Sales Cr. Memo Type"::"R3 Corrected Invoice (Art. 80.4)",
                         SIIDocUploadState."Sales Cr. Memo Type"::"R4 Corrected Invoice (Other)"]);
    end;

    local procedure IncludeChangesVersion11(): Boolean
    var
        SIIDocUploadState: Record "SII Doc. Upload State";
    begin
        exit(GetSIIVersionNo < SIIDocUploadState."Version No."::"1.0");
    end;

    [Scope('OnPrem')]
    procedure Reset()
    begin
        IsInitialized := false;
    end;

    [IntegrationEvent(false, false)]
    local procedure OnAfterCalculateCuotaDeducibleValue(var CuotaDeducibleValue: Decimal; var VATAmount: Decimal; var VATEntry: Record "VAT Entry")
    begin
    end;

    [IntegrationEvent(false, false)]
    local procedure OnBeforeAddLineAmountElement(var TempVATEntry: Record "VAT Entry" temporary; AmountNodeName: Text; var Amount: Decimal)
    begin
    end;

    [IntegrationEvent(false, false)]
    local procedure OnBeforeAddVATAmountPurchDiffElement(var TempVATEntry: Record "VAT Entry" temporary; var VATAmountDiff: Decimal)
    begin
    end;

    [IntegrationEvent(false, false)]
    local procedure OnBeforeGenerateXML(LedgerEntry: Variant; var XMLDocOut: DotNet XmlDocument; UploadType: Option; IsCreditMemoRemoval: Boolean; var ResultValue: Boolean; var IsHandled: Boolean)
    begin
    end;
}

