package epam.integrationtests.cucumber.TraineeTests;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class DeleteTraineeByUsernameStepsDefs extends SpringIntegrationTest {

    private String username;
    private String accessToken;

    private ResponseEntity<Void> response;

    @Given("a client with existing username")
    public void aClientWithExistingUsername(){
        this.username = DataTracker.get("username_trainee");
        this.accessToken = "Bearer " + DataTracker.get("accessToken_trainee");
    }

    @When("the client sends a DELETE request to {string}")
    public void theClientSendsADeleteRequestTo(String endpoint){
        String url = endpoint + username;
        response = executeCall(url, HttpMethod.DELETE, Void.class, accessToken, null);
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode){
        int actualStatusCode = response.getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }
}
