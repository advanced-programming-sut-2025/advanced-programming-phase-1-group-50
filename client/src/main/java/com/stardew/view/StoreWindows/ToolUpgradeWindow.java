package com.stardew.view.StoreWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.view.windows.CloseableWindow;

public class ToolUpgradeWindow extends CloseableWindow {

    private final Table contentTable;

    public ToolUpgradeWindow(Stage stage) {
        super("Tool Upgrade", stage);

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
                Result result = upgradeTool(selectedTool);
                if (result != null) {
                    showingResult(result);
                } else {
                    remove();
                }
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

    private Result upgradeTool(String toolName) {
        return switch (toolName) {
            case "Axe" -> App.getGame().getMap().getNpcVillage().getBlacksmith().upgradeTool("axe");
            case "Hoe" -> App.getGame().getMap().getNpcVillage().getBlacksmith().upgradeTool("hoe");
            case "Pickaxe" -> App.getGame().getMap().getNpcVillage().getBlacksmith().upgradeTool("pickaxe");
            case "Watering Can" -> App.getGame().getMap().getNpcVillage().getBlacksmith().upgradeTool("wateringcan");
            default -> null;
        };
    }
}
