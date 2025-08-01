package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public enum CookingAsset {
    FriedEgg(GamePictureManager.friedEggNormal, GamePictureManager.friedEggDisable),
    BakedFish(GamePictureManager.bakedFishNormal, GamePictureManager.bakedFishDisable),
    Salad(GamePictureManager.saladNormal, GamePictureManager.saladDisable),
    Omelet(GamePictureManager.omeletNormal, GamePictureManager.omeletDisable),
    PumpkinPie(GamePictureManager.pumpkinPieNormal, GamePictureManager.pumpkinPieDisable),
    Spaghetti(GamePictureManager.spaghettiNormal, GamePictureManager.spaghettiDisable),
    Pizza(GamePictureManager.pizzaNormal, GamePictureManager.pizzaDisable),
    Tortilla(GamePictureManager.tortillaNormal, GamePictureManager.tortillaDisable),
    MakiRoll(GamePictureManager.makiRollNormal, GamePictureManager.makiRollDisable),
    TripleShotEspresso(GamePictureManager.tripleShotEspressoNormal, GamePictureManager.tripleShotEspressoDisable),
    Cookie(GamePictureManager.cookieNormal, GamePictureManager.cookieDisable),
    HashBrowns(GamePictureManager.hashbrownsNormal, GamePictureManager.hashbrownsDisable),
    Pancakes(GamePictureManager.pancakesNormal, GamePictureManager.pancakesDisable),
    FruitSalad(GamePictureManager.fruitSaladNormal, GamePictureManager.fruitSaladDisable),
    RedPlate(GamePictureManager.redPlateNormal, GamePictureManager.redPlateDisable),
    Bread(GamePictureManager.breadNormal, GamePictureManager.breadDisable),
    SalmonDinner(GamePictureManager.salmonDinnerNormal, GamePictureManager.salmonDinnerDisable),
    VegetableMedley(GamePictureManager.vegetableMedleyNormal, GamePictureManager.vegetableMedleyDisable),
    FarmersLunch(GamePictureManager.farmersLunchNormal, GamePictureManager.farmersLunchDisable),
    SurvivalBurger(GamePictureManager.survivalBurgerNormal, GamePictureManager.survivalBurgerDisable),
    DishOTheSea(GamePictureManager.dishOTheSeaNormal, GamePictureManager.dishOTheSeaDisable),
    SeaFoamPudding(GamePictureManager.seaFoamPuddingNormal, GamePictureManager.seaFoamPuddingDisable),
    MinersTreat(GamePictureManager.minersTreatNormal, GamePictureManager.minersTreatDisable);

    private final ImageButton.ImageButtonStyle style;

    CookingAsset(TextureRegionDrawable normalImage, TextureRegionDrawable disabledImage) {
        style = new ImageButton.ImageButtonStyle();
        style.imageUp = normalImage;
        style.imageDisabled = disabledImage;
    }

    public ImageButton.ImageButtonStyle getStyle() {
        return style;
    }


}
