package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws IOException {
		StepDefinition sd=new StepDefinition();
		sd.add_Place_payload("English","AA House", "Suchi");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_Id_created_maps_to_using("Suchi", "GetPlaceAPI");
	}

}
