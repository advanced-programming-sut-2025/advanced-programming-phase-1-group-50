package com.stardew;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.LoginAndRegisterMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static SpriteBatch batch;
    private static Main main;


    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        main.setScreen(new LoginAndRegisterMenu(GamePictureManager.skin));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
