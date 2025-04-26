package controller.GameDateAndWeatherController;

import models.Result;
import models.app.App;

public class DateController {
    public Result getHour(){
        return new Result(true , String.format("it's %d" , App.getGame().getTime().getHour()));
    }
    public Result getDate(){
        return new Result(true , String.format("we are in %s season , in %d " , App.getGame().getTime().getSeason().name() , App.getGame().getTime().getDate()));
    }
    public Result getDateTime(){
        return new Result(true , String.format("Season : %s , Day : %d , Hour : %d" , App.getGame().getTime()
                .getSeason().name() , App.getGame().getTime().getDate() , App.getGame().getTime().getHour()));
    }
    public Result getDayOfTheWeek(){
        return new Result(true , String.format("Day : %s ", App.getGame().getTime().getDayOfWeek().name()));
    }
    public Result advancedTimeCheatCode(int h){
        if(h <= 0){
            return new Result(false , "hour is less than 0");

        }
        App.getGame().getTime().advancedHour(h);
        return new Result(true , String.format("new advanced hour is : %d", App.getGame().getTime().getHour()));
    }
    public Result advancedDateCheatCode(int h){
        if(h <= 0){
            return new Result(false , "day is less than 0");
        }
        App.getGame().getTime().advancedDay(h);
        return new Result(true , "new advanced date is : " + App.getGame().getTime().getDate());
    }
    public Result getSeason(){
        return new Result(true,"Season : " + App.getGame().getTime().getSeason());
    }
}
