package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.view.windows.CloseableWindow;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

public class InventoryWindow extends CloseableWindow {
    private final Label playerNameLabel;


    private final BackpackGridActor backpackGrid;

    private final ScrollPane backpackScrollPane;
    private final ImageButton OKButton;

    public InventoryWindow(Stage stage) {
        super("Inventory", stage);


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
    }

}
