package com.forecast.weather;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */	
    public void testApp()
    {
    	App app=new App();
    	try {
			JSONObject obj=app.getResponseFromAPI("https://api.weather.gov/points/39.7456,-97.0892");
			String forecast=String.valueOf(((JSONObject)obj.get("properties")).get("forecast"));
			JSONObject output=app.getResponseFromAPI(forecast);
			JSONArray finalResponse=(JSONArray) ((JSONObject)output.get("properties")).get("periods");
			assertEquals( 14, finalResponse.size() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
