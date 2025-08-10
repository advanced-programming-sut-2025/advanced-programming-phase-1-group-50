package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.userInfo.PasswordUtil;
import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.repository.UserDAO;

import java.sql.SQLException;
import java.util.HashMap;

public class ForgetPasswordController {
    private final LoginAndRegisterController loginAndRegisterController = new LoginAndRegisterController();


    public void handleGetSecurityQuestion(Message message, ClientConnectionThread connection) {
        String username = message.getFromBody("username");
        User user = null;
        try {
            user = UserDAO.getInstance().findByUsername(username);
        } catch (SQLException ignored) {}

        HashMap<String, Object> body = new HashMap<>();

        if(user == null) {
            body.put("success", false);
            body.put("message", "No User found");
            body.put("securityQuestion", "");
            Message responseMessage = new Message(body, MessageType.GET_SECURITY_QUESTION_RESULT);
            responseMessage.setRequestID(message.getRequestID());
            connection.sendMessage(responseMessage);
            return;
        }

        body.put("success", true);
        body.put("message", "Security Question Found");
        body.put("securityQuestion", user.getSecurityQuestion().getQuestion());
        Message responseMessage = new Message(body, MessageType.GET_SECURITY_QUESTION_RESULT);
        responseMessage.setRequestID(message.getRequestID());
        connection.sendMessage(responseMessage);
    }


    public void handleForgetPassword(Message message, ClientConnectionThread connection) {
        String username = message.getFromBody("username");
        String answer = message.getFromBody("answer");
        String newPassword = message.getFromBody("newPassword");
        PasswordUtil passwordUtil = new PasswordUtil();

        User user = null;
        try {
            user = UserDAO.getInstance().findByUsername(username);
        } catch (SQLException ignored) {}

        HashMap<String, Object> body = new HashMap<>();
        if(user == null){
           body.put("result", new Result(false, "User not found"));
           Message responseMessage = new Message(body, MessageType.FORGET_PASSWORD_RESULT);
            responseMessage.setRequestID(message.getRequestID());
           connection.sendMessage(responseMessage);
           return;
        }

        if(!answer.equals(user.getSecurityQuestion().getAnswer())){
            body.put("result", new Result(false, "answer is incorrect"));
            Message responseMessage = new Message(body, MessageType.FORGET_PASSWORD_RESULT);
            responseMessage.setRequestID(message.getRequestID());
            connection.sendMessage(responseMessage);
            return;
        }
        if (user.getPasswordHash().equals(passwordUtil.hashPassword(newPassword))) {
            body.put("result", new Result(false, "new password is equal to old password"));
            Message responseMessage = new Message(body, MessageType.FORGET_PASSWORD_RESULT);
            responseMessage.setRequestID(message.getRequestID());
            connection.sendMessage(responseMessage);
            return;
        }

        boolean success = true;
        String contentMessage = null;
        if(!loginAndRegisterController.isaValidPasswordLength(newPassword)){
            contentMessage = "Password length must be between 6 and 20 characters";
            success = false;
        }
        else if(!loginAndRegisterController.hasLowerCasePassword(newPassword)){
            contentMessage = "use lower case letters";
            success = false;
        }
        else if(!loginAndRegisterController.hasUpperCasePassword(newPassword)){
            contentMessage = "use upper case letters";
            success = false;
        }
        else if(!loginAndRegisterController.hasSpecialCharacters(newPassword)){
            contentMessage = "use special characters";
            success = false;
        }

        if(!success){
            body.put("result", new Result(false, contentMessage));
            Message responseMessage = new Message(body, MessageType.FORGET_PASSWORD_RESULT);
            responseMessage.setRequestID(message.getRequestID());
            connection.sendMessage(responseMessage);
            return;
        }


        try {
            UserDAO.getInstance().updatePasswordHash(username, passwordUtil.hashPassword(newPassword));
            body.put("result", new Result(true, "Changed password"));
        } catch (SQLException e) {
            body.put("result", new Result(false, "Error in DB query: " + e.getMessage()));
        }
        Message responseMessage = new Message(body, MessageType.FORGET_PASSWORD_RESULT);
        responseMessage.setRequestID(message.getRequestID());
        connection.sendMessage(responseMessage);
    }
}
