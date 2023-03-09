Feature:  Dashboard Graphs and Charts

  Scenario: Graphs and Charts
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I verify the required titles of each graphs and charts are displayed on the Dashboard
    And I check whether the redirection URL of the right arrow on each graphs is set to reports page
    Then I verify the elements in Highcharts Bar Series are displayed and verify the click functionality of legends values
      | Encounter Volume           |
      | Average_Client_Call_Rating |
      | Language_Utilization       |
      | Abandon_Rate               |
      | Language_Duration          |
      | Volume By Duration         |
      | Concurrent_Calls           |
      | Financial_Performance      |
      | Interpreter_Pay            |
      | Third Party Volume         |
      | Third Party Duration       |
