# Documentation

---
## Summary:

Many modern systems use APIs to interact with a source database or application and port data into a frontend application, which powers business process needs to meet the demands of clients. API testing is an important aspect of the overall testing suite.

This template will introduce you to API testing with Java, Cucumber and Maven. 
It will also teach you how to write a suite of tests in Java for an API.
In my example I have used the open source hosted REST populated API called 'Reqres'.


---
## Outcomes:

- Understand the structure of a Java Maven project 
- Understand how to test APIs with Http requests & responses
- Understand how to format and parse JSON
- Understand how to write API tests in Java and Cucumber

---
## Pre-requisites

Knowledge of basic Java programming
Knowledge of APIs and Http methods  _(add quick summary to slides to give REST,endpoints etc??)_

---
## Support:

If you have any questions about the topics in this pathway or need a helping hand with a concept or completing the validation task, reach out to:

_*Playground email*_

---
## Framework Setup

To start creating tests in this project we'll be using Vscode as editor, Java, Maven and Cucumber.

To begin we'll need to make sure Java and Maven are both installed.

For Java on a mac the quickest way to do it is via Homebrew, so you can just use the command:

    brew install java

Then run the command:

    java --version

And you should get the response showing what version you have installed

For Maven on a mac the quickest way to do it is via Homebrew, so you can just use the command:

    brew install maven

Then run the command:

    mvn -version
And you should get the response showing what JDK version you have installed
e.g.

```bash
Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63)
Maven home: /usr/local/Cellar/maven/3.8.6/libexec
Java version: 19.0.1 
```

## Project Setup

Clone this project
_add instructions?_

Then we'll want to add Cucumber and Junit dependencies via the pom.xml file. 

To do this go onto the [Maven repository](https://mvnrepository.com/)
and find the cucumber plugins required:

```bash
    cucumber-java
    cucumber-junit
```
Then you can copy and add the XML for the dependencies to your pom.xml file:

```xml
<dependencies>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.9.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.9.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Playground Start

Once the pom.xml has the new dependencies you'll then need to add the appropriate VsCode 'Cucumber for Java' plugin.

### Example Project

You can start adding feature files via a resources folder inside the ```src -> test -> java``` folder.

_Explain what the feature file is e.g. the Gherkin 'driver'_

And we'll need to create the steps within a folder  ```src -> test -> java -> stepdefs```.

_Explain this is the test steps called by the feature?_

After that, we'll need to create a ``` testRunner.java ``` file 
in src/test 

which will allow us to run the tests from a terminal:

```java
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
features = {"src/test/resource"},
tags = {},
plugin ="json:target/jsonReports/cucumber-report.json"

)

public class TestRunner {}

```

Once done you'll have a basic template which you can start filling with tests. 

---
## Adding in HTTP requests:

To start creating Http requests and parsing json responses we'll want to start with a GET request. 
We will use a generic API that conforms to REST principles and accepts a content type of JSON.

https://reqres.in/  already has data and gives free access to
 GET, POST, PUT and DELETE requests.


The below Http requests use Java's internal library to access modules:

    import java.net.http.HttpRequest;

_* SAY something about abstraction and builder *_

The request examples follow the same basic pattern but with slight variations. 

We'll be using HttpRequest for GET, POST, PUT and DELETE requests.

### Get Request:

```gherkin
Feature file:

Given I create a get request
When I run a get request
Then the status returns 200
And I print the response array
```
Step definitions:

For the Get request you'll be retrieving data
  - To do this you'll want to set up the request by specifying a couple variables
    - First, the Base Url, for example the github url is https://api.github.com/ but we are using reqres"https://reqres.in/api/"
    - Second, the kind of request, in this example we'll be using Get
    - Third, the header, in this case we'll set the header as User Agent identifier with a value of JavaBot
    - Finally, we can set it to build with the .build(); command, see the below example:

```java
    HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users?page=2"))
        .GET()
        .setHeader("User-Agent", "Java 11 Http bot")
        .build();
```

Then to arrange the request by sending it to API endpoint, you can take the get variable you created in the setup section earlier.
From that you can add the httpClient.send command and append with the response handler

    HttpResponse<String> response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());

Using the above line you can then get and interpret the response, for example:

    response.statusCode(); or String.valueOf(response.body());

Can be used to get the response attributes, but we'll go into more detail in the parsing responses section.

Finally, you can assert and print the results.

    assertTrue(response.statusCode(), 200) or System.out.println(String.valueOf(response.body()));

### Post Request:

```gherkin
Feature file:

Given I create a post request with table
When I run a post request
Then the status returns 201
```

Step definitions:
For the Post request you'll be creating data
- To do this you'll want to set up the request by specifying a couple variables
    - First, the Base Url, "https://reqres.in/api/"
    - Second, the kind of request, in this example we'll be using Post
    - This will also have the body of the request, in the form of a string that can be parsed to json
    - Third, the header, in this case we'll set the header as User Agent with a value of JavaBot
    - Finally, we can set it to build with the .build(); command, see the below example:

```java
    post = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users"))
        .POST(HttpRequest.BodyPublishers.ofString("{\n" +
            "    \"name\": \"" + morpheus + "\",\n" +
            "    \"job\": \"" + leader + "\"\n" +
            "}"))
        .setHeader("User-Agent", "Java 11 Http bot")
        .build();
```

Then arrange the request by sending it, you can take the post variable you created in the setup section earlier.

    response = httpClient.send(post, HttpResponse.BodyHandlers.ofString());

Using the above line you can then get and interpret the response, for example:

    response.statusCode(); or String.valueOf(response.body());

Can be used to get the response attributes, but we'll go into more detail in the parsing responses section.

Finally, you can assert and print the results.

    assertTrue(response.statusCode(), 201) or System.out.println(String.valueOf(response.body()));


### Put Request:

```gherkin
Feature file:

Given I create a put request with table
When I run a put request
Then the status returns 200
```

Step definitions:
For the Put request you'll be updating data
- To do this you'll want to set up the request by specifying a couple variables
    - First, the Base Url, "https://reqres.in/api/"
    - Second, the kind of request, in this example we'll be using Put
    - This will also have the body of the request, in the form of a string that can be parsed to json
    - Third, the header, in this case we'll set the header as User Agent with a value of JavaBot
    - Finally, we can set it to build with the .build(); command, see the below example:


    put = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users"))
        .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
            "    \"name\": \"" + itemsToLoad + "\",\n" +
            "    \"job\": \"" + itemsToLoad + "\"\n" +
            "}"))
        .setHeader("User-Agent", "Java 11 Http bot")
        .build();

Then arrange the request by sending it, you can take the put variable you created in the setup section earlier.

    response = httpClient.send(put, HttpResponse.BodyHandlers.ofString());

Using the above line you can then get and interpret the response, for example:

    response.statusCode(); or String.valueOf(response.body());

Can be used to get the response attributes, but we'll go into more detail in the parsing responses section.

Finally, you can assert and print the results.

    assertTrue(response.statusCode(), 200) or System.out.println(String.valueOf(response.body()));

### Delete Request:

```gherkin
Feature file:

Given I create a delete request
When I run a delete request
Then the status returns 204
```

Step definitions:
For the Delete request you'll deleting data
- To do this you'll want to set up the request by specifying a couple variables
    - First, the Base Url, "https://reqres.in/api/"
    - Second, the kind of request, in this example we'll be using Delete
    - Third, the header, in this case we'll set the header as User Agent with a value of JavaBot
    - Finally, we can set it to build with the .build(); command, see the below example:


    delete = HttpRequest.newBuilder(URI.create(BASE_URL_NAME + "users"))
            .DELETE()
            .setHeader("User-Agent", "Java 11 Http bot")
            .build();

Then arrange the request by sending it, you can take the get variable you created in the setup section earlier,

    response = httpClient.send(delete, HttpResponse.BodyHandlers.ofString());

Finally, you can assert and print the results.

    assertTrue(response.statusCode(), 204) or System.out.println(String.valueOf(response.body()));

### Parsing the Json responses: Top level response:

Feature file:

```gherkin
And I print the json response
```
Step definitions:

To do this you'll just need to grab the response body. In order to do this you'll need to make the json response handle-able by the code.
So we'll take the earlier response.body() and pass it to a jsonObject.

    bodyJson = response.body();
    JSONObject jsonObject = new JSONObject(bodyJson);

From there we can print out the full response:

    System.out.println("Full response: " + jsonObject);

Or we can get a specific value by getting the key value, such as status_code:

    String specificValue = jsonObject.getString("keyValue");

### Parsing the Json responses: Nested level response:


To parse the nested response it will involve a similar beginning, but we'll need to dive deeper into the array.

Feature file:

```gherkin
And I print the json array response
```

Step definitions:
To do this you'll just need to grab the response body. In order to do this you'll need to make the json response handle-able by the code.
So we'll take the earlier response.body() and pass it to a jsonObject.

    bodyJson = response.body();
    JSONObject jsonObject = new JSONObject(bodyJson);

From here you'll want to interact with the JsonArray to lay out the values.

    JSONArray jsonArray = jsonObject.getJSONArray(keyValue);

from there, you can use a for loop to cycle through the items in the array:

    for (int counter = 0; counter < jsonArray.length(); counter++) {
    JSONObject jsonArrayNestedContents = jsonArray.getJSONObject(counter);

And print out the contents of the array as you cycle through:

    System.out.println("Json array contents: " + jsonArrayNestedContents);

Including grabbing specific values:

    session = jsonArrayNestedContents.getString("value");

### Asserting the responses:

This is straightforward, you'll want to take a part of the array you're interested in and compare it to your expected value.
For example, you can take the value per_page and make sure it's value is correct, in this case "6".

    And the "per_page" from the response is "6"

This can be done in a way that's similar to unit testing, where you compare two values:

    assertEquals(getValueFor(jsonObject, keyValue).toString(), expectedValue);

---
## Refactoring the HTTP requests

Updating the HTTP requests to remove repeating steps. At this point we will have a couple repeating steps, and it's important to refactor early and often. To fix this we'll want to do a couple of things.

Create an utilities folder and in there create a Base_url file and Common_methods file. This will allow us to write a method once and call it when we need it, eliminating repetitions. You can see the examples of these files in this project.

First the Base_Url where we can declare our HttpRequests and HttpResponses, as well as the Base_url itself and initialising the get, post, etc. request types.

Second, the common methods can be used to write in commonly used methods, such as get Json response or Get Json nested response

Finally, the Cucumber files can be used to eliminate repetition, by adding the string variable we can use one method to create all the requests:

    Given I create a "<String>" request

We can replace <String> with get, delete, post or put to have one method (in the code example i've used two for the cucumber tables).
Then in the step definitions we can write an if or switch statement (depending on preference) to choose our method.

    if(requestType.equalsIgnoreCase("GET")) { write get code }
    else if(requestType.equalsIgnoreCase("POST")) { write post code }

This way we avoid repartition in the codebase.

---
## Setting up cucumber tables

In your feature files there are two main ways of interpreting datatables, the first is through a list, the second is through a map. We'll be using a map but there is an example of a list in this project that we'll briefly cover.

```gherkin
Feature file:

Given I create a "post" request with table
| Name   | Job   |
| <name> | <job> |
```

Step Definitions:

The list will be the quickest with initialising a list:

    List<List<String>> rows = table.asLists(String.class);

Then iterating through that with a for loop:

    for (List<String> columns : rows) { add items }

The map will be very similar, we'll be creating a map object:

    List<Map<String, String>> rows = table.asMaps(String.class, String.class);

Then iterating through that with

    for (Map<String, String> columns : rows) { addItems(columns.get("argumentOne"), columns.get("argumentTwo"))) }

---
## Running the project

From terminal 
``` mvn test```

---
_Need to explain how the cucumber datatables help to run it  a little bit more!!! e.g. to allow multiple runs varying the data table inputs_