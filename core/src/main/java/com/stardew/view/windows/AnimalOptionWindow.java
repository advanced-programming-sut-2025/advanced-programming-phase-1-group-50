package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.animals.Animal;

public class AnimalOptionWindow extends CloseableWindow {
    private final AnimalsController controller = new AnimalsController();
    private final TextButton feedButton;
    private final TextButton petButton;
    private final TextButton shepherdButton;
    private final TextButton sellButton;
    private final TextButton collectProductButton;


    public AnimalOptionWindow(Stage stage, Animal animal, float x, float y) {
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

        feedButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.feedHay(animal);
                if (result.getSuccessful())
                    closeWindow();
                else
                    showResult(result);
            }
        });

        petButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.pet(animal);
                if (result.getSuccessful())
                    closeWindow();
                else
                    showResult(result);
            }
        });

        shepherdButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.shepherdAnimal(animal);
                if (result.getSuccessful())
                    closeWindow();
                else
                    showResult(result);
            }
        });

        sellButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.sellAnimal(animal);
                showResult(result);
                if (result.getSuccessful())
                    closeWindow();
            }
        });

        collectProductButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.collectProduce(animal);
                showResult(result);
                if (result.getSuccessful())
                    closeWindow();
            }
        });
    }
}
