package ru.weatherclub;

import ru.weatherclub.formatter.WeatherFormatter;
import ru.weatherclub.model.Weather;
import ru.weatherclub.weatherapi.WeatherService;

public class App{
    public String city(WeatherService weatherService, String city, int service)
    {
        WeatherFormatter weatherFormatter = new WeatherFormatter();
        String weatherData;
        String apiKey = null;
        String url = null;

        switch (service){
            case 0: {
                apiKey = "eceae5bae9142cc79ef4bb4199703b7f";
                url = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + city;
                break;
            }
            case 1: {
                apiKey = "1ecc0040255dea8d567b4528f1629bfe";
                url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=" + apiKey;
                break;
            }
            case 2: {
                apiKey = "2ca3e7b6051e441db830194bb249c72a";
                url = "https://api.weatherbit.io/v2.0/current?city=" + city + "&key=" + apiKey;
            }
        }
        weatherService.setApiKey(apiKey);
        weatherService.setIp(url);
        weatherData = weatherService.getWeatherData(city);

        if (weatherData.equals("")){
            return "";
        }
        Weather weather = weatherFormatter.parseData(weatherData);

        return weatherFormatter.format(weather);

    }

}