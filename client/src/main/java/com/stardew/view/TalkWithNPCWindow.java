package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.NPCs.NPCType;
import com.stardew.network.*;
import com.stardew.network.Event;
import com.stardew.view.windows.CloseableWindow;

import java.util.HashMap;

public class TalkWithNPCWindow extends CloseableWindow {
    private final TextField playerInput;
    private final Label npcResponse;
    private final TextButton talkButton;
    private Timer.Task thinkingTask;

    public TalkWithNPCWindow(Stage stage, NPCType npcType, int gameId) {
        super("Talk with " + npcType, stage);

        setSize(700, 500);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
        pad(20);
        align(Align.center);

        Skin skin = GamePictureManager.skin;

        playerInput = new TextField("", skin);
        playerInput.setMessageText("Type your message...");
        playerInput.setColor(0.95f, 0.98f, 0.95f, 1f);
        playerInput.getStyle().fontColor = Color.BLACK;
        playerInput.setAlignment(Align.center);

        talkButton = new TextButton("Talk", skin);
        talkButton.setColor(Color.FOREST);
        talkButton.getLabel().setColor(Color.WHITE);

        talkButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (playerInput.getText().isEmpty()) {
                    playerInput.addAction(Actions.sequence(
                        Actions.color(Color.SCARLET, 0.1f),
                        Actions.color(Color.WHITE, 0.2f)
                    ));
                    return;
                }

                startThinkingAnimation(npcType.name());

                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("npcType", npcType);
                    body.put("id", gameId);
                    body.put("input", playerInput.getText());
                    body.put("event", Event.TalkToNPC);
                    Message m = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(m, 10000);
                    if (response != null && response.getType() == MessageType.TALK_TO_NPC_RESULT) {
                        String result = response.getFromBody("result");
                        Gdx.app.postRunnable(() -> {
                            stopThinkingAnimation();
                            npcResponse.setText(parseInput(result));
                        });
                    } else {
                        Gdx.app.postRunnable(() -> {
                            stopThinkingAnimation();
                            npcResponse.setText("[No response from NPC]");
                        });
                    }
                }).start();
            }
        });

        npcResponse = new Label("", skin);
        npcResponse.setWrap(true);
        npcResponse.setAlignment(Align.center);

        npcResponse.setColor(Color.BLACK);

        Table table = new Table();
        table.center();
        table.defaults().space(15).width(300);

        table.add(playerInput).height(40).row();
        table.add(talkButton).height(45).row();
        table.add(npcResponse).height(120).row();

        add(table).expand().fill();
    }

    private void startThinkingAnimation(String npcName) {
        stopThinkingAnimation();

        playerInput.setVisible(false);
        talkButton.setVisible(false);

        npcResponse.setText(npcName + " is thinking");

        thinkingTask = Timer.schedule(new Timer.Task() {
            int dots = 0;
            @Override
            public void run() {
                dots = (dots % 3) + 1;
                String dotStr = ".".repeat(dots);
                npcResponse.setText(npcName + " is thinking" + dotStr);
            }
        }, 0, 0.5f);
    }

    private void stopThinkingAnimation() {
        if (thinkingTask != null) {
            thinkingTask.cancel();
            thinkingTask = null;
        }
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
