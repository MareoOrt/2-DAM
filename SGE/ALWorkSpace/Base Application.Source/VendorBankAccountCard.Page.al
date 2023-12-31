page 425 "Vendor Bank Account Card"
{
    Caption = 'Vendor Bank Account Card';
    PageType = Card;
    SourceTable = "Vendor Bank Account";

    layout
    {
        area(content)
        {
            group(General)
            {
                Caption = 'General';
                field("Code"; Code)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies a code to identify this vendor bank account.';
                }
                field(Name; Name)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the name of the bank where the vendor has this bank account.';
                }
                field(Address; Address)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the address of the bank where the vendor has the bank account.';
                }
                field("Address 2"; "Address 2")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies additional address information.';
                }
                field("Post Code"; "Post Code")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the postal code.';
                }
                field(City; City)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the city of the bank where the vendor has the bank account.';
                }
                field(County; County)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the county of the address.';
                }
                field("Country/Region Code"; "Country/Region Code")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the country/region of the address.';
                }
                field("Phone No."; "Phone No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the telephone number of the bank where the vendor has the bank account.';
                }
                field(Contact; Contact)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the name of the bank employee regularly contacted in connection with this bank account.';
                }
                field("Currency Code"; "Currency Code")
                {
                    ApplicationArea = Suite;
                    ToolTip = 'Specifies the relevant currency code for the bank account.';
                }
                field("Transit No."; "Transit No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies a bank identification number of your own choice.';
                }
                field("Use For Electronic Payments"; "Use For Electronic Payments")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies if this vendor bank account to be used for electronic payments.';

                    trigger OnValidate()
                    begin
                        UseForElectronicPaymentsOnPush;
                    end;
                }
            }
            group(Communication)
            {
                Caption = 'Communication';
                field("Fax No."; "Fax No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the fax number of the bank where the vendor has the bank account.';
                }
                field("E-Mail"; "E-Mail")
                {
                    ApplicationArea = Basic, Suite;
                    ExtendedDatatype = EMail;
                    ToolTip = 'Specifies the email address associated with the bank account.';
                }
                field("Home Page"; "Home Page")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the bank web site.';
                }
            }
            group(Transfer)
            {
                Caption = 'Transfer';
                field("CCC Bank No."; "CCC Bank No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the bank account code. This code is the first part of the Codigo Cuenta Cliente (CCC) number.';
                }
                field("CCC Bank Branch No."; "CCC Bank Branch No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the four-digit bank office number. This number is the second part of the Codigo Cuenta Cliente (CCC) number.';
                }
                field("CCC Control Digits"; "CCC Control Digits")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the two-digit account control code. This number is the third part of the Codigo Cuenta Cliente (CCC) number.';
                }
                field("CCC Bank Account No."; "CCC Bank Account No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the company''s bank account code.';
                }
                field("CCC No."; "CCC No.")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the Codigo Cuenta Cliente (CCC) number.';
                }
                field("SWIFT Code"; "SWIFT Code")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the SWIFT code (international bank identifier code) of the bank where the vendor has the account.';
                }
                field(IBAN; IBAN)
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the bank account''s international bank account number.';
                }
                field("Bank Clearing Standard"; "Bank Clearing Standard")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the format standard to be used in bank transfers if you use the Bank Clearing Code field to identify you as the sender.';
                }
                field("Bank Clearing Code"; "Bank Clearing Code")
                {
                    ApplicationArea = Basic, Suite;
                    ToolTip = 'Specifies the code for bank clearing that is required according to the format standard you selected in the Bank Clearing Standard field.';
                }
            }
        }
        area(factboxes)
        {
            systempart(Control1900383207; Links)
            {
                ApplicationArea = RecordLinks;
                Visible = false;
            }
            systempart(Control1905767507; Notes)
            {
                ApplicationArea = Notes;
                Visible = false;
            }
        }
    }

    actions
    {
    }

    local procedure UseForElectronicPaymentsOnPush()
    begin
        CurrPage.Update;
    end;
}

