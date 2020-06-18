package com.forecast.weather;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Unit test for simple App.
 */

@RunWith( SpringRunner.class )
@SpringBootTest
public class AppTest{
	
	@Autowired
	WeatherService service;
	
	@Test
    public void nullCordinates()
    {
    	try {
    		String args1=null;
    		String args2="-97.0892";
    		JSONArray arr=service.getWeatherResponse(args1, args2);
			assertNull(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	@Test
    public void validResponse()
    {
    	try {
    		String args1="45.7456";
    		String args2="-97.0892";
    		JSONArray arr=service.getWeatherResponse(args1, args2);
    		JSONObject response=service.formatResponse((JSONObject)arr.get(0));
			assertTrue(response.get("detailedForecast").toString().length()>0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	@Test
    public void testAPI()
    {
    	try {
    		JSONObject points=service.getResponseFromAPI("https://api.weather.gov/points/45.7456,-97.0892");
			assertNotNull(points);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}