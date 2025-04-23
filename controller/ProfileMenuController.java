package controller;

import models.*;
import models.app.App;
import java.util.regex.*;

public class ProfileMenuController {
    private final LoginAndRegisterController controller = new LoginAndRegisterController();
    public Result  changePassword(String oldPas , String newPas){
        Matcher matcher;
        if(!App.getLoggedInUser().getPassword().equals(oldPas)){
            return new Result(false, "password is incorrect");
        }
        if(newPas.equals(oldPas)){
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
        App.getLoggedInUser().setPassword(newPas);
        return new Result(true, "password cahnged succsessfuly");

    } 
    public Result changeUsername(String username ){
        if(App.getLoggedInUser().getUsername().equals(username)){
            return new Result(false , "enter a new username");
        }
        if(controller.checkRepeatedUsername(username)){
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

    public Result changeNickname(String newNickname){
        if(App.getLoggedInUser().getNickname().equals(newNickname)){
            return new Result(false, "enter a new nickname");
        }
        App.getLoggedInUser().setNickname(newNickname);
        return new Result(true, "nickname changed succsessfuly");
    }
    public Result changeEmail(String email){
        if(App.getLoggedInUser().getEmail().equals(email)){
            return new Result(false, "enter a new email");
        }
        if(!controller.isValidEmail(email)){
            return new Result(false , "invalid email format");
        }
        App.getLoggedInUser().setEmail(email);
        return new Result(true, "email changed succsessfuly");
    }
    public Result showUserInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Username : " + App.getLoggedInUser().getUsername() + "\n");
        sb.append("Nickname : " + App.getLoggedInUser().getNickname() + "\n");
        sb.append("Highest money earned in the game : " + App.getLoggedInUser().getHighestScore() + "\n");
        sb.append("Number of games : " + App.getLoggedInUser().getNumberOfGames() + "\n");

        return new Result(true, sb.toString());

    }
}
