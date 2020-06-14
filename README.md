# weather stand-alone application

This application returns the weather forcast of next 5 days.

1) It retrieves points from the given latitude and longitude API using following API:
https://api.weather.gov/points/{latitude},{longitude}
For example: https://api.weather.gov/gridpoints/TOP/31,80/forecast

2) Once we have grid coordinates, It retrieves weather forecast for that grid using following  APi and returns the results:
https://api.weather.gov/gridpoints/{office}/{grid X},{grid Y}/forecast
For example: https://api.weather.gov/gridpoints/TOP/31,80/forecast


## Instructions to use this Api

1. Build Using Maven (When using command prompt instead of IDE where your current directory is project folder).

```mvn clean install ```

2. Run the Application Using Maven.

```mvn spring-boot:run -Dspring-boot.run.arguments="39.7456,-97.0892" ```

3. Run the Application as stand-alone Java application.
 
``` java -jar target/weather-0.0.1-SNAPSHOT.jar ```

4. Application prints forecast for next 5 days. An example response is as following:

```
Response :

{
  "detailedForecast": "Sunny, with a high near 93. Southeast wind around 15 mph, with gusts as high as 30 mph.",
  "shortForecast": "Sunny",
  "icon": "https://api.weather.gov/icons/land/day/skc?size\u003dmedium",
  "number": 1,
  "temperatureUnit": "F",
  "name": "This Afternoon",
  "temperature": 93,
  "startTime": "2020-06-13T14:00:00-05:00",
  "isDaytime": true,
  "endTime": "2020-06-13T18:00:00-05:00",
  "windDirection": "SE",
  "windSpeed": "15 mph"
}{
  "detailedForecast": "Mostly clear, with a low around 71. Southeast wind around 15 mph, with gusts as high as 25 mph.",
  "shortForecast": "Mostly Clear",
  "icon": "https://api.weather.gov/icons/land/night/few?size\u003dmedium",
  "number": 2,
  "temperatureUnit": "F",
  "name": "Tonight",
  "temperature": 71,
  "startTime": "2020-06-13T18:00:00-05:00",
  "isDaytime": false,
  "endTime": "2020-06-14T06:00:00-05:00",
  "windDirection": "SE",
  "windSpeed": "15 mph"
}{
  "detailedForecast": "Sunny, with a high near 96. South wind 15 to 20 mph, with gusts as high as 30 mph.",
  "shortForecast": "Sunny",
  "icon": "https://api.weather.gov/icons/land/day/few?size\u003dmedium",
  "number": 3,
  "temperatureUnit": "F",
  "name": "Sunday",
  "temperature": 96,
  "startTime": "2020-06-14T06:00:00-05:00",
  "isDaytime": true,
  "endTime": "2020-06-14T18:00:00-05:00",
  "windDirection": "S",
  "windSpeed": "15 to 20 mph"
}{
  "detailedForecast": "Mostly cloudy, with a low around 73. South wind around 15 mph, with gusts as high as 30 mph.",
  "shortForecast": "Mostly Cloudy",
  "icon": "https://api.weather.gov/icons/land/night/bkn?size\u003dmedium",
  "number": 4,
  "temperatureUnit": "F",
  "name": "Sunday Night",
  "temperature": 73,
  "startTime": "2020-06-14T18:00:00-05:00",
  "isDaytime": false,
  "endTime": "2020-06-15T06:00:00-05:00",
  "windDirection": "S",
  "windSpeed": "15 mph"
}{
  "detailedForecast": "Mostly sunny, with a high near 96. South wind around 15 mph, with gusts as high as 30 mph.",
  "shortForecast": "Mostly Sunny",
  "icon": "https://api.weather.gov/icons/land/day/sct?size\u003dmedium",
  "number": 5,
  "temperatureUnit": "F",
  "name": "Monday",
  "temperature": 96,
  "startTime": "2020-06-15T06:00:00-05:00",
  "isDaytime": true,
  "endTime": "2020-06-15T18:00:00-05:00",
  "windDirection": "S",
  "windSpeed": "15 mph"
}{
  "detailedForecast": "Partly cloudy, with a low around 73. South wind around 15 mph, with gusts as high as 25 mph.",
  "shortForecast": "Partly Cloudy",
  "icon": "https://api.weather.gov/icons/land/night/sct?size\u003dmedium",
  "number": 6,
  "temperatureUnit": "F",
  "name": "Monday Night",
  "temperature": 73,
  "startTime": "2020-06-15T18:00:00-05:00",
  "isDaytime": false,
  "endTime": "2020-06-16T06:00:00-05:00",
  "windDirection": "S",
  "windSpeed": "15 mph"
}{
  "detailedForecast": "Mostly sunny, with a high near 97. South wind around 15 mph, with gusts as high as 30 mph.",
  "shortForecast": "Mostly Sunny",
  "icon": "https://api.weather.gov/icons/land/day/hot?size\u003dmedium",
  "number": 7,
  "temperatureUnit": "F",
  "name": "Tuesday",
  "temperature": 97,
  "startTime": "2020-06-16T06:00:00-05:00",
  "isDaytime": true,
  "endTime": "2020-06-16T18:00:00-05:00",
  "windDirection": "S",
  "windSpeed": "15 mph"
}{
  "detailedForecast": "Partly cloudy, with a low around 74. South wind around 15 mph, with gusts as high as 30 mph.",
  "shortForecast": "Partly Cloudy",
  "icon": "https://api.weather.gov/icons/land/night/sct?size\u003dmedium",
  "number": 8,
  "temperatureUnit": "F",
  "name": "Tuesday Night",
  "temperature": 74,
  "startTime": "2020-06-16T18:00:00-05:00",
  "isDaytime": false,
  "endTime": "2020-06-17T06:00:00-05:00",
  "windDirection": "S",
  "windSpeed": "15 mph"
}{
  "detailedForecast": "Mostly sunny, with a high near 97. South wind around 15 mph, with gusts as high as 30 mph.",
  "shortForecast": "Mostly Sunny",
  "icon": "https://api.weather.gov/icons/land/day/hot?size\u003dmedium",
  "number": 9,
  "temperatureUnit": "F",
  "name": "Wednesday",
  "temperature": 97,
  "startTime": "2020-06-17T06:00:00-05:00",
  "isDaytime": true,
  "endTime": "2020-06-17T18:00:00-05:00",
  "windDirection": "S",
  "windSpeed": "15 mph"
}
```

## Technologies Used 

Java 8, Spring Boot, Maven, Junit
