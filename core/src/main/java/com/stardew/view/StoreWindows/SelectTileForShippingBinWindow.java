package com.stardew.view.StoreWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.controller.CookingAndCraftingControllers.TileSelectionController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.view.GridMap.GridMapActor;
import com.stardew.view.windows.CloseableWindow;

public class SelectTileForShippingBinWindow extends CloseableWindow {

    private final TileSelectionController controller;
    private final GridMapActor gridMap;


    public SelectTileForShippingBinWindow(Stage stage, StoreWindow storeWindow, int selectionWidth, int selectionHeight) {
        super(" Select a tile to place: A Rectangle " + selectionWidth + " x " + selectionHeight, stage);

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


        controller = new TileSelectionController(selectionWidth, selectionHeight);
        gridMap = new GridMapActor(selectionWidth, selectionHeight , App.getGame());
        ScrollPane scrollPane = new ScrollPane(gridMap, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);
        scrollPane.setOverscroll(true, true);

        add(scrollPane).expand().fill().pad(20, 20, 20, 20);
        row();
        scrollPane.layout();
        scrollPane.setScrollPercentY(1f);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = GamePictureManager.OKButtonUp;
        style.imageOver = new TextureRegionDrawable(GamePictureManager.OKButtonDown);
        style.imageOver.setMinWidth(32);
        style.imageOver.setMinHeight(30);
        style.imageDown = GamePictureManager.OKButtonDown;
        ImageButton OKButton = new ImageButton(style);
        getTitleTable().clear();
        getTitleTable().left();
        getTitleTable().add(titleLabel).expandX().left();
        getTitleTable().add(OKButton).padRight(5).right();
        getTitleTable().add(closeButton).right();

        OKButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.checkTileSelection(gridMap.getSelectedTile());
                if (result.getSuccessful()) {
                    closeWindow();
                    int xPosition = controller.getSelectedTile().getPosition().getX();
                    int yPosition = controller.getSelectedTile().getPosition().getY();
                    Result result1 = App.getGame().getMap().getNpcVillage().getCarpenterShop().purchaseShippingBin(xPosition,yPosition);
                    storeWindow.refreshProducts();
                    showResult(result1);
                } else {
                    showResult(result);
                }

            }
        });
    }

}
