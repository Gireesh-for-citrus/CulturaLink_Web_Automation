Feature:  Add new Location

  Scenario: Add new Client
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I go to Clients page and verify that the required page is loaded
    And I navigate to Add New Location page
    And I verify the client page container title displayed is correct
    Then I verify the wizard header tabs are displayed and also verify the Basic Info tab is active state
    And I check whether the required content headers are visible in the Basic Info tab as expected
    Then I verify each input text fields are displayed with proper labels and place holder values
    When I click on Add another button I verify the new classification field is added to the page
    Then I check the Remove button on the classification section removes the input fields
    Then I input some values to the input fields and verify whether they are accepting the values in these fields
    And I verify the input fields Name, Cart PIN, Classification Name are mandatory fields
    Then I verified the check box labels are displayed in Location Information and Classification Name sections
    And I enter the required values to the input fields and click on Next button and I verify it switched to Contact Info tab
    And I verify the required titles in Contact Info tab is displayed
    And I verify the input fields in Contact Info tab are working as expected
    Then I verify the Back and Next button functionality in Contact Info tab
    Then I verify the input fields and its functionality in Business Info tab
    And I verify the Back and Next button functionality in Business Address tab
    Then I verify the functionalities of check box, input fields, back button and Next button functionalities in Mailing address tab
    And I verify the checkboxes and list of permissions in Location Roles tab
    And I verify the elements and fields in Smart Routing Tab are visible and their behavior
    When I click on Smart Routing checkbox I verify whether the input field is accepting the values, also I verify the functionalities of Back and Next button
    Then I move to Billing Profile tab and I verify the table values displayed
    And I click on edit and verify the Billing Profile popup and the input fields and their behaviors
    Then I verify the edit billing profile functionality with valid and invalid parameters
    And I go to Billing Rates tab and verify the visibility of elements in it
    Then I click on Add Rate button and verify the elements and fields in Add Bill Rate popup
    Then I verify the Add Bill rate functionality with valid parameters
    And I verify the Back and Next button functionality in Billing Rate tab
    Then I go to Carts tab and verify all elements are properly visible
    And I click on Add new cart button and verify all elements and its properties
    Then I verify the add, edit and delete cart functionalities
    And Finally I verified the Add new client submit button functionalities