package com.stardew.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.date.Time;

public class TimeManager {
    private Label timeLabel ;
    private Label seasonAndDayLabel;
    private Label playerGoldLabel;
    private Image blackFadeImage;
    private boolean isFaded = false;
    private Image nightOverlay;
    private final Stage uiStage = new Stage(new ScreenViewport());
    private TextureRegion clockTexture = GamePictureManager.clockTexture;
    private Image clockImage = new Image(clockTexture);



    public TimeManager() {
        initializeTime();
        initializeFadeImage();
        initializeOverlayImage();


    }

    public void initializeFadeImage(){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Texture blackTexture = new Texture(pixmap);
        pixmap.dispose();

        blackFadeImage = new Image(new TextureRegion(blackTexture));
        blackFadeImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        blackFadeImage.getColor().a = 0f;

        uiStage.addActor(blackFadeImage);
    }

    public void checkForDayTransition(){
        Time time = App.getGame().getTime();
        int hour = time.getHour();
        if(hour == 21 && !isFaded){
            isFaded = true;
            blackFadeImage.getColor().a = 0f;
            blackFadeImage.addAction(Actions.sequence(
                Actions.fadeIn(1f),
                Actions.delay(3f),
                Actions.fadeOut(1f)
            ));

        }

        isFaded = false;
    }


    public void loadBlackTexture(){

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        Texture blackTexture = new Texture(pixmap);
        nightOverlay = new Image(blackTexture);
        uiStage.addActor(nightOverlay);

    }

    public void initializeOverlayImage(){
        loadBlackTexture();
        nightOverlay.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        nightOverlay.setColor(0, 0, 0, 0f);

        //TODO  : add tis line to gameScreenMenu
        //stage.addActor(nightOverlay);
    }


    public void updateNightOverlay(){
        int hour = App.getGame().getTime().getHour();
        float targetAlpha;

        if (hour >= 18 && hour < 22) {

            targetAlpha = (hour - 18) / 4f * 0.5f;
        } else if (hour >= 22 || hour < 6) {

            targetAlpha = 0.5f;
        } else {

            targetAlpha = 0f;
        }


        float currentAlpha = nightOverlay.getColor().a;

        nightOverlay.getColor().a = currentAlpha + (targetAlpha - currentAlpha) * 0.05f;

    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public Label getSeasonAndDayLabel() {
        return seasonAndDayLabel;
    }

    public Image getBlackFadeImage() {
        return blackFadeImage;
    }

    public Label getPlayerGoldLabel() {
        return playerGoldLabel;
    }

    public boolean isFaded() {
        return isFaded;
    }

    public Image getNightOverlay() {
        return nightOverlay;
    }

    public Stage getUiStage() {
        return uiStage;
    }

    public void setIsFaded(boolean isFaded) {
        this.isFaded = isFaded;
    }

    public void initializeTime(){


        clockImage.setSize(300, 200);
        clockImage.setPosition(Gdx.graphics.getWidth() - 330, Gdx.graphics.getHeight() - 220);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;


        timeLabel = new Label("", style);
        timeLabel.setPosition(clockImage.getX() + 163, clockImage.getY() + 180);

        timeLabel.setFontScale(1.4f);

        seasonAndDayLabel = new Label("", style);
        seasonAndDayLabel.setPosition(clockImage.getX() + 135, clockImage.getY() + 110);

        seasonAndDayLabel.setFontScale(1.2f);

        playerGoldLabel = new Label("", style);
        playerGoldLabel.setPosition(clockImage.getX() + 135, clockImage.getY() + 50);

        playerGoldLabel.setFontScale(1.2f);

        uiStage.addActor(clockImage);
        uiStage.addActor(timeLabel);
        uiStage.addActor(seasonAndDayLabel);
        uiStage.addActor(playerGoldLabel);
    }
}
