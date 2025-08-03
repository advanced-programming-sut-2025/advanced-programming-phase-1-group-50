package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.gameApp.TimeProvider;

public class DateController {
    public Result advancedTimeCheatCode(int h , TimeProvider timeProvider){
        if(h <= 0){
            return new Result(false , "hour is less than 0");

        }
        timeProvider.getTime().advancedHour(h);
        return new Result(true , String.format("new advanced hour is : %d", timeProvider.getTime().getHour()));
    }
    public Result advancedDateCheatCode(int h , TimeProvider timeProvider){
        if(h <= 0){
            return new Result(false , "day is less than 0");
        }
        timeProvider.getTime().advancedDay(h);
        return new Result(true , "new advanced date is : " + timeProvider.getTime().getDate());
    }
}
