Feature: Delete Trainee by username.

Scenario: Successfully delete a trainee by username
  Given a client with existing username
  When the client sends a DELETE request to "/gym/trainees/"
  Then the response status code is 200