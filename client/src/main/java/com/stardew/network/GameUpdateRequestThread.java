package com.stardew.network;

import com.stardew.controller.GameStateController;

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
        long lastSentMsg1 = System.currentTimeMillis();
        long lastSentMsg2 = System.currentTimeMillis();
        NetworkManager.getConnection().sendMessage(requestMap());
        while (running) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastSentMsg1 > 30 && !GameStateController.getInstance().isFirstUpdate()) {
                NetworkManager.getConnection().sendMessage(updatePlayersMessage());
                lastSentMsg1 = currentTime;
            }

            if (currentTime - lastSentMsg2 > 1000) {
                NetworkManager.getConnection().sendMessage(updateTilesAndPlaceablesMessage());
                NetworkManager.getConnection().sendMessage(updateAnimalsMessage());
                NetworkManager.getConnection().sendMessage(updateScoreBoardMessage());
                NetworkManager.getConnection().sendMessage(updateHotBarMessage());
                lastSentMsg2 = currentTime;
            }

            try {
                Thread.sleep(5);
            } catch (Exception e) {
                System.err.println("Error sending game state request " + e.getMessage());
            }
        }
    }



    private Message requestMap() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        return new Message(body, MessageType.MAP_REQUEST);
    }

    private Message updatePlayersMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("startX", GameStateController.getInstance().getGameState().getStartX());
        body.put("startY", GameStateController.getInstance().getGameState().getStartY());
        body.put("endX", GameStateController.getInstance().getGameState().getEndX());
        body.put("endY", GameStateController.getInstance().getGameState().getEndY());
        body.put("id", id);
        return new Message(body, MessageType.UPDATE_PLAYERS);
    }

    private Message updateTilesAndPlaceablesMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("startX", GameStateController.getInstance().getGameState().getStartX());
        body.put("startY", GameStateController.getInstance().getGameState().getStartY());
        body.put("endX", GameStateController.getInstance().getGameState().getEndX());
        body.put("endY", GameStateController.getInstance().getGameState().getEndY());
        return new Message(body, MessageType.UPDATE_TILES);
    }

    private Message updateAnimalsMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        return new Message(body, MessageType.UPDATE_ANIMALS);
    }

    private Message updateScoreBoardMessage() {
        HashMap<String,Object> body = new HashMap<>();
        body.put("id", id);
        return new Message(body , MessageType.UPDATE_SCOREBOARD);
    }

    private Message updateHotBarMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        return new Message(body, MessageType.UPDATE_HOT_BAR);
    }
}
