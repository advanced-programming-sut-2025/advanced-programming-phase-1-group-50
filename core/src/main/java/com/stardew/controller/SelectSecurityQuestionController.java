package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.stardew.Main;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.app.SecurityQuestion;
import com.stardew.models.userInfo.Avatar;
import com.stardew.models.userInfo.Gender;
import com.stardew.models.userInfo.User;
import com.stardew.view.LoginAndRegisterMenu;
import com.stardew.view.SelectSecurityQuestionMenu;

public class SelectSecurityQuestionController {
    private SelectSecurityQuestionMenu menu;



    public void setView(SelectSecurityQuestionMenu menu) {
        this.menu = menu;
    }

    public void handleRegister(String username, String password , String nickname , String email , Gender gender) {
        String question = menu.getSecurityQuestionSelectBox().getSelected();
        String answer = menu.getAnswerTextField().getText();

        if(answer.isEmpty()) {
            Dialog dialog = new Dialog("Warning" , GamePictureManager.skin);
            dialog.getContentTable().add(new Label("you should fill answer", GamePictureManager.skin));
            dialog.getContentTable().getCell(dialog.getContentTable().getChildren().first())
                .getActor().setColor(Color.RED);
            dialog.setColor(1, 234 , 54 , 70);

            dialog.button("OK");
            dialog.show(menu.getStage());
        }

        SecurityQuestion sq = new SecurityQuestion(question , answer);

        User user = new User(username , password , nickname , email , gender , sq , Avatar.abigail);
        App.users.add(user);

        Dialog successfullyRegisterWithSq = new Dialog("", GamePictureManager.skin);
        successfullyRegisterWithSq.text("you registered successfully");
        successfullyRegisterWithSq.getColor().a = 0;
        successfullyRegisterWithSq.show(menu.getStage());
        float x = (Gdx.graphics.getWidth() - successfullyRegisterWithSq.getWidth()) / 2f;
        float y = 50;
        successfullyRegisterWithSq.setPosition(x, y);
        successfullyRegisterWithSq.addAction(Actions.fadeIn(1f));

        Screen currentScreen = Main.getMain().getScreen();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                successfullyRegisterWithSq.addAction(Actions.sequence(
                    Actions.fadeOut(1f),
                    Actions.run(() -> {
                        successfullyRegisterWithSq.hide();
                        LoginAndRegisterMenu lok = new LoginAndRegisterMenu(GamePictureManager.skin);
                        Main.getMain().setScreen(lok);
                        currentScreen.dispose();

                    })
                ));
            }
        }, 2);


    }
}
