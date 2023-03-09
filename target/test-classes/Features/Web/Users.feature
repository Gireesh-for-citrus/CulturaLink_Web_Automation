Feature:  Users

  Scenario: Users listing page
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    And I navigate to Users page and verify that the required page is loaded
    Then I verify the header sort functionality of users table
    Then I verify the visibility of search field and add new user button
    And I validate the functionality of search field with valid and invalid inputs
    Then I verify the add new user button redirects to new user creation page
    And I search one user and verify link is redirected to user profile page
    Then I verify the functionality of grid control in users page