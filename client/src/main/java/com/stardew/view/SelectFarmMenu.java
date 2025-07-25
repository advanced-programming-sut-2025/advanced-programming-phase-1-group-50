package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.ArrayList;
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
    private final ArrayList<Player> players = new ArrayList<>();


    public SelectFarmMenu(int id) {
        this.id = id;
        stage = new Stage(new ScreenViewport());
        String[] farmNames = new String[]{
            "Farm 1",
            "Farm 2",
            "Farm 3",
            "Farm 4",
        };

//        this.players.addAll(players);

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
                App.gameSample.setCurrentPlayingPlayer(players.get(0));
                stage.addActor(new ShowFarmsWindow(stage , 1 , App.gameSample));
            }
        });
        farm2 = new TextButton("Farm 2", GamePictureManager.skin);
        farm2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.gameSample.setCurrentPlayingPlayer(players.get(1));
                stage.addActor(new ShowFarmsWindow(stage , 2 , App.gameSample));
            }
        });
        farm3 = new TextButton("Farm 3", GamePictureManager.skin);
        farm3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.gameSample.setCurrentPlayingPlayer(players.get(2));
                stage.addActor(new ShowFarmsWindow(stage , 3 , App.gameSample));
            }
        });
        farm4 = new TextButton("Farm 4", GamePictureManager.skin);
        farm4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.gameSample.setCurrentPlayingPlayer(players.get(3));
                stage.addActor(new ShowFarmsWindow(stage , 4 , App.gameSample));
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
        remainingTime -= delta;
        if (remainingTime <= 0) {
            remainingTime = 0;
            if (!hasSentReadyMessage)
                sendReadyMessage();
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
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Stage getStage() {
        return stage;
    }

}
