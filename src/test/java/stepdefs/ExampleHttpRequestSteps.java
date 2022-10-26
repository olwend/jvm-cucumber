package stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static utils.Base_Url.*;
import static utils.Common_Methods.*;

public class ExampleHttpRequestSteps {

    @Given("^I create a get request$")
    public void i_create_a_get_request() {
        get = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users?page=2"))
                .GET()
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();
    }

    @When("^I run a get request$")
    public void iRunAGetRequest() throws Throwable {
        response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
        actualCode = response.statusCode();
        System.out.println("Request: " + response);
    }

    @Given("^I create a post request$")
    public void i_create_a_post_request(DataTable dataTable) {
        List<Map<String, String>> itemsToLoad = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < itemsToLoad.size(); i++) {
            post = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users"))
                    .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                            "    \"name\": \"" + itemsToLoad + "\",\n" +
                            "    \"job\": \"" + itemsToLoad + "\"\n" +
                            "}"))
                    .setHeader("User-Agent", "Java 11 Http bot")
                    .build();
            System.out.println(post);
        }
    }

    @When("^I run a post request$")
    public void iRunAPostRequest() throws Throwable {
        response = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
        System.out.println("Here: " + response);
        actualCode = response.statusCode();
        bodyJson = String.valueOf(response.body());
        System.out.println("StatusCode: " + actualCode);
    }

    @Given("^I create a put request$")
    public void i_create_a_put_request() {
        put = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users/2"))
                .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}"))
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();
    }

    @When("^I run a put request$")
    public void iRunAPutRequest() throws Throwable {
        response = httpClient.send(put, HttpResponse.BodyHandlers.ofString());
        actualCode = response.statusCode();
        System.out.println("StatusCode: " + actualCode);
    }

    @Given("^I create a patch request$")
    public void i_create_a_patch_request() {
        patch = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users/2"))
                .method("PATCH", HttpRequest.BodyPublishers.ofString("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}"))
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();
    }

    @When("^I run a patch request$")
    public void iRunAPatchRequest() throws Throwable {
        response = httpClient.send(patch, HttpResponse.BodyHandlers.ofString());
        actualCode = response.statusCode();
        System.out.println("StatusCode: " + actualCode);
    }

    @Given("^I create a delete request$")
    public void i_create_a_delete_request() {
        delete = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users/2"))
                .DELETE()
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();
    }

    @When("^I run a delete request$")
    public void iRunADeleteRequest() throws Throwable {
        response = httpClient.send(delete, HttpResponse.BodyHandlers.ofString());
        actualCode = response.statusCode();
        System.out.println("StatusCode: " + actualCode);
    }

    @Given("^I create a \"([^\"]*)\" request with table$")
    public void i_create_a_request_with_table(String requestType, DataTable dataTable) {
        if(requestType.equalsIgnoreCase("POST")) {
            itemsToLoad = dataTable.asMaps(String.class, String.class);
            for (int i = 0; i < itemsToLoad.size(); i++) {
                post = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users"))
                        .POST(HttpRequest.BodyPublishers.ofString("{\n" +
                                "    \"name\": \"" + itemsToLoad + "\",\n" +
                                "    \"job\": \"" + itemsToLoad + "\"\n" +
                                "}"))
                        .setHeader("User-Agent", "Java 11 Http bot")
                        .build();
            }
        } else if (requestType.equalsIgnoreCase("PUT")) {
            itemsToLoad = dataTable.asMaps(String.class, String.class);
            for (int i = 0; i < itemsToLoad.size(); i++) {
                put = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users/2"))
                        .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                                "    \"name\": \"" + itemsToLoad + "\",\n" +
                                "    \"job\": \"" + itemsToLoad + "\"\n" +
                                "}"))
                        .setHeader("User-Agent", "Java 11 Http bot")
                        .build();
            }
        }
    }

    @Given("^I create a \"([^\"]*)\" request$")
    public void i_create_a_request(String requestType) throws IOException, InterruptedException {
        if(requestType.equalsIgnoreCase("GET")) {
            get = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users?page=2"))
                    .GET()
                    .setHeader("User-Agent", "Java 11 Http bot")
                    .build();
        } else if(requestType.equalsIgnoreCase("DELETE")) {
            delete = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users/2"))
                    .DELETE()
                    .setHeader("User-Agent", "Java 11 Http bot")
                    .build();
        }
    }
    @When("^I run a \"([^\"]*)\" request$")
    public void i_run_a_request(String requestType) throws IOException, InterruptedException {
        if(requestType.equalsIgnoreCase("POST")) {
            response = httpClient.send(post, HttpResponse.BodyHandlers.ofString());
            System.out.println("Here: " + response);
            actualCode = response.statusCode();
            bodyJson = String.valueOf(response.body());
            System.out.println("StatusCode: " + actualCode);
        } else if(requestType.equalsIgnoreCase("PUT")) {
            response = httpClient.send(put, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("StatusCode: " + actualCode);
        } else if(requestType.equalsIgnoreCase("GET")) {
            response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("Request: " + response);
        } else if(requestType.equalsIgnoreCase("DELETE")) {
            response = httpClient.send(delete, HttpResponse.BodyHandlers.ofString());
            actualCode = response.statusCode();
            System.out.println("StatusCode: " + actualCode);
        }
    }

    @Then("^the status returns \"([^\"]*)\"$")
    public void it_returns_a_status(int number) {
        assertEquals(number, actualCode);
    }

    @Then("^the \"([^\"]*)\" from the response is \"([^\"]*)\"$")
    public void i_print_the_int_from_response(String keyValue, String expectedValue) {
        bodyJson = response.body();
        JSONObject jsonObject = new JSONObject(bodyJson);

        System.out.println("Full response: " + jsonObject);

        assertEquals(getValueFor(jsonObject, keyValue).toString(), expectedValue);

        int topLevel = jsonObject.getInt(keyValue);
        System.out.println("Top level object which is " + keyValue + ": " + topLevel);

    }

    @Then("^I print from the response using key \"([^\"]*)\"$")
    public void i_print_the_response(String keyValue) {
        bodyJson = response.body();
        JSONObject jsonObject = new JSONObject(bodyJson);

        printJsonResponse(jsonObject, keyValue);
    }

    @Then("^I print the response array$")
    public void i_print_the_response_array() {
        bodyJson = response.body();
        JSONObject jsonObject = new JSONObject(bodyJson);

        printResponseArray(jsonObject);
    }
}


