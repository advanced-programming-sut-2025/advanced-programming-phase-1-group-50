package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class GamePictureManager {
    public static Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.skin"));

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
}
