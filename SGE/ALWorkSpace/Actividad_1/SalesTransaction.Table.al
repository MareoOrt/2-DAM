table 50101 "Sales Transaction"
{
    DataClassification = ToBeClassified;

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


        field(3; "Item No."; Code[20]) { }
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