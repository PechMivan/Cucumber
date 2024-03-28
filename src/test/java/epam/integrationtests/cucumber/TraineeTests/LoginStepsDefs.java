//package epam.integrationtests.cucumber.TraineeTests;
//
//import epam.integrationtests.cucumber.DataTracker;
//import epam.integrationtests.cucumber.SpringIntegrationTest;
//import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class LoginStepsDefs extends SpringIntegrationTest {
//
//    private JsonObject request;
//    private String username;
//    private String password;
//    private ResponseEntity<String> response;
//
//    @Before
//    public void setUpTestData() {
//        this.username = DataTracker.get("username");
//        this.password = DataTracker.get("password");
//    }
//
//    @Given("a valid user credentials")
//    public void givenValidUserCredentials() {
//        this.request = new JsonObject();
//        request.add("username", username);
//        request.add("password", password);
//    }
//
//    @When("the client sends a POST request to {string} with credentials")
//    public void whenClientSendsPostRequest(String url) {
//        response = executeCall(url, HttpMethod.POST, String.class, null, request);
//    }
//
//    @Then("the client receives a response with status code {int}")
//    public void thenClientReceivesResponseWithStatusCode(int expectedStatusCode) {
//        int actualStatusCode = response.getStatusCode().value();
//        assertEquals(expectedStatusCode, actualStatusCode);
//    }
//
//    @And("the response body contains a JWT token")
//    public void thenResponseBodyContainsJwtToken() {
//        String responseBody = response.getBody();
//        assertNotNull(responseBody);
//        assertNotNull(responseBody);
//        DataTracker.save("accessToken", responseBody);
//    }
//}
