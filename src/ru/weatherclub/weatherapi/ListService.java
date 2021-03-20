package ru.weatherclub.weatherapi;

public class ListService {
    public String listService(int a){
        switch (a){
            case 0: {
                return "weatherstack.com";
            }
            case 1: {
                return "openweathermap.org";
            }
            case 2: {
                return "weatherbit.io";
            }
        }
        return null;
    }
}
