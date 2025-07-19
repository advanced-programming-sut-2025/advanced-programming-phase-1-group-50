package com.stardew.view.PlayersRelationsWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.RelationWithPlayers;
import com.stardew.view.windows.CloseableWindow;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import java.util.HashMap;

public class FriendshipWindow extends CloseableWindow {
    private final HashMap<Player, RelationWithPlayers> relations = new HashMap<>();
    private final Table mainTable;

    public FriendshipWindow(Stage stage) {
        super("Friendships", stage);
        mainTable = new Table();
        createUI();
    }

    private void initializeRelations() {
        for (Player player : App.getGame().getPlayers()) {
            if (!player.equals(App.getGame().getCurrentPlayingPlayer())) {
                relations.put(player, PlayersRelationController.getFriendshipLevelsWithPlayers(player));
            }
        }
    }

    protected void createUI() {
        relations.clear();
        mainTable.clear();
        initializeRelations();
        mainTable.top().pad(10).defaults().pad(10);

        for (Player player : relations.keySet()) {
            RelationWithPlayers relation = relations.get(player);

            Label nameLabel = new Label(player.getUsername(), GamePictureManager.skin);
            nameLabel.setFontScale(1.5f);
            nameLabel.setColor(Color.BLACK);
            Label levelLabel = new Label(relation.toString(), GamePictureManager.skin);
            levelLabel.setFontScale(1.5f);
            levelLabel.setColor(Color.BLACK);

            TextButton giftButton = new TextButton("Gift", GamePictureManager.skin);

            giftButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        openGiftMenu(player , relation);
                }
            });

            Table row = new Table();
            row.add(nameLabel).left().padRight(80).width(200);
            row.add(levelLabel).left().padRight(80).width(200);
            row.add(giftButton).left().width(100);

            mainTable.add(row).left().row();
        }

        ScrollPane scrollPane = new ScrollPane(mainTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        add(scrollPane).expand().fill().pad(20);
        pack();

        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    private void openGiftMenu(Player player, RelationWithPlayers relation) {
        GiftMenuWindow giftMenuWindow = new GiftMenuWindow(stage,this ,player,relation);
        stage.addActor(giftMenuWindow);
    }
}
