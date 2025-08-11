package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.stardew.model.TextureID;
import com.stardew.models.GameAssetManagers.GamePictureManager;

import com.stardew.view.InventoryWindows.MapWindowActor;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;

public class ShowFarmsWindow extends CloseableWindow {
    private MapWindowActor gridMap;
    private final ScrollPane scrollPane;
    public ShowFarmsWindow(Stage stage , TextureID[][] tiles){


        super("show Farm ", stage);


        Label titleLabel = getTitleLabel();
        Label.LabelStyle titleLabelStyle = titleLabel.getStyle();
        titleLabelStyle.fontColor = Color.YELLOW;
        getTitleLabel().setStyle(titleLabelStyle);

        pad(25, 5, 20, 0);
        pack();
        setSize(900, 700);
        setPosition((float) Gdx.graphics.getWidth() /2, (float) Gdx.graphics.getHeight() /2);

        gridMap = new MapWindowActor(tiles , 100 , 75);
        scrollPane = new ScrollPane(gridMap, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);
        scrollPane.setOverscroll(true, true);

        add(scrollPane).expand().fill().pad(20, 20, 20, 20);
        row();
        scrollPane.layout();
        scrollPane.setScrollPercentY(1f);
    }





}
