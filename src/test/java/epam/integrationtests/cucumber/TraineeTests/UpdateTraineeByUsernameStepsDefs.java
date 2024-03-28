package epam.integrationtests.cucumber.TraineeTests;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import epam.integrationtests.cucumber.dtos.response.TraineeUpdateResponse;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UpdateTraineeByUsernameStepsDefs extends SpringIntegrationTest {

    private String username;
    private String accessToken;
    private JsonObject request;
    private ResponseEntity<TraineeUpdateResponse> response;


    @Given("a trainee with username exists in the system")
    public void givenTraineeExists() {
        this.username = DataTracker.get("username_trainee");
    }

    @And("trainee has proper authorization")
    public void traineeHasProperAuthorization() {
        this.accessToken = "Bearer " + DataTracker.get("accessToken_trainee");
    }

    @And("a valid trainee update request is provided")
    public void givenValidUpdateRequest() {
        this.request = new JsonObject();

        request.add("firstname", "Marco");
        request.add("lastname", "Polo");
        request.add("dateOfBirth", "1997-02-04");
        request.add("address", JsonObject.NULL);
        request.add("active", true);
    }

    @When("the client sends a PUT request to {string} with the update request")
    public void whenClientSendsPutRequest(String endpoint) {
        String url = endpoint + username;
        response = executeCall(url, HttpMethod.PUT, TraineeUpdateResponse.class, accessToken, request);
    }

    @Then("the returned response status code is {int}")
    public void thenReturnedResponseStatusCodeIs(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @And("the response body should contain the updated trainee information")
    public void thenResponseBodyContainsUpdatedTraineeInfo() {
        assertNotNull(response.getBody());
        assertEquals("Marco", response.getBody().getFirstname());
        assertEquals("Polo", response.getBody().getLastname());
    }

}
