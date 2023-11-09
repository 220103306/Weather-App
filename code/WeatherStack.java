import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherStack implements WeatherAPI
{
    private String cityName;

    private final String API_KEY = "b73a5e7c4a2c23063f2c3f75daacd25a";
    private final String API_DATA = "http://api.weatherstack.com/current";

    @Override
    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    @Override
    public String getWeatherDataJson() {
        try {
            String weatherDataUrl = API_DATA + "?access_key=" + API_KEY + "&query=" + cityName;
            
            URL url = new URL(weatherDataUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            connection.disconnect();
            String weatherDataJson = content.toString();
            
            return weatherDataJson;
        } catch (Exception e) {
            return null;
        }
    }
}
