package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacepojo;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlacepojo addPlacePayLoad(String language, String address, String name)
	{
		AddPlacepojo p=new AddPlacepojo();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
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
		
		return p;
	}
	public String DeletePlace (String placeId)
	{
		return "{\r\n   \"place_id\":\""+placeId+"\"\r\n}";
		
	}

}
