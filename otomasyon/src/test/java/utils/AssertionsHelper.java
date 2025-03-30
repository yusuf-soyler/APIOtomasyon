package utils;

import io.restassured.response.Response;
import org.testng.Assert;

public class AssertionsHelper {

    public static void assert_status_code(Response response, int expected_code) {
        Assert.assertEquals(response.getStatusCode(), expected_code);
    }

    public static void assert_field_value(Response response, String field, String expected_value) {
        Assert.assertEquals(response.jsonPath().getString(field), expected_value);
    }


}
