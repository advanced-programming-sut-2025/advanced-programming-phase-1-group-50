package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GamePictureManager {
    public static Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

    public static TextureRegionDrawable greenBackground =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("green_bg.png"))));


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

        public static Texture cherryBombTexture = new Texture("Crafting/Cherry_Bomb.png");
        public static Texture bombTexture = new Texture("Crafting/Bomb.png");
        public static Texture megaBombTexture = new Texture("Crafting/Mega_Bomb.png");
        public static Texture sprinklerTexture = new Texture("Crafting/Sprinkler.png");
        public static Texture qualitySprinklerTexture = new Texture("Crafting/Quality_Sprinkler.png");
        public static Texture iridiumSprinklerTexture = new Texture("Crafting/Iridium_Sprinkler.png");
        public static Texture charcoalKilnTexture = new Texture("Crafting/Charcoal_Kiln.png");
        public static Texture furnaceTexture = new Texture("Crafting/Furnace.png");
        public static Texture scarecrowTexture = new Texture("Crafting/Scarecrow.png");
        public static Texture deluxeScarecrowTexture = new Texture("Crafting/Deluxe_Scarecrow.png");
        public static Texture beeHouseTexture = new Texture("Crafting/Bee_House.png");
        public static Texture cheesePressTexture = new Texture("Crafting/Cheese_Press.png");
        public static Texture kegTexture = new Texture("Crafting/Keg.png");
        public static Texture loomTexture = new Texture("Crafting/Loom.png");
        public static Texture mayonnaiseMachineTexture = new Texture("Crafting/Mayonnaise_Machine.png");
        public static Texture oilMakerTexture = new Texture("Crafting/Oil_Maker.png");
        public static Texture preservesJarTexture = new Texture("Crafting/Preserves_Jar.png");
        public static Texture dehydratorTexture = new Texture("Crafting/Dehydrator.png");
        public static Texture fishSmokerTexture = new Texture("Crafting/Fish_Smoker.png");
        public static Texture mysticTreeSeedTexture = new Texture("Crafting/Mystic_Tree_Seed.png");
}
