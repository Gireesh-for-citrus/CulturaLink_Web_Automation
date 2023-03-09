Feature:  Login functionality of CulturaLink Web app

  Scenario: Login page validation

    Given I Load the URL "Culturalink_Web"
    And I check whether the background image and page title are displayed
    And I verify the forgot password link redirects to One Login app
    Then I check whether the validation is correct for valid and invalid login credentials
    Then I Login to dashboard with valid credentials
    And I check whether the dashboard page is loaded



