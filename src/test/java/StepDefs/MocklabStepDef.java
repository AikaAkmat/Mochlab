package StepDefs;

import Pojo.UserInfo;
import Utils.ConfigReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import java.io.IOException;
import java.net.URISyntaxException;

public class MocklabStepDef {

    HttpClient httpClient;
    URIBuilder uri;
    HttpGet httpGet;
    HttpResponse response;
    ObjectMapper objectMapper = new ObjectMapper();
    UserInfo userInfo;

    @When("user sends GET request to mocklab.io")
    public void user_sends_GET_request_to_mocklab_io() throws IOException, URISyntaxException {
        httpClient = HttpClientBuilder.create().build();
        uri = new URIBuilder();
        uri.setScheme("http").setHost(ConfigReader.getProperty("baseUrl")).
                setPath(ConfigReader.getProperty("basePath"));
        httpGet = new HttpGet(uri.build());
        httpGet.setHeader("Accept", ConfigReader.getProperty("contentType"));
        response = httpClient.execute(httpGet);
    }
    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer statusCode) {
        System.out.println("Status code is "
                + response.getStatusLine().getStatusCode());
        Assert.assertEquals("Status code assertion failed" + statusCode,
                HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }
    @Then("content type should be {string}")
    public void content_type_should_be(String contentType) {
        System.out.println("Content type of response is  "
                + response.getEntity().getContentType().getValue());
        Assert.assertEquals(" Invalid content type header",
                contentType, response.getEntity().getContentType().getValue());
    }
    @Then("response time must be less than {string}")
    public void response_time_must_be_less_than(String time) {
    }
    @Then("user id should be greater than {int}")
    public void user_id_should_be_greater_than(Integer id) throws IOException {
        userInfo = objectMapper.readValue(response.getEntity().getContent(), UserInfo.class);
        System.out.println("Users id is " + userInfo.getId());
        Assert.assertTrue(userInfo.getId() > id);
    }
    @Then("user name can't be longer than {int} alpha characters")
    public void user_name_can_t_be_longer_than_alpha_characters(Integer nameLenght) {

        System.out.println("The lenght  of the  name is " + userInfo.getName().length());
        Assert.assertTrue(userInfo.getName().length() < nameLenght);
    }
    @Then("user lastName can't be longer than {int} alpha characters")
    public void user_lastName_can_t_be_longer_than_alpha_characters(Integer lastNameLeght) {

        System.out.println("The lenght of the lastName is " + userInfo.getLast().length());
        Assert.assertTrue(userInfo.getLast().length() < lastNameLeght);
    }
    @Then("user age must be integer and {int} > age < {int}")
    public void user_age_must_be_integer_and_age(Integer less, Integer more) throws IOException {

        System.out.println("User age is " + userInfo.getAge());
        Assert.assertTrue(userInfo.getAge() > less && userInfo.getAge() < more);
    }
    @Then("user gender must only be {string} or {string}")
    public void user_gender_must_only_be_or(String F, String M) throws IOException {
        System.out.println("The user's gender is " +  userInfo.getGender());
        Assert.assertTrue(userInfo.getGender().contains(M) || userInfo.getGender().contains(F));

    }
}

