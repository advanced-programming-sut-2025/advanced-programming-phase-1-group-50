package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.ServerApp;
import com.stardew.model.userInfo.PasswordUtil;
import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.repository.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileController {
    private static ProfileController instance ;
    private final PasswordUtil passwordUtil = new PasswordUtil();
    private final LoginAndRegisterController controller = new LoginAndRegisterController();

    private ProfileController() {}

    public static ProfileController getInstance() {
        if (instance == null) {
            instance = new ProfileController();
        }
        return instance;
    }


    public Result changePassword(User user, String newPas) {
        Matcher matcher;

        if (passwordUtil.hashPassword(newPas).equals(user.getPasswordHash())) {
            return new Result(false, "enter a new password, it is your previous password");
        }

        String passwordRegex = "^[a-zA-Z0-9?><,\"';:/|\\]\\[}{+=)(*&^%$@#!]+";
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

        try {
            user.setPasswordHash(passwordUtil.hashPassword(newPas));
            UserDAO.getInstance().updatePasswordHash(user.getUsername(), passwordUtil.hashPassword(newPas));
        } catch (SQLException e) {
            return new Result(false, "Error in DB query: " + e.getMessage());
        }

        return new Result(true, "password changed successfully");

    }

    public void handleChangePassword(Message message, ClientConnectionThread connection) {
        User user = connection.getUser();

        if(user == null) return;

        String newPassword = message.getFromBody("password");

        Result result = changePassword(user, newPassword);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_CHANGE_PASSWORD_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);
    }


    public void handleChangeUsername(Message message, ClientConnectionThread connection) {
        User user = connection.getUser();

        if(user == null) return;

        String newUsername = message.getFromBody("newUsername");
        Result result = changeUsername(user, newUsername);
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

    public Result changeUsername(User user, String newUsername) {
        if (user.getUsername().equals(newUsername)) {
            return new Result(false, "enter a new username");
        }
        if (controller.checkRepeatedUsername(newUsername)) {
            return new Result(false, "username is already taken");
        }
        Matcher matcher;
        String UsernameRegex = "^[a-zA-Z0-9-]{3,16}$";
        matcher = Pattern.compile(UsernameRegex).matcher(newUsername);
        if (!matcher.matches()) {
            return new Result(false, "invalid username format");
        }
        try {
            UserDAO.getInstance().updateUsername(user.getUsername(), newUsername);
            user.setUsername(newUsername);
        } catch (SQLException e) {
            return new Result(false, "Error in DB query: " + e.getMessage());
        }
        return new Result(true, "username changed successfully");
    }


    public void handleChangeEmail(Message message, ClientConnectionThread connection) {
        User user = connection.getUser();
        if(user == null) return;
        String newEmail = message.getFromBody("email");
        Result result = changeEmail(user, newEmail);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_CHANGE_EMAIL_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);

    }

    public Result changeEmail(User user, String newEmail) {
        if (user.getEmail().equals(newEmail)) {
            return new Result(false, "enter a new email");
        }
        if (!controller.isValidEmail(newEmail)) {
            return new Result(false, "invalid email format");
        }
        try {
            user.setEmail(newEmail);
            UserDAO.getInstance().updateEmail(user.getUsername(), newEmail);
        } catch (SQLException e) {
            return new Result(false, "Error in DB query: " + e.getMessage());
        }
        return new Result(true, "email changed successfully");
    }


    public void handleChangeNickname(Message message, ClientConnectionThread connection) {
        User user = connection.getUser();
        if(user == null) return;
        String newNickname = message.getFromBody("nickname");
        Result result = changeNickname(user, newNickname);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_CHANGE_NICKNAME_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);

    }

    public Result changeNickname(User user, String newNickname) {
        if (user.getNickname().equals(newNickname)) {
            return new Result(false, "enter a new nickname");
        }
        try {
            user.setNickname(newNickname);
            UserDAO.getInstance().updateNickname(user.getUsername(), newNickname);
        } catch (SQLException e) {
            return new Result(false, "Error in DB query: " + e.getMessage());
        }
        return new Result(true, "nickname changed successfully");
    }


    public void handelShowUserInfo(Message message, ClientConnectionThread connection) {
        User user = connection.getUser();
        if(user == null) return;
        Result result = showUserInfo(user);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_SHOW_USER_INFO_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);

    }

    public Result showUserInfo(User user) {
        String sb =
            "Username : " + user.getUsername() + "\n" +
            "Nickname : " + user.getNickname() + "\n" +
            "Email : " + user.getEmail() + "\n" +
            "Highest money earned in the game : " + user.getHighestScore() + "\n" +
            "Number of games : " + user.getNumberOfGames() + "\n";

        return new Result(true, sb);

    }
}
