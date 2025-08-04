package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.userInfo.PasswordUtil;
import com.stardew.model.userInfo.User;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

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
        if (!user.getPassword().equals(passwordUtil.hashPassword(oldPas))) {
            return new Result(false, "password is incorrect");
        }
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
        user.setPassword(newPas);
        return new Result(true, "password cahnged succsessfuly");

    }

    public void handleChangePassword(Message message , ClientConnectionThread connection , User user) {
        String oldPassword = user.getPassword();
        String newPassword = message.getFromBody("password");
        Result result = changePassword(oldPassword, newPassword, user);
        HashMap<String , Object> body = new HashMap<> ();
        body.put("result", result);
        Message m = new Message(body , MessageType.PROFILE_CHANGE_PASSWORD_RESULT);
        m.setRequestID(message.getRequestID());
        connection.sendMessage(m);
    }
}
