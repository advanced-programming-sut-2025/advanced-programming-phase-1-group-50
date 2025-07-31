package com.stardew.model.gameApp;

import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeService {
    private final ScheduledExecutorService executor;
    private final TimeProvider timeProvider;
    private final Game game;

    public TimeService(TimeProvider timeProvider, Game game) {
        this.timeProvider = timeProvider;
        this.game = game;
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        sendUpdateTime();
        executor.scheduleAtFixedRate(() -> {
            timeProvider.getTime().advancedMinute(10);
            sendUpdateTime();
        }, 10, 10,TimeUnit.SECONDS);
    }
    // TODO : when the game finished , we should stop the executor;
    public void stop() {
        executor.shutdown();
    }

    public void sendUpdateTime() {
        HashMap<String , Object> body = new HashMap<>();
        body.put("timeDTO" , timeProvider.getTime().toDTO());
        Message m = new Message(body , MessageType.UPDATE_TIME);
        for(ClientConnectionThread cl : game.clientConnectionThreads()){
            cl.sendMessage(m);
        }

    }

}
