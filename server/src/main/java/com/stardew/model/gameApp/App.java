package com.stardew.model.gameApp;

import com.stardew.model.userInfo.Gender;
import com.stardew.model.userInfo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static List<User> users = Collections.synchronizedList(new ArrayList<>());

    static {
        User u = new User("ali", "wwwwww", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u);
        User u2 = new User("mamad","wwwwww", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u2);
        User u3 = new User("hossein","ksdfkklf", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u3);
        User u4 = new User("sina","ksdfkklf", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u4);
    }


    public synchronized static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }


}
