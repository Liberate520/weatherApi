package ru.weatherclub.formatter;

import ru.weatherclub.model.Weather;

import java.text.DecimalFormat;

public class WeatherFormatter {

    public String format(Weather weather) {
        String result = "";

        result = result.concat("Местное время: " + weather.getLocalTime() + "\n");
        result = result.concat("Температура: " + weather.getTemperature() + "\n");
        result = result.concat("Ощущается как: " + weather.getFeelsLike() + "\n");
        result = result.concat("Скорость ветра: " + weather.getWindSpeed() + "\n");
        result = result.concat("Направление ветра: " + weather.getWindDirection() + "\n");
        result = result.concat("Давление: " + weather.getPressure() + "\n");
        result = result.concat("Влажность: " + weather.getHumidity() + "\n");
        result = result.concat("Регион: " + weather.getRegion() + "\n");
        result = result.concat("Широта: " + weather.getLat() + "\n");
        result = result.concat("Долгота: " + weather.getLot() + "\n");
        return result;
    }

    public Weather parseData(String data) {
        Weather weather = new Weather();

        weather.setRegion(getData(data, "query"));
        weather.setLat(getData(data, "lat"));
        weather.setLot(getData(data, "lon"));
        weather.setLocalTime(getData(data, "localtime"));
        weather.setTemperature(getData(data, "temp"));
        weather.setWindSpeed(getData(data, "wind_sp"));
        weather.setWindDirection(getData(data, "wind_dir"));
        weather.setPressure(getData(data, "pressure"));
        weather.setHumidity(getData(data, "humidity"));
        weather.setFeelsLike(getData(data, "feelslike"));

        if (Double.parseDouble(weather.getTemperature()) > 100){
            String s = String.valueOf(Double.parseDouble(weather.getTemperature()) - 273);
            s = s.substring(0, 5);
            weather.setTemperature(s);
        }
        return weather;
    }

    public String getData(String row, String param) {
        char[] row1 = row.toCharArray();
        char[] row2 = param.toCharArray();
        int k = 0;
        String result = "";
        for (int i = 0; i < row1.length; i++) {
            if (row1[i] == row2[k]) {
                k++;
            } else {
                k = 0;
            }
            if (k == row2.length) {
                while (Character.getType(row1[i]) != 9){
                    i++;
                }
                for (; (int) row1[i] != 34 ; i++) {
                    if (row1[i] == ',' && row1[i+1] == '"'){
                        return result;
                    }
                    if (row1[i] == '}'){
                        return result;
                    }
                    result += row1[i];
                }
                return result;
            }
        }
        return null;
    }
}