package models.date;

public class Time {
    private Season season;
    private DaysOfTheWeek dayOfWeek;
    private int date;
    private int hour;
    private Weather weather;
    private Weather nextDayWeather;

    public void increaseHour(int x) {
//        this.hour += x;
//        while (this.hour >= 24) {
//            this.hour -= 24;
//
//
//        }
    }
    public void increaseDate(int x) {

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
