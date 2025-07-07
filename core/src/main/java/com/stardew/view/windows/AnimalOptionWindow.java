package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.stardew.models.GameAssetManagers.GamePictureManager;

public class AnimalOptionWindow extends CloseableWindow {
    private final TextButton feedButton;
    private final TextButton petButton;
    private final TextButton shepherdButton;
    private final TextButton sellButton;
    private final TextButton collectProductButton;


    public AnimalOptionWindow(Stage stage, float x, float y) {
        super("Options", stage);

        pad(30, 5, 20, 0);
        defaults().space(5);
        pack();
        setPosition(x, y);
        setSize(200, 385);


        feedButton = new TextButton("Feed", GamePictureManager.skin);
        petButton = new TextButton("Pet", GamePictureManager.skin);
        shepherdButton = new TextButton("Shepherd", GamePictureManager.skin);
        sellButton = new TextButton("Sell", GamePictureManager.skin);
        collectProductButton = new TextButton("Collect Product", GamePictureManager.skin);

        add(feedButton).row();
        add(petButton).row();
        add(shepherdButton).row();
        add(sellButton).row();
        add(collectProductButton).row();
    }
}
