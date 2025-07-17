package com.stardew.view.miniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.Fish;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;
import java.util.Random;

public class MiniGameWindow extends CloseableWindow {
    private final Image fishingSystem = new Image();//TODO
    private final Image greenBar = new Image();//TODO
    private final Image fishImage = new Image();//TODO
    private ProgressBar successBar;
    private final Fish[] fishes;
    private final ArrayList<Fish> caughtFishes = new ArrayList<>();
    private Fish currentFish;
    private int numberOfPlayedFish = 0;
    private float collisionAmount = 25f;
    private float restTime;
    private final float greenBarSpeed = 10f;


    public MiniGameWindow(Stage stage, Fish[] fishes) {
        super(" Mini Game", stage);
        this.fishes = fishes;

        Label titleLabel = getTitleLabel();
        Label.LabelStyle titleLabelStyle = titleLabel.getStyle();
        titleLabelStyle.fontColor = Color.YELLOW;
        getTitleLabel().setStyle(titleLabelStyle);

        pad(25, 5, 20, 0);
        pack();
        setSize(900, 700);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        setColor(Color.BROWN);//TODO

        initializeGame();
    }

    private void initializeGame() {
        currentFish = fishes[0];
        restTime = new Random().nextFloat(4f) + 4;
        for (Fish fish : fishes) {
            fish.getPosition().set(100, 100); //TODO
        }
        greenBar.setPosition(100, 100); //TODO
        fishImage.setPosition(currentFish.getPosition().x, currentFish.getPosition().y);
        fishImage.setVisible(false);
        successBar = new ProgressBar(0, 100, 0.1f, true, GamePictureManager.skin);
        successBar.setAnimateDuration(0.5f);
        successBar.setPosition(200, 100); //TODO
        add(fishingSystem).expand().fill().pad(50, 50, 50, 50);
        addActor(greenBar);
        addActor(fishImage);
        addActor(successBar);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (restTime > 0) {
            restTime -= delta;
            updateGreenBarMovement(delta);
            return;
        }

        if (isFinished()) {
            //TODO
            return;
        }

        fishImage.setVisible(true);
        updateGreenBarMovement(delta);
        currentFish.update(delta);
        updateFishPosition();
        checkCatchFish();
        updateSuccessBar(delta);

    }

    private boolean isFinished() {
        return numberOfPlayedFish == fishes.length; // || closed window
    }

    private void updateSuccessBar(float delta) {
        if (isFishInGreenBar())
            collisionAmount += delta;
        else
            collisionAmount -= delta;

        successBar.setValue(collisionAmount);
    }

    private boolean isFishInGreenBar() {
        Rectangle fishRectangle = new Rectangle();  //TODO
        Rectangle greenBarRectangle = new Rectangle(); //TODO
        return fishRectangle.overlaps(greenBarRectangle);
    }

    private void checkCatchFish() {
        if (successBar.getValue() == successBar.getMaxValue()) {
            numberOfPlayedFish++;
            fishImage.setVisible(false);
            caughtFishes.add(currentFish);
            if (numberOfPlayedFish < fishes.length) {
                restTime = new Random().nextFloat(4f) + 4;
                currentFish = fishes[numberOfPlayedFish];
                //TODO fishImage  position (can be random)
                //TODO check for being legendary
            }
            successBar.setValue(successBar.getMaxValue() / 2f);
            collisionAmount = successBar.getMaxValue() / 2f;
        }
        else if (successBar.getValue() == successBar.getMinValue()) {
            numberOfPlayedFish++;
            fishImage.setVisible(false);
            if (numberOfPlayedFish < fishes.length) {
                restTime = new Random().nextFloat(4f) + 4;
                currentFish = fishes[numberOfPlayedFish];
                //TODO fishImage  position (can be random)
                //TODO check for being legendary
            }
            successBar.setValue(successBar.getMaxValue() / 2f);
            collisionAmount = successBar.getMaxValue() / 2f;
        }
    }

    private void updateGreenBarMovement(float delta) {
        float vy = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            vy += 1;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            vy -= 1;

        if (vy == 0)
            return;

        float newX = greenBar.getX();
        float newY = greenBar.getY() + vy * greenBarSpeed * delta;

        greenBar.setPosition(newX, newY);
    }

    private void updateFishPosition() {
        fishImage.setPosition(currentFish.getPosition().x, currentFish.getPosition().y);
    }
}
