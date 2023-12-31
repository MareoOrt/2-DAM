page 7000033 "Posted Bill Groups Maturity"
{
    Caption = 'Posted Bill Groups Maturity';
    DataCaptionExpression = Caption;
    DeleteAllowed = false;
    InsertAllowed = false;
    PageType = List;
    SaveValues = true;
    SourceTable = "Posted Bill Group";

    layout
    {
        area(content)
        {
            group(General)
            {
                Caption = 'General';
                field("Category Filter"; "Category Filter")
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Category Filter';
                    ToolTip = 'Specifies the categories that the data is included for.';

                    trigger OnValidate()
                    begin
                        UpdateSubForm;
                    end;
                }
            }
            group(Options)
            {
                Caption = 'Options';
                field(PeriodType; PeriodType)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'View by';
                    OptionCaption = 'Day,Week,Month,Quarter,Year,Period';
                    ToolTip = 'Specifies by which period amounts are displayed.';

                    trigger OnValidate()
                    begin
                        UpdateSubForm;
                    end;
                }
                field(AmountType; AmountType)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'View as';
                    OptionCaption = 'Net Change,Balance at Date';
                    ToolTip = 'Specifies how amounts are displayed. Net Change: The net change in the balance for the selected period. Balance at Date: The balance as of the last day in the selected period.';

                    trigger OnValidate()
                    begin
                        UpdateSubForm;
                    end;
                }
            }
            part(MaturityLines; "Posted Bill Gr. Maturity Lines")
            {
                ApplicationArea = Basic, Suite;
            }
        }
    }

    actions
    {
    }

    trigger OnAfterGetRecord()
    begin
        UpdateSubForm;
    end;

    trigger OnOpenPage()
    begin
        UpdateSubForm;
    end;

    var
        PeriodType: Option Day,Week,Month,Quarter,Year,Period;
        AmountType: Option "Net Change","Balance at Date";

    local procedure UpdateSubForm()
    begin
        CurrPage.MaturityLines.PAGE.Set(Rec, PeriodType, AmountType);
    end;
}

