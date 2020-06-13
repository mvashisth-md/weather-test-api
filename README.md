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
```mvn clean install```

2. Run the Application Using Maven (When using command prompt instead of IDE)
```
mvn spring-boot:run -Dspring-boot.run.arguments="39.7456,-97.0892"

3. Application prints forcast for the next 5 days as following:

```
Response :

```json
{
  "customerId":"C1",
  "monthlyRewards":{
    "2020-03":590,
    "2020-02":0
  },
  "totalRewards":590
}

```
5. To get Rewards of all customers, use this api endpoint: 
http://localhost:8080/rewards-api/customers/rewards
```json
[
  {
      "customerId":"C1",
      "monthlyRewards":{
      "2020-03":590,
      "2020-02":0
      },
      "totalRewards":590
   },
   {
      "customerId":"C2",
      "monthlyRewards":{
      "2020-04":30,
      "2020-02":170
      },
      "totalRewards":200
    },
    
    {
      "customerId":"C3",
      "monthlyRewards":{
      "2020-04":440,
      "2020-02":250
      },
      "totalRewards":690
    },
    
    {
      "customerId":"C4",
      "monthlyRewards":{
      "2020-04":130
      },
      "totalRewards":130
    }
]

```
6. To create/save more test data, use this POST api endpoint in Swagger: 
http://localhost:8080/rewards-api/customers/{customerId}/transaction

```json
{
  "amount": 150,
  "transactionTs": "2020-04-19T10:20:30.678Z"
}

```

## Technologies Used 

Java 8, Spring Boot, H2 database, Gradle, Junit-5 and Mockito.
