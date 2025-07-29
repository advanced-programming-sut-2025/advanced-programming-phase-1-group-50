package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.gameApp.App;
import com.stardew.model.gameApp.SecurityQuestion;
import com.stardew.model.userInfo.Gender;
import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

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

        User user = new User(username , password , nickname , email , Gender.valueOf(gender) , sq);
        App.users.add(user);

        HashMap<String, Object> body = new HashMap<>();
        body.put("result", new Result(true, "Registered Successfully"));
        Message responseMessage = new Message(body, MessageType.REGISTER_RESULT);
        responseMessage.setRequestID(message.getRequestID());
        connection.sendMessage(responseMessage);

    }
}
