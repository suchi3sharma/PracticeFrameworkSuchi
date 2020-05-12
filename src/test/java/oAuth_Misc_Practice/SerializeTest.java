package oAuth_Misc_Practice;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlacepojo;
import pojo.Location;

public class SerializeTest {
	@Test
	public void serializetest() {
	RestAssured.baseURI="https://rahulshettyacademy.com";
	
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
	
	Response res=given().queryParam("key", "qaclick123").body(p)
	.when()
		.post("/maps/api/place/add/json")
		.then()
		.statusCode(200).extract().response();
	
	String resString=res.asString();
	System.out.println(resString);
	
	
}

}