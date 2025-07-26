package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.gameApp.App;
import com.stardew.model.userInfo.Gender;
import com.stardew.model.userInfo.PasswordUtil;
import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginAndRegisterController {
    private final PasswordUtil passwordUtil = new PasswordUtil();



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


    public void handleRegister(Message message, ClientConnectionThread connection) {
        String username = message.getFromBody("username");
        String password = message.getFromBody("password");
        String confirmPassword = message.getFromBody("confirmPassword");
        String nickname = message .getFromBody("nickname");
        String email = message.getFromBody("email");
        String gender = message.getFromBody("gender");

        Result registerResult = register(username , password , confirmPassword , nickname , email , gender);

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", registerResult);
        Message responseMessage = new Message(body, MessageType.REGISTER_RESULT);
        connection.sendMessage(responseMessage);

    }

    public void handleLogin(Message message, ClientConnectionThread connection){
        String username = message.getFromBody("username");
        String password = message.getFromBody("password");

        User user = App.getUserByUsername(username);
        HashMap<String, Object> body = new HashMap<>();

        if (user == null) {
            body.put("result", new Result(false, "user not found"));
        }
        else if (!user.getPassword().equals(passwordUtil.hashPassword(password))) {
            body.put("result", new Result(false, "wrong password"));

        }
        else {
            body.put("result", new Result(true, "user logged in"));
            body.put("username", user.getUsername());
            body.put("nickname", user.getNickname());
            connection.setUser(user);
        }

        Message responseMessage =  new Message(body, MessageType.LOGIN_RESULT);
        connection.sendMessage(responseMessage);
    }

}
