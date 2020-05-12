package oAuth_Misc_Practice;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.API;
import pojo.GetCourse_parent;

public class OAuth {
	
	@Test
	public void oauth() {
		// to fetch code
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\suchisharma\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=abcgdjdie");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("suchi3sharma@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("queenking1");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = driver.getCurrentUrl(); */
		
		String url="https://rahulshettyacademy.com/getCourse.php?state=abcgdjdie&code=4%2FzQEujfTOXJ3pOBtgtQEQQq0EOfo7acLtTRxU6ziVPI1rm1k4S-PxX8QdZPn5OE4EQ6jXN-8aHoTcGNKnCVRK8Ug&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope=")[0];
		System.out.println(code);
		
		// to fetch access token
		String accesstokenreponse=given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js=new JsonPath(accesstokenreponse);
		String access_token=js.getString("access_token");
		System.out.println(access_token);
		
		
		GetCourse_parent gc=given().queryParam("access_token",access_token).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse_parent.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getAPI().get(1).getCourseTitle());
		//List<API> apiCourses=gc.getCourses().getAPI();
	}

}
