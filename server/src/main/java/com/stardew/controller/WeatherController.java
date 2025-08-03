package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.date.Weather;

public class WeatherController {
    public Result cheatWeatherSetCode(String weather , TimeProvider timeProvider) {
        Weather w;
        try{
            w = Weather.valueOf(weather.trim());

        }
        catch(Exception exception){
            return new Result(false, "Invalid weather");
        }
        timeProvider.getTime().setNextDayWeather(w);
        return new Result(true, "Tomorrow weather : " + w.getName());
    }
}
