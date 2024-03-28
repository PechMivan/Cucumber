package epam.integrationtests.cucumber.TrainingTests;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class CreateTrainingStepsDefs extends SpringIntegrationTest {

    private String accessToken;
    private JsonObject request;
    private ResponseEntity<Void> response;

    @Given("a valid training creation request is provided")
    public void validTrainingCreationRequest() {
        this.request = new JsonObject()
                .add("traineeUsername", "John.Doe")
                .add("trainerUsername", "Douglas.Lionheart")
                .add("name", "Super Fuerza")
                .add("date", "2026-10-30")
                .add("duration", 4);
    }

    @And("client has proper authorization token")
    public void clientHasProperAuthorizationToken() {
        this.accessToken = "Bearer " + DataTracker.get("accessToken_trainer");
    }

    @When("the client sends a POST request to {string} with the creation request")
    public void sendPostRequest(String endpoint) {
        response = executeCall(endpoint, HttpMethod.POST, Void.class, accessToken, request);
    }

    @Then("the returned response status code is equal to {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }
}

