table 275 "Bank Account Statement"
{
    Caption = 'Bank Account Statement';
    DataCaptionFields = "Bank Account No.", "Statement No.";
    LookupPageID = "Bank Account Statement List";

    fields
    {
        field(1; "Bank Account No."; Code[20])
        {
            Caption = 'Bank Account No.';
            NotBlank = true;
            TableRelation = "Bank Account";
        }
        field(2; "Statement No."; Code[20])
        {
            Caption = 'Statement No.';
            NotBlank = true;
        }
        field(3; "Statement Ending Balance"; Decimal)
        {
            AutoFormatExpression = GetCurrencyCode;
            AutoFormatType = 1;
            Caption = 'Statement Ending Balance';
        }
        field(4; "Statement Date"; Date)
        {
            Caption = 'Statement Date';
        }
        field(5; "Balance Last Statement"; Decimal)
        {
            AutoFormatExpression = GetCurrencyCode;
            AutoFormatType = 1;
            Caption = 'Balance Last Statement';
            Editable = false;
        }
    }

    keys
    {
        key(Key1; "Bank Account No.", "Statement No.")
        {
            Clustered = true;
        }
    }

    fieldgroups
    {
        fieldgroup(DropDown; "Bank Account No.", "Statement No.")
        {
        }
    }

    trigger OnDelete()
    begin
        if not Confirm(HasBankEntriesQst, false, "Bank Account No.", "Statement No.") then
            Error('');
        CODEUNIT.Run(CODEUNIT::"BankAccStmtLines-Delete", Rec);
    end;

    trigger OnRename()
    begin
        Error(Text000, TableCaption);
    end;

    var
        Text000: Label 'You cannot rename a %1.';
        HasBankEntriesQst: Label 'One or more bank account ledger entries in bank account %1 have been reconciled for bank account statement %2, which contain information about the bank statement. These bank ledger entries will not be modified if you delete bank account statement %2.\\Do you want to continue?';

    local procedure GetCurrencyCode(): Code[10]
    var
        BankAcc: Record "Bank Account";
    begin
        if "Bank Account No." = BankAcc."No." then
            exit(BankAcc."Currency Code");

        if BankAcc.Get("Bank Account No.") then
            exit(BankAcc."Currency Code");

        exit('');
    end;
}

