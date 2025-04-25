package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;
import models.userInfo.*;
import models.Result;
import controller.GameMenuController;
public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();
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
                            Result result  = controller.selectMapForCreateNewGame(Map, players.get(i));
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
        else {
            System.out.println("invalid command");
        }

    }

}
