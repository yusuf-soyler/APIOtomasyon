package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.TokenGenerator;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        String token = TokenGenerator.generate_token();

        RestAssured.requestSpecification = RestAssured
                .given()
                .header("api_key", token)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json");
    }
}