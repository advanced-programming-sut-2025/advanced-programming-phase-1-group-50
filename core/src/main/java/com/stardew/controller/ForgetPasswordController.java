package com.stardew.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew.Main;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.User;
import com.stardew.view.ForgetPasswordMenu;
import com.stardew.view.LoginAndRegisterMenu;
import com.stardew.view.MainMenu;

public class ForgetPasswordController {
    private ForgetPasswordMenu menu;
    private LoginAndRegisterController loginAndRegisterController;

    public void setView(ForgetPasswordMenu menu) {
        this.menu = menu;
    }


    public User findUserByUsername(String username){
        for(User u : App.users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    public void handleForgetPassword() {
        String username = menu.getUsernameTextField().getText();
        String answer = menu.getAnswerTextField().getText();
        if(findUserByUsername(username) == null){
            Dialog usernameFindError = new Dialog("error" , GamePictureManager.skin);
            usernameFindError.getContentTable().add(new Label("user not found", GamePictureManager.skin));
            usernameFindError.getContentTable().getCell(usernameFindError.getContentTable().getChildren().first())
                .getActor().setColor(Color.RED);
            usernameFindError.setColor(1, 234 , 54 , 70);

            usernameFindError.button("OK");
            usernameFindError.show(menu.getStage());

        }
        User u = findUserByUsername(username);
        if(!answer.equals(u.getSecurityQuestion().getAnswer())){
            Dialog answerError = new Dialog("error" , GamePictureManager.skin);
            answerError.getContentTable().add(new Label("answer is incorrect", GamePictureManager.skin));
            answerError.getContentTable().getCell(answerError.getContentTable().getChildren().first())
                .getActor().setColor(Color.RED);
            answerError.setColor(1, 234 , 54 , 70);

            answerError.button("OK");
            answerError.show(menu.getStage());
        }

        String password = menu.getEnterNewPasswordTextField().getText();
        boolean success = true;
        String message = null;
        if(!loginAndRegisterController.isaValidPasswordLength(password)){
            message = "Password length must be between 6 and 20 characters";
            success = false;
        }
        if(!loginAndRegisterController.hasLowerCasePassword(password)){
            message = "use lower case letters";
            success = false;
        }
        if(!loginAndRegisterController.hasUpperCasePassword(password)){
            message = "use upper case letters";
            success = false;
        }
        if(!loginAndRegisterController.hasSpecialCharacters(password)){
            message = "use special characters";
            success = false;
        }

        if(!success){
            Dialog loginError = new Dialog("error" , GamePictureManager.skin);
            loginError.getContentTable().add(new Label("error", GamePictureManager.skin));
            loginError.getContentTable().getCell(loginError.getContentTable().getChildren().first()).getActor().setColor(Color.RED);
            loginError.text(message);
            loginError.button("OK");
            loginError.show(menu.getStage());
        }

        findUserByUsername(username).setPassword(password);
        Screen screen = Main.getMain().getScreen();

        LoginAndRegisterMenu loginAndRegisterMenu = new LoginAndRegisterMenu(GamePictureManager.skin);
        Main.getMain().setScreen(loginAndRegisterMenu);
        screen.dispose();



    }
}
