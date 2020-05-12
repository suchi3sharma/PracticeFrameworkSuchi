package Cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue= {"stepDefinition"},tags= {"@DeletePlaceAPI"})

//AddPlaceAPI
public class TestRunner {
	
}
