package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.stardew.models.recipes.CraftingRecipes;

public enum CraftingAsset {
    CherryBomb(GamePictureManager.cherryBombNormal, GamePictureManager.cherryBombDisable, CraftingRecipes.CherryBomb),
    Bomb(GamePictureManager.bombNormal, GamePictureManager.bombDisable, CraftingRecipes.Bomb),
    MegaBomb(GamePictureManager.megaBombNormal, GamePictureManager.megaBombDisable, CraftingRecipes.MegaBomb),
    Sprinkler(GamePictureManager.sprinklerNormal, GamePictureManager.sprinklerDisable, CraftingRecipes.Sprinkler),
    QualitySprinkler(GamePictureManager.qualitySprinklerNormal, GamePictureManager.qualitySprinklerDisable, CraftingRecipes.QualitySprinkler),
    IridiumSprinkler(GamePictureManager.iridiumSprinklerNormal, GamePictureManager.iridiumSprinklerDisable, CraftingRecipes.IridiumSprinkler),
    CharcoalKiln(GamePictureManager.charcoalKilnNormal, GamePictureManager.charcoalKilnDisable, CraftingRecipes.CharcoalKiln),
    Furnace(GamePictureManager.furnaceNormal, GamePictureManager.furnaceDisable, CraftingRecipes.Furnace),
    Scarecrow(GamePictureManager.scarecrowNormal, GamePictureManager.scarecrowDisable, CraftingRecipes.Scarecrow),
    DeluxeScarecrow(GamePictureManager.deluxeScarecrowNormal, GamePictureManager.deluxeScarecrowDisable, CraftingRecipes.DeluxeScarecrow),
    BeeHouse(GamePictureManager.beeHouseNormal, GamePictureManager.beeHouseDisable, CraftingRecipes.BeeHouse),
    CheesePress(GamePictureManager.cheesePressNormal, GamePictureManager.cheesePressDisable, CraftingRecipes.CheesePress),
    Keg(GamePictureManager.kegNormal, GamePictureManager.kegDisable, CraftingRecipes.Keg),
    Loom(GamePictureManager.loomNormal, GamePictureManager.loomDisable, CraftingRecipes.Loom),
    MayonnaiseMachine(GamePictureManager.mayonnaiseMachineNormal, GamePictureManager.mayonnaiseMachineDisable, CraftingRecipes.MayonnaiseMachine),
    OilMaker(GamePictureManager.oilMakerNormal, GamePictureManager.oilMakerDisable, CraftingRecipes.OilMaker),
    PreservesJar(GamePictureManager.preservesJarNormal, GamePictureManager.preservesJarDisable, CraftingRecipes.PreservesJar),
    Dehydrator(GamePictureManager.dehydratorNormal, GamePictureManager.dehydratorDisable, CraftingRecipes.Dehydrator),
    FishSmoker(GamePictureManager.fishSmokerNormal, GamePictureManager.fishSmokerDisable, CraftingRecipes.FishSmoker),
    MysticTreeSeed(GamePictureManager.mysticTreeSeedNormal, GamePictureManager.mysticTreeSeedDisable, CraftingRecipes.MysticTreeSeed),;

    private final TextureRegionDrawable normalImage;
    private final TextureRegionDrawable disabledImage;
    private final ImageButton.ImageButtonStyle style;
    private final CraftingRecipes recipe;
    private final Label description;

    CraftingAsset(TextureRegionDrawable normalImage, TextureRegionDrawable disabledImage, CraftingRecipes recipe) {
        this.normalImage = normalImage;
        this.disabledImage = disabledImage;
        this.recipe = recipe;

        this.style = new ImageButton.ImageButtonStyle();
        style.imageUp = normalImage;
        style.imageDisabled = disabledImage;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.GOLD;
        labelStyle.background = GamePictureManager.woodBackground;
        description = new Label(CraftingRecipes.getDescription(recipe), labelStyle);
        description.setAlignment(Align.center);
    }

    public TextureRegionDrawable getNormalImage() {
        return normalImage;
    }

    public TextureRegionDrawable getDisabledImage() {
        return disabledImage;
    }

    public ImageButton.ImageButtonStyle getStyle() {
        return style;
    }

    public CraftingRecipes getRecipe() {
        return recipe;
    }

    public Label getDescription() {
        return description;
    }
}
