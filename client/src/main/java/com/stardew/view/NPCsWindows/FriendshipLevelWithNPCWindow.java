package com.stardew.view.NPCsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.controller.NPCController.NPCController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.NPCs.NPC;
import com.stardew.models.NPCs.RelationWithNPC;
import com.stardew.view.windows.CloseableWindow;

public class FriendshipLevelWithNPCWindow extends CloseableWindow {

    public FriendshipLevelWithNPCWindow(Stage stage, NPC npc) {
        super("Friendship with " + npc.getType().getName(), stage);

        RelationWithNPC relation = NPCController.getRelationWithNPC(npc);
        float fontScale = 1.2f;
        Color fontColor = Color.BLACK;
        int maxXp = npc.getType().getMaxFriendShipLevel();

        Table contentTable = new Table();
        contentTable.defaults().pad(8).left();

        Label levelLabel = new Label("Friendship Tier:      " + relation.getNpcFriendshipLevel().toString(), GamePictureManager.skin);
        levelLabel.setFontScale(fontScale);
        levelLabel.setColor(fontColor);

        Label xpLabel = new Label("Friendship XP:       " + relation.getNumericalFriendShipLevel() + " / " + maxXp, GamePictureManager.skin);
        xpLabel.setFontScale(fontScale);
        xpLabel.setColor(fontColor);

        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(
            GamePictureManager.skin.newDrawable("white", new Color(0.9f, 0.9f, 0.9f, 1f)),
            GamePictureManager.skin.newDrawable("white", new Color(1f,0.6f,0f,1f))
        );

        barStyle.background.setMinHeight(20);
        barStyle.knob.setMinHeight(20);
        barStyle.knobBefore = barStyle.knob;

        ProgressBar xpBar = new ProgressBar(0, maxXp, 1, false, barStyle);
        xpBar.setValue(relation.getNumericalFriendShipLevel());
        xpBar.setWidth(280);
        xpBar.setHeight(20);

        contentTable.add(levelLabel).row();
        contentTable.add(xpLabel).padTop(4).row();
        contentTable.add(xpBar).width(280).height(20).padBottom(12).row();

        Table questStatusTable = new Table();
        questStatusTable.defaults().pad(5).left();

        Image secondQuestIcon = new Image(relation.isSecondQuestLocked() ? GamePictureManager.lockedIcon : GamePictureManager.unlockedIcon);
        Label secondQuestLabel = new Label("Second Quest          ", GamePictureManager.skin);
        secondQuestLabel.setFontScale(fontScale);
        secondQuestLabel.setColor(fontColor);

        questStatusTable.add(secondQuestLabel);
        questStatusTable.add(secondQuestIcon).size(24).padLeft(8).row();

        Image thirdQuestIcon = new Image(relation.isThirdQuestLocked() ? GamePictureManager.lockedIcon : GamePictureManager.unlockedIcon);
        Label thirdQuestLabel = new Label("Third Quest        ", GamePictureManager.skin);
        thirdQuestLabel.setFontScale(fontScale);
        thirdQuestLabel.setColor(fontColor);

        questStatusTable.add(thirdQuestLabel);
        questStatusTable.add(thirdQuestIcon).size(24).padLeft(8).row();

        contentTable.add(questStatusTable).row();

        Table spokenTable = new Table();
        spokenTable.defaults().pad(5).left();
        Label spokenLabel = new Label("Spoken Today          ", GamePictureManager.skin);
        spokenLabel.setFontScale(fontScale);
        spokenLabel.setColor(fontColor);
        Image spokenIcon = new Image(relation.isFirstTimeToSpeakWithNPC() ? GamePictureManager.noIcon : GamePictureManager.yesIcon);
        spokenTable.add(spokenLabel);
        spokenTable.add(spokenIcon).size(24).padLeft(8).row();
        contentTable.add(spokenTable).row();

        Table giftedTable = new Table();
        giftedTable.defaults().pad(5).left();
        Label giftedLabel = new Label("Gifted Today           ", GamePictureManager.skin);
        giftedLabel.setFontScale(fontScale);
        giftedLabel.setColor(fontColor);
        Image giftedIcon = new Image(relation.isFirstTimeGiftToNPC() ? GamePictureManager.noIcon : GamePictureManager.yesIcon);
        giftedTable.add(giftedLabel);
        giftedTable.add(giftedIcon).size(24).padLeft(8).row();
        contentTable.add(giftedTable).row();

        for (Cell<?> cell : contentTable.getCells()) {
            if (cell.getActor() instanceof Label) {
                ((Label) cell.getActor()).setFontScale(fontScale);
            }
        }

        add(contentTable).pad(20);
        pack();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
