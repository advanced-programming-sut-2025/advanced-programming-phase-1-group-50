package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.stardew.Main;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.MainMenu;
import com.stardew.view.windows.CloseableWindow;

import java.util.HashMap;

public class SettingWindow extends CloseableWindow {
    private final TextButton exitButton;
    private final TextButton removePlayersButton;



    public SettingWindow(Stage stage, int id) {
        super("Setting Window" ,stage );
        Label titleLabel = getTitleLabel();

        setSize(600, 400);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        pad(15, 10, 15, 10);
        setColor(Color.ORANGE);
        align(Align.top);



        exitButton = new TextButton("exit Game" , GamePictureManager.skin);
        removePlayersButton = new TextButton("Remove Players", GamePictureManager.skin);




        add(exitButton);
        add(removePlayersButton);

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HashMap<String, Object> body = new HashMap<>();
                body.put("id", id);
                body.put("event", Event.ExitGame);
                Message message = new Message(body, MessageType.EVENT_IN_GAME);
                NetworkManager.getConnection().sendMessage(message);

                Screen currentScreen = Main.getMain().getScreen();
                MainMenu mainMenu = new MainMenu(LoggedInUser.getUser().getNickname());
                Main.getMain().setScreen(mainMenu);
                currentScreen.dispose();
            }
        });
    }
}
