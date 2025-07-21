package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.controller.CreateNewGameController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.userInfo.Player;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreateNewGameMenu implements Screen {
    private final Stage stage;

    private final TextField username2;
    private final TextField username3;
    private final TextField username4;
    private final TextButton startGame;
    private CreateNewGameController controller = new CreateNewGameController();
    public Table table;

    public CreateNewGameMenu() {
        stage = new Stage();


        username2 = new TextField("" , GamePictureManager.skin);

        username3 = new TextField("" , GamePictureManager.skin);

        username4 = new TextField("" , GamePictureManager.skin);

        startGame = new TextButton("Start", GamePictureManager.skin);

        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ArrayList<Player> players = new ArrayList<>();
                controller.handleStartNewGame(players);
            }
        });

        controller.setView(this);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);
        table = new Table(GamePictureManager.skin);
        table.setFillParent(true);
        table.center();




        table.add("Username 2:").pad(10);
        table.add(username2).width(200).row();

        table.add("Username 3:").pad(10);
        table.add(username3).width(200).row();

        table.add("Username 4:").pad(10);
        table.add(username4).width(200).row();

        table.add(startGame).colspan(2).padTop(20).row();

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

    public CreateNewGameController getController() {
        return controller;
    }

    public TextButton getStartGame() {
        return startGame;
    }

    public TextField getUsername3() {
        return username3;
    }

    public TextField getUsername4() {
        return username4;
    }

    public TextField getUsername2() {
        return username2;
    }


}
