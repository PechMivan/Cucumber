package epam.integrationtests.cucumber.TrainerTests.UpdateTrainerByUsername;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import epam.integrationtests.cucumber.dtos.response.TraineeUpdateResponse;
import epam.integrationtests.cucumber.dtos.response.TrainerUpdateResponse;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UpdateTrainerStepsDefs extends SpringIntegrationTest {

    private String username;
    private String accessToken;
    private JsonObject request;
    private ResponseEntity<TrainerUpdateResponse> response;

    @Given("a trainer with existing username is in the system")
    public void existingTrainerInSystem() {
        this.username = DataTracker.get("username_trainer");
    }

    @Given("has valid access token")
    public void validAccessToken() {
        this.accessToken = "Bearer " + DataTracker.get("accessToken_trainer");
    }

    @Given("a valid trainer update request is provided")
    public void validTrainerUpdateRequest() {
        this.request = new JsonObject();
        request.add("firstname", "Dougo");
        request.add("lastname", "Lionheart");
        request.add("specialization", "STRENGTH");
        request.add("active", false);
    }

    @When("the trainer sends a PUT request to {string} with the update request")
    public void sendPutRequest(String endpoint) {
        String url = endpoint + username;
        response = executeCall(url, HttpMethod.PUT, TrainerUpdateResponse.class, accessToken, request);
    }

    @Then("the returned response status code is going to be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response body should contain the updated trainer information")
    public void verifyResponseBody() {
        assertNotNull(response.getBody());
        assertEquals("Dougo", response.getBody().getFirstname());
        assertEquals("Lionheart", response.getBody().getLastname());
    }
}

