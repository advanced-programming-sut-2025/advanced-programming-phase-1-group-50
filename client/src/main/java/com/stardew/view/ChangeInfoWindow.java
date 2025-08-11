package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.model.Result;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.awt.*;
import java.util.HashMap;

public class ChangeInfoWindow extends CloseableWindow {
    private final TextField textField;
    private final TextButton button;
    private final AppMenu appMenu;

    public ChangeInfoWindow(Stage stage , String title , AppMenu appMenu) {
        super(title, stage);



        this.appMenu = appMenu;

        setSize(600, 400);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        pad(15, 10, 15, 10);
        setColor(Color.ORANGE);
        align(Align.top);

        textField = new TextField("" , GamePictureManager.skin);
        button = new TextButton("Execute", GamePictureManager.skin);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                executeChange(title);
            }
        });

        Table buttonTable = new Table();
        buttonTable.add(textField).padTop(10).row();
        buttonTable.add(button).padTop(10).row();


        add(buttonTable).center();

        if(title.equals("Show UserInfo")){
            textField.setDisabled(true);
            textField.setVisible(false);
        }
    }

    public void executeChange(String title){
        switch (title) {
            case "Change Password":
                String password = textField.getText();
                if(password.isEmpty()) return;
                new Thread(() -> {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("password", password);
                    Message m = new Message(map , MessageType.PROFILE_CHANGE_PASSWORD);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(m  ,  500);
                    if ( response != null && response.getType() == MessageType.PROFILE_CHANGE_PASSWORD_RESULT){
                        Result result = response.getFromBody("result" , Result.class);
                        Gdx.app.postRunnable(()->{
                            appMenu.showResult(result);
                        });

                    }

                }).start();
                break;
            case "Change Username":
                String username = textField.getText();
                if(username.isEmpty()) return;
                new Thread(() -> {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("newUsername",username );
                    Message m = new Message(map , MessageType.PROFILE_CHANGE_USERNAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(m  ,  500);
                    if ( response != null && response.getType() == MessageType.PROFILE_CHANGE_USERNAME_RESULT){
                        Result result = response.getFromBody("result" , Result.class);
                        appMenu.showResult(result);
                        if(result.getSuccessful()) LoggedInUser.setUsername(username);
                    }


                }).start();
                break;
            case "Change Email":
                String email = textField.getText();
                if(email.isEmpty()) return;
                new Thread(() -> {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("email",email );
                    Message m = new Message(map , MessageType.PROFILE_CHANGE_EMAIL);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(m  ,  500);
                    if ( response != null && response.getType() == MessageType.PROFILE_CHANGE_EMAIL_RESULT){
                        Result result = response.getFromBody("result" , Result.class);

                        appMenu.showResult(result);


                    }
                }).start();
                break;

            case "nickname":
                String nickname = textField.getText();

                if(nickname.isEmpty()) return;
                new Thread(() -> {
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("nickname",nickname );
                    Message m = new Message(map , MessageType.PROFILE_CHANGE_NICKNAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(m  ,  500);
                    if ( response != null && response.getType() == MessageType.PROFILE_CHANGE_NICKNAME_RESULT){
                        Result result = response.getFromBody("result" , Result.class);
                        appMenu.showResult(result);

                    }
                }).start();
                break;

            case "Show UserInfo":
                new Thread(() -> {
                    HashMap<String,Object> map = new HashMap<>();
                    Message m = new Message(map , MessageType.PROFILE_SHOW_USER_INFO);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(m  ,  500);
                    if ( response != null && response.getType() == MessageType.PROFILE_SHOW_USER_INFO_RESULT){
                        Result result = response.getFromBody("result" , Result.class);
                        appMenu.showResult(result);
                    }
                }).start();
                break;
        }
    }
}
