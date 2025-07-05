package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class GamePictureManager {
    public static Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.skin"));
    public static int TILE_SIZE = 120;

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
        //Apple:

        public static Texture appleFruitTexture = new Texture("Trees/Apple.png");
        public static Texture appleSaplingTexture = new Texture("Trees/Apple_Sapling.png");
        public static Texture appleStage1Texture = new Texture("Trees/Apple_Stage_1.png");
        public static Texture appleStage2Texture = new Texture("Trees/Apple_Stage_2.png");
        public static Texture appleStage3Texture = new Texture("Trees/Apple_Stage_3.png");
        public static Texture appleStage4Texture = new Texture("Trees/Apple_Stage_4.png");
        public static Texture appleStage5Texture = new Texture("Trees/Apple_Stage_5.png");
        public static Texture appleStage5WithFruit = new Texture("Trees/Apple_Stage_5_Fruit.png");

        public static ArrayList<Texture> appleStageTextures = new ArrayList<>();
        static {
            appleStageTextures.add(appleStage1Texture);
            appleStageTextures.add(appleStage2Texture);
            appleStageTextures.add(appleStage3Texture);
            appleStageTextures.add(appleStage4Texture);
            appleStageTextures.add(appleStage5Texture);
        }

        //Apricot :

        public static Texture apricotTexture = new Texture("Trees/Apricot.png");
        public static Texture apricotSaplingTexture = new Texture("Trees/Apricot_Sapling.png");
        public static Texture apricotStage1Texture = new Texture("Trees/Apricot_Stage_1.png");
        public static Texture apricotStage2Texture = new Texture("Trees/Apricot_Stage_2.png");
        public static Texture apricotStage3Texture = new Texture("Trees/Apricot_Stage_3.png");
        public static Texture apricotStage4Texture = new Texture("Trees/Apricot_Stage_4.png");
        public static Texture apricotStage5Texture = new Texture("Trees/Apricot_Stage_5.png");
        public static Texture apricotStage5WithFruit = new Texture("Trees/Apricot_Stage_5_Fruit.png");

        public static ArrayList<Texture> apricotStageTextures = new ArrayList<>();
        static {
            apricotStageTextures.add(apricotStage1Texture);
            apricotStageTextures.add(apricotStage2Texture);
            apricotStageTextures.add(apricotStage3Texture);
            apricotStageTextures.add(apricotStage4Texture);
            apricotStageTextures.add(apricotStage5Texture);
        }

        //Banana :

        public static Texture bananaTexture = new Texture("Trees/Banana.png");
        public static Texture bananaSaplingTexture = new Texture("Trees/Banana_Sapling.png");
        public static Texture bananaStage1Texture = new Texture("Trees/Banana_Stage_1.png");
        public static Texture bananaStage2Texture = new Texture("Trees/Banana_Stage_2.png");
        public static Texture bananaStage3Texture = new Texture("Trees/Banana_Stage_3.png");
        public static Texture bananaStage4Texture = new Texture("Trees/Banana_Stage_4.png");
        public static Texture bananaStage5Texture = new Texture("Trees/Banana_Stage_5.png");
        public static Texture bananaStage5WithFruit = new Texture("Trees/Banana_Stage_5_Fruit.png");

        public static ArrayList<Texture> bananaStageTextures = new ArrayList<>();
        static {
            bananaStageTextures.add(bananaStage1Texture);
            bananaStageTextures.add(bananaStage2Texture);
            bananaStageTextures.add(bananaStage3Texture);
            bananaStageTextures.add(bananaStage4Texture);
            bananaStageTextures.add(bananaStage5Texture);
        }


        //Cherry :

        public static Texture cherryTexture = new Texture("Trees/Cherry.png");
        public static Texture cherrySaplingTexture = new Texture("Trees/Cherry_Sapling.png");
        public static Texture cherryStage1Texture = new Texture("Trees/Cherry_Stage_1.png");






        // Mango
        public static Texture mangoTexture = new Texture("Trees/Mango.png");
        public static Texture mangoSaplingTexture = new Texture("Trees/Mango_Sapling.png");
        public static Texture mangoStage1Texture = new Texture("Trees/Mango_Stage_1.png");
        public static Texture mangoStage2Texture = new Texture("Trees/Mango_Stage_2.png");
        public static Texture mangoStage3Texture = new Texture("Trees/Mango_Stage_3.png");
        public static Texture mangoStage4Texture = new Texture("Trees/Mango_Stage_4.png");
        public static Texture mangoStage5Texture = new Texture("Trees/Mango_Stage_5.png");
        public static Texture mangoStage5WithFruit = new Texture("Trees/Mango_Stage_5_Fruit.png");

        public static ArrayList<Texture> mangoStageTextures = new ArrayList<>();
        static {
            mangoStageTextures.add(mangoStage1Texture);
            mangoStageTextures.add(mangoStage2Texture);
            mangoStageTextures.add(mangoStage3Texture);
            mangoStageTextures.add(mangoStage4Texture);
            mangoStageTextures.add(mangoStage5Texture);
        }

        // Maple

        public static Texture mapleSaplingTexture = new Texture("Trees/Maple_Seed.png");
        public static Texture mapleStage1Texture = new Texture("Trees/Maple_Stage_1.png");
        public static Texture mapleStage2Texture = new Texture("Trees/Maple_Stage_2.png");
        public static Texture mapleStage3Texture = new Texture("Trees/Maple_Stage_3.png");
        public static Texture mapleStage4Texture = new Texture("Trees/Maple_Stage_4.png");
        public static Texture mapleStage5Texture = new Texture("Trees/Maple_Stage_5.png");

        public static ArrayList<Texture> mapleStageTextures = new ArrayList<>();
        static {
            mapleStageTextures.add(mapleStage1Texture);
            mapleStageTextures.add(mapleStage2Texture);
            mapleStageTextures.add(mapleStage3Texture);
            mapleStageTextures.add(mapleStage4Texture);
            mapleStageTextures.add(mapleStage5Texture);
        }


        // MushroomTree
        public static Texture mushroomTreeTexture = new Texture("Trees/Mushroom_stump.png");
        public static Texture mushroomTreeSaplingTexture = new Texture("Trees/Mushroom_Tree_Seed.png");
        public static Texture mushroomTreeStage1Texture = new Texture("Trees/MushroomTree_Stage_1.png");
        public static Texture mushroomTreeStage2Texture = new Texture("Trees/MushroomTree_Stage_2.png");
        public static Texture mushroomTreeStage3Texture = new Texture("Trees/MushroomTree_Stage_3.png");
        public static Texture mushroomTreeStage4Texture = new Texture("Trees/MushroomTree_Stage_4.png");
        public static Texture mushroomTreeStage5Texture = new Texture("Trees/MushroomTree_Stage_5.png");

        public static ArrayList<Texture> mushroomTreeTextures = new ArrayList<>();
        static {
            mushroomTreeTextures.add(mushroomTreeSaplingTexture);
            mushroomTreeTextures.add(mushroomTreeStage1Texture);
            mushroomTreeTextures.add(mushroomTreeStage2Texture);
            mushroomTreeTextures.add(mushroomTreeStage3Texture);
            mushroomTreeTextures.add(mushroomTreeStage4Texture);
            mushroomTreeTextures.add(mushroomTreeStage5Texture);
        }


        // Mystic_Tree

        public static Texture mysticTreeSaplingTexture = new Texture("Trees/Mystic_Tree_Seed.png");
        public static Texture mysticTreeStage1Texture = new Texture("Trees/Mystic_Tree_Stage_1.png");
        public static Texture mysticTreeStage2Texture = new Texture("Trees/Mystic_Tree_Stage_2.png");
        public static Texture mysticTreeStage3Texture = new Texture("Trees/Mystic_Tree_Stage_3.png");
        public static Texture mysticTreeStage4Texture = new Texture("Trees/Mystic_Tree_Stage_4.png");
        public static Texture mysticTreeStage5Texture = new Texture("Trees/Mystic_Tree_Stage_5.png");

        public static ArrayList<Texture> mysticTreeTextures = new ArrayList<>();
        static {
            mysticTreeTextures.add(mysticTreeSaplingTexture);
            mysticTreeTextures.add(mysticTreeStage1Texture);
            mysticTreeTextures.add(mysticTreeStage2Texture);
            mysticTreeTextures.add(mysticTreeStage3Texture);
            mysticTreeTextures.add(mysticTreeStage4Texture);
            mysticTreeTextures.add(mysticTreeStage5Texture);
        }


        // Orange
        public static Texture orangeTexture = new Texture("Trees/Orange.png");
        public static Texture orangeSaplingTexture = new Texture("Trees/Orange_Sapling.png");
        public static Texture orangeStage1Texture = new Texture("Trees/Orange_Stage_1.png");
        public static Texture orangeStage2Texture = new Texture("Trees/Orange_Stage_2.png");
        public static Texture orangeStage3Texture = new Texture("Trees/Orange_Stage_3.png");
        public static Texture orangeStage4Texture = new Texture("Trees/Orange_Stage_4.png");
        public static Texture orangeStage5Texture = new Texture("Trees/Orange_Stage_5.png");
        public static Texture orangeStage5WithFruit = new Texture("Trees/Orange_Stage_5_Fruit.png");

        public static ArrayList<Texture> orangeTextures = new ArrayList<>();
        static {
            orangeTextures.add(orangeStage1Texture);
            orangeTextures.add(orangeStage2Texture);
            orangeTextures.add(orangeStage3Texture);
            orangeTextures.add(orangeStage4Texture);
            orangeTextures.add(orangeStage5Texture);
        }

        // Peach
        public static Texture peachTexture = new Texture("Trees/Peach.png");
        public static Texture peachSaplingTexture = new Texture("Trees/Peach_Sapling.png");
        public static Texture peachStage1Texture = new Texture("Trees/Peach_Stage_1.png");
        public static Texture peachStage2Texture = new Texture("Trees/Peach_Stage_2.png");
        public static Texture peachStage3Texture = new Texture("Trees/Peach_Stage_3.png");
        public static Texture peachStage4Texture = new Texture("Trees/Peach_Stage_4.png");
        public static Texture peachStage5Texture = new Texture("Trees/Peach_Stage_5.png");
        public static Texture peachStage5WithFruit = new Texture("Trees/Peach_Stage_5_Fruit.png");

        public static ArrayList<Texture> peachTextures = new ArrayList<>();
        static {
            peachTextures.add(peachStage1Texture);
            peachTextures.add(peachStage2Texture);
            peachTextures.add(peachStage3Texture);
            peachTextures.add(peachStage4Texture);
            peachTextures.add(peachStage5Texture);
        }

        // Pine

        public static Texture pineSaplingTexture = new Texture("Trees/Pine_Cone.png");
        public static Texture pineStage1Texture = new Texture("Trees/Pine_Stage_1.png");
        public static Texture pineStage2Texture = new Texture("Trees/Pine_Stage_2.png");
        public static Texture pineStage3Texture = new Texture("Trees/Pine_Stage_3.png");
        public static Texture pineStage4Texture = new Texture("Trees/Pine_Stage_4.png");
        public static Texture pineStage5Texture = new Texture("Trees/Pine_Stage_5.png");

        public static ArrayList<Texture> pineTextures = new ArrayList<>();
        static {
            pineTextures.add(pineStage1Texture);
            pineTextures.add(pineStage2Texture);
            pineTextures.add(pineStage3Texture);
            pineTextures.add(pineStage4Texture);
            pineTextures.add(pineStage5Texture);
        }


        // Pomegranate
        public static Texture pomegranateTexture = new Texture("Trees/Pomegranate.png");
        public static Texture pomegranateSaplingTexture = new Texture("Trees/Pomegranate_Sapling.png");
        public static Texture pomegranateStage1Texture = new Texture("Trees/Pomegranate_Stage_1.png");
        public static Texture pomegranateStage2Texture = new Texture("Trees/Pomegranate_Stage_2.png");
        public static Texture pomegranateStage3Texture = new Texture("Trees/Pomegranate_Stage_3.png");
        public static Texture pomegranateStage4Texture = new Texture("Trees/Pomegranate_Stage_4.png");
        public static Texture pomegranateStage5Texture = new Texture("Trees/Pomegranate_Stage_5.png");
        public static Texture pomegranateStage5WithFruit = new Texture("Trees/Pomegranate_Stage_5_Fruit.png");

        public static ArrayList<Texture> pomegranateTextures = new ArrayList<>();
        static {
            pomegranateTextures.add(pomegranateStage1Texture);
            pomegranateTextures.add(pomegranateStage2Texture);
            pomegranateTextures.add(pomegranateStage3Texture);
            pomegranateTextures.add(pomegranateStage4Texture);
            pomegranateTextures.add(pomegranateStage5Texture);
        }

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


}
