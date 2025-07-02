package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.models.recipes.CookingRecipe;

public enum CookingAsset {
    FriedEgg(GamePictureManager.friedEggNormal, GamePictureManager.friedEggDisable, CookingRecipe.FriedEgg),
    BakedFish(GamePictureManager.bakedFishNormal, GamePictureManager.bakedFishDisable, CookingRecipe.BakedFish),
    Salad(GamePictureManager.saladNormal, GamePictureManager.saladDisable, CookingRecipe.Salad),
    Omelet(GamePictureManager.omeletNormal, GamePictureManager.omeletDisable, CookingRecipe.Omelet),
    PumpkinPie(GamePictureManager.pumpkinPieNormal, GamePictureManager.pumpkinPieDisable, CookingRecipe.PumpkinPie),
    Spaghetti(GamePictureManager.spaghettiNormal, GamePictureManager.spaghettiDisable, CookingRecipe.Spaghetti),
    Pizza(GamePictureManager.pizzaNormal, GamePictureManager.pizzaDisable, CookingRecipe.Pizza),
    Tortilla(GamePictureManager.tortillaNormal, GamePictureManager.tortillaDisable, CookingRecipe.Tortilla),
    MakiRoll(GamePictureManager.makiRollNormal, GamePictureManager.makiRollDisable, CookingRecipe.MakiRoll),
    TripleShotEspresso(GamePictureManager.tripleShotEspressoNormal, GamePictureManager.tripleShotEspressoDisable, CookingRecipe.TripleShotEspresso),
    Cookie(GamePictureManager.cookieNormal, GamePictureManager.cookieDisable, CookingRecipe.Cookie),
    HashBrowns(GamePictureManager.hashbrownsNormal, GamePictureManager.hashbrownsDisable, CookingRecipe.HashBrowns),
    Pancakes(GamePictureManager.pancakesNormal, GamePictureManager.pancakesDisable, CookingRecipe.Pancakes),
    FruitSalad(GamePictureManager.fruitSaladNormal, GamePictureManager.fruitSaladDisable, CookingRecipe.FruitSalad),
    RedPlate(GamePictureManager.redPlateNormal, GamePictureManager.redPlateDisable, CookingRecipe.RedPlate),
    Bread(GamePictureManager.breadNormal, GamePictureManager.breadDisable, CookingRecipe.Bread),
    SalmonDinner(GamePictureManager.salmonDinnerNormal, GamePictureManager.salmonDinnerDisable, CookingRecipe.SalmonDinner),
    VegetableMedley(GamePictureManager.vegetableMedleyNormal, GamePictureManager.vegetableMedleyDisable, CookingRecipe.VegetableMedley),
    FarmersLunch(GamePictureManager.farmersLunchNormal, GamePictureManager.farmersLunchDisable, CookingRecipe.FarmersLunch),
    SurvivalBurger(GamePictureManager.survivalBurgerNormal, GamePictureManager.survivalBurgerDisable, CookingRecipe.SurvivalBurger),
    DishOTheSea(GamePictureManager.dishOTheSeaNormal, GamePictureManager.dishOTheSeaDisable, CookingRecipe.DishOTheSea),
    SeaFoamPudding(GamePictureManager.seaFoamPuddingNormal, GamePictureManager.seaFoamPuddingDisable, CookingRecipe.SeaFoamPudding),
    MinersTreat(GamePictureManager.minersTreatNormal, GamePictureManager.minersTreatDisable , CookingRecipe.MinersTreat),;

    private final TextureRegionDrawable normalImage;
    private final TextureRegionDrawable disabledImage;
    private final ImageButton.ImageButtonStyle style;
    private final CookingRecipe recipe;
    private final Label description;

    CookingAsset(TextureRegionDrawable normalImage, TextureRegionDrawable disabledImage, CookingRecipe recipe) {
        this.normalImage = normalImage;
        this.disabledImage = disabledImage;
        this.recipe = recipe;
        style = new ImageButton.ImageButtonStyle();
        style.imageUp = normalImage;
        style.imageDisabled = disabledImage;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.GOLD;
        labelStyle.background = GamePictureManager.woodBackground;
        description = new Label(CookingRecipe.getDescription(recipe), labelStyle);
    }

    public ImageButton.ImageButtonStyle getStyle() {
        return style;
    }

    public TextureRegionDrawable getNormalImage() {
        return normalImage;
    }

    public TextureRegionDrawable getDisabledImage() {
        return disabledImage;
    }

    public CookingRecipe getRecipe() {
        return recipe;
    }

    public Label getDescription() {
        return description;
    }
}
