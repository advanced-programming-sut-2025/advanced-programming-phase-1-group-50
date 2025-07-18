package com.stardew.view.miniGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.animals.Fish;
import com.stardew.models.animals.FishType;
import com.stardew.models.animals.Quality;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;
import java.util.Random;

public class MiniGameWindow extends CloseableWindow {
    private final Image fishingSystem = new Image(GamePictureManager.fishingSystem);
    private final Image greenBar = new Image(GamePictureManager.greenBar);
    private final Image fishImage = new Image(GamePictureManager.normalFish);
    private final Image mainFishImage = new Image(GamePictureManager.normalFish);
    private final Label nameOfFish;
    private ProgressBar successBar;
    private final Table rightPanel = new Table();
    private final Label.LabelStyle labelStyle = new Label.LabelStyle();
    private final Label perfectCatchLabel;
    private final Fish[] fishes;
    private final ArrayList<Fish> caughtFishes = new ArrayList<>();
    private Fish currentFish;
    private int numberOfPlayedFish = 0;
    private float collisionAmount;
    private float restTime;
    private final float greenBarSpeed = 300f;
    private boolean isInGreenBarAllTime = true;
    private final boolean hasSonarBobber;
    private boolean isClosedGame = false;
    private boolean shownResult = false;


    public MiniGameWindow(Stage stage, Fish[] fishes) {
        super(" Mini Game", stage);
        this.fishes = fishes;

        //initialize labelStyle:
        labelStyle.font = GamePictureManager.smallFont;

        //TODO check for having  < SonarBobber >
        hasSonarBobber = false;
        nameOfFish = new Label("", labelStyle);
        mainFishImage.setPosition(40, 200);
        nameOfFish.setPosition(100, 220);
        addActor(mainFishImage);
        addActor(nameOfFish);

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
        setColor(Color.BLUE);

        Table mainContent = new Table();
        mainContent.setFillParent(true);
        add(mainContent).expand().fill();

        rightPanel.top().left().pad(10);

        mainContent.add().width(600);
        mainContent.add(rightPanel).width(300).top().left();


        //perfect_label
        perfectCatchLabel = new Label("", labelStyle);
        perfectCatchLabel.setPosition(35, 600);
        addActor(perfectCatchLabel);

        initializeGame();
    }

    private void initializeGame() {
        for (Fish fish : fishes) {
            fish.getPosition().set(430, new Random().nextFloat(600) + 90);
        }
        currentFish = fishes[0];

        fishingSystem.setSize(300, 750);
        fishingSystem.setPosition(getWidth() / 2 - fishingSystem.getWidth() / 2, getHeight() / 2 - fishingSystem.getHeight() / 2);
        greenBar.setPosition(fishingSystem.getX() + 128, 100);
        greenBar.setSize(55, 150);
        fishImage.setPosition(currentFish.getPosition().x, currentFish.getPosition().y);
        fishImage.setVisible(false);
        successBar = new ProgressBar(0, 50, 0.1f, true, GamePictureManager.skin);
        successBar.setAnimateDuration(0.1f);
        successBar.setHeight(680);
        successBar.setPosition(530, 70);
        addActor(fishingSystem);
        addActor(greenBar);
        addActor(fishImage);
        addActor(successBar);

        startNewRound();
    }

    private void startNewRound() {
        fishImage.setVisible(false);
        successBar.setValue(0);
        mainFishImage.setVisible(false);
        nameOfFish.setVisible(false);
        if (numberOfPlayedFish < fishes.length) {
            isInGreenBarAllTime = true;
            restTime = new Random().nextFloat(4f) + 4;
            currentFish = fishes[numberOfPlayedFish];
            if (FishType.isLegendary(currentFish.getType())) fishImage.setDrawable(GamePictureManager.legendFish);
            collisionAmount = successBar.getMaxValue() / 6f;
            if (hasSonarBobber) {
                mainFishImage.setDrawable(new TextureRegionDrawable(currentFish.getInventoryTexture()));
                nameOfFish.setText(currentFish.toString());
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (isFinished()) {
            updateGreenBarMovement(delta);
            finishAndCloseGame();
            return;
        }

        if (restTime > 0) {
            restTime -= delta;
            updateGreenBarMovement(delta);
            return;
        }

        fishImage.setVisible(true);
        mainFishImage.setVisible(hasSonarBobber);
        nameOfFish.setVisible(hasSonarBobber);
        updateGreenBarMovement(delta);
        currentFish.update(delta);
        updateFishPosition();
        updateSuccessBar(delta);
        checkCatchFish();

    }

    private boolean isFinished() {
        return numberOfPlayedFish == fishes.length || isClosedGame;
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
        Rectangle fishRectangle = new Rectangle(fishImage.getX(), fishImage.getY(), fishImage.getImageWidth(), fishImage.getImageHeight());
        Rectangle greenBarRectangle = new Rectangle(greenBar.getX(), greenBar.getY(), greenBar.getImageWidth(), greenBar.getImageHeight());
        if (!isInGreenBarAllTime)
            return greenBarRectangle.contains(fishRectangle);
        else {
            isInGreenBarAllTime = greenBarRectangle.contains(fishRectangle);
            return isInGreenBarAllTime;
        }
    }

    private void checkCatchFish() {
        if (successBar.getValue() == successBar.getMaxValue()) {
            numberOfPlayedFish++;
            caughtFishes.add(currentFish);
            checkToBePerfect(currentFish);
            showCaughtFish(currentFish);
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

    private void showCaughtFish(Fish fish) {
        Image image = new Image(fish.getInventoryTexture());
        Label label = new Label(fish.toString(), labelStyle);
        Table table = new Table();
        table.add(image).size(32);
        table.add(label).padLeft(10).left().expandX().fillX();
        rightPanel.add(table).padBottom(10).left().row();
    }

    private void checkToBePerfect(Fish fish) {
        if (isInGreenBarAllTime) {
            Quality previousQuality = fish.getQuality();
            if (previousQuality != Quality.Regular) fish.developQuality();
            Quality newQuality = fish.getQuality();

            Player player = App.getGame().getCurrentPlayingPlayer();
            int previousFishingRate = player.getAbility().getFishingRate();
            player.getAbility().increaseFishingRate(((int) (previousFishingRate * 1.4)));
            int newFishingRate = player.getAbility().getFishingRate();

            perfectCatchLabel.setText(
                "You catch this fish PERFECTLY!\n\n\n" +
                "    Previous Quality:  " + previousQuality + "\n\n" +
                "    New Quality:       " + newQuality + "\n\n\n" +
                "    Previous Fishing Skill:  " + previousFishingRate + "\n\n" +
                "    new Fishing Skill:       " + newFishingRate + "\n\n"
            );

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    perfectCatchLabel.setText("");
                }
            }, 5f);
        }
    }



    private void finishAndCloseGame() {

        if (!shownResult) {     //end game (just one time do it)
            Player player = App.getGame().getCurrentPlayingPlayer();
            for (Fish fish : caughtFishes) {
                player.getBackpack().addIngredients(fish, 1);
            }
        }

        if (isClosedGame && !shownResult) {
            showResult(prepareResult());
            shownResult = true;
        }
        if (!isClosedGame && !shownResult) {
            shownResult = true;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    showResult(prepareResult());
                    closeWindow();
                }
            }, 5f);  //timer is for showing last result
        }
    }

    private Result prepareResult() {
        if (caughtFishes.isEmpty())
            return new Result(false, "NO Fish was caught");

        StringBuilder result = new StringBuilder();
        result.append("Caught Fish: \n\n\n");
        for (Fish fish : caughtFishes) {
            result.append(fish.getInfo()).append("\n\n");
        }
        return new Result(true, result.toString());
    }



    @Override
    protected void closeWindow() {
        isClosedGame = true;

        getChildren().forEach(Actor::clearListeners);

        addAction(Actions.sequence(
            Actions.parallel(
                Actions.fadeOut(0.3f),
                Actions.scaleTo(0.7f, 0.7f, 0.3f)
            ),
            Actions.removeActor()
        ));

    }

}
