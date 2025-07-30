package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.GameUpdateRequestThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.HashMap;

public class SelectFarmMenu implements Screen, AppMenu {
    private final Stage stage;
    private final SelectBox<String> selectFarm;
    private final TextButton farm1;
    private final TextButton farm2;
    private final TextButton farm3;
    private final TextButton farm4;
    private final TextButton ready;
    private final Label timeLabel;
    private final int id;
    private final float SELECT_TIME = 30f;
    private float remainingTime = SELECT_TIME;
    private String farmSelected;
    private boolean hasSentReadyMessage = false;
    private boolean fadeOutStarted;
    private float fadeAlpha = 0f;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();


    public SelectFarmMenu(int id) {
        this.id = id;
        stage = new Stage(new ScreenViewport());
        String[] farmNames = new String[]{
            "Farm 1",
            "Farm 2",
            "Farm 3",
            "Farm 4",
        };

        selectFarm = new SelectBox<>(GamePictureManager.skin);
        selectFarm.setItems(farmNames);
        selectFarm.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                String currentSelected = selectFarm.getSelected();
                if (farmSelected != null) {
                    if (farmSelected.equals(currentSelected)) {
                        selectFarm.setColor(Color.GREEN);
                    } else {
                        selectFarm.setColor(Color.WHITE);
                    }
                }
            }
        });

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = GamePictureManager.smallFont;
        labelStyle.fontColor = Color.BLACK;
        timeLabel = new Label("", labelStyle);

        ready = new TextButton("Ready", GamePictureManager.skin);
        ready.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sendReadyMessage();
            }
        });

        farm1 = new TextButton("Farm 1", GamePictureManager.skin);
        farm1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showResult(new Result(false, "NO Implementation"));
//                App.gameSample.setCurrentPlayingPlayer(players.get(0));
//                stage.addActor(new ShowFarmsWindow(stage , 1 , App.gameSample));
            }
        });
        farm2 = new TextButton("Farm 2", GamePictureManager.skin);
        farm2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showResult(new Result(false, "NO Implementation"));
//                App.gameSample.setCurrentPlayingPlayer(players.get(1));
//                stage.addActor(new ShowFarmsWindow(stage , 2 , App.gameSample));
            }
        });
        farm3 = new TextButton("Farm 3", GamePictureManager.skin);
        farm3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showResult(new Result(false, "NO Implementation"));
//                App.gameSample.setCurrentPlayingPlayer(players.get(2));
//                stage.addActor(new ShowFarmsWindow(stage , 3 , App.gameSample));
            }
        });
        farm4 = new TextButton("Farm 4", GamePictureManager.skin);
        farm4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showResult(new Result(false, "NO Implementation"));
//                App.gameSample.setCurrentPlayingPlayer(players.get(3));
//                stage.addActor(new ShowFarmsWindow(stage , 4 , App.gameSample));
            }
        });
    }


    private void sendReadyMessage() {
        farmSelected = selectFarm.getSelected();
        HashMap<String, Object> body = new HashMap<>();
        body.put("farmSelected", farmSelected);
        body.put("isReady", true);
        body.put("lobbyID", id);
        Message message = new Message(body, MessageType.READY_STATUS);
        NetworkManager.getConnection().sendMessage(message);
        hasSentReadyMessage = true;
        selectFarm.setColor(Color.GREEN);
    }

    private void updateTimer(float delta) {
        if (remainingTime > 0) {
            remainingTime -= delta;
            if (remainingTime <= 3 && !hasSentReadyMessage) {
                sendReadyMessage();
            }
        }
    }

    private void updateTimeLabel() {
        timeLabel.setText(((int) remainingTime) + " s");
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);

        Table table = new Table(GamePictureManager.skin);
        table.setFillParent(true);
        table.center();

        table.add(timeLabel).colspan(2).pad(20).row();
        table.add("Select Farm: ").pad(10);
        table.add(selectFarm).width(200).row();

        table.add(ready).colspan(2).padTop(20).row();
        table.add(farm1).colspan(2).padTop(20).width(200).row();
        table.add(farm2).colspan(2).padTop(20).width(200).row();
        table.add(farm3).colspan(2).padTop(20).width(200).row();
        table.add(farm4).colspan(2).padTop(20).width(200).row();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0 , 0  , 0 , 1);
        updateTimer(delta);
        updateTimeLabel();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        if (fadeOutStarted) {
            fadeAlpha += delta / 2f;
            if (fadeAlpha > 1f) fadeAlpha = 1f;

            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, fadeAlpha);
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();

            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }

    @Override public void resize(int i, int i1) {

    }
    @Override public void pause() {

    }
    @Override public void resume() {

    }
    @Override public void hide() {

    }
    @Override public void dispose() {
        shapeRenderer.dispose();
    }
    public Stage getStage() {
        return stage;
    }

    public void startGameTransition(int gameId) {
        fadeOutStarted = true;

        GameUpdateRequestThread updateRequestThread = new GameUpdateRequestThread(gameId);
        updateRequestThread.start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Gdx.app.postRunnable(() -> {
                Main.getMain().setScreen(new GameScreenMenu(updateRequestThread, gameId));
                dispose();
            });
        }).start();
    }
}
