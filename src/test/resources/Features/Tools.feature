Feature:  Tools

  Scenario: Tools
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I click on Tools
    Then I validate the URL and Title
    Then I validate the OPI_Import
    Then I validate the OSI_Import
    Then I validate the demo-setup
    Then I validate the role_management
    Then I validate the Application Settings
    Then I validate Client Import
    Then I validate Client Strip
    Then I validate Public ID  add
    Then I validate Fix Billing Amount
    Then I validate Fix OSI billing Amount
    Then I validate Void_Transactions
    Then I validate Edit_Transactions
    Then I validate Fix_OPI_import
    Then I validate Dispute Transaction
    Then I validate Retract Import
    Then I validate Fix Dispute Transaction
    Then I validate  Import Fluency Names
    Then I validate Clear_Reports
