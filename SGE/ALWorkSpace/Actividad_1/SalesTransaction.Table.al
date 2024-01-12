table 50101 "Sales Transaction"
{
    DataClassification = ToBeClassified;
    DrillDownPageId = "Sales Transaction";

    fields
    {
        field(1; "Line No."; Integer)
        {
            DataClassification = ToBeClassified;

        }

        field(2; "Salesperson Code"; Code[20])
        {
            TableRelation = "Salesperson/Purchaser" where("Commission %" = filter('<>0'));
        }


        field(3; "No."; Code[20])
        {
            TableRelation =
            IF (Type = const(Account)) "G/L Account"
            ELSE
            IF (Type = const(Item)) "G/L - Item Ledger Relation"
            ELSE
            IF (Type = const(Resource)) "G/L Entry - VAT Entry Link";
        }
        field(4; Amount; Decimal) { }

        field(5; Type; Enum "TransacionSales Line Type") { }
    }

    keys
    {
        key(PK; "Line No.")
        {
            Clustered = true;
        }
    }


}