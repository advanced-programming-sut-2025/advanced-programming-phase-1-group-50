package com.stardew.view.LobbyMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.google.gson.reflect.TypeToken;
import com.stardew.model.LobbyDTO;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.AppMenu;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class PreLobbyMenu implements Screen, AppMenu {
    private final Stage stage;
    private final TextButton createLobby;
    private final TextButton refresh;
    private final TextField searchBar;
    private final ImageButton searchButton;
    private final Table lobbyTable;

    public PreLobbyMenu() {
        stage = new Stage(new ScreenViewport());

        createLobby = new TextButton("Create Lobby", GamePictureManager.skin);
        createLobby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new LobbyInformationWindow(stage , GamePictureManager.skin));
            }
        });


        refresh = new TextButton("Refresh", GamePictureManager.skin);
        refresh.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                refresh.setDisabled(true);
                HashMap<String , Object> body = new HashMap<>();
                Message message = new Message(body , MessageType.SEND_LOBBIES);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message , 500);
                if(response != null && response.getType() == MessageType.SEND_LOBBIES_RESULT) {
                    Type type = new TypeToken<ArrayList<LobbyDTO>>(){}.getType();

                    ArrayList<LobbyDTO> ltd = response.getFromBody("lobbyDTOS" , type);
                    ArrayList<LobbyDTO> visibleLobbies = getVisibleLobbies(ltd);
                    updateLobbyTable(visibleLobbies);
                }
                refresh.setDisabled(false);

            }
        });
        searchBar = new TextField("", GamePictureManager.skin);
        searchBar.setMessageText("Enter ID to search");
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = GamePictureManager.searchButtonUp;
        buttonStyle.imageDown = GamePictureManager.searchButtonDown;
        buttonStyle.imageOver = GamePictureManager.searchButtonOver;
        searchButton = new ImageButton(buttonStyle);
        searchButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String search = searchBar.getText();
                if (search.isEmpty() || !search.matches("\\d+")) {
                    showResult(new Result(false, "Please enter a number!!"));
                    return;
                }

                searchButton.setDisabled(true);
                HashMap<String , Object> body = new HashMap<>();
                body.put("search", search);
                Message message = new Message(body , MessageType.SEARCH_LOBBY);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message , 500);
                if(response != null && response.getType() == MessageType.SEARCH_LOBBY_RESULT) {
                    LobbyDTO result = response.getFromBody("lobbyDTOS" , LobbyDTO.class);
                    ArrayList<LobbyDTO> ltd = new ArrayList<>();
                    ltd.add(result);
                    updateLobbyTable(ltd);
                }
                searchButton.setDisabled(false);
            }
        });

        lobbyTable = new Table();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);


        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);


        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);


        Table topBar = new Table();
        topBar.add(createLobby).width(200).height(50).padRight(20);


        Table searchBox = new Table();
        searchBox.add(searchBar).width(300).height(50);
        searchBox.add(searchButton).width(50).height(50).padLeft(10);
        topBar.add(searchBox).padRight(20);

        topBar.add(refresh).width(200).height(50);


        ScrollPane scrollPane = new ScrollPane(lobbyTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);


        root.top().padTop(30);
        root.add(topBar).expandX().fillX().padBottom(20).row();
        root.add(scrollPane).height(700).pad(20);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    private void updateLobbyTable(ArrayList<LobbyDTO> lobbies) {
        lobbyTable.clear();
        for (LobbyDTO lobby : lobbies) {

            Label lobbyName = new Label(String.format(
                "%-25s  ID: %-10d  Admin: %-25s  N: %d", lobby.name, lobby.id, lobby.adminUsername, lobby.players.size()),
                GamePictureManager.skin);
            lobbyName.setColor(Color.BLACK);
            final LobbyDTO lobbyDTO = lobby;

            TextButton joinBtn = new TextButton("Join", GamePictureManager.skin);

            joinBtn.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(lobby.isPrivate){
                        stage.addActor(new AskPrivateLobbyPasswordWindow(stage , lobbyDTO));
                    }
                    else{
                        HashMap<String , Object> body = new HashMap<>();
                        body.put("lobbyDTO", lobbyDTO);
                        Message message = new Message(body , MessageType.JOIN_LOBBY);
                        NetworkManager.getConnection().sendMessage(message);

                    }
                }
            });

            lobbyTable.add(lobbyName).left().pad(10);
            lobbyTable.add(joinBtn).right().pad(10);
            lobbyTable.row();
        }
    }

    private ArrayList<LobbyDTO> getVisibleLobbies(ArrayList<LobbyDTO> lobbies) {
        ArrayList<LobbyDTO> visibleLobbies = new ArrayList<>();
        for (LobbyDTO lobby : lobbies) {
            if(lobby.isVisible){
                visibleLobbies.add(lobby);
            }
        }
        return visibleLobbies;
    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
