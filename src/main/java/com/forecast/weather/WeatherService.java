package com.forecast.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
	
	private static final org.apache.logging.log4j.Logger log = 
		    org.apache.logging.log4j.LogManager.getLogger(WeatherService.class);
	
	public JSONArray getWeatherResponse(String args1,String args2) throws IOException, ParseException {
		if(args1 == null || args1.length()<1) {
			return null;
		}
		if(args2 == null || args2.length()<1) {
			return null;
		}
		JSONObject points=getResponseFromAPI("https://api.weather.gov/points/"+args1+","+args2);
		if(points==null) {
			log.error("Not a valid response from https://api.weather.gov/points/");
			return null;
		}
		String forecast=String.valueOf(((JSONObject)points.get("properties")).get("forecast"));

		JSONObject output=getResponseFromAPI(forecast);
		if(output==null) {
			log.error("Not a valid response from https://api.weather.gov/gridpoints/");
			return null;
		}
		JSONArray arr=(JSONArray)((JSONObject)output.get("properties")).get("periods");
		return arr;
	}
	
	public JSONObject getResponseFromAPI(String url) throws IOException, ParseException {
	    URL urlForGetRequest = new URL(url);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    int responseCode = conection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        StringBuffer response = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine+"\n");
	        } in .close();
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject) parser.parse(response.toString());
	        return obj;
	    } else {
	        return null;
	    }
	}
	
	public JSONObject formatResponse(JSONObject input) {
		Map<String,Object> temp=new HashMap<String,Object>();
		temp.put("detailedForecast",input.get("detailedForecast"));
		temp.put("shortForecast",input.get("shortForecast"));
		temp.put("number",input.get("number"));
		temp.put("temperatureUnit",input.get("temperatureUnit"));
		temp.put("name",input.get("name"));
		temp.put("temperature",input.get("temperature"));
		temp.put("startTime",input.get("startTime"));
		temp.put("isDaytime",input.get("isDaytime"));
		temp.put("endTime",input.get("endTime"));
		temp.put("windDirection",input.get("windDirection"));
		temp.put("windSpeed",input.get("windSpeed"));
		JSONObject response=new JSONObject(temp);
		return response;
	}
	
	

}
