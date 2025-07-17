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
import com.stardew.models.animals.FishType;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;
import java.util.Random;

public class MiniGameWindow extends CloseableWindow {
    private final Image fishingSystem = new Image(GamePictureManager.fishingSystem);//TODO
    private final Image greenBar = new Image(GamePictureManager.greenBar);//TODO
    private final Image fishImage = new Image(GamePictureManager.normalFish);//TODO
    private ProgressBar successBar;
    private final Fish[] fishes;
    private final ArrayList<Fish> caughtFishes = new ArrayList<>();
    private Fish currentFish;
    private int numberOfPlayedFish = 0;
    private float collisionAmount;
    private float restTime;
    private final float greenBarSpeed = 300f;


    public MiniGameWindow(Stage stage, Fish[] fishes) {
        super(" Mini Game", stage);
        this.fishes = fishes;

        Label titleLabel = getTitleLabel();
        Label.LabelStyle titleLabelStyle = titleLabel.getStyle();
        titleLabelStyle.fontColor = Color.YELLOW;
        getTitleLabel().setStyle(titleLabelStyle);

        pad(25, 5, 20, 0);
        pack();
        setSize(900, 800);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        setColor(Color.BLUE);//TODO

        initializeGame();
    }

    private void initializeGame() {
        for (Fish fish : fishes) {
            fish.getPosition().set(430, new Random().nextFloat(600) + 90); //TODO
        }
        currentFish = fishes[0];

        fishingSystem.setSize(300, 750);
        fishingSystem.setPosition(getWidth() / 2 - fishingSystem.getWidth() / 2, getHeight() / 2 - fishingSystem.getHeight() / 2);
        greenBar.setPosition(fishingSystem.getX() + 128, 100); //TODO
        greenBar.setSize(55, 150);
        fishImage.setPosition(currentFish.getPosition().x, currentFish.getPosition().y);
        fishImage.setVisible(false);
        successBar = new ProgressBar(0, 50, 0.1f, true, GamePictureManager.skin);
        successBar.setAnimateDuration(0.1f);
        successBar.setHeight(680);
        successBar.setPosition(530, 70); //TODO
        addActor(fishingSystem);
        addActor(greenBar);
        addActor(fishImage);
        addActor(successBar);

        startNewRound();
    }

    private void startNewRound() {
        fishImage.setVisible(false);
        successBar.setValue(0);
        if (numberOfPlayedFish < fishes.length) {
            restTime = new Random().nextFloat(4f) + 4;
            currentFish = fishes[numberOfPlayedFish];
            if (FishType.isLegendary(currentFish.getType())) fishImage.setDrawable(GamePictureManager.legendFish);
        }
        collisionAmount = successBar.getMaxValue() / 6f;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (restTime > 0) {
            restTime -= delta;
            updateGreenBarMovement(delta);
            return;
        }

//        if (isFinished()) {
//            TODO
//            return;
//        }

        fishImage.setVisible(true);
        updateGreenBarMovement(delta);
        currentFish.update(delta);
        updateFishPosition();
        updateSuccessBar(delta);
        checkCatchFish();

    }

    private boolean isFinished() {
        return numberOfPlayedFish == fishes.length; // || closed window
    }

    private void updateSuccessBar(float delta) {
        if (isFishInGreenBar())
            collisionAmount += 6*delta;
        else
            collisionAmount -= 6*delta;

        successBar.setValue(collisionAmount);

        if (successBar.getValue() < successBar.getMaxValue() / 7)
            successBar.setColor(Color.RED);
        else if (successBar.getValue() > (6 * successBar.getMaxValue() / 8))
            successBar.setColor(Color.GREEN);
        else
            successBar.setColor(Color.WHITE);
    }

    private boolean isFishInGreenBar() {
        Rectangle fishRectangle = new Rectangle(fishImage.getX(), fishImage.getY(), fishImage.getImageWidth(), fishImage.getImageHeight());  //TODO
        Rectangle greenBarRectangle = new Rectangle(greenBar.getX(), greenBar.getY(), greenBar.getImageWidth(), greenBar.getImageHeight()); //TODO
        return greenBarRectangle.contains(fishRectangle);
    }

    private void checkCatchFish() {
        if (successBar.getValue() == successBar.getMaxValue()) {
            numberOfPlayedFish++;
            caughtFishes.add(currentFish);
            startNewRound();
        }
        else if (successBar.getValue() == successBar.getMinValue()) {
            numberOfPlayedFish++;
            startNewRound();
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
        if (newY < 85) newY = 85;
        if (newY > 590) newY = 590;

        greenBar.setPosition(newX, newY);
    }

    private void updateFishPosition() {
        fishImage.setPosition(currentFish.getPosition().x, currentFish.getPosition().y);
    }
}
