package epam.integrationtests.cucumber.TrainerTests;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import epam.integrationtests.cucumber.dtos.CredentialsAndAccessToken;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateTrainerStepsDefs extends SpringIntegrationTest {

    private JsonObject request;
    private ResponseEntity<CredentialsAndAccessToken> response;

    @Given("a valid trainer registration request is provided")
    public void givenValidRegistrationRequest() {
        this.request = new JsonObject();
        request.add("firstname", "Douglas");
        request.add("lastname", "Lionheart");
        request.add("specialization", "HIIT");
    }

    @When("the client sends a POST request to {string} with the registration request")
    public void whenClientSendsPostRequest(String endpoint) {
        response = executeCall(endpoint, HttpMethod.POST, CredentialsAndAccessToken.class, null, request);
    }

    @Then("returned response status code will be {int}")
    public void thenReturnedResponseStatusCodeWillBe(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response body should contain the credentials and access token")
    public void thenResponseBodyContainsCredentialsAndAccessToken() {
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getCredentials());
        assertNotNull(response.getBody().getCredentials().getUsername());
        assertNotNull(response.getBody().getCredentials().getPassword());
        assertNotNull(response.getBody().getAccessToken());
        DataTracker.save("username_trainer", response.getBody().getCredentials().getUsername());
        DataTracker.save("password_trainer", response.getBody().getCredentials().getPassword());
        DataTracker.save("accessToken_trainer", response.getBody().getAccessToken());
    }
}

