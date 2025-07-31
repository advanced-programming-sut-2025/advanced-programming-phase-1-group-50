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
import com.stardew.controller.GameDateAndWeatherController.DateController;
import com.stardew.model.TimeDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.GameModel;
import com.stardew.models.app.App;
import com.stardew.models.date.Season;
import com.stardew.models.date.Time;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Coin;

public class TimeManager {
    private Label timeLabel ;
    private Label seasonAndDayLabel;
    private Label playerGoldLabel;
    private Image blackFadeImage;
    private Image nightOverlay;
    private float start = 0f;
    private Stage uiStage ;
    private final Image clockImage = new Image(GamePictureManager.clockTexture);
    private int previousDigitsOfGold = 0;
    private boolean changeTileTextureInWinter = false;
    private boolean changeTileTextureInSpring = false;
    private boolean firstTimeChangeInSpring = true;

    private TimeDTO timeDTO = new TimeDTO(9 , 1 , "Spring" , "Saturday" , "Sunny") ;





    public TimeManager() {







    }

    public void setStage(Stage uiStage) {
        this.uiStage = uiStage;
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
        if(hour == 22){
            blackFadeImage.getColor().a = 0f;
            blackFadeImage.addAction(Actions.sequence(
                Actions.fadeIn(1f),
                Actions.delay(3f),
                Actions.fadeOut(1f)
            ));

            new DateController().advancedTimeCheatCode(1);

        }
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

    public void updateTime(){
//        start += v;
//        if(start >= 60){
//            start = 0;
//            App.getGame().getTime().advancedHour(1);
//        }
        updateTimeUi();
    }

    private void updateTimeUi(){

        int hour = timeDTO.getHour();
        int day = timeDTO.getDay();
        int minute = (((int)start) / 10) * 10;
        String dayOfTheWeek = timeDTO.getDayOfWeekName();
        String season = timeDTO.getSeasonName();
        String timeText = String.format("%02d:%02d    %2d", hour, minute, day);
        String seasonAndDayOfTheWeek = String.format("%-6s%11s", season, dayOfTheWeek);
//        int playerGold = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity()
//            .getOrDefault(new Coin() , 0);
        timeLabel.setText(timeText);
        seasonAndDayLabel.setText(seasonAndDayOfTheWeek);
//        playerGoldLabel.setText(playerGold + "");
//        setPlayerGoldLabelPosition(playerGold);
    }

    public void initializeTime(){


        clockImage.setSize(330, 200);
        clockImage.setPosition(Gdx.graphics.getWidth() - 330, Gdx.graphics.getHeight() - 220);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;


        timeLabel = new Label("", style);
        timeLabel.setPosition(clockImage.getX() + 170, clockImage.getY() + 178);

        timeLabel.setFontScale(1.4f);

        seasonAndDayLabel = new Label("", style);
        seasonAndDayLabel.setPosition(clockImage.getX() + 135, clockImage.getY() + 113);

        seasonAndDayLabel.setFontScale(1.2f);

        playerGoldLabel = new Label("", style);
        playerGoldLabel.setPosition(clockImage.getX() + 135, clockImage.getY() + 50);

        playerGoldLabel.setFontScale(2.2f);

        uiStage.addActor(clockImage);
        uiStage.addActor(timeLabel);
        uiStage.addActor(seasonAndDayLabel);
        uiStage.addActor(playerGoldLabel);
    }

    private void setPlayerGoldLabelPosition(int gold) {
        if (gold < 0) return;
        int digitsOfGold;
        if (gold > 0)
            digitsOfGold = (int) Math.log10(gold) + 1;
        else
            digitsOfGold = 1;

        if (digitsOfGold != previousDigitsOfGold) {
            previousDigitsOfGold = digitsOfGold;
            playerGoldLabel.setPosition(clockImage.getX() + 280 - digitsOfGold * 17, clockImage.getY() + 55);
        }
    }

    public void changeTileTextureInWinter(){

        if(!changeTileTextureInWinter) {
            if (App.getGame().getTime().getSeason().equals(Season.Winter)) {
                firstTimeChangeInSpring = false;
                changeTileTextureInSpring = false;
//                Tile[][] tiles = gameModel.getMap().getTiles();
                Tile[][] tiles = new Tile[250][200];
                for (Tile[] tile : tiles) {
                    for (Tile value : tile) {
                        value.checkSeasonIsWinter();
                    }
                }
                changeTileTextureInWinter = true;
            }
        }
    }

    public void changeTileTextureInSpring(){
        if(firstTimeChangeInSpring){

            return;
        }
        if(!changeTileTextureInSpring) {
            if (App.getGame().getTime().getSeason().equals(Season.Spring)) {
                changeTileTextureInWinter = false;
                Tile[][] tiles = new Tile[250][200];
                for (Tile[] tile : tiles) {
                    for (Tile value : tile) {
                        value.checkIsSeasonSpring();
                    }
                }
                changeTileTextureInSpring = true;
            }
        }
    }

    public void setWateredTile(float delta){
        Tile[][] tiles = new Tile[250][200];
        for(Tile[] tile : tiles){
            for (Tile value : tile) {
                if(value.isWatered()){
                    value.setWateredTimeTexture(value.getWateredTimeTexture() + delta);
                    if(value.getWateredTimeTexture() > 100){
                        value.setWatered(false);
                        value.setWateredTimeTexture(0);
                    }
                }
            }
        }
    }

    public void setTimeDTO(TimeDTO timeDTO){
        this.timeDTO = timeDTO;
    }

    public void initializeUI(){
        initializeTime();
        initializeFadeImage();
        initializeOverlayImage();
    }

    public Stage getUIStage() {
        return uiStage;
    }
}
