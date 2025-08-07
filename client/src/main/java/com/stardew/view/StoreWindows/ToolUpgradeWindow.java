package com.stardew.view.StoreWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.stardew.controller.StoreController;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

public class ToolUpgradeWindow extends CloseableWindow {

    private final Table contentTable;
    private final int gameId;

    public ToolUpgradeWindow(int gameId,Stage stage) {
        super("Tool Upgrade", stage);
        this.gameId = gameId;

        pad(40);
        defaults().space(20);

        contentTable = new Table();
        add(contentTable).row();

        showToolSelection();
        pack();
        centerOnStage();
    }

    private void showToolSelection() {
        contentTable.clear();

        Label label = new Label("Select a tool to upgrade:", GamePictureManager.skin);
        label.setAlignment(Align.center);

        final SelectBox<String> toolBox = new SelectBox<>(GamePictureManager.skin);
        toolBox.setItems("Axe", "Hoe", "Pickaxe", "Watering Can");

        TextButton upgradeButton = new TextButton("Upgrade", GamePictureManager.skin);
        upgradeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String selectedTool = toolBox.getSelected();
                String toolName = switch (selectedTool) {
                    case "Axe" -> "axe";
                    case "Hoe" -> "hoe";
                    case "Pickaxe" -> "pickaxe";
                    case "Watering Can" -> "wateringcan";
                    default -> "";
                };

                StoreController.upgradeTool(gameId,toolName,result -> showingResult(result));
            }
        });

        contentTable.add(label).width(300).padBottom(10).row();
        contentTable.add(toolBox).width(200).height(40).padBottom(10).row();
        contentTable.add(upgradeButton).width(120).height(45).center().row();
    }

    private void showingResult(Result result) {
        contentTable.clear();

        boolean success = result.getSuccessful();

        Label resultLabel = new Label(result.getMessage(), GamePictureManager.skin);
        resultLabel.setColor(success ? Color.GREEN : Color.RED);
        resultLabel.setWrap(true);
        resultLabel.setAlignment(Align.center);

        TextButton okButton = new TextButton("OK", GamePictureManager.skin);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        contentTable.add(resultLabel).width(300).padBottom(10).row();
        contentTable.add(okButton).width(100).height(40).center().row();

        pack();
        centerOnStage();
    }

    private void centerOnStage() {
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }
}
