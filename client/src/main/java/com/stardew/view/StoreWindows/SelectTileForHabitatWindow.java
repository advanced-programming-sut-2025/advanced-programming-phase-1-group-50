package com.stardew.view.StoreWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.controller.CookingAndCraftingControllers.TileSelectionController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.view.GridMap.GridMapActor;
import com.stardew.view.windows.CloseableWindow;

public class SelectTileForHabitatWindow extends CloseableWindow {

    private final TileSelectionController controller;
    private final GridMapActor gridMap;


    public SelectTileForHabitatWindow(Stage stage, StoreWindow storeWindow, String buildingName, int selectionWidth,
                                      int selectionHeight) {
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
        gridMap = new GridMapActor(selectionWidth, selectionHeight);
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
                    AnimalsController controller1 = new AnimalsController();
                    Result result1 = controller1.build(stage, controller.getSelectedTile(), buildingName);
                    switch (buildingName) {
                        case "barn":
                            App.getGame().getMap().getNpcVillage().getCarpenterShop().purchaseBuilding("Barn");
                            break;
                        case "big_barn":
                            App.getGame().getMap().getNpcVillage().getCarpenterShop().purchaseBuilding("Big Barn");
                            break;
                        case "deluxe_barn":
                            App.getGame().getMap().getNpcVillage().getCarpenterShop().purchaseBuilding("Deluxe Barn");
                            break;
                        case "coop":
                            App.getGame().getMap().getNpcVillage().getCarpenterShop().purchaseBuilding("Coop");
                            break;
                        case "big_coop":
                            App.getGame().getMap().getNpcVillage().getCarpenterShop().purchaseBuilding("Big Coop");
                            break;
                        case "deluxe_coop":
                            App.getGame().getMap().getNpcVillage().getCarpenterShop().purchaseBuilding("Deluxe Coop");
                            break;
                    }
                    storeWindow.refreshProducts();
                    showResult(result1);
                } else {
                    showResult(result);
                }

            }
        });
    }

}
