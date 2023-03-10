Feature:  Help

  Scenario: Help
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I click on help
    And I validate the link