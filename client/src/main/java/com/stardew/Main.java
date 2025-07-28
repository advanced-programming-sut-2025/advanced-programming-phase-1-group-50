package com.stardew;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.NetworkManager;
import com.stardew.view.LoginAndRegisterMenu;

import java.io.IOException;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private final String host;
    private final int port;
    private static SpriteBatch batch;
    private static Main main;

    public Main(String host, int port) {
        this.host = host;
        this.port = port;
    }


    @Override
    public void create() {
        try {
            NetworkManager.connect(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        if (getScreen() != null) {
            getScreen().dispose();
        }

        if (NetworkManager.getConnection() != null) {
            NetworkManager.getConnection().end();
            try {
                NetworkManager.getConnection().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }
}
