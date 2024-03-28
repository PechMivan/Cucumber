Feature: Create Trainee.

  Scenario: Create Trainee for the GYM application.
    Given a client valid registration request
    When client sends POST to "/gym/trainees" with the registration request
    Then the client receives a a response with status code 201
    And the response body contains the credentials and access token.