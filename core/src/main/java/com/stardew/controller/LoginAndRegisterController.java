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
import com.stardew.models.PasswordUtil;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;
import com.stardew.models.app.SecurityQuestion;
import com.stardew.models.enums.LoginMenuCommands;
import com.stardew.models.userInfo.Gender;
import com.stardew.models.userInfo.User;
import com.stardew.view.*;

import java.security.SecureRandom;
import java.util.Scanner;
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

    public SecurityQuestion findSecurityQuestion(int x) {
        if (x >= 1 && x <= App.securityQuestions.size()) {
            return App.securityQuestions.get(x - 1);
        }
        return null;
    }

    public String generatePassword() {
        String Characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?><,\"';:\\/|][}{+=)(*&^%$#!";
        SecureRandom rand = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(Characters.charAt(rand.nextInt(Characters.length())));
        }
        return sb.toString();
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
        boolean randomPassword = false;
        Matcher matcher;
        if (!checkRepeatedUsername(username)) {
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

        if (password.equals("random") && passwordConfirm.equals("random")) {
            password = generatePassword();
            randomPassword = true;

        } else {
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
        }
        Gender g;
        try {
            g = Gender.valueOf(gender.trim());
        } catch (IllegalArgumentException e) {
            return new Result(false, "Invalid gender");
        }
        if(randomPassword) {
            return new Result(true , "now we want to select security question, your random password : "
                    + password );
        }
        return new Result(true, "now we want to select security question");


    }

    public Result selectSecurityQuestion(String username, String password, String passwordConfirm, String nickname,
                                         String email,
                                         String gender, String number, String answer) {
        int counter;
        try {
            counter = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return new Result(false, "invalid number");
        }

        if (counter > App.securityQuestions.size()) return new Result(false, "please select a valid security question");
        SecurityQuestion question = App.securityQuestions.get(counter);
        SecurityQuestion s = new SecurityQuestion(question.getQuestion(), answer);
        App.users.add(new User(username, password, nickname, email, Gender.valueOf(gender), s));
        return new Result(true, "user registered successfully");


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

    public Result askSecurityQuestion(String username) {
        if (findUser(username) == null) {
            return new Result(false, "user not found");
        }
        SecurityQuestion sq = findUser(username).getSecurityQuestion();
        return new Result(true, sq.getQuestion());
    }

    public Result checkAnswerQuestion(String username, String answer) {
        if (findUser(username) == null) {
            return new Result(false, "user not found");
        }
        SecurityQuestion sq = findUser(username).getSecurityQuestion();
        if (!sq.getAnswer().equals(answer)) {
            return new Result(false, "wrong answer");
        }
        return new Result(true, "user answered successfully " + "your password : " + findUser(username).getPassword());
    }

    public Result setNewPasswordAfterForgetPassword(String username, String newPassword) {
        if (findUser(username) == null) {
            return new Result(false, "user not found");
        }
        String passwordRegex = "^[a-zA-Z0-9?><,\"';:\\/|\\]\\[}{+=)(*&^%$#!]+";
        Matcher matcher = Pattern.compile(passwordRegex).matcher(newPassword);
        if (!matcher.matches()) {
            return new Result(false, "invalid password format");
        }
        if (!isaValidPasswordLength(newPassword)) {
            return new Result(false, "Password is too short");
        }

        if (!hasUpperCasePassword(newPassword)) {
            return new Result(false, "please use Upper Case Letter");
        }

        if (!hasLowerCasePassword(newPassword)) {
            return new Result(false, "please use Lower Case Letter");
        }
        if (!hasSpecialCharacters(newPassword)) {
            return new Result(false, "please use Special Characters");
        }
        findUser(username).setPassword(passwordUtil.hashPassword(newPassword));
        return new Result(true, "user password changed successfully");
    }
    public Result showMenu(){
        return new Result(true,"login and register menu");
    }


    public void handleRegister(){
        String username = loginAndRegisterMenu.getUsernameTextField().getText();
        String password = loginAndRegisterMenu.getPasswordTextField().getText();
        String nickname = loginAndRegisterMenu.getNicknameTextField().getText();
        String email = loginAndRegisterMenu.getEmailTextField().getText();
        String confirmPassword = loginAndRegisterMenu.getConfirmPasswordTextField().getText();
        String gender = loginAndRegisterMenu.getGenderSelectBox().getSelected();
        System.out.println(password);
        Gender genderEnum;
        try{
             genderEnum = Gender.valueOf(gender);
        }
        catch(IllegalArgumentException e){
            return;
        }



        Result registerResult = register(username , password , confirmPassword , nickname , email , gender);
        System.out.println(registerResult.getSuccessful());
        if(registerResult.getSuccessful()){
            Screen currentScreen = Main.getMain().getScreen();
            SelectSecurityQuestionController selectSecurityQuestionController = new SelectSecurityQuestionController();
            SelectSecurityQuestionMenu sqMenu = new SelectSecurityQuestionMenu(selectSecurityQuestionController , username , password , nickname , email , genderEnum);
            Main.getMain().setScreen(sqMenu);
            currentScreen.dispose();
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

    public void handleRandomPassword(){
        String password = generatePassword();
        loginAndRegisterMenu.getPasswordTextField().setText(password);

    }


    public void handleLogin(){
        String username = loginAndRegisterMenu.getUsernameInputTextField().getText();
        String password = loginAndRegisterMenu.getPasswordInputTextField().getText();
        Result loginResult = login(username , password);
        if(loginResult.getSuccessful()){
            Screen currentScreen = Main.getMain().getScreen();
            MainMenu mainMenu = new MainMenu();
            Main.getMain().setScreen(mainMenu);
            currentScreen.dispose();
            //TODO : enter main menu , first we should create main menu
        }
        else {
            Dialog loginError = new Dialog("error" , GamePictureManager.skin);
            loginError.getContentTable().add(new Label(loginResult.getMessage(), GamePictureManager.skin));
            loginError.getContentTable().getCell(loginError.getContentTable().getChildren().first()).getActor().setColor(Color.RED);
            loginError.button("OK");
            loginError.show(loginAndRegisterMenu.getStage());
        }
    }

    public void handleForgetPassword(){
        String username = loginAndRegisterMenu.getUsernameInputTextField().getText();
        Screen currentScreen = Main.getMain().getScreen();
        ForgetPasswordController forgetPasswordController = new ForgetPasswordController();
        ForgetPasswordMenu forgetPasswordMenu = new ForgetPasswordMenu(forgetPasswordController , username);
        Main.getMain().setScreen(forgetPasswordMenu);
        currentScreen.dispose();

    }

}
