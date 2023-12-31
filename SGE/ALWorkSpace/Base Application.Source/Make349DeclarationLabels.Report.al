report 10709 "Make 349 Declaration Labels"
{
    DefaultLayout = RDLC;
    RDLCLayout = './Make349DeclarationLabels.rdlc';
    ApplicationArea = Basic, Suite;
    Caption = '349 Declaration Labels';
    UsageCategory = ReportsAndAnalysis;

    dataset
    {
        dataitem("Integer"; "Integer")
        {
            DataItemTableView = SORTING(Number);
            column(A; A)
            {
            }
            column(B; B)
            {
            }
            column(C; C)
            {
            }
            column(D; D)
            {
            }
            column(E; E)
            {
            }
            column(B1; B1)
            {
            }
            column(F; F)
            {
            }
            column(G; G)
            {
            }
            column(G1; G1)
            {
            }
            column(G2; G2)
            {
            }
            column(H; H)
            {
            }
            column(I; I)
            {
            }
            column(J; J)
            {
            }
            column(K; K)
            {
            }
            column(Counter_1; Counter - 1)
            {
            }
            column(NoOfLabels; NoOfLabels)
            {
            }
            column(L; L)
            {
            }
            column(M; M)
            {
            }
            column(IsBody1; Counter = NoOfLabels + 1)
            {
            }
            column(IsBody1_Control1100002; PageBreaker)
            {
            }
            column(A_Control1; A)
            {
            }
            column(B_Control6; B)
            {
            }
            column(C_Control8; C)
            {
            }
            column(D_Control10; D)
            {
            }
            column(E_Control12; E)
            {
            }
            column(F_Control14; F)
            {
            }
            column(G_Control16; G)
            {
            }
            column(G1_Control18; G1)
            {
            }
            column(G2_Control20; G2)
            {
            }
            column(H_Control22; H)
            {
            }
            column(I_Control24; I)
            {
            }
            column(J_Control26; J)
            {
            }
            column(K_Control28; K)
            {
            }
            column(B1_Control34; B1)
            {
            }
            column(Counter; Counter)
            {
            }
            column(NoOfLabels_Control5; NoOfLabels)
            {
            }
            column(A_Control31; A)
            {
            }
            column(B_Control32; B)
            {
            }
            column(C_Control33; C)
            {
            }
            column(D_Control37; D)
            {
            }
            column(E_Control39; E)
            {
            }
            column(B1_Control42; B1)
            {
            }
            column(F_Control43; F)
            {
            }
            column(G_Control45; G)
            {
            }
            column(G1_Control47; G1)
            {
            }
            column(G2_Control48; G2)
            {
            }
            column(H_Control49; H)
            {
            }
            column(I_Control51; I)
            {
            }
            column(J_Control53; J)
            {
            }
            column(K_Control55; K)
            {
            }
            column(Counter_1_Control57; Counter - 1)
            {
            }
            column(NoOfLabels_Control59; NoOfLabels)
            {
            }
            column(M_Control96; M)
            {
            }
            column(L_Control97; L)
            {
            }
            column(M_Control105; M)
            {
            }
            column(L_Control106; L)
            {
            }
            column(IsBody2; Counter <= NoOfLabels)
            {
            }
            column(Integer_Number; Number)
            {
            }
            column(ACaption; ACaptionLbl)
            {
            }
            column(BCaption; BCaptionLbl)
            {
            }
            column(CCaption; CCaptionLbl)
            {
            }
            column(DCaption; DCaptionLbl)
            {
            }
            column(ECaption; ECaptionLbl)
            {
            }
            column(B1Caption; B1CaptionLbl)
            {
            }
            column(FCaption; FCaptionLbl)
            {
            }
            column(GCaption; GCaptionLbl)
            {
            }
            column(HCaption; HCaptionLbl)
            {
            }
            column(ICaption; ICaptionLbl)
            {
            }
            column(JCaption; JCaptionLbl)
            {
            }
            column(KCaption; KCaptionLbl)
            {
            }
            column(Counter_1Caption; Counter_1CaptionLbl)
            {
            }
            column(NoOfLabelsCaption; NoOfLabelsCaptionLbl)
            {
            }
            column(M_Caption; M_CaptionLbl)
            {
            }
            column(L_Caption; L_CaptionLbl)
            {
            }
            column(A_Control1Caption; A_Control1CaptionLbl)
            {
            }
            column(B_Control6Caption; B_Control6CaptionLbl)
            {
            }
            column(C_Control8Caption; C_Control8CaptionLbl)
            {
            }
            column(D_Control10Caption; D_Control10CaptionLbl)
            {
            }
            column(E_Control12Caption; E_Control12CaptionLbl)
            {
            }
            column(F_Control14Caption; F_Control14CaptionLbl)
            {
            }
            column(G_Control16Caption; G_Control16CaptionLbl)
            {
            }
            column(H_Control22Caption; H_Control22CaptionLbl)
            {
            }
            column(I_Control24Caption; I_Control24CaptionLbl)
            {
            }
            column(J_Control26Caption; J_Control26CaptionLbl)
            {
            }
            column(K_Control28Caption; K_Control28CaptionLbl)
            {
            }
            column(CounterCaption; CounterCaptionLbl)
            {
            }
            column(NoOfLabels_Control5Caption; NoOfLabels_Control5CaptionLbl)
            {
            }
            column(A_Control31Caption; A_Control31CaptionLbl)
            {
            }
            column(B_Control32Caption; B_Control32CaptionLbl)
            {
            }
            column(C_Control33Caption; C_Control33CaptionLbl)
            {
            }
            column(D_Control37Caption; D_Control37CaptionLbl)
            {
            }
            column(E_Control39Caption; E_Control39CaptionLbl)
            {
            }
            column(F_Control43Caption; F_Control43CaptionLbl)
            {
            }
            column(G_Control45Caption; G_Control45CaptionLbl)
            {
            }
            column(H_Control49Caption; H_Control49CaptionLbl)
            {
            }
            column(I_Control51Caption; I_Control51CaptionLbl)
            {
            }
            column(J_Control53Caption; J_Control53CaptionLbl)
            {
            }
            column(K_Control55Caption; K_Control55CaptionLbl)
            {
            }
            column(Counter_1_Control57Caption; Counter_1_Control57CaptionLbl)
            {
            }
            column(M_Control96Caption; M_Control96CaptionLbl)
            {
            }
            column(L_Control97Caption; L_Control97CaptionLbl)
            {
            }
            column(M_Caption_Control103; M_Caption_Control103Lbl)
            {
            }
            column(L_Caption_Control104; L_Caption_Control104Lbl)
            {
            }

            trigger OnAfterGetRecord()
            begin
                Counter := Counter + 2;
                if Counter > NoOfLabels + 1 then
                    CurrReport.Break();

                if SectionsCounter <> 5 then begin
                    SectionsCounter := SectionsCounter + 1;
                    if Counter <= NoOfLabels + 1 then begin
                        if SectionsPerPage = SectionsCounter then begin
                            PageBreaker := PageBreaker + 1;
                            SectionsCounter := 0;
                        end;
                    end;
                end;
                if SectionsCounter = 5 then
                    SectionsCounter := 0;
            end;

            trigger OnPreDataItem()
            begin
                K := StrSubstNo('#1##########', MediaDensity);

                Counter := 0;
                SectionsCounter := 5;
                PageBreaker := 0;
                SectionsPerPage := 4;
            end;
        }
    }

    requestpage
    {
        SaveValues = true;

        layout
        {
            area(content)
            {
                group(Options)
                {
                    Caption = 'Options';
                    field(A; A)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'A) AEAT Delegation';
                        ToolTip = 'Specifies the Spanish tax delegation code.';
                    }
                    group("B)")
                    {
                        Caption = 'B)';
                        field(B; B)
                        {
                            ApplicationArea = Basic, Suite;
                            Caption = 'Fiscal Year';
                            ToolTip = 'Specifies the year of the reporting period. It must be 4 digits without spaces or special characters.';
                        }
                        field(B1; B1)
                        {
                            ApplicationArea = Basic, Suite;
                            Caption = 'Period';
                            ToolTip = 'Specifies the period for the declaration.';
                        }
                    }
                    field(C; C)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'C) Model Layout';
                        ToolTip = 'Specifies 349 as the model layout.';
                    }
                    field(D; D)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'D) No. of Just. 1st Page';
                        ToolTip = 'Specifies the control number for the first summary page of the declaration.';
                    }
                    field(E; E)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'E) VAT Registration No.';
                        ToolTip = 'Specifies the VAT registration number that is associated with the declaration.';
                    }
                    field(F; F)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'F) Company Name';
                        ToolTip = 'Specifies the name of the company that is making the declaration.';
                    }
                    group("G)")
                    {
                        Caption = 'G)';
                        field(G; G)
                        {
                            ApplicationArea = Basic, Suite;
                            Caption = 'Address';
                            ToolTip = 'Specifies the address of the company that is making the declaration.';
                        }
                        field(G2; G2)
                        {
                            ApplicationArea = Basic, Suite;
                            Caption = 'Post Code/City';
                            ToolTip = 'Specifies the company postal code that is associated with the declaration.';
                        }
                    }
                    field(G1; G1)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'City';
                        ToolTip = 'Specifies the city of the company that is making the declaration.';
                    }
                    field(H; H)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'H) Contact';
                        ToolTip = 'Specifies the contact that is making the declaration.';
                    }
                    field(I; I)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'I) Phone No.';
                        ToolTip = 'Specifies the company telephone number that is making the declaration.';
                    }
                    field(J; J)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'J) Total No. of Records';
                        ToolTip = 'Specifies the total number of records included in the media.';
                    }
                    field(MediaDensity; MediaDensity)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'K) Media Density';
                        ToolTip = 'Specifies the density of the magnetic media.';
                    }
                    field(L; L)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'L) Total No of passive subjects declared';
                        ToolTip = 'Specifies the total number of passive subjects declared.';
                    }
                    field(M; M)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'M) Total No of related personas and entities entries';
                        ToolTip = 'Specifies the total number of related personas and entities that are associated with the declaration.';
                    }
                    field(NoOfLabels; NoOfLabels)
                    {
                        ApplicationArea = Basic, Suite;
                        Caption = 'No. of Labels';
                        ToolTip = 'Specifies the number of labels to print.';
                    }
                }
            }
        }

        actions
        {
        }
    }

    labels
    {
    }

    trigger OnInitReport()
    begin
        CompanyInfo.Get();
        if A = '' then
            A := '0000';
        C := '349';
        if E = '' then
            E := CompanyInfo."VAT Registration No.";
        if F = '' then
            F := CompanyInfo.Name;
        if G = '' then
            G := CompanyInfo.Address;
        if G1 = '' then
            G1 := CompanyInfo.City;
        if G2 = '' then
            G2 := CompanyInfo."Post Code";
        if I = '' then
            I := CompanyInfo."Phone No.";
        if K = '' then
            K := Text1100000;
        if NoOfLabels = 0 then
            NoOfLabels := 1;
    end;

    var
        Text1100000: Label '720KB';
        CompanyInfo: Record "Company Information";
        A: Code[4];
        B: Code[2];
        B1: Code[2];
        C: Code[3];
        D: Text[15];
        E: Text[15];
        F: Text[100];
        G: Text[100];
        G1: Text[30];
        G2: Text[20];
        H: Text[40];
        I: Text[20];
        J: Integer;
        K: Text[15];
        L: Integer;
        M: Integer;
        MediaDensity: Option "360KB","720KB","1.2MB","1.44MB";
        NoOfLabels: Integer;
        Counter: Integer;
        SectionsCounter: Integer;
        SectionsPerPage: Integer;
        PageBreaker: Integer;
        ACaptionLbl: Label 'A)';
        BCaptionLbl: Label 'B)';
        CCaptionLbl: Label 'C)';
        DCaptionLbl: Label 'D)';
        ECaptionLbl: Label 'E)';
        B1CaptionLbl: Label '/', Locked = true;
        FCaptionLbl: Label 'F)';
        GCaptionLbl: Label 'G)';
        HCaptionLbl: Label 'H)';
        ICaptionLbl: Label 'I)';
        JCaptionLbl: Label 'J)';
        KCaptionLbl: Label 'K)';
        Counter_1CaptionLbl: Label 'Number in sequence';
        NoOfLabelsCaptionLbl: Label '/', Locked = true;
        M_CaptionLbl: Label 'M)';
        L_CaptionLbl: Label 'L)';
        A_Control1CaptionLbl: Label 'A)';
        B_Control6CaptionLbl: Label 'B)';
        C_Control8CaptionLbl: Label 'C)';
        D_Control10CaptionLbl: Label 'D)';
        E_Control12CaptionLbl: Label 'E)';
        F_Control14CaptionLbl: Label 'F)';
        G_Control16CaptionLbl: Label 'G)';
        H_Control22CaptionLbl: Label 'H)';
        I_Control24CaptionLbl: Label 'I)';
        J_Control26CaptionLbl: Label 'J)';
        K_Control28CaptionLbl: Label 'K)';
        CounterCaptionLbl: Label 'Number in sequence';
        NoOfLabels_Control5CaptionLbl: Label '/', Locked = true;
        A_Control31CaptionLbl: Label 'A)';
        B_Control32CaptionLbl: Label 'B)';
        C_Control33CaptionLbl: Label 'C)';
        D_Control37CaptionLbl: Label 'D)';
        E_Control39CaptionLbl: Label 'E)';
        F_Control43CaptionLbl: Label 'F)';
        G_Control45CaptionLbl: Label 'G)';
        H_Control49CaptionLbl: Label 'H)';
        I_Control51CaptionLbl: Label 'I)';
        J_Control53CaptionLbl: Label 'J)';
        K_Control55CaptionLbl: Label 'K)';
        Counter_1_Control57CaptionLbl: Label 'Number in sequence';
        M_Control96CaptionLbl: Label 'M)';
        L_Control97CaptionLbl: Label 'L)';
        M_Caption_Control103Lbl: Label 'M)';
        L_Caption_Control104Lbl: Label 'L)';
}

