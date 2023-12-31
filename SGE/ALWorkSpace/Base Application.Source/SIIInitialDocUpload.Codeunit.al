codeunit 10755 "SII Initial Doc. Upload"
{

    trigger OnRun()
    begin
        HandleExistingPostedDocuments;
    end;

    var
        SIIJobManagement: Codeunit "SII Job Management";
        StartDate: Date;
        EndDate: Date;
        JobType: Option HandlePending,HandleCommError,InitialUpload;

    local procedure HandleExistingPostedDocuments()
    begin
        // We need to upload all the documents poseted from 01 January 2017 to 01 July 2017
        StartDate := GetInitialStartDate;
        EndDate := GetInitialEndDate;

        HandleExistingCustomerLedgerEntries;
        HandleExistingVendorLedgerEntries;

        SIIJobManagement.RenewJobQueueEntry(JobType::HandlePending);
    end;

    local procedure HandleExistingCustomerLedgerEntries()
    var
        SIIDocUploadState: Record "SII Doc. Upload State";
        CustLedgerEntry: Record "Cust. Ledger Entry";
    begin
        CustLedgerEntry.SetRange("Posting Date", StartDate, EndDate);
        if CustLedgerEntry.FindSet then begin
            repeat
                if CustLedgerEntry."Document Type" in [CustLedgerEntry."Document Type"::"Credit Memo",
                                                       CustLedgerEntry."Document Type"::Invoice]
                then
                    SIIDocUploadState.CreateNewRequest(
                      CustLedgerEntry."Entry No.", SIIDocUploadState."Document Source"::"Customer Ledger",
                      CustLedgerEntry."Document Type", CustLedgerEntry."Document No.", CustLedgerEntry."External Document No.",
                      CustLedgerEntry."Posting Date");
            until CustLedgerEntry.Next = 0;
        end;
    end;

    local procedure HandleExistingVendorLedgerEntries()
    var
        SIIDocUploadState: Record "SII Doc. Upload State";
        VendorLedgerEntry: Record "Vendor Ledger Entry";
    begin
        VendorLedgerEntry.SetRange("Posting Date", StartDate, EndDate);
        if VendorLedgerEntry.FindSet then begin
            repeat
                if VendorLedgerEntry."Document Type" in [VendorLedgerEntry."Document Type"::"Credit Memo",
                                                         VendorLedgerEntry."Document Type"::Invoice]
                then
                    SIIDocUploadState.CreateNewRequest(
                      VendorLedgerEntry."Entry No.", SIIDocUploadState."Document Source"::"Vendor Ledger",
                      VendorLedgerEntry."Document Type", VendorLedgerEntry."Document No.", VendorLedgerEntry."External Document No.",
                      VendorLedgerEntry."Posting Date");
            until VendorLedgerEntry.Next = 0;
        end;
    end;

    [Scope('OnPrem')]
    procedure ScheduleInitialUpload()
    begin
        SIIJobManagement.RenewJobQueueEntry(JobType::InitialUpload);
    end;

    [Scope('OnPrem')]
    procedure GetInitialStartDate(): Date
    begin
        exit(DMY2Date(1, 1, 2017));
    end;

    [Scope('OnPrem')]
    procedure GetInitialEndDate(): Date
    begin
        exit(DMY2Date(30, 6, 2017));
    end;

    [Scope('OnPrem')]
    procedure DateWithinInitialUploadPeriod(InputDate: Date): Boolean
    begin
        exit(InputDate in [GetInitialStartDate .. GetInitialEndDate]);
    end;
}

