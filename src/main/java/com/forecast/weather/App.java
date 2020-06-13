package com.forecast.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner{
	
	public JSONObject API(String url) throws IOException, ParseException {
		System.out.println(url);
	    URL urlForGetRequest = new URL(url);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
//	    conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
	    int responseCode = conection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        StringBuffer response = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine+"\n");
	        } in .close();
	        // print result
//	        System.out.println("JSON String Result " + response.toString());
	        JSONParser parser = new JSONParser();
	        JSONObject obj = (JSONObject) parser.parse(response.toString());
	        //GetAndPost.POSTRequest(response.toString());
	        return obj;
	    } else {
	        return null;
	    }
	}
	
	@Override
	public void run(String... args) throws Exception {
		try {
			for(String i:args) {
				System.out.println("value here:"+i);
			}
			
			JSONObject obj=API("https://api.weather.gov/points/"+args[0]+","+args[1]);
//			String gridX=String.valueOf(((JSONObject)obj.get("properties")).get("gridX"));
//			String gridY=String.valueOf(((JSONObject)obj.get("properties")).get("gridY"));
//			String cwa=(String)((JSONObject)obj.get("properties")).get("cwa");
			String forecast=String.valueOf(((JSONObject)obj.get("properties")).get("forecast"));
			System.out.println("forecast:"+forecast);

//			JSONObject output=forecast.weatherAPI("https://api.weather.gov/gridpoints/"+cwa+"/"+gridX+","+gridY+"/forecast");
			JSONObject output=API(forecast);
//			System.out.println(output);
			
			String finalResponse=String.valueOf(((JSONObject)output.get("properties")).get("periods"));
			System.out.println("finalResponse:"+finalResponse);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
