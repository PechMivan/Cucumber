Feature: User Authentication

  Scenario: Authenticate user with valid credentials
    Given a valid user credentials
    When the client sends a POST request to "/gym/user/login" with credentials
    Then the client receives a response with status code 200
    And the response body contains a JWT token