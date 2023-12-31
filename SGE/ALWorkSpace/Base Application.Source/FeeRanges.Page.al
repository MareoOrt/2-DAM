page 7000049 "Fee Ranges"
{
    Caption = 'Fee Ranges';
    DataCaptionExpression = Caption;
    PageType = List;
    SourceTable = "Fee Range";

    layout
    {
        area(content)
        {
            repeater(Control1)
            {
                ShowCaption = false;
                field("From No. of Days"; "From No. of Days")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the number of days for which commissions will be charged.';
                }
                field("Charge Amount per Doc."; "Charge Amount per Doc.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the amount per document that will be charged for each of the operations that generates commissions.';
                }
                field("Charge % per Doc."; "Charge % per Doc.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the percentage per document that will be charged for each type of operation that generates commissions.';
                }
                field("Minimum Amount"; "Minimum Amount")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the minimum amount charged for each operation type, if the sum of the rest of the commissions is less than this minimum charge.';
                }
            }
        }
    }

    actions
    {
    }
}

