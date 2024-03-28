package epam.integrationtests.cucumber.TraineeTests;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import epam.integrationtests.cucumber.dtos.response.TraineeFindResponse;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class GetTraineeByUsernameStepsDefs extends SpringIntegrationTest {
    private String username;
    private String accessToken;

    private ResponseEntity<TraineeFindResponse> response;

    @Before
    public void setUpTestData() {
        this.accessToken = "Bearer " + DataTracker.get("accessToken_trainee");
    }

    @Given("a trainee exists with the username")
    public void aTraineeWithUsername() {
        this.username = DataTracker.get("username_trainee");
    }

    @When("the client requests the trainee by username")
    public void theClientRequestsTheTraineeByUsername() {
        String endpoint = "/gym/trainees/" + username;
        response = executeCall(endpoint, HttpMethod.GET, TraineeFindResponse.class, accessToken, null);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
        assertTrue(true);
    }

    @And("the response body should contain the trainee details")
    public void theResponseBodyShouldContainTheTraineeDetails() {
        TraineeFindResponse details = response.getBody();
        assertNotNull(details);
    }
}