package com.stardew.models.date;

public class TimeInterval {
    private final int days;
    private final int hours;

    public TimeInterval(int days, int hours) {
        this.days = days;
        this.hours = hours;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }
}
