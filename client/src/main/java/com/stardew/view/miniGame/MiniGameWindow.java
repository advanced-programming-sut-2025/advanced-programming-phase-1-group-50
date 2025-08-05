package com.stardew.view.miniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.stardew.model.DrawableID;
import com.stardew.model.TextureID;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.model.Result;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MiniGameWindow extends CloseableWindow {
    private static MiniGameWindow instance;
    private final Image fishingSystem = new Image(GamePictureManager.fishingSystem);
    private final Image greenBar = new Image(GamePictureManager.greenBar);
    private final Image fishImage = new Image(GamePictureManager.normalFish);
    private final Image mainFishImage = new Image(GamePictureManager.normalFish);
    private final Label nameOfFish;
    private ProgressBar successBar;
    private final Table rightPanel = new Table();
    private final Label.LabelStyle labelStyle = new Label.LabelStyle();
    private final Label perfectCatchLabel;
    private final ScheduledExecutorService requestThread = Executors.newSingleThreadScheduledExecutor();
    private volatile float greenBarY;
    private volatile float fishY;
    private volatile float successAmount;
    private boolean isClosedGame = false;
    private boolean canShowFish;
    private final int miniGameID;

    public static MiniGameWindow getInstance() {
        return instance;
    }

    public MiniGameWindow(Stage stage, int miniGameID) {
        super(" Mini Game", stage);
        this.miniGameID = miniGameID;
        instance = this;

        //initialize labelStyle:
        labelStyle.font = GamePictureManager.smallFont;

        nameOfFish = new Label("", labelStyle);
        mainFishImage.setPosition(40, 200);
        nameOfFish.setPosition(100, 220);
        addActor(mainFishImage);
        addActor(nameOfFish);

        Label titleLabel = getTitleLabel();
        Label.LabelStyle titleLabelStyle = titleLabel.getStyle();
        titleLabelStyle.fontColor = Color.YELLOW;
        getTitleLabel().setStyle(titleLabelStyle);
        pad(25, 5, 20, 0);
        pack();
        setSize(900, 800);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        setColor(Color.BLUE);

        Table mainContent = new Table();
        mainContent.setFillParent(true);
        add(mainContent).expand().fill();

        rightPanel.top().left().pad(10);

        mainContent.add().width(600);
        mainContent.add(rightPanel).width(300).top().left();


        //perfect_label
        perfectCatchLabel = new Label("", labelStyle);
        perfectCatchLabel.setPosition(35, 600);
        addActor(perfectCatchLabel);

        initializeGame();
        startRequestThread();
    }

    private void initializeGame() {

        fishingSystem.setSize(300, 750);
        fishingSystem.setPosition(getWidth() / 2 - fishingSystem.getWidth() / 2, getHeight() / 2 - fishingSystem.getHeight() / 2);
        greenBar.setPosition(428, 100);
        greenBar.setSize(55, 150);
        fishImage.setPosition(430, fishY);
        fishImage.setVisible(false);
        successBar = new ProgressBar(0, 50, 0.1f, true, GamePictureManager.skin);
        successBar.setAnimateDuration(0.1f);
        successBar.setHeight(680);
        successBar.setPosition(530, 70);
        addActor(fishingSystem);
        addActor(greenBar);
        addActor(fishImage);
        addActor(successBar);

    }



    @Override
    public void act(float delta) {
        super.act(delta);

        sendGreenBarMovement(delta);
        updatePositions();
        updateSuccessBar();

    }

    private void updateSuccessBar() {
        successBar.setValue(successAmount);

        if (successBar.getValue() < successBar.getMaxValue() / 7)
            successBar.setColor(Color.RED);
        else if (successBar.getValue() > (6 * successBar.getMaxValue() / 8))
            successBar.setColor(Color.GREEN);
        else
            successBar.setColor(Color.WHITE);
    }

    private void updatePositions() {
        fishImage.setY(fishY);
        greenBar.setY(greenBarY);
    }


    // "command" -> "movement"   : send
    private void sendGreenBarMovement(float delta) {
        float vy = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            vy += 1;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            vy -= 1;

        if (vy == 0) return;

        HashMap<String, Object> body = new HashMap<>();
        body.put("dy", vy * delta);
        body.put("miniGame_ID", miniGameID);
        body.put("command", "movement");
        Message message = new Message(body, MessageType.MINI_GAME_REQUESTS);
        NetworkManager.getConnection().sendMessage(message);
    }

    // "command" -> "close"    : send
    private void sendCloseGameMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("miniGame_ID", miniGameID);
        body.put("command", "close");
        Message message = new Message(body, MessageType.MINI_GAME_REQUESTS);
        NetworkManager.getConnection().sendMessage(message);
    }

    // "command" -> "get_status"   : send
    private void startRequestThread() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("miniGame_ID", miniGameID);
        body.put("command", "get_status");
        Message message = new Message(body, MessageType.MINI_GAME_REQUESTS);
        requestThread.scheduleAtFixedRate(
            () -> NetworkManager.getConnection().sendMessage(message), 30, 20, TimeUnit.MILLISECONDS
        );
    }

    // "command" -> "caught_fish"  : receive
    private void showCaughtFish(Message message) {
        TextureID textureID = message.getFromBody("textureID", TextureID.class);
        String description = message.getFromBody("description", String.class);

        Gdx.app.postRunnable(() -> {
            Image image = new Image(GameAssetIDManager.getTextureRegion(textureID));
            Label label = new Label(description, labelStyle);
            Table table = new Table();
            table.add(image).size(32);
            table.add(label).padLeft(10).left().expandX().fillX();
            rightPanel.add(table).padBottom(10).left().row();
        });
    }

    // "command" -> "perfect_fish"   : receive
    private void showPerfectCaught(Message message) {
        String description = message.getFromBody("description", String.class);

        Gdx.app.postRunnable(() -> {
            perfectCatchLabel.setText(description);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    perfectCatchLabel.setText("");
                }
            }, 5f);
        });
    }

    //"command" -> "status"     : receive
    private void handleUpdateStatus(Message message) {
        boolean isResting = message.getFromBody("is_resting", Boolean.class);
        greenBarY = message.getFromBody("greenBar_y", Float.class);
        Gdx.app.postRunnable(() -> {
            if (isResting) {
                successAmount = 0;
                fishImage.setVisible(false);
                mainFishImage.setVisible(false);
                nameOfFish.setVisible(false);
            } else {
                fishImage.setVisible(true);
                mainFishImage.setVisible(canShowFish);
                nameOfFish.setVisible(canShowFish);

                boolean isFirstUpdate = message.getFromBody("is_first_update", Boolean.class);
                canShowFish = message.getFromBody("can_show_fish", Boolean.class);
                if (isFirstUpdate) {
                    DrawableID fishDrawableID = message.getFromBody("fish_drawableID", DrawableID.class);
                    fishImage.setDrawable(GameAssetIDManager.getDrawable(fishDrawableID));
                    if (canShowFish) {
                        TextureID mainFishTextureID = message.getFromBody("main_fish_textureID", TextureID.class);
                        String mainFishName = message.getFromBody("main_fish_name", String.class);
                        mainFishImage.setDrawable(new TextureRegionDrawable(GameAssetIDManager.getTextureRegion(mainFishTextureID)));
                        nameOfFish.setText(mainFishName);
                    }
                }

                fishY = message.getFromBody("fish_y", Float.class);
                successAmount = message.getFromBody("success_amount", Float.class);
            }

        });
    }

    // "command" -> "terminate"   : receive
    private void handleTerminateGame(Message message) {
        Result result = message.getFromBody("result", Result.class);
        requestThread.shutdown();

        Gdx.app.postRunnable(() -> {
            if (isClosedGame) {
                showResult(result);
                terminateWindow();
            }
            else {
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        showResult(result);
                        terminateWindow();
                    }
                }, 5f);  //timer is for showing last result
            }
        });
    }




    @Override
    protected void closeWindow() {
        if (!isClosedGame) sendCloseGameMessage();
        isClosedGame = true;

    }

    private void terminateWindow() {
        super.closeWindow();
    }




    public void handleMessages(Message message) {
        String command = message.getFromBody("command", String.class);
        switch (command) {
            case "caught_fish" -> showCaughtFish(message);
            case "perfect_fish" -> showPerfectCaught(message);
            case "status" -> handleUpdateStatus(message);
            case "terminate" -> handleTerminateGame(message);
            default -> System.err.println("Unknown command in MINI_GAME: " + command);
        }
    }

}
