package com.forecast.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;


/**
 * Hello world!
 *
 */
@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner{
	static final int NUMBER_OF_DAYS=10;
	private static final org.apache.logging.log4j.Logger log = 
		    org.apache.logging.log4j.LogManager.getLogger(CommandLineRunner.class);
	
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
	
	@Override
	public void run(String... args) throws Exception {
		try {			
			if(args==null || args.length<2) {
				log.error("Required parameters missing.");
				return;
			}
			JSONObject points=getResponseFromAPI("https://api.weather.gov/points/"+args[0]+","+args[1]);
			if(points==null) {
				log.error("Not a valid response from https://api.weather.gov/points/");
				return;
			}
			String forecast=String.valueOf(((JSONObject)points.get("properties")).get("forecast"));
			log.debug("forecast:"+forecast);

			JSONObject output=getResponseFromAPI(forecast);
			if(output==null) {
				log.error("Not a valid response from https://api.weather.gov/gridpoints/");
				return;
			}
			JSONArray arr=(JSONArray)((JSONObject)output.get("properties")).get("periods");
			
			StringBuilder buildResponse=new StringBuilder();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			for(int i=0;i<NUMBER_OF_DAYS-1;i++) {
				buildResponse.append(gson.toJson(arr.get(i)));
			}
			log.info(buildResponse);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

    public static void main( String[] args )
    {
    	log.info("Starting weather application.");
    	SpringApplication.run(App.class, args);
    }
}
