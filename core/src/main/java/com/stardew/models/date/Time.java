package com.stardew.models.date;

import com.stardew.models.app.App;

import java.util.ArrayList;
import java.util.Random;

public class Time {
    public Time clone;
    private Season season;
    private DaysOfTheWeek dayOfWeek ;
    private int date;
    private int hour;
    private Weather weather;
    private Weather nextDayWeather;
    public Time(){
        this.hour = 9;
        this.dayOfWeek = DaysOfTheWeek.Saturday;
        this.season = Season.Spring;
        this.date = 1;
        this.weather = Weather.Sunny;
        this.nextDayWeather = Weather.Sunny;

    }

    public void advancedHour(int h){
        this.hour += h;
        while (this.hour >= 22){
            this.hour -= 22 ;
            this.hour += 9;
            advancedDay(1);

        }

    }

    public void advancedDay(int d){
        for(int i = 0 ; i < d; i++){
            this.date ++;
            if(this.date > 28){
                this.date = 1;
                advancedSeason();
            }
            DaysOfTheWeek[] days = DaysOfTheWeek.values();
            int currentIndex = this.dayOfWeek.ordinal();
            this.dayOfWeek = days[(currentIndex + 1) % days.length];
            this.weather = this.nextDayWeather;
            this.nextDayWeather = createNextDayWeather();
            App.getGame().callMethodsForTomorrow();
        }
    }
    public void advancedSeason(){
        Season[] seasons = Season.values();
        int currentIndex = this.season.ordinal();
        if (currentIndex < 3) {
            this.season = seasons[currentIndex + 1];
        } else {
            this.season = Season.Spring;
        }
    }

    public Season getSeason() {
        return season;
    }

    public DaysOfTheWeek getDayOfWeek() {
        return dayOfWeek;
    }


    public int getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Weather getNextDayWeather() {
        return nextDayWeather;
    }

    public void setNextDayWeather(Weather nextDayWeather) {
        this.nextDayWeather = nextDayWeather;
    }
    public Weather createNextDayWeather(){
        Weather[] weather = Weather.values();
        ArrayList<Weather> weatherList = new ArrayList<>();
        for(Weather w : weather){
            if(w.getSeasons().contains(this.season)){
                weatherList.add(w);
            }
        }
        Random random = new Random();
        int index = random.nextInt(weatherList.size());
        return weatherList.get(index);
    }

    public Time clone() {
        Time cloned = new Time();
        cloned.season = season;
        cloned.dayOfWeek = dayOfWeek;
        cloned.date = date;
        cloned.hour = hour;
        cloned.weather = weather;
        cloned.nextDayWeather = nextDayWeather;
        return cloned;
    }
}
