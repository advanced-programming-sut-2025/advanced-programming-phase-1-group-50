package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.InventoryItem;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.tools.Tool;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InventoryWindow extends CloseableWindow {

    private final Label playerNameLabel;
    private final ImageButton skillsButton;
    private final ImageButton socialButton;
    private final ImageButton mapButton;
    private final ImageButton exitButton;
    private final ImageButton trashButton;
    private final TextButton shuffleButton;
    private final HotBarActor hotBar;
    private final BackpackGridActor backpackGrid;
    private final ScrollPane backpackScrollPane;


    public InventoryWindow(Stage stage , HotBarActor hotBar) {
        super("Inventory", stage);
        this.hotBar = hotBar;


        setSize(700, 500);
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

        playerNameLabel.setText(App.getGame().getCurrentPlayingPlayer().getUsername());
        add(playerNameLabel).left().padBottom(10).center();
        row();



        backpackGrid = new BackpackGridActor();

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

                Player p = App.getGame().getCurrentPlayingPlayer();

                InventoryItem item = backpackGrid.getInventoryItemByXAndY(xx , yy);
                if(item != null) {
                    if(item instanceof Tool t){
                        p.getBackpack().removeTool(t);
                    }

                    else if(item instanceof Ingredient ing){
                        p.getBackpack().removeIngredients(ing , p.getBackpack().getIngredientQuantity().get(ing));
                    }
                }
                hotBar.update();
                backpackGrid.update();
                return true;
            }
        });



        socialButton = new ImageButton(GamePictureManager.socialHeartTextureDrawable);
        socialButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                stage.addActor(new SocialWindow(stage));
                return true;
            }
        });


        skillsButton = new ImageButton(GamePictureManager.skillsTextureDrawable);
        skillsButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                stage.addActor(new SkillWindow(stage));
                return true;
            }
        });


        mapButton = new ImageButton(GamePictureManager.mapTextureDrawable);
        mapButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                stage.addActor(new MapWindow(stage));
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

        shuffleButton = new TextButton("shuffle" , GamePictureManager.skin);
        shuffleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.getGame().getCurrentPlayingPlayer().shuffleInventoryItems();
            }
        });


        Table buttonRowTable = new Table();
        buttonRowTable.add(trashButton).width(50).height(70).padRight(10);
        buttonRowTable.add(socialButton).width(50).height(70).padRight(10);
        buttonRowTable.add(skillsButton).width(50).height(70).padRight(10);
        buttonRowTable.add(mapButton).width(50).height(70).padRight(10);
        buttonRowTable.add(exitButton).width(50).height(70).padRight(10);
        buttonRowTable.add(shuffleButton).width(120).height(70);

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

}
