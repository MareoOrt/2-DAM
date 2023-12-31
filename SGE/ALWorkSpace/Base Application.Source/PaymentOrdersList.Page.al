page 7000051 "Payment Orders List"
{
    ApplicationArea = Basic, Suite;
    Caption = 'Payment Orders';
    CardPageID = "Payment Orders";
    Editable = false;
    PageType = List;
    SourceTable = "Payment Order";
    UsageCategory = Lists;

    layout
    {
        area(content)
        {
            repeater(Control1)
            {
                ShowCaption = false;
                field("No."; "No.")
                {
                    ApplicationArea = All;
                    ToolTip = 'Specifies the number for this payment order.';
                }
                field("Bank Account No."; "Bank Account No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the number or code of the bank where the payment order is delivered.';
                }
                field("Bank Account Name"; "Bank Account Name")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the name of the bank where the payment order is delivered.';
                }
                field("Currency Code"; "Currency Code")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the currency code associated with this payment order.';
                }
                field(Amount; Amount)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the sum total of the documents included in this payment order.';
                }
                field("Amount (LCY)"; "Amount (LCY)")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the total amount of all of the documents included in this payment order.';
                }
                field("Elect. Pmts Exported"; "Elect. Pmts Exported")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies if the electronic payment has been exported for the amount on the Cartera Document.';
                }
            }
        }
        area(factboxes)
        {
            part(Control1901420307; "Pmt Orders Analysis Fact Box")
            {
                ApplicationArea = Basic, Suite;
                SubPageLink = "No." = FIELD("No.");
                Visible = true;
            }
            systempart(Control1905767507; Notes)
            {
                ApplicationArea = Notes;
                Visible = true;
            }
        }
    }

    actions
    {
        area(navigation)
        {
            group("Pmt. O&rd.")
            {
                Caption = 'Pmt. O&rd.';
                action(Comments)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Co&mments';
                    Image = ViewComments;
                    RunObject = Page "BG/PO Comment Sheet";
                    RunPageLink = "BG/PO No." = FIELD("No."),
                                  Type = FILTER(Payable);
                    ToolTip = 'View or create a comment.';
                }
                separator(Action21)
                {
                }
                action(Analysis)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Analysis';
                    Enabled = true;
                    Image = "Report";
                    RunObject = Page "Payment Orders Analysis";
                    RunPageLink = "No." = FIELD("No."),
                                  "Due Date Filter" = FIELD("Due Date Filter"),
                                  "Global Dimension 1 Filter" = FIELD("Global Dimension 1 Filter"),
                                  "Global Dimension 2 Filter" = FIELD("Global Dimension 2 Filter"),
                                  "Category Filter" = FIELD("Category Filter");
                    ToolTip = 'View details about the related documents. First you define which document category and currency you want to analyze documents for.';
                }
                separator(Action24)
                {
                }
                action(Listing)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Listing';
                    Ellipsis = true;
                    Enabled = true;
                    Image = List;
                    Promoted = true;
                    PromotedCategory = "Report";
                    ToolTip = 'View detailed information about the posted bill group or payment order.';

                    trigger OnAction()
                    begin
                        if Find then begin
                            PmtOrd.Copy(Rec);
                            PmtOrd.SetRecFilter;
                        end;
                        PmtOrd.PrintRecords(true);
                    end;
                }
            }
        }
        area(processing)
        {
            group("P&osting")
            {
                Caption = 'P&osting';
                Image = Post;
                action(TestReport)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Test Report';
                    Ellipsis = true;
                    Image = TestReport;
                    ToolTip = 'Preview the resulting entries to see the consequences before you perform the actual posting.';

                    trigger OnAction()
                    begin
                        if not Find then
                            exit;
                        PmtOrd.Reset();
                        PmtOrd := Rec;
                        PmtOrd.SetRecFilter;
                        REPORT.Run(REPORT::"Payment Order - Test", true, false, PmtOrd);
                    end;
                }
                action(Post)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'P&ost';
                    Ellipsis = true;
                    Image = Post;
                    Promoted = true;
                    PromotedCategory = Process;
                    PromotedIsBig = true;
                    ShortCutKey = 'F9';
                    ToolTip = 'Post the documents to indicate that they are ready to submit to the bank for payment or collection. ';

                    trigger OnAction()
                    begin
                        if Find then
                            PostBGPO.PayablePostOnly(Rec);
                    end;
                }
                action("Post and &Print")
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Post and &Print';
                    Ellipsis = true;
                    Image = PostPrint;
                    Promoted = true;
                    PromotedCategory = Process;
                    PromotedIsBig = true;
                    ShortCutKey = 'Shift+F9';
                    ToolTip = 'Post and then print the documents to indicate that they are ready to submit to the bank for payment or collection.';

                    trigger OnAction()
                    begin
                        if Find then
                            PostBGPO.PayablePostAndPrint(Rec);
                    end;
                }
            }
            action(GeneralPaymentOrdersMaturity)
            {
                ApplicationArea = Basic, Suite;
                Caption = 'Payment Orders Maturity';
                Image = DocumentsMaturity;
                Promoted = true;
                PromotedCategory = Process;
                RunObject = Page "Payment Orders Maturity";
                RunPageLink = "No." = FIELD("No."),
                              "Global Dimension 1 Filter" = FIELD("Global Dimension 1 Filter"),
                              "Global Dimension 2 Filter" = FIELD("Global Dimension 2 Filter"),
                              "Category Filter" = FIELD("Category Filter");
                ToolTip = 'View the document lines that have matured. Maturity information can be viewed by period start date.';
            }
        }
    }

    var
        PmtOrd: Record "Payment Order";
        PostBGPO: Codeunit "BG/PO-Post and Print";
}

