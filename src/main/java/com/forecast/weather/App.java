package com.forecast.weather;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner{
	private static final int NUMBER_OF_DAYS=10;
	private static final org.apache.logging.log4j.Logger log = 
		    org.apache.logging.log4j.LogManager.getLogger(App.class);
	
	@Autowired
	WeatherService service;
	
	@Override
	public void run(String... args) throws Exception {
		try {			
			if(args==null || args.length<2) {
				log.error("Required parameters missing.");
				return;
			}
			
			JSONArray arr=service.getWeatherResponse(args[0],args[1]);
			if(arr==null) {
				log.error("Unable to provide data for requested point.");
				return;
			}
			StringBuilder buildResponse=new StringBuilder();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			for(int i=0;i<NUMBER_OF_DAYS;i++) {	
				buildResponse.append(gson.toJson((service.formatResponse((JSONObject)arr.get(i)))));
			}
			log.info("Response:{}",buildResponse);			
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
