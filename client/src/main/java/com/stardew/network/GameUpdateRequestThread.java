package com.stardew.network;

import java.util.HashMap;

public class GameUpdateRequestThread extends Thread {
    private volatile boolean running = true;
    private final int id;

    public GameUpdateRequestThread(int id) {
        this.id = id;
    }

    public void stopRequesting() {
        running = false;
        interrupt();
    }

    @Override
    public void run() {
        while (running) {
            try {
                NetworkManager.getConnection().sendMessage(updateMessage());
                Thread.sleep(50);
            } catch (InterruptedException ignored) {

            } catch (Exception e) {
                System.out.println("Error sending game state request " + e.getMessage());
            }
        }
    }


    private Message updateMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        return new Message(body, MessageType.UPDATE_GAME);
    }
}
