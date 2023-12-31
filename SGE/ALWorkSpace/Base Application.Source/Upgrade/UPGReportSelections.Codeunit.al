codeunit 104107 "Upg Report Selections"
{
    Subtype = Upgrade;

    trigger OnRun()
    begin
    end;

    trigger OnUpgradePerCompany()
    begin
        UpdateReportSelections;
    end;

    local procedure UpdateReportSelections()
    var
        ReportSelections: Record "Report Selections";
        TempReportSelections: Record "Report Selections";
        UpgradeTag: Codeunit "Upgrade Tag";
        UpgradeTagDefCountry: Codeunit "Upgrade Tag Def - Country";
    begin
        IF UpgradeTag.HasUpgradeTag(UpgradeTagDefCountry.GetUpdateReportSelectionsTag) THEN
            EXIT;

        TempReportSelections.DeleteAll();

        ReportSelections.SetRange(Usage, 58, 59);
        if ReportSelections.FindSet then
            repeat
                TempReportSelections := ReportSelections;
                case ReportSelections.Usage of
                    58:
                        TempReportSelections.Usage := 100;
                    59:
                        TempReportSelections.Usage := 101;
                end;
                TempReportSelections.Insert();
            until ReportSelections.Next = 0;

        TempReportSelections.Reset();
        if TempReportSelections.FindSet then begin
            repeat
                ReportSelections := TempReportSelections;
                ReportSelections.Insert();
            until TempReportSelections.Next = 0;

            ReportSelections.SetRange(Usage, 58, 59);
            ReportSelections.DeleteAll();
        end;

        UpgradeTag.SetUpgradeTag(UpgradeTagDefCountry.GetUpdateReportSelectionsTag);
    end;
}

