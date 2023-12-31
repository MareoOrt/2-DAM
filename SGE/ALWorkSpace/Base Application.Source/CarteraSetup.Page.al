page 7000040 "Cartera Setup"
{
    ApplicationArea = Basic, Suite;
    Caption = 'Cartera Setup';
    DeleteAllowed = false;
    InsertAllowed = false;
    PageType = Card;
    SourceTable = "Cartera Setup";
    UsageCategory = Administration;

    layout
    {
        area(content)
        {
            group(General)
            {
                Caption = 'General';
                field("Bills Discount Limit Warnings"; "Bills Discount Limit Warnings")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies if a warning message is issued when the credit limit authorized by the selected bank for a bill group is exceeded.';
                }
                field("Euro Currency Code"; "Euro Currency Code")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the currency code that represents the euro.';
                }
            }
            group(Numbering)
            {
                Caption = 'Numbering';
                field("GenLedgerSetup.""Bank Account Nos."""; GenLedgerSetup."Bank Account Nos.")
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Bank Account Nos.';
                    TableRelation = "No. Series";
                    ToolTip = 'Specifies the number series for bank accounts.';

                    trigger OnValidate()
                    begin
                        GenLedgerSetupBankAccountNosOn;
                    end;
                }
                field("Bill Group Nos."; "Bill Group Nos.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the numbering code used to assign numbers to bill groups.';
                }
                field("Payment Order Nos."; "Payment Order Nos.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the numbering code used to assign numbers to payment orders.';
                }
            }
        }
    }

    actions
    {
    }

    trigger OnAfterGetRecord()
    begin
        GenLedgerSetup.Get();
    end;

    trigger OnOpenPage()
    begin
        GenLedgerSetup.Get();
    end;

    var
        GenLedgerSetup: Record "General Ledger Setup";

    local procedure GenLedgerSetupBankAccountNosOn()
    begin
        GenLedgerSetup.Modify();
    end;
}

