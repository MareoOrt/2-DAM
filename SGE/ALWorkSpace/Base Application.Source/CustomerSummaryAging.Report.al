report 105 "Customer - Summary Aging"
{
    DefaultLayout = RDLC;
    RDLCLayout = './CustomerSummaryAging.rdlc';
    AdditionalSearchTerms = 'customer balance,payment due';
    ApplicationArea = Basic, Suite;
    Caption = 'Customer - Summary Aging';
    UsageCategory = ReportsAndAnalysis;

    dataset
    {
        dataitem(Customer; Customer)
        {
            DataItemTableView = SORTING("No.");
            RequestFilterFields = "No.", "Search Name", "Customer Posting Group", "Currency Filter";
            column(CompanyName; COMPANYPROPERTY.DisplayName)
            {
            }
            column(PrintAmtInLCY; PrintAmountsInLCY)
            {
            }
            column(CustFilter1; Customer.TableCaption + ': ' + CustFilter)
            {
            }
            column(CustFilter; CustFilter)
            {
            }
            column(PeriodStartDate2; Format(PeriodStartDate[2]))
            {
            }
            column(PeriodStartDate3; Format(PeriodStartDate[3]))
            {
            }
            column(PeriodStartDate4; Format(PeriodStartDate[4]))
            {
            }
            column(PeriodStartDate31; Format(PeriodStartDate[3] - 1))
            {
            }
            column(PeriodStartDate41; Format(PeriodStartDate[4] - 1))
            {
            }
            column(PeriodStartDate51; Format(PeriodStartDate[5] - 1))
            {
            }
            column(CustBalDueLCY1; CustBalanceDueLCY[1])
            {
                AutoFormatType = 1;
            }
            column(CustBalDueLCY2; CustBalanceDueLCY[2])
            {
                AutoFormatType = 1;
            }
            column(CustBalDueLCY3; CustBalanceDueLCY[3])
            {
                AutoFormatType = 1;
            }
            column(CustBalDueLCY4; CustBalanceDueLCY[4])
            {
                AutoFormatType = 1;
            }
            column(CustBalDueLCY5; CustBalanceDueLCY[5])
            {
                AutoFormatType = 1;
            }
            column(TotalCustBalCY; TotalCustBalanceLCY)
            {
                AutoFormatType = 1;
            }
            column(LineTotalCustBal; LineTotalCustBalance)
            {
                AutoFormatType = 1;
            }
            column(CustBalDue5; CustBalanceDue[5])
            {
                AutoFormatType = 1;
            }
            column(CustBalDue4; CustBalanceDue[4])
            {
                AutoFormatType = 1;
            }
            column(CustBalDue3; CustBalanceDue[3])
            {
                AutoFormatType = 1;
            }
            column(CustBalDue2; CustBalanceDue[2])
            {
                AutoFormatType = 1;
            }
            column(CustBalDue1; CustBalanceDue[1])
            {
                AutoFormatType = 1;
            }
            column(Name_Cust; Name)
            {
                IncludeCaption = true;
            }
            column(No_Cust; "No.")
            {
                IncludeCaption = true;
            }
            column(InCustBalDueLCY1; InCustBalanceDueLCY[1])
            {
                AutoFormatType = 1;
            }
            column(InCustBalDueLCY2; InCustBalanceDueLCY[2])
            {
                AutoFormatType = 1;
            }
            column(InCustBalDueLCY3; InCustBalanceDueLCY[3])
            {
                AutoFormatType = 1;
            }
            column(InCustBalDueLCY4; InCustBalanceDueLCY[4])
            {
                AutoFormatType = 1;
            }
            column(InCustBalDueLCY5; InCustBalanceDueLCY[5])
            {
                AutoFormatType = 1;
            }
            column(CustomerSummaryAgingCaption; CustomerSummaryAgingCaptionLbl)
            {
            }
            column(PageCaption; PageCaptionLbl)
            {
            }
            column(AlAmtAreInLCYCaption; AlAmtAreInLCYCaptionLbl)
            {
            }
            column(BalDueCaption; BalDueCaptionLbl)
            {
            }
            column(BeforeCaption; BeforeCaptionLbl)
            {
            }
            column(AfterCaption; AfterCaptionLbl)
            {
            }
            column(BalCaption; BalCaptionLbl)
            {
            }
            column(TotalLCYCaption; TotalLCYCaptionLbl)
            {
            }
            column(PrintLine; PrintLine)
            {
            }
            dataitem("Integer"; "Integer")
            {
                DataItemTableView = SORTING(Number) WHERE(Number = FILTER(1 ..));
                column(LineTotalCustBal1; LineTotalCustBalance)
                {
                    AutoFormatExpression = Currency2.Code;
                    AutoFormatType = 1;
                }
                column(CustBalanceDue6; CustBalanceDue[5])
                {
                    AutoFormatExpression = Currency2.Code;
                    AutoFormatType = 1;
                }
                column(CustBalanceDue7; CustBalanceDue[4])
                {
                    AutoFormatExpression = Currency2.Code;
                    AutoFormatType = 1;
                }
                column(CustBalanceDue8; CustBalanceDue[3])
                {
                    AutoFormatExpression = Currency2.Code;
                    AutoFormatType = 1;
                }
                column(CustBalanceDue9; CustBalanceDue[2])
                {
                    AutoFormatExpression = Currency2.Code;
                    AutoFormatType = 1;
                }
                column(CustBalanceDue10; CustBalanceDue[1])
                {
                    AutoFormatExpression = Currency2.Code;
                    AutoFormatType = 1;
                }
                column(Curr2Code; Currency2.Code)
                {
                }

                trigger OnAfterGetRecord()
                var
                    DtldCustLedgEntry: Record "Detailed Cust. Ledg. Entry";
                begin
                    if Number = 1 then
                        Currency2.Find('-')
                    else
                        if Currency2.Next = 0 then
                            CurrReport.Break();
                    Currency2.CalcFields("Cust. Ledg. Entries in Filter");
                    if not Currency2."Cust. Ledg. Entries in Filter" then
                        CurrReport.Skip();

                    PrintLine := false;
                    LineTotalCustBalance := 0;
                    for i := 1 to 5 do begin
                        DtldCustLedgEntry.SetCurrentKey(
                          "Excluded from calculation", "Customer No.", "Posting Date", "Currency Code",
                          "Initial Entry Due Date", "Initial Entry Global Dim. 1", "Initial Entry Global Dim. 2");
                        DtldCustLedgEntry.SetRange("Excluded from calculation", false);
                        DtldCustLedgEntry.SetRange("Customer No.", Customer."No.");
                        DtldCustLedgEntry.SetRange("Initial Entry Due Date", PeriodStartDate[i], PeriodStartDate[i + 1] - 1);
                        DtldCustLedgEntry.SetRange("Currency Code", Currency2.Code);
                        DtldCustLedgEntry.CalcSums(Amount);
                        CustBalanceDue[i] := DtldCustLedgEntry.Amount;
                        InCustBalanceDueLCY[i] := InCustBalanceDueLCY2[i];
                        if CustBalanceDue[i] <> 0 then
                            PrintLine := true;
                        LineTotalCustBalance := LineTotalCustBalance + CustBalanceDue[i];
                    end;
                end;

                trigger OnPreDataItem()
                begin
                    if PrintAmountsInLCY or not PrintLine then
                        CurrReport.Break();
                    Currency2.Reset();
                    Currency2.SetRange("Customer Filter", Customer."No.");
                    Customer.CopyFilter("Currency Filter", Currency2.Code);
                    if (Customer.GetFilter("Global Dimension 1 Filter") <> '') or
                       (Customer.GetFilter("Global Dimension 2 Filter") <> '')
                    then begin
                        Customer.CopyFilter("Global Dimension 1 Filter", Currency2."Global Dimension 1 Filter");
                        Customer.CopyFilter("Global Dimension 2 Filter", Currency2."Global Dimension 2 Filter");
                    end;
                end;
            }

            trigger OnAfterGetRecord()
            var
                DtldCustLedgEntry: Record "Detailed Cust. Ledg. Entry";
            begin
                PrintLine := false;
                LineTotalCustBalance := 0;
                CopyFilter("Currency Filter", DtldCustLedgEntry."Currency Code");
                for i := 1 to 5 do begin
                    DtldCustLedgEntry.SetCurrentKey(
                      "Excluded from calculation", "Customer No.", "Posting Date", "Currency Code",
                      "Initial Entry Due Date", "Initial Entry Global Dim. 1", "Initial Entry Global Dim. 2");
                    DtldCustLedgEntry.SetRange("Excluded from calculation", false);
                    DtldCustLedgEntry.SetRange("Customer No.", "No.");
                    DtldCustLedgEntry.SetRange("Initial Entry Due Date", PeriodStartDate[i], PeriodStartDate[i + 1] - 1);
                    DtldCustLedgEntry.CalcSums("Amount (LCY)");
                    CustBalanceDue[i] := DtldCustLedgEntry."Amount (LCY)";
                    CustBalanceDueLCY[i] := DtldCustLedgEntry."Amount (LCY)";
                    if PrintAmountsInLCY then
                        InCustBalanceDueLCY[i] += DtldCustLedgEntry."Amount (LCY)"
                    else
                        InCustBalanceDueLCY2[i] += DtldCustLedgEntry."Amount (LCY)";
                    if CustBalanceDue[i] <> 0 then
                        PrintLine := true;
                    LineTotalCustBalance := LineTotalCustBalance + CustBalanceDueLCY[i];
                    TotalCustBalanceLCY := TotalCustBalanceLCY + CustBalanceDueLCY[i];
                end;

                if not PrintLine then
                    CurrReport.Skip();
            end;

            trigger OnPreDataItem()
            begin
                Clear(CustBalanceDue);
                Clear(CustBalanceDueLCY);
                Clear(TotalCustBalanceLCY);
                Currency2.Code := '';
                Currency2.Insert();
                if Currency.Find('-') then
                    repeat
                        Currency2 := Currency;
                        Currency2.Insert();
                    until Currency.Next = 0;
            end;
        }
    }

    requestpage
    {
        SaveValues = true;

        layout
        {
            area(content)
            {
                group(Options)
                {
                    Caption = 'Options';
                    field(StartingDate; PeriodStartDate[2])
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'Starting Date';
                        NotBlank = true;
                        ToolTip = 'Specifies the date for the beginning of the period covered by the report.';
                    }
                    field(PeriodLength; PeriodLength)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'Period Length';
                        ToolTip = 'Specifies the length of each of the three periods. For example, enter "1M" for one month.';
                    }
                    field(ShowAmountsInLCY; PrintAmountsInLCY)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'Show Amounts in LCY';
                        ToolTip = 'Specifies that you want amounts in the report to be displayed in LCY. Leave this field blank if you want to see amounts in foreign currencies.';
                    }
                }
            }
        }

        actions
        {
        }

        trigger OnOpenPage()
        begin
            if PeriodStartDate[2] = 0D then
                PeriodStartDate[2] := WorkDate;
            if Format(PeriodLength) = '' then
                Evaluate(PeriodLength, '<1M>');
        end;
    }

    labels
    {
    }

    trigger OnPreReport()
    var
        FormatDocument: Codeunit "Format Document";
    begin
        CustFilter := FormatDocument.GetRecordFiltersWithCaptions(Customer);
        for i := 3 to 5 do
            PeriodStartDate[i] := CalcDate(PeriodLength, PeriodStartDate[i - 1]);
        PeriodStartDate[6] := DMY2Date(31, 12, 9999);
    end;

    var
        Currency: Record Currency;
        Currency2: Record Currency temporary;
        CustFilter: Text;
        PrintAmountsInLCY: Boolean;
        PeriodLength: DateFormula;
        PeriodStartDate: array[6] of Date;
        CustBalanceDue: array[5] of Decimal;
        CustBalanceDueLCY: array[5] of Decimal;
        LineTotalCustBalance: Decimal;
        TotalCustBalanceLCY: Decimal;
        PrintLine: Boolean;
        i: Integer;
        InCustBalanceDueLCY: array[5] of Decimal;
        InCustBalanceDueLCY2: array[5] of Decimal;
        CustomerSummaryAgingCaptionLbl: Label 'Customer - Summary Aging';
        PageCaptionLbl: Label 'Page';
        AlAmtAreInLCYCaptionLbl: Label 'All amounts are in LCY';
        BalDueCaptionLbl: Label 'Balance Due';
        BeforeCaptionLbl: Label '...Before';
        AfterCaptionLbl: Label 'After...';
        BalCaptionLbl: Label 'Balance';
        TotalLCYCaptionLbl: Label 'Total (LCY)';

    procedure InitializeRequest(StartingDate: Date; SetPeriodLength: Text[1024]; ShowAmountInLCY: Boolean)
    begin
        PeriodStartDate[2] := StartingDate;
        Evaluate(PeriodLength, SetPeriodLength);
        PrintAmountsInLCY := ShowAmountInLCY;
    end;
}

