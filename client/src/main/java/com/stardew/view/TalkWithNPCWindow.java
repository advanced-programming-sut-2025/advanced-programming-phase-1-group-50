package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.HashMap;

public class TalkWithNPCWindow extends CloseableWindow {
    private final TextField playerInput;
    private final Label npcResponse;
    public TalkWithNPCWindow(Stage stage , String npcName , int gameId) {
        super("talk with npc : " + npcName  , stage);

        setSize( 700 , 600);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
        pad(20);
        setColor(Color.GRAY);
        align(Align.top);

        Skin skin = GamePictureManager.skin;

        playerInput = new TextField("", skin);
        playerInput.setMessageText("Type your message...");
        playerInput.setAlignment(Align.left);

        TextButton talkButton = new TextButton("Talk", skin);

        talkButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if(playerInput.getText().isEmpty()) {
                    return false;
                }
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("npcName", npcName);
                    body.put("id", gameId);
                    body.put("input", playerInput.getText());
                    body.put("event" , Event.TalkToNPC);
                    Message m = new Message(body , MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(m , 10000);
                    if(response != null && response.getType() == MessageType.TALK_TO_NPC_RESULT) {

                        String result = response.getFromBody("result");
                        Gdx.app.postRunnable(() -> {
                            npcResponse.setText(parseInput(result));

                        });
                    }
                }).start();


                return true;



            }
        });


        npcResponse = new Label("", skin);
        npcResponse.setWrap(true);
        npcResponse.setAlignment(Align.topLeft);


        Table table = new Table();
        table.top().left().pad(5);
        table.defaults().space(10).fillX();

        table.add(playerInput).height(30).row();
        table.add(talkButton).height(35).row();
        table.add(npcResponse).width(350).height(100).row();

        add(table).expand().fill();

    }

    private String parseInput(String response) {
        StringBuilder combinedResponse = new StringBuilder();


        String[] parts = response.split("(?=\\{)");

        JsonParser parser = new JsonParser();

        for (String part : parts) {
            try {
                JsonObject obj = parser.parse(part).getAsJsonObject();
                if (obj.has("response")) {
                    combinedResponse.append(obj.get("response").getAsString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return combinedResponse.toString();
    }


}
