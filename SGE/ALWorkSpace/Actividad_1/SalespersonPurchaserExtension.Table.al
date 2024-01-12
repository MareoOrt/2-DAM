tableextension 50100 "Salesperson/Purchaser" extends "Salesperson/Purchaser"
{
    fields
    {
        field(50100; "Total Sales"; Decimal)
        {
            // Configuramos como campo figurado

            Editable = false;
            FieldClass = FlowField;
            CalcFormula = sum("Sales Transaction".Amount where("Salesperson Code" = field(Code)));

        }
    }


}