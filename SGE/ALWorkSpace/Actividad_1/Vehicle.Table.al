table 50100 Vehicle
{
    DataClassification = ToBeClassified;
    Caption = 'Vehiculo';

    fields
    {
        field(1; Model; Code[20])
        {
            DataClassification = ToBeClassified;

        }
        field(2; "Serial No."; Integer) { }
        field(3; Description; Text[100]) { }
        field(4; "List Price"; Decimal) { }
        field(5; "Date of Manufacturing"; Date) { }
        field(6; Transmission; Option)
        {
            OptionMembers = Automatic,"4-Speed","5-Speed";
        }
    }
    keys
    {
        key(pk; Model, "Serial No.")
        {
            Clustered = true;
        }
        key(key2; "Date of Manufacturing", Transmission) { }
    }


}