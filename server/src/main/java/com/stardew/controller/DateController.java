package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.TimeService;

public class DateController {
    public Result advancedTimeCheatCode(int h , TimeProvider timeProvider, TimeService timeService) {
        if(h <= 0){
            return new Result(false , "hour is less than 0");

        }
        timeProvider.getTime().advancedHour(h);
        timeService.sendUpdateTime();
        return new Result(true , String.format("new advanced hour is : %d", timeProvider.getTime().getHour()));
    }

    public Result advancedDateCheatCode(int d, TimeProvider timeProvider, TimeService timeService) {
        if(d <= 0){
            return new Result(false , "day is less than 0");
        }
        timeProvider.getTime().advancedDay(d);
        timeService.sendUpdateTime();
        return new Result(true , "new advanced date is : " + timeProvider.getTime().getDate());
    }
}
