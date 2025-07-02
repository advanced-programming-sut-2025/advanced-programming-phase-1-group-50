package com.stardew.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.PasswordUtil;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.view.ProfileMenu;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.awt.*;
import java.util.regex.*;

public class ProfileMenuController {
    private final LoginAndRegisterController controller = new LoginAndRegisterController();
    private final PasswordUtil passwordUtil = new PasswordUtil();
    private ProfileMenu profileMenu;

    public void setView(ProfileMenu profileMenu) {
        this.profileMenu = profileMenu;
    }

    public Result changePassword(String oldPas, String newPas) {
        Matcher matcher;
        if (!App.getLoggedInUser().getPassword().equals(passwordUtil.hashPassword(oldPas))) {
            return new Result(false, "password is incorrect");
        }
        if (newPas.equals(oldPas)) {
            return new Result(false, "enter a new password");
        }
        String passwordRegex = "^[a-zA-Z0-9?><,\"';:\\/|\\]\\[}{+=)(*&^%$#!]+";
        matcher = Pattern.compile(passwordRegex).matcher(newPas);
        if (!matcher.matches()) {
            return new Result(false, "invalid password format");
        }
        if (!controller.isaValidPasswordLength(newPas)) {
            return new Result(false, "Password is too short");
        }

        if (!controller.hasUpperCasePassword(newPas)) {
            return new Result(false, "please use Upper Case Letter");
        }

        if (!controller.hasLowerCasePassword(newPas)) {
            return new Result(false, "please use Lower Case Letter");
        }
        if (!controller.hasSpecialCharacters(newPas)) {
            return new Result(false, "please use Special Characters");
        }
        App.getLoggedInUser().setPassword(passwordUtil.hashPassword(newPas));
        return new Result(true, "password cahnged succsessfuly");

    }

    public Result changeUsername(String username) {
        if (App.getLoggedInUser().getUsername().equals(username)) {
            return new Result(false, "enter a new username");
        }
        if (controller.checkRepeatedUsername(username)) {
            return new Result(false, "username is already taken");
        }
        Matcher matcher;
        String UsernameRegex = "^[a-zA-Z0-9-]{3,16}$";
        matcher = Pattern.compile(UsernameRegex).matcher(username);
        if (!matcher.matches()) {
            return new Result(false, "invalid username format");
        }
        App.getLoggedInUser().setUsername(username);
        return new Result(true, "username changed succsessfuly");
    }

    public Result changeNickname(String newNickname) {
        if (App.getLoggedInUser().getNickname().equals(newNickname)) {
            return new Result(false, "enter a new nickname");
        }
        App.getLoggedInUser().setNickname(newNickname);
        return new Result(true, "nickname changed succsessfuly");
    }

    public Result changeEmail(String email) {
        if (App.getLoggedInUser().getEmail().equals(email)) {
            return new Result(false, "enter a new email");
        }
        if (!controller.isValidEmail(email)) {
            return new Result(false, "invalid email format");
        }
        App.getLoggedInUser().setEmail(email);
        return new Result(true, "email changed succsessfuly");
    }

    public Result showUserInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Username : " + App.getLoggedInUser().getUsername() + "\n");
        sb.append("Nickname : " + App.getLoggedInUser().getNickname() + "\n");
        sb.append("Highest money earned in the game : " + App.getLoggedInUser().getHighestScore() + "\n");
        sb.append("Number of games : " + App.getLoggedInUser().getNumberOfGames() + "\n");

        return new Result(true, sb.toString());

    }

    public void handleChangePassword(){
        TextField oldPassword = new TextField("change password", GamePictureManager.skin);
        Dialog dialog = new Dialog("enter your password here" , GamePictureManager.skin){
            protected void result(Object object) {
                if((boolean)object){
                    String password = oldPassword.getText();
                    Dialog error;
                    boolean re = true;
                    String message = "";

                    if(!controller.isaValidPasswordLength(password)){
                        re = false;
                        message = "Password is too short";

                    }
                    if(!controller.hasUpperCasePassword(password)){
                        re = false;
                        message = "use Upper Case Letter";
                    }
                    if(!controller.hasLowerCasePassword(password)){
                        re = false;
                        message = "please use Lower Case Letter";
                    }
                    if(!controller.hasSpecialCharacters(password)){
                        re = false;
                        message = "please use Special Characters";

                    }
                    if(re){
                        message = "password changed successfully";
                    }
                    error = new Dialog("error" , GamePictureManager.skin);
                    Label messageLabel = new Label(message, GamePictureManager.skin);
                    messageLabel.setColor(Color.RED);
                    messageLabel.setFontScale(1.1f);


                    error.getContentTable().add(messageLabel).pad(20).row();


                    error.button("OK");


                    error.center();
                    error.show(profileMenu.getStage());
                }
            }

        };
        //final TextField passwordField = new TextField("", GamePictureManager.skin);


        Label label = new Label("" , GamePictureManager.skin);
        label.setColor(Color.RED);
        dialog.getContentTable().add(label).padBottom(10).row();

        dialog.getContentTable().row();
        dialog.getContentTable().add(oldPassword).width(200);

        dialog.button("OK", true);
        dialog.button("Cancel", false);
        dialog.show(profileMenu.getStage());

    }

    public void handleChangeUsername(){
        TextField changeUsername = new TextField("change username", GamePictureManager.skin);
        Dialog dialog = new Dialog("enter your new username" , GamePictureManager.skin){
            protected void result(Object object) {
                if((boolean)object){
                    Dialog error = new Dialog("error" , GamePictureManager.skin);
                    Label messageLabel = new Label("", GamePictureManager.skin);
                    messageLabel.setColor(Color.RED);
                    messageLabel.setFontScale(1.1f);


                    error.getContentTable().add(messageLabel).pad(20).row();


                    error.button("OK");


                    error.center();
                    Result changeUsernameResult = changeUsername(changeUsername.getText());
                    if(changeUsernameResult.getSuccessful()){
                        error.text("congratulations!");
                    }
                    else{
                        error.text("Something went wrong");
                    }
                    messageLabel.setText(changeUsernameResult.getMessage());
                    error.show(profileMenu.getStage());


                }
            }
        };
        Label label = new Label("" , GamePictureManager.skin);
        label.setColor(Color.RED);
        dialog.getContentTable().add(label).padBottom(10).row();

        dialog.getContentTable().row();
        dialog.getContentTable().add(changeUsername).width(200);

        dialog.button("OK", true);
        dialog.button("Cancel", false);
        dialog.show(profileMenu.getStage());
    }


}
