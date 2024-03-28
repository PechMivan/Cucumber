package epam.integrationtests.cucumber.TrainerTests;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import epam.integrationtests.cucumber.dtos.response.TraineeFindResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetTrainerByUsernameStepsDefs extends SpringIntegrationTest {

    private String username;
    private String accessToken;
    private ResponseEntity<TraineeFindResponse> response;

    @Given("a trainer with username exists in the system")
    public void givenTrainerExists() {
        this.username = DataTracker.get("username_trainer");
    }
    
    @And("has permission to get information")
    public void hasPermissionToGetInformation(){
        this.accessToken = "Bearer " + DataTracker.get("accessToken_trainer");
    }

    @When("the client sends a GET request to {string}")
    public void whenClientSendsGetRequest(String endpoint) {
        String url = endpoint + username;
        response = executeCall(url, HttpMethod.GET, TraineeFindResponse.class, accessToken, null);
    }

    @Then("the returned response status code will be {int}")
    public void thenReturnedResponseStatusCodeIs(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response body should contain the trainer information")
    public void thenResponseBodyContainsTrainerInfo() {
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getFirstname());
        assertNotNull(response.getBody().getLastname());
    }
}

