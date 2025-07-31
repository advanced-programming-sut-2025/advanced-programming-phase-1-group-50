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
import com.stardew.controller.GameStateController;
import com.stardew.controller.PlayerController;
import com.stardew.controller.TimeManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.ShippingBin;
import com.stardew.models.GameModel;
import com.stardew.models.app.App;
import com.stardew.network.GameUpdateRequestThread;
import com.stardew.view.InventoryWindows.HotBarActor;
import com.stardew.view.windows.SmartTooltip;

public class GameScreenMenu implements Screen {
    private final GameUpdateRequestThread updateRequestThread;
    private GameModel gameState;
    private GameRenderer gameRenderer;

    private GameMenuInputAdapter gameMenuInputAdapter;
    private Stage stage;
    private final int id;
    private final HotBarActor hotBarActor;

    private SpriteBatch batch;
    private final Stage uiStage;

    private final TimeManager timeManager ;
    private final EnergyManager energyManager ;

//    private final WeatherManager weatherManager = new WeatherManager();



    public GameScreenMenu(GameUpdateRequestThread updateRequestThread, int id) {
        this.id = id;
        this.updateRequestThread = updateRequestThread;
        this.batch = Main.getBatch();
        this.gameState = GameStateController.getInstance().getGameState();
        this.gameRenderer = new GameRenderer(this.batch);
        this.stage = new Stage(new ScreenViewport(gameState.getCamera()));
        this.gameMenuInputAdapter = new GameMenuInputAdapter(id);
        this.gameMenuInputAdapter.setGameState(gameState);
        this.gameMenuInputAdapter.setStage(stage);
        this.uiStage = new Stage(new ScreenViewport());
        this.timeManager = new TimeManager(gameState, uiStage);
        this.energyManager = new EnergyManager(gameState, uiStage);
        this.hotBarActor = new HotBarActor(gameState);
        gameMenuInputAdapter.setHotBar(hotBarActor);



        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(gameMenuInputAdapter);
        Gdx.input.setInputProcessor(inputMultiplexer);

//        initializeGame();
        uiStage.addActor(hotBarActor);

    }

//    public void initializeGame(){
//
//        gameModel = new GameModel(App.getGame().getMap() , 250 , 200 , hotBarActor);
//        timeManager.setGameModel(gameModel);
//        weatherManager.setGameModel(gameModel);
//        gameModel.setPlayerController(new PlayerController(App.getGame().getCurrentPlayingPlayer(), gameModel));
//        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel);
//        gameMenuInputAdapter.setHotBar(hotBarActor);
//        batch = Main.getBatch();
//        gameRenderer = new GameRenderer(gameModel, gameMenuInputAdapter, batch);
//
//        stage = new Stage(new ScreenViewport(gameModel.getCamera()));
//
//        InputMultiplexer inputMultiplexer = new InputMultiplexer();
//        inputMultiplexer.addProcessor(stage);
//        inputMultiplexer.addProcessor(uiStage);
//        inputMultiplexer.addProcessor(gameMenuInputAdapter);
//        Gdx.input.setInputProcessor(inputMultiplexer);
//
//        SmartTooltip.initialize(stage, GamePictureManager.skin);
//        addStoresImages();
//
//        gameMenuInputAdapter.setStage(stage);
//        gameModel.setStage(stage);
//        //stage.addActor(timeManager.getNightOverlay());
//
//
//    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(gameState.getCamera().combined);
//        weatherManager.render(v);
        batch.begin();
//
        timeManager.updateTime();
        energyManager.update();
//        gameModel.update(v);
        gameRenderer.render(v);
        gameMenuInputAdapter.update(v);
//        weatherManager.draw(batch);
//
//
        batch.end();
//
        stage.act(v);
        stage.draw();
//
        uiStage.act(v);
        uiStage.draw();
//
//
//        timeManager.checkForDayTransition();
//        timeManager.updateNightOverlay();
//        timeManager.changeTileTextureInWinter();
//        timeManager.changeTileTextureInSpring();
//        timeManager.setWateredTile(v);
//        weatherManager.thunder(v , stage);

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

    public void addShippingBinImage(ShippingBin bin) {
        stage.addActor(bin.getShippingBinImage());
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
        if (updateRequestThread != null) {
            updateRequestThread.stopRequesting();
        }
    }
}
