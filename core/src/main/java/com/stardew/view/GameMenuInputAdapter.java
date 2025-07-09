package com.stardew.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;

import java.util.HashSet;
import java.util.Set;

public class GameMenuInputAdapter extends InputAdapter {
    private GameModel model;
    private final Set<Integer> keys = new HashSet<>();
    private boolean showingMap = false;


    public GameMenuInputAdapter(GameModel model) {
        this.model = model;
    }

    @Override
    public boolean keyDown(int keycode) {
        keys.add(keycode);
        if(keycode == Input.Keys.M) {
            showingMap = true;
        }
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        keys.remove(keycode);
        if(keycode == Input.Keys.M) {
            showingMap = false;
        }
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


}
