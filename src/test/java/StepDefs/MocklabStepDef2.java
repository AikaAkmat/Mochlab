package StepDefs;


import Pojo.UserInfo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.given;

public class MocklabStepDef2 {

    private Response response;

    SoftAssert softAssert = new SoftAssert();

    @When("the user sends GET request")
    public void the_user_sends_GET_request() {
        response = given().accept(ContentType.JSON).when().get("http://v2eok.mocklab.io/customer/a");
    }

    @Then("the status code should  be {int}")
    public void the_status_code_should_be(Integer expectedStatusCode) {
        response.then().assertThat().statusCode(expectedStatusCode).log().all();
    }

    @Then("the user validates response body values")
    public void the_user_validates_response_body_values() {
        UserInfo bodyValues = response.getBody().as(UserInfo.class);
        System.out.println("The user Id is " + bodyValues.getId());
        softAssert.assertTrue(bodyValues.getId() > 0);
        System.out.println("The users  name lenght is  " + bodyValues.getName());
        softAssert.assertTrue(bodyValues.getName().length() < 10);
        System.out.println("The user lastName is " + bodyValues.getLast());
        softAssert.assertTrue(bodyValues.getLast().length() < 10);
        System.out.println("The users age is " + bodyValues.getAge());
        softAssert.assertTrue(bodyValues.getAge() < 120 || bodyValues.getAge() > 0);
        System.out.println("The users gender is  " + bodyValues.getGender());
        softAssert.assertTrue(bodyValues.getGender().equalsIgnoreCase("F") || bodyValues.getGender().equalsIgnoreCase("M"));
        softAssert.assertAll();
    }
}