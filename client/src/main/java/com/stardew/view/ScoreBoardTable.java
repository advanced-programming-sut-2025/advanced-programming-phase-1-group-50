package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.stardew.model.ScoreBoardDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.GameModel;
import com.stardew.view.ReactionWindows.ReactionTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ScoreBoardTable extends Table {
    private final Map<String, Array<Label>> playerStats = new HashMap<>();
    private static ScoreBoardTable currentInstance;
    Skin skin = GamePictureManager.skin;
    private final GameModel gameState;
    public ScoreBoardTable(GameModel gameState) {
        this.gameState = gameState;

        currentInstance = this;
        pad(10);
        top().left();
        setBackground(new TextureRegionDrawable(GamePictureManager.emptyTile));

        // Header
        add(new Label("Player", skin)).pad(5);
        add(new Label("Coin", skin)).pad(5);
        add(new Label("Farming", skin)).pad(5);
        add(new Label("Fishing", skin)).pad(5);
        add(new Label("Foraging", skin)).pad(5);
        add(new Label("Mining", skin)).pad(5);
        row();

        pack();


        setPosition(20, Gdx.graphics.getHeight() - this.getHeight() - 300);
    }

    public void updatePlayer() {
        ArrayList<ScoreBoardDTO> scoreBoard = gameState.getScoreBoard();
        for(ScoreBoardDTO scoreBoardDTO : scoreBoard) {
            String name = scoreBoardDTO.getUsername();
            int coin = scoreBoardDTO.getCoin();
            int atk = scoreBoardDTO.getFarmingLevel();
            int def = scoreBoardDTO.getFishingLevel();
            int spd = scoreBoardDTO.getForagingLevel();
            int hp = scoreBoardDTO.getMiningLevel();

            if (!playerStats.containsKey(name)) {
                Label nameLabel = new Label(name, skin);
                Label coinLabel = new Label("" + coin, skin);
                Label atkLabel = new Label("" + atk, skin);
                Label defLabel = new Label("" + def, skin);
                Label spdLabel = new Label("" + spd, skin);
                Label hpLabel = new Label("" + hp, skin);

                Array<Label> stats = new Array<>(new Label[]{coinLabel, atkLabel, defLabel, spdLabel, hpLabel});
                playerStats.put(name, stats);

                add(nameLabel).pad(5);
                add(coinLabel).pad(5);
                add(atkLabel).pad(5);
                add(defLabel).pad(5);
                add(spdLabel).pad(5);
                add(hpLabel).pad(5);
                row();
                pack();
                invalidate();
                setPosition(20, Gdx.graphics.getHeight() - getHeight() - 300);
            } else {
                Array<Label> stats = playerStats.get(name);
                stats.get(0).setText("" + coin);
                stats.get(1).setText("" + atk);
                stats.get(2).setText("" + def);
                stats.get(3).setText("" + spd);
                stats.get(4).setText("" + hp);
            }
        }


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



    public static ScoreBoardTable getInstance() {
        return currentInstance;
    }
}



