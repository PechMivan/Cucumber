Feature: Get trainee by username

  Scenario: Get trainee by existing username
    Given a trainee exists with the username
    When the client requests the trainee by username
    Then the response status code should be 200
    And the response body should contain the trainee details
