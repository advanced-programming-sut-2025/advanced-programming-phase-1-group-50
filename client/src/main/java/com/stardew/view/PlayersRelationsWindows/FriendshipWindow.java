package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.models.userInfo.RelationWithPlayers;
import com.stardew.view.windows.CloseableWindow;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.HashMap;

public class FriendshipWindow extends CloseableWindow {
    private final HashMap<String, RelationWithPlayers> relations = new HashMap<>();
    private final Table mainTable;

    public FriendshipWindow(Stage stage, HashMap<String, RelationWithPlayers> inputRelations) {
        super("Friendships", stage);
        mainTable = new Table();
        this.relations.putAll(inputRelations);
        createUI(false);
    }

    private void updateRelations() {
//        relations.clear();
//        for (Player player : App.getGame().getPlayers()) {
//            if (!player.equals(App.getGame().getCurrentPlayingPlayer())) {
//                relations.put(player, PlayersRelationController.getFriendshipLevelsPlayers(player));
//            }
//        }
    }

    protected void createUI(boolean refresh) {
        if (refresh) {
            updateRelations();
        }
        mainTable.clear();
        mainTable.top().pad(10).defaults().pad(10);

        for (String username : relations.keySet()) {
            RelationWithPlayers relation = relations.get(username);

            Label nameLabel = new Label(username, GamePictureManager.skin);
            nameLabel.setFontScale(1.3f);
            nameLabel.setColor(Color.BLACK);

            Label levelLabel = new Label(relation.toString(), GamePictureManager.skin);
            levelLabel.setFontScale(1.3f);
            levelLabel.setColor(Color.OLIVE);

            TextButton giftButton = new TextButton("Gift", GamePictureManager.skin);
            giftButton.setColor(Color.ROYAL);

            Image giftIcon = new Image(GamePictureManager.giftIcon);
            giftIcon.setSize(32, 32);

            Table giftRow = new Table();
            giftRow.add(giftButton).width(100).height(45).padRight(5);
            giftRow.add(giftIcon).size(32, 32);

            giftButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        openGiftMenu(username , relation);
                }
            });

            Table row = new Table();
            row.add(nameLabel).left().padRight(30).width(150);
            row.add(levelLabel).left().padRight(60).width(200);
            row.add(giftRow).left();

            mainTable.add(row).left().row();
        }

        mainTable.center();
        ScrollPane scrollPane = new ScrollPane(mainTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        add(scrollPane).expand().fill().pad(20);
        pack();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    private void openGiftMenu(String username, RelationWithPlayers relation) {
        GiftMenuWindow giftMenuWindow = new GiftMenuWindow(stage, this, null, relation); // TODO
        stage.addActor(giftMenuWindow);
    }
}
