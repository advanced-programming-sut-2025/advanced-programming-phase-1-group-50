package com.stardew.view.LobbyMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.model.LobbyDTO;

import com.stardew.models.GameAssetManagers.GamePictureManager;


import java.util.List;

public class LobbyMenu implements Screen {
    private Stage stage;
    private final Skin skin = GamePictureManager.skin;
    private final LobbyDTO lobby;


    public LobbyMenu(LobbyDTO lobby) {
        this.lobby = lobby;


    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);


        Table root = new Table();
        root.setFillParent(true);
        root.top().pad(20);
        stage.addActor(root);

        Label lobbyNameLabel = new Label("Lobby: " + lobby.name, skin);
        lobbyNameLabel.setAlignment(Align.center);
        root.add(lobbyNameLabel).colspan(2).center().padBottom(30).row();

        List<String> members = lobby.players;
        Table playersTable = new Table();
        for (String player : members) {
            Label playerLabel = new Label(player, skin);
            playerLabel.setAlignment(Align.center);
            playersTable.add(playerLabel).center().padBottom(10).row();
        }


        ScrollPane scrollPane = new ScrollPane(playersTable, skin);
        scrollPane.setFadeScrollBars(false);
        root.add(scrollPane).height(250).width(400).colspan(2).padBottom(30).row();


        TextButton startGameBtn = new TextButton("Start Game", skin);
        TextButton leaveLobbyBtn = new TextButton("Leave Lobby", skin);




        root.add(startGameBtn).width(180).padRight(20);
        root.add(leaveLobbyBtn).width(180);


        startGameBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

            }
        });

        leaveLobbyBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

            }
        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0 , 0  , 0 , 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    public LobbyDTO getLobby() {
        return lobby;
    }
}
