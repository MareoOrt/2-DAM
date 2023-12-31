table 7000008 "BG/PO Comment Line"
{
    Caption = 'BG/PO Comment Line';
    DrillDownPageID = "BG/PO Comment List";
    LookupPageID = "BG/PO Comment List";

    fields
    {
        field(2; "BG/PO No."; Code[20])
        {
            Caption = 'BG/PO No.';
            NotBlank = true;
        }
        field(3; "Line No."; Integer)
        {
            Caption = 'Line No.';
        }
        field(4; Date; Date)
        {
            Caption = 'Date';
        }
        field(5; "Code"; Code[10])
        {
            Caption = 'Code';
        }
        field(6; Comment; Text[80])
        {
            Caption = 'Comment';
        }
        field(7; Type; Option)
        {
            Caption = 'Type';
            OptionCaption = 'Receivable,Payable';
            OptionMembers = Receivable,Payable;
        }
    }

    keys
    {
        key(Key1; "BG/PO No.", Type, "Line No.")
        {
            Clustered = true;
        }
    }

    fieldgroups
    {
    }

    [Scope('OnPrem')]
    procedure SetUpNewLine()
    var
        BGPOCommentLine: Record "BG/PO Comment Line";
    begin
        BGPOCommentLine.SetRange("BG/PO No.", "BG/PO No.");
        BGPOCommentLine.SetRange(Type, Type);
        if not BGPOCommentLine.FindFirst then
            Date := WorkDate;
    end;

    procedure Caption(): Text
    var
        BillGr: Record "Bill Group";
        PostedBillGr: Record "Posted Bill Group";
        ClosedBillGr: Record "Closed Bill Group";
        PmtOrd: Record "Payment Order";
        PostedPmtOrd: Record "Posted Payment Order";
        ClosedPmtOrd: Record "Closed Payment Order";
    begin
        case true of
            BillGr.Get("BG/PO No."):
                begin
                    BillGr.CalcFields("Bank Account Name");
                    exit(StrSubstNo('%1 %2', BillGr."No.", BillGr."Bank Account Name"));
                end;
            PostedBillGr.Get("BG/PO No."):
                begin
                    PostedBillGr.CalcFields("Bank Account Name");
                    exit(StrSubstNo('%1 %2', PostedBillGr."No.", PostedBillGr."Bank Account Name"));
                end;
            ClosedBillGr.Get("BG/PO No."):
                begin
                    ClosedBillGr.CalcFields("Bank Account Name");
                    exit(StrSubstNo('%1 %2', ClosedBillGr."No.", ClosedBillGr."Bank Account Name"));
                end;
            PmtOrd.Get("BG/PO No."):
                begin
                    PmtOrd.CalcFields("Bank Account Name");
                    exit(StrSubstNo('%1 %2', PmtOrd."No.", PmtOrd."Bank Account Name"));
                end;
            PostedPmtOrd.Get("BG/PO No."):
                begin
                    PostedPmtOrd.CalcFields("Bank Account Name");
                    exit(StrSubstNo('%1 %2', PostedPmtOrd."No.", PostedPmtOrd."Bank Account Name"));
                end;
            ClosedPmtOrd.Get("BG/PO No."):
                begin
                    ClosedPmtOrd.CalcFields("Bank Account Name");
                    exit(StrSubstNo('%1 %2', ClosedPmtOrd."No.", ClosedPmtOrd."Bank Account Name"));
                end;
        end;
    end;
}

