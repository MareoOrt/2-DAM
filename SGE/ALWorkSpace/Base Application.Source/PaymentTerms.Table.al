﻿table 3 "Payment Terms"
{
    Caption = 'Payment Terms';
    DataCaptionFields = "Code", Description;
    LookupPageID = "Payment Terms";

    fields
    {
        field(1; "Code"; Code[10])
        {
            Caption = 'Code';
            NotBlank = true;
        }
        field(2; "Due Date Calculation"; DateFormula)
        {
            Caption = 'Due Date Calculation';
        }
        field(3; "Discount Date Calculation"; DateFormula)
        {
            Caption = 'Discount Date Calculation';
        }
        field(4; "Discount %"; Decimal)
        {
            Caption = 'Discount %';
            DecimalPlaces = 0 : 5;
            MaxValue = 100;
            MinValue = 0;
        }
        field(5; Description; Text[100])
        {
            Caption = 'Description';
        }
        field(6; "Calc. Pmt. Disc. on Cr. Memos"; Boolean)
        {
            Caption = 'Calc. Pmt. Disc. on Cr. Memos';
        }
        field(8; "Last Modified Date Time"; DateTime)
        {
            Caption = 'Last Modified Date Time';
            Editable = false;
        }
        field(8000; Id; Guid)
        {
            Caption = 'Id';
            ObsoleteState = Pending;
            ObsoleteReason = 'This functionality will be replaced by the systemID field';
            ObsoleteTag = '15.0';
        }
        field(10710; "Max. No. of Days till Due Date"; Integer)
        {
            BlankZero = true;
            Caption = 'Max. No. of Days till Due Date';

            trigger OnValidate()
            begin
                if "Max. No. of Days till Due Date" < 0 then
                    Error(Text10701);
            end;
        }
        field(7000000; "No. of Installments"; Integer)
        {
            BlankZero = true;
            CalcFormula = Count (Installment WHERE("Payment Terms Code" = FIELD(Code)));
            Caption = 'No. of Installments';
            Editable = false;
            FieldClass = FlowField;
        }
        field(7000002; "VAT distribution"; Option)
        {
            Caption = 'VAT distribution';
            OptionCaption = 'First Installment,Last Installment,Proportional';
            OptionMembers = "First Installment","Last Installment",Proportional;
        }
    }

    keys
    {
        key(Key1; "Code")
        {
            Clustered = true;
        }
    }

    fieldgroups
    {
        fieldgroup(DropDown; "Code", Description, "Due Date Calculation")
        {
        }
        fieldgroup(Brick; "Code", Description, "Due Date Calculation")
        {
        }
    }

    trigger OnDelete()
    var
        PaymentTermsTranslation: Record "Payment Term Translation";
        O365SalesInitialSetup: Record "O365 Sales Initial Setup";
        EnvInfoProxy: Codeunit "Env. Info Proxy";
        Installment: Record Installment;
    begin
        if EnvInfoProxy.IsInvoicing then
            if O365SalesInitialSetup.Get and
               (O365SalesInitialSetup."Default Payment Terms Code" = Code)
            then
                Error(CannotRemoveDefaultPaymentTermsErr);

        with PaymentTermsTranslation do begin
            SetRange("Payment Term", Code);
            DeleteAll
        end;

        if Installment.WritePermission then begin
            Installment.SetRange("Payment Terms Code", Code);
            Installment.DeleteAll();
        end;
    end;

    trigger OnInsert()
    begin
        SetLastModifiedDateTime;
    end;

    trigger OnModify()
    begin
        SetLastModifiedDateTime;
    end;

    trigger OnRename()
    var
        CRMSyncHelper: Codeunit "CRM Synch. Helper";
    begin
        SetLastModifiedDateTime;
        CRMSyncHelper.UpdateCDSOptionMapping(xRec.RecordId(), RecordId());
    end;

    var
        CannotRemoveDefaultPaymentTermsErr: Label 'You cannot remove the default payment terms.';
        Text10700: Label 'The %1 exceeds the %2 defined on the %3.', Comment = '%1 is fieldcaption,%2 is fieldcaption,%3 is tablecaption';
        Text10701: Label 'The value must be greater than or equal to 0.';

    local procedure SetLastModifiedDateTime()
    begin
        "Last Modified Date Time" := CurrentDateTime;
    end;

    procedure TranslateDescription(var PaymentTerms: Record "Payment Terms"; Language: Code[10])
    var
        PaymentTermsTranslation: Record "Payment Term Translation";
    begin
        if PaymentTermsTranslation.Get(PaymentTerms.Code, Language) then
            PaymentTerms.Description := PaymentTermsTranslation.Description;
        OnAfterTranslateDescription(PaymentTerms, Language);
    end;

    procedure CalculateMaxDueDate(BaseDate: Date): Date
    begin
        if "Max. No. of Days till Due Date" = 0 then
            exit(99991231D);
        exit(CalcDate(StrSubstNo('<%1D>', "Max. No. of Days till Due Date"), BaseDate));
    end;

    [Scope('OnPrem')]
    procedure VerifyMaxNoDaysTillDueDate(DueDate: Date; DocumentDate: Date; MessageFieldCaption: Text[50])
    begin
        if (DueDate <> 0D) and ("Max. No. of Days till Due Date" > 0) then
            if DueDate - DocumentDate > "Max. No. of Days till Due Date" then
                Error(Text10700, MessageFieldCaption, FieldCaption("Max. No. of Days till Due Date"), TableCaption);
    end;

    procedure GetDescriptionInCurrentLanguage(): Text[50]
    var
        PaymentTermTranslation: Record "Payment Term Translation";
        Language: Codeunit Language;
    begin
        if PaymentTermTranslation.Get(Code, Language.GetUserLanguageCode) then
            exit(PaymentTermTranslation.Description);

        exit(Description);
    end;

    procedure UsePaymentDiscount(): Boolean
    var
        PaymentTerms: Record "Payment Terms";
    begin
        PaymentTerms.SetFilter("Discount %", '<>%1', 0);

        exit(not PaymentTerms.IsEmpty);
    end;

    [IntegrationEvent(false, false)]
    local procedure OnAfterTranslateDescription(var PaymentTerms: Record "Payment Terms"; Language: Code[10])
    begin
    end;
}

