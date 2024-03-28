Feature: Create Trainer

  Scenario: Create a trainer
    Given a valid trainer registration request is provided
    When the client sends a POST request to "/gym/trainers" with the registration request
    Then returned response status code will be 200
    And the response body should contain the credentials and access token