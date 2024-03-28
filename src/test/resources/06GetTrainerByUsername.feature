Feature: Get Trainer by username

  Scenario: Create trainer.
    Given a trainer with username exists in the system
    And  has permission to get information
    When the client sends a GET request to "/gym/trainers/"
    Then the returned response status code will be 200
    And the response body should contain the trainer information