Feature:  Add new user

  Scenario: Add new user page
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    And I navigate to Users page and verify that the required page is loaded
    Then I navigate to Add new user page
#    Then I verify whether the required titles are displayed for CulturaLink User
#    And I verify the expand and collapse functionalities of input field titles in CulturaLink user
#    Then I verify functionalities and properties of the checkbox and input fields under User Association and User Information
#    And I verify the properties of input field and add valid inputs to all fields in Contact Info section
#    Then I verify the functionalities and properties of input fields in Home address tab
#    Then I go to mailing address section and verified the properties and functionalities of input fields and checkbox
    And I verify the Role selection in Roles section