package com.stardew.view.NPCsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.NPCs.NPC;
import com.stardew.models.Result;
import com.stardew.view.windows.CloseableWindow;

public class NPCMenuWindow extends CloseableWindow {

    public NPCMenuWindow(Stage stage, NPC npc) {
        super("NPC menu",stage);

        setColor(Color.BROWN);
        padTop(20);
        padBottom(20);
        padLeft(30);
        padRight(30);

        Table contentTable = new Table();
        Label nameLabel = new Label("NPC: " + npc.getType().getName(), GamePictureManager.skin);
        nameLabel.setFontScale(1.2f);
        nameLabel.setColor(Color.BLACK);
        nameLabel.setAlignment(Align.center);
        contentTable.add(nameLabel).padBottom(20).center().row();

        TextButton giftButton = new TextButton("Gift to NPC", GamePictureManager.skin);
        giftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new GiftSelectionWindow(stage, npc));
            }
        });

        TextButton questButton = new TextButton("Quests list", GamePictureManager.skin);
        questButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result temp = null;
                if (temp.getSuccessful()) {
//                    hug(otherPlayer);
//                    showResult(new Result(true, "A heartfelt hug was shared."));
//                    closeWindow();
                } else {
                    showResult(temp);
                }
            }
        });

        TextButton friendshipButton = new TextButton("Friendship level", GamePictureManager.skin);
        friendshipButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new FriendshipLevelWithNPCWindow(stage,npc));
            }
        });

        contentTable.defaults().width(200).height(55).pad(8);
        contentTable.add(giftButton).row();
        contentTable.add(questButton).row();
        contentTable.add(friendshipButton).row();

        add(contentTable);
        pack();

        setWidth(300);
        invalidate();
        layout();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
