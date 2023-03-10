Feature:  Request Appointment

  Scenario: Request  Appointment
    Given I Load the URL "Culturalink_Web"
    Then I Login to dashboard with valid credentials
    Then I click on Request Appointment
    Then  I validate headers
    Then I validate CustomerInfo
    Then I validate AppointmentInfo
    Then I validate PatientInfo
    Then I validate InputFields