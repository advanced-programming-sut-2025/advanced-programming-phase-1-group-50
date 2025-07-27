package com.stardew.model.gameApp;

import com.stardew.model.userInfo.Gender;
import com.stardew.model.userInfo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class App {
    public static final List<User> users = Collections.synchronizedList(new ArrayList<>());
    private static final Map<Integer, Game> games = new ConcurrentHashMap<>();

    static {
        User u = new User("ali", "wwwwww", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u);
        User u2 = new User("mamad","wwwwww", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u2);
        User u3 = new User("hossein","ksdfkklf", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u3);
        User u4 = new User("sina","ksdfkklf", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u4);
        User u5 = new User("hasan","ksdfkklf", "fkmd", "emua@dfjk.com", Gender.Male, new SecurityQuestion("what is your favorite color?", "answer"));
        users.add(u5);
    }


    public static User getUserByUsername(String username) {
        synchronized (users) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        }
        return null;
    }


    public static Game getGame(int id) {
        return games.get(id);
    }

    public static void addGame(int id, Game game) {
        games.put(id, game);
    }

    public static void removeGame(int id) {
        games.remove(id);
    }


}
