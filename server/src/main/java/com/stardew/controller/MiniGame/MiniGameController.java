package com.stardew.controller.MiniGame;

import com.stardew.model.DrawableID;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.Tools.PoleType;
import com.stardew.model.animals.Fish;
import com.stardew.model.animals.FishType;
import com.stardew.model.animals.Quality;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MiniGameController {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final long deltaMilliSeconds = 20;
    private final int id;
    private final Player player;
    private final ClientConnectionThread connection;
    private final Fish[] fishes;
    private final ArrayList<Fish> caughtFishes;
    private Fish currentFish;
    private int numOfPlayedFish;
    private float greenBarY;
    private final float greenBarSpeed = 300f;
    private float successAmount;
    private final float successMaxAmount;
    private float restTime;
    private boolean isInGreenBarAllTime = true;
    private final boolean hasSonarBobber;
    private boolean isClosedGame = false;
    private boolean isFirstUpdateMessage = true;



    public MiniGameController(Player player, ClientConnectionThread connection, Fish[] fishes, PoleType poleType, int id) {
        this.id = id;
        this.player = player;
        this.connection = connection;
        this.fishes = fishes;
        this.caughtFishes = new ArrayList<>();
        this.hasSonarBobber = poleType == PoleType.Bamboo || poleType == PoleType.Fiberglass || poleType == PoleType.Iridium;
        this.greenBarY = 100;
        this.successMaxAmount = 50;

        for (Fish fish : fishes) {
            fish.getPosition().set(430, new Random().nextFloat(600) + 90);
        }
        currentFish = fishes[0];

        startNewRound();
        startGameLoop();
    }


    private void startNewRound() {
        isFirstUpdateMessage = true;
        if (numOfPlayedFish < fishes.length) {
            successAmount = successMaxAmount / 6f;
            isInGreenBarAllTime = true;
            restTime = new Random().nextFloat(4f) + 4;
            currentFish = fishes[numOfPlayedFish];
        } else {
            restTime = 10f;
        }
    }

    public void startGameLoop() {
        float delta = deltaMilliSeconds / 1000f;
        executor.scheduleAtFixedRate(() -> {
            if (isFinished()) { finishAndCloseGame(); executor.shutdown(); }
            if (restTime > 0) { restTime -= delta; return; }

            currentFish.update(delta);
            updateSuccessAmount(delta);
            checkCatchFish();

        }, 1000, deltaMilliSeconds, TimeUnit.MILLISECONDS);
    }

    private boolean isFinished() {
        return numOfPlayedFish == fishes.length || isClosedGame;
    }

    private void updateSuccessAmount(float delta) {
        Rectangle fishRectangle = new Rectangle((int) currentFish.getPosition().x, (int) currentFish.getPosition().y, 48, 48);
        Rectangle greenBarRectangle = new Rectangle(428, (int) greenBarY, 55, 150);
        boolean isFishInGreenBar;
        if (!isInGreenBarAllTime) {
            isFishInGreenBar = greenBarRectangle.contains(fishRectangle);
        } else {
            isInGreenBarAllTime = greenBarRectangle.contains(fishRectangle);
            isFishInGreenBar = isInGreenBarAllTime;
        }
        if (isFishInGreenBar) {
            successAmount += 6*delta;
        } else {
            successAmount -= 6*delta;
        }

    }

    private void checkCatchFish() {
        if (successAmount >= successMaxAmount) {
            numOfPlayedFish++;
            caughtFishes.add(currentFish);
            checkToBePerfect(currentFish);
            showCaughtFish(currentFish);
            startNewRound();
        }
        else if (successAmount <= 0) {
            numOfPlayedFish++;
            startNewRound();
        }

    }

    // "command" -> "caught_fish"   : send
    private void showCaughtFish(Fish fish) {
        TextureID textureID = fish.getInventoryTexture();
        String description = fish.toString();

        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "caught_fish");
        body.put("description", description);
        body.put("textureID", textureID);
        Message message = new Message(body, MessageType.MINI_GAME_REQUESTS);
        connection.sendMessage(message);
    }

    // "command" -> "perfect_fish"   : send
    private void checkToBePerfect(Fish fish) {
        if (isInGreenBarAllTime) {
            Quality previousQuality = fish.getQuality();
            if (previousQuality != Quality.Regular) fish.developQuality();
            Quality newQuality = fish.getQuality();

            int previousFishingRate = player.getAbility().getFishingRate();
            player.getAbility().increaseFishingRate(((int) (previousFishingRate * 1.4)));
            int newFishingRate = player.getAbility().getFishingRate();

            String result =
                "You catch this fish PERFECTLY!\n\n\n" +
                    "    Previous Quality:  " + previousQuality + "\n\n" +
                    "    New Quality:       " + newQuality + "\n\n\n" +
                    "    Previous Fishing Skill:  " + previousFishingRate + "\n\n" +
                    "    new Fishing Skill:       " + newFishingRate + "\n\n";

            HashMap<String, Object> body = new HashMap<>();
            body.put("command", "perfect_fish");
            body.put("description", result);
            Message message = new Message(body, MessageType.MINI_GAME_REQUESTS);
            connection.sendMessage(message);
        }
    }

    // "command" -> "status"      : send
    private void sendStatus() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "status");
        body.put("greenBar_y", greenBarY);
        if (restTime > 0) {
            body.put("is_resting", true);
        } else {
            body.put("is_resting", false);
            //TODO
            body.put("is_first_update", isFirstUpdateMessage);
            body.put("can_show_fish", hasSonarBobber);
            if (isFirstUpdateMessage) {
                isFirstUpdateMessage = false;
                body.put("fish_drawableID", (FishType.isLegendary(currentFish.getType()) ? DrawableID.legendFish : DrawableID.normalFish));
                if (hasSonarBobber) {
                    body.put("main_fish_textureID", currentFish.getInventoryTexture());
                    body.put("main_fish_name", currentFish.toString());
                }
            }
            body.put("fish_y", currentFish.getPosition().y);
            body.put("success_amount", successAmount);

        }
        Message message = new Message(body, MessageType.MINI_GAME_REQUESTS);
        connection.sendMessage(message);
    }

    // "command" -> "terminate"   : send
    private void sendTerminateMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "terminate");
        body.put("result", prepareFinalResult());
        Message message = new Message(body, MessageType.MINI_GAME_REQUESTS);
        connection.sendMessage(message);
    }

    private Result prepareFinalResult() {
        if (caughtFishes.isEmpty())
            return new Result(false, "NO Fish was caught");

        StringBuilder result = new StringBuilder();
        result.append("Caught Fish: \n\n\n");
        for (Fish fish : caughtFishes) {
            result.append(fish.getInfo()).append("\n\n");
        }
        return new Result(true, result.toString());
    }

    private void finishAndCloseGame() {
        for (Fish fish : caughtFishes) {
            player.getBackpack().addIngredients(fish, 1);
        }

        sendTerminateMessage();
        MiniGameControllersManager.getInstance().removeController(id);
    }


    // "command" -> "close"   : receive
    private void closeGameByPlayer() {
        isClosedGame = true;
    }

    // "command" -> "movement"   : receive
    private void updateGreenBarMovement(Message message) {
        float dy = message.getFromBody("dy", Float.class);

        float newY = greenBarY + dy * greenBarSpeed;
        if (newY < 85) newY = 85;
        if (newY > 590) newY = 590;

        greenBarY = newY;
    }

    // "command" -> "get_status"   : receive
    private void handleStatusSending() {
        sendStatus();
    }





    // after recognize id we come here
    public void handleMessages(Message message) {
        String command = message.getFromBody("command", String.class);
        switch (command) {
            case "close" -> closeGameByPlayer();
            case "movement" -> updateGreenBarMovement(message);
            case "get_status" -> handleStatusSending();
            default -> System.err.println("Unknown command in MINI_GAME: " + command);
        }
    }
}
