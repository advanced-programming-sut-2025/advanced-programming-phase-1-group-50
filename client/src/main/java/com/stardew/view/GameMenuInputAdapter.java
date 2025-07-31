
package com.stardew.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.InventoryItemDTO;
import com.stardew.models.ShippingBin;
import com.stardew.models.stores.Store;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.GridMap.TileSelectionWindow;
import com.stardew.view.InventoryWindows.HotBarActor;
import com.stardew.view.InventoryWindows.InventoryWindow;
import com.stardew.view.InventoryWindows.MapWindow;
import com.stardew.view.RefrigeratorView.RefrigeratorWindow;
import com.stardew.view.SellProductWindow.ShippingBinWindow;
import com.stardew.view.StoreWindows.StoreClosedMessageWindow;
import com.stardew.view.StoreWindows.StoreWindow;
import com.stardew.view.cheatConsole.CheatWindow;
import com.stardew.view.windows.CookingWindow;
import com.stardew.view.windows.CraftingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GameMenuInputAdapter extends InputAdapter {
//    private final GameModel model;
    private final int id;
    private final Set<Integer> keys = new HashSet<>();
    private final Set<Integer> justPressedKeys = new HashSet<>();
    private float lastVx, lastVy;

    private Stage stage;
    private HotBarActor hotBar;


    public GameMenuInputAdapter(int id) {
        this.id = id;
    }

//    public GameMenuInputAdapter(GameModel model) {
//        this.model = model;
//    }

    @Override
    public boolean keyDown(int keycode) {
        keys.add(keycode);
        justPressedKeys.add(keycode);
        return true;
//        if(hotBar != null) {
//            if(keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9) {
//                hotBar.setSelectedIndex(keycode - Input.Keys.NUM_1);
//
//            }
//            else if(keycode == Input.Keys.NUM_0){
//                hotBar.setSelectedIndex(9);
//            }
//        }
//        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        keys.remove(keycode);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        Player currentPlayer = App.getGame().getCurrentPlayingPlayer();
//
//        int startX = App.getGame().getMap().getFarmStartX(currentPlayer , App.getGame());
//        int startY = App.getGame().getMap().getFarmStartY(currentPlayer , App.getGame());
//
//        Vector2 stageCoords = stage.screenToStageCoordinates(new Vector2(screenX, screenY));
//        int indexTileX = ((int)(stageCoords.x / GamePictureManager.TILE_SIZE)) + startX;
//        int indexTileY = ((int)(stageCoords.y / GamePictureManager.TILE_SIZE)) + startY;
//
//        model.handleClickTile(indexTileX, indexTileY);


        return true;
    }

    public void update(float delta) {
//        Player p = App.getGame().getCurrentPlayingPlayer();
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

        sendMoveEventIfNeed(vx, vy, dir, delta);


        if ((keys.contains(Input.Keys.SHIFT_LEFT) || keys.contains(Input.Keys.SHIFT_RIGHT)) &&
            justPressedKeys.contains(Input.Keys.L)) {
            stage.addActor(new CheatWindow(stage));
        }

        if (justPressedKeys.contains(Input.Keys.SPACE)) {
            stage.addActor(new TileSelectionWindow(stage, 5, 3));
        }

        if(justPressedKeys.contains(Input.Keys.M)){
            stage.addActor(new MapWindow(stage));
        }

        if (justPressedKeys.contains(Input.Keys.B)) {
            stage.addActor(new CraftingWindow(stage));
        }

        if (justPressedKeys.contains(Input.Keys.E)) {
            stage.addActor(new CookingWindow(stage));
        }

        if(justPressedKeys.contains(Input.Keys.ESCAPE)){
            sendInventoryShow();
        }

        if (justPressedKeys.contains(Input.Keys.R)) {
            stage.addActor(new RefrigeratorWindow(stage));
        }


        justPressedKeys.clear();
    }

    private void sendMoveEventIfNeed(float vx, float vy, int dir, float delta) {

        float length = (float) Math.sqrt(vx * vx + vy * vy);
        if (length > 0) {
            vx /= length;
            vy /= length;
        } else {
            dir = 0;
        }

        if (vx == lastVx && vy == lastVy) return;

        vx *= delta;
        vy *= delta;
        lastVx = vx;
        lastVy = vy;

        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("event", Event.Moving);
        body.put("vx", vx);
        body.put("vy", vy);
        body.put("dir", dir);
        Message message = new Message(body, MessageType.EVENT_IN_GAME);
        NetworkManager.getConnection().sendMessage(message);
    }

    private void sendInventoryShow(){
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("event", Event.ShowInventory);
        Message response = NetworkManager.getConnection().sendAndWaitForResponse(new Message(body , MessageType.EVENT_IN_GAME) , 500);
        if(response != null && response.getType() == MessageType.SHOW_INVENTORY_RESULT){
            ArrayList<InventoryItemDTO> dto = response.getFromBody("inventory", new TypeToken<ArrayList<InventoryItemDTO>>(){}.getType());
            String username = response.getFromBody("username");
            System.out.println("hello");
            stage.addActor(new InventoryWindow(stage , hotBar , dto , username));
        }
    }

    public void createStoreWindow(Store store) {
        stage.addActor(new StoreWindow(stage , store));
    }

    public void showClosedStoreMessage() {
        stage.addActor(new StoreClosedMessageWindow(stage));
    }

    public void createShippingBinWindow(ShippingBin bin) {
        stage.addActor(new ShippingBinWindow(stage , bin));
    }



    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void setHotBar(HotBarActor hotBar) {
        this.hotBar = hotBar;
    }

    public HotBarActor getHotBar() {
        return hotBar;
    }

    @Override
    public boolean scrolled(float amountX , float amountY){
//        if (hotBar != null) {
//            int current = hotBar.getSelectedIndex();
//            int count = hotBar.getItemCount();
//
//            if (amountY > 0) {
//                hotBar.setSelectedIndex((current + 1) % count);
//            } else if (amountY < 0) {
//                hotBar.setSelectedIndex((current - 1 + count) % count);
//            }
//        }
        return true;
    }


    private void sendEvent(Event event) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("event", event);
        Message message = new Message(body, MessageType.EVENT_IN_GAME);
        NetworkManager.getConnection().sendMessage(message);
    }

}
