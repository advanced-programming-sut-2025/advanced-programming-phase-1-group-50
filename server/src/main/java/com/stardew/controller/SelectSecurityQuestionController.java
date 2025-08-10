package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.gameApp.App;
import com.stardew.model.gameApp.SecurityQuestion;
import com.stardew.model.userInfo.Gender;
import com.stardew.model.userInfo.PasswordUtil;
import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.repository.UserDAO;

import java.util.HashMap;

public class SelectSecurityQuestionController {

    public void handleRegisterFinally(Message message, ClientConnectionThread connection) {
        String username = message.getFromBody("username");
        String password = message.getFromBody("password");
        String nickname = message.getFromBody("nickname");
        String email = message.getFromBody("email");
        String gender = message.getFromBody("gender");
        String question = message.getFromBody("question");
        String answer = message.getFromBody("answer");


        SecurityQuestion sq = new SecurityQuestion(question , answer);

        PasswordUtil passwordUtil = new PasswordUtil();
        User user = new User(username , passwordUtil.hashPassword(password) , nickname , email , Gender.valueOf(gender) , sq);

        HashMap<String, Object> body = new HashMap<>();

        try {
            boolean success = UserDAO.getInstance().insertUser(user);
            if (success) {
                body.put("result", new Result(true, "Registered Successfully"));
            } else {
                body.put("result", new Result(false, "Failed to register user"));
            }
        } catch (Exception e) {
            body.put("result", new Result(false, "Error in register user: " + e.getMessage()));
        }

        Message responseMessage = new Message(body, MessageType.REGISTER_RESULT);
        responseMessage.setRequestID(message.getRequestID());
        connection.sendMessage(responseMessage);

    }
}
