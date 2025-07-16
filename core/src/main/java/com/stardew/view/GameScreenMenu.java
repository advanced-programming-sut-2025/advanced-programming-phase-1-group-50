package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.controller.EnergyManager;
import com.stardew.controller.PlayerController;
import com.stardew.controller.TimeManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;
import com.stardew.models.date.Weather;
import com.stardew.view.InventoryWindows.HotBarActor;
import com.stardew.view.windows.SmartTooltip;

public class GameScreenMenu implements Screen {

    private GameModel gameModel;
    private GameRenderer gameRenderer;
    private GameMenuInputAdapter gameMenuInputAdapter;
    private Stage stage;

    private final HotBarActor hotBarActor= new HotBarActor();

    private SpriteBatch batch;
    private final Stage uiStage = new Stage(new ScreenViewport());

    private final TimeManager timeManager = new TimeManager(uiStage);
    private final EnergyManager energyManager = new EnergyManager(uiStage);

    private final WeatherManager weatherManager = new WeatherManager();



    public GameScreenMenu(){
        initializeGame();
        uiStage.addActor(hotBarActor);

    }

    public void initializeGame(){

        gameModel = new GameModel(App.getGame().getMap() , 250 , 200 , hotBarActor);
        timeManager.setGameModel(gameModel);
        weatherManager.setGameModel(gameModel);
        gameModel.setPlayerController(new PlayerController(App.getGame().getCurrentPlayingPlayer(), gameModel));
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel);
        gameMenuInputAdapter.setHotBar(hotBarActor);
        batch = Main.getBatch();
        gameRenderer = new GameRenderer(gameModel, gameMenuInputAdapter, batch);

        stage = new Stage(new ScreenViewport(gameModel.getCamera()));

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(gameMenuInputAdapter);
        Gdx.input.setInputProcessor(inputMultiplexer);

        SmartTooltip.initialize(stage, GamePictureManager.skin);
        addStoresImages();

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


        batch.setProjectionMatrix(gameModel.getCamera().combined);
        if(App.getGame().getTime().getWeather().equals(Weather.Rainy)) {
            weatherManager.render(v);
        }
        batch.begin();

        timeManager.updateTime(v);
        energyManager.update();
        gameModel.update(v);
        gameRenderer.render();
        gameMenuInputAdapter.update(v);
        if(App.getGame().getTime().getWeather().equals(Weather.Rainy)){
            weatherManager.draw(batch);
        }


        batch.end();

        stage.act(v);
        stage.draw();

        uiStage.act(v);
        uiStage.draw();


        timeManager.checkForDayTransition();
        timeManager.updateNightOverlay();
        timeManager.changeTileTextureInWinter();
        timeManager.changeTileTextureInSpring();
        timeManager.setWateredTile(v);
    }

    private void addStoresImages() {
        stage.addActor(App.getGame().getMap().getNpcVillage().getBlacksmith().getStoreImage());
        stage.addActor(App.getGame().getMap().getNpcVillage().getCarpenterShop().getStoreImage());
        stage.addActor(App.getGame().getMap().getNpcVillage().getFishShop().getStoreImage());
        stage.addActor(App.getGame().getMap().getNpcVillage().getJojaMart().getStoreImage());
        stage.addActor(App.getGame().getMap().getNpcVillage().getMarnieRanch().getStoreImage());
        stage.addActor(App.getGame().getMap().getNpcVillage().getPierreGeneralStore().getStoreImage());
        stage.addActor(App.getGame().getMap().getNpcVillage().getStardopSaloon().getStoreImage());
    }

    public GameMenuInputAdapter getGameMenuInputAdapter() {
        return gameMenuInputAdapter;
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



}
