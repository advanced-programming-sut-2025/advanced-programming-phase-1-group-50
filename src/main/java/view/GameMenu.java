package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

import controller.GameDateAndWeatherController.DateController;
import controller.GameDateAndWeatherController.WeatherController;
import models.enums.GameMenuCommands;
import models.userInfo.*;
import models.Result;
import controller.GameMenuController;
public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();
    private final DateController dateController = new DateController();
    private final WeatherController weatherController = new WeatherController();
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher ;
        if(models.enums.GameMenuCommands.NewGame.getMatcher(input)!=null){
           ArrayList<Player> players = new ArrayList<>(); 
           System.out.println(controller.createNewGamePlayers(input, players));
           if(controller.createNewGamePlayers(input, players).getSuccessful()){
                
                int x = players.size();
                for(int i=0 ; i<x  ; i++){
                    String command = null;
                    while((command = scanner.nextLine()) != null ){
                        if(models.enums.GameMenuCommands.GameMap.getMatcher(command)!=null){
                            matcher = models.enums.GameMenuCommands.GameMap.getMatcher(command);
                            String Map = matcher.group(1);
                            int mapNumber = Integer.parseInt(matcher.group(1));
                            Result result  = controller.selectMapForCreateNewGame(mapNumber, players.get(i));
                            System.out.println(result);
                            if(result.getSuccessful()){
                                
                                break;
                            }


                        }
                        else {
                            System.out.println("invalid command , please try again!");
                        }

                        
                    }
                    System.out.println(controller.createNewGame(players));
                    

                }

           }
        }
        else if(models.enums.GameMenuCommands.LoadGame.getMatcher(input)!=null){
            System.out.println(controller.loadGame());
        }
        else if(models.enums.GameMenuCommands.ExitGame.getMatcher(input)!=null){
            System.out.println(controller.exitGame());
        }
        else if(models.enums.GameMenuCommands.DeleteGame.getMatcher(input)!=null){
            System.out.println(controller.deleteGame());
        }
        else if(models.enums.GameMenuCommands.NextTurn.getMatcher(input)!=null){
            System.out.println(controller.nextTurn());
        }
        else if(models.enums.GameMenuCommands.GetHour.getMatcher(input)!=null){
            System.out.println(dateController.getHour());
        }
        else if(models.enums.GameMenuCommands.GetDate.getMatcher(input)!=null){
            System.out.println(dateController.getDate());
        }
        else if(models.enums.GameMenuCommands.GetDate.getMatcher(input)!=null){
            System.out.println(dateController.getDateTime());
        }
        else if(GameMenuCommands.DayOfWeek.getMatcher(input)!=null){
            System.out.println(dateController.getDayOfTheWeek());
        }
        else if(GameMenuCommands.AdvancedTimeCheatCode.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.AdvancedTimeCheatCode.getMatcher(input);
            int hour = Integer.parseInt(matcher.group(1));
            System.out.println(dateController.advancedTimeCheatCode(hour));
        }
        else if(GameMenuCommands.AdvancedDateCheatCode.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.AdvancedDateCheatCode.getMatcher(input);
            int day = Integer.parseInt(matcher.group(1));
            System.out.println(dateController.advancedDateCheatCode(day));
        }
        else if(GameMenuCommands.GetSeason.getMatcher(input)!=null){
            System.out.println(dateController.getSeason());
        }
        else if(GameMenuCommands.GetTodayWeather.getMatcher(input)!=null){
            System.out.println(weatherController.getWeather());
        }
        else if(GameMenuCommands.WeatherForecast.getMatcher(input)!=null){
            System.out.println(weatherController.weatherForecast());
        }
        else if(GameMenuCommands.CheatWeatherSetCode.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.CheatWeatherSetCode.getMatcher(input);
            String weather = matcher.group(1).trim();
            System.out.println(weatherController.cheatWeatherSetCode(weather));
        }
        else if(GameMenuCommands.CheatThunder.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.CheatThunder.getMatcher(input);
            int thunderX = Integer.parseInt(matcher.group(1));
            int thunderY = Integer.parseInt(matcher.group(2));
            System.out.println(weatherController.cheatThunder(thunderX, thunderY));
        }
        else if(GameMenuCommands.GreenhouseBuild.getMatcher(input)!=null){

        }
        else {
            System.out.println("invalid command");
        }

    }

}
