package com.stardew.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.stardew.Main;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.PasswordUtil;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;
import com.stardew.models.userInfo.Gender;
import com.stardew.models.userInfo.User;
import com.stardew.network.Message;
import com.stardew.view.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginAndRegisterController {
    private final PasswordUtil passwordUtil = new PasswordUtil();
    private LoginAndRegisterMenu loginAndRegisterMenu;


    public void setView(LoginAndRegisterMenu loginAndRegisterMenu) {
        this.loginAndRegisterMenu = loginAndRegisterMenu;
    }

    public User findUser(String username) {
        for (User user : App.users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkRepeatedUsername(String username) {
        for (User u : App.users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidEmail(String email) {
        if (email == null || email.chars().filter(ch -> ch == '@').count() != 1) {
            return false;
        }


        String[] parts = email.split("@");
        if (parts.length != 2) return false;

        String local = parts[0];
        String domain = parts[1];


        String localRegex = "^(?!\\.)(?!.*\\.\\.)[a-zA-Z0-9._-]+(?<!\\.)$";
        if (!local.matches(localRegex)) return false;


        String domainRegex = "^(?!-)[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$";
        if (!domain.matches(domainRegex)) return false;


        String invalidChars = "[<>\"\\[\\]{}()\\\\,;:\\s!%^*+=?/|#$&]";
        return !email.matches(".*" + invalidChars + ".*");
    }

    public boolean isaValidPasswordLength(String password) {
        return password.length() >= 8;
    }

    public boolean hasUpperCasePassword(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) return true;

        }
        return false;
    }

    public boolean hasLowerCasePassword(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) return true;

        }
        return false;
    }

    public boolean hasSpecialCharacters(String password) {
        String specialCharacters = "?><,\"';:\\/|][}{+=)(*&^%$#!";
        for (char ch : password.toCharArray()) {
            if (specialCharacters.indexOf(ch) >= 0) {
                return true;
            }
        }
        return false;
    }

    public Result register(String username, String password, String passwordConfirm, String nickname, String email,
                           String gender) {

        Matcher matcher;
        if (checkRepeatedUsername(username)) {
            return new Result(false, "Username is already taken");
        }

        String UsernameRegex = "^[a-zA-Z0-9-]{3,16}$";
        matcher = Pattern.compile(UsernameRegex).matcher(username);
        if (!matcher.matches()) {
            return new Result(false, "invalid username format");
        }

        if (!isValidEmail(email)) {
            return new Result(false, "Email is invalid");
        }

        String passwordRegex = "^[a-zA-Z0-9?><,\"';:\\/|\\]\\[}{+=)(*&^%$#!]+";
        matcher = Pattern.compile(passwordRegex).matcher(password);
        if (!matcher.matches()) {
            return new Result(false, "invalid password format");
        }
        if (!isaValidPasswordLength(password)) {
            return new Result(false, "Password is too short");
        }

        if (!hasUpperCasePassword(password)) {
            return new Result(false, "please use Upper Case Letter");
        }

        if (!hasLowerCasePassword(password)) {
            return new Result(false, "please use Lower Case Letter");
        }
        if (!hasSpecialCharacters(password)) {
            return new Result(false, "please use Special Characters");
        }
        if(!password.equals(passwordConfirm)) {
            return new Result(false, "passwords do not match");
        }

        Gender g;
        try {
            g = Gender.valueOf(gender.trim());
        } catch (IllegalArgumentException e) {
            return new Result(false, "Invalid gender");
        }

        return new Result(true, "now we want to select security question");


    }

    public Result login(String username, String password) {
        if (findUser(username) == null) {
            return new Result(false, "user not found");
        }
        if (!findUser(username).getPassword().equals(passwordUtil.hashPassword(password))) {
            return new Result(false, "wrong password");

        }
        App.setLoggedInUser(findUser(username));
        App.setMenu(Menus.MainMenu);
        return new Result(true, "user logged in");
    }

    public void handleRegister(Message message) {
        String username = message.getFromBody("username");
        String password = message.getFromBody("password");
        String confirmPassword = message.getFromBody("confirmPassword");
        String nickname = message .getFromBody("nickname");
        String email = message.getFromBody("email");
        String gender = message.getFromBody("gender");

        Gender genderEnum;
        try{
             genderEnum = Gender.valueOf(gender);
        }
        catch(IllegalArgumentException e){
            return;
        }


        Result registerResult = register(username , password , confirmPassword , nickname , email , gender);

        if(registerResult.getSuccessful()){
//            Screen currentScreen = Main.getMain().getScreen();
//            SelectSecurityQuestionController selectSecurityQuestionController = new SelectSecurityQuestionController();
//            SelectSecurityQuestionMenu sqMenu = new SelectSecurityQuestionMenu(selectSecurityQuestionController , username , password , nickname , email , genderEnum);
//            Main.getMain().setScreen(sqMenu);
//            currentScreen.dispose();
        }
        else{
            Dialog dialog = new Dialog("error" , GamePictureManager.skin);
            dialog.getContentTable().add(new Label(registerResult.getMessage(), GamePictureManager.skin));
            dialog.getContentTable().getCell(dialog.getContentTable().getChildren().first())
                .getActor().setColor(Color.RED);
            dialog.setColor(1, 234 , 54 , 70);

            dialog.button("OK");
            dialog.show(loginAndRegisterMenu.getStage());
        }



    }


//    public void handleLogin(){
//        String username = loginAndRegisterMenu.getUsernameInputTextField().getText();
//        String password = loginAndRegisterMenu.getPasswordInputTextField().getText();
//        Result loginResult = login(username , password);
//        if(loginResult.getSuccessful()){
//            Screen currentScreen = Main.getMain().getScreen();
//            MainMenu mainMenu = new MainMenu();
//            Main.getMain().setScreen(mainMenu);
//            currentScreen.dispose();
//            //TODO : enter main menu , first we should create main menu
//        }
//        else {
//            Dialog loginError = new Dialog("error" , GamePictureManager.skin);
//            loginError.getContentTable().add(new Label(loginResult.getMessage(), GamePictureManager.skin));
//            loginError.getContentTable().getCell(loginError.getContentTable().getChildren().first()).getActor().setColor(Color.RED);
//            loginError.button("OK");
//            loginError.show(loginAndRegisterMenu.getStage());
//        }
//    }

    public void handleForgetPassword(){
        String username = loginAndRegisterMenu.getUsernameInputTextField().getText();
        Screen currentScreen = Main.getMain().getScreen();
        ForgetPasswordController forgetPasswordController = new ForgetPasswordController();
        ForgetPasswordMenu forgetPasswordMenu = new ForgetPasswordMenu(forgetPasswordController , username);
        Main.getMain().setScreen(forgetPasswordMenu);
        currentScreen.dispose();

    }

}
