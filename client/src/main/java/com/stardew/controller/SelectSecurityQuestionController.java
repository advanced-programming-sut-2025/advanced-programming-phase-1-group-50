package com.stardew.controller;

import com.stardew.models.app.App;
import com.stardew.models.app.SecurityQuestion;
import com.stardew.models.userInfo.Gender;
import com.stardew.models.userInfo.User;
import com.stardew.network.Message;

public class SelectSecurityQuestionController {
    String username; String password; String nickname; String email; Gender gender;
    String question; String answer;
    public void handleRegisterFinally(Message message) {
//        String username;

        SecurityQuestion sq = new SecurityQuestion(question , answer);

//        User user = new User(username , password , nickname , email , gender , sq);
//        App.users.add(user);


    }
}
