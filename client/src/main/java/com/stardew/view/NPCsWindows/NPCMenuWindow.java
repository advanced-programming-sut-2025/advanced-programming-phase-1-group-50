package com.stardew.view.NPCsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.NPCs.NPC;
import com.stardew.view.windows.CloseableWindow;

public class NPCMenuWindow extends CloseableWindow {

    public NPCMenuWindow(Stage stage, NPC npc) {
        super("NPC menu", stage);

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

        Image giftIcon = new Image(GamePictureManager.giftIcon);
        TextButton giftButton = new TextButton("Gift to NPC", GamePictureManager.skin);
        giftButton.setColor(Color.ROYAL);

        Table giftRow = new Table();
        giftRow.add(giftIcon).size(36, 36).padRight(10);
        giftRow.add(giftButton).width(200).height(55);
        contentTable.add(giftRow).pad(8).left().row();

        giftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new GiftSelectionWindow(stage, npc));
            }
        });

        Image questIcon = new Image(GamePictureManager.questIcon);
        TextButton questButton = new TextButton("Quests list", GamePictureManager.skin);
        questButton.setColor(Color.OLIVE);

        Table questRow = new Table();
        questRow.add(questIcon).size(36, 36).padRight(10);
        questRow.add(questButton).width(200).height(55);
        contentTable.add(questRow).pad(8).left().row();

        questButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new QuestsListWindow(stage, npc));
            }
        });

        Image friendshipIcon = new Image(GamePictureManager.friendshipIcon);
        TextButton friendshipButton = new TextButton("Friendship level", GamePictureManager.skin);
        friendshipButton.setColor(Color.FIREBRICK);

        Table friendshipRow = new Table();
        friendshipRow.add(friendshipIcon).size(36, 36).padRight(10);
        friendshipRow.add(friendshipButton).width(200).height(55);
        contentTable.add(friendshipRow).pad(8).left().row();

        friendshipButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new FriendshipLevelWithNPCWindow(stage, npc));
            }
        });

        add(contentTable);
        pack();

        setWidth(330);
        invalidate();
        layout();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
