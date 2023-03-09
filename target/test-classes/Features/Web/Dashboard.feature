Feature:  Dashboard Page

  Scenario: Filter Results
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I check the Filter Results button is displayed
    And I add one client to the filter and check whether the dashboard results displaying for the selected client
    Then I check whether the filter functionality is working fine with two filter values
    Then I verify the filter is applied for other pages
    And I clear the applied filters from Dashboard
    Then I check the Filter by date functionality is displayed with required fields
    Then I select Portuguese language from the list and verify all charts are displayed the selected language
    And I add one user to filter and check whether the user is displayed on the filter section
    Then I Clear the user filter
    And I verify the start and end date in the date picker, the end date should be current date and start date should be previous month's date
    Then I click on Today button on the Start Date picker and check whether the current Date updated on Start Date calendar
    Then I filter the dashboard using previous month
    Then I filter the dashboard with next month and verify that it is not displaying the charts
    Then I verify the Today Activity section displays the header and the inner elements
    And I verify the Performance Statistics section displays the header and inner texts
