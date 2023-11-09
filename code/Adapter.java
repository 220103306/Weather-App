import org.json.JSONObject;

public class Adapter implements WeatherData
{
    private WeatherAPI api;
    
    @Override
    public void setSource(String source){
        switch(source){
            case "OpenWeatherMap":
                api = new OpenWeatherMap();
                break;
            case "WeatherStack":
                api = new WeatherStack();
                break;
                
        }
    }
    @Override
    public void setCity(String cityName){
        api.setCityName(cityName);
    }
    @Override
    public String getData(){
        String data = "";
        if (api instanceof OpenWeatherMap){
            data = adaptOpenWeatherMapJson();
        }
        if (api instanceof WeatherStack){
            data = adaptWeatherStackJson();
        }
        return data;
    }

    public String adaptOpenWeatherMapJson(){
        try{
        String weatherDataJson = api.getWeatherDataJson();
        
        JSONObject jsonObject = new JSONObject(weatherDataJson);
        
        String discription = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
        int temperature = jsonObject.getJSONObject("main").getInt("temp") - 273;
        int humidity = jsonObject.getJSONObject("main").getInt("humidity");
        
        String weatherData = "Discription: " + discription + "\nTemperature: " + temperature + "C˚\nHumidity: " + humidity + "%";
        
        return weatherData;
        } catch(Exception e){
            Main.alertError("Incorrect city entered");
            return null;
        }
    }
    public String adaptWeatherStackJson(){
        try{ 
        String weatherDataJson = api.getWeatherDataJson();
        
        JSONObject jsonObject = new JSONObject(weatherDataJson);
        
        String discription = jsonObject.getJSONObject("current").getJSONArray("weather_descriptions").getString(0);
        int temperature = jsonObject.getJSONObject("current").getInt("temperature");
        int humidity = jsonObject.getJSONObject("current").getInt("humidity");
        
        String weatherData = "Discription: " + discription + "\nTemperature: " + temperature + "C˚\nHumidity: " + humidity + "%";
        
        return weatherData;
        } catch(Exception e){
            Main.alertError("Incorrect city entered");
            return null;
        }
    }
}
