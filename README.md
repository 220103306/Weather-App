# Weather-App

To run the program, you need to enter “java -jar C:\path\to\your\Weather App.jar” on the command line
(use jdk version 17)

The program is built using JavaFX for the graphical user interface.

The program is designed using the Adapter pattern.

Main class:
Creates a basic graphical user interface.
Sends data such as city name and weather data source.
Receives weather data for the same city.

WeatherData interface:
The interface describes the methods that the Main class understands.
It allows you to receive weather data, transmit the name of the city and the selected source.

API classes:
API classes, such as OpenWeatherMap, WeatherStack, implement the WeatherAPI interface common to all. Each class has its own unique URL and API password. They make requests to the servers and receive weather data for the city they are hosting. And then they transmit the city in Json format.

Adapter class:
Since the User (Main) does not understand the API classes interface and does not understand the JSON format, this Adapter class is created.
It receives data from the user, such as the city and the source, creates the required API class, and passes the city to it. When the user clicks the show button, the Adapter receives Json data from the API class and converts it into something that the user can understand. An adapter serves as an adapter between two different interfaces.
