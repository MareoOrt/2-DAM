codeunit 7000089 "Bill group - Export factoring"
{
    TableNo = "Direct Debit Collection Entry";

    trigger OnRun()
    var
        BillGroup: Record "Bill Group";
        DirectDebitCollection: Record "Direct Debit Collection";
        BillGroupNo: Code[20];
    begin
        DirectDebitCollection.Get(GetRangeMin("Direct Debit Collection No."));
        BillGroupNo := DirectDebitCollection.Identifier;
        DirectDebitCollection.Delete();
        if not BillGroup.Get(BillGroupNo) then
            Error(ExportDirectDebitErr);
        Commit();
        REPORT.Run(REPORT::"Bill group - Export factoring", true, false, BillGroup);
    end;

    var
        ExportDirectDebitErr: Label 'You cannot export the direct debit with the selected SEPA Direct Debit Exp. Format in To Bank Account No.';
}

