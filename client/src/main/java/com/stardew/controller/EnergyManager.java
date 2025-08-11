package com.stardew.controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.GameModel;

public class EnergyManager {
    private final Stage stage ;
    private ProgressBar progressBar;
    private GameModel gameModel;

    public EnergyManager(GameModel gameModel, Stage stage) {
        this.stage = stage;
        this.gameModel = gameModel;
        initializeProgressBar();

    }


    public void initializeProgressBar(){
        ;
        Skin skin = GamePictureManager.skin;
        progressBar = new ProgressBar(0, 200, 1, true, skin);
        progressBar.setAnimateDuration(0.5f);
        progressBar.setValue(200);
        progressBar.setSize(20, 200);
        progressBar.setPosition(20 , 70);
        stage.addActor(progressBar);
    }


    public void update() {
        int energy = gameModel.getPlayerEnergy();
        progressBar.setValue(energy);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

}
