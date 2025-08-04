package com.stardew.network;

import com.stardew.controller.GameStateController;

import java.util.HashMap;

public class GameUpdateRequestThread extends Thread {
    private volatile boolean running = true;
    private final int id;
    private boolean firstUpdate = true;

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
                NetworkManager.getConnection().sendMessage(getMessage());
                if (!firstUpdate){
                    NetworkManager.getConnection().sendMessage(updateAnimalsMessage());
                    NetworkManager.getConnection().sendMessage(scoreBoardRequest());
                }

                Thread.sleep(50);
            } catch (InterruptedException ignored) {

            } catch (Exception e) {
                System.err.println("Error sending game state request " + e.getMessage());
            }
        }
    }

    private Message getMessage() {
        if (firstUpdate) {
            firstUpdate = false;
            return requestMap();
        } else
            return updateMessage();
    }


    private Message updateMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("startX", GameStateController.getInstance().getGameState().getStartX());
        body.put("startY", GameStateController.getInstance().getGameState().getStartY());
        body.put("endX", GameStateController.getInstance().getGameState().getEndX());
        body.put("endY", GameStateController.getInstance().getGameState().getEndY());
        return new Message(body, MessageType.UPDATE_GAME);
    }

    private Message requestMap(){
        HashMap<String,Object> body = new HashMap<>();
        body.put("id", id);
        return new Message(body, MessageType.MAP_REQUEST);
    }

    private Message updateAnimalsMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        return new Message(body, MessageType.UPDATE_ANIMALS);
    }

    private Message scoreBoardRequest() {
        HashMap<String,Object> body = new HashMap<>();
        body.put("id", id);
        return new Message(body , MessageType.UPDATE_SCOREBOARD);

    }
}
