pageextension 50100 "Salespersons/Purchasers"
 extends "Salesperson/Purchaser Card"
{
    layout
    {
        addafter("Phone No.")
        {
            field("Total Sales"; Rec."Total Sales")
            {
                ApplicationArea = all;
            }
        }
    }
}