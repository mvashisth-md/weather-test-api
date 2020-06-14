package com.forecast.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest{
	
	@Mock
	WeatherService service;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
    public void inValidCordinates()
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
 
    public void validCordinates()
    {
    	try {
    		String args1="39.7456";
    		String args2="-97.0892";
    		when(service.getResponseFromAPI("https://api.weather.gov/points/"+args1+","+args2)).thenReturn(firstOutput());
    		when(service.getResponseFromAPI("\"https://api.weather.gov/gridpoints/TOP/31,80/forecast\"")).thenReturn(secondOutput());
    		
    		JSONArray arr=service.getWeatherResponse(args1, args2);
			assertEquals( 14, arr.size() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public JSONObject firstOutput() throws ParseException {
		String response="{\"geometry\":{\"coordinates\":[-97.0892,39.7456],\"type\":\"Point\"},\"id\":\"https:\\/\\/api.weather.gov\\/points\\/39.7456,-97.0892\",\"type\":\"Feature\",\"@context\":[\"https:\\/\\/raw.githubusercontent.com\\/geojson\\/geojson-ld\\/master\\/contexts\\/geojson-base.jsonld\",{\"wx\":\"https:\\/\\/api.weather.gov\\/ontology#\",\"@vocab\":\"https:\\/\\/api.weather.gov\\/ontology#\",\"distance\":{\"@type\":\"s:QuantitativeValue\",\"@id\":\"s:Distance\"},\"city\":\"s:addressLocality\",\"bearing\":{\"@type\":\"s:QuantitativeValue\"},\"county\":{\"@type\":\"@id\"},\"geo\":\"http:\\/\\/www.opengis.net\\/ont\\/geosparql#\",\"unit\":\"http:\\/\\/codes.wmo.int\\/common\\/unit\\/\",\"forecastGridData\":{\"@type\":\"@id\"},\"s\":\"https:\\/\\/schema.org\\/\",\"publicZone\":{\"@type\":\"@id\"},\"unitCode\":{\"@type\":\"@id\",\"@id\":\"s:unitCode\"},\"forecastOffice\":{\"@type\":\"@id\"},\"geometry\":{\"@type\":\"geo:wktLiteral\",\"@id\":\"s:GeoCoordinates\"},\"state\":\"s:addressRegion\",\"value\":{\"@id\":\"s:value\"}}],\"properties\":{\"radarStation\":\"KTWX\",\"fireWeatherZone\":\"https:\\/\\/api.weather.gov\\/zones\\/fire\\/KSZ009\",\"@type\":\"wx:Point\",\"forecastZone\":\"https:\\/\\/api.weather.gov\\/zones\\/forecast\\/KSZ009\",\"county\":\"https:\\/\\/api.weather.gov\\/zones\\/county\\/KSC201\",\"timeZone\":\"America\\/Chicago\",\"forecast\":\"https:\\/\\/api.weather.gov\\/gridpoints\\/TOP\\/31,80\\/forecast\",\"cwa\":\"TOP\",\"relativeLocation\":{\"geometry\":{\"coordinates\":[-97.086661,39.679376],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"distance\":{\"unitCode\":\"unit:m\",\"value\":7366.9851976443715},\"city\":\"Linn\",\"bearing\":{\"unitCode\":\"unit:degrees_true\",\"value\":358},\"state\":\"KS\"}},\"forecastHourly\":\"https:\\/\\/api.weather.gov\\/gridpoints\\/TOP\\/31,80\\/forecast\\/hourly\",\"observationStations\":\"https:\\/\\/api.weather.gov\\/gridpoints\\/TOP\\/31,80\\/stations\",\"gridX\":31,\"forecastGridData\":\"https:\\/\\/api.weather.gov\\/gridpoints\\/TOP\\/31,80\",\"gridY\":80,\"forecastOffice\":\"https:\\/\\/api.weather.gov\\/offices\\/TOP\",\"@id\":\"https:\\/\\/api.weather.gov\\/points\\/39.7456,-97.0892\"}}";
		JSONParser parser = new JSONParser();
		return (JSONObject) parser.parse(response);
	}
    
    public JSONObject secondOutput() throws ParseException {
    	String response="{\"geometry\":{\"geometries\":[{\"coordinates\":[-97.0944084,39.7559738],\"type\":\"Point\"},{\"coordinates\":[[[-97.1089731,39.7668263],[-97.1085269,39.7447788],[-97.0798467,39.7451195],[-97.08028680000001,39.767167],[-97.1089731,39.7668263]]],\"type\":\"Polygon\"}],\"type\":\"GeometryCollection\"},\"type\":\"Feature\",\"@context\":[\"https:\\/\\/raw.githubusercontent.com\\/geojson\\/geojson-ld\\/master\\/contexts\\/geojson-base.jsonld\",{\"geo\":\"http:\\/\\/www.opengis.net\\/ont\\/geosparql#\",\"wx\":\"https:\\/\\/api.weather.gov\\/ontology#\",\"unit\":\"http:\\/\\/codes.wmo.int\\/common\\/unit\\/\",\"@vocab\":\"https:\\/\\/api.weather.gov\\/ontology#\"}],\"properties\":{\"elevation\":{\"unitCode\":\"unit:m\",\"value\":441.96000000000004},\"validTimes\":\"2020-06-13T21:00:00+00:00\\/P7DT4H\",\"forecastGenerator\":\"BaselineForecastGenerator\",\"generatedAt\":\"2020-06-14T05:07:15+00:00\",\"periods\":[{\"detailedForecast\":\"Mostly clear, with a low around 71. Southeast wind around 15 mph, with gusts as high as 25 mph.\",\"temperatureTrend\":null,\"shortForecast\":\"Mostly Clear\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/night\\/few?size=medium\",\"number\":1,\"temperatureUnit\":\"F\",\"name\":\"Overnight\",\"temperature\":71,\"startTime\":\"2020-06-14T00:00:00-05:00\",\"isDaytime\":false,\"endTime\":\"2020-06-14T06:00:00-05:00\",\"windDirection\":\"SE\",\"windSpeed\":\"15 mph\"},{\"detailedForecast\":\"Sunny, with a high near 97. South wind 15 to 20 mph, with gusts as high as 30 mph.\",\"temperatureTrend\":null,\"shortForecast\":\"Sunny\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/day\\/hot?size=medium\",\"number\":2,\"temperatureUnit\":\"F\",\"name\":\"Sunday\",\"temperature\":97,\"startTime\":\"2020-06-14T06:00:00-05:00\",\"isDaytime\":true,\"endTime\":\"2020-06-14T18:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"15 to 20 mph\"},{\"detailedForecast\":\"Partly cloudy, with a low around 72. South wind 15 to 20 mph, with gusts as high as 30 mph.\",\"temperatureTrend\":null,\"shortForecast\":\"Partly Cloudy\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/night\\/sct?size=medium\",\"number\":3,\"temperatureUnit\":\"F\",\"name\":\"Sunday Night\",\"temperature\":72,\"startTime\":\"2020-06-14T18:00:00-05:00\",\"isDaytime\":false,\"endTime\":\"2020-06-15T06:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"15 to 20 mph\"},{\"detailedForecast\":\"Mostly sunny, with a high near 95. South wind 10 to 15 mph, with gusts as high as 30 mph.\",\"temperatureTrend\":null,\"shortForecast\":\"Mostly Sunny\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/day\\/sct?size=medium\",\"number\":4,\"temperatureUnit\":\"F\",\"name\":\"Monday\",\"temperature\":95,\"startTime\":\"2020-06-15T06:00:00-05:00\",\"isDaytime\":true,\"endTime\":\"2020-06-15T18:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"10 to 15 mph\"},{\"detailedForecast\":\"Mostly clear, with a low around 71. South wind 10 to 15 mph, with gusts as high as 25 mph.\",\"temperatureTrend\":null,\"shortForecast\":\"Mostly Clear\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/night\\/few?size=medium\",\"number\":5,\"temperatureUnit\":\"F\",\"name\":\"Monday Night\",\"temperature\":71,\"startTime\":\"2020-06-15T18:00:00-05:00\",\"isDaytime\":false,\"endTime\":\"2020-06-16T06:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"10 to 15 mph\"},{\"detailedForecast\":\"Sunny, with a high near 96. South wind 10 to 15 mph, with gusts as high as 30 mph.\",\"temperatureTrend\":null,\"shortForecast\":\"Sunny\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/day\\/few?size=medium\",\"number\":6,\"temperatureUnit\":\"F\",\"name\":\"Tuesday\",\"temperature\":96,\"startTime\":\"2020-06-16T06:00:00-05:00\",\"isDaytime\":true,\"endTime\":\"2020-06-16T18:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"10 to 15 mph\"},{\"detailedForecast\":\"Mostly clear, with a low around 74. South wind around 15 mph, with gusts as high as 30 mph.\",\"temperatureTrend\":null,\"shortForecast\":\"Mostly Clear\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/night\\/few?size=medium\",\"number\":7,\"temperatureUnit\":\"F\",\"name\":\"Tuesday Night\",\"temperature\":74,\"startTime\":\"2020-06-16T18:00:00-05:00\",\"isDaytime\":false,\"endTime\":\"2020-06-17T06:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"15 mph\"},{\"detailedForecast\":\"Sunny, with a high near 96. South wind around 15 mph, with gusts as high as 30 mph.\",\"temperatureTrend\":null,\"shortForecast\":\"Sunny\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/day\\/few?size=medium\",\"number\":8,\"temperatureUnit\":\"F\",\"name\":\"Wednesday\",\"temperature\":96,\"startTime\":\"2020-06-17T06:00:00-05:00\",\"isDaytime\":true,\"endTime\":\"2020-06-17T18:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"15 mph\"},{\"detailedForecast\":\"A slight chance of showers and thunderstorms after 1am. Partly cloudy, with a low around 72. South wind 10 to 15 mph, with gusts as high as 25 mph. Chance of precipitation is 20%.\",\"temperatureTrend\":null,\"shortForecast\":\"Partly Cloudy then Slight Chance Showers And Thunderstorms\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/night\\/sct\\/tsra_hi,20?size=medium\",\"number\":9,\"temperatureUnit\":\"F\",\"name\":\"Wednesday Night\",\"temperature\":72,\"startTime\":\"2020-06-17T18:00:00-05:00\",\"isDaytime\":false,\"endTime\":\"2020-06-18T06:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"10 to 15 mph\"},{\"detailedForecast\":\"A slight chance of showers and thunderstorms. Mostly sunny, with a high near 93. Chance of precipitation is 20%.\",\"temperatureTrend\":null,\"shortForecast\":\"Slight Chance Showers And Thunderstorms\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/day\\/tsra_hi,20?size=medium\",\"number\":10,\"temperatureUnit\":\"F\",\"name\":\"Thursday\",\"temperature\":93,\"startTime\":\"2020-06-18T06:00:00-05:00\",\"isDaytime\":true,\"endTime\":\"2020-06-18T18:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"5 to 10 mph\"},{\"detailedForecast\":\"A chance of showers and thunderstorms. Partly cloudy, with a low around 69. Chance of precipitation is 40%.\",\"temperatureTrend\":null,\"shortForecast\":\"Chance Showers And Thunderstorms\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/night\\/tsra_hi,40?size=medium\",\"number\":11,\"temperatureUnit\":\"F\",\"name\":\"Thursday Night\",\"temperature\":69,\"startTime\":\"2020-06-18T18:00:00-05:00\",\"isDaytime\":false,\"endTime\":\"2020-06-19T06:00:00-05:00\",\"windDirection\":\"SE\",\"windSpeed\":\"10 mph\"},{\"detailedForecast\":\"A chance of showers and thunderstorms. Partly sunny, with a high near 91. Chance of precipitation is 40%.\",\"temperatureTrend\":null,\"shortForecast\":\"Chance Showers And Thunderstorms\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/day\\/tsra_hi,40\\/tsra_hi,30?size=medium\",\"number\":12,\"temperatureUnit\":\"F\",\"name\":\"Friday\",\"temperature\":91,\"startTime\":\"2020-06-19T06:00:00-05:00\",\"isDaytime\":true,\"endTime\":\"2020-06-19T18:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"10 mph\"},{\"detailedForecast\":\"A chance of showers and thunderstorms. Mostly cloudy, with a low around 68. Chance of precipitation is 50%.\",\"temperatureTrend\":null,\"shortForecast\":\"Chance Showers And Thunderstorms\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/night\\/tsra_sct,40\\/tsra_sct,50?size=medium\",\"number\":13,\"temperatureUnit\":\"F\",\"name\":\"Friday Night\",\"temperature\":68,\"startTime\":\"2020-06-19T18:00:00-05:00\",\"isDaytime\":false,\"endTime\":\"2020-06-20T06:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"5 mph\"},{\"detailedForecast\":\"A chance of showers and thunderstorms. Partly sunny, with a high near 87. Chance of precipitation is 50%.\",\"temperatureTrend\":null,\"shortForecast\":\"Chance Showers And Thunderstorms\",\"icon\":\"https:\\/\\/api.weather.gov\\/icons\\/land\\/day\\/tsra_sct,50\\/tsra_sct,40?size=medium\",\"number\":14,\"temperatureUnit\":\"F\",\"name\":\"Saturday\",\"temperature\":87,\"startTime\":\"2020-06-20T06:00:00-05:00\",\"isDaytime\":true,\"endTime\":\"2020-06-20T18:00:00-05:00\",\"windDirection\":\"S\",\"windSpeed\":\"5 to 10 mph\"}],\"updateTime\":\"2020-06-14T03:40:09+00:00\",\"units\":\"us\",\"updated\":\"2020-06-14T03:40:09+00:00\"}}";
    	JSONParser parser = new JSONParser();
		return (JSONObject) parser.parse(response);
    }
  
}