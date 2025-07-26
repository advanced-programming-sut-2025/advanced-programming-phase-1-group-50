package com.stardew.view.LobbyMenus;

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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.model.LobbyDTO;

import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.AppMenu;


import java.util.HashMap;
import java.util.List;

public class LobbyMenu implements Screen, AppMenu {
    private Stage stage;
    private final Skin skin = GamePictureManager.skin;
    private LobbyDTO lobby;
    private Table root;
    private final float JOIN_TIMER = 300f;
    private float remainingTime = JOIN_TIMER;
    private boolean destroyLobby = false;
    private Label timeLabel;
    private Table playersTable;
    private String username;




    public LobbyMenu(LobbyDTO lobby , String username) {
        this.lobby = lobby;
        this.username = username;


    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);


        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = GamePictureManager.smallFont;
        labelStyle.fontColor = Color.BLACK;
        timeLabel = new Label("", labelStyle);


        root = new Table();
        root.setFillParent(true);
        root.top().pad(20);
        stage.addActor(root);

        root = new Table();
        root.setFillParent(true);
        root.top().pad(20);
        stage.addActor(root);



        Label lobbyNameLabel = new Label("Lobby: " + lobby.name, skin);
        lobbyNameLabel.setAlignment(Align.center);
        Label idLabel = new Label("ID: " + lobby.id, skin);
        idLabel.setAlignment(Align.center);
        root.add(lobbyNameLabel).colspan(2).center().padBottom(30).row();
        root.add(idLabel).colspan(2).center().padBottom(30).row();

        List<String> members = lobby.players;
        playersTable = new Table();
        for (String player : members) {
            Label playerLabel;
            if(player.equals(lobby.adminUsername)){
                playerLabel = new Label( "Admin : " + player, skin);
            }
            else{
                playerLabel = new Label(player, skin);
            }
            playerLabel.setAlignment(Align.center);
            playersTable.add(playerLabel).center().padBottom(10).row();
        }
        root.add(playersTable).colspan(2).padBottom(30).row();




        TextButton startGameBtn = new TextButton("Start Game", skin);
        TextButton leaveLobbyBtn = new TextButton("Leave Lobby", skin);




        root.add(startGameBtn).width(180).padRight(20);
        root.add(leaveLobbyBtn).width(180);

        root.add(timeLabel).row();


        startGameBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                HashMap<String, Object> body = new HashMap<>();
                body.put("lobbyID", lobby.id);
                Message message = new Message(body, MessageType.START_GAME);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);

                if (response == null || !response.getType().equals(MessageType.START_GAME_RESULT)) {
                    showResult(new Result(false, "Connection failed or timed out!"));
                    return;
                }
                Result result = response.getFromBody("result", Result.class);
                showResult(result);
            }
        });

        leaveLobbyBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                HashMap<String, Object> body = new HashMap<>();
                body.put("lobbyID", lobby.id);
                Message message = new Message(body, MessageType.LEAVE_LOBBY);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                if (response != null && response.getType().equals(MessageType.LEAVE_LOBBY_RESULT)) {
                    Result result = response.getFromBody("result", Result.class);
                    if(result != null && result.getSuccessful()){
                        Screen screen = Main.getMain().getScreen();
                        Main.getMain().setScreen(new PreLobbyMenu());
                        screen.dispose();
                    }
                }

            }
        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0 , 0  , 0 , 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        if(destroyLobby) root.removeActor(timeLabel);
        if(!destroyLobby && username.equals(lobby.adminUsername)) {
            updateTimer(delta);
            updateTimeLabel();
        }
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

    @Override
    public Stage getStage() {
        return stage;
    }

    public LobbyDTO getLobby() {
        return lobby;
    }

    public void updatePlayerList(List<String> newPlayers) {
        if (playersTable != null) {
            root.removeActor(playersTable);
        }

        playersTable = new Table();
        for (String player : newPlayers) {
            Label label;
            if (player.equals(lobby.adminUsername)) {
                label = new Label("Admin: " + player, skin);
            } else {
                label = new Label(player, skin);
            }
            label.setAlignment(Align.center);
            playersTable.add(label).center().padBottom(10).row();
        }

        root.add(playersTable).colspan(2).padBottom(30).row();

        lobby.players.clear();
        lobby.players.addAll(newPlayers);
    }



    private void updateTimer(float delta) {
        remainingTime -= delta;
        if(!destroyLobby){
            if(remainingTime <= 0){
                sendDestroyLobbyMessage();
            }
        }
    }

    public void sendDestroyLobbyMessage(){
        HashMap<String, Object> body = new HashMap<>();
        body.put("lobbyID", lobby.id);
        Message message = new Message(body, MessageType.DESTROY_LOBBY);
        Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
        if (response != null && response.getType().equals(MessageType.DESTROY_LOBBY_RESULT)) {
            Result result = response.getFromBody("result", Result.class);
            if(result != null && result.getSuccessful()){
                Screen screen = Main.getMain().getScreen();
                Main.getMain().setScreen(new PreLobbyMenu());
                screen.dispose();
            }
        }
        //TODO : destroy lobby and back to preLobby menu
    }

    private void updateTimeLabel() {
        timeLabel.setText(((int) remainingTime) + " s");
    }

    public void setDestroyLobby(boolean destroyLobby) {
        this.destroyLobby = destroyLobby;
    }

    public void setLobby(LobbyDTO lobby) {
        this.lobby = lobby;
    }




}
