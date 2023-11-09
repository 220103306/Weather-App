import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class OpenWeatherMap implements WeatherAPI
{
    private String cityName;
    
    private final String API_KEY = "117e98f1365e1ec313366d7f58654b6a";
    private final String API_DATA = "https://api.openweathermap.org/data/2.5/weather?lat=";
    private final String API_GEO = "http://api.openweathermap.org/geo/1.0/direct?q=";
    
    @Override
    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    @Override
    public String getWeatherDataJson(){
        try {
            String geoCode = getGeoCode();
            double lat = getLat(getGeoCode());
            double lon = getLon(getGeoCode());
            String weatherDataUrl = API_DATA + lat + "&lon=" + lon + "&appid=" + API_KEY;
            
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
    private String getGeoCode(){
        try {
            String geoCodeUrl = API_GEO + cityName + "&limit=1&appid=" + API_KEY;
            
            URL url = new URL(geoCodeUrl);
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
            String geoCode = content.toString();
            
            return geoCode;
        } catch (Exception e) {
            return null;
        }
    }
    private double getLat(String geoCoode) {
        int latStartIndex = geoCoode.indexOf("\"lat\":") + 6;
        int latEndIndex = geoCoode.indexOf(",", latStartIndex);
        String latValue = geoCoode.substring(latStartIndex, latEndIndex);
        return Double.parseDouble(latValue);
    }
    private double getLon(String geoCoode) {
        int lonStartIndex = geoCoode.indexOf("\"lon\":") + 6;
        int lonEndIndex = geoCoode.indexOf(",", lonStartIndex);
        String lonValue = geoCoode.substring(lonStartIndex, lonEndIndex);
        return Double.parseDouble(lonValue);
    }
}
