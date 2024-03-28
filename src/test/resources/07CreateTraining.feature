Feature: Create Training

  Scenario: Successfully create a training.
    Given a valid training creation request is provided
    And client has proper authorization token
    When the client sends a POST request to "/gym/trainings" with the creation request
    Then the returned response status code is equal to 200