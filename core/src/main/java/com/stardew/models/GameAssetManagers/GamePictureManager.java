package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.stardew.models.animals.AnimalState;
import com.stardew.models.date.Season;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePictureManager {
    public static Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.skin"));
    public static int TILE_SIZE = 60;
    public static int Tree_SIze_Width = 75;
    public static int Tree_SIze_Height = 150;
    public static int ROCK_SIZE = 50;

    public static TextureRegionDrawable menuBackground = new TextureRegionDrawable(new TextureRegion
        (new Texture(Gdx.files.internal("menu_bg.png"))));

    public static TextureRegionDrawable woodBackground =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Window/wood_bg.png"))));
    public static TextureRegionDrawable windowWoodBackground =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Window/window_org.png"))));
    public static TextureRegionDrawable closeWindow =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Window/close_window.png"))));
    public static TextureRegionDrawable OKButtonUp =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Window/OK_Button_up.png"))));
    public static TextureRegionDrawable OKButtonDown =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Window/OK_Button_down.png"))));


    public static BitmapFont smallFont;
    static {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/ChevyRay - Express.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        smallFont = generator.generateFont(parameter);
        generator.dispose();
    }


    //Tools :
        //Axe :

            public static TextureRegion axeTexture = new TextureRegion(new Texture("Tools/Axe/Axe.png"));
            public static TextureRegion steelAxeTexture = new TextureRegion(new Texture("Tools/Axe/Steel_Axe.png"));
            public static TextureRegion copperAxeTexture = new TextureRegion(new Texture("Tools/Axe/Copper_Axe.png"));
            public static TextureRegion goldAxeTexture = new TextureRegion(new Texture("Tools/Axe/Gold_Axe.png"));
            public static TextureRegion iridiumAxeTexture = new TextureRegion(new Texture("Tools/Axe/Iridium_Axe.png"));

        //Pickaxe :

            public static TextureRegion pickaxeTexture = new TextureRegion(new Texture("Tools/Pickaxe/Pickaxe.png"));
            public static TextureRegion steelPickaxeTexture = new TextureRegion(new Texture("Tools/Pickaxe/Steel_Pickaxe.png"));
            public static TextureRegion copperPickaxeTexture = new TextureRegion(new Texture("Tools/Pickaxe/Copper_Pickaxe.png"));
            public static TextureRegion goldPickaxeTexture = new TextureRegion(new Texture("Tools/Pickaxe/Gold_Pickaxe.png"));
            public static TextureRegion iridiumPickaxeTexture = new TextureRegion(new Texture("Tools/Pickaxe/Iridium_Pickaxe.png"));

        //Scythe :

            public static TextureRegion scytheTexture = new TextureRegion(new Texture("Tools/Scythe.png"));
            public static TextureRegion goldScytheTexture = new TextureRegion(new Texture("Tools/Golden_Scythe.png"));
            public static TextureRegion iridiumScytheTexture = new TextureRegion(new Texture("Tools/Iridium_Scythe.png"));

        //Backpack :

            public static TextureRegion backpackTexture = new TextureRegion(new Texture("Tools/Backpack.png"));
            public static TextureRegion backpack36Texture = new TextureRegion(new Texture("Tools/36_Backpack.png"));

        //TrashCan :

            public static TextureRegion copperTrashCanTexture = new TextureRegion(new Texture("Tools/Trash_Can_Copper.png"));
            public static TextureRegion goldTrashCanTexture = new TextureRegion(new Texture("Tools/Trash_Can_Gold.png"));
            public static TextureRegion steelTrashCanTexture = new TextureRegion(new Texture("Tools/Trash_Can_Steel.png"));
            public static TextureRegion iridiumTrashCanTexture = new TextureRegion(new Texture("Tools/Trash_Can_Iridium.png"));

        //MilkPail :

            public static TextureRegion milkPailTexture = new TextureRegion(new Texture("Tools/Milk_Pail.png"));

        //Shear :

            public static TextureRegion shearTexture = new TextureRegion(new Texture("Tools/Shears.png"));

        //wateringCan :

            public static TextureRegion wateringCanTexture = new TextureRegion(new Texture("Watering_Can/Watering_Can.png"));
            public static TextureRegion copperWateringCanTexture = new TextureRegion(new Texture("Watering_Can/Copper_Watering_Can.png"));
            public static TextureRegion goldWateringCanTexture = new TextureRegion(new Texture("Watering_Can/Gold_Watering_Can.png"));
            public static TextureRegion steelWateringCanTexture = new TextureRegion(new Texture("Watering_Can/Steel_Watering_Can.png"));
            public static TextureRegion iridiumWateringCanTexture = new TextureRegion(new Texture("Watering_Can/Iridium_Watering_Can.png"));

    //Villagers :

        public static TextureRegion abigailTexture = new TextureRegion(new Texture("Villagers/Abigail.png"));
        public static TextureRegion harveyTexture = new TextureRegion(new Texture("Villagers/Harvey.png"));
        public static TextureRegion leahTexture = new TextureRegion(new Texture("Villagers/Leah.png"));
        public static TextureRegion marnieTexture = new TextureRegion(new Texture("Villagers/Marnie.png"));
        public static TextureRegion pierreTexture = new TextureRegion(new Texture("Villagers/Pierre.png"));
        public static TextureRegion robinTexture = new TextureRegion(new Texture("Villagers/Robin.png"));
        public static TextureRegion sebastianTexture = new TextureRegion(new Texture("Villagers/Sebastian.png"));
        public static TextureRegion willyTexture = new TextureRegion(new Texture("Villagers/Willy.png"));

    //Rock :

        public static TextureRegion farmBoulderTexture = new TextureRegion(new Texture("Rock/Farm_Boulder.png"));
        public static TextureRegion MineBoulderTexture1 = new TextureRegion(new Texture("Rock/Mine_Boulder_1.png"));
        public static TextureRegion mineBoulderTexture2 = new TextureRegion(new Texture("Rock/Mine_Boulder_2.png"));
        public static TextureRegion mineBoulderTexture3 = new TextureRegion(new Texture("Rock/Mine_Boulder_3.png"));
        public static TextureRegion mineBoulderTexture4 = new TextureRegion(new Texture("Rock/Mine_Boulder_4.png"));
        public static TextureRegion quarryBoulderTexture = new TextureRegion(new Texture("Rock/Quarry_Boulder.png"));
        public static TextureRegion stoneIndex32Texture = new TextureRegion(new Texture("Rock/Stone_Index32.png"));
        public static TextureRegion stoneIndex34Texture = new TextureRegion(new Texture("Rock/Stone_Index34.png"));
        public static TextureRegion stoneIndex36Texture = new TextureRegion(new Texture("Rock/Stone_Index36.png"));
        public static TextureRegion stoneIndex38Texture = new TextureRegion(new Texture("Rock/Stone_Index38.png"));
        public static TextureRegion stoneIndex40Texture = new TextureRegion(new Texture("Rock/Stone_Index40.png"));
        public static TextureRegion stoneIndex42Texture = new TextureRegion(new Texture("Rock/Stone_Index42.png"));
        public static TextureRegion stoneIndex48Texture = new TextureRegion(new Texture("Rock/Stone_Index48.png"));
        public static TextureRegion stoneIndex50Texture = new TextureRegion(new Texture("Rock/Stone_Index50.png"));
        public static TextureRegion stoneIndex52Texture = new TextureRegion(new Texture("Rock/Stone_Index52.png"));
        public static TextureRegion stoneIndex54Texture = new TextureRegion(new Texture("Rock/Stone_Index54.png"));
        public static TextureRegion stoneIndex56Texture = new TextureRegion(new Texture("Rock/Stone_Index56.png"));
        public static TextureRegion stoneIndex58Texture = new TextureRegion(new Texture("Rock/Stone_Index58.png"));
        public static TextureRegion stoneIndex343Texture = new TextureRegion(new Texture("Rock/Stone_Index343.png"));
        public static TextureRegion stoneIndex450Texture = new TextureRegion(new Texture("Rock/Stone_Index450.png"));
        public static TextureRegion stoneIndex668Texture = new TextureRegion(new Texture("Rock/Stone_Index668.png"));
        public static TextureRegion stoneIndex670Texture = new TextureRegion(new Texture("Rock/Stone_Index670.png"));

    //Cooking_recipes :

        public static TextureRegionDrawable friedEggNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Fried_Egg.png")));
        public static TextureRegionDrawable bakedFishNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Baked_Fish.png")));
        public static TextureRegionDrawable saladNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Salad.png")));
        public static TextureRegionDrawable omeletNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Omelet.png")));
        public static TextureRegionDrawable pumpkinPieNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Pumpkin_Pie.png")));
        public static TextureRegionDrawable spaghettiNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Spaghetti.png")));
        public static TextureRegionDrawable pizzaNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Pizza.png")));
        public static TextureRegionDrawable tortillaNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Tortilla.png")));
        public static TextureRegionDrawable makiRollNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Maki_Roll.png")));
        public static TextureRegionDrawable tripleShotEspressoNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Triple_Shot_Espresso.png")));
        public static TextureRegionDrawable cookieNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Cookie.png")));
        public static TextureRegionDrawable hashbrownsNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Hashbrowns.png")));
        public static TextureRegionDrawable pancakesNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Pancakes.png")));
        public static TextureRegionDrawable fruitSaladNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Fruit_Salad.png")));
        public static TextureRegionDrawable redPlateNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Red_Plate.png")));
        public static TextureRegionDrawable breadNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Bread.png")));
        public static TextureRegionDrawable salmonDinnerNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Salmon_Dinner.png")));
        public static TextureRegionDrawable vegetableMedleyNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Vegetable_Medley.png")));
        public static TextureRegionDrawable farmersLunchNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Farmer%27s_Lunch.png")));
        public static TextureRegionDrawable survivalBurgerNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Survival_Burger.png")));
        public static TextureRegionDrawable dishOTheSeaNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Dish_O%27_The_Sea.png")));
        public static TextureRegionDrawable seaFoamPuddingNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Seafoam_Pudding.png")));
        public static TextureRegionDrawable minersTreatNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Recipe/Miner%27s_Treat.png")));

        public static TextureRegionDrawable friedEggDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Fried_Egg.png")));
        public static TextureRegionDrawable bakedFishDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Baked_Fish.png")));
        public static TextureRegionDrawable saladDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Salad.png")));
        public static TextureRegionDrawable omeletDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Omelet.png")));
        public static TextureRegionDrawable pumpkinPieDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Pumpkin_Pie.png")));
        public static TextureRegionDrawable spaghettiDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Spaghetti.png")));
        public static TextureRegionDrawable pizzaDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Pizza.png")));
        public static TextureRegionDrawable tortillaDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Tortilla.png")));
        public static TextureRegionDrawable makiRollDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Maki_Roll.png")));
        public static TextureRegionDrawable tripleShotEspressoDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Triple_Shot_Espresso.png")));
        public static TextureRegionDrawable cookieDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Cookie.png")));
        public static TextureRegionDrawable hashbrownsDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Hashbrowns.png")));
        public static TextureRegionDrawable pancakesDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Pancakes.png")));
        public static TextureRegionDrawable fruitSaladDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Fruit_Salad.png")));
        public static TextureRegionDrawable redPlateDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Red_Plate.png")));
        public static TextureRegionDrawable breadDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Bread.png")));
        public static TextureRegionDrawable salmonDinnerDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Salmon_Dinner.png")));
        public static TextureRegionDrawable vegetableMedleyDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Vegetable_Medley.png")));
        public static TextureRegionDrawable farmersLunchDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Farmer%27s_Lunch.png")));
        public static TextureRegionDrawable survivalBurgerDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Survival_Burger.png")));
        public static TextureRegionDrawable dishOTheSeaDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Dish_O%27_The_Sea.png")));
        public static TextureRegionDrawable seaFoamPuddingDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Seafoam_Pudding.png")));
        public static TextureRegionDrawable minersTreatDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedRecipes/Miner%27s_Treat.png")));

        public static TextureRegion jojaColaTexture = new TextureRegion(new Texture("Concessions/Joja_Cola_%28large%29.png"));
        public static TextureRegion troutSoupTexture = new TextureRegion(new Texture("Crops/Parsnip_Soup.png"));

    //Crafting :

        public static TextureRegionDrawable cherryBombNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Cherry_Bomb.png")));
        public static TextureRegionDrawable bombNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Bomb.png")));
        public static TextureRegionDrawable megaBombNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Mega_Bomb.png")));
        public static TextureRegionDrawable sprinklerNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Sprinkler.png")));
        public static TextureRegionDrawable qualitySprinklerNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Quality_Sprinkler.png")));
        public static TextureRegionDrawable iridiumSprinklerNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Iridium_Sprinkler.png")));
        public static TextureRegionDrawable charcoalKilnNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Charcoal_Kiln.png")));
        public static TextureRegionDrawable furnaceNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Furnace.png")));
        public static TextureRegionDrawable scarecrowNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Scarecrow.png")));
        public static TextureRegionDrawable deluxeScarecrowNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Deluxe_Scarecrow.png")));
        public static TextureRegionDrawable beeHouseNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Bee_House.png")));
        public static TextureRegionDrawable cheesePressNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Cheese_Press.png")));
        public static TextureRegionDrawable kegNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Keg.png")));
        public static TextureRegionDrawable loomNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Loom.png")));
        public static TextureRegionDrawable mayonnaiseMachineNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Mayonnaise_Machine.png")));
        public static TextureRegionDrawable oilMakerNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Oil_Maker.png")));
        public static TextureRegionDrawable preservesJarNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Preserves_Jar.png")));
        public static TextureRegionDrawable dehydratorNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Dehydrator.png")));
        public static TextureRegionDrawable fishSmokerNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Fish_Smoker.png")));
        public static TextureRegionDrawable mysticTreeSeedNormal = new TextureRegionDrawable(new TextureRegion(new Texture("Crafting/Mystic_Tree_Seed.png")));

        public static TextureRegionDrawable cherryBombDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Cherry_Bomb.png")));
        public static TextureRegionDrawable bombDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Bomb.png")));
        public static TextureRegionDrawable megaBombDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Mega_Bomb.png")));
        public static TextureRegionDrawable sprinklerDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Sprinkler.png")));
        public static TextureRegionDrawable qualitySprinklerDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Quality_Sprinkler.png")));
        public static TextureRegionDrawable iridiumSprinklerDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Iridium_Sprinkler.png")));
        public static TextureRegionDrawable charcoalKilnDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Charcoal_Kiln.png")));
        public static TextureRegionDrawable furnaceDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Furnace.png")));
        public static TextureRegionDrawable scarecrowDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Scarecrow.png")));
        public static TextureRegionDrawable deluxeScarecrowDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Deluxe_Scarecrow.png")));
        public static TextureRegionDrawable beeHouseDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Bee_House.png")));
        public static TextureRegionDrawable cheesePressDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Cheese_Press.png")));
        public static TextureRegionDrawable kegDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Keg.png")));
        public static TextureRegionDrawable loomDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Loom.png")));
        public static TextureRegionDrawable mayonnaiseMachineDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Mayonnaise_Machine.png")));
        public static TextureRegionDrawable oilMakerDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Oil_Maker.png")));
        public static TextureRegionDrawable preservesJarDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Preserves_Jar.png")));
        public static TextureRegionDrawable dehydratorDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Dehydrator.png")));
        public static TextureRegionDrawable fishSmokerDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Fish_Smoker.png")));
        public static TextureRegionDrawable mysticTreeSeedDisable = new TextureRegionDrawable(new TextureRegion(new Texture("LockedCrafting/Mystic_Tree_Seed.png")));

    //Artisan_Goods :

        public static TextureRegion honeyTexture = new TextureRegion(new Texture("Artisan_good/Honey.png"));
        public static TextureRegion cheeseTexture = new TextureRegion(new Texture("Artisan_good/Cheese.png"));
        public static TextureRegion cheeseByLargeMilkTexture = new TextureRegion(new Texture("Artisan_good/CheeseByLargeMilk.png"));
        public static TextureRegion goatCheeseTexture = new TextureRegion(new Texture("Artisan_good/Goat_Cheese.png"));
        public static TextureRegion goatCheeseByLargeMilkTexture = new TextureRegion(new Texture("Artisan_good/Goat_CheeseByLargeMilk.png"));
        public static TextureRegion beerTexture = new TextureRegion(new Texture("Artisan_good/Beer.png"));
        public static TextureRegion vinegarTexture = new TextureRegion(new Texture("Artisan_good/Vinegar.png"));
        public static TextureRegion coffeeTexture = new TextureRegion(new Texture("Artisan_good/Coffee.png"));
        public static TextureRegion juiceTexture = new TextureRegion(new Texture("Artisan_good/Juice.png"));
        public static TextureRegion meadTexture = new TextureRegion(new Texture("Artisan_good/Mead.png"));
        public static TextureRegion paleAleTexture = new TextureRegion(new Texture("Artisan_good/Pale_Ale.png"));
        public static TextureRegion wineTexture = new TextureRegion(new Texture("Artisan_good/Wine.png"));
        public static TextureRegion driedMushroomsTexture = new TextureRegion(new Texture("Artisan_good/Dried_Mushrooms.png"));
        public static TextureRegion driedFruitTexture = new TextureRegion(new Texture("Artisan_good/Dried_Fruit.png"));
        public static TextureRegion raisinsTexture = new TextureRegion(new Texture("Artisan_good/Raisins.png"));
        public static TextureRegion coalTexture = new TextureRegion(new Texture("Artisan_good/Coal.png"));
        public static TextureRegion clothTexture = new TextureRegion(new Texture("Artisan_good/Cloth.png"));
        public static TextureRegion mayonnaiseTexture = new TextureRegion(new Texture("Artisan_good/Mayonnaise.png"));
        public static TextureRegion duckMayonnaiseTexture = new TextureRegion(new Texture("Artisan_good/Duck_Mayonnaise.png"));
        public static TextureRegion dinosaurMayonnaiseTexture = new TextureRegion(new Texture("Artisan_good/Dinosaur_Mayonnaise.png"));
        public static TextureRegion oilTexture = new TextureRegion(new Texture("Artisan_good/Oil.png"));
        public static TextureRegion truffleOilTexture = new TextureRegion(new Texture("Artisan_good/Truffle_Oil.png"));
        public static TextureRegion picklesTexture = new TextureRegion(new Texture("Artisan_good/Pickles.png"));
        public static TextureRegion jellyTexture = new TextureRegion(new Texture("Artisan_good/Jelly.png"));
        public static TextureRegion smokedFishTexture = new TextureRegion(new Texture("Artisan_good/Smoked_Fish.png"));
        public static TextureRegion ironBarTexture = new TextureRegion(new Texture("Artisan_good/Iron_Bar.png"));
        public static TextureRegion iridiumBarTexture = new TextureRegion(new Texture("Artisan_good/Iridium_Bar.png"));
        public static TextureRegion copperBarTexture = new TextureRegion(new Texture("Artisan_good/Copper_Bar.png"));
        public static TextureRegion goldBarTexture = new TextureRegion(new Texture("Artisan_good/Gold_Bar.png"));


    //end_process image :

        public static Texture endProcessTexture = new Texture("Achievement/end_process.png");

    //Tree :

        //Apricot :
        public static TextureRegion apricotTexture = new TextureRegion(new Texture("Trees/Apricot.png"));
        public static TextureRegion apricotSaplingTexture = new TextureRegion(new Texture("Trees/Apricot_Sapling.png"));
        public static TextureRegion apricotStage1Texture = new TextureRegion(new Texture("Trees/Apricot_Stage_1.png"));
        public static TextureRegion apricotStage2Texture = new TextureRegion(new Texture("Trees/Apricot_Stage_2.png"));
        public static TextureRegion apricotStage3Texture = new TextureRegion(new Texture("Trees/Apricot_Stage_3.png"));
        public static TextureRegion apricotStage4Texture = new TextureRegion(new Texture("Trees/Apricot_Stage_4.png"));
        public static TextureRegion apricotStage5WithFruit = new TextureRegion(new Texture("Trees/Apricot_Stage_5_Fruit.png"));
        public static Map<Season, TextureRegion> apricotStage5 = new HashMap<>(4);
        static {
            Texture apricotStage5SeasonsTexture = new Texture("Trees/Apricot_Stage_5.png");
            apricotStage5.put(Season.Spring, new TextureRegion(apricotStage5SeasonsTexture, 0, 0, 96, 160));
            apricotStage5.put(Season.Summer, new TextureRegion(apricotStage5SeasonsTexture, 96, 0, 96, 160));
            apricotStage5.put(Season.Fall,   new TextureRegion(apricotStage5SeasonsTexture, 192, 0, 96, 160));
            apricotStage5.put(Season.Winter, new TextureRegion(apricotStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] apricotStageTextures = new TextureRegion[]{
            apricotStage1Texture,
            apricotStage2Texture,
            apricotStage3Texture,
            apricotStage4Texture
        };

        //Cherry :
        public static TextureRegion cherryTexture = new TextureRegion(new Texture("Trees/Cherry.png"));
        public static TextureRegion cherrySaplingTexture = new TextureRegion(new Texture("Trees/Cherry_Sapling.png"));
        public static TextureRegion cherryStage1Texture = new TextureRegion(new Texture("Trees/Cherry_Stage_1.png"));
        public static TextureRegion cherryStage2Texture = new TextureRegion(new Texture("Trees/Cherry_Stage_2.png"));
        public static TextureRegion cherryStage3Texture = new TextureRegion(new Texture("Trees/Cherry_Stage_3.png"));
        public static TextureRegion cherryStage4Texture = new TextureRegion(new Texture("Trees/Cherry_Stage_4.png"));
        public static TextureRegion cherryStage5WithFruitTexture = new TextureRegion(new Texture("Trees/Cherry_Stage_5_Fruit.png"));
        public static Map<Season, TextureRegion> cherryStage5 = new HashMap<>(4);
        static {
            Texture cherryStage5SeasonsTexture = new Texture("Trees/Cherry_Stage_5.png");
            cherryStage5.put(Season.Spring, new TextureRegion(cherryStage5SeasonsTexture, 0, 0, 96, 160));
            cherryStage5.put(Season.Summer, new TextureRegion(cherryStage5SeasonsTexture, 96, 0, 96, 160));
            cherryStage5.put(Season.Fall,   new TextureRegion(cherryStage5SeasonsTexture, 192, 0, 96, 160));
            cherryStage5.put(Season.Winter, new TextureRegion(cherryStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] cherryStageTextures = new TextureRegion[]{
            cherryStage1Texture,
            cherryStage2Texture,
            cherryStage3Texture,
            cherryStage4Texture,
        };

        //Banana :
        public static TextureRegion bananaTexture = new TextureRegion(new Texture("Trees/Banana.png"));
        public static TextureRegion bananaSaplingTexture = new TextureRegion(new Texture("Trees/Banana_Sapling.png"));
        public static TextureRegion bananaStage1Texture = new TextureRegion(new Texture("Trees/Banana_Stage_1.png"));
        public static TextureRegion bananaStage2Texture = new TextureRegion(new Texture("Trees/Banana_Stage_2.png"));
        public static TextureRegion bananaStage3Texture = new TextureRegion(new Texture("Trees/Banana_Stage_3.png"));
        public static TextureRegion bananaStage4Texture = new TextureRegion(new Texture("Trees/Banana_Stage_4.png"));
        public static TextureRegion bananaStage5WithFruit = new TextureRegion(new Texture("Trees/Banana_Stage_5_Fruit.png"));
        public static Map<Season, TextureRegion> bananaStage5 = new HashMap<>(4);
        static {
            Texture bananaStage5SeasonsTexture = new Texture("Trees/Banana_Stage_5.png");
            bananaStage5.put(Season.Spring, new TextureRegion(bananaStage5SeasonsTexture, 0, 0, 96, 160));
            bananaStage5.put(Season.Summer, new TextureRegion(bananaStage5SeasonsTexture, 96, 0, 96, 160));
            bananaStage5.put(Season.Fall,   new TextureRegion(bananaStage5SeasonsTexture, 192, 0, 96, 160));
            bananaStage5.put(Season.Winter, new TextureRegion(bananaStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] bananaStageTextures = new TextureRegion[]{
            bananaStage1Texture,
            bananaStage2Texture,
            bananaStage3Texture,
            bananaStage4Texture
        };

        // Mango:
        public static TextureRegion mangoTexture = new TextureRegion(new Texture("Trees/Mango.png"));
        public static TextureRegion mangoSaplingTexture = new TextureRegion(new Texture("Trees/Mango_Sapling.png"));
        public static TextureRegion mangoStage1Texture = new TextureRegion(new Texture("Trees/Mango_Stage_1.png"));
        public static TextureRegion mangoStage2Texture = new TextureRegion(new Texture("Trees/Mango_Stage_2.png"));
        public static TextureRegion mangoStage3Texture = new TextureRegion(new Texture("Trees/Mango_Stage_3.png"));
        public static TextureRegion mangoStage4Texture = new TextureRegion(new Texture("Trees/Mango_Stage_4.png"));
        public static TextureRegion mangoStage5WithFruit = new TextureRegion(new Texture("Trees/Mango_Stage_5_Fruit.png"));
        public static Map<Season, TextureRegion> mangoStage5 = new HashMap<>(4);
        static {
            Texture mangoStage5SeasonsTexture = new Texture("Trees/Mango_Stage_5.png");
            mangoStage5.put(Season.Spring, new TextureRegion(mangoStage5SeasonsTexture, 0, 0, 96, 160));
            mangoStage5.put(Season.Summer, new TextureRegion(mangoStage5SeasonsTexture, 96, 0, 96, 160));
            mangoStage5.put(Season.Fall,   new TextureRegion(mangoStage5SeasonsTexture, 192, 0, 96, 160));
            mangoStage5.put(Season.Winter, new TextureRegion(mangoStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] mangoStageTextures = new TextureRegion[]{
            mangoStage1Texture,
            mangoStage2Texture,
            mangoStage3Texture,
            mangoStage4Texture
        };

        // Orange:
        public static TextureRegion orangeTexture = new TextureRegion(new Texture("Trees/Orange.png"));
        public static TextureRegion orangeSaplingTexture = new TextureRegion(new Texture("Trees/Orange_Sapling.png"));
        public static TextureRegion orangeStage1Texture = new TextureRegion(new Texture("Trees/Orange_Stage_1.png"));
        public static TextureRegion orangeStage2Texture = new TextureRegion(new Texture("Trees/Orange_Stage_2.png"));
        public static TextureRegion orangeStage3Texture = new TextureRegion(new Texture("Trees/Orange_Stage_3.png"));
        public static TextureRegion orangeStage4Texture = new TextureRegion(new Texture("Trees/Orange_Stage_4.png"));
        public static TextureRegion orangeStage5WithFruit = new TextureRegion(new Texture("Trees/Orange_Stage_5_Fruit.png"));
        public static Map<Season, TextureRegion> orangeStage5 = new HashMap<>(4);
        static {
            Texture orangeStage5SeasonsTexture = new Texture("Trees/Orange_Stage_5.png");
            orangeStage5.put(Season.Spring, new TextureRegion(orangeStage5SeasonsTexture, 0, 0, 96, 160));
            orangeStage5.put(Season.Summer, new TextureRegion(orangeStage5SeasonsTexture, 96, 0, 96, 160));
            orangeStage5.put(Season.Fall,   new TextureRegion(orangeStage5SeasonsTexture, 192, 0, 96, 160));
            orangeStage5.put(Season.Winter, new TextureRegion(orangeStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] orangeTextures = new TextureRegion[]{
            orangeStage1Texture,
            orangeStage2Texture,
            orangeStage3Texture,
            orangeStage4Texture
        };

        // Peach:
        public static TextureRegion peachTexture = new TextureRegion(new Texture("Trees/Peach.png"));
        public static TextureRegion peachSaplingTexture = new TextureRegion(new Texture("Trees/Peach_Sapling.png"));
        public static TextureRegion peachStage1Texture = new TextureRegion(new Texture("Trees/Peach_Stage_1.png"));
        public static TextureRegion peachStage2Texture = new TextureRegion(new Texture("Trees/Peach_Stage_2.png"));
        public static TextureRegion peachStage3Texture = new TextureRegion(new Texture("Trees/Peach_Stage_3.png"));
        public static TextureRegion peachStage4Texture = new TextureRegion(new Texture("Trees/Peach_Stage_4.png"));
        public static TextureRegion peachStage5WithFruit = new TextureRegion(new Texture("Trees/Peach_Stage_5_Fruit.png"));
        public static Map<Season, TextureRegion> peachStage5 = new HashMap<>(4);
        static {
            Texture peachStage5SeasonsTexture = new Texture("Trees/Peach_Stage_5.png");
            peachStage5.put(Season.Spring, new TextureRegion(peachStage5SeasonsTexture, 0, 0, 96, 160));
            peachStage5.put(Season.Summer, new TextureRegion(peachStage5SeasonsTexture, 96, 0, 96, 160));
            peachStage5.put(Season.Fall,   new TextureRegion(peachStage5SeasonsTexture, 192, 0, 96, 160));
            peachStage5.put(Season.Winter, new TextureRegion(peachStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] peachTextures = new TextureRegion[]{
            peachStage1Texture,
            peachStage2Texture,
            peachStage3Texture,
            peachStage4Texture
        };

        //Apple:
        public static TextureRegion appleTexture = new TextureRegion(new Texture("Trees/Apple.png"));
        public static TextureRegion appleSaplingTexture = new TextureRegion(new Texture("Trees/Apple_Sapling.png"));
        public static TextureRegion appleStage1Texture = new TextureRegion(new Texture("Trees/Apple_Stage_1.png"));
        public static TextureRegion appleStage2Texture = new TextureRegion(new Texture("Trees/Apple_Stage_2.png"));
        public static TextureRegion appleStage3Texture = new TextureRegion(new Texture("Trees/Apple_Stage_3.png"));
        public static TextureRegion appleStage4Texture = new TextureRegion(new Texture("Trees/Apple_Stage_4.png"));
        public static TextureRegion appleStage5WithFruit = new TextureRegion(new Texture("Trees/Apple_Stage_5_Fruit.png"));
        public static Map<Season, TextureRegion> appleStage5 = new HashMap<>(4);
        static {
            Texture appleStage5SeasonsTexture = new Texture("Trees/Apple_Stage_5.png");
            appleStage5.put(Season.Spring, new TextureRegion(appleStage5SeasonsTexture, 0, 0, 96, 160));
            appleStage5.put(Season.Summer, new TextureRegion(appleStage5SeasonsTexture, 96, 0, 96, 160));
            appleStage5.put(Season.Fall,   new TextureRegion(appleStage5SeasonsTexture, 192, 0, 96, 160));
            appleStage5.put(Season.Winter, new TextureRegion(appleStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] appleStageTextures = new TextureRegion[]{
            appleStage1Texture,
            appleStage2Texture,
            appleStage3Texture,
            appleStage4Texture
        };

        // Pomegranate:
        public static TextureRegion pomegranateTexture = new TextureRegion(new Texture("Trees/Pomegranate.png"));
        public static TextureRegion pomegranateSaplingTexture = new TextureRegion(new Texture("Trees/Pomegranate_Sapling.png"));
        public static TextureRegion pomegranateStage1Texture = new TextureRegion(new Texture("Trees/Pomegranate_Stage_1.png"));
        public static TextureRegion pomegranateStage2Texture = new TextureRegion(new Texture("Trees/Pomegranate_Stage_2.png"));
        public static TextureRegion pomegranateStage3Texture = new TextureRegion(new Texture("Trees/Pomegranate_Stage_3.png"));
        public static TextureRegion pomegranateStage4Texture = new TextureRegion(new Texture("Trees/Pomegranate_Stage_4.png"));
        public static TextureRegion pomegranateStage5WithFruit = new TextureRegion(new Texture("Trees/Pomegranate_Stage_5_Fruit.png"));
        public static Map<Season, TextureRegion> pomegranateStage5 = new HashMap<>(4);
        static {
            Texture pomegranateStage5SeasonsTexture = new Texture("Trees/Pomegranate_Stage_5.png");
            pomegranateStage5.put(Season.Spring, new TextureRegion(pomegranateStage5SeasonsTexture, 0, 0, 96, 160));
            pomegranateStage5.put(Season.Summer, new TextureRegion(pomegranateStage5SeasonsTexture, 96, 0, 96, 160));
            pomegranateStage5.put(Season.Fall,   new TextureRegion(pomegranateStage5SeasonsTexture, 192, 0, 96, 160));
            pomegranateStage5.put(Season.Winter, new TextureRegion(pomegranateStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] pomegranateTextures = new TextureRegion[]{
            pomegranateStage1Texture,
            pomegranateStage2Texture,
            pomegranateStage3Texture,
            pomegranateStage4Texture
        };


        // Oak:
        public static TextureRegion oakResinTexture = new TextureRegion(new Texture("Trees/Oak_Resin.png")); //fruit
        public static TextureRegion acornTexture = new TextureRegion(new Texture("Trees/Acorn.png"));        //source
        public static TextureRegion oakStage1Texture = new TextureRegion(new Texture("Trees/Oak_Stage_1.png"));
        public static TextureRegion oakStage2Texture = new TextureRegion(new Texture("Trees/Oak_Stage_2.png"));
        public static TextureRegion oakStage3Texture = new TextureRegion(new Texture("Trees/Oak_Stage_3.png"));
        public static TextureRegion oakStage4Texture = new TextureRegion(new Texture("Trees/Oak_Stage_4.png"));
        public static Map<Season, TextureRegion> oakStage5 = new HashMap<>(4);
        static {
            Texture oakStage5SeasonsTexture = new Texture("Trees/Oak_Stage_5.png");
            oakStage5.put(Season.Spring, new TextureRegion(oakStage5SeasonsTexture, 0, 0, 96, 160));
            oakStage5.put(Season.Summer, new TextureRegion(oakStage5SeasonsTexture, 96, 0, 96, 160));
            oakStage5.put(Season.Fall,   new TextureRegion(oakStage5SeasonsTexture, 192, 0, 96, 160));
            oakStage5.put(Season.Winter, new TextureRegion(oakStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] oakTextures = new TextureRegion[]{
            oakStage1Texture,
            oakStage2Texture,
            oakStage3Texture,
            oakStage4Texture
        };


        // Maple:
        public static TextureRegion mapleSyrupTexture = new TextureRegion(new Texture("Trees/Maple_Syrup.png"));  //fruit
        public static TextureRegion mapleSeedsTexture = new TextureRegion(new Texture("Trees/Maple_Seed.png"));   //source
        public static TextureRegion mapleStage1Texture = new TextureRegion(new Texture("Trees/Maple_Stage_1.png"));
        public static TextureRegion mapleStage2Texture = new TextureRegion(new Texture("Trees/Maple_Stage_2.png"));
        public static TextureRegion mapleStage3Texture = new TextureRegion(new Texture("Trees/Maple_Stage_3.png"));
        public static TextureRegion mapleStage4Texture = new TextureRegion(new Texture("Trees/Maple_Stage_4.png"));
        public static Map<Season, TextureRegion> mapleStage5 = new HashMap<>(4);
        static {
            Texture mapleStage5SeasonsTexture = new Texture("Trees/Maple_Stage_5.png");
            mapleStage5.put(Season.Spring, new TextureRegion(mapleStage5SeasonsTexture, 0, 0, 96, 160));
            mapleStage5.put(Season.Summer, new TextureRegion(mapleStage5SeasonsTexture, 96, 0, 96, 160));
            mapleStage5.put(Season.Fall,   new TextureRegion(mapleStage5SeasonsTexture, 192, 0, 96, 160));
            mapleStage5.put(Season.Winter, new TextureRegion(mapleStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] mapleStageTextures = new TextureRegion[]{
            mapleStage1Texture,
            mapleStage2Texture,
            mapleStage3Texture,
            mapleStage4Texture
        };

        // Pine:
        public static TextureRegion pineTarTexture = new TextureRegion(new Texture("Trees/Pine_Tar.png"));    //fruit
        public static TextureRegion pineConeTexture = new TextureRegion(new Texture("Trees/Pine_Cone.png"));  //source
        public static TextureRegion pineStage1Texture = new TextureRegion(new Texture("Trees/Pine_Stage_1.png"));
        public static TextureRegion pineStage2Texture = new TextureRegion(new Texture("Trees/Pine_Stage_2.png"));
        public static TextureRegion pineStage3Texture = new TextureRegion(new Texture("Trees/Pine_Stage_3.png"));
        public static TextureRegion pineStage4Texture = new TextureRegion(new Texture("Trees/Pine_Stage_4.png"));
        public static Map<Season, TextureRegion> pineStage5 = new HashMap<>(4);
        static {
            Texture pineStage5SeasonsTexture = new Texture("Trees/Pine_Stage_5.png");
            pineStage5.put(Season.Spring, new TextureRegion(pineStage5SeasonsTexture, 0, 0, 96, 160));
            pineStage5.put(Season.Summer, new TextureRegion(pineStage5SeasonsTexture, 96, 0, 96, 160));
            pineStage5.put(Season.Fall,   new TextureRegion(pineStage5SeasonsTexture, 192, 0, 96, 160));
            pineStage5.put(Season.Winter, new TextureRegion(pineStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] pineTextures = new TextureRegion[]{
            pineStage1Texture,
            pineStage2Texture,
            pineStage3Texture,
            pineStage4Texture
        };

        // Mahogany:
        public static TextureRegion sapTexture = new TextureRegion(new Texture("Trees/Sap.png"));    //fruit
        public static TextureRegion mahoganySeedTexture = new TextureRegion(new Texture("Trees/Mahogany_Seed.png"));  //source
        public static TextureRegion mahoganyStage1Texture = new TextureRegion(new Texture("Trees/Mahogany_Stage_1.png"));
        public static TextureRegion mahoganyStage2Texture = new TextureRegion(new Texture("Trees/Mahogany_Stage_2.png"));
        public static TextureRegion mahoganyStage3Texture = new TextureRegion(new Texture("Trees/Mahogany_Stage_3.png"));
        public static TextureRegion mahoganyStage4Texture = new TextureRegion(new Texture("Trees/Mahogany_Stage_4.png"));
        public static Map<Season, TextureRegion> mahoganyStage5 = new HashMap<>(4);
        static {
            Texture mahoganyStage5SeasonsTexture = new Texture("Trees/Mahogany_Stage_5.png");
            mahoganyStage5.put(Season.Spring, new TextureRegion(mahoganyStage5SeasonsTexture, 0, 0, 96, 160));
            mahoganyStage5.put(Season.Summer, new TextureRegion(mahoganyStage5SeasonsTexture, 96, 0, 96, 160));
            mahoganyStage5.put(Season.Fall,   new TextureRegion(mahoganyStage5SeasonsTexture, 192, 0, 96, 160));
            mahoganyStage5.put(Season.Winter, new TextureRegion(mahoganyStage5SeasonsTexture, 288, 0, 96, 160));
        }
        public static TextureRegion[] mahoganyTextures = new TextureRegion[]{
            mahoganyStage1Texture,
            mahoganyStage2Texture,
            mahoganyStage3Texture,
            mahoganyStage4Texture
        };

        // MushroomTree:
        public static TextureRegion commonMushroom = new TextureRegion(new Texture("Trees/Common_Mushroom.png"));   //fruit
        public static TextureRegion mushroomTreeSeedTexture = new TextureRegion(new Texture("Trees/Mushroom_Tree_Seed.png")); //source
        public static TextureRegion mushroomTreeStage1Texture = new TextureRegion(new Texture("Trees/MushroomTree_Stage_1.png"));
        public static TextureRegion mushroomTreeStage2Texture = new TextureRegion(new Texture("Trees/MushroomTree_Stage_2.png"));
        public static TextureRegion mushroomTreeStage3Texture = new TextureRegion(new Texture("Trees/MushroomTree_Stage_3.png"));
        public static TextureRegion mushroomTreeStage4Texture = new TextureRegion(new Texture("Trees/MushroomTree_Stage_4.png"));
        public static TextureRegion mushroomTreeStage5Texture = new TextureRegion(new Texture("Trees/MushroomTree_Stage_5.png"));
        public static TextureRegion[] mushroomTreeTextures = new TextureRegion[]{
            mushroomTreeStage1Texture,
            mushroomTreeStage2Texture,
            mushroomTreeStage3Texture,
            mushroomTreeStage4Texture,
            mushroomTreeStage5Texture
        };

        // Mystic_Tree
        public static TextureRegion mysticSyrupTexture = new TextureRegion(new Texture("Trees/Mystic_Syrup.png"));     //fruit
        public static TextureRegion mysticTreeSeedTexture = new TextureRegion(new Texture("Trees/Mystic_Tree_Seed.png")); //source
        public static TextureRegion mysticTreeStage1Texture = new TextureRegion(new Texture("Trees/Mystic_Tree_Stage_1.png"));
        public static TextureRegion mysticTreeStage2Texture = new TextureRegion(new Texture("Trees/Mystic_Tree_Stage_2.png"));
        public static TextureRegion mysticTreeStage3Texture = new TextureRegion(new Texture("Trees/Mystic_Tree_Stage_3.png"));
        public static TextureRegion mysticTreeStage4Texture = new TextureRegion(new Texture("Trees/Mystic_Tree_Stage_4.png"));
        public static TextureRegion mysticTreeStage5Texture = new TextureRegion(new Texture("Trees/Mystic_Tree_Stage_5.png"));
        public static TextureRegion[] mysticTreeTextures = new TextureRegion[]{
            mysticTreeStage1Texture,
            mysticTreeStage2Texture,
            mysticTreeStage3Texture,
            mysticTreeStage4Texture,
            mysticTreeStage5Texture
        };


    //Crops :

        //BlueJazz:
        public static TextureRegion blueJazzTexture = new TextureRegion(new Texture("Crops/Blue_Jazz.png"));
        public static TextureRegion jazzSeedsTexture = new TextureRegion(new Texture("Crops/Jazz_Seeds.png"));
        public static TextureRegion blueJazzStage1Texture = new TextureRegion(new Texture("Crops/Blue_Jazz_Stage_1.png"));
        public static TextureRegion blueJazzStage2Texture = new TextureRegion(new Texture("Crops/Blue_Jazz_Stage_2.png"));
        public static TextureRegion blueJazzStage3Texture = new TextureRegion(new Texture("Crops/Blue_Jazz_Stage_3.png"));
        public static TextureRegion blueJazzStage4Texture = new TextureRegion(new Texture("Crops/Blue_Jazz_Stage_4.png"));
        public static TextureRegion blueJazzStage5Texture = new TextureRegion(new Texture("Crops/Blue_Jazz_Stage_5.png"));
        public static TextureRegion[] blueJazzTextures = new TextureRegion[]{
            blueJazzStage1Texture,
            blueJazzStage2Texture,
            blueJazzStage3Texture,
            blueJazzStage4Texture,
            blueJazzStage5Texture
        };

        //Carrot:
        public static TextureRegion carrotTexture = new TextureRegion(new Texture("Crops/Carrot.png"));
        public static TextureRegion carrotSeedsTexture = new TextureRegion(new Texture("Crops/Carrot_Seeds.png"));
        public static TextureRegion carrotStage1Texture = new TextureRegion(new Texture("Crops/Carrot_Stage_1.png"));
        public static TextureRegion carrotStage2Texture = new TextureRegion(new Texture("Crops/Carrot_Stage_2.png"));
        public static TextureRegion carrotStage3Texture = new TextureRegion(new Texture("Crops/Carrot_Stage_3.png"));
        public static TextureRegion carrotStage4Texture = new TextureRegion(new Texture("Crops/Carrot_Stage_4.png"));
        public static TextureRegion[] carrotTextures = new TextureRegion[]{
            carrotStage1Texture,
            carrotStage2Texture,
            carrotStage3Texture,
            carrotStage4Texture,
        };

        //Cauliflower:
        public static TextureRegion cauliflowerTexture = new TextureRegion(new Texture("Crops/Cauliflower.png"));
        public static TextureRegion cauliflowerSeedsTexture = new TextureRegion(new Texture("Crops/Cauliflower_Seeds.png"));
        public static TextureRegion cauliflowerStage1Texture = new TextureRegion(new Texture("Crops/Cauliflower_Stage_1.png"));
        public static TextureRegion cauliflowerStage2Texture = new TextureRegion(new Texture("Crops/Cauliflower_Stage_2.png"));
        public static TextureRegion cauliflowerStage3Texture = new TextureRegion(new Texture("Crops/Cauliflower_Stage_3.png"));
        public static TextureRegion cauliflowerStage4Texture = new TextureRegion(new Texture("Crops/Cauliflower_Stage_4.png"));
        public static TextureRegion cauliflowerStage5Texture = new TextureRegion(new Texture("Crops/Cauliflower_Stage_5.png"));
        public static TextureRegion cauliflowerStage6Texture = new TextureRegion(new Texture("Crops/Cauliflower_Stage_6.png"));
        public static TextureRegion[] cauliflowerTextures = new TextureRegion[]{
            cauliflowerStage1Texture,
            cauliflowerStage2Texture,
            cauliflowerStage3Texture,
            cauliflowerStage4Texture,
            cauliflowerStage5Texture,
            cauliflowerStage6Texture,
        };

        //CoffeeBean:
        public static TextureRegion coffeeBeanTexture = new TextureRegion(new Texture("Crops/Coffee_Bean.png"));
        public static TextureRegion coffeeStage1Texture = new TextureRegion(new Texture("Crops/Coffee_Stage_1.png"));
        public static TextureRegion coffeeStage2Texture = new TextureRegion(new Texture("Crops/Coffee_Stage_2.png"));
        public static TextureRegion coffeeStage3Texture = new TextureRegion(new Texture("Crops/Coffee_Stage_3.png"));
        public static TextureRegion coffeeStage4Texture = new TextureRegion(new Texture("Crops/Coffee_Stage_4.png"));
        public static TextureRegion coffeeStage5Texture = new TextureRegion(new Texture("Crops/Coffee_Stage_5.png"));
        public static TextureRegion coffeeStage6Texture = new TextureRegion(new Texture("Crops/Coffee_Stage_6.png"));
        public static TextureRegion coffeeStage7Texture = new TextureRegion(new Texture("Crops/Coffee_Stage_7.png"));
        public static TextureRegion[] coffeeTextures = new TextureRegion[]{
            coffeeStage1Texture,
            coffeeStage2Texture,
            coffeeStage3Texture,
            coffeeStage4Texture,
            coffeeStage5Texture,
            coffeeStage6Texture,
            coffeeStage7Texture,
        };

        //Garlic:
        public static TextureRegion garlicTexture = new TextureRegion(new Texture("Crops/Garlic.png"));
        public static TextureRegion garlicSeedsTexture = new TextureRegion(new Texture("Crops/Garlic_Seeds.png"));
        public static TextureRegion garlicStage1Texture = new TextureRegion(new Texture("Crops/Garlic_Stage_1.png"));
        public static TextureRegion garlicStage2Texture = new TextureRegion(new Texture("Crops/Garlic_Stage_2.png"));
        public static TextureRegion garlicStage3Texture = new TextureRegion(new Texture("Crops/Garlic_Stage_3.png"));
        public static TextureRegion garlicStage4Texture = new TextureRegion(new Texture("Crops/Garlic_Stage_4.png"));
        public static TextureRegion garlicStage5Texture = new TextureRegion(new Texture("Crops/Garlic_Stage_5.png"));
        public static TextureRegion[] garlicTextures = new TextureRegion[]{
            garlicStage1Texture,
            garlicStage2Texture,
            garlicStage3Texture,
            garlicStage4Texture,
            garlicStage5Texture,
        };

        //GreenBean:
        public static TextureRegion greenBeanTexture = new TextureRegion(new Texture("Crops/Green_Bean.png"));
        public static TextureRegion beanStarterTexture = new TextureRegion(new Texture("Crops/Bean_Starter.png"));
        public static TextureRegion greenBeanStage1Texture = new TextureRegion(new Texture("Crops/Green_Bean_Stage_2.png"));
        public static TextureRegion greenBeanStage2Texture = new TextureRegion(new Texture("Crops/Green_Bean_Stage_3.png"));
        public static TextureRegion greenBeanStage3Texture = new TextureRegion(new Texture("Crops/Green_Bean_Stage_4.png"));
        public static TextureRegion greenBeanStage4Texture = new TextureRegion(new Texture("Crops/Green_Bean_Stage_5.png"));
        public static TextureRegion greenBeanStage5Texture = new TextureRegion(new Texture("Crops/Green_Bean_Stage_6.png"));
        public static TextureRegion greenBeanStage6Texture = new TextureRegion(new Texture("Crops/Green_Bean_Stage_7.png"));
        public static TextureRegion greenBeanStage7Texture = new TextureRegion(new Texture("Crops/Green_Bean_Stage_8.png"));
        public static TextureRegion[] greenBeanTextures = new TextureRegion[]{
            greenBeanStage1Texture,
            greenBeanStage2Texture,
            greenBeanStage3Texture,
            greenBeanStage4Texture,
            greenBeanStage5Texture,
            greenBeanStage6Texture,
            greenBeanStage7Texture,
        };

        //Kale:
        public static TextureRegion kaleTexture = new TextureRegion(new Texture("Crops/Kale.png"));
        public static TextureRegion kaleSeedsTexture = new TextureRegion(new Texture("Crops/Kale_Seeds.png"));
        public static TextureRegion kaleStage1Texture = new TextureRegion(new Texture("Crops/Kale_Stage_1.png"));
        public static TextureRegion kaleStage2Texture = new TextureRegion(new Texture("Crops/Kale_Stage_2.png"));
        public static TextureRegion kaleStage3Texture = new TextureRegion(new Texture("Crops/Kale_Stage_3.png"));
        public static TextureRegion kaleStage4Texture = new TextureRegion(new Texture("Crops/Kale_Stage_4.png"));
        public static TextureRegion kaleStage5Texture = new TextureRegion(new Texture("Crops/Kale_Stage_5.png"));
        public static TextureRegion[] kaleTextures = new TextureRegion[]{
            kaleStage1Texture,
            kaleStage2Texture,
            kaleStage3Texture,
            kaleStage4Texture,
            kaleStage5Texture,
        };

        //Parsnip:
        public static TextureRegion parsnipTexture = new TextureRegion(new Texture("Crops/Parsnip.png"));
        public static TextureRegion parsnipSeedsTexture = new TextureRegion(new Texture("Crops/Parsnip_Seeds.png"));
        public static TextureRegion parsnipStage1Texture = new TextureRegion(new Texture("Crops/Parsnip_Stage_1.png"));
        public static TextureRegion parsnipStage2Texture = new TextureRegion(new Texture("Crops/Parsnip_Stage_2.png"));
        public static TextureRegion parsnipStage3Texture = new TextureRegion(new Texture("Crops/Parsnip_Stage_3.png"));
        public static TextureRegion parsnipStage4Texture = new TextureRegion(new Texture("Crops/Parsnip_Stage_4.png"));
        public static TextureRegion parsnipStage5Texture = new TextureRegion(new Texture("Crops/Parsnip_Stage_5.png"));
        public static TextureRegion[] parsnipTextures = new TextureRegion[]{
            parsnipStage1Texture,
            parsnipStage2Texture,
            parsnipStage3Texture,
            parsnipStage4Texture,
            parsnipStage5Texture,
        };

        //Potato:
        public static TextureRegion potatoTexture = new TextureRegion(new Texture("Crops/Potato.png"));
        public static TextureRegion potatoSeedsTexture = new TextureRegion(new Texture("Crops/Potato_Seeds.png"));
        public static TextureRegion potatoStage1Texture = new TextureRegion(new Texture("Crops/Potato_Stage_1.png"));
        public static TextureRegion potatoStage2Texture = new TextureRegion(new Texture("Crops/Potato_Stage_2.png"));
        public static TextureRegion potatoStage3Texture = new TextureRegion(new Texture("Crops/Potato_Stage_3.png"));
        public static TextureRegion potatoStage4Texture = new TextureRegion(new Texture("Crops/Potato_Stage_4.png"));
        public static TextureRegion potatoStage5Texture = new TextureRegion(new Texture("Crops/Potato_Stage_5.png"));
        public static TextureRegion potatoStage6Texture = new TextureRegion(new Texture("Crops/Potato_Stage_6.png"));
        public static TextureRegion[] potatoTextures = new TextureRegion[]{
            potatoStage1Texture,
            potatoStage2Texture,
            potatoStage3Texture,
            potatoStage4Texture,
            potatoStage5Texture,
            potatoStage6Texture,
        };

        //Rhubarb:
        public static TextureRegion rhubarbTexture = new TextureRegion(new Texture("Crops/Rhubarb.png"));
        public static TextureRegion rhubarbSeedsTexture = new TextureRegion(new Texture("Crops/Rhubarb_Seeds.png"));
        public static TextureRegion rhubarbStage1Texture = new TextureRegion(new Texture("Crops/Rhubarb_Stage_1.png"));
        public static TextureRegion rhubarbStage2Texture = new TextureRegion(new Texture("Crops/Rhubarb_Stage_2.png"));
        public static TextureRegion rhubarbStage3Texture = new TextureRegion(new Texture("Crops/Rhubarb_Stage_3.png"));
        public static TextureRegion rhubarbStage4Texture = new TextureRegion(new Texture("Crops/Rhubarb_Stage_4.png"));
        public static TextureRegion rhubarbStage5Texture = new TextureRegion(new Texture("Crops/Rhubarb_Stage_5.png"));
        public static TextureRegion rhubarbStage6Texture = new TextureRegion(new Texture("Crops/Rhubarb_Stage_6.png"));
        public static TextureRegion[] rhubarbTextures = new TextureRegion[]{
            rhubarbStage1Texture,
            rhubarbStage2Texture,
            rhubarbStage3Texture,
            rhubarbStage4Texture,
            rhubarbStage5Texture,
            rhubarbStage6Texture,
        };

        //Strawberry:
        public static TextureRegion strawberryTexture = new TextureRegion(new Texture("Crops/Strawberry.png"));
        public static TextureRegion strawberrySeedsTexture = new TextureRegion(new Texture("Crops/Strawberry_Seeds.png"));
        public static TextureRegion strawberryStage1Texture = new TextureRegion(new Texture("Crops/Strawberry_Stage_1.png"));
        public static TextureRegion strawberryStage2Texture = new TextureRegion(new Texture("Crops/Strawberry_Stage_2.png"));
        public static TextureRegion strawberryStage3Texture = new TextureRegion(new Texture("Crops/Strawberry_Stage_3.png"));
        public static TextureRegion strawberryStage4Texture = new TextureRegion(new Texture("Crops/Strawberry_Stage_4.png"));
        public static TextureRegion strawberryStage5Texture = new TextureRegion(new Texture("Crops/Strawberry_Stage_5.png"));
        public static TextureRegion strawberryStage6Texture = new TextureRegion(new Texture("Crops/Strawberry_Stage_6.png"));
        public static TextureRegion strawberryStage7Texture = new TextureRegion(new Texture("Crops/Strawberry_Stage_7.png"));
        public static TextureRegion[] strawberryTextures = new TextureRegion[]{
            strawberryStage1Texture,
            strawberryStage2Texture,
            strawberryStage3Texture,
            strawberryStage4Texture,
            strawberryStage5Texture,
            strawberryStage6Texture,
            strawberryStage7Texture,
        };

        //Tulip:
        public static TextureRegion tulipTexture = new TextureRegion(new Texture("Crops/Tulip.png"));
        public static TextureRegion tulipBulbTexture = new TextureRegion(new Texture("Crops/Tulip_Bulb.png"));
        public static TextureRegion tulipStage1Texture = new TextureRegion(new Texture("Crops/Tulip_Stage_1.png"));
        public static TextureRegion tulipStage2Texture = new TextureRegion(new Texture("Crops/Tulip_Stage_2.png"));
        public static TextureRegion tulipStage3Texture = new TextureRegion(new Texture("Crops/Tulip_Stage_3.png"));
        public static TextureRegion tulipStage4Texture = new TextureRegion(new Texture("Crops/Tulip_Stage_4.png"));
        public static TextureRegion tulipStage5Texture = new TextureRegion(new Texture("Crops/Tulip_Stage_5.png"));
        public static TextureRegion[] tulipTextures = new TextureRegion[]{
            tulipStage1Texture,
            tulipStage2Texture,
            tulipStage3Texture,
            tulipStage4Texture,
            tulipStage5Texture,
        };

        //UnMilledRice:
        public static TextureRegion unMilledRiceTexture = new TextureRegion(new Texture("Crops/Unmilled_Rice.png"));
        public static TextureRegion riceShootTexture = new TextureRegion(new Texture("Crops/Rice_Shoot.png"));
        public static TextureRegion unMilledRiceStage1Texture = new TextureRegion(new Texture("Crops/Unmilled_Rice_Stage_1.png"));
        public static TextureRegion unMilledRiceStage2Texture = new TextureRegion(new Texture("Crops/Unmilled_Rice_Stage_2.png"));
        public static TextureRegion unMilledRiceStage3Texture = new TextureRegion(new Texture("Crops/Unmilled_Rice_Stage_3.png"));
        public static TextureRegion unMilledRiceStage4Texture = new TextureRegion(new Texture("Crops/Unmilled_Rice_Stage_4.png"));
        public static TextureRegion unMilledRiceStage5Texture = new TextureRegion(new Texture("Crops/Unmilled_Rice_Stage_5.png"));
        public static TextureRegion[] unMilledRiceTextures = new TextureRegion[]{
            unMilledRiceStage1Texture,
            unMilledRiceStage2Texture,
            unMilledRiceStage3Texture,
            unMilledRiceStage4Texture,
            unMilledRiceStage5Texture,
        };

        //Blueberry:
        public static TextureRegion blueberryTexture = new TextureRegion(new Texture("Crops/Blueberry.png"));
        public static TextureRegion blueberrySeedsTexture = new TextureRegion(new Texture("Crops/Blueberry_Seeds.png"));
        public static TextureRegion blueberryStage1Texture = new TextureRegion(new Texture("Crops/Blueberry_Stage_1.png"));
        public static TextureRegion blueberryStage2Texture = new TextureRegion(new Texture("Crops/Blueberry_Stage_2.png"));
        public static TextureRegion blueberryStage3Texture = new TextureRegion(new Texture("Crops/Blueberry_Stage_3.png"));
        public static TextureRegion blueberryStage4Texture = new TextureRegion(new Texture("Crops/Blueberry_Stage_4.png"));
        public static TextureRegion blueberryStage5Texture = new TextureRegion(new Texture("Crops/Blueberry_Stage_5.png"));
        public static TextureRegion blueberryStage6Texture = new TextureRegion(new Texture("Crops/Blueberry_Stage_6.png"));
        public static TextureRegion blueberryStage7Texture = new TextureRegion(new Texture("Crops/Blueberry_Stage_7.png"));
        public static TextureRegion[] blueberryTextures = new TextureRegion[]{
            blueberryStage1Texture,
            blueberryStage2Texture,
            blueberryStage3Texture,
            blueberryStage4Texture,
            blueberryStage5Texture,
            blueberryStage6Texture,
            blueberryStage7Texture
        };

        //Corn
        public static TextureRegion cornTexture = new TextureRegion(new Texture("Crops/Corn.png"));
        public static TextureRegion cornSeedsTexture = new TextureRegion(new Texture("Crops/Corn_Seeds.png"));
        public static TextureRegion cornStage1Texture = new TextureRegion(new Texture("Crops/Corn_Stage_1.png"));
        public static TextureRegion cornStage2Texture = new TextureRegion(new Texture("Crops/Corn_Stage_2.png"));
        public static TextureRegion cornStage3Texture = new TextureRegion(new Texture("Crops/Corn_Stage_3.png"));
        public static TextureRegion cornStage4Texture = new TextureRegion(new Texture("Crops/Corn_Stage_4.png"));
        public static TextureRegion cornStage5Texture = new TextureRegion(new Texture("Crops/Corn_Stage_5.png"));
        public static TextureRegion cornStage6Texture = new TextureRegion(new Texture("Crops/Corn_Stage_6.png"));
        public static TextureRegion cornStage7Texture = new TextureRegion(new Texture("Crops/Corn_Stage_7.png"));
        public static TextureRegion[] cornTextures = new TextureRegion[]{
            cornStage1Texture,
            cornStage2Texture,
            cornStage3Texture,
            cornStage4Texture,
            cornStage5Texture,
            cornStage6Texture,
            cornStage7Texture
        };

        //Hops:
        public static TextureRegion hopsTexture = new TextureRegion(new Texture("Crops/Hops.png"));
        public static TextureRegion hopsStarterTexture = new TextureRegion(new Texture("Crops/Hops_Starter.png"));
        public static TextureRegion hopsStage1Texture = new TextureRegion(new Texture("Crops/Hops_Stage_1.png"));
        public static TextureRegion hopsStage2Texture = new TextureRegion(new Texture("Crops/Hops_Stage_2.png"));
        public static TextureRegion hopsStage3Texture = new TextureRegion(new Texture("Crops/Hops_Stage_3.png"));
        public static TextureRegion hopsStage4Texture = new TextureRegion(new Texture("Crops/Hops_Stage_4.png"));
        public static TextureRegion hopsStage5Texture = new TextureRegion(new Texture("Crops/Hops_Stage_5.png"));
        public static TextureRegion hopsStage6Texture = new TextureRegion(new Texture("Crops/Hops_Stage_6.png"));
        public static TextureRegion hopsStage7Texture = new TextureRegion(new Texture("Crops/Hops_Stage_7.png"));
        public static TextureRegion[] hopsTextures = new TextureRegion[]{
            hopsStage1Texture,
            hopsStage2Texture,
            hopsStage3Texture,
            hopsStage4Texture,
            hopsStage5Texture,
            hopsStage6Texture,
            hopsStage7Texture
        };

        //HotPepper:
        public static TextureRegion hotPepperTexture = new TextureRegion(new Texture("Crops/Hot_Pepper.png"));
        public static TextureRegion pepperSeedsTexture = new TextureRegion(new Texture("Crops/Pepper_Seeds.png"));
        public static TextureRegion hotPepperStage1Texture = new TextureRegion(new Texture("Crops/Hot_Pepper_Stage_1.png"));
        public static TextureRegion hotPepperStage2Texture = new TextureRegion(new Texture("Crops/Hot_Pepper_Stage_2.png"));
        public static TextureRegion hotPepperStage3Texture = new TextureRegion(new Texture("Crops/Hot_Pepper_Stage_3.png"));
        public static TextureRegion hotPepperStage4Texture = new TextureRegion(new Texture("Crops/Hot_Pepper_Stage_4.png"));
        public static TextureRegion hotPepperStage5Texture = new TextureRegion(new Texture("Crops/Hot_Pepper_Stage_5.png"));
        public static TextureRegion hotPepperStage6Texture = new TextureRegion(new Texture("Crops/Hot_Pepper_Stage_6.png"));
        public static TextureRegion hotPepperStage7Texture = new TextureRegion(new Texture("Crops/Hot_Pepper_Stage_7.png"));
        public static TextureRegion[] hotPepperTextures = new TextureRegion[]{
            hotPepperStage1Texture,
            hotPepperStage2Texture,
            hotPepperStage3Texture,
            hotPepperStage4Texture,
            hotPepperStage5Texture,
            hotPepperStage6Texture,
            hotPepperStage7Texture
        };

        //Melon:
        public static TextureRegion melonTexture = new TextureRegion(new Texture("Crops/Melon.png"));
        public static TextureRegion melonSeedsTexture = new TextureRegion(new Texture("Crops/Melon_Seeds.png"));
        public static TextureRegion melonStage1Texture = new TextureRegion(new Texture("Crops/Melon_Stage_1.png"));
        public static TextureRegion melonStage2Texture = new TextureRegion(new Texture("Crops/Melon_Stage_2.png"));
        public static TextureRegion melonStage3Texture = new TextureRegion(new Texture("Crops/Melon_Stage_3.png"));
        public static TextureRegion melonStage4Texture = new TextureRegion(new Texture("Crops/Melon_Stage_4.png"));
        public static TextureRegion melonStage5Texture = new TextureRegion(new Texture("Crops/Melon_Stage_5.png"));
        public static TextureRegion melonStage6Texture = new TextureRegion(new Texture("Crops/Melon_Stage_6.png"));
        public static TextureRegion[] melonTextures = new TextureRegion[]{
            melonStage1Texture,
            melonStage2Texture,
            melonStage3Texture,
            melonStage4Texture,
            melonStage5Texture,
            melonStage6Texture,
        };

        //Poppy:
        public static TextureRegion poppyTexture = new TextureRegion(new Texture("Crops/Poppy.png"));
        public static TextureRegion poppySeedsTexture = new TextureRegion(new Texture("Crops/Poppy_Seeds.png"));
        public static TextureRegion poppyStage1Texture = new TextureRegion(new Texture("Crops/Poppy_Stage_1.png"));
        public static TextureRegion poppyStage2Texture = new TextureRegion(new Texture("Crops/Poppy_Stage_2.png"));
        public static TextureRegion poppyStage3Texture = new TextureRegion(new Texture("Crops/Poppy_Stage_3.png"));
        public static TextureRegion poppyStage4Texture = new TextureRegion(new Texture("Crops/Poppy_Stage_4.png"));
        public static TextureRegion poppyStage5Texture = new TextureRegion(new Texture("Crops/Poppy_Stage_5.png"));
        public static TextureRegion[] poppyTextures = new TextureRegion[]{
            poppyStage1Texture,
            poppyStage2Texture,
            poppyStage3Texture,
            poppyStage4Texture,
            poppyStage5Texture,
        };

        //Radish:
        public static TextureRegion radishTexture = new TextureRegion(new Texture("Crops/Radish.png"));
        public static TextureRegion radishSeedsTexture = new TextureRegion(new Texture("Crops/Radish_Seeds.png"));
        public static TextureRegion radishStage1Texture = new TextureRegion(new Texture("Crops/Radish_Stage_1.png"));
        public static TextureRegion radishStage2Texture = new TextureRegion(new Texture("Crops/Radish_Stage_2.png"));
        public static TextureRegion radishStage3Texture = new TextureRegion(new Texture("Crops/Radish_Stage_3.png"));
        public static TextureRegion radishStage4Texture = new TextureRegion(new Texture("Crops/Radish_Stage_4.png"));
        public static TextureRegion radishStage5Texture = new TextureRegion(new Texture("Crops/Radish_Stage_5.png"));
        public static TextureRegion[] radishTextures = new TextureRegion[]{
            radishStage1Texture,
            radishStage2Texture,
            radishStage3Texture,
            radishStage4Texture,
            radishStage5Texture,
        };

        //RedCabbage:
        public static TextureRegion redCabbageTexture = new TextureRegion(new Texture("Crops/Red_Cabbage.png"));
        public static TextureRegion redCabbageSeedsTexture = new TextureRegion(new Texture("Crops/Red_Cabbage_Seeds.png"));
        public static TextureRegion redCabbageStage1Texture = new TextureRegion(new Texture("Crops/Red_Cabbage_Stage_1.png"));
        public static TextureRegion redCabbageStage2Texture = new TextureRegion(new Texture("Crops/Red_Cabbage_Stage_2.png"));
        public static TextureRegion redCabbageStage3Texture = new TextureRegion(new Texture("Crops/Red_Cabbage_Stage_3.png"));
        public static TextureRegion redCabbageStage4Texture = new TextureRegion(new Texture("Crops/Red_Cabbage_Stage_4.png"));
        public static TextureRegion redCabbageStage5Texture = new TextureRegion(new Texture("Crops/Red_Cabbage_Stage_5.png"));
        public static TextureRegion redCabbageStage6Texture = new TextureRegion(new Texture("Crops/Red_Cabbage_Stage_6.png"));
        public static TextureRegion[] redCabbageTextures = new TextureRegion[]{
            redCabbageStage1Texture,
            redCabbageStage2Texture,
            redCabbageStage3Texture,
            redCabbageStage4Texture,
            redCabbageStage5Texture,
            redCabbageStage6Texture,
        };

        //Starfruit:
        public static TextureRegion starfruitTexture = new TextureRegion(new Texture("Crops/Starfruit.png"));
        public static TextureRegion starfruitSeedsTexture = new TextureRegion(new Texture("Crops/Starfruit_Seeds.png"));
        public static TextureRegion starfruitStage1Texture = new TextureRegion(new Texture("Crops/Starfruit_Stage_1.png"));
        public static TextureRegion starfruitStage2Texture = new TextureRegion(new Texture("Crops/Starfruit_Stage_2.png"));
        public static TextureRegion starfruitStage3Texture = new TextureRegion(new Texture("Crops/Starfruit_Stage_3.png"));
        public static TextureRegion starfruitStage4Texture = new TextureRegion(new Texture("Crops/Starfruit_Stage_4.png"));
        public static TextureRegion starfruitStage5Texture = new TextureRegion(new Texture("Crops/Starfruit_Stage_5.png"));
        public static TextureRegion starfruitStage6Texture = new TextureRegion(new Texture("Crops/Starfruit_Stage_6.png"));
        public static TextureRegion[] starfruitTextures = new TextureRegion[]{
            starfruitStage1Texture,
            starfruitStage2Texture,
            starfruitStage3Texture,
            starfruitStage4Texture,
            starfruitStage5Texture,
            starfruitStage6Texture,
        };

        //SummerSpangle:
        public static TextureRegion summerSpangleTexture = new TextureRegion(new Texture("Crops/Summer_Spangle.png"));
        public static TextureRegion spangleSeedsTexture = new TextureRegion(new Texture("Crops/Spangle_Seeds.png"));
        public static TextureRegion summerSpangleStage1Texture = new TextureRegion(new Texture("Crops/Summer_Spangle_Stage_1.png"));
        public static TextureRegion summerSpangleStage2Texture = new TextureRegion(new Texture("Crops/Summer_Spangle_Stage_2.png"));
        public static TextureRegion summerSpangleStage3Texture = new TextureRegion(new Texture("Crops/Summer_Spangle_Stage_3.png"));
        public static TextureRegion summerSpangleStage4Texture = new TextureRegion(new Texture("Crops/Summer_Spangle_Stage_4.png"));
        public static TextureRegion summerSpangleStage5Texture = new TextureRegion(new Texture("Crops/Summer_Spangle_Stage_5.png"));
        public static TextureRegion[] summerSpangleTextures = new TextureRegion[]{
            summerSpangleStage1Texture,
            summerSpangleStage2Texture,
            summerSpangleStage3Texture,
            summerSpangleStage4Texture,
            summerSpangleStage5Texture,
        };

        //SummerSquash:
        public static TextureRegion summerSquashTexture = new TextureRegion(new Texture("Crops/Summer_Squash.png"));
        public static TextureRegion summerSquashSeedsTexture = new TextureRegion(new Texture("Crops/Summer_Squash_Seeds.png"));
        public static TextureRegion summerSquashStage1Texture = new TextureRegion(new Texture("Crops/Summer_Squash_Stage_1.png"));
        public static TextureRegion summerSquashStage2Texture = new TextureRegion(new Texture("Crops/Summer_Squash_Stage_2.png"));
        public static TextureRegion summerSquashStage3Texture = new TextureRegion(new Texture("Crops/Summer_Squash_Stage_3.png"));
        public static TextureRegion summerSquashStage4Texture = new TextureRegion(new Texture("Crops/Summer_Squash_Stage_4.png"));
        public static TextureRegion summerSquashStage5Texture = new TextureRegion(new Texture("Crops/Summer_Squash_Stage_5.png"));
        public static TextureRegion summerSquashStage6Texture = new TextureRegion(new Texture("Crops/Summer_Squash_Stage_6.png"));
        public static TextureRegion summerSquashStage7Texture = new TextureRegion(new Texture("Crops/Summer_Squash_Stage_7.png"));
        public static TextureRegion[] summerSquashTextures = new TextureRegion[]{
            summerSquashStage1Texture,
            summerSquashStage2Texture,
            summerSquashStage3Texture,
            summerSquashStage4Texture,
            summerSquashStage5Texture,
            summerSquashStage6Texture,
            summerSquashStage7Texture
        };

        //sunflower:
        public static TextureRegion sunflowerTexture = new TextureRegion(new Texture("Crops/Sunflower.png"));
        public static TextureRegion sunflowerSeedsTexture = new TextureRegion(new Texture("Crops/Sunflower_Seeds.png"));
        public static TextureRegion sunflowerStage1Texture = new TextureRegion(new Texture("Crops/Sunflower_Stage_1.png"));
        public static TextureRegion sunflowerStage2Texture = new TextureRegion(new Texture("Crops/Sunflower_Stage_2.png"));
        public static TextureRegion sunflowerStage3Texture = new TextureRegion(new Texture("Crops/Sunflower_Stage_3.png"));
        public static TextureRegion sunflowerStage4Texture = new TextureRegion(new Texture("Crops/Sunflower_Stage_4.png"));
        public static TextureRegion sunflowerStage5Texture = new TextureRegion(new Texture("Crops/Sunflower_Stage_5.png"));
        public static TextureRegion[] sunflowerTextures = new TextureRegion[]{
            sunflowerStage1Texture,
            sunflowerStage2Texture,
            sunflowerStage3Texture,
            sunflowerStage4Texture,
            sunflowerStage5Texture,
        };

        //tomato:
        public static TextureRegion tomatoTexture = new TextureRegion(new Texture("Crops/Tomato.png"));
        public static TextureRegion tomatoSeedsTexture = new TextureRegion(new Texture("Crops/Tomato_Seeds.png"));
        public static TextureRegion tomatoStage1Texture = new TextureRegion(new Texture("Crops/Tomato_Stage_1.png"));
        public static TextureRegion tomatoStage2Texture = new TextureRegion(new Texture("Crops/Tomato_Stage_2.png"));
        public static TextureRegion tomatoStage3Texture = new TextureRegion(new Texture("Crops/Tomato_Stage_3.png"));
        public static TextureRegion tomatoStage4Texture = new TextureRegion(new Texture("Crops/Tomato_Stage_4.png"));
        public static TextureRegion tomatoStage5Texture = new TextureRegion(new Texture("Crops/Tomato_Stage_5.png"));
        public static TextureRegion tomatoStage6Texture = new TextureRegion(new Texture("Crops/Tomato_Stage_6.png"));
        public static TextureRegion tomatoStage7Texture = new TextureRegion(new Texture("Crops/Tomato_Stage_7.png"));
        public static TextureRegion[] tomatoTextures = new TextureRegion[]{
            tomatoStage1Texture,
            tomatoStage2Texture,
            tomatoStage3Texture,
            tomatoStage4Texture,
            tomatoStage5Texture,
            tomatoStage6Texture,
            tomatoStage7Texture,
        };

        //wheat:
        public static TextureRegion wheatTexture = new TextureRegion(new Texture("Crops/Wheat.png"));
        public static TextureRegion wheatSeedsTexture = new TextureRegion(new Texture("Crops/Wheat_Seeds.png"));
        public static TextureRegion wheatStage1Texture = new TextureRegion(new Texture("Crops/Wheat_Stage_1.png"));
        public static TextureRegion wheatStage2Texture = new TextureRegion(new Texture("Crops/Wheat_Stage_2.png"));
        public static TextureRegion wheatStage3Texture = new TextureRegion(new Texture("Crops/Wheat_Stage_3.png"));
        public static TextureRegion wheatStage4Texture = new TextureRegion(new Texture("Crops/Wheat_Stage_4.png"));
        public static TextureRegion wheatStage5Texture = new TextureRegion(new Texture("Crops/Wheat_Stage_5.png"));
        public static TextureRegion[] wheatTextures = new TextureRegion[]{
            wheatStage1Texture,
            wheatStage2Texture,
            wheatStage3Texture,
            wheatStage4Texture,
            wheatStage5Texture,
        };

        //amaranth:
        public static TextureRegion amaranthTexture = new TextureRegion(new Texture("Crops/Amaranth.png"));
        public static TextureRegion amaranthSeedsTexture = new TextureRegion(new Texture("Crops/Amaranth_Seeds.png"));
        public static TextureRegion amaranthStage1Texture = new TextureRegion(new Texture("Crops/Amaranth_Stage_1.png"));
        public static TextureRegion amaranthStage2Texture = new TextureRegion(new Texture("Crops/Amaranth_Stage_2.png"));
        public static TextureRegion amaranthStage3Texture = new TextureRegion(new Texture("Crops/Amaranth_Stage_3.png"));
        public static TextureRegion amaranthStage4Texture = new TextureRegion(new Texture("Crops/Amaranth_Stage_4.png"));
        public static TextureRegion amaranthStage5Texture = new TextureRegion(new Texture("Crops/Amaranth_Stage_5.png"));
        public static TextureRegion[] amaranthTextures = new TextureRegion[]{
            amaranthStage1Texture,
            amaranthStage2Texture,
            amaranthStage3Texture,
            amaranthStage4Texture,
            amaranthStage5Texture,
        };

        //artichoke:
        public static TextureRegion artichokeTexture = new TextureRegion(new Texture("Crops/Artichoke.png"));
        public static TextureRegion artichokeSeedsTexture = new TextureRegion(new Texture("Crops/Artichoke_Seeds.png"));
        public static TextureRegion artichokeStage1Texture = new TextureRegion(new Texture("Crops/Artichoke_Stage_1.png"));
        public static TextureRegion artichokeStage2Texture = new TextureRegion(new Texture("Crops/Artichoke_Stage_2.png"));
        public static TextureRegion artichokeStage3Texture = new TextureRegion(new Texture("Crops/Artichoke_Stage_3.png"));
        public static TextureRegion artichokeStage4Texture = new TextureRegion(new Texture("Crops/Artichoke_Stage_4.png"));
        public static TextureRegion artichokeStage5Texture = new TextureRegion(new Texture("Crops/Artichoke_Stage_5.png"));
        public static TextureRegion artichokeStage6Texture = new TextureRegion(new Texture("Crops/Artichoke_Stage_6.png"));
        public static TextureRegion[] artichokeTextures = new TextureRegion[]{
            artichokeStage1Texture,
            artichokeStage2Texture,
            artichokeStage3Texture,
            artichokeStage4Texture,
            artichokeStage5Texture,
            artichokeStage6Texture,
        };

        //beet:
        public static TextureRegion beetTexture = new TextureRegion(new Texture("Crops/Beet.png"));
        public static TextureRegion beetSeedsTexture = new TextureRegion(new Texture("Crops/Beet_Seeds.png"));
        public static TextureRegion beetStage1Texture = new TextureRegion(new Texture("Crops/Beet_Stage_1.png"));
        public static TextureRegion beetStage2Texture = new TextureRegion(new Texture("Crops/Beet_Stage_2.png"));
        public static TextureRegion beetStage3Texture = new TextureRegion(new Texture("Crops/Beet_Stage_3.png"));
        public static TextureRegion beetStage4Texture = new TextureRegion(new Texture("Crops/Beet_Stage_4.png"));
        public static TextureRegion beetStage5Texture = new TextureRegion(new Texture("Crops/Beet_Stage_5.png"));
        public static TextureRegion[] beetTextures = new TextureRegion[]{
            beetStage1Texture,
            beetStage2Texture,
            beetStage3Texture,
            beetStage4Texture,
            beetStage5Texture,
        };

        //bokChoy:
        public static TextureRegion bokChoyTexture = new TextureRegion(new Texture("Crops/Bok_Choy.png"));
        public static TextureRegion bokChoySeedsTexture = new TextureRegion(new Texture("Crops/Bok_Choy_Seeds.png"));
        public static TextureRegion bokChoyStage1Texture = new TextureRegion(new Texture("Crops/Bok_Choy_Stage_1.png"));
        public static TextureRegion bokChoyStage2Texture = new TextureRegion(new Texture("Crops/Bok_Choy_Stage_2.png"));
        public static TextureRegion bokChoyStage3Texture = new TextureRegion(new Texture("Crops/Bok_Choy_Stage_3.png"));
        public static TextureRegion bokChoyStage4Texture = new TextureRegion(new Texture("Crops/Bok_Choy_Stage_4.png"));
        public static TextureRegion bokChoyStage5Texture = new TextureRegion(new Texture("Crops/Bok_Choy_Stage_5.png"));
        public static TextureRegion[] bokChoyTextures = new TextureRegion[]{
            bokChoyStage1Texture,
            bokChoyStage2Texture,
            bokChoyStage3Texture,
            bokChoyStage4Texture,
            bokChoyStage5Texture,
        };

        //broccoli:
        public static TextureRegion broccoliTexture = new TextureRegion(new Texture("Crops/Broccoli.png"));
        public static TextureRegion broccoliSeedsTexture = new TextureRegion(new Texture("Crops/Broccoli_Seeds.png"));
        public static TextureRegion broccoliStage1Texture = new TextureRegion(new Texture("Crops/Broccoli_Stage_1.png"));
        public static TextureRegion broccoliStage2Texture = new TextureRegion(new Texture("Crops/Broccoli_Stage_2.png"));
        public static TextureRegion broccoliStage3Texture = new TextureRegion(new Texture("Crops/Broccoli_Stage_3.png"));
        public static TextureRegion broccoliStage4Texture = new TextureRegion(new Texture("Crops/Broccoli_Stage_4.png"));
        public static TextureRegion broccoliStage5Texture = new TextureRegion(new Texture("Crops/Broccoli_Stage_5.png"));
        public static TextureRegion broccoliStage6Texture = new TextureRegion(new Texture("Crops/Broccoli_Stage_4.png"));
        public static TextureRegion[] broccoliTextures = new TextureRegion[]{
            broccoliStage1Texture,
            broccoliStage2Texture,
            broccoliStage3Texture,
            broccoliStage4Texture,
            broccoliStage5Texture,
            broccoliStage6Texture,
        };

        //cranberries:
        public static TextureRegion cranberriesTexture = new TextureRegion(new Texture("Crops/Cranberries.png"));
        public static TextureRegion cranberrySeedsTexture = new TextureRegion(new Texture("Crops/Cranberry_Seeds.png"));
        public static TextureRegion cranberriesStage1Texture = new TextureRegion(new Texture("Crops/Cranberry_Stage_1.png"));
        public static TextureRegion cranberriesStage2Texture = new TextureRegion(new Texture("Crops/Cranberry_Stage_2.png"));
        public static TextureRegion cranberriesStage3Texture = new TextureRegion(new Texture("Crops/Cranberry_Stage_3.png"));
        public static TextureRegion cranberriesStage4Texture = new TextureRegion(new Texture("Crops/Cranberry_Stage_4.png"));
        public static TextureRegion cranberriesStage5Texture = new TextureRegion(new Texture("Crops/Cranberry_Stage_5.png"));
        public static TextureRegion cranberriesStage6Texture = new TextureRegion(new Texture("Crops/Cranberry_Stage_6.png"));
        public static TextureRegion cranberriesStage7Texture = new TextureRegion(new Texture("Crops/Cranberry_Stage_7.png"));
        public static TextureRegion[] cranberriesTextures = new TextureRegion[]{
            cranberriesStage1Texture,
            cranberriesStage2Texture,
            cranberriesStage3Texture,
            cranberriesStage4Texture,
            cranberriesStage5Texture,
            cranberriesStage6Texture,
            cranberriesStage7Texture,
        };

        //eggplant:
        public static TextureRegion eggplantTexture = new TextureRegion(new Texture("Crops/Eggplant.png"));
        public static TextureRegion eggplantSeedsTexture = new TextureRegion(new Texture("Crops/Eggplant_Seeds.png"));
        public static TextureRegion eggplantStage1Texture = new TextureRegion(new Texture("Crops/Eggplant_Stage_1.png"));
        public static TextureRegion eggplantStage2Texture = new TextureRegion(new Texture("Crops/Eggplant_Stage_2.png"));
        public static TextureRegion eggplantStage3Texture = new TextureRegion(new Texture("Crops/Eggplant_Stage_3.png"));
        public static TextureRegion eggplantStage4Texture = new TextureRegion(new Texture("Crops/Eggplant_Stage_4.png"));
        public static TextureRegion eggplantStage5Texture = new TextureRegion(new Texture("Crops/Eggplant_Stage_5.png"));
        public static TextureRegion eggplantStage6Texture = new TextureRegion(new Texture("Crops/Eggplant_Stage_6.png"));
        public static TextureRegion eggplantStage7Texture = new TextureRegion(new Texture("Crops/Eggplant_Stage_7.png"));
        public static TextureRegion[] eggplantTextures = new TextureRegion[]{
            eggplantStage1Texture,
            eggplantStage2Texture,
            eggplantStage3Texture,
            eggplantStage4Texture,
            eggplantStage5Texture,
            eggplantStage6Texture,
            eggplantStage7Texture
        };

        //fairyRose:
        public static TextureRegion fairyRoseTexture = new TextureRegion(new Texture("Crops/Fairy_Rose.png"));
        public static TextureRegion fairySeedsTexture = new TextureRegion(new Texture("Crops/Fairy_Seeds.png"));
        public static TextureRegion fairyRoseStage1Texture = new TextureRegion(new Texture("Crops/Fairy_Rose_Stage_1.png"));
        public static TextureRegion fairyRoseStage2Texture = new TextureRegion(new Texture("Crops/Fairy_Rose_Stage_2.png"));
        public static TextureRegion fairyRoseStage3Texture = new TextureRegion(new Texture("Crops/Fairy_Rose_Stage_3.png"));
        public static TextureRegion fairyRoseStage4Texture = new TextureRegion(new Texture("Crops/Fairy_Rose_Stage_4.png"));
        public static TextureRegion fairyRoseStage5Texture = new TextureRegion(new Texture("Crops/Fairy_Rose_Stage_5.png"));
        public static TextureRegion[] fairyRoseTextures = new TextureRegion[]{
            fairyRoseStage1Texture,
            fairyRoseStage2Texture,
            fairyRoseStage3Texture,
            fairyRoseStage4Texture,
            fairyRoseStage5Texture,
        };

        //grape:
        public static TextureRegion grapeTexture = new TextureRegion(new Texture("Crops/Grape.png"));
        public static TextureRegion grapeStarterTexture = new TextureRegion(new Texture("Crops/Grape_Starter.png"));
        public static TextureRegion grapeStage1Texture = new TextureRegion(new Texture("Crops/Grape_Stage_1.png"));
        public static TextureRegion grapeStage2Texture = new TextureRegion(new Texture("Crops/Grape_Stage_2.png"));
        public static TextureRegion grapeStage3Texture = new TextureRegion(new Texture("Crops/Grape_Stage_3.png"));
        public static TextureRegion grapeStage4Texture = new TextureRegion(new Texture("Crops/Grape_Stage_4.png"));
        public static TextureRegion grapeStage5Texture = new TextureRegion(new Texture("Crops/Grape_Stage_5.png"));
        public static TextureRegion grapeStage6Texture = new TextureRegion(new Texture("Crops/Grape_Stage_6.png"));
        public static TextureRegion grapeStage7Texture = new TextureRegion(new Texture("Crops/Grape_Stage_7.png"));
        public static TextureRegion[] grapeTextures = new TextureRegion[]{
            grapeStage1Texture,
            grapeStage2Texture,
            grapeStage3Texture,
            grapeStage4Texture,
            grapeStage5Texture,
            grapeStage6Texture,
            grapeStage7Texture,
        };

        //pumpkin:
        public static TextureRegion pumpkinTexture = new TextureRegion(new Texture("Crops/Pumpkin.png"));
        public static TextureRegion pumpkinSeedsTexture = new TextureRegion(new Texture("Crops/Pumpkin_Seeds.png"));
        public static TextureRegion pumpkinStage1Texture = new TextureRegion(new Texture("Crops/Pumpkin_Stage_1.png"));
        public static TextureRegion pumpkinStage2Texture = new TextureRegion(new Texture("Crops/Pumpkin_Stage_2.png"));
        public static TextureRegion pumpkinStage3Texture = new TextureRegion(new Texture("Crops/Pumpkin_Stage_3.png"));
        public static TextureRegion pumpkinStage4Texture = new TextureRegion(new Texture("Crops/Pumpkin_Stage_4.png"));
        public static TextureRegion pumpkinStage5Texture = new TextureRegion(new Texture("Crops/Pumpkin_Stage_5.png"));
        public static TextureRegion pumpkinStage6Texture = new TextureRegion(new Texture("Crops/Pumpkin_Stage_6.png"));
        public static TextureRegion[] pumpkinTextures = new TextureRegion[]{
            pumpkinStage1Texture,
            pumpkinStage2Texture,
            pumpkinStage3Texture,
            pumpkinStage4Texture,
            pumpkinStage5Texture,
            pumpkinStage6Texture,
        };

        //yam:
        public static TextureRegion yamTexture = new TextureRegion(new Texture("Crops/Yam.png"));
        public static TextureRegion yamSeedsTexture = new TextureRegion(new Texture("Crops/Yam_Seeds.png"));
        public static TextureRegion yamStage1Texture = new TextureRegion(new Texture("Crops/Yam_Stage_1.png"));
        public static TextureRegion yamStage2Texture = new TextureRegion(new Texture("Crops/Yam_Stage_2.png"));
        public static TextureRegion yamStage3Texture = new TextureRegion(new Texture("Crops/Yam_Stage_3.png"));
        public static TextureRegion yamStage4Texture = new TextureRegion(new Texture("Crops/Yam_Stage_4.png"));
        public static TextureRegion yamStage5Texture = new TextureRegion(new Texture("Crops/Yam_Stage_5.png"));
        public static TextureRegion[] yamTextures = new TextureRegion[]{
            yamStage1Texture,
            yamStage2Texture,
            yamStage3Texture,
            yamStage4Texture,
            yamStage5Texture,
        };

        //sweetGemBerry:
        public static TextureRegion sweetGemBerryTexture = new TextureRegion(new Texture("Crops/Sweet_Gem_Berry.png"));
        public static TextureRegion rareSeedTexture = new TextureRegion(new Texture("Crops/Rare_Seed.png"));
        public static TextureRegion sweetGemBerryStage1Texture = new TextureRegion(new Texture("Crops/Sweet_Gem_Berry_Stage_1.png"));
        public static TextureRegion sweetGemBerryStage2Texture = new TextureRegion(new Texture("Crops/Sweet_Gem_Berry_Stage_2.png"));
        public static TextureRegion sweetGemBerryStage3Texture = new TextureRegion(new Texture("Crops/Sweet_Gem_Berry_Stage_3.png"));
        public static TextureRegion sweetGemBerryStage4Texture = new TextureRegion(new Texture("Crops/Sweet_Gem_Berry_Stage_4.png"));
        public static TextureRegion sweetGemBerryStage5Texture = new TextureRegion(new Texture("Crops/Sweet_Gem_Berry_Stage_5.png"));
        public static TextureRegion sweetGemBerryStage6Texture = new TextureRegion(new Texture("Crops/Sweet_Gem_Berry_Stage_6.png"));
        public static TextureRegion[] sweetGemBerryTextures = new TextureRegion[]{
            sweetGemBerryStage1Texture,
            sweetGemBerryStage2Texture,
            sweetGemBerryStage3Texture,
            sweetGemBerryStage4Texture,
            sweetGemBerryStage5Texture,
            sweetGemBerryStage6Texture,
        };

        //powdermelon:
        public static TextureRegion powdermelonTexture = new TextureRegion(new Texture("Crops/Powdermelon.png"));
        public static TextureRegion powdermelonSeedsTexture = new TextureRegion(new Texture("Crops/Powdermelon_Seeds.png"));
        public static TextureRegion powdermelonStage1Texture = new TextureRegion(new Texture("Crops/Powdermelon_Stage_1.png"));
        public static TextureRegion powdermelonStage2Texture = new TextureRegion(new Texture("Crops/Powdermelon_Stage_2.png"));
        public static TextureRegion powdermelonStage3Texture = new TextureRegion(new Texture("Crops/Powdermelon_Stage_3.png"));
        public static TextureRegion powdermelonStage4Texture = new TextureRegion(new Texture("Crops/Powdermelon_Stage_4.png"));
        public static TextureRegion powdermelonStage5Texture = new TextureRegion(new Texture("Crops/Powdermelon_Stage_5.png"));
        public static TextureRegion powdermelonStage6Texture = new TextureRegion(new Texture("Crops/Powdermelon_Stage_6.png"));
        public static TextureRegion[] powdermelonTextures = new TextureRegion[]{
            powdermelonStage1Texture,
            powdermelonStage2Texture,
            powdermelonStage3Texture,
            powdermelonStage4Texture,
            powdermelonStage5Texture,
            powdermelonStage6Texture,
        };

        //ancientFruit:
        public static TextureRegion ancientFruitTexture = new TextureRegion(new Texture("Crops/Ancient_Fruit.png"));
        public static TextureRegion ancientSeedsTexture = new TextureRegion(new Texture("Crops/Ancient_Seeds.png"));
        public static TextureRegion ancientFruitStage1Texture = new TextureRegion(new Texture("Crops/Ancient_Fruit_Stage_1.png"));
        public static TextureRegion ancientFruitStage2Texture = new TextureRegion(new Texture("Crops/Ancient_Fruit_Stage_2.png"));
        public static TextureRegion ancientFruitStage3Texture = new TextureRegion(new Texture("Crops/Ancient_Fruit_Stage_3.png"));
        public static TextureRegion ancientFruitStage4Texture = new TextureRegion(new Texture("Crops/Ancient_Fruit_Stage_4.png"));
        public static TextureRegion ancientFruitStage5Texture = new TextureRegion(new Texture("Crops/Ancient_Fruit_Stage_5.png"));
        public static TextureRegion ancientFruitStage6Texture = new TextureRegion(new Texture("Crops/Ancient_Fruit_Stage_6.png"));
        public static TextureRegion ancientFruitStage7Texture = new TextureRegion(new Texture("Crops/Ancient_Fruit_Stage_7.png"));
        public static TextureRegion[] ancientFruitTextures = new TextureRegion[]{
            ancientFruitStage1Texture,
            ancientFruitStage2Texture,
            ancientFruitStage3Texture,
            ancientFruitStage4Texture,
            ancientFruitStage5Texture,
            ancientFruitStage6Texture,
            ancientFruitStage7Texture,
        };

        //MixedSeeds:
        public static TextureRegion mixedSeedsTexture = new TextureRegion(new Texture("Crops/Mixed_Seeds.png"));


    //ForagingCrops:

        public static TextureRegion daffodil = new TextureRegion(new Texture("Foraging/Daffodil.png"));
        public static TextureRegion dandelion = new TextureRegion(new Texture("Foraging/Dandelion.png"));
        public static TextureRegion leek = new TextureRegion(new Texture("Foraging/Leek.png"));
        public static TextureRegion morel = new TextureRegion(new Texture("Foraging/Morel.png"));
        public static TextureRegion salmonBerry = new TextureRegion(new Texture("Foraging/SalmonBerry.png"));
        public static TextureRegion springOnion = new TextureRegion(new Texture("Foraging/Spring_Onion.png"));
        public static TextureRegion wildHorseradish = new TextureRegion(new Texture("Foraging/Wild_Horseradish.png"));
        public static TextureRegion fiddleHeadFern = new TextureRegion(new Texture("Foraging/Fiddlehead_Fern.png"));
        public static TextureRegion grape = new TextureRegion(new Texture("Foraging/Grape.png"));
        public static TextureRegion redMushroom = new TextureRegion(new Texture("Foraging/Red_Mushroom.png"));
        public static TextureRegion spiceBerry = new TextureRegion(new Texture("Foraging/Spice_Berry.png"));
        public static TextureRegion sweetPea = new TextureRegion(new Texture("Foraging/Sweet_Pea.png"));
        public static TextureRegion blackberry = new TextureRegion(new Texture("Foraging/Blackberry.png"));
        public static TextureRegion chanterelle = new TextureRegion(new Texture("Foraging/Chanterelle.png"));
        public static TextureRegion hazelnut = new TextureRegion(new Texture("Foraging/Hazelnut.png"));
        public static TextureRegion purpleMushroom = new TextureRegion(new Texture("Foraging/Purple_Mushroom.png"));
        public static TextureRegion wildPlum = new TextureRegion(new Texture("Foraging/Wild_Plum.png"));
        public static TextureRegion crocus = new TextureRegion(new Texture("Foraging/Crocus.png"));
        public static TextureRegion crystalFruit = new TextureRegion(new Texture("Foraging/Crystal_Fruit.png"));
        public static TextureRegion holly = new TextureRegion(new Texture("Foraging/Holly.png"));
        public static TextureRegion snowYam = new TextureRegion(new Texture("Foraging/Snow_Yam.png"));
        public static TextureRegion winterRoot = new TextureRegion(new Texture("Foraging/Winter_Root.png"));


    //Fertilizer:
        public static TextureRegion waterFertilizer = new TextureRegion(new Texture("Fertilizer/Basic_Retaining_Soil.png"));
        public static TextureRegion growthFertilizer = new TextureRegion(new Texture("Fertilizer/Hyper_Speed-Gro.png"));

    //Minerals:
        public static TextureRegion quartz = new TextureRegion(new Texture("Mineral/Quartz.png"));
        public static TextureRegion earthCrystal = new TextureRegion(new Texture("Mineral/Earth_Crystal.png"));
        public static TextureRegion frozenTear = new TextureRegion(new Texture("Mineral/Frozen_Tear.png"));
        public static TextureRegion fireQuartz = new TextureRegion(new Texture("Mineral/Fire_Quartz.png"));
        public static TextureRegion emerald = new TextureRegion(new Texture("Gem/Emerald.png"));
        public static TextureRegion aquamarine = new TextureRegion(new Texture("Gem/Aquamarine.png"));
        public static TextureRegion ruby = new TextureRegion(new Texture("Gem/Ruby.png"));
        public static TextureRegion amethyst = new TextureRegion(new Texture("Gem/Amethyst.png"));
        public static TextureRegion topaz = new TextureRegion(new Texture("Gem/Topaz.png"));
        public static TextureRegion jade = new TextureRegion(new Texture("Gem/Jade.png"));
        public static TextureRegion diamond = new TextureRegion(new Texture("Gem/Diamond.png"));
        public static TextureRegion prismaticShard = new TextureRegion(new Texture("Gem/Prismatic_Shard.png"));
        public static TextureRegion copperOre = new TextureRegion(new Texture("Resource/Copper_Ore.png"));
        public static TextureRegion ironOre = new TextureRegion(new Texture("Resource/Iron_Ore.png"));
        public static TextureRegion goldOre = new TextureRegion(new Texture("Resource/Gold_Ore.png"));
        public static TextureRegion iridiumOre = new TextureRegion(new Texture("Resource/Iridium_Ore.png"));
        public static TextureRegion coalMineral = new TextureRegion(new Texture("Resource/Coal.png"));


    //Fish:
        public static TextureRegion salmon = new TextureRegion(new Texture("Fish/Salmon.png"));
        public static TextureRegion sardine = new TextureRegion(new Texture("Fish/Sardine.png"));
        public static TextureRegion shad = new TextureRegion(new Texture("Fish/Shad.png"));
        public static TextureRegion blueDiscus = new TextureRegion(new Texture("Fish/Blue_Discus.png"));
        public static TextureRegion midnightCarp = new TextureRegion(new Texture("Fish/Midnight_Carp.png"));
        public static TextureRegion squid = new TextureRegion(new Texture("Fish/Squid.png"));
        public static TextureRegion tuna = new TextureRegion(new Texture("Fish/Tuna.png"));
        public static TextureRegion perch = new TextureRegion(new Texture("Fish/Perch.png"));
        public static TextureRegion flounder = new TextureRegion(new Texture("Fish/Flounder.png"));
        public static TextureRegion lionfish = new TextureRegion(new Texture("Fish/Lionfish.png"));
        public static TextureRegion herring = new TextureRegion(new Texture("Fish/Herring.png"));
        public static TextureRegion ghostfish = new TextureRegion(new Texture("Fish/Ghostfish.png"));
        public static TextureRegion tilapia = new TextureRegion(new Texture("Fish/Tilapia.png"));
        public static TextureRegion dorado = new TextureRegion(new Texture("Fish/Dorado.png"));
        public static TextureRegion sunfish = new TextureRegion(new Texture("Fish/Sunfish.png"));
        public static TextureRegion rainbowTrout = new TextureRegion(new Texture("Fish/Rainbow_Trout.png"));
        public static TextureRegion legend = new TextureRegion(new Texture("Fish/Legend.png"));
        public static TextureRegion glacierfish = new TextureRegion(new Texture("Fish/Glacierfish.png"));
        public static TextureRegion angler = new TextureRegion(new Texture("Fish/Angler.png"));
        public static TextureRegion crimsonfish = new TextureRegion(new Texture("Fish/Crimsonfish.png"));

    //Mini_Game:
        public static TextureRegionDrawable fishingSystem = new TextureRegionDrawable(new TextureRegion(new Texture("MiniGame/FishingSystem.png")));
        public static TextureRegionDrawable greenBar = new TextureRegionDrawable(new TextureRegion(new Texture("MiniGame/Green_Bar.png")));
        public static TextureRegionDrawable normalFish = new TextureRegionDrawable(new TextureRegion(new Texture("MiniGame/Normal_Fish.png")));
        public static TextureRegionDrawable legendFish = new TextureRegionDrawable(new TextureRegion(new Texture("MiniGame/Legend_Fish.png")));


    // Animals :

        //Chicken :
        public static TextureRegion chickenTexture = new TextureRegion(new Texture("Animals/Brown_Chicken.png"));
        public static TextureRegion eggTexture = new TextureRegion(new Texture("Animal_product/Brown_Egg.png"));
        public static TextureRegion largeEggTexture = new TextureRegion(new Texture("Animal_product/Large_Brown_Egg.png"));
        public static Map<AnimalState, Animation<TextureRegion>> chickenAnimations = new HashMap<>();
        static {
            TextureRegion[][] chicken = TextureRegion.split(new Texture("Animals_animation/Chicken_Brown.png"), 16, 16);
            chickenAnimations.put(AnimalState.MOVING_RIGHT, new Animation<>(0.15f, chicken[1]));
            chickenAnimations.put(AnimalState.MOVING_LEFT, new Animation<>(0.15f, chicken[3]));
            chickenAnimations.put(AnimalState.MOVING_UP, new Animation<>(0.15f, chicken[2]));
            chickenAnimations.put(AnimalState.MOVING_DOWN, new Animation<>(0.15f, chicken[0]));
            chickenAnimations.put(AnimalState.IN_FARM_EATING, new Animation<>(0.15f, chicken[6]));
            chickenAnimations.put(AnimalState.IS_PETTING, new Animation<>(0.15f, chicken[4]));
        }


        //Duck :
        public static TextureRegion duckTexture = new TextureRegion(new Texture("Animals/Duck.png"));
        public static TextureRegion duckEggTexture = new TextureRegion(new Texture("Animal_product/Duck_Egg.png"));
        public static TextureRegion duckFeatherTexture = new TextureRegion(new Texture("Animal_product/Duck_Feather.png"));
        public static Map<AnimalState, Animation<TextureRegion>> duckAnimations = new HashMap<>();
        static {
            TextureRegion[][] duck = TextureRegion.split(new Texture("Animals_animation/Duck.png"), 16, 16);
            duckAnimations.put(AnimalState.MOVING_RIGHT, new Animation<>(0.15f, duck[1]));
            duckAnimations.put(AnimalState.MOVING_LEFT, new Animation<>(0.15f, duck[3]));
            duckAnimations.put(AnimalState.MOVING_UP, new Animation<>(0.15f, duck[2]));
            duckAnimations.put(AnimalState.MOVING_DOWN, new Animation<>(0.15f, duck[0]));
            duckAnimations.put(AnimalState.IN_FARM_EATING, new Animation<>(0.15f, duck[6]));
            duckAnimations.put(AnimalState.IS_PETTING, new Animation<>(0.15f, duck[4]));
        }

        //Rabbit :
        public static TextureRegion rabbitTexture = new TextureRegion(new Texture("Animals/Rabbit.png"));
        public static TextureRegion rabbitFootTexture = new TextureRegion(new Texture("Animal_product/Rabbit%27s_Foot.png"));
        public static TextureRegion woolTexture = new TextureRegion(new Texture("Animal_product/Wool.png"));
        public static Map<AnimalState, Animation<TextureRegion>> rabbitAnimations = new HashMap<>();
        static {
            TextureRegion[][] rabbit = TextureRegion.split(new Texture("Animals_animation/Rabbit.png"), 16, 16);
            rabbitAnimations.put(AnimalState.MOVING_RIGHT, new Animation<>(0.15f, rabbit[1]));
            rabbitAnimations.put(AnimalState.MOVING_LEFT, new Animation<>(0.15f, rabbit[3]));
            rabbitAnimations.put(AnimalState.MOVING_UP, new Animation<>(0.15f, rabbit[2]));
            rabbitAnimations.put(AnimalState.MOVING_DOWN, new Animation<>(0.15f, rabbit[0]));
            rabbitAnimations.put(AnimalState.IN_FARM_EATING, new Animation<>(0.15f, rabbit[6]));
            rabbitAnimations.put(AnimalState.IS_PETTING, new Animation<>(0.15f, rabbit[4]));
        }

        //Dinosaur :
        public static TextureRegion dinosaurTexture = new TextureRegion(new Texture("Animals/Dinosaur.png"));
        public static TextureRegion dinosaurEggTexture = new TextureRegion(new Texture("Animal_product/Dinosaur_Egg.png"));
        public static Map<AnimalState, Animation<TextureRegion>> dinosaurAnimations = new HashMap<>();
        static {
            TextureRegion[][] dinosaur = TextureRegion.split(new Texture("Animals_animation/Dinosaur.png"), 16, 16);
            dinosaurAnimations.put(AnimalState.MOVING_RIGHT, new Animation<>(0.15f, dinosaur[1]));
            dinosaurAnimations.put(AnimalState.MOVING_LEFT, new Animation<>(0.15f, dinosaur[3]));
            dinosaurAnimations.put(AnimalState.MOVING_UP, new Animation<>(0.15f, dinosaur[2]));
            dinosaurAnimations.put(AnimalState.MOVING_DOWN, new Animation<>(0.15f, dinosaur[0]));
            dinosaurAnimations.put(AnimalState.IN_FARM_EATING, new Animation<>(0.15f, dinosaur[6]));
            dinosaurAnimations.put(AnimalState.IS_PETTING, new Animation<>(0.15f, dinosaur[4]));
        }

        //Cow :
        public static TextureRegion cowTexture = new TextureRegion(new Texture("Animals/White_Cow.png"));
        public static TextureRegion milkTexture = new TextureRegion(new Texture("Animal_product/Milk.png"));
        public static TextureRegion largeMilkTexture = new TextureRegion(new Texture("Animal_product/Large_Milk.png"));
        public static Map<AnimalState, Animation<TextureRegion>> cowAnimations = new HashMap<>();
        static {
            TextureRegion[][] cow = TextureRegion.split(new Texture("Animals_animation/Cow_White.png"), 32, 32);
            cowAnimations.put(AnimalState.MOVING_RIGHT, new Animation<>(0.15f, cow[1]));
            cowAnimations.put(AnimalState.MOVING_LEFT, new Animation<>(0.15f, cow[0])); //dont have
            cowAnimations.put(AnimalState.MOVING_UP, new Animation<>(0.15f, cow[2]));
            cowAnimations.put(AnimalState.MOVING_DOWN, new Animation<>(0.15f, cow[0]));
            cowAnimations.put(AnimalState.IN_FARM_EATING, new Animation<>(0.15f, cow[4]));
            cowAnimations.put(AnimalState.IS_PETTING, new Animation<>(0.15f, cow[3]));
        }

        //Goat :
        public static TextureRegion goatTexture = new TextureRegion(new Texture("Animals/Goat.png"));
        public static TextureRegion goatMilkTexture = new TextureRegion(new Texture("Animal_product/Goat_Milk.png"));
        public static TextureRegion largeGoatMilkTexture = new TextureRegion(new Texture("Animal_product/Large_Goat_Milk.png"));
        public static Map<AnimalState, Animation<TextureRegion>> goatAnimations = new HashMap<>();
        static {
            TextureRegion[][] goat = TextureRegion.split(new Texture("Animals_animation/Goat.png"), 32, 32);
            goatAnimations.put(AnimalState.MOVING_RIGHT, new Animation<>(0.15f, goat[1]));
            goatAnimations.put(AnimalState.MOVING_LEFT, new Animation<>(0.15f, goat[0]));  //dont have
            goatAnimations.put(AnimalState.MOVING_UP, new Animation<>(0.15f, goat[2]));
            goatAnimations.put(AnimalState.MOVING_DOWN, new Animation<>(0.15f, goat[0]));
            goatAnimations.put(AnimalState.IN_FARM_EATING, new Animation<>(0.15f, goat[4]));
            goatAnimations.put(AnimalState.IS_PETTING, new Animation<>(0.15f, goat[3]));
        }

        //Sheep :
        public static TextureRegion sheepTexture = new TextureRegion(new Texture("Animals/Sheep.png"));
        public static Map<AnimalState, Animation<TextureRegion>> sheepAnimations = new HashMap<>();
        static {
            TextureRegion[][] sheep = TextureRegion.split(new Texture("Animals_animation/Sheep.png"), 32, 32);
            sheepAnimations.put(AnimalState.MOVING_RIGHT, new Animation<>(0.15f, sheep[1]));
            sheepAnimations.put(AnimalState.MOVING_LEFT, new Animation<>(0.15f, sheep[0]));  //dont have
            sheepAnimations.put(AnimalState.MOVING_UP, new Animation<>(0.15f, sheep[2]));
            sheepAnimations.put(AnimalState.MOVING_DOWN, new Animation<>(0.15f, sheep[0]));
            sheepAnimations.put(AnimalState.IN_FARM_EATING, new Animation<>(0.15f, sheep[4]));
            sheepAnimations.put(AnimalState.IS_PETTING, new Animation<>(0.15f, sheep[3]));
        }

        //Pig :
        public static TextureRegion pigTexture = new TextureRegion(new Texture("Animals/Pig.png"));
        public static TextureRegion truffleTexture = new TextureRegion(new Texture("Animal_product/Truffle.png"));
        public static Map<AnimalState, Animation<TextureRegion>> pigAnimations = new HashMap<>();
        static {
            TextureRegion[][] pig = TextureRegion.split(new Texture("Animals_animation/Pig.png"), 32, 32);
            pigAnimations.put(AnimalState.MOVING_RIGHT, new Animation<>(0.15f, pig[1]));
            pigAnimations.put(AnimalState.MOVING_LEFT, new Animation<>(0.15f, pig[0]));  //dont have
            pigAnimations.put(AnimalState.MOVING_UP, new Animation<>(0.15f, pig[2]));
            pigAnimations.put(AnimalState.MOVING_DOWN, new Animation<>(0.15f, pig[0]));
            pigAnimations.put(AnimalState.IN_FARM_EATING, new Animation<>(0.15f, pig[4]));
            pigAnimations.put(AnimalState.IS_PETTING, new Animation<>(0.15f, pig[3]));
        }

    //Animal_Habitat :

        //Barn :
        public static TextureRegion barnTexture = new TextureRegion(new Texture("Animal_Habitat/Barn.png"));
        public static TextureRegion bigBarnTexture = new TextureRegion(new Texture("Animal_Habitat/Big_Barn.png"));
        public static TextureRegion deluxeBarnTexture = new TextureRegion(new Texture("Animal_Habitat/Deluxe_Barn.png"));
        //Coop :
        public static TextureRegion coopTexture = new TextureRegion(new Texture("Animal_Habitat/Coop.png"));
        public static TextureRegion bigCoopTexture = new TextureRegion(new Texture("Animal_Habitat/Big_Coop.png"));
        public static TextureRegion deluxeCoopTexture = new TextureRegion(new Texture("Animal_Habitat/Deluxe_Coop.png"));


    public static TextureAtlas character1Atlas = new TextureAtlas(Gdx.files.internal("Characters/sprites_player.atlas"));
    public static ArrayList<Animation<TextureRegion>> playerAnimations = new ArrayList<>();
    static {
        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(character1Atlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(character1Atlas.findRegion(region));
                }
            }
            Animation<TextureRegion> a = new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP);
            playerAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }
    }

    public static Texture defaultTileTexture = new Texture("Flooring/Flooring_50.png");
    public static Texture defaultTileTexture2 = new Texture("Flooring/Flooring_28.png");
    public static Texture defaultTileTexture3 = new Texture("Flooring/Flooring_44.png");
    public static Texture lakeTexture = new Texture("Flooring/Flooring_26.png");


    public static Texture greenHouseTexture = new Texture("Greenhouse/greenhouse.png");
    public static TextureRegion[][] regions ;
    static {
        regions = TextureRegion.split(greenHouseTexture , greenHouseTexture.getWidth() / 6, greenHouseTexture.getHeight() / 6);
    }

    public static TextureRegion cottageTexture = new TextureRegion(new Texture("Cottage.png"));
    public static TextureRegion[][] cottageRegions ;
    static {
        Texture cottageTexture2 = new Texture("Cottage.png");
        cottageRegions = TextureRegion.split(cottageTexture2 , cottageTexture.getRegionWidth() / 6 , cottageTexture.getRegionHeight() / 6);
    }


    public static Texture whiteBox;

    public static void loadWhiteBox() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 1, 1, 1);
        pixmap.fill();
        whiteBox = new Texture(pixmap);
        pixmap.dispose();
    }

    static {
        loadWhiteBox();
    }


    public static Texture blacksmithTexture = new Texture("Stores/Blacksmith.png");
    public static TextureRegion[][] blacksmithRegions ;
    static {
        blacksmithRegions = TextureRegion.split(blacksmithTexture , blacksmithTexture.getWidth() / 6, blacksmithTexture.getHeight() / 4);
    }

    public static Texture fishShopTexture = new Texture("Stores/Fish_Shop.png");
    public static TextureRegion[][] fishShopRegions ;
    static {
        fishShopRegions = TextureRegion.split(fishShopTexture , fishShopTexture.getWidth() / 4, fishShopTexture.getHeight() / 4);
    }

    public static Texture carpenterShopTexture = new Texture("Stores/Carpenter's_Shop.png");
    public static TextureRegion[][] carpenterShopRegions ;
    static {
        carpenterShopRegions = TextureRegion.split(carpenterShopTexture , carpenterShopTexture.getWidth() / 16, carpenterShopTexture.getHeight() / 12);
    }


    public static Texture marnieRanchTexture = new Texture("Stores/Ranch.png");
    public static TextureRegion[][] marnieRanchRegions ;
    static {
        marnieRanchRegions = TextureRegion.split(marnieRanchTexture , marnieRanchTexture.getWidth() / 18, marnieRanchTexture.getHeight() / 8);

    }

    public static Texture stardopSaloonTexture = new Texture("Stores/Saloon.png");
    public static TextureRegion[][] stardopSaloonRegions ;
    static {
        stardopSaloonRegions = TextureRegion.split(stardopSaloonTexture , stardopSaloonTexture.getWidth() / 4, stardopSaloonTexture.getHeight() / 4);

    }


    public static Texture jojaMartTexture = new Texture("Stores/Jojamart.png");
    public static TextureRegion[][] jojaMartRegions ;
    static {
        jojaMartRegions = TextureRegion.split(jojaMartTexture , jojaMartTexture.getWidth() / 4, jojaMartTexture.getHeight() / 4);
    }


    public static Texture pierresShopTexture = new Texture("Stores/Pierres_shop.png");
    public static TextureRegion[][] pierresShopRegions ;
    static {
        pierresShopRegions = TextureRegion.split(pierresShopTexture , pierresShopTexture.getWidth() / 4, pierresShopTexture.getHeight() / 4);
    }

    public static Texture npcHome1Texture = new Texture("NpcHomes/NpcHome1.png");
    public static TextureRegion[][] npcHome1Regions;
    static {
        npcHome1Regions = TextureRegion.split(npcHome1Texture , npcHome1Texture.getWidth() / 6, npcHome1Texture.getHeight() / 6);

    }

    public static Texture npcHome2Texture = new Texture("NpcHomes/NpcHome2.png");
    public static TextureRegion[][] npcHome2Regions;
    static {
        npcHome2Regions = TextureRegion.split(npcHome2Texture , npcHome2Texture.getWidth() / 6, npcHome2Texture.getHeight() / 6);

    }

    public static Texture npcHome3Texture = new Texture("NpcHomes/NpcHome3.png");
    public static TextureRegion[][] npcHome3Regions;
    static {
        npcHome3Regions = TextureRegion.split(npcHome3Texture , npcHome3Texture.getWidth() / 6, npcHome3Texture.getHeight() / 6);

    }


    public static Texture npcHome4Texture = new Texture("NpcHomes/NpcHome4.png");
    public static TextureRegion[][] npcHome4Regions;
    static {
        npcHome4Regions = TextureRegion.split(npcHome4Texture , npcHome4Texture.getWidth() / 6, npcHome4Texture.getHeight() / 6);

    }

    public static Texture npcHome5Texture = new Texture("NpcHomes/NpcHome5.png");
    public static TextureRegion[][] npcHome5Regions;
    static {
        npcHome5Regions = TextureRegion.split(npcHome5Texture , npcHome5Texture.getWidth() / 6, npcHome5Texture.getHeight() / 6);

    }


    public static TextureRegion clockTexture = new TextureRegion(new Texture("Clock/newClock.png"));

    public static TextureRegion emptyTile = new TextureRegion(new Texture("Tile_Selection/Empty_Tile.png"));
    public static TextureRegion selectedTile = new TextureRegion(new Texture("Tile_Selection/Selected_Tile.png"));

    public static TextureRegion coinTexture = new TextureRegion(new Texture("Gold.png"));

    public static TextureRegion hoeTexture = new TextureRegion(new Texture("Hoe/Hoe.png"));
    public static TextureRegion copperHoeTexture = new TextureRegion(new Texture("Hoe/Copper_Hoe.png"));
    public static TextureRegion steelHoeTexture = new TextureRegion(new Texture("Hoe/Steel_Hoe.png"));
    public static TextureRegion goldHoeTexture = new TextureRegion(new Texture("Hoe/Gold_Hoe.png"));
    public static TextureRegion iridiumHoeTexture = new TextureRegion(new Texture("Hoe/Iridium_Hoe.png"));


    public static TextureRegion woodTexture = new TextureRegion(new Texture("Wood.png"));

    public static TextureRegion trashTexture = new TextureRegion(new Texture("Inventory/Trash.png"));
    public static TextureRegionDrawable trashDrawable = new TextureRegionDrawable(trashTexture);

    public static TextureRegionDrawable socialHeartTextureDrawable = new TextureRegionDrawable
        (new TextureRegion(new Texture("Inventory/Social.png")));

    public static TextureRegionDrawable skillsTextureDrawable = new TextureRegionDrawable
        (new TextureRegion(new Texture("Inventory/Skills.png")));

    public static TextureRegionDrawable mapTextureDrawable = new TextureRegionDrawable
        (new TextureRegion(new Texture("Inventory/Map.png")));

    public static TextureRegionDrawable exitTextureDrawable = new TextureRegionDrawable
        (new TextureRegion(new Texture("Inventory/Exit.png")));

    public static TextureRegionDrawable friendshipTextureDrawable = new TextureRegionDrawable
        (new TextureRegion(new Texture("Inventory/Friendship.png")));


    public static TextureRegionDrawable fishingTextureDrawable = new TextureRegionDrawable
        (new TextureRegion(new Texture("Inventory/Carp.png")));

    public static TextureRegionDrawable foragingTextureDrawable = new TextureRegionDrawable
        (new TextureRegion(new Texture("Inventory/Dandelion.png")));

    public static TextureRegion plowedTile = new TextureRegion(new Texture("PlowedTile.png"));
    public static TextureRegion waterFertilizedTile = new TextureRegion(new Texture("Tile/Basic-Retaining-Soil_tile.png"));
    public static TextureRegion growthFertilizedTile = new TextureRegion(new Texture("Tile/Hyper_Speed-Gro_tile.png"));


    public static TextureRegion snowyTile = new TextureRegion(new Texture("Tile/snowy.png"));
    public static TextureRegion snowyTile2 = new TextureRegion(new Texture("Tile/snowy2.png"));

    public static TextureRegion trainingFishingPole = new TextureRegion(new Texture("Fishing_Pole/Training_Rod.png"));
    public static TextureRegion bambooFishingPole = new TextureRegion(new Texture("Fishing_Pole/Bamboo_Pole.png"));
    public static TextureRegion fiberglassFishingPole = new TextureRegion(new Texture("Fishing_Pole/Fiberglass_Rod.png"));
    public static TextureRegion iridiumFishingPole = new TextureRegion(new Texture("Fishing_Pole/Iridium_Rod.png"));
    public static TextureRegion advancedIridiumFishingPole = new TextureRegion(new Texture("Fishing_Pole/Advanced_Iridium_Rod.png"));

    public static TextureRegion wateredTileTexture = new TextureRegion(new Texture("Flooring/Flooring_57.png"));
    public static Texture rainTexture = new Texture("Rain.png");
    public static TextureRegion[][] rainTextureArray = TextureRegion.split(rainTexture,32 , 32);
    static Array<TextureRegion> frames = new Array<>();
    static {
        for(int i=0 ; i<1 ; i++){
            for(int j=0 ; j<1 ; j++){
                frames.add(rainTextureArray[i][j]);
            }
        }
    }


    public static Animation<TextureRegion> rainAnimation = new Animation<>(0.1f , frames , Animation.PlayMode.LOOP);

    public static Texture snowTexture = new Texture("snow.png");
    public static TextureRegion[][] snowTextureArray = TextureRegion.split(snowTexture , 25 , 30);
    static Array<TextureRegion> frames2 = new Array<>();
    static {
        for(int i=0 ; i<1 ; i++){
            for(int j=0 ; j<6 ; j++){
                frames2.add(snowTextureArray[i][j]);
            }
        }
    }

    public static Animation<TextureRegion> snowAnimation = new Animation<>(0.1f , frames2 , Animation.PlayMode.LOOP);
    public static Texture windTexture = new Texture("Wind.png");
    public static TextureRegion[][] windTextureArray = TextureRegion.split(windTexture , 40 , 21);
    static Array<TextureRegion> frames3 = new Array<>();
    static {
        for(int i=0 ; i<1 ; i++){
            for(int j=0 ; j<6 ; j++){
                frames3.add(windTextureArray[i][j]);
            }
        }
    }
    public static Animation<TextureRegion> windAnimation = new Animation<>(0.1f , frames3 , Animation.PlayMode.LOOP);

    public static TextureRegion shippingBinTexture = new TextureRegion(new Texture("Mini-Shipping_Bin.png"));


    public static Texture playerPicture = new Texture("Characters/player.png");
    public static TextureRegion[][] faintedPlayerTextureArray = TextureRegion.split(playerPicture , 16 , 32);
    public static Animation<TextureRegion> faintAnimation = new Animation<>(0.15f , faintedPlayerTextureArray[6] );

    public static Texture filledStar = new Texture("Star/emptyStar.png");
    public static Texture emptyStar = new Texture("Star/filledStar.png");
}
