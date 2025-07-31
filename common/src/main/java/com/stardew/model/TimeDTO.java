package com.stardew.model;

public class TimeDTO {
    private int minute;
    private int hour;
    private int day;
    private String seasonName;
    private String dayOfWeekName;
    private String weather;

    public TimeDTO() {}

    public TimeDTO(int minute, int hour, int day, String seasonName, String dayOfWeekName, String weather) {
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.seasonName = seasonName;
        this.dayOfWeekName = dayOfWeekName;
        this.weather = weather;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeekName() {
        return dayOfWeekName;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getWeather() {
        return weather;
    }

}
