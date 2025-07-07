package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("wood_bg.png"))));
    public static TextureRegionDrawable windowWoodBackground =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("window_org.png"))));
    public static TextureRegionDrawable closeWindow =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("close_window.png"))));


    //Tools :
        //Axe :

            public static Texture axeTexture = new Texture("Tools/Axe/Axe.png");
            public static Texture steelAxeTexture = new Texture("Tools/Axe/Steel_Axe.png");
            public static Texture copperAxeTexture = new Texture("Tools/Axe/Copper_Axe.png");
            public static Texture goldAxeTexture = new Texture("Tools/Axe/Gold_Axe.png");
            public static Texture iridiumAxeTexture = new Texture("Tools/Axe/Iridium_Axe.png");

        //Pickaxe :

            public static Texture pickaxeTexture = new Texture("Tools/Pickaxe/Pickaxe.png");
            public static Texture steelPickaxeTexture = new Texture("Tools/Pickaxe/Steel_Pickaxe.png");
            public static Texture copperPickaxeTexture = new Texture("Tools/Pickaxe/Copper_Pickaxe.png");
            public static Texture goldPickaxeTexture = new Texture("Tools/Pickaxe/Gold_Pickaxe.png");
            public static Texture iriduimPiclaxeTexture = new Texture("Tools/Pickaxe/Iridium_Pickaxe.png");

        //Scythe :

            public static Texture scytheTexture = new Texture("Tools/Scythe.png");
            public static Texture goldScytheTexture = new Texture("Tools/Golden_Scythe.png");
            public static Texture iridiumScytheTexture = new Texture("Tools/Iridium_Scythe.png");

        //Backpack :

            public static Texture backpackTexture = new Texture("Tools/Backpack.png");
            public static Texture backpack36Texture = new Texture("Tools/36_Backpack.png");

        //TrashCan :

            public static Texture copperTrashCanTexture = new Texture("Tools/Trash_Can_Copper.png");
            public static Texture goldTrashCanTexture = new Texture("Tools/Trash_Can_Gold.png");
            public static Texture steelTrashCanTexture = new Texture("Tools/Trash_Can_Steel.png");
            public static Texture iridiumTrashCanTexture = new Texture("Tools/Trash_Can_Iridium.png");

        //MilkPail :

            public static Texture milkPailTexture = new Texture("Tools/Milk_Pail.png");

        //Shear :

            public static Texture shearTexture = new Texture("Tools/Shears.png");

        //wateringCan :

            public static Texture wateringCanTexture = new Texture("Watering_Can/Watering_Can.png");
            public static Texture copperWateringCanTexture = new Texture("Watering_Can/Copper_Watering_Can.png");
            public static Texture goldWateringCanTexture = new Texture("Watering_Can/Gold_Watering_Can.png");
            public static Texture steelWateringCanTexture = new Texture("Watering_Can/Steel_Watering_Can.png");
            public static Texture iridiumWateringCanTexture = new Texture("Watering_Can/Iridium_Watering_Can.png");

    //Villagers :

        public static Texture abigailTexture = new Texture("Villagers/Abigail.png");
        public static Texture harveyTexture = new Texture("Villagers/Harvey.png");
        public static Texture leahTexture = new Texture("Villagers/Leah.png");
        public static Texture marnieTexture = new Texture("Villagers/Marnie.png");
        public static Texture pierreTexture = new Texture("Villagers/Pierre.png");
        public static Texture robinTexture = new Texture("Villagers/Robin.png");
        public static Texture sebastianTexture = new Texture("Villagers/Sebastian.png");
        public static Texture willyTexture = new Texture("Villagers/Willy.png");

    //Rock :

        public static Texture farmBoulderTexture = new Texture("Rock/Farm_Boulder.png");
        public static Texture MineBoulderTexture1 = new Texture("Rock/Mine_Boulder_1.png");
        public static Texture mineBoulderTexture2 = new Texture("Rock/Mine_Boulder_2.png");
        public static Texture mineBoulderTexture3 = new Texture("Rock/Mine_Boulder_3.png");
        public static Texture mineBoulderTexture4 = new Texture("Rock/Mine_Boulder_4.png");
        public static Texture quarryBoulderTexture = new Texture("Rock/Quarry_Boulder.png");
        public static Texture stoneIndex32Texture = new Texture("Rock/Stone_Index32.png");
        public static Texture stoneIndex34Texture = new Texture("Rock/Stone_Index34.png");
        public static Texture stoneIndex36Texture = new Texture("Rock/Stone_Index36.png");
        public static Texture stoneIndex38Texture = new Texture("Rock/Stone_Index38.png");
        public static Texture stoneIndex40Texture = new Texture("Rock/Stone_Index40.png");
        public static Texture stoneIndex42Texture = new Texture("Rock/Stone_Index42.png");
        public static Texture stoneIndex48Texture = new Texture("Rock/Stone_Index48.png");
        public static Texture stoneIndex50Texture = new Texture("Rock/Stone_Index50.png");
        public static Texture stoneIndex52Texture = new Texture("Rock/Stone_Index52.png");
        public static Texture stoneIndex54Texture = new Texture("Rock/Stone_Index54.png");
        public static Texture stoneIndex56Texture = new Texture("Rock/Stone_Index56.png");
        public static Texture stoneIndex58Texture = new Texture("Rock/Stone_Index58.png");
        public static Texture stoneIndex343Texture = new Texture("Rock/Stone_Index343.png");
        public static Texture stoneIndex450Texture = new Texture("Rock/Stone_Index450.png");
        public static Texture stoneIndex668Texture = new Texture("Rock/Stone_Index668.png");
        public static Texture stoneIndex670Texture = new Texture("Rock/Stone_Index670.png");

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

        public static Texture honeyTexture = new Texture("Artisan_good/Honey.png");
        public static Texture cheeseTexture = new Texture("Artisan_good/Cheese.png");
        public static Texture goatCheeseTexture = new Texture("Artisan_good/Goat_Cheese.png");
        public static Texture beerTexture = new Texture("Artisan_good/Beer.png");
        public static Texture vinegarTexture = new Texture("Artisan_good/Vinegar.png");
        public static Texture coffeeTexture = new Texture("Artisan_good/Coffee.png");
        public static Texture juiceTexture = new Texture("Artisan_good/Juice.png");
        public static Texture meadTexture = new Texture("Artisan_good/Mead.png");
        public static Texture paleAleTexture = new Texture("Artisan_good/Pale_Ale.png");
        public static Texture wineTexture = new Texture("Artisan_good/Wine.png");
        public static Texture driedMushroomsTexture = new Texture("Artisan_good/Dried_Mushrooms.png");
        public static Texture driedFruitTexture = new Texture("Artisan_good/Dried_Fruit.png");
        public static Texture raisinsTexture = new Texture("Artisan_good/Raisins.png");
        public static Texture coalTexture = new Texture("Artisan_good/Coal.png");
        public static Texture clothTexture = new Texture("Artisan_good/Cloth.png");
        public static Texture mayonnaiseTexture = new Texture("Artisan_good/Mayonnaise.png");
        public static Texture duckMayonnaiseTexture = new Texture("Artisan_good/Duck_Mayonnaise.png");
        public static Texture dinosaurMayonnaiseTexture = new Texture("Artisan_good/Dinosaur_Mayonnaise.png");
        public static Texture oilTexture = new Texture("Artisan_good/Oil.png");
        public static Texture truffleOilTexture = new Texture("Artisan_good/Truffle_Oil.png");
        public static Texture picklesTexture = new Texture("Artisan_good/Pickles.png");
        public static Texture jellyTexture = new Texture("Artisan_good/Jelly.png");
        public static Texture smokedFishTexture = new Texture("Artisan_good/Smoked_Fish.png");
        public static Texture ironBarTexture = new Texture("Artisan_good/Iron_Bar.png");
        public static Texture iridiumBarTexture = new Texture("Artisan_good/Iridium_Bar.png");
        public static Texture copperBarTexture = new Texture("Artisan_good/Copper_Bar.png");
        public static Texture goldBarTexture = new Texture("Artisan_good/Gold_Bar.png");


    //end_process image :

        public static Texture endProcessTexture = new Texture("Achievement/end_process.png");

    //Tree :

        //Apricot :
        public static Texture apricotTexture = new Texture("Trees/Apricot.png");
        public static Texture apricotSaplingTexture = new Texture("Trees/Apricot_Sapling.png");
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
        public static Texture cherryTexture = new Texture("Trees/Cherry.png");
        public static Texture cherrySaplingTexture = new Texture("Trees/Cherry_Sapling.png");
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
        public static Texture bananaTexture = new Texture("Trees/Banana.png");
        public static Texture bananaSaplingTexture = new Texture("Trees/Banana_Sapling.png");
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
        public static Texture mangoTexture = new Texture("Trees/Mango.png");
        public static Texture mangoSaplingTexture = new Texture("Trees/Mango_Sapling.png");
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
        public static Texture orangeTexture = new Texture("Trees/Orange.png");
        public static Texture orangeSaplingTexture = new Texture("Trees/Orange_Sapling.png");
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
        public static Texture peachTexture = new Texture("Trees/Peach.png");
        public static Texture peachSaplingTexture = new Texture("Trees/Peach_Sapling.png");
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
        public static Texture appleTexture = new Texture("Trees/Apple.png");
        public static Texture appleSaplingTexture = new Texture("Trees/Apple_Sapling.png");
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
        public static Texture pomegranateTexture = new Texture("Trees/Pomegranate.png");
        public static Texture pomegranateSaplingTexture = new Texture("Trees/Pomegranate_Sapling.png");
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
        public static Texture oakResinTexture = new Texture("Trees/Oak_Resin.png");   //fruit
        public static Texture acornTexture = new Texture("Trees/Acorn.png");          //source
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
        public static Texture mapleSyrupTexture = new Texture("Trees/Maple_Syrup.png");  //fruit
        public static Texture mapleSeedsTexture = new Texture("Trees/Maple_Seed.png");   //source
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
        public static Texture pineTarTexture = new Texture("Trees/Pine_Tar.png");    //fruit
        public static Texture pineConeTexture = new Texture("Trees/Pine_Cone.png");  //source
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
        public static Texture sapTexture = new Texture("Trees/Sap.png");                     //fruit
        public static Texture mahoganySeedTexture = new Texture("Trees/Mahogany_Seed.png");  //source
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
        public static Texture commonMushroom = new Texture("Trees/Common_Mushroom.png");             //fruit
        public static Texture mushroomTreeSeedTexture = new Texture("Trees/Mushroom_Tree_Seed.png"); //source
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
        public static Texture mysticSyrupTexture = new Texture("Trees/Mystic_Syrup.png");           //fruit
        public static Texture mysticTreeSeedTexture = new Texture("Trees/Mystic_Tree_Seed.png"); //source
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
        public static Texture blueJazzTexture = new Texture("Crops/Blue_Jazz.png");
        public static Texture jazzSeedsTexture = new Texture("Crops/Jazz_Seeds.png");
        public static Texture blueJazzStage1Texture = new Texture("Crops/Blue_Jazz_Stage_1.png");
        public static Texture blueJazzStage2Texture = new Texture("Crops/Blue_Jazz_Stage_2.png");
        public static Texture blueJazzStage3Texture = new Texture("Crops/Blue_Jazz_Stage_3.png");
        public static Texture blueJazzStage4Texture = new Texture("Crops/Blue_Jazz_Stage_4.png");
        public static Texture blueJazzStage5Texture = new Texture("Crops/Blue_Jazz_Stage_5.png");
        public static Texture[] blueJazzTextures = new Texture[]{
            blueJazzStage1Texture,
            blueJazzStage2Texture,
            blueJazzStage3Texture,
            blueJazzStage4Texture,
            blueJazzStage5Texture
        };

        //Carrot:
        public static Texture carrotTexture = new Texture("Crops/Carrot.png");
        public static Texture carrotSeedsTexture = new Texture("Crops/Carrot_Seeds.png");
        public static Texture carrotStage1Texture = new Texture("Crops/Carrot_Stage_1.png");
        public static Texture carrotStage2Texture = new Texture("Crops/Carrot_Stage_2.png");
        public static Texture carrotStage3Texture = new Texture("Crops/Carrot_Stage_3.png");
        public static Texture carrotStage4Texture = new Texture("Crops/Carrot_Stage_4.png");
        public static Texture[] carrotTextures = new Texture[]{
            carrotStage1Texture,
            carrotStage2Texture,
            carrotStage3Texture,
            carrotStage4Texture,
        };

        //Cauliflower:
        public static Texture cauliflowerTexture = new Texture("Crops/Cauliflower.png");
        public static Texture cauliflowerSeedsTexture = new Texture("Crops/Cauliflower_Seeds.png");
        public static Texture cauliflowerStage1Texture = new Texture("Crops/Cauliflower_Stage_1.png");
        public static Texture cauliflowerStage2Texture = new Texture("Crops/Cauliflower_Stage_2.png");
        public static Texture cauliflowerStage3Texture = new Texture("Crops/Cauliflower_Stage_3.png");
        public static Texture cauliflowerStage4Texture = new Texture("Crops/Cauliflower_Stage_4.png");
        public static Texture cauliflowerStage5Texture = new Texture("Crops/Cauliflower_Stage_5.png");
        public static Texture cauliflowerStage6Texture = new Texture("Crops/Cauliflower_Stage_6.png");
        public static Texture[] cauliflowerTextures = new Texture[]{
            cauliflowerStage1Texture,
            cauliflowerStage2Texture,
            cauliflowerStage3Texture,
            cauliflowerStage4Texture,
            cauliflowerStage5Texture,
            cauliflowerStage6Texture,
        };

        //CoffeeBean:
        public static Texture coffeeBeanTexture = new Texture("Crops/Coffee_Bean.png");
        public static Texture coffeeStage1Texture = new Texture("Crops/Coffee_Stage_1.png");
        public static Texture coffeeStage2Texture = new Texture("Crops/Coffee_Stage_2.png");
        public static Texture coffeeStage3Texture = new Texture("Crops/Coffee_Stage_3.png");
        public static Texture coffeeStage4Texture = new Texture("Crops/Coffee_Stage_4.png");
        public static Texture coffeeStage5Texture = new Texture("Crops/Coffee_Stage_5.png");
        public static Texture coffeeStage6Texture = new Texture("Crops/Coffee_Stage_6.png");
        public static Texture coffeeStage7Texture = new Texture("Crops/Coffee_Stage_7.png");
        public static Texture[] coffeeTextures = new Texture[]{
            coffeeStage1Texture,
            coffeeStage2Texture,
            coffeeStage3Texture,
            coffeeStage4Texture,
            coffeeStage5Texture,
            coffeeStage6Texture,
            coffeeStage7Texture,
        };

        //Garlic:
        public static Texture garlicTexture = new Texture("Crops/Garlic.png");
        public static Texture garlicSeedsTexture = new Texture("Crops/Garlic_Seeds.png");
        public static Texture garlicStage1Texture = new Texture("Crops/Garlic_Stage_1.png");
        public static Texture garlicStage2Texture = new Texture("Crops/Garlic_Stage_2.png");
        public static Texture garlicStage3Texture = new Texture("Crops/Garlic_Stage_3.png");
        public static Texture garlicStage4Texture = new Texture("Crops/Garlic_Stage_4.png");
        public static Texture garlicStage5Texture = new Texture("Crops/Garlic_Stage_5.png");
        public static Texture[] garlicTextures = new Texture[]{
            garlicStage1Texture,
            garlicStage2Texture,
            garlicStage3Texture,
            garlicStage4Texture,
            garlicStage5Texture,
        };

        //GreenBean:
        public static Texture greenBeanTexture = new Texture("Crops/Green_Bean.png");
        public static Texture beanStarterTexture = new Texture("Crops/Bean_Starter.png");
        public static Texture greenBeanStage1Texture = new Texture("Crops/Green_Bean_Stage_2.png");
        public static Texture greenBeanStage2Texture = new Texture("Crops/Green_Bean_Stage_3.png");
        public static Texture greenBeanStage3Texture = new Texture("Crops/Green_Bean_Stage_4.png");
        public static Texture greenBeanStage4Texture = new Texture("Crops/Green_Bean_Stage_5.png");
        public static Texture greenBeanStage5Texture = new Texture("Crops/Green_Bean_Stage_6.png");
        public static Texture greenBeanStage6Texture = new Texture("Crops/Green_Bean_Stage_7.png");
        public static Texture greenBeanStage7Texture = new Texture("Crops/Green_Bean_Stage_8.png");
        public static Texture[] greenBeanTextures = new Texture[]{
            greenBeanStage1Texture,
            greenBeanStage2Texture,
            greenBeanStage3Texture,
            greenBeanStage4Texture,
            greenBeanStage5Texture,
            greenBeanStage6Texture,
            greenBeanStage7Texture,
        };

        //Kale:
        public static Texture kaleTexture = new Texture("Crops/Kale.png");
        public static Texture kaleSeedsTexture = new Texture("Crops/Kale_Seeds.png");
        public static Texture kaleStage1Texture = new Texture("Crops/Kale_Stage_1.png");
        public static Texture kaleStage2Texture = new Texture("Crops/Kale_Stage_2.png");
        public static Texture kaleStage3Texture = new Texture("Crops/Kale_Stage_3.png");
        public static Texture kaleStage4Texture = new Texture("Crops/Kale_Stage_4.png");
        public static Texture kaleStage5Texture = new Texture("Crops/Kale_Stage_5.png");
        public static Texture[] kaleTextures = new Texture[]{
            kaleStage1Texture,
            kaleStage2Texture,
            kaleStage3Texture,
            kaleStage4Texture,
            kaleStage5Texture,
        };

        //Parsnip:
        public static Texture parsnipTexture = new Texture("Crops/Parsnip.png");
        public static Texture parsnipSeedsTexture = new Texture("Crops/Parsnip_Seeds.png");
        public static Texture parsnipStage1Texture = new Texture("Crops/Parsnip_Stage_1.png");
        public static Texture parsnipStage2Texture = new Texture("Crops/Parsnip_Stage_2.png");
        public static Texture parsnipStage3Texture = new Texture("Crops/Parsnip_Stage_3.png");
        public static Texture parsnipStage4Texture = new Texture("Crops/Parsnip_Stage_4.png");
        public static Texture parsnipStage5Texture = new Texture("Crops/Parsnip_Stage_5.png");
        public static Texture[] parsnipTextures = new Texture[]{
            parsnipStage1Texture,
            parsnipStage2Texture,
            parsnipStage3Texture,
            parsnipStage4Texture,
            parsnipStage5Texture,
        };

        //Potato:
        public static Texture potatoTexture = new Texture("Crops/Potato.png");
        public static Texture potatoSeedsTexture = new Texture("Crops/Potato_Seeds.png");
        public static Texture potatoStage1Texture = new Texture("Crops/Potato_Stage_1.png");
        public static Texture potatoStage2Texture = new Texture("Crops/Potato_Stage_2.png");
        public static Texture potatoStage3Texture = new Texture("Crops/Potato_Stage_3.png");
        public static Texture potatoStage4Texture = new Texture("Crops/Potato_Stage_4.png");
        public static Texture potatoStage5Texture = new Texture("Crops/Potato_Stage_5.png");
        public static Texture potatoStage6Texture = new Texture("Crops/Potato_Stage_6.png");
        public static Texture[] potatoTextures = new Texture[]{
            potatoStage1Texture,
            potatoStage2Texture,
            potatoStage3Texture,
            potatoStage4Texture,
            potatoStage5Texture,
            potatoStage6Texture,
        };

        //Rhubarb:
        public static Texture rhubarbTexture = new Texture("Crops/Rhubarb.png");
        public static Texture rhubarbSeedsTexture = new Texture("Crops/Rhubarb_Seeds.png");
        public static Texture rhubarbStage1Texture = new Texture("Crops/Rhubarb_Stage_1.png");
        public static Texture rhubarbStage2Texture = new Texture("Crops/Rhubarb_Stage_2.png");
        public static Texture rhubarbStage3Texture = new Texture("Crops/Rhubarb_Stage_3.png");
        public static Texture rhubarbStage4Texture = new Texture("Crops/Rhubarb_Stage_4.png");
        public static Texture rhubarbStage5Texture = new Texture("Crops/Rhubarb_Stage_5.png");
        public static Texture rhubarbStage6Texture = new Texture("Crops/Rhubarb_Stage_6.png");
        public static Texture[] rhubarbTextures = new Texture[]{
            rhubarbStage1Texture,
            rhubarbStage2Texture,
            rhubarbStage3Texture,
            rhubarbStage4Texture,
            rhubarbStage5Texture,
            rhubarbStage6Texture,
        };

        //Strawberry:
        public static Texture strawberryTexture = new Texture("Crops/Strawberry.png");
        public static Texture strawberrySeedsTexture = new Texture("Crops/Strawberry_Seeds.png");
        public static Texture strawberryStage1Texture = new Texture("Crops/Strawberry_Stage_1.png");
        public static Texture strawberryStage2Texture = new Texture("Crops/Strawberry_Stage_2.png");
        public static Texture strawberryStage3Texture = new Texture("Crops/Strawberry_Stage_3.png");
        public static Texture strawberryStage4Texture = new Texture("Crops/Strawberry_Stage_4.png");
        public static Texture strawberryStage5Texture = new Texture("Crops/Strawberry_Stage_5.png");
        public static Texture strawberryStage6Texture = new Texture("Crops/Strawberry_Stage_6.png");
        public static Texture strawberryStage7Texture = new Texture("Crops/Strawberry_Stage_7.png");
        public static Texture[] strawberryTextures = new Texture[]{
            strawberryStage1Texture,
            strawberryStage2Texture,
            strawberryStage3Texture,
            strawberryStage4Texture,
            strawberryStage5Texture,
            strawberryStage6Texture,
            strawberryStage7Texture,
        };

        //Tulip:
        public static Texture tulipTexture = new Texture("Crops/Tulip.png");
        public static Texture tulipBulbTexture = new Texture("Crops/Tulip_Bulb.png");
        public static Texture tulipStage1Texture = new Texture("Crops/Tulip_Stage_1.png");
        public static Texture tulipStage2Texture = new Texture("Crops/Tulip_Stage_2.png");
        public static Texture tulipStage3Texture = new Texture("Crops/Tulip_Stage_3.png");
        public static Texture tulipStage4Texture = new Texture("Crops/Tulip_Stage_4.png");
        public static Texture tulipStage5Texture = new Texture("Crops/Tulip_Stage_5.png");
        public static Texture[] tulipTextures = new Texture[]{
            tulipStage1Texture,
            tulipStage2Texture,
            tulipStage3Texture,
            tulipStage4Texture,
            tulipStage5Texture,
        };

        //UnMilledRice:
        public static Texture unMilledRiceTexture = new Texture("Crops/Unmilled_Rice.png");
        public static Texture riceShootTexture = new Texture("Crops/Rice_Shoot.png");
        public static Texture unMilledRiceStage1Texture = new Texture("Crops/Unmilled_Rice_Stage_1.png");
        public static Texture unMilledRiceStage2Texture = new Texture("Crops/Unmilled_Rice_Stage_2.png");
        public static Texture unMilledRiceStage3Texture = new Texture("Crops/Unmilled_Rice_Stage_3.png");
        public static Texture unMilledRiceStage4Texture = new Texture("Crops/Unmilled_Rice_Stage_4.png");
        public static Texture unMilledRiceStage5Texture = new Texture("Crops/Unmilled_Rice_Stage_5.png");
        public static Texture[] unMilledRiceTextures = new Texture[]{
            unMilledRiceStage1Texture,
            unMilledRiceStage2Texture,
            unMilledRiceStage3Texture,
            unMilledRiceStage4Texture,
            unMilledRiceStage5Texture,
        };

        //Blueberry:
        public static Texture blueberryTexture = new Texture("Crops/Blueberry.png");
        public static Texture blueberrySeedsTexture = new Texture("Crops/Blueberry_Seeds.png");
        public static Texture blueberryStage1Texture = new Texture("Crops/Blueberry_Stage_1.png");
        public static Texture blueberryStage2Texture = new Texture("Crops/Blueberry_Stage_2.png");
        public static Texture blueberryStage3Texture = new Texture("Crops/Blueberry_Stage_3.png");
        public static Texture blueberryStage4Texture = new Texture("Crops/Blueberry_Stage_4.png");
        public static Texture blueberryStage5Texture = new Texture("Crops/Blueberry_Stage_5.png");
        public static Texture blueberryStage6Texture = new Texture("Crops/Blueberry_Stage_6.png");
        public static Texture blueberryStage7Texture = new Texture("Crops/Blueberry_Stage_7.png");
        public static Texture[] blueberryTextures = new Texture[]{
            blueberryStage1Texture,
            blueberryStage2Texture,
            blueberryStage3Texture,
            blueberryStage4Texture,
            blueberryStage5Texture,
            blueberryStage6Texture,
            blueberryStage7Texture
        };

        //Corn
        public static Texture cornTexture = new Texture("Crops/Corn.png");
        public static Texture cornSeedsTexture = new Texture("Crops/Corn_Seeds.png");
        public static Texture cornStage1Texture = new Texture("Crops/Corn_Stage_1.png");
        public static Texture cornStage2Texture = new Texture("Crops/Corn_Stage_2.png");
        public static Texture cornStage3Texture = new Texture("Crops/Corn_Stage_3.png");
        public static Texture cornStage4Texture = new Texture("Crops/Corn_Stage_4.png");
        public static Texture cornStage5Texture = new Texture("Crops/Corn_Stage_5.png");
        public static Texture cornStage6Texture = new Texture("Crops/Corn_Stage_6.png");
        public static Texture cornStage7Texture = new Texture("Crops/Corn_Stage_7.png");
        public static Texture[] cornTextures = new Texture[]{
            cornStage1Texture,
            cornStage2Texture,
            cornStage3Texture,
            cornStage4Texture,
            cornStage5Texture,
            cornStage6Texture,
            cornStage7Texture
        };

        //Hops:
        public static Texture hopsTexture = new Texture("Crops/Hops.png");
        public static Texture hopsStarterTexture = new Texture("Crops/Hops_Starter.png");
        public static Texture hopsStage1Texture = new Texture("Crops/Hops_Stage_1.png");
        public static Texture hopsStage2Texture = new Texture("Crops/Hops_Stage_2.png");
        public static Texture hopsStage3Texture = new Texture("Crops/Hops_Stage_3.png");
        public static Texture hopsStage4Texture = new Texture("Crops/Hops_Stage_4.png");
        public static Texture hopsStage5Texture = new Texture("Crops/Hops_Stage_5.png");
        public static Texture hopsStage6Texture = new Texture("Crops/Hops_Stage_6.png");
        public static Texture hopsStage7Texture = new Texture("Crops/Hops_Stage_7.png");
        public static Texture[] hopsTextures = new Texture[]{
            hopsStage1Texture,
            hopsStage2Texture,
            hopsStage3Texture,
            hopsStage4Texture,
            hopsStage5Texture,
            hopsStage6Texture,
            hopsStage7Texture
        };

        //HotPepper:
        public static Texture hotPepperTexture = new Texture("Crops/Hot_Pepper.png");
        public static Texture pepperSeedsTexture = new Texture("Crops/Pepper_Seeds.png");
        public static Texture hotPepperStage1Texture = new Texture("Crops/Hot_Pepper_Stage_1.png");
        public static Texture hotPepperStage2Texture = new Texture("Crops/Hot_Pepper_Stage_2.png");
        public static Texture hotPepperStage3Texture = new Texture("Crops/Hot_Pepper_Stage_3.png");
        public static Texture hotPepperStage4Texture = new Texture("Crops/Hot_Pepper_Stage_4.png");
        public static Texture hotPepperStage5Texture = new Texture("Crops/Hot_Pepper_Stage_5.png");
        public static Texture hotPepperStage6Texture = new Texture("Crops/Hot_Pepper_Stage_6.png");
        public static Texture hotPepperStage7Texture = new Texture("Crops/Hot_Pepper_Stage_7.png");
        public static Texture[] hotPepperTextures = new Texture[]{
            hotPepperStage1Texture,
            hotPepperStage2Texture,
            hotPepperStage3Texture,
            hotPepperStage4Texture,
            hotPepperStage5Texture,
            hotPepperStage6Texture,
            hotPepperStage7Texture
        };

        //Melon:
        public static Texture melonTexture = new Texture("Crops/Melon.png");
        public static Texture melonSeedsTexture = new Texture("Crops/Melon_Seeds.png");
        public static Texture melonStage1Texture = new Texture("Crops/Melon_Stage_1.png");
        public static Texture melonStage2Texture = new Texture("Crops/Melon_Stage_2.png");
        public static Texture melonStage3Texture = new Texture("Crops/Melon_Stage_3.png");
        public static Texture melonStage4Texture = new Texture("Crops/Melon_Stage_4.png");
        public static Texture melonStage5Texture = new Texture("Crops/Melon_Stage_5.png");
        public static Texture melonStage6Texture = new Texture("Crops/Melon_Stage_6.png");
        public static Texture[] melonTextures = new Texture[]{
            melonStage1Texture,
            melonStage2Texture,
            melonStage3Texture,
            melonStage4Texture,
            melonStage5Texture,
            melonStage6Texture,
        };

        //Poppy:
        public static Texture poppyTexture = new Texture("Crops/Poppy.png");
        public static Texture poppySeedsTexture = new Texture("Crops/Poppy_Seeds.png");
        public static Texture poppyStage1Texture = new Texture("Crops/Poppy_Stage_1.png");
        public static Texture poppyStage2Texture = new Texture("Crops/Poppy_Stage_2.png");
        public static Texture poppyStage3Texture = new Texture("Crops/Poppy_Stage_3.png");
        public static Texture poppyStage4Texture = new Texture("Crops/Poppy_Stage_4.png");
        public static Texture poppyStage5Texture = new Texture("Crops/Poppy_Stage_5.png");
        public static Texture[] poppyTextures = new Texture[]{
            poppyStage1Texture,
            poppyStage2Texture,
            poppyStage3Texture,
            poppyStage4Texture,
            poppyStage5Texture,
        };

        //Radish:
        public static Texture radishTexture = new Texture("Crops/Radish.png");
        public static Texture radishSeedsTexture = new Texture("Crops/Radish_Seeds.png");
        public static Texture radishStage1Texture = new Texture("Crops/Radish_Stage_1.png");
        public static Texture radishStage2Texture = new Texture("Crops/Radish_Stage_2.png");
        public static Texture radishStage3Texture = new Texture("Crops/Radish_Stage_3.png");
        public static Texture radishStage4Texture = new Texture("Crops/Radish_Stage_4.png");
        public static Texture radishStage5Texture = new Texture("Crops/Radish_Stage_5.png");
        public static Texture[] radishTextures = new Texture[]{
            radishStage1Texture,
            radishStage2Texture,
            radishStage3Texture,
            radishStage4Texture,
            radishStage5Texture,
        };

        //RedCabbage:
        public static Texture redCabbageTexture = new Texture("Crops/Red_Cabbage.png");
        public static Texture redCabbageSeedsTexture = new Texture("Crops/Red_Cabbage_Seeds.png");
        public static Texture redCabbageStage1Texture = new Texture("Crops/Red_Cabbage_Stage_1.png");
        public static Texture redCabbageStage2Texture = new Texture("Crops/Red_Cabbage_Stage_2.png");
        public static Texture redCabbageStage3Texture = new Texture("Crops/Red_Cabbage_Stage_3.png");
        public static Texture redCabbageStage4Texture = new Texture("Crops/Red_Cabbage_Stage_4.png");
        public static Texture redCabbageStage5Texture = new Texture("Crops/Red_Cabbage_Stage_5.png");
        public static Texture redCabbageStage6Texture = new Texture("Crops/Red_Cabbage_Stage_6.png");
        public static Texture[] redCabbageTextures = new Texture[]{
            redCabbageStage1Texture,
            redCabbageStage2Texture,
            redCabbageStage3Texture,
            redCabbageStage4Texture,
            redCabbageStage5Texture,
            redCabbageStage6Texture,
        };

        //Starfruit:
        public static Texture starfruitTexture = new Texture("Crops/Starfruit.png");
        public static Texture starfruitSeedsTexture = new Texture("Crops/Starfruit_Seeds.png");
        public static Texture starfruitStage1Texture = new Texture("Crops/Starfruit_Stage_1.png");
        public static Texture starfruitStage2Texture = new Texture("Crops/Starfruit_Stage_2.png");
        public static Texture starfruitStage3Texture = new Texture("Crops/Starfruit_Stage_3.png");
        public static Texture starfruitStage4Texture = new Texture("Crops/Starfruit_Stage_4.png");
        public static Texture starfruitStage5Texture = new Texture("Crops/Starfruit_Stage_5.png");
        public static Texture starfruitStage6Texture = new Texture("Crops/Starfruit_Stage_6.png");
        public static Texture[] starfruitTextures = new Texture[]{
            starfruitStage1Texture,
            starfruitStage2Texture,
            starfruitStage3Texture,
            starfruitStage4Texture,
            starfruitStage5Texture,
            starfruitStage6Texture,
        };

        //SummerSpangle:
        public static Texture summerSpangleTexture = new Texture("Crops/Summer_Spangle.png");
        public static Texture spangleSeedsTexture = new Texture("Crops/Spangle_Seeds.png");
        public static Texture summerSpangleStage1Texture = new Texture("Crops/Summer_Spangle_Stage_1.png");
        public static Texture summerSpangleStage2Texture = new Texture("Crops/Summer_Spangle_Stage_2.png");
        public static Texture summerSpangleStage3Texture = new Texture("Crops/Summer_Spangle_Stage_3.png");
        public static Texture summerSpangleStage4Texture = new Texture("Crops/Summer_Spangle_Stage_4.png");
        public static Texture summerSpangleStage5Texture = new Texture("Crops/Summer_Spangle_Stage_5.png");
        public static Texture[] summerSpangleTextures = new Texture[]{
            summerSpangleStage1Texture,
            summerSpangleStage2Texture,
            summerSpangleStage3Texture,
            summerSpangleStage4Texture,
            summerSpangleStage5Texture,
        };

        //SummerSquash:
        public static Texture summerSquashTexture = new Texture("Crops/Summer_Squash.png");
        public static Texture summerSquashSeedsTexture = new Texture("Crops/Summer_Squash_Seeds.png");
        public static Texture summerSquashStage1Texture = new Texture("Crops/Summer_Squash_Stage_1.png");
        public static Texture summerSquashStage2Texture = new Texture("Crops/Summer_Squash_Stage_2.png");
        public static Texture summerSquashStage3Texture = new Texture("Crops/Summer_Squash_Stage_3.png");
        public static Texture summerSquashStage4Texture = new Texture("Crops/Summer_Squash_Stage_4.png");
        public static Texture summerSquashStage5Texture = new Texture("Crops/Summer_Squash_Stage_5.png");
        public static Texture summerSquashStage6Texture = new Texture("Crops/Summer_Squash_Stage_6.png");
        public static Texture summerSquashStage7Texture = new Texture("Crops/Summer_Squash_Stage_7.png");
        public static Texture[] summerSquashTextures = new Texture[]{
            summerSquashStage1Texture,
            summerSquashStage2Texture,
            summerSquashStage3Texture,
            summerSquashStage4Texture,
            summerSquashStage5Texture,
            summerSquashStage6Texture,
            summerSquashStage7Texture
        };

        //sunflower:
        public static Texture sunflowerTexture = new Texture("Crops/Sunflower.png");
        public static Texture sunflowerSeedsTexture = new Texture("Crops/Sunflower_Seeds.png");
        public static Texture sunflowerStage1Texture = new Texture("Crops/Sunflower_Stage_1.png");
        public static Texture sunflowerStage2Texture = new Texture("Crops/Sunflower_Stage_2.png");
        public static Texture sunflowerStage3Texture = new Texture("Crops/Sunflower_Stage_3.png");
        public static Texture sunflowerStage4Texture = new Texture("Crops/Sunflower_Stage_4.png");
        public static Texture sunflowerStage5Texture = new Texture("Crops/Sunflower_Stage_5.png");
        public static Texture[] sunflowerTextures = new Texture[]{
            sunflowerStage1Texture,
            sunflowerStage2Texture,
            sunflowerStage3Texture,
            sunflowerStage4Texture,
            sunflowerStage5Texture,
        };

        //tomato:
        public static Texture tomatoTexture = new Texture("Crops/Tomato.png");
        public static Texture tomatoSeedsTexture = new Texture("Crops/Tomato_Seeds.png");
        public static Texture tomatoStage1Texture = new Texture("Crops/Tomato_Stage_1.png");
        public static Texture tomatoStage2Texture = new Texture("Crops/Tomato_Stage_2.png");
        public static Texture tomatoStage3Texture = new Texture("Crops/Tomato_Stage_3.png");
        public static Texture tomatoStage4Texture = new Texture("Crops/Tomato_Stage_4.png");
        public static Texture tomatoStage5Texture = new Texture("Crops/Tomato_Stage_5.png");
        public static Texture tomatoStage6Texture = new Texture("Crops/Tomato_Stage_6.png");
        public static Texture tomatoStage7Texture = new Texture("Crops/Tomato_Stage_7.png");
        public static Texture[] tomatoTextures = new Texture[]{
            tomatoStage1Texture,
            tomatoStage2Texture,
            tomatoStage3Texture,
            tomatoStage4Texture,
            tomatoStage5Texture,
            tomatoStage6Texture,
            tomatoStage7Texture,
        };

        //wheat:
        public static Texture wheatTexture = new Texture("Crops/Wheat.png");
        public static Texture wheatSeedsTexture = new Texture("Crops/Wheat_Seeds.png");
        public static Texture wheatStage1Texture = new Texture("Crops/Wheat_Stage_1.png");
        public static Texture wheatStage2Texture = new Texture("Crops/Wheat_Stage_2.png");
        public static Texture wheatStage3Texture = new Texture("Crops/Wheat_Stage_3.png");
        public static Texture wheatStage4Texture = new Texture("Crops/Wheat_Stage_4.png");
        public static Texture wheatStage5Texture = new Texture("Crops/Wheat_Stage_5.png");
        public static Texture[] wheatTextures = new Texture[]{
            wheatStage1Texture,
            wheatStage2Texture,
            wheatStage3Texture,
            wheatStage4Texture,
            wheatStage5Texture,
        };

        //amaranth:
        public static Texture amaranthTexture = new Texture("Crops/Amaranth.png");
        public static Texture amaranthSeedsTexture = new Texture("Crops/Amaranth_Seeds.png");
        public static Texture amaranthStage1Texture = new Texture("Crops/Amaranth_Stage_1.png");
        public static Texture amaranthStage2Texture = new Texture("Crops/Amaranth_Stage_2.png");
        public static Texture amaranthStage3Texture = new Texture("Crops/Amaranth_Stage_3.png");
        public static Texture amaranthStage4Texture = new Texture("Crops/Amaranth_Stage_4.png");
        public static Texture amaranthStage5Texture = new Texture("Crops/Amaranth_Stage_5.png");
        public static Texture[] amaranthTextures = new Texture[]{
            amaranthStage1Texture,
            amaranthStage2Texture,
            amaranthStage3Texture,
            amaranthStage4Texture,
            amaranthStage5Texture,
        };

        //artichoke:
        public static Texture artichokeTexture = new Texture("Crops/Artichoke.png");
        public static Texture artichokeSeedsTexture = new Texture("Crops/Artichoke_Seeds.png");
        public static Texture artichokeStage1Texture = new Texture("Crops/Artichoke_Stage_1.png");
        public static Texture artichokeStage2Texture = new Texture("Crops/Artichoke_Stage_2.png");
        public static Texture artichokeStage3Texture = new Texture("Crops/Artichoke_Stage_3.png");
        public static Texture artichokeStage4Texture = new Texture("Crops/Artichoke_Stage_4.png");
        public static Texture artichokeStage5Texture = new Texture("Crops/Artichoke_Stage_5.png");
        public static Texture artichokeStage6Texture = new Texture("Crops/Artichoke_Stage_6.png");
        public static Texture[] artichokeTextures = new Texture[]{
            artichokeStage1Texture,
            artichokeStage2Texture,
            artichokeStage3Texture,
            artichokeStage4Texture,
            artichokeStage5Texture,
            artichokeStage6Texture,
        };

        //beet:
        public static Texture beetTexture = new Texture("Crops/Beet.png");
        public static Texture beetSeedsTexture = new Texture("Crops/Beet_Seeds.png");
        public static Texture beetStage1Texture = new Texture("Crops/Beet_Stage_1.png");
        public static Texture beetStage2Texture = new Texture("Crops/Beet_Stage_2.png");
        public static Texture beetStage3Texture = new Texture("Crops/Beet_Stage_3.png");
        public static Texture beetStage4Texture = new Texture("Crops/Beet_Stage_4.png");
        public static Texture beetStage5Texture = new Texture("Crops/Beet_Stage_5.png");
        public static Texture[] beetTextures = new Texture[]{
            beetStage1Texture,
            beetStage2Texture,
            beetStage3Texture,
            beetStage4Texture,
            beetStage5Texture,
        };

        //bokChoy:
        public static Texture bokChoyTexture = new Texture("Crops/Bok_Choy.png");
        public static Texture bokChoySeedsTexture = new Texture("Crops/Bok_Choy_Seeds.png");
        public static Texture bokChoyStage1Texture = new Texture("Crops/Bok_Choy_Stage_1.png");
        public static Texture bokChoyStage2Texture = new Texture("Crops/Bok_Choy_Stage_2.png");
        public static Texture bokChoyStage3Texture = new Texture("Crops/Bok_Choy_Stage_3.png");
        public static Texture bokChoyStage4Texture = new Texture("Crops/Bok_Choy_Stage_4.png");
        public static Texture bokChoyStage5Texture = new Texture("Crops/Bok_Choy_Stage_5.png");
        public static Texture[] bokChoyTextures = new Texture[]{
            bokChoyStage1Texture,
            bokChoyStage2Texture,
            bokChoyStage3Texture,
            bokChoyStage4Texture,
            bokChoyStage5Texture,
        };

        //broccoli:
        public static Texture broccoliTexture = new Texture("Crops/Broccoli.png");
        public static Texture broccoliSeedsTexture = new Texture("Crops/Broccoli_Seeds.png");
        public static Texture broccoliStage1Texture = new Texture("Crops/Broccoli_Stage_1.png");
        public static Texture broccoliStage2Texture = new Texture("Crops/Broccoli_Stage_2.png");
        public static Texture broccoliStage3Texture = new Texture("Crops/Broccoli_Stage_3.png");
        public static Texture broccoliStage4Texture = new Texture("Crops/Broccoli_Stage_4.png");
        public static Texture broccoliStage5Texture = new Texture("Crops/Broccoli_Stage_5.png");
        public static Texture broccoliStage6Texture = new Texture("Crops/Broccoli_Stage_4.png");
        public static Texture[] broccoliTextures = new Texture[]{
            broccoliStage1Texture,
            broccoliStage2Texture,
            broccoliStage3Texture,
            broccoliStage4Texture,
            broccoliStage5Texture,
            broccoliStage6Texture,
        };

        //cranberries:
        public static Texture cranberriesTexture = new Texture("Crops/Cranberries.png");
        public static Texture cranberrySeedsTexture = new Texture("Crops/Cranberry_Seeds.png");
        public static Texture cranberriesStage1Texture = new Texture("Crops/Cranberry_Stage_1.png");
        public static Texture cranberriesStage2Texture = new Texture("Crops/Cranberry_Stage_2.png");
        public static Texture cranberriesStage3Texture = new Texture("Crops/Cranberry_Stage_3.png");
        public static Texture cranberriesStage4Texture = new Texture("Crops/Cranberry_Stage_4.png");
        public static Texture cranberriesStage5Texture = new Texture("Crops/Cranberry_Stage_5.png");
        public static Texture cranberriesStage6Texture = new Texture("Crops/Cranberry_Stage_6.png");
        public static Texture cranberriesStage7Texture = new Texture("Crops/Cranberry_Stage_7.png");
        public static Texture[] cranberriesTextures = new Texture[]{
            cranberriesStage1Texture,
            cranberriesStage2Texture,
            cranberriesStage3Texture,
            cranberriesStage4Texture,
            cranberriesStage5Texture,
            cranberriesStage6Texture,
            cranberriesStage7Texture,
        };

        //eggplant:
        public static Texture eggplantTexture = new Texture("Crops/Eggplant.png");
        public static Texture eggplantSeedsTexture = new Texture("Crops/Eggplant_Seeds.png");
        public static Texture eggplantStage1Texture = new Texture("Crops/Eggplant_Stage_1.png");
        public static Texture eggplantStage2Texture = new Texture("Crops/Eggplant_Stage_2.png");
        public static Texture eggplantStage3Texture = new Texture("Crops/Eggplant_Stage_3.png");
        public static Texture eggplantStage4Texture = new Texture("Crops/Eggplant_Stage_4.png");
        public static Texture eggplantStage5Texture = new Texture("Crops/Eggplant_Stage_5.png");
        public static Texture eggplantStage6Texture = new Texture("Crops/Eggplant_Stage_6.png");
        public static Texture[] eggplantTextures = new Texture[]{
            eggplantStage1Texture,
            eggplantStage2Texture,
            eggplantStage3Texture,
            eggplantStage4Texture,
            eggplantStage5Texture,
            eggplantStage6Texture,
        };

        //fairyRose:
        public static Texture fairyRoseTexture = new Texture("Crops/Fairy_Rose.png");
        public static Texture fairySeedsTexture = new Texture("Crops/Fairy_Seeds.png");
        public static Texture fairyRoseStage1Texture = new Texture("Crops/Fairy_Rose_Stage_1.png");
        public static Texture fairyRoseStage2Texture = new Texture("Crops/Fairy_Rose_Stage_2.png");
        public static Texture fairyRoseStage3Texture = new Texture("Crops/Fairy_Rose_Stage_3.png");
        public static Texture fairyRoseStage4Texture = new Texture("Crops/Fairy_Rose_Stage_4.png");
        public static Texture fairyRoseStage5Texture = new Texture("Crops/Fairy_Rose_Stage_5.png");
        public static Texture[] fairyRoseTextures = new Texture[]{
            fairyRoseStage1Texture,
            fairyRoseStage2Texture,
            fairyRoseStage3Texture,
            fairyRoseStage4Texture,
            fairyRoseStage5Texture,
        };

        //grape:
        public static Texture grapeTexture = new Texture("Crops/Grape.png");
        public static Texture grapeStarterTexture = new Texture("Crops/Grape_Starter.png");
        public static Texture grapeStage1Texture = new Texture("Crops/Grape_Stage_1.png");
        public static Texture grapeStage2Texture = new Texture("Crops/Grape_Stage_2.png");
        public static Texture grapeStage3Texture = new Texture("Crops/Grape_Stage_3.png");
        public static Texture grapeStage4Texture = new Texture("Crops/Grape_Stage_4.png");
        public static Texture grapeStage5Texture = new Texture("Crops/Grape_Stage_5.png");
        public static Texture grapeStage6Texture = new Texture("Crops/Grape_Stage_6.png");
        public static Texture grapeStage7Texture = new Texture("Crops/Grape_Stage_7.png");
        public static Texture[] grapeTextures = new Texture[]{
            grapeStage1Texture,
            grapeStage2Texture,
            grapeStage3Texture,
            grapeStage4Texture,
            grapeStage5Texture,
            grapeStage6Texture,
            grapeStage7Texture,
        };

        //pumpkin:
        public static Texture pumpkinTexture = new Texture("Crops/Pumpkin.png");
        public static Texture pumpkinSeedsTexture = new Texture("Crops/Pumpkin_Seeds.png");
        public static Texture pumpkinStage1Texture = new Texture("Crops/Pumpkin_Stage_1.png");
        public static Texture pumpkinStage2Texture = new Texture("Crops/Pumpkin_Stage_2.png");
        public static Texture pumpkinStage3Texture = new Texture("Crops/Pumpkin_Stage_3.png");
        public static Texture pumpkinStage4Texture = new Texture("Crops/Pumpkin_Stage_4.png");
        public static Texture pumpkinStage5Texture = new Texture("Crops/Pumpkin_Stage_5.png");
        public static Texture pumpkinStage6Texture = new Texture("Crops/Pumpkin_Stage_6.png");
        public static Texture[] pumpkinTextures = new Texture[]{
            pumpkinStage1Texture,
            pumpkinStage2Texture,
            pumpkinStage3Texture,
            pumpkinStage4Texture,
            pumpkinStage5Texture,
            pumpkinStage6Texture,
        };

        //yam:
        public static Texture yamTexture = new Texture("Crops/Yam.png");
        public static Texture yamSeedsTexture = new Texture("Crops/Yam_Seeds.png");
        public static Texture yamStage1Texture = new Texture("Crops/Yam_Stage_1.png");
        public static Texture yamStage2Texture = new Texture("Crops/Yam_Stage_2.png");
        public static Texture yamStage3Texture = new Texture("Crops/Yam_Stage_3.png");
        public static Texture yamStage4Texture = new Texture("Crops/Yam_Stage_4.png");
        public static Texture yamStage5Texture = new Texture("Crops/Yam_Stage_5.png");
        public static Texture[] yamTextures = new Texture[]{
            yamStage1Texture,
            yamStage2Texture,
            yamStage3Texture,
            yamStage4Texture,
            yamStage5Texture,
        };

        //sweetGemBerry:
        public static Texture sweetGemBerryTexture = new Texture("Crops/Sweet_Gem_Berry.png");
        public static Texture rareSeedTexture = new Texture("Crops/Rare_Seed.png");
        public static Texture sweetGemBerryStage1Texture = new Texture("Crops/Sweet_Gem_Berry_Stage_1.png");
        public static Texture sweetGemBerryStage2Texture = new Texture("Crops/Sweet_Gem_Berry_Stage_2.png");
        public static Texture sweetGemBerryStage3Texture = new Texture("Crops/Sweet_Gem_Berry_Stage_3.png");
        public static Texture sweetGemBerryStage4Texture = new Texture("Crops/Sweet_Gem_Berry_Stage_4.png");
        public static Texture sweetGemBerryStage5Texture = new Texture("Crops/Sweet_Gem_Berry_Stage_5.png");
        public static Texture sweetGemBerryStage6Texture = new Texture("Crops/Sweet_Gem_Berry_Stage_6.png");
        public static Texture[] sweetGemBerryTextures = new Texture[]{
            sweetGemBerryStage1Texture,
            sweetGemBerryStage2Texture,
            sweetGemBerryStage3Texture,
            sweetGemBerryStage4Texture,
            sweetGemBerryStage5Texture,
            sweetGemBerryStage6Texture,
        };

        //powdermelon:
        public static Texture powdermelonTexture = new Texture("Crops/Powdermelon.png");
        public static Texture powdermelonSeedsTexture = new Texture("Crops/Powdermelon_Seeds.png");
        public static Texture powdermelonStage1Texture = new Texture("Crops/Powdermelon_Stage_1.png");
        public static Texture powdermelonStage2Texture = new Texture("Crops/Powdermelon_Stage_2.png");
        public static Texture powdermelonStage3Texture = new Texture("Crops/Powdermelon_Stage_3.png");
        public static Texture powdermelonStage4Texture = new Texture("Crops/Powdermelon_Stage_4.png");
        public static Texture powdermelonStage5Texture = new Texture("Crops/Powdermelon_Stage_5.png");
        public static Texture powdermelonStage6Texture = new Texture("Crops/Powdermelon_Stage_6.png");
        public static Texture[] powdermelonTextures = new Texture[]{
            powdermelonStage1Texture,
            powdermelonStage2Texture,
            powdermelonStage3Texture,
            powdermelonStage4Texture,
            powdermelonStage5Texture,
            powdermelonStage6Texture,
        };

        //ancientFruit:
        public static Texture ancientFruitTexture = new Texture("Crops/Ancient_Fruit.png");
        public static Texture ancientSeedsTexture = new Texture("Crops/Ancient_Seeds.png");
        public static Texture ancientFruitStage1Texture = new Texture("Crops/Ancient_Fruit_Stage_1.png");
        public static Texture ancientFruitStage2Texture = new Texture("Crops/Ancient_Fruit_Stage_2.png");
        public static Texture ancientFruitStage3Texture = new Texture("Crops/Ancient_Fruit_Stage_3.png");
        public static Texture ancientFruitStage4Texture = new Texture("Crops/Ancient_Fruit_Stage_4.png");
        public static Texture ancientFruitStage5Texture = new Texture("Crops/Ancient_Fruit_Stage_5.png");
        public static Texture ancientFruitStage6Texture = new Texture("Crops/Ancient_Fruit_Stage_6.png");
        public static Texture ancientFruitStage7Texture = new Texture("Crops/Ancient_Fruit_Stage_7.png");
        public static Texture[] ancientFruitTextures = new Texture[]{
            ancientFruitStage1Texture,
            ancientFruitStage2Texture,
            ancientFruitStage3Texture,
            ancientFruitStage4Texture,
            ancientFruitStage5Texture,
            ancientFruitStage6Texture,
            ancientFruitStage7Texture,
        };

        //MixedSeeds:
        public static Texture mixedSeedsTexture = new Texture("Crops/Mixed_Seeds.png");


    // Animals :

        //Chicken :
        public static TextureRegion chickenTexture = new TextureRegion(new Texture("Animals/Brown_Chicken.png"));
        public static TextureRegion eggTexture = new TextureRegion(new Texture("Animal_product/Brown_Egg.png"));
        public static TextureRegion largeEggTexture = new TextureRegion(new Texture("Animal_product/Large_Brown_Egg.png"));

        //Duck :
        public static TextureRegion duckTexture = new TextureRegion(new Texture("Animals/Duck.png"));
        public static TextureRegion duckEggTexture = new TextureRegion(new Texture("Animal_product/Duck_Egg.png"));
        public static TextureRegion duckFeatherTexture = new TextureRegion(new Texture("Animal_product/Duck_Feather.png"));

        //Rabbit :
        public static TextureRegion rabbitTexture = new TextureRegion(new Texture("Animals/Rabbit.png"));
        public static TextureRegion rabbitFootTexture = new TextureRegion(new Texture("Animal_product/Rabbit%27s_Foot.png"));
        public static TextureRegion woolTexture = new TextureRegion(new Texture("Animal_product/Wool.png"));

        //Dinosaur :
        public static TextureRegion dinosaurTexture = new TextureRegion(new Texture("Animals/Dinosaur.png"));
        public static TextureRegion dinosaurEggTexture = new TextureRegion(new Texture("Animal_product/Dinosaur_Egg.png"));

        //Cow :
        public static TextureRegion cowTexture = new TextureRegion(new Texture("Animals/White_Cow.png"));
        public static TextureRegion milkTexture = new TextureRegion(new Texture("Animal_product/Milk.png"));
        public static TextureRegion largeMilkTexture = new TextureRegion(new Texture("Animal_product/Large_Milk.png"));

        //Goat :
        public static TextureRegion goatTexture = new TextureRegion(new Texture("Animals/Goat.png"));
        public static TextureRegion goatMilkTexture = new TextureRegion(new Texture("Animal_product/Goat_Milk.png"));
        public static TextureRegion largeGoatMilkTexture = new TextureRegion(new Texture("Animal_product/Large_Goat_Milk.png"));

        //Sheep :
        public static TextureRegion sheepTexture = new TextureRegion(new Texture("Animals/Sheep.png"));

        //Pig :
        public static TextureRegion pigTexture = new TextureRegion(new Texture("Animals/Pig.png"));
        public static TextureRegion truffleTexture = new TextureRegion(new Texture("Animal_product/Truffle.png"));


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

}
