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
import com.stardew.controller.GameModelController;
import com.stardew.controller.TimeManager;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.GameModel;
import com.stardew.network.GameUpdateRequestThread;
import com.stardew.view.NPCsWindows.NPCsUIManger;
import com.stardew.view.Notification.NotificationManager;
import com.stardew.view.ShippingBin.ShippingBinUIManager;
import com.stardew.view.Stores.UpdatableWindow;
import com.stardew.view.modelsUI.ArtisanMachinesManager;
import com.stardew.view.modelsUI.HabitatUIManager;
import com.stardew.view.InventoryWindows.HotBarActor;
import com.stardew.view.ReactionWindows.ReactionTable;
import com.stardew.view.Stores.StoreUIManager;
import com.stardew.view.miniGame.MiniGameStarter;
import com.stardew.view.scoreBoardTable.ScoreBoardTable;
import com.stardew.view.windows.SmartTooltip;

public class GameScreenMenu implements Screen {
    private final GameUpdateRequestThread updateRequestThread;
    private final GameModel gameModel;
    private final GameRenderer gameRenderer;
    private final GameMenuInputAdapter gameMenuInputAdapter;
    private final Stage stage;
    private final HotBarActor hotBarActor;
    private final SpriteBatch batch;
    private final Stage uiStage;
    private final TimeManager timeManager ;
    private final EnergyManager energyManager ;
    private final WeatherManager weatherManager;
    private final ReactionTable reactionTable;
    private final ScoreBoardTable scoreBoard;
    private double timerForUpdateWindows = 0.0f;



    public GameScreenMenu(GameUpdateRequestThread updateRequestThread, int id) {
        this.updateRequestThread = updateRequestThread;
        this.batch = Main.getBatch();
        this.gameModel = GameModelController.getInstance().getGameModel();
        this.gameRenderer = new GameRenderer(this.batch, gameModel);
        this.stage = new Stage(new ScreenViewport(gameModel.getCamera()));
        this.uiStage = new Stage(new ScreenViewport());
        this.gameMenuInputAdapter = new GameMenuInputAdapter(id, stage);
        this.timeManager = new TimeManager(gameModel, uiStage);
        this.energyManager = new EnergyManager(gameModel, uiStage);
        this.hotBarActor = new HotBarActor(gameModel, id);
        this.weatherManager = new WeatherManager(gameModel);
        gameMenuInputAdapter.setHotBar(hotBarActor);
        this.reactionTable = new ReactionTable();
        this.scoreBoard = new ScoreBoardTable(gameModel);
        uiStage.addActor(reactionTable);
        uiStage.addActor(scoreBoard);
        uiStage.addActor(hotBarActor);



        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(uiStage);
        inputMultiplexer.addProcessor(gameMenuInputAdapter);
        Gdx.input.setInputProcessor(inputMultiplexer);

        SmartTooltip.initialize(stage, GamePictureManager.skin);
        ArtisanMachinesManager.initialize(stage, id);
        HabitatUIManager.initialize(stage, id);
        MiniGameStarter.initialize(stage);
        ShippingBinUIManager.initialize(stage,id);
        WindowOpener.initialize(stage,id);
        StoreUIManager.createAllStoresUI(stage,id);
        NPCsUIManger.createAllNPCsUI(stage,id);
        GreenhouseUIManager.initialize(stage,id);
        NotificationManager.initialize(stage);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(gameModel.getCamera().combined);
        batch.begin();

        timeManager.updateTime();
        timeManager.checkForDayTransition();
        timeManager.updateNightOverlay(gameModel.getTime().getHour());
        energyManager.update();
        gameRenderer.render(delta);
        gameMenuInputAdapter.update(delta);
        weatherManager.render(delta, gameModel.getTime().getWeather());
        weatherManager.draw(batch, gameModel.getTime().getWeather());
        weatherManager.thunder(delta, stage, gameModel.getTime().getWeather());
        reactionTable.render(delta);
        scoreBoard.updatePlayer();
        NotificationManager.getInstance().updatePositions();

        timerForUpdateWindows += delta;
        if (timerForUpdateWindows > 1f) {
            timerForUpdateWindows = 0f;
            UpdatableWindow.refreshAll();
        }

        batch.end();

        stage.act(delta);
        stage.draw();

        uiStage.act(delta);
        uiStage.draw();
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
