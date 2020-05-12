package test_Misc_Practice;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import pojo2.GetWeather;

public class TestsusinPOJO {
		
	@Test
	public void getweathervalidation()  {
		
		GetWeather gw=given().expect().defaultParser(Parser.JSON)
		.when()
			.get("http://restapi.demoqa.com/utilities/weather/city/Hyderabad").as(GetWeather.class);
		
		
		System.out.println(gw.getCity());
		//System.out.println(gw.getWindSpeed());
		
	}
	
}
