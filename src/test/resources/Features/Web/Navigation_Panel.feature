Feature:  Navigation panel

  Scenario: Navigation panel
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I check the logo is displayed
    And I check whether the Expand or Collapse button is working fine
    And I check whether the Edit profile picture popup displayed when clicking on it
    And I verify the notification button showing the notification popup
    Then I verify the redirection URLs for other links on the Navigation panel
    And I verify the logout button is working fine
