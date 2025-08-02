package com.stardew.view.ReactionWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.GameModel;
import com.stardew.view.InventoryWindows.InventoryWindow;

public class ReactionTable extends Table {
    private Image currentReaction;
    private static ReactionTable currentInstance;
    private final Label nameLabel;
    private boolean gotReaction = false;
    private float timeReaction = 0f;
    private final float TIME_REMAINING_REACTION = 5f;

    public ReactionTable() {
        currentInstance = this;
//        setSize(350 , 250);
        this.currentReaction = new Image();
        this.nameLabel = new Label("" , GamePictureManager.skin);
        this.add(nameLabel).size(100).left();
        this.add(currentReaction).size(72);
        this.pack();

        this.setBackground(new TextureRegionDrawable(GamePictureManager.emptyTile));
        Gdx.app.postRunnable(() -> {
            this.setPosition(60, Gdx.graphics.getHeight() - this.getHeight() - 10);
        });

    }

    public void update(TextureRegion textureRegion , String username) {
        currentReaction.setDrawable(new TextureRegionDrawable(textureRegion));
        nameLabel.setText(username);
        gotReaction = true;
    }

    @Override
    public boolean remove() {
        boolean removed = super.remove();
        if (removed) currentInstance = null;
        return removed;
    }

    public static boolean isOpen() {
        return currentInstance != null;
    }



    public static ReactionTable getInstance() {
        return currentInstance;
    }

    public void setGotReaction(boolean gotReaction) {
        this.gotReaction = gotReaction;
    }

    public boolean GotReaction() {
        return gotReaction;
    }

    public void clearReaction() {
        gotReaction = false;
        currentReaction .setDrawable(null);
        nameLabel.setText("");
    }


    public void render(float v) {
        if (GotReaction()) {
            timeReaction += v;
            if (timeReaction >= TIME_REMAINING_REACTION) {
                timeReaction = 0f;
                clearReaction();
            }
        }
    }




}
