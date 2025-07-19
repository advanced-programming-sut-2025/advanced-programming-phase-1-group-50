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
import com.stardew.view.windows.SmartTooltip;

import java.util.HashMap;

public class FriendshipWindow extends CloseableWindow {
    private final HashMap<Player, RelationWithPlayers> relations = new HashMap<>();

    public FriendshipWindow(Stage stage) {
        super("Friendships", stage);
        initializeRelations();
        createUI();
    }

    protected void initializeRelations() {
        relations.clear();
        for (Player player : App.getGame().getPlayers()) {
            if (!player.equals(App.getGame().getCurrentPlayingPlayer())) {
                relations.put(player, PlayersRelationController.getFriendshipLevelsWithPlayers(player));
            }
        }
    }

    private void createUI() {
        Table mainTable = new Table();
        mainTable.top().pad(10).defaults().pad(10);

        for (Player player : relations.keySet()) {
            RelationWithPlayers relation = relations.get(player);

            Label nameLabel = new Label(player.getUsername(), GamePictureManager.skin);
            Label levelLabel = new Label(relation.toString(), GamePictureManager.skin);

            TextButton giftButton = new TextButton("Gift", GamePictureManager.skin);
            giftButton.setDisabled(!relation.canGift());

            if (!relation.canGift()) {
                giftButton.setColor(Color.GRAY);

                SmartTooltip tooltip = SmartTooltip.getInstance();

                giftButton.addListener(new InputListener() {
                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        tooltip.show("      Friendship level too low to gift      ");
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        tooltip.hide();
                    }
                });
            }

            giftButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (relation.canGift()) {
                        openGiftMenu(player);
                    }
                }
            });

            Table row = new Table();
            row.add(nameLabel).left().padRight(80);
            row.add(levelLabel).left().padRight(80);
            row.add(giftButton).left();

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

    private void openGiftMenu(Player player) {
        GiftMenuWindow giftMenuWindow = new GiftMenuWindow(stage,this ,player);
        stage.addActor(giftMenuWindow);
    }
}
