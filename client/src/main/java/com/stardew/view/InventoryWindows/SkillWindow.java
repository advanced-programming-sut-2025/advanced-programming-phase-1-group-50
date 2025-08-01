package com.stardew.view.InventoryWindows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
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

public class SkillWindow extends CloseableWindow {
    private final ImageButton OKButton;
    private final ImageButton farmingButton;
    private final ImageButton miningButton;
    private final ImageButton fishingButton;
    private final ImageButton foragingButton;

    private final ProgressBar farmingBar;
    private final ProgressBar miningBar;
    private final ProgressBar fishingBar;
    private final ProgressBar foragingBar;

    private final Label farmingLevelLabel;
    private final Label miningLevelLabel;
    private final Label fishingLevelLabel;
    private final Label foragingLevelLabel;

    public SkillWindow(Stage stage , int farmingLevel , int miningLevel , int fishingLevel , int foragingLevel , int farmingRate , int foragingRate , int miningRate , int fishingRate) {
        super("skill window", stage);

        Label titleLabel = getTitleLabel();

        setSize(600, 400);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);
        pad(15, 10, 15, 10);
        setColor(Color.ORANGE);
        align(Align.top);

        // Title bar buttons
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = GamePictureManager.OKButtonUp;
        style.imageOver = new TextureRegionDrawable(GamePictureManager.OKButtonDown);
        style.imageDown = GamePictureManager.OKButtonDown;
        OKButton = new ImageButton(style);

        getTitleTable().clear();
        getTitleTable().left();
        getTitleTable().add(titleLabel).expandX().left();
        getTitleTable().add(OKButton).padRight(5).right();
        getTitleTable().add(closeButton).right();


        miningButton = new ImageButton(new TextureRegionDrawable(GamePictureManager.steelPickaxeTexture));
        farmingButton = new ImageButton(new TextureRegionDrawable(GamePictureManager.steelHoeTexture));
        fishingButton = new ImageButton(GamePictureManager.fishingTextureDrawable);
        foragingButton = new ImageButton(GamePictureManager.foragingTextureDrawable);


        Label miningLabel = new Label("Ability : mining", GamePictureManager.skin);
        miningLabel.setColor(Color.BLACK);
        Table tooltipTable = new Table();
        tooltipTable.setBackground(createWhiteBackground());
        tooltipTable.add(miningLabel).pad(5);

        Tooltip<Table> miningTooltip = new Tooltip<>(tooltipTable);

        Label farmingLabel = new Label("Ability : farming", GamePictureManager.skin);
        farmingLabel.setColor(Color.BLACK);
        Table farmingTable = new Table();
        farmingTable.setBackground(createWhiteBackground());
        farmingTable.add(farmingLabel).pad(5);
        Tooltip<Table> farmingTooltip = new Tooltip<>(farmingTable);


        Label fishingLabel = new Label("Ability : fishing", GamePictureManager.skin);
        fishingLabel.setColor(Color.BLACK);
        Table fishingTable = new Table();
        fishingTable.setBackground(createWhiteBackground());
        fishingTable.add(fishingLabel).pad(5);
        Tooltip<Table> fishingTooltip = new Tooltip<>(fishingTable);

        Label foragingLabel = new Label("Ability : foraging", GamePictureManager.skin);
        foragingLabel.setColor(Color.BLACK);
        Table foragingTable = new Table();
        foragingTable.setBackground(createWhiteBackground());
        foragingTable.add(foragingLabel).pad(5);
        Tooltip<Table> foragingTooltip = new Tooltip<>(foragingTable);

        miningButton.addListener(miningTooltip);
        farmingButton.addListener(farmingTooltip);
        fishingButton.addListener(fishingTooltip);
        foragingButton.addListener(foragingTooltip);

        TooltipManager.getInstance().initialTime = 0.3f;
        TooltipManager.getInstance().hideAll();


        miningBar = new ProgressBar(0, requiredRateForLevel(miningLevel), 1, false, GamePictureManager.skin);
        farmingBar = new ProgressBar(0, requiredRateForLevel(farmingLevel), 1, false, GamePictureManager.skin);
        fishingBar = new ProgressBar(0, requiredRateForLevel(fishingLevel), 1, false, GamePictureManager.skin);
        foragingBar = new ProgressBar(0, requiredRateForLevel(foragingLevel), 1, false, GamePictureManager.skin);

        miningBar.setValue(miningRate);
        farmingBar.setValue(farmingRate);
        fishingBar.setValue(fishingRate);
        foragingBar.setValue(foragingRate);


        miningLevelLabel = new Label(String.format("%d/%d   %d" , miningRate
            , requiredRateForLevel(miningLevel)
            , miningLevel) , GamePictureManager.skin);
        farmingLevelLabel = new Label(String.format("%d/%d   %d" , farmingRate
            , requiredRateForLevel(farmingLevel)
            , farmingLevel), GamePictureManager.skin);
        fishingLevelLabel = new Label(String.format("%d/%d   %d" , fishingRate
            , requiredRateForLevel(fishingLevel)
            , fishingLevel), GamePictureManager.skin);
        foragingLevelLabel = new Label(String.format("%d/%d   %d" , foragingRate
            , requiredRateForLevel(foragingLevel)
            , foragingLevel), GamePictureManager.skin);


        Table content = new Table();
        content.defaults().pad(10).left().expandX();

        content.add(miningButton).size(48).padRight(10);
        content.add(miningBar).width(300).padRight(10);
        content.add(miningLevelLabel);
        content.row();

        content.add(farmingButton).size(48).padRight(10);
        content.add(farmingBar).width(300).padRight(10);
        content.add(farmingLevelLabel);
        content.row();

        content.add(fishingButton).size(48).padRight(10);
        content.add(fishingBar).width(300).padRight(10);
        content.add(fishingLevelLabel);
        content.row();

        content.add(foragingButton).size(48).padRight(10);
        content.add(foragingBar).width(300).padRight(10);
        content.add(foragingLevelLabel);

        add(content).expand().fill();
    }

    private Drawable createWhiteBackground() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(new TextureRegion(texture));
    }
    private int requiredRateForLevel(int level) {
        return 100 * level + 50;
    }

}
