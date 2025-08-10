package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.ServerApp;
import com.stardew.model.gameApp.App;
import com.stardew.model.userInfo.PasswordUtil;
import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileController {
    private static ProfileController instance ;

    private ProfileController() {

    }

    public static ProfileController getInstance() {
        if (instance == null) {
            instance = new ProfileController();
        }
        return instance;
    }

    private final PasswordUtil passwordUtil = new PasswordUtil();
    private final LoginAndRegisterController controller = new
        LoginAndRegisterController();
    public Result changePassword(String oldPas, String newPas , User user) {
        Matcher matcher;
//        if (!user.getPasswordHash().equals(passwordUtil.hashPassword(oldPas))) {
//            return new Result(false, "password is incorrect");
//        }
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
        user.setPasswordHash(newPas);
        return new Result(true, "password cahnged succsessfuly");

    }

    public void handleChangePassword(Message message , ClientConnectionThread connection ) {
        String username = message.getFromBody("username");
        User user = App.getUserByUsername(username);
        if(user == null) return;
        String oldPassword = user.getPasswordHash();
        String newPassword = message.getFromBody("password");

        Result result = changePassword(oldPassword, newPassword, user);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_CHANGE_PASSWORD_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);
    }


    public void handleChangeUsername(Message message , ClientConnectionThread connection ) {
        String username = message.getFromBody("username");
        User user = App.getUserByUsername(username);
        if(user == null) return;
        String newUsername = message.getFromBody("newUsername");
        Result result = changeUsername(newUsername , user);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        body.put("newUsername", newUsername);
        Message m = new Message(body , MessageType.PROFILE_CHANGE_USERNAME_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);

        ArrayList<String> onlineUsers = ServerApp.getOnlineUsernames();

        HashMap<String, Object> body2 = new HashMap<>();
        body2.put("onlineUsers", onlineUsers);
        Message updateMsg = new Message(body2, MessageType.SEND_ONLINE_USERS_RESULT);

        synchronized (ServerApp.getClientConnectionThreads()) {
            for (ClientConnectionThread c : ServerApp.getClientConnectionThreads()) {
                c.sendMessage(updateMsg);
            }
        }



    }


    public Result changeUsername(String username , User user) {
        if (user.getUsername().equals(username)) {
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
        user.setUsername(username);
        return new Result(true, "username changed succsessfuly");
    }

    public void handleChangeEmail(Message message , ClientConnectionThread connection ) {
        String username = message.getFromBody("username");
        User user = App.getUserByUsername(username);
        if(user == null) return;
        String email = message.getFromBody("email");
        Result result = changeEmail(email, user);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_CHANGE_EMAIL_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);

    }


    public Result changeEmail(String email , User user) {
        if (user.getEmail().equals(email)) {
            return new Result(false, "enter a new email");
        }
        if (!controller.isValidEmail(email)) {
            return new Result(false, "invalid email format");
        }
        user.setEmail(email);
        return new Result(true, "email changed succsessfuly");
    }


    public void handleChangeNickname(Message message , ClientConnectionThread connection ) {
        String username = message.getFromBody("username");
        User user = App.getUserByUsername(username);
        if(user == null) return;
        String nickname = message.getFromBody("nickname");
        Result result = changeNickname(nickname, user);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_CHANGE_NICKNAME_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);

    }

    public Result changeNickname(String newNickname , User user) {
        if (user.getNickname().equals(newNickname)) {
            return new Result(false, "enter a new nickname");
        }
        user.setNickname(newNickname);
        return new Result(true, "nickname changed succsessfuly");
    }


    public void handelShowUserInfo(Message message , ClientConnectionThread connection ) {
        String username = message.getFromBody("username");
        User user = App.getUserByUsername(username);
        if(user == null) return;
        Result result = showUserInfo(user);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_SHOW_USER_INFO_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);

    }

    public Result showUserInfo(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("Username : ").append(user.getUsername()).append("\n");
        sb.append("Nickname : ").append(user.getNickname()).append("\n");
        sb.append("Email : ").append(user.getEmail()).append("\n");
        sb.append("Highest money earned in the game : ").append(user.getHighestScore()).append("\n");
        sb.append("Number of games : ").append(user.getNumberOfGames()).append("\n");

        return new Result(true, sb.toString());

    }
}
