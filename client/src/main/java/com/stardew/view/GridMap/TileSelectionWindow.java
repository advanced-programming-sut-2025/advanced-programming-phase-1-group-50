package com.stardew.view.GridMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.model.PlaceableDTO;
import com.stardew.model.TileDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class TileSelectionWindow extends CloseableWindow {
    private BiConsumer<Integer, Integer> onOKCallback;
    private final ImageButton OKButton;
    private final GridMapActor gridMap;
    private final ScrollPane scrollPane;


    public TileSelectionWindow(Stage stage, int selectionWidth, int selectionHeight,
                               ArrayList<TileDTO> tiles, ArrayList<PlaceableDTO> placeables) {
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


//        controller = new TileSelectionController(selectionWidth, selectionHeight);
        gridMap = new GridMapActor(selectionWidth, selectionHeight , tiles, placeables);
        scrollPane = new ScrollPane(gridMap, GamePictureManager.skin);
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
        OKButton = new ImageButton(style);
        getTitleTable().clear();
        getTitleTable().left();
        getTitleTable().add(titleLabel).expandX().left();
        getTitleTable().add(OKButton).padRight(5).right();
        getTitleTable().add(closeButton).right();

        OKButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                Result result = controller.checkTileSelection(gridMap.getSelectedTile());
//                if (result.getSuccessful())
//                    closeWindow();
//                else
//                    showResult(result);
                int selectedX = gridMap.getSelectedX();
                int selectedY = gridMap.getSelectedY();
                if (selectedX != -1 && selectedY != -1) {
                    if (onOKCallback != null) {
                        onOKCallback.accept(selectedX, selectedY);
                        closeWindow();
                    }
                }
                else {
                    showResult(new Result(false, "Please select a tile!"));
                }
            }
        });
    }

    public void setOnOKCallback(BiConsumer<Integer, Integer> callback) {
        this.onOKCallback = callback;
    }

}
