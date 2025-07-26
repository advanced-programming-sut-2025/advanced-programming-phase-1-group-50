package com.stardew.view.LobbyMenus;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.Main;
import com.stardew.model.LobbyDTO;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.HashMap;

public class AskPrivateLobbyPasswordWindow extends CloseableWindow {

    private TextField password;
    private TextButton join;
    private LobbyDTO lobby;

    public AskPrivateLobbyPasswordWindow(Stage primaryStage , LobbyDTO lobby) {

        super("password" , primaryStage);
        this.lobby = lobby;

        setSize(400, 300);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
        pad(25, 10, 15, 10);
        setColor(Color.ORANGE);
        align(Align.top);

        Skin skin = GamePictureManager.skin;

        Table content = new Table(skin);
        content.defaults().pad(10).fillX();

        content.add(new Label("Password:", skin)).left();
        password = new TextField("", skin);
        content.add(password).colspan(2).row();


        join = new TextButton("Join", skin);
        join.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(password.getText().isEmpty()) {
                    return;
                }
                HashMap<String, Object> body2 = new HashMap<>();
                body2.put("lobbyDTO", lobby);
                body2.put("password" , password.getText());
                Message message = new Message(body2 , MessageType.JOIN_LOBBY);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message , 500);
                if(response != null && response.getType() == MessageType.JOIN_LOBBY_RESULT) {

                    LobbyDTO lobbyDTO = response.getFromBody("lobbyDTO", LobbyDTO.class);

                    Screen screen = Main.getMain().getScreen();
                    Main.getMain().setScreen(new LobbyMenu(lobbyDTO));
                    screen.dispose();

                }
            }
        });
        content.add(join).width(150).left();

        add(content).expand().top();
    }
}
