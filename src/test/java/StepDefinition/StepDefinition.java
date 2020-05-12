package StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacepojo;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data=new TestDataBuild();
	static String place_id;

	@Given("Add Place payload {string} {string} {string}")
	public void add_Place_payload(String language, String address, String name) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
					
		res=given().spec(requestSpecifcation()).body(data.addPlacePayLoad(language,address,name));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		resspec=new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		if (method.equalsIgnoreCase("POST"))
		response=res.when()
				.post(resourceAPI.getResource());
		
		else if (method.equalsIgnoreCase("GET"))
					response=res.when()
					.get(resourceAPI.getResource());
		
	    }

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions

	   
	   assertEquals(getJSONPath(response,keyvalue),expectedvalue);
	  
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getJSONPath(response,"place_id");
		res=given().spec(requestSpecifcation()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource,"GET");
		String name=getJSONPath(response,"name");
		 assertEquals(name,expectedname);
		
	}
	@Given("deleteplace Payload")
	public void deleteplace_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(requestSpecifcation()).body(data.DeletePlace(place_id));
	   
	}

}


