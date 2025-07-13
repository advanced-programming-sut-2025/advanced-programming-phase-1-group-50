package com.stardew.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;
import com.stardew.view.GridMap.TileSelectionWindow;
import com.stardew.view.cheatConsole.CheatWindow;
import com.stardew.view.windows.CookingWindow;
import com.stardew.view.windows.CraftingWindow;

import java.util.HashSet;
import java.util.Set;

public class GameMenuInputAdapter extends InputAdapter {
    private final GameModel model;
    private final Set<Integer> keys = new HashSet<>();
    private final Set<Integer> justPressedKeys = new HashSet<>();
    private boolean showingMap = false;
    private Stage stage;


    public GameMenuInputAdapter(GameModel model) {
        this.model = model;
    }

    @Override
    public boolean keyDown(int keycode) {
        keys.add(keycode);
        justPressedKeys.add(keycode);
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        keys.remove(keycode);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Player currentPlayer = App.getGame().getCurrentPlayingPlayer();

        int startX = App.getGame().getMap().getFarmStartX(currentPlayer);
        int startY = App.getGame().getMap().getFarmStartY(currentPlayer);

        Vector2 stageCoords = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
        int indexTileX = ((int)(stageCoords.x / GamePictureManager.TILE_SIZE)) + startX;
        int indexTileY = ((int)(stageCoords.y / GamePictureManager.TILE_SIZE)) + startY;

//        System.out.println("tile X: " + indexTileX + ", Y: " + indexTileY); //TODO for planting

        return true;
    }

    public void update(float delta) {
        Player p = App.getGame().getCurrentPlayingPlayer();
        float vx = 0 , vy = 0;
        int dir = 0;

        if(keys.contains(Input.Keys.W)){
            vy += 1;
            dir = 3;
        }

        if(keys.contains(Input.Keys.S)){
            vy -= 1;
            dir = 1;
        }

        if(keys.contains(Input.Keys.A)){
            vx -= 1;
            dir = 4;
        }

        if(keys.contains(Input.Keys.D)){
            vx += 1;
            dir = 2;
        }

        showingMap = keys.contains(Input.Keys.M);

        if ((keys.contains(Input.Keys.SHIFT_LEFT) || keys.contains(Input.Keys.SHIFT_RIGHT)) &&
            justPressedKeys.contains(Input.Keys.L)) {
            stage.addActor(new CheatWindow(stage));
        }

        if (justPressedKeys.contains(Input.Keys.SPACE)) {
            stage.addActor(new TileSelectionWindow(stage, 5, 3));
        }

        if (justPressedKeys.contains(Input.Keys.B)) {
            stage.addActor(new CraftingWindow(stage));
        }

        if (justPressedKeys.contains(Input.Keys.E)) {
            stage.addActor(new CookingWindow(stage));
        }

        if (justPressedKeys.contains(Input.Keys.Q)) {
            new AnimalsController().build(stage, "big_coop");
        }

        if (justPressedKeys.contains(Input.Keys.I)) {
            System.out.println(new AnimalsController().buyAnimal("Goat", "amir2"));
        }


        handlePlayerMove(p, vx, vy, dir, delta);

        justPressedKeys.clear();
    }

    private void handlePlayerMove(Player p, float vx, float vy, int dir, float delta) {

        float length = (float) Math.sqrt(vx * vx + vy * vy);
        if(length > 0){
            vx/=length;
            vy/=length;
            p.setMoveDirection(dir);
        }else{
            p.setMoveDirection(0);
        }

        float speed = p.getSpeed();
        p.setVelocity(vx * speed, vy * speed);
        model.getPlayerController().update(delta);


    }

    public boolean isShowingMap() {
        return showingMap;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
