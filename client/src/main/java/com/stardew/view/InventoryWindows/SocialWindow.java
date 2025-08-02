package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Player;
import com.stardew.view.windows.CloseableWindow;

public class SocialWindow extends CloseableWindow {
    private final ImageButton abigailButton;
    private final ImageButton robinButton;
    private final ImageButton sebastianButton;
    private final ImageButton leahButton;
    private final ImageButton harveyButton;
    private final ImageButton OKButton;
    public SocialWindow(Stage stage , int abigail , int harvey , int leah , int sebastian , int robin) {
        super("Social Window" ,stage );
        Label titleLabel = getTitleLabel();

        setSize(600, 400);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        pad(15, 10, 15, 10);
        setColor(Color.ORANGE);
        align(Align.top);



        TextureRegionDrawable abigailDrawable = new TextureRegionDrawable(new TextureRegion(GamePictureManager.abigailTexture));
        TextureRegionDrawable robinDrawable = new TextureRegionDrawable(new TextureRegion(GamePictureManager.robinTexture));
        TextureRegionDrawable sebastianDrawable = new TextureRegionDrawable(new TextureRegion(GamePictureManager.sebastianTexture));
        TextureRegionDrawable leahDrawable = new TextureRegionDrawable(new TextureRegion(GamePictureManager.leahTexture));
        TextureRegionDrawable harveyDrawable = new TextureRegionDrawable(new TextureRegion(GamePictureManager.harveyTexture));
        TextureRegionDrawable heartDrawable = GamePictureManager.socialHeartTextureDrawable;
        Skin skin = GamePictureManager.skin;

        Table contentTable = new Table();
        contentTable.align(Align.topLeft);
        contentTable.padTop(10).padLeft(10);





        abigailButton = new ImageButton(abigailDrawable);
        robinButton = new ImageButton(robinDrawable);
        sebastianButton = new ImageButton(sebastianDrawable);
        leahButton = new ImageButton(leahDrawable);
        harveyButton = new ImageButton(harveyDrawable);


        addCharacterRow(contentTable, abigailButton, "Abigail", abigail, heartDrawable, skin);
        addCharacterRow(contentTable, robinButton, "Robin", robin, heartDrawable, skin);
        addCharacterRow(contentTable, sebastianButton, "Sebastian", sebastian, heartDrawable, skin);
        addCharacterRow(contentTable, leahButton, "Leah", leah, heartDrawable, skin);
        addCharacterRow(contentTable, harveyButton, "Harvey", harvey, heartDrawable, skin);

        add(contentTable).expand().top().left();

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

    private void addCharacterRow(Table table, ImageButton characterButton, String name, int hearts, Drawable heartDrawable, Skin skin){
        table.add(characterButton).width(50).height(50).padRight(10);
        for (int i = 0; i < 4; i++) {
            Image heart = new Image(heartDrawable);
            heart.setColor(i < hearts ? Color.WHITE : Color.GRAY);
            table.add(heart).size(24, 24).pad(2);
        }
        Label nameLabel = new Label(name, skin);
        nameLabel.setColor(Color.BLACK);
        table.add(nameLabel).padLeft(10);
        table.row();
    }
}
