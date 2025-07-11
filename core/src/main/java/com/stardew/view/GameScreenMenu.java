package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.controller.PlayerController;
import com.stardew.controller.TimeManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;
import com.stardew.models.date.Time;
import com.stardew.models.userInfo.Coin;
import com.stardew.view.windows.SmartTooltip;

public class GameScreenMenu implements Screen {

    private GameModel gameModel;
    private GameRenderer gameRenderer;
    private GameMenuInputAdapter gameMenuInputAdapter;
    private Stage stage;
    private Stage uiStage;
    private SpriteBatch batch;
    private float start = 0f;


    private final TimeManager timeManager = new TimeManager();





    public GameScreenMenu(){
        initializeGame();

    }

    public void initializeGame(){

        gameModel = new GameModel(App.getGame().getMap() , 250 , 200);
        gameModel.setPlayerController(new PlayerController(App.getGame().getCurrentPlayingPlayer(), gameModel));
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel);
        batch = Main.getBatch();
        gameRenderer = new GameRenderer(gameModel, gameMenuInputAdapter, batch);

        stage = new Stage(new ScreenViewport(gameModel.getCamera()));
        uiStage = timeManager.getUiStage();
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(gameMenuInputAdapter);
        Gdx.input.setInputProcessor(inputMultiplexer);

        SmartTooltip.initialize(stage, GamePictureManager.skin);

        gameMenuInputAdapter.setStage(stage);
        //stage.addActor(timeManager.getNightOverlay());


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateTime(v);
        updateTimeUi();




        batch.setProjectionMatrix(gameModel.getCamera().combined);
        batch.begin();

        gameModel.update(v);
        gameRenderer.render();
        gameMenuInputAdapter.update(v);

        batch.end();

        stage.act(v);
        stage.draw();

        uiStage.act(v);
        uiStage.draw();

        timeManager.checkForDayTransition();
        timeManager.updateNightOverlay();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

//    public void startTimer(){
//        Timer.schedule(new Timer.Task() {
//            @Override
//            public void run() {
//                App.getGame().getTime().advancedHour(1);
//            }
//        }, 60, 60);
//    }

    public void updateTimeUi(){
        Time time = App.getGame().getTime();
        int hour = time.getHour();
        int day = time.getDate();
        String dayOfTheWeek = time.getDayOfWeek().getDayOfWeek();
        String season = time.getSeason().name();
        String timeText = hour + ":00" + " " + day;
        String seasonAndDayOfTheWeek = season + " " + dayOfTheWeek;
        int playerGold = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity()
            .getOrDefault(new Coin() , 0);
        timeManager.getTimeLabel().setText(timeText);
        timeManager.getSeasonAndDayLabel().setText(seasonAndDayOfTheWeek);
        timeManager.getPlayerGoldLabel().setText(playerGold + "");

    }

    public void updateTime(float v){
        start += v;
        if(start >= 6){
            start = 0;
            App.getGame().getTime().advancedHour(1);
        }
    }









}
