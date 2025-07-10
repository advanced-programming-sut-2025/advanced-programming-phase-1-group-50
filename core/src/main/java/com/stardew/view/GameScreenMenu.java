package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.controller.PlayerController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;
import com.stardew.view.windows.SmartTooltip;

public class GameScreenMenu implements Screen {

    private GameModel gameModel;
    private GameRenderer gameRenderer;
    private GameMenuInputAdapter gameMenuInputAdapter;
    private Stage stage;

    public GameScreenMenu(){
        initializeGame();
    }

    public void initializeGame(){

        gameModel = new GameModel(App.getGame().getMap() , 250 , 200);
        gameModel.setPlayerController(new PlayerController(App.getGame().getCurrentPlayingPlayer(), gameModel));
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel);
        gameRenderer = new GameRenderer(gameModel , gameMenuInputAdapter);

        stage = new Stage(new ScreenViewport(gameModel.getCamera()));
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(gameMenuInputAdapter);
        Gdx.input.setInputProcessor(inputMultiplexer);

        SmartTooltip.initialize(stage, GamePictureManager.skin);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameModel.update(v);
        gameRenderer.render();
        gameMenuInputAdapter.update(v);

        stage.act(v);
        stage.draw();
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
