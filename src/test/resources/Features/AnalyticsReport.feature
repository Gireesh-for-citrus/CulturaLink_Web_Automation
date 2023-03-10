Feature:  Analytics Report

  Scenario: Analytics Report
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I click on Analytics Report
    Then I validate the header
    And I validate Tool tip
