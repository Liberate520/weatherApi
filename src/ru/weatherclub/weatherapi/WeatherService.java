package ru.weatherclub.weatherapi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherService {
    private String apiKey = "";
    private String ip = "";

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getWeatherData(String city) {

        String inputLine = null;
        String result = "";

        try {
            URL url = new URL(ip);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                result = result + inputLine;
            }

            in.close();
        } catch (Exception e){
            System.out.println("Не удалось получить данные!");
            return "";
        }
        return result;
    }
}
