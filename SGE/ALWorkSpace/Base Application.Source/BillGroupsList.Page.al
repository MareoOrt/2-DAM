page 7000011 "Bill Groups List"
{
    ApplicationArea = Basic, Suite;
    Caption = 'Bill Groups';
    CardPageID = "Bill Groups";
    Editable = false;
    PageType = List;
    SourceTable = "Bill Group";
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
                    ToolTip = 'Specifies the number of this bill group.';
                }
                field("Bank Account No."; "Bank Account No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the number or code of the bank, to which this bill group is being submitted.';
                }
                field("Bank Account Name"; "Bank Account Name")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the number of the bank to which this bill group is submitted.';
                }
                field("Currency Code"; "Currency Code")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the currency code for the bill group.';
                }
                field(Amount; Amount)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the total of the sums of the documents included in the bill group.';
                }
                field("Amount (LCY)"; "Amount (LCY)")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the total amount for all of the documents included in the bill group.';
                }
            }
        }
        area(factboxes)
        {
            part(Control1901421207; "BG Analysis Fact Box")
            {
                ApplicationArea = Basic, Suite;
                SubPageLink = "No." = FIELD("No.");
                Visible = true;
            }
            part(Control1903433307; "Bank Account Information FB")
            {
                ApplicationArea = Basic, Suite;
                SubPageLink = "No." = FIELD("Bank Account No.");
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
            group("Bill &Group")
            {
                Caption = 'Bill &Group';
                Image = VoucherGroup;
                action(Comments)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Co&mments';
                    Image = ViewComments;
                    RunObject = Page "BG/PO Comment Sheet";
                    RunPageLink = Type = FILTER(Receivable),
                                  "BG/PO No." = FIELD("No.");
                    ToolTip = 'View or create a comment.';
                }
                separator(Action21)
                {
                }
                action(Analysis)
                {
                    ApplicationArea = Basic, Suite;
                    Caption = 'Analysis';
                    Image = "Report";
                    RunObject = Page "Bill Groups Analysis";
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
                    Image = List;
                    Promoted = true;
                    PromotedCategory = "Report";
                    ToolTip = 'View detailed information about the posted bill group or payment order.';

                    trigger OnAction()
                    begin
                        if Find then begin
                            BillGr.Copy(Rec);
                            BillGr.SetRecFilter;
                        end;
                        BillGr.PrintRecords(true);
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
                        BillGr.Reset();
                        BillGr := Rec;
                        BillGr.SetRecFilter;
                        REPORT.Run(REPORT::"Bill Group - Test", true, false, BillGr);
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
                            PostBillGr.ReceivablePostOnly(Rec);
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
                            PostBillGr.ReceivablePostAndPrint(Rec);
                    end;
                }
            }
            action(GeneralBillGroupsMaturity)
            {
                ApplicationArea = Basic, Suite;
                Caption = 'Bill Groups Maturity';
                Image = DocumentsMaturity;
                Promoted = true;
                PromotedCategory = Process;
                RunObject = Page "Bill Groups Maturity";
                RunPageLink = "No." = FIELD("No."),
                              "Global Dimension 1 Filter" = FIELD("Global Dimension 1 Filter"),
                              "Global Dimension 2 Filter" = FIELD("Global Dimension 2 Filter"),
                              "Category Filter" = FIELD("Category Filter");
                ToolTip = 'View matured bill groups.';
            }
        }
    }

    var
        BillGr: Record "Bill Group";
        PostBillGr: Codeunit "BG/PO-Post and Print";
}

