Feature: Update Trainer

  Scenario: Successfully update a trainer
    Given a trainer with existing username is in the system
    And has valid access token
    And a valid trainer update request is provided
    When the trainer sends a PUT request to "/gym/trainers/" with the update request
    Then the returned response status code is going to be 200
    And the response body should contain the updated trainer information
