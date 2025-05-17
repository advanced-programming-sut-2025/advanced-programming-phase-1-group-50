package controller.GameDateAndWeatherController;

import models.Result;
import models.app.App;
import models.date.Weather;
import models.foraging.Growable;
import models.mapInfo.Tile;

public class WeatherController {

    public Tile findTilePosition(int x , int y){
        for(int i = 0; i< App.getGame().getMap().getTiles().length ; i++){
            for(int j=0 ; j<App.getGame().getMap().getTiles().length ; j++){
                if(i==x && j==y){
                    return App.getGame().getMap().getTiles()[i][j];
                }
            }
        }
        return null;
    }
    public Result getWeather(){
        return new Result(true, "Today weather : " + App.getGame().getTime().getWeather().getName());
    }
    public Result weatherForecast(){
        return new Result(true , "Tomorrow weather : " + App.getGame().getTime().getNextDayWeather()
                .getName());
    }
    public Result cheatWeatherSetCode(String weather){
        Weather w;
        try{
            w = Weather.valueOf(weather.trim());

        }
        catch(Exception exception){
            return new Result(false, "Invalid weather");
        }
        App.getGame().getTime().setNextDayWeather(w);
        return new Result(true, "Tomorrow weather : " + w.getName());
    }
    public Result cheatThunder(int x , int y)
    {
        Tile t = findTilePosition(x , y);
        if(t == null){
            return new Result(false, "Tile not found");
        }
        if(t.getPlaceable() instanceof Growable){
            t.setPlaceable(null);
            t.setSymbol('.');
            t.setFertilizer(null);
            t.setPlowed(false);

        }
        t.setGotThunder(true);
        return new Result(true , "position with x : " + x + " and y : " + y + " is going thunder");
    }
}
