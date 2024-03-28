Feature: Update Trainee.

  Scenario: Successfully update a trainee
    Given a trainee with username exists in the system
    And trainee has proper authorization
    And a valid trainee update request is provided
    When the client sends a PUT request to "/gym/trainees/" with the update request
    Then the returned response status code is 200
    And the response body should contain the updated trainee information