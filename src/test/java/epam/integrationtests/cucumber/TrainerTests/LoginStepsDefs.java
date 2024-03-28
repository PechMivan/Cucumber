package epam.integrationtests.cucumber.TrainerTests;

import epam.integrationtests.cucumber.DataTracker;
import epam.integrationtests.cucumber.SpringIntegrationTest;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class LoginStepsDefs extends SpringIntegrationTest {

    private JsonObject request;
    private ResponseEntity<String> response;

    @Given("a valid user credentials")
    public void givenValidUserCredentials() {
        this.request = new JsonObject();
        request.add("username", DataTracker.get("username_trainer"));
        request.add("password", DataTracker.get("password_trainer"));
    }

    @When("the client sends a POST request to {string} with credentials")
    public void whenClientSendsPostRequest(String url) {
        response = executeCall(url, HttpMethod.POST, String.class, null, request);
    }

    @Then("the client receives a response with status code {int}")
    public void thenClientReceivesResponseWithStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode().value();
        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @And("the response body contains a JWT token")
    public void thenResponseBodyContainsJwtToken() {
        String responseBody = response.getBody();
        assertNotNull(responseBody);
        assertInstanceOf(String.class, responseBody);
    }
}
