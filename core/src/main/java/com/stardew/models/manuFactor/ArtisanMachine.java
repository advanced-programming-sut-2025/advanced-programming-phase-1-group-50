package com.stardew.models.manuFactor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.date.Time;
import com.stardew.models.date.TimeInterval;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.recipes.CraftingRecipes;
import com.stardew.models.userInfo.Player;
import com.stardew.view.craftingWindows.ArtisanOptionWindow;
import com.stardew.view.craftingWindows.ArtisanWindow;

import java.util.HashMap;

public abstract class ArtisanMachine {
    protected Time timeOfRequest;
    protected HashMap<ArtisanGood, TimeInterval> processingTimes;
    protected ArtisanGood producingGood;
    protected boolean cheatReady = false;
    protected Image image;
    protected ProgressBar processBar;
    protected Image endProcessImage;
    protected Label processLabel;

    public ArtisanMachine() {
        processingTimes = new HashMap<>();
        timeOfRequest = null;
        producingGood = null;
        endProcessImage = new Image(GamePictureManager.endProcessTexture);
        processBar = new ProgressBar(0, getTotalProcessingTime(), 1, false, GamePictureManager.skin);
        processLabel = new Label("0 / " + getTotalProcessingTime(), GamePictureManager.skin);
    }

    public void setPosition(Stage stage, float x, float y) {
        image.setPosition(x, y);
        endProcessImage.setPosition(x + image.getWidth() / 2 - endProcessImage.getWidth() / 2, y + image.getHeight() + 10);
        endProcessImage.setVisible(false);
        endProcessImage.setSize(40, 40);
        processBar.setPosition(x + image.getWidth() / 2 - processBar.getWidth() / 2, y + image.getHeight() + 10);
        processBar.setVisible(false);
        processBar.setAnimateDuration(0.5f);
        processLabel.setPosition(x + 5, y + 5);
        processLabel.setVisible(false);

        stage.addActor(image);
        stage.addActor(endProcessImage);
        stage.addActor(processBar);
        stage.addActor(processLabel);

        ArtisanMachine artisanMachine = this;

        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    stage.addActor(new ArtisanWindow(ArtisanAsset.getArtisanAssetByInstance(artisanMachine),
                        stage, x + image.getX(), y + image.getY()));
                    return true;
                }
                else if (button == Input.Buttons.RIGHT) {
                    stage.addActor(new ArtisanOptionWindow(ArtisanAsset.getArtisanAssetByInstance(artisanMachine),
                        stage, x + image.getX(), y + image.getY()));
                    return true;
                }
                return false;
            }
        });
    }

    public void updateMachine() {
        if (timeOfRequest == null) {
            processBar.setVisible(false);
            processLabel.setVisible(false);
            endProcessImage.setVisible(false);
        }
        int passedTime = getPassedTime();
        int totalProcessingTime = getTotalProcessingTime();
        if (isReady().getSuccessful() || passedTime >= totalProcessingTime) {
            processBar.setVisible(false);
            processLabel.setVisible(false);
            endProcessImage.setVisible(true);
        } else {
            processBar.setVisible(true);
            processLabel.setVisible(true);
            endProcessImage.setVisible(false);
            processBar.setValue(passedTime);
            processLabel.setText(((int)processBar.getValue()) + " / " + ((int)processBar.getMaxValue()) + " Hours");
        }
    }

    public void use() {
        timeOfRequest = App.getGame().getTime().clone();
        updateMachine();
    }

    public ArtisanGood get() {
        if (isReady().getSuccessful())
            return producingGood;
        return null;
    }

    public abstract Result canUse(Player player, String product);

    public void reset() {
        timeOfRequest = null;
        producingGood = null;
        cheatReady = false;
        updateMachine();
    }

    public Result isReady() {
        if (cheatReady)
            return new Result(true, "Your product is Ready.");
        if (timeOfRequest == null)
            return new Result(false, "You don't have any artisan goods in machine yet!!");
        int todayDate = App.getGame().getTime().getDate();
        int todayHour = App.getGame().getTime().getHour();
        if (App.getGame().getTime().getSeason() != timeOfRequest.getSeason())
            todayDate += 28;

        int exactDays = timeOfRequest.getDate() + processingTimes.get(producingGood).getDays();
        int exactHours = timeOfRequest.getHour() + processingTimes.get(producingGood).getHours();
        if (exactHours > 22) {
            exactHours -= 22 - 9;
            exactDays += 1;
        }
        if(exactDays < todayDate ||
                exactDays == todayDate &&
                exactHours <= todayHour)
            return new Result(true, "Your product is Ready.");
        return new Result(false, "Your product is Not Ready.");
    }

    public int getPassedTime() {
        if (timeOfRequest == null)
            return 0;
        int todayDate = App.getGame().getTime().getDate();
        int todayHour = App.getGame().getTime().getHour();
        if (App.getGame().getTime().getSeason() != timeOfRequest.getSeason())
            todayDate += 28;
        int passedDays;
        int passedHours;
        if (todayDate > timeOfRequest.getDate()) {
            passedDays = todayDate - timeOfRequest.getDate() - 1;
            passedHours = (22 - timeOfRequest.getHour()) + (todayHour);
        }
        else {
            passedDays = 0;
            passedHours = todayHour - timeOfRequest.getHour();
        }
        return passedDays * (22 - 9) + passedHours;
    }

    public int getTotalProcessingTime() {
        return processingTimes.get(producingGood).getDays() * (22 - 9) + processingTimes.get(producingGood).getHours();
    }

    public void setCheatReady(boolean cheatReady) {
        this.cheatReady = cheatReady;
        updateMachine();
    }

    public static ArtisanMachine getArtisanMachineByRecipe(CraftingRecipes recipe) {
        if (recipe == null)
            return null;
        return switch (recipe) {
            case CharcoalKiln -> new CharcoalKiln();
            case Furnace -> new Furnace();
            case BeeHouse -> new BeeHouse();
            case CheesePress -> new CheesePress();
            case Keg -> new Keg();
            case Loom -> new Loom();
            case MayonnaiseMachine -> new MayonnaiseMachine();
            case OilMaker -> new OilMaker();
            case PreservesJar -> new PreservesJar();
            case Dehydrator -> new Dehydrator();
            case FishSmoker -> new FishSmoker();
            default -> null;
        };
    }
}
