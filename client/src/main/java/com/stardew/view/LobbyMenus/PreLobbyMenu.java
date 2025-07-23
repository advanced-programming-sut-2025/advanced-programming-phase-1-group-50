package com.stardew.view.LobbyMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.google.gson.reflect.TypeToken;
import com.stardew.model.LobbyDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class PreLobbyMenu implements Screen {
    private Stage stage;
    private final TextButton createLobby;
    private final TextButton joinLobby;
    private final TextButton refresh;

    public PreLobbyMenu() {
        stage = new Stage(new ScreenViewport());

        createLobby = new TextButton("Create Lobby", GamePictureManager.skin);
        createLobby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new LobbyInformationWindow(stage , GamePictureManager.skin));
            }
        });

        joinLobby = new TextButton("Join Lobby", GamePictureManager.skin);
        joinLobby.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: open lobby join window
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
                    RefreshLobbiesWindow refreshLobbiesWindow = new RefreshLobbiesWindow(stage);
                    refreshLobbiesWindow.updateLobbyList(ltd);
                    stage.addActor(refreshLobbiesWindow);
                }


            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // پس‌زمینه
        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);

        // استفاده از Table برای چینش مرتب
        Table table = new Table();
        table.setFillParent(true);
        table.center(); // وسط صفحه

        table.add(createLobby).width(300).height(60).padBottom(20).row();
        table.add(joinLobby).width(300).height(60);
        table.add(refresh).width(300).height(60);

        stage.addActor(table);
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
}
