package view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import controller.AbilityAndEnergyController.EnergyController;
import controller.GameDateAndWeatherController.DateController;
import controller.GameDateAndWeatherController.WeatherController;
import controller.ToolsControllers.ToolController;
import models.app.App;
import models.app.Menus;
import models.enums.GameMenuCommands;
import models.mapInfo.Position;
import models.userInfo.*;
import models.Result;
import controller.GameMenuController;
public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();
    private final DateController dateController = new DateController();
    private final WeatherController weatherController = new WeatherController();
    private final EnergyController energyController = new EnergyController();
    private final ToolController toolController = new ToolController();
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher ;

        if(models.enums.GameMenuCommands.LoadGame.getMatcher(input)!=null){
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
            System.out.println(controller.buildGreenhouse());
        }
        else if(GameMenuCommands.EnergyShow.getMatcher(input)!=null){
            System.out.println(energyController.showEnergy());
        }
        else if(GameMenuCommands.CheatSetEnergy.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.CheatSetEnergy.getMatcher(input);
            int energyValue = Integer.parseInt(matcher.group(1));
            System.out.println(energyController.setEnergy(energyValue));
        }
        else if(GameMenuCommands.CheatUnlimitedEnergy.getMatcher(input)!=null){
            System.out.println(energyController.setUnlimitedEnergy());
        }
        else if(GameMenuCommands.ExitMenu.getMatcher(input)!=null){
            App.setMenu(Menus.MainMenu);
        }
        else if(GameMenuCommands.PrintMap.getMatcher(input)!=null){
            App.getGame().getMap().printMap();
        }
        else if(GameMenuCommands.Walk.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.Walk.getMatcher(input);
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            List<Position> positions = new LinkedList<Position>();
            Result result = controller.findPath(x  ,y , positions);
            System.out.println(result.getMessage());
            if(result.getSuccessful()){
                System.out.println("do you want to continue?");
                String message = scanner.nextLine();
                if(message.equals("yes")){
                    System.out.println(controller.walk(x , y , positions));
                }


            }
        }
        else if(GameMenuCommands.HelpReadingMap.getMatcher(input)!=null){
            System.out.println(controller.helpReadingMap());
        }
        else if(GameMenuCommands.InventoryShow.getMatcher(input)!=null){
            System.out.println(controller.inventoryShow());
        }
        else if(GameMenuCommands.InventoryTrash.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.InventoryTrash.getMatcher(input);
            boolean hasNumber = false;

            String name = matcher.group(1);
            if(matcher.group(3)!=null){
                hasNumber = true;
            }
            int number = Integer.parseInt(matcher.group(3));
            System.out.println(controller.inventoryTrash(name , number , hasNumber));
        }
        else if(GameMenuCommands.ToolsEquip.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.ToolsEquip.getMatcher(input);
            String toolName = matcher.group(1);
            System.out.println(toolController.ToolsEquip(toolName));
        }
        else if(GameMenuCommands.ShowCurrentTool.getMatcher(input)!=null){
            System.out.println(toolController.showCurrentTool());
        }
        else if(GameMenuCommands.ToolsShowAvailable.getMatcher(input)!=null){
            System.out.println(toolController.showAvailableTool());
        }
        else if(GameMenuCommands.ToolUpgrade.getMatcher(input)!=null){
            matcher = models.enums.GameMenuCommands.ToolUpgrade.getMatcher(input);
            String toolName = matcher.group(1);
        }
        else if(models.enums.GameMenuCommands.NewGame.getMatcher(input)!=null){
            ArrayList<Player> players = new ArrayList<>();
            Player currentPlayer = new Player(App.getLoggedInUser().getUsername() , App.getLoggedInUser().getNickname() , App.getLoggedInUser());
            players.add(currentPlayer);

            Result result1 = controller.createNewGamePlayers(input, players);
            System.out.println(result1);
            if(result1.getSuccessful()){

                int x = players.size();
                System.out.println("number of created players: " + x);
                for(Player p : players){
                    System.out.println(p.getUsername());
                }
                int[] startXForMap = {0 ,150 ,0 ,150 };
                int[] startYForMap = {0 , 0 , 125 ,125};
                for(int i=0 ; i<x  ; i++){
                    String command ;
                    while((command = scanner.nextLine()) != null ){
                        if(models.enums.GameMenuCommands.GameMap.getMatcher(command)!=null){
                            matcher = models.enums.GameMenuCommands.GameMap.getMatcher(command);
                            String Map = matcher.group(1);
                            int mapNumber = Integer.parseInt(matcher.group(1));
                            Result result  = controller.selectMapForCreateNewGame(mapNumber, players.get(i) ,
                                    startXForMap[i] , startYForMap[i]);
                            System.out.println(result);
                            if(result.getSuccessful()){

                                break;
                            }


                        }
                        else {
                            System.out.println("invalid command , please try again!");
                        }


                    }



                }
                System.out.println(controller.createNewGame(players));

            }
        }
        else {
            System.out.println("invalid command");
        }

    }

}
