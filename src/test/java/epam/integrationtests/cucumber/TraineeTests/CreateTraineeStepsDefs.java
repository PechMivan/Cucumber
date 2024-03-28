package epam.integrationtests.cucumber.TraineeTests;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import epam.integrationtests.cucumber.dtos.CredentialsAndAccessToken;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateTraineeStepsDefs extends SpringIntegrationTest {

    private JsonObject request;

    private ResponseEntity<CredentialsAndAccessToken> response;

    @Given("a client valid registration request")
    public void aClientValidRegistrationRequest() {
        this.request = new JsonObject();
        request.add("firstname", "John");
        request.add("lastname", "Doe");
        request.add("dateOfBirth", "1998-10-31");
        request.add("address", "Main Street 20");
    }

    @When("client sends POST to {string} with the registration request")
    public void clientSendsPOSTToWithTheRegistrationRequest(String url) {
        response = executeCall(url, HttpMethod.POST, CredentialsAndAccessToken.class, null, request);
    }

    @Then("the client receives a a response with status code {int}")
    public void theClientReceivesAAResponseWithStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @And("the response body contains the credentials and access token.")
    public void theResponseBodyShouldContainTheCredentialsAndAccessToken() {
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getCredentials().getUsername());
        assertNotNull(response.getBody().getCredentials().getPassword());
        assertNotNull(response.getBody().getAccessToken());
        DataTracker.save("username_trainee", response.getBody().getCredentials().getUsername());
        DataTracker.save("password_trainee", response.getBody().getCredentials().getPassword());
        DataTracker.save("accessToken_trainee", response.getBody().getAccessToken());
    }

}
