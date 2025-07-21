package com.stardew.view.NPCsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.NPCController.NPCController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.NPCs.NPC;
import com.stardew.models.NPCs.RelationWithNPC;
import com.stardew.models.Result;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;

public class QuestsListWindow extends CloseableWindow {
    public QuestsListWindow(Stage stage, NPC npc) {
        super("Quests list", stage);

        ArrayList<String> questDescriptions = NPCController.getQuestsList(npc);
        RelationWithNPC relation = NPCController.getRelationWithNPC(npc);
        float fontScale = 1.2f;
        Color fontColor = Color.BLACK;

        Table contentTable = new Table();
        contentTable.defaults().pad(10).left();

        Label titleLabel = new Label(npc.getType().getName() + " Quests:", GamePictureManager.skin);
        titleLabel.setFontScale(1.5f);
        titleLabel.setColor(fontColor);
        contentTable.add(titleLabel).colspan(4).padBottom(10).row();

        Label questLabel = new Label("Quest", GamePictureManager.skin);
        questLabel.setFontScale(fontScale);
        questLabel.setColor(fontColor);

        Label lockedLabel = new Label("Locked", GamePictureManager.skin);
        lockedLabel.setFontScale(fontScale);
        lockedLabel.setColor(fontColor);

        Label statusLabel = new Label("Done", GamePictureManager.skin);
        statusLabel.setFontScale(fontScale);
        statusLabel.setColor(fontColor);

        Label actionLabel = new Label("Action", GamePictureManager.skin);
        actionLabel.setFontScale(fontScale);
        actionLabel.setColor(fontColor);

        contentTable.add(questLabel);
        contentTable.add(lockedLabel).padLeft(20);
        contentTable.add(statusLabel).padLeft(20);
        contentTable.add(actionLabel).padLeft(70).row();

        for (int i = 0; i < questDescriptions.size(); i++) {
            String description = questDescriptions.get(i);
            boolean isLocked = false;
            boolean isDone = false;

            if (i == 0) {
                isDone = npc.isFirstQuestDone();
            } else if (i == 1) {
                isLocked = relation.isSecondQuestLocked();
                isDone = npc.isSecondQuestDone();
            } else if (i == 2) {
                isLocked = relation.isThirdQuestLocked();
                isDone = npc.isThirdQuestDone();
            }

            int index = i + 1;

            Label nameLabel = new Label("Quest " + index + ": " + description, GamePictureManager.skin);
            nameLabel.setFontScale(fontScale);
            nameLabel.setColor(fontColor);

            Image lockIcon = new Image(isLocked ? GamePictureManager.lockedIcon : GamePictureManager.unlockedIcon);
            lockIcon.setSize(24, 24);

            Image statusIcon = new Image(isDone ? GamePictureManager.yesIcon : GamePictureManager.noIcon);
            statusIcon.setSize(24, 24);

            TextButton doButton = new TextButton("Do Quest", GamePictureManager.skin);

            if (isLocked || isDone) {
                doButton.setDisabled(true);
                doButton.setColor(Color.DARK_GRAY);
            }

            doButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Result result = NPCController.doQuest(npc, index);
                    showResult(result);
                    closeWindow();
                }
            });

            contentTable.add(nameLabel).left();
            contentTable.add(lockIcon).size(24).padLeft(40);
            contentTable.add(statusIcon).size(24).padLeft(30);
            contentTable.add(doButton).padLeft(15).row();
        }

        add(contentTable).pad(20);
        pack();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
