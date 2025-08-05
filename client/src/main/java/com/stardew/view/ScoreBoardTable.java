package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.stardew.model.ScoreBoardDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.GameModel;
import com.stardew.models.SortType;
import com.stardew.view.ReactionWindows.ReactionTable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class ScoreBoardTable extends Table {
    private final Map<String, Array<Label>> playerStats = new HashMap<>();
    private static ScoreBoardTable currentInstance;
    private final TextButton coinSort;
    private final TextButton farmingSort;
    private final TextButton fishingSort;
    private final TextButton foragingSort;
    private final TextButton miningSort;
    Skin skin = GamePictureManager.skin;
    private SortType sortType = SortType.NORMAL;
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

        // Buttons under headers
        coinSort = new TextButton("coin", skin);
        coinSort.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    ScoreBoardTable.getInstance().setSortType(SortType.COIN);
                    return true;
//                updatePlayer();
            }
        });
        farmingSort = new TextButton("farming", skin);
        farmingSort.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScoreBoardTable.getInstance().setSortType(SortType.FARMING);
                return true;
//                updatePlayer();
            }
        });
        fishingSort = new TextButton("fishing", skin);
        fishingSort.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScoreBoardTable.getInstance().setSortType(SortType.FISHING);
                return true;
//                updatePlayer();
            }
        });
        foragingSort = new TextButton("foraging", skin);
        foragingSort.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScoreBoardTable.getInstance().setSortType(SortType.FORAGING);
                return true;
//                updatePlayer();
            }
        });
        miningSort = new TextButton("mining", skin);
        miningSort.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ScoreBoardTable.getInstance().setSortType(SortType.MINING);
                return true;
            }
        });

        add(new Label("", skin)).pad(5); // Empty cell under "Player"
        add(coinSort).padTop(5);
        add(farmingSort).padTop(5);
        add(fishingSort).padTop(5);
        add(foragingSort).padTop(5);
        add(miningSort).padTop(5);
        row();

        pack();
        setPosition(150, Gdx.graphics.getHeight() - this.getHeight() - 30);
    }


    public void updatePlayer() {
        clearChildren();
        playerStats.clear();

        add(new Label("Player", skin)).pad(5);
        add(new Label("Coin", skin)).pad(5);
        add(new Label("Farming", skin)).pad(5);
        add(new Label("Fishing", skin)).pad(5);
        add(new Label("Foraging", skin)).pad(5);
        add(new Label("Mining", skin)).pad(5);
        row();

        // Sort buttons
        add(new Label("", skin)).pad(5);
        add(coinSort).padTop(5);
        add(farmingSort).padTop(5);
        add(fishingSort).padTop(5);
        add(foragingSort).padTop(5);
        add(miningSort).padTop(5);
        row();

        ArrayList<ScoreBoardDTO> scores = gameState.getScoreBoard();
        getSortedScoreBoard(sortType, scores);

        for(ScoreBoardDTO scoreBoardDTO : scores) {
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
                setPosition(150, Gdx.graphics.getHeight() - getHeight() - 30);
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


    private void getSortedScoreBoard(SortType sortType , ArrayList<ScoreBoardDTO> sortedScoreBoard) {

        switch (sortType){
            case NORMAL:
                break;
            case COIN:
                sortedScoreBoard.sort(Comparator.comparingInt(ScoreBoardDTO::getCoin).reversed());
                break;
            case FARMING:
                sortedScoreBoard.sort(Comparator.comparingInt(ScoreBoardDTO::getFarmingLevel).reversed());
                break;
            case FISHING:
                sortedScoreBoard.sort(Comparator.comparingInt(ScoreBoardDTO::getFishingLevel).reversed());
                break;
            case FORAGING:
                sortedScoreBoard.sort(Comparator.comparingInt(ScoreBoardDTO::getForagingLevel).reversed());
                break;
            case MINING:
                sortedScoreBoard.sort(Comparator.comparingInt(ScoreBoardDTO::getMiningLevel).reversed());
                break;


        }
//        for(int i = 0 ; i < sortedScoreBoard.size() ; i++) {
//            System.out.println(i + ": " + sortedScoreBoard.get(i).getUsername() + sortedScoreBoard.get(i).getCoin());
//        }
//        System.out.println(sortType);






    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}



