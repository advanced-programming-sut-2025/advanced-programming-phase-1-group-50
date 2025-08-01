package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.google.gson.reflect.TypeToken;
import com.stardew.model.GameState;
import com.stardew.model.InventoryItemDTO;
import com.stardew.model.TextureID;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.InventoryItem;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.tools.Tool;
import com.stardew.models.userInfo.Player;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.PlayersRelationsWindows.FriendshipWindow;
import com.stardew.view.windows.CloseableWindow;
import com.badlogic.gdx.graphics.Color;
import com.stardew.view.windows.CookingWindow;
import com.stardew.view.windows.CraftingWindow;

import java.util.*;

public class InventoryWindow extends CloseableWindow {

    private final Label playerNameLabel;
    private final ImageButton skillsButton;
    private final ImageButton socialButton;
    private final ImageButton mapButton;
    private final ImageButton exitButton;
    private final ImageButton trashButton;
    private final ImageButton journalButton;
    private final ImageButton shuffleButton;
    private final ImageButton friendshipButton;
    private final HotBarActor hotBar;
    private final BackpackGridActor backpackGrid;
    private final ScrollPane backpackScrollPane;
    private static InventoryWindow currentInstance = null;
    private final int id;


    public InventoryWindow(Stage stage , HotBarActor hotBar , ArrayList<InventoryItemDTO> dto , String username , int id) {
        super("Inventory", stage);
        this.hotBar = hotBar;
        this.id = id;
        currentInstance = this;


        setSize(750, 500);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        pad(25, 10, 15, 0);
        setColor(Color.ORANGE);
        align(Align.top);

        Label.LabelStyle st = new Label.LabelStyle();
        st.font = GamePictureManager.smallFont;
        st.fontColor = Color.BLACK;
        playerNameLabel = new Label("" , st);
        playerNameLabel.setFontScale(1.5f);

        playerNameLabel.setText(username);
        add(playerNameLabel).left().padBottom(10).center();
        row();



        backpackGrid = new BackpackGridActor(dto);

        backpackScrollPane = new ScrollPane(backpackGrid, GamePictureManager.skin);
        backpackScrollPane.setFadeScrollBars(false);
        backpackScrollPane.setScrollingDisabled(false, false);
        backpackScrollPane.setOverscroll(true, true);


        add(backpackScrollPane).height(210).width(505).padTop(50).padBottom(10).center().top();
        row();
        backpackScrollPane.layout();
        backpackScrollPane.setScrollPercentY(1f);


        trashButton = new ImageButton(GamePictureManager.trashDrawable);
        trashButton.setSize(50 , 70);
        trashButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int xx = backpackGrid.getSelectedX();
                int yy = backpackGrid.getSelectedY();
                if(xx == -1 && yy == -1) {
                    return false;
                }



                InventoryItemDTO item = backpackGrid.getInventoryItemByXAndY(xx , yy);
                if(item != null) {
                    HashMap<String , Object> body = new HashMap<>();
                    body.put("id" , id);
                    body.put("itemDTO", item);
                    body.put("event" , Event.RemoveItem);
                    Message message = new Message(body , MessageType.EVENT_IN_GAME);
                    NetworkManager.getConnection().sendMessage(message);
//                    if(item instanceof Tool t){
//                        p.getBackpack().removeTool(t);
//                    }
//
//                    else if(item instanceof Ingredient ing){
//                        p.getBackpack().removeIngredients(ing , p.getBackpack().getIngredientQuantity().get(ing));
//                    }
                }
//                hotBar.update();
//                backpackGrid.update();
                return true;
            }
        });



        socialButton = new ImageButton(GamePictureManager.socialHeartTextureDrawable);
        socialButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", id);
                    body.put("event", Event.GetRelationWithNPCInfo);
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null && response.getType().equals(MessageType.GET_RELATION_INFO_RESULT)) {
                        int relationWithAbigail = response.getIntFromBody("relationWithAbigail");
                        int relationWithHarvey = response.getIntFromBody("relationWithHarvey");
                        int relationWithLeah = response.getIntFromBody("relationWithLeah");
                        int relationWithSebastian = response.getIntFromBody("relationWithSebastian");
                        int relationWithRobin = response.getIntFromBody("relationWithRobin");

                        Gdx.app.postRunnable(() -> stage.addActor(new SocialWindow(stage , relationWithAbigail , relationWithHarvey
                            , relationWithLeah , relationWithSebastian , relationWithRobin )));
                    }
                }).start();


                return true;
            }
        });


        skillsButton = new ImageButton(GamePictureManager.skillsTextureDrawable);
        skillsButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
//                stage.addActor(new SkillWindow(stage));
                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", id);
                    body.put("event", Event.GetSkillInfo);

                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if (response != null && response.getType() == MessageType.GET_SKILL_INFO_RESULT) {
                        int farmingLevel = response.getIntFromBody("farmingLevel");
                        int foragingLevel = response.getIntFromBody("foragingLevel");
                        int miningLevel = response.getIntFromBody("miningLevel");
                        int fishingLevel = response.getIntFromBody("fishingLevel");

                        int farmingRate = response.getIntFromBody("farmingRate");
                        int foragingRate = response.getIntFromBody("foragingRate");
                        int miningRate = response.getIntFromBody("miningRate");
                        int fishingRate = response.getIntFromBody("fishingRate");

                        Gdx.app.postRunnable(() -> stage.addActor(new SkillWindow(stage, farmingLevel, miningLevel
                            , fishingLevel , foragingLevel
                        ,farmingRate,foragingRate
                            ,miningRate,fishingRate)));


                    }
                }).start();
                return true;
            }
        });


        mapButton = new ImageButton(GamePictureManager.mapTextureDrawable);
        mapButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
//                stage.addActor(new MapWindow(stage));

                new Thread(() -> {
                    HashMap<String, Object> body = new HashMap<>();
                    body.put("id", id);
                    body.put("event", Event.GetMapInfo);
                    Message message = new Message(body, MessageType.EVENT_IN_GAME);
                    Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                    if(response != null && response.getType() == MessageType.GET_MAP_INFO_RESULT) {
                        TextureID[][] tiles = response.getFromBody("tiles", new TypeToken<TextureID[][]>(){}.getType());

                        Gdx.app.postRunnable(() -> stage.addActor(new MapWindow(stage , tiles)));
                    }
                }).start();
                return true;
            }
        });


        exitButton = new ImageButton(GamePictureManager.exitTextureDrawable);
        exitButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                stage.addActor(new SettingWindow(stage));


                return true;
            }

        });

        shuffleButton = new ImageButton(GamePictureManager.shuffleButtonDrawable);
        shuffleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                App.getGame().getCurrentPlayingPlayer().shuffleInventoryItems();
                HashMap<String , Object> body = new HashMap<>();
                body.put("id", id);
                body.put("event", Event.ShuffleInventory);
                Message message = new Message(body, MessageType.EVENT_IN_GAME);
                NetworkManager.getConnection().sendMessage(message);
            }
        });

        journalButton = new ImageButton(GamePictureManager.journalTextureDrawable);
        journalButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new JournalWindow(stage));
            }
        });

        friendshipButton = new ImageButton(GamePictureManager.friendshipTextureDrawable);
        friendshipButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                stage.addActor(new FriendshipWindow(stage));
                return true;
            }
        });


        Table buttonRowTable = new Table();
        buttonRowTable.add(trashButton).width(50).height(70).padRight(10);
        buttonRowTable.add(socialButton).width(50).height(70).padRight(10);
        buttonRowTable.add(skillsButton).width(50).height(70).padRight(10);
        buttonRowTable.add(mapButton).width(50).height(70).padRight(10);
        buttonRowTable.add(exitButton).width(50).height(70).padRight(10);
        buttonRowTable.add(friendshipButton).width(50).height(70).padRight(10);
        buttonRowTable.add(shuffleButton).width(50).height(70);
        buttonRowTable.add(journalButton).width(50).height(70);

        row();
        add(buttonRowTable).padTop(10).center();





    }

    public Tool findTool(Tool t , ArrayList<Tool> tools) {
        for(Tool tool : tools) {
            if(tool.equals(t)) {
                return tool;
            }
        }
        return null;
    }

    public Ingredient findIngredient(Ingredient ing, HashMap<Ingredient , Integer> map) {
        for(Map.Entry<Ingredient , Integer> entry : map.entrySet()) {
            if(entry.getKey().equals(ing)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public void updateDTO(ArrayList<InventoryItemDTO> dto) {
        backpackGrid.updateDTO(dto);
    }

    @Override
    public boolean remove() {
        boolean removed = super.remove();
        if (removed) currentInstance = null;
        return removed;
    }

    public static boolean isOpen() {
        return currentInstance != null;
    }



    public static InventoryWindow getInstance() {
        return currentInstance;
    }

}
