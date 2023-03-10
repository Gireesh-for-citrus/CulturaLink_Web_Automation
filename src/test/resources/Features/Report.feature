Feature:  Report

  Scenario: Report
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I click on Report
    Then I validate the titles
    Then I validate the links
    Then I validate Encounter quality report
    Then I validate Encounter Density report
    Then I validate Usage_By_Language
    Then I validate Device_Usage
    Then I validate Usage_By_Modality
    Then I validate Usage_By_Origin
    Then I validate All_Session Report
    Then I validate Interpreter Pay