package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public enum CraftingAsset {
    CherryBomb(GamePictureManager.cherryBombNormal, GamePictureManager.cherryBombDisable),
    Bomb(GamePictureManager.bombNormal, GamePictureManager.bombDisable),
    MegaBomb(GamePictureManager.megaBombNormal, GamePictureManager.megaBombDisable),
    Sprinkler(GamePictureManager.sprinklerNormal, GamePictureManager.sprinklerDisable),
    QualitySprinkler(GamePictureManager.qualitySprinklerNormal, GamePictureManager.qualitySprinklerDisable),
    IridiumSprinkler(GamePictureManager.iridiumSprinklerNormal, GamePictureManager.iridiumSprinklerDisable),
    CharcoalKiln(GamePictureManager.charcoalKilnNormal, GamePictureManager.charcoalKilnDisable),
    Furnace(GamePictureManager.furnaceNormal, GamePictureManager.furnaceDisable),
    Scarecrow(GamePictureManager.scarecrowNormal, GamePictureManager.scarecrowDisable),
    DeluxeScarecrow(GamePictureManager.deluxeScarecrowNormal, GamePictureManager.deluxeScarecrowDisable),
    BeeHouse(GamePictureManager.beeHouseNormal, GamePictureManager.beeHouseDisable),
    CheesePress(GamePictureManager.cheesePressNormal, GamePictureManager.cheesePressDisable),
    Keg(GamePictureManager.kegNormal, GamePictureManager.kegDisable),
    Loom(GamePictureManager.loomNormal, GamePictureManager.loomDisable),
    MayonnaiseMachine(GamePictureManager.mayonnaiseMachineNormal, GamePictureManager.mayonnaiseMachineDisable),
    OilMaker(GamePictureManager.oilMakerNormal, GamePictureManager.oilMakerDisable),
    PreservesJar(GamePictureManager.preservesJarNormal, GamePictureManager.preservesJarDisable),
    Dehydrator(GamePictureManager.dehydratorNormal, GamePictureManager.dehydratorDisable),
    FishSmoker(GamePictureManager.fishSmokerNormal, GamePictureManager.fishSmokerDisable),
    MysticTreeSeed(GamePictureManager.mysticTreeSeedNormal, GamePictureManager.mysticTreeSeedDisable),;

    private final ImageButton.ImageButtonStyle style;

    CraftingAsset(TextureRegionDrawable normalImage, TextureRegionDrawable disabledImage) {
        this.style = new ImageButton.ImageButtonStyle();
        style.imageUp = normalImage;
        style.imageDisabled = disabledImage;
    }

    public ImageButton.ImageButtonStyle getStyle() {
        return style;
    }

}
