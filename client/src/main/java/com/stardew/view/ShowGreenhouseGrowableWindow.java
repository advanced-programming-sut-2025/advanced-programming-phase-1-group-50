package com.stardew.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.stardew.model.GrowableDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.view.windows.CloseableWindow;

import java.util.ArrayList;

public class ShowGreenhouseGrowableWindow extends CloseableWindow {
    public ShowGreenhouseGrowableWindow(Stage stage , ArrayList<GrowableDTO> dtos) {
        super("Greenhouse" , stage);

        // تغییر رنگ عنوان
        Label titleLabel = getTitleLabel();
        Label.LabelStyle titleLabelStyle = titleLabel.getStyle();
        titleLabelStyle.fontColor = Color.YELLOW;
        getTitleLabel().setStyle(titleLabelStyle);

        pad(25, 5, 20, 0);
        setSize(900, 700);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
        setColor(Color.PINK);


        Table table = new Table();
        table.defaults().pad(10).left();


        for (GrowableDTO dto : dtos) {
            String text = String.format("%s - Level: %d", dto.getDescription(), dto.getLevelOfGrowth());
            Label label = new Label(text, GamePictureManager.skin);
            label.setColor(Color.GREEN);
            table.add(label).left().row();
        }

        add(table).expand().fill();
    }
}
