package com.stardew.view.cheatConsole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.HashMap;

public class CheatWindow extends CloseableWindow {

    private final TextArea logArea;
    private final TextField inputField;
    private final ScrollPane scrollPane;


    public CheatWindow(Stage stage , int id) {
        super("Cheat Box", stage);

        pad(25, 7, 20, 0);
        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);

        logArea = new TextArea("", GamePictureManager.skin);
        logArea.setDisabled(true);



        scrollPane = new ScrollPane(logArea, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);




        inputField = new TextField("", GamePictureManager.skin);
        inputField.setMessageText("Enter cheat code...");
        inputField.setTextFieldListener((field, c) -> {
            if (c == '\r' || c == '\n') {
                String command = field.getText().trim();
                if (!command.isEmpty()) {
//                    String result = cheatHandler.executeCommand(command);
                    new Thread(() -> {
                        HashMap<String , Object> body = new HashMap<>();
                        body.put("id" , id);
                        body.put("command", command);
                        body.put("event" , Event.CheatCode);
                        Message m = new Message(body , MessageType.EVENT_IN_GAME);
                        Message response = NetworkManager.getConnection().sendAndWaitForResponse(m ,  500);
                        if(response != null && response.getType() == MessageType.CHEAT_CODE_RESULT) {
                            String result = response.getFromBody("result");
                            Gdx.app.postRunnable(() -> {

                                log("> " + command);
                                log(result);
                                field.setText("");
                            });


                        }


                    }).start();
                }
            }
        });


        defaults().pad(5, 8, 5, 15);
        row();
        add(scrollPane).expand().fill().colspan(2).height(250);
        row();
        add(inputField).expandX().fillX();
    }

    public void log(String line) {
        logArea.appendText(line + "\n");
        scrollPane.layout();
        scrollPane.setScrollPercentY(1f);
    }
}
