Feature:  Clients page

  Scenario: Clients page
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I go to Clients page and verify that the required page is loaded
    And I click on Tree View button and check whether it changes the client list behaviour from Table view to tree view
    Then I click on Client View button and check whether it changes the client list behavior from Tree View to Table View
    And I verify the search field functionality with an invalid search value
    Then I check the Add New Location button redirected to add new location page
    When I click on page numbers and first and last icons, I verify whether it is displaying the proper list
    Then I click on one client and check whether it redirected to proper URL
    Then I click on Client list header and verify the sort client list functionality
    Then I choose the Tree View and verify client page redirection

