package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;

public class GameScreenMenu implements Screen {

    private GameModel gameModel;
    private GameRenderer gameRenderer;
    private GameMenuInputAdapter gameMenuInputAdapter;

    public GameScreenMenu(){
        initializeGame();
    }

    public void initializeGame(){
        gameModel = new GameModel(App.getGame().getMap() , 100 , 100);
        gameMenuInputAdapter = new GameMenuInputAdapter();
        gameRenderer = new GameRenderer(gameModel);
        Gdx.input.setInputProcessor(gameMenuInputAdapter);

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
