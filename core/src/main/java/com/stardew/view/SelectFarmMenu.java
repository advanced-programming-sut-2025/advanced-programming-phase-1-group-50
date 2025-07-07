package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.controller.SelectFarmMenuController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.userInfo.Player;

import java.util.ArrayList;

public class SelectFarmMenu implements Screen {
    private Stage stage;
    private final SelectFarmMenuController controller = new SelectFarmMenuController();
    private final SelectBox<String> selectFarmPlayer1;
    private final SelectBox<String> selectFarmPlayer2;
    private final SelectBox<String> selectFarmPlayer3;
    private final SelectBox<String> selectFarmPlayer4;

    private ArrayList<Player> players = new ArrayList<>();
    private final TextButton start;

    public SelectFarmMenu(ArrayList<Player> players) {
        stage = new Stage();
        Array<String> farmNames = new Array<>();
        farmNames.add("Farm 1");
        farmNames.add("Farm 2");
        farmNames.add("Farm 3");
        farmNames.add("Farm 4");

        this.players.addAll(players);

        selectFarmPlayer1 = new SelectBox<>(GamePictureManager.skin);
        selectFarmPlayer1.setItems(farmNames);
        selectFarmPlayer2 = new SelectBox<>(GamePictureManager.skin);
        selectFarmPlayer2.setItems(farmNames);
        selectFarmPlayer3 = new SelectBox<>(GamePictureManager.skin);
        selectFarmPlayer3.setItems(farmNames);
        selectFarmPlayer4 = new SelectBox<>(GamePictureManager.skin);
        selectFarmPlayer4.setItems(farmNames);

        start = new TextButton("Start", GamePictureManager.skin);


        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleSelectFarm(players);
            }
        });

        controller.setView(this);

    }




    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table(GamePictureManager.skin);
        table.setFillParent(true);
        table.center();

        table.add("Player 1 - Select Farm:").pad(10);
        table.add(selectFarmPlayer1).width(200).row();

        table.add("Player 2 - Select Farm:").pad(10);
        table.add(selectFarmPlayer2).width(200).row();

        table.add("Player 3 - Select Farm:").pad(10);
        table.add(selectFarmPlayer3).width(200).row();

        table.add("Player 4 - Select Farm:").pad(10);
        table.add(selectFarmPlayer4).width(200).row();

        table.add(start).colspan(2).padTop(20).row();

        stage.addActor(table);
    }


    @Override
    public void render(float v) {

        ScreenUtils.clear(0 , 0  , 0 , 1);
        Main.getBatch().begin();
        Main.getBatch().end();
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

    public SelectBox<String> getSelectFarmPlayer4() {
        return selectFarmPlayer4;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public SelectBox<String> getSelectFarmPlayer2() {
        return selectFarmPlayer2;
    }

    public SelectBox<String> getSelectFarmPlayer3() {
        return selectFarmPlayer3;
    }

    public SelectBox<String> getSelectFarmPlayer1() {
        return selectFarmPlayer1;
    }

    public SelectFarmMenuController getController() {
        return controller;
    }
}
