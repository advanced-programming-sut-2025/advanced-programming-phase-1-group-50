package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;

public class EnergyManager {
    private final Stage stage ;
    private ProgressBar progressBar;

    public EnergyManager(Stage stage) {
        this.stage = stage;
        initializeProgressBar();

    }


    public void initializeProgressBar(){
        Player player = App.getGame().getCurrentPlayingPlayer();
        Skin skin = GamePictureManager.skin;
        progressBar = new ProgressBar(0, player.getMaxEnergy(), 1, true, skin);
        progressBar.setValue(player.getEnergy());
        progressBar.setSize(20, 200);
        progressBar.setPosition(20 , Gdx.graphics.getHeight() - 210);
        stage.addActor(progressBar);
    }


    public void update() {
        Player player = App.getGame().getCurrentPlayingPlayer();
        progressBar.setValue(player.getEnergy());
    }

}
