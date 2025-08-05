package com.stardew.controller.MiniGame;

import com.stardew.network.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MiniGameControllersManager {
    private static MiniGameControllersManager instance;
    private final Map<Integer, MiniGameController> controllers = new ConcurrentHashMap<>();
    private int currentID;

    private MiniGameControllersManager() {}

    public static synchronized MiniGameControllersManager getInstance() {
        if (instance == null) {
            instance = new MiniGameControllersManager();
        }
        return instance;
    }

    public int generateID() {
        return ++currentID;
    }

    public void addController(MiniGameController controller, int id) {
        controllers.put(id, controller);
    }

    public void removeController(int id) {
        controllers.remove(id);
    }



    public void handleMiniGameMessage(Message message) {
        int id = message.getIntFromBody("miniGame_ID");
        MiniGameController controller = controllers.get(id);
        if (controller == null) return;

        controller.handleMessages(message);
    }

}
