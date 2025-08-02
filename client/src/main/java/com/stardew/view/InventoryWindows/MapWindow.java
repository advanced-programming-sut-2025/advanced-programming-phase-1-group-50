package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.model.TextureID;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.view.GridMap.GridMapActor;
import com.stardew.view.windows.CloseableWindow;

public class MapWindow extends CloseableWindow {
    private final ImageButton OKButton;
    private final MapWindowActor mapWindowActor;
    private final ScrollPane scrollPane;
    public MapWindow(Stage stage , TextureID[][] tiles) {
        super("Map Window" ,stage );

        Label titleLabel = getTitleLabel();
        Label.LabelStyle titleLabelStyle = titleLabel.getStyle();
        titleLabelStyle.fontColor = Color.YELLOW;
        getTitleLabel().setStyle(titleLabelStyle);

        pad(25, 5, 20, 0);
        pack();
        setSize(900, 700);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        setColor(Color.BROWN);

        mapWindowActor = new MapWindowActor(tiles);
        scrollPane = new ScrollPane(mapWindowActor, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);
        scrollPane.setOverscroll(true, true);

        add(scrollPane).expand().fill().pad(20, 20, 20, 20);
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
