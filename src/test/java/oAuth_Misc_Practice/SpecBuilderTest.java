package oAuth_Misc_Practice;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacepojo;
import pojo.Location;

public class SpecBuilderTest {
	@Test
	public void serializetest() {
		
	AddPlacepojo p=new AddPlacepojo();
	p.setAccuracy(50);
	p.setAddress("29, side layout, cohen 09");
	p.setLanguage("English");
	p.setName("Frontline house");
	p.setPhone_number("\"(+91) 983 893 3937");
	p.setWebsite("http://google.com");
	List<String> myList= new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shoe");
	p.setTypes(myList);
	
	Location l=new Location();
	l.setLat(-38.383494);
	l.setLng(33.427365);

	p.setLocation(l);
	
	RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
	.setContentType(ContentType.JSON).build();
	
	ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	RequestSpecification res=given().spec(req).body(p);
	
	Response response=res.when()
		.post("/maps/api/place/add/json")
		.then()
		.spec(resspec).extract().response();
	
	String resString=response.asString();
	System.out.println(resString);
	
	
}

}