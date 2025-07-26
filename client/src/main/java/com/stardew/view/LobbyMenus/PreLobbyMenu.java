package com.stardew.view.LobbyMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.google.gson.reflect.TypeToken;
import com.stardew.Main;
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

public class PreLobbyMenu implements Screen , AppMenu {
    private Stage stage;
    private final TextButton createLobby;
    private final TextButton refresh;
    private final TextField searchLobby;
    private final ImageButton searchButton;
    private final Table lobbyTable;
    private final Table onlineUsersTable = new Table();
    private float timeToSendOnline = 0;
    private final float STATE_TIME = 3f;


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
                HashMap<String , Object> body = new HashMap<>();
                Message message = new Message(body , MessageType.SEND_LOBBIES);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message , 500);
                if(response != null && response.getType() == MessageType.SEND_LOBBIES_RESULT) {
                    Type type = new TypeToken<ArrayList<LobbyDTO>>(){}.getType();

                    ArrayList<LobbyDTO> ltd = response.getFromBody("lobbyDTOS" , type);
                    ArrayList<LobbyDTO> visibleLobbies = getVisibleLobbies(ltd);
                    updateLobbyTable(visibleLobbies);
                }


            }
        });
        searchLobby = new TextField("Search Lobby", GamePictureManager.skin);
        searchButton = new ImageButton(GamePictureManager.searchButtonDrawable);
        searchButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String search = searchLobby.getText();
                if(search.isEmpty()) return;


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


        Table onlineContainer = new Table();
        onlineContainer.setFillParent(false);
        onlineContainer.top().left().pad(10);
        onlineContainer.setPosition(10, stage.getHeight() - 10);
        onlineContainer.add(onlineUsersTable).left().top();

        stage.addActor(onlineContainer);



        Table topBar = new Table();
        topBar.add(createLobby).width(200).height(50).padRight(20);


        Table searchBox = new Table();
        searchBox.add(searchLobby).width(300).height(50);
        searchBox.add(searchButton).width(50).height(50).padLeft(10);
        topBar.add(searchBox).padRight(20);

        topBar.add(refresh).width(200).height(50);


        ScrollPane scrollPane = new ScrollPane(lobbyTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);


        root.top().padTop(30);
        root.add(topBar).expandX().fillX().padBottom(20).row();
        root.add(scrollPane).expand().fill().pad(20);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
//        timeToSendOnline += delta;
//        if(timeToSendOnline >= STATE_TIME){
//            timeToSendOnline = 0;
//            Message message = new Message(new HashMap<>(), MessageType.SEND_ONLINE_USERS);
//            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 300);
//            if (response != null && response.getType() == MessageType.SEND_ONLINE_USERS_RESULT) {
//                ArrayList<String> onlineUsers = response.getFromBody("onlineUsers", new TypeToken<ArrayList<String>>(){}.getType());
//                updateOnlineUsers(onlineUsers);
//            }
//        }
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

            Label lobbyName = new Label(lobby.name + "ID : " + lobby.id + "User : " + lobby.adminUsername, GamePictureManager.skin);
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
                        Message response = NetworkManager.getConnection().sendAndWaitForResponse(message , 500);
                        if(response != null && response.getType() == MessageType.JOIN_LOBBY_RESULT) {

                            LobbyDTO lobbyDTO = response.getFromBody("lobbyDTO", LobbyDTO.class);
                            String username = response.getFromBody("username");


                            Screen screen = Main.getMain().getScreen();
                            Main.getMain().setScreen(new LobbyMenu(lobbyDTO , username));
                            screen.dispose();


                        }

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

    public void updateOnlineUsers(ArrayList<String> usernames) {


        onlineUsersTable.clear();
        onlineUsersTable.add(new Label("Online Users", GamePictureManager.skin)).padBottom(10).row();
        for (String user : usernames) {
            onlineUsersTable.add(new Label(user, GamePictureManager.skin)).padBottom(5).row();
        }
    }

}
