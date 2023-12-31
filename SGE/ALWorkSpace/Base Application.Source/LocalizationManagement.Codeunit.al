codeunit 10760 "Localization Management"
{

    trigger OnRun()
    begin
    end;

    local procedure IsSameExtDocNoInDiffFY(): Boolean
    var
        PurchasesPayablesSetup: Record "Purchases & Payables Setup";
    begin
        PurchasesPayablesSetup.Get();
        exit(PurchasesPayablesSetup."Same Ext. Doc. No. in Diff. FY");
    end;

    local procedure ApplyFiscalYearFilter(var VendorLedgerEntry: Record "Vendor Ledger Entry"; DocumentDate: Date)
    begin
        VendorLedgerEntry.SetRange("Document Date", CalcDate('<-CY>', DocumentDate), CalcDate('<CY>', DocumentDate));
    end;

    [EventSubscriber(ObjectType::Codeunit, 1312, 'OnAfterSetFilterForExternalDocNo', '', false, false)]
    local procedure OnAfterSetFilterForExternalDocNo(var VendorLedgerEntry: Record "Vendor Ledger Entry"; DocumentDate: Date)
    begin
        if not IsSameExtDocNoInDiffFY then
            exit;

        ApplyFiscalYearFilter(VendorLedgerEntry, DocumentDate);
    end;
}

