Feature: Validating Place APIs
@AddPlaceAPI
Scenario Outline: Verify if Place is added
	Given Add Place payload "<language>" "<address>" "<name>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
	Examples:
	|language|address				   |name        |
	|English |29, side layout, cohen 09|Suchi House |
#	|Spanish |29, side layout, cohen 10|My House    |
@DeletePlaceAPI
Scenario: Verify Delete place functionality is working

	Given deleteplace Payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"