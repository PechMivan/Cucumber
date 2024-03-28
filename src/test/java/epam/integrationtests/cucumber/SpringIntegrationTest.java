package epam.integrationtests.cucumber;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@CucumberContextConfiguration
public class SpringIntegrationTest {


    private final int port = 9090;
    private final TestRestTemplate testRestTemplate;

    protected SpringIntegrationTest(){
        this.testRestTemplate = new TestRestTemplate();
    }

    protected <T> ResponseEntity<T> executeCall(String endpoint, HttpMethod method, Class<T> responseType, String accessToken, JsonObject jsonObject) {
        HttpHeaders header = setHeader(accessToken);
        String body = null;
        if(jsonObject != null){
            body = jsonObject.toString();
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(body, header);

        String url = "http://localhost:" + port + endpoint;
        return testRestTemplate.exchange(
                url,
                method,
                requestEntity,
                responseType
        );
        
    }

    protected HttpHeaders setHeader(String headerValue){
        HttpHeaders headers = new HttpHeaders();
        if(headerValue != null){
            headers.set("Authorization", headerValue);
        }
        headers.set("Content-Type", "application/json");
        headers.set("Host", "localhost:" + port);
        return headers;
    }

}
