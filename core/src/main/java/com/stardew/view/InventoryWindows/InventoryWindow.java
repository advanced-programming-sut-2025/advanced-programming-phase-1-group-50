package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.InventoryItem;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.tools.Tool;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InventoryWindow extends CloseableWindow {

    private final Label playerNameLabel;
//    private final ImageButton skillsButton;
//    private final ImageButton socialButton;
//    private final ImageButton mapButton;
//    private final ImageButton settingsButton;
    private final ImageButton trashButton;
    private final HotBarActor hotBar;




    private final BackpackGridActor backpackGrid;

    private final ScrollPane backpackScrollPane;
    private final ImageButton OKButton;

    public InventoryWindow(Stage stage , HotBarActor hotBar) {
        super("Inventory", stage);
        this.hotBar = hotBar;

        Label titleLabel = getTitleLabel();

        setSize(600, 400);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        pad(15, 10, 15, 10);
        setColor(Color.ORANGE);
        align(Align.top);

        Label.LabelStyle st = new Label.LabelStyle();
        st.font = new BitmapFont();
        st.fontColor = Color.BLACK;
        playerNameLabel = new Label("" , st);
        playerNameLabel.setFontScale(1.5f);

        playerNameLabel.setText(App.getGame().getCurrentPlayingPlayer().getUsername());
        add(playerNameLabel).left().padBottom(10).center();
        row();



        backpackGrid = new BackpackGridActor();
        backpackGrid.setSize(500, 400);

        backpackScrollPane = new ScrollPane(backpackGrid, GamePictureManager.skin);
        backpackScrollPane.setFadeScrollBars(false);
        backpackScrollPane.setScrollingDisabled(false, false);
        backpackScrollPane.setOverscroll(true, true);


        add(backpackScrollPane).height(5 * 40f + 10).width(10 * 40f + 10).padTop(10).center().top();
        row();




        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = GamePictureManager.OKButtonUp;
        style.imageOver = new TextureRegionDrawable(GamePictureManager.OKButtonDown);
        style.imageOver.setMinWidth(32);
        style.imageOver.setMinHeight(30);
        style.imageDown = GamePictureManager.OKButtonDown;
        OKButton = new ImageButton(style);

        getTitleTable().clear();
        getTitleTable().left();
        getTitleTable().add(titleLabel).expandX().left();
        getTitleTable().add(OKButton).padRight(5).right();
        getTitleTable().add(closeButton).right();

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
                        p.getBackpack().getTools().remove(findTool(t , p.getBackpack().getTools()));
                    }

                    else if(item instanceof Ingredient ing){
                        p.getBackpack().getIngredientQuantity().remove(findIngredient(ing , p.getBackpack().getIngredientQuantity()));
                    }
                }
                p.updateInventoryItems();
                hotBar.update();
                backpackGrid.update();
                return true;
            }
        });
        add(trashButton).width(50).height(70).bottom().left();

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
