package com.stardew.view.ReactionWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.model.TextureID;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.models.GameAssetManagers.GameAssetIDManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;
import java.util.HashMap;

public class ReactionWindow extends CloseableWindow {
    private final Table emojiTable;
    private ArrayList<TextureID> textures;
    private final TextButton changeDefalutReactions;
    public ReactionWindow(Stage stage , int id) {
        super("Reaction Window" , stage);
        textures = GameAssetIDManager.emojis;

        Label titleLabel = getTitleLabel();
        Label.LabelStyle titleLabelStyle = titleLabel.getStyle();
        titleLabelStyle.fontColor = Color.YELLOW;
        getTitleLabel().setStyle(titleLabelStyle);

        pad(25, 5, 20, 0);
        pack();
        setSize(900, 700);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        setColor(Color.BROWN);

        emojiTable = new Table();

        for(TextureID textureID : textures){
            final TextureRegion emojiRegion = GameAssetIDManager.getTextureRegion(textureID);
            ImageButton emojiButton = new ImageButton(new TextureRegionDrawable(emojiRegion));
            emojiButton.setSize( 72 , 72);
            emojiButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    HashMap<String , Object> body = new HashMap<>();
                    body.put("id" , id);
                    body.put("emoji", textureID);
                    body.put("username" , LoggedInUser.getUser().getUsername());
                    body.put("event" , Event.Reaction);
                    NetworkManager.getConnection().sendMessage(new Message(body , MessageType.EVENT_IN_GAME));
                }
            });

            emojiTable.add(emojiButton).pad(5);
        }

        add(emojiTable).row();
        Table table = new Table();
        changeDefalutReactions = new TextButton("Change Default Reactions", GamePictureManager.skin);
        changeDefalutReactions.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                textures = GameAssetIDManager.emojis2;
                emojiTable.clear();

                for (TextureID textureID : textures) {
                    final TextureRegion emojiRegion = GameAssetIDManager.getTextureRegion(textureID);
                    ImageButton emojiButton = new ImageButton(new TextureRegionDrawable(emojiRegion));
                    emojiButton.setSize( 72 , 72);
                    emojiButton.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            HashMap<String, Object> body = new HashMap<>();
                            body.put("id", id);
                            body.put("emoji", textureID);
                            body.put("username", LoggedInUser.getUser().getUsername());
                            body.put("event", Event.Reaction);
                            NetworkManager.getConnection().sendMessage(new Message(body, MessageType.EVENT_IN_GAME));
                        }
                    });

                    emojiTable.add(emojiButton).pad(5);
                }

                emojiTable.invalidateHierarchy();
            }
        });

        table.add(changeDefalutReactions).pad(5).center();
        add(table).row();

    }
}
