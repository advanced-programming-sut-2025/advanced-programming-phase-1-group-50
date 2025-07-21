package com.stardew.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.stardew.Main;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.User;
import com.stardew.view.CreateNewGameMenu;
import com.stardew.view.SelectFarmMenu;

import java.util.ArrayList;

public class CreateNewGameController {
    private CreateNewGameMenu view;
    private GameMenuController controller;


    public void setView(CreateNewGameMenu view) {
        this.view = view;
    }

    public User findUserByName(String name){
        for(User u : App.users){
            if(u.getUsername().equals(name)){
                return u;
            }
        }
        return null;
    }

    public void handleStartNewGame(ArrayList<Player> players){
        String username1 = view.getUsername4().getText();
        String username2 = view.getUsername2().getText();
        String username3 = view.getUsername3().getText();
        Dialog UsernameError = new Dialog("error" , GamePictureManager.skin);
        StringBuilder sb = new StringBuilder();
        boolean result = true;


        if(findUserByName(username1)==null){
            sb.append(username1).append(" not found").append("\n");
            result = false;
        }

        if(findUserByName(username2)==null){
            sb.append(username2).append(" not found").append("\n");
            result = false;
        }

        if(findUserByName(username3)==null){
            sb.append(username3).append(" not found").append("\n");
            result = false;
        }

        if(result){
            UsernameError.text("game started successfully , please choose your map");
            UsernameError.button("ok");
            UsernameError.show(view.getStage());
            User u1 = findUserByName(username1);
            User u2 = findUserByName(username2);
            User u3 = findUserByName(username3);
            User u = App.getLoggedInUser();
            Player p = new Player(u.getUsername() , u.getNickname() , u);
            Player p1 = new Player(u1.getUsername() , u1.getNickname() , u1);
            Player p2 = new Player(u2.getUsername() , u2.getNickname() , u2);
            Player p3 = new Player(u3.getUsername() , u3.getNickname() , u3);
            players.add(p);
            players.add(p1);
            players.add(p2);
            players.add(p3);




            Screen screen = Main.getMain().getScreen();
            SelectFarmMenu selectFarmMenu = new SelectFarmMenu(players);
            Main.getMain().setScreen(selectFarmMenu);
            screen.dispose();


        }
        else {
            UsernameError.text(sb.toString());
            UsernameError.button("ok");
            UsernameError.show(view.getStage());
        }





    }
}
