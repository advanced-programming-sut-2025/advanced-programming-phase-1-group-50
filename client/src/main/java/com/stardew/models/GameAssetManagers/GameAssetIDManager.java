package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.date.Season;

import java.util.HashMap;
import java.util.Map;

public class GameAssetIDManager {
    public static HashMap<Integer , TextureRegion> ids = new HashMap<>();
    static {
        ids.put(1 , GamePictureManager.axeTexture);
        ids.put(2 , GamePictureManager.copperAxeTexture);
        ids.put(3 , GamePictureManager.goldAxeTexture);
        ids.put(4 , GamePictureManager.iridiumAxeTexture);
        ids.put(5 , GamePictureManager.steelAxeTexture);
        ids.put(6 , GamePictureManager.pickaxeTexture);
        ids.put(7 , GamePictureManager.copperPickaxeTexture);
        ids.put(8 , GamePictureManager.steelPickaxeTexture);
        ids.put(9 , GamePictureManager.goldPickaxeTexture);
        ids.put(10, GamePictureManager.iridiumPickaxeTexture);
        ids.put(11, GamePictureManager.scytheTexture);
        ids.put(12 , GamePictureManager.milkPailTexture);
        ids.put(13 , GamePictureManager.milkPailTexture);
        ids.put(14 , GamePictureManager.wateringCanTexture);
        ids.put(15 , GamePictureManager.copperWateringCanTexture);
        ids.put(16 , GamePictureManager.goldWateringCanTexture);
        ids.put(17 , GamePictureManager.steelWateringCanTexture);
        ids.put(18 , GamePictureManager.steelWateringCanTexture);
        ids.put(19 , GamePictureManager.abigailTexture);
        ids.put(20 , GamePictureManager.robinTexture);
        ids.put(21 , GamePictureManager.sebastianTexture);
        ids.put(22 , GamePictureManager.harveyTexture);
        ids.put(23 , GamePictureManager.leahTexture);
        //Stone :
        ids.put(24 , GamePictureManager.farmBoulderTexture);


        ids.put(25 , GamePictureManager.jojaColaTexture);
        ids.put(26 , GamePictureManager.troutSoupTexture);
        ids.put(27, GamePictureManager.honeyTexture);
        ids.put(28, GamePictureManager.cheeseTexture);
        ids.put(29, GamePictureManager.cheeseByLargeMilkTexture);
        ids.put(30, GamePictureManager.goatCheeseTexture);
        ids.put(31, GamePictureManager.goatCheeseByLargeMilkTexture);
        ids.put(32, GamePictureManager.beerTexture);
        ids.put(33, GamePictureManager.vinegarTexture);
        ids.put(34, GamePictureManager.coffeeTexture);
        ids.put(35, GamePictureManager.juiceTexture);
        ids.put(36, GamePictureManager.meadTexture);
        ids.put(37, GamePictureManager.paleAleTexture);
        ids.put(38, GamePictureManager.wineTexture);
        ids.put(39, GamePictureManager.driedMushroomsTexture);
        ids.put(40, GamePictureManager.driedFruitTexture);
        ids.put(41, GamePictureManager.raisinsTexture);
        ids.put(42, GamePictureManager.coalTexture);
        ids.put(43, GamePictureManager.clothTexture);
        ids.put(44, GamePictureManager.mayonnaiseTexture);
        ids.put(45, GamePictureManager.duckMayonnaiseTexture);
        ids.put(46, GamePictureManager.dinosaurMayonnaiseTexture);
        ids.put(47, GamePictureManager.oilTexture);
        ids.put(48, GamePictureManager.truffleOilTexture);
        ids.put(49, GamePictureManager.picklesTexture);
        ids.put(50, GamePictureManager.jellyTexture);
        ids.put(51, GamePictureManager.smokedFishTexture);
        ids.put(52, GamePictureManager.ironBarTexture);
        ids.put(53, GamePictureManager.iridiumBarTexture);
        ids.put(54, GamePictureManager.copperBarTexture);
        ids.put(55, GamePictureManager.goldBarTexture);

        //apricot :
        ids.put(60 , GamePictureManager.apricotSaplingTexture);  //sapling
        ids.put(61 , GamePictureManager.apricotStage1Texture);
        ids.put(62 , GamePictureManager.apricotStage2Texture);
        ids.put(63 , GamePictureManager.apricotStage3Texture);
        ids.put(64 , GamePictureManager.apricotStage4Texture);
        ids.put(65  , GamePictureManager.apricotStage5WithFruit);
        int base = 0 ;
        for(Map.Entry<Season, TextureRegion> entry : GamePictureManager.apricotStage5.entrySet()){
            ids.put(base + 65  ,entry.getValue());
            base++;
        }
        ids.put(70 , GamePictureManager.apricotTexture); // fruit


        ids.put(80 , GamePictureManager.cherrySaplingTexture);
        ids.put(81 , GamePictureManager.cherryStage1Texture);
        ids.put(82 , GamePictureManager.cherryStage2Texture);
        ids.put(83 , GamePictureManager.cherryStage3Texture);
        ids.put(84 , GamePictureManager.cherryStage4Texture);
        ids.put(85 , GamePictureManager.cherryStage5WithFruitTexture);
        base = 0;
        for(Map.Entry<Season, TextureRegion> entry : GamePictureManager.cherryStage5.entrySet()){
            ids.put(base + 85  ,entry.getValue());
            base++;
        }
        ids.put(90 , GamePictureManager.cherryTexture);


        ids.put(100 , GamePictureManager.mangoSaplingTexture);
        ids.put(101 , GamePictureManager.mangoStage1Texture);
        ids.put(102 , GamePictureManager.mangoStage2Texture);
        ids.put(103 , GamePictureManager.mangoStage3Texture);
        ids.put(104 , GamePictureManager.mangoStage4Texture);
        ids.put(105, GamePictureManager.mangoStage5WithFruit);
        base = 0;
        for(Map.Entry<Season, TextureRegion> entry : GamePictureManager.mangoStage5.entrySet()){
            ids.put(base + 105  ,entry.getValue());
            base++;
        }
        ids.put(110, GamePictureManager.mangoTexture);


        ids.put(120 , GamePictureManager.orangeSaplingTexture);
        ids.put(121 , GamePictureManager.orangeStage1Texture);
        ids.put(122 , GamePictureManager.orangeStage2Texture);
        ids.put(123 , GamePictureManager.orangeStage3Texture);
        ids.put(124 , GamePictureManager.orangeStage4Texture);
        ids.put(125 , GamePictureManager.orangeStage5WithFruit);
        base = 0;
        for(Map.Entry<Season, TextureRegion> entry : GamePictureManager.orangeStage5.entrySet()){
            ids.put(base + 125  ,entry.getValue());
            base++;
        }
        ids.put(130, GamePictureManager.orangeTexture);

        ids.put(140 , GamePictureManager.peachSaplingTexture);
        ids.put(141 , GamePictureManager.peachStage1Texture);
        ids.put(142 , GamePictureManager.peachStage2Texture);
        ids.put(143 , GamePictureManager.peachStage3Texture);
        ids.put(144 , GamePictureManager.peachStage4Texture);
        ids.put(145 , GamePictureManager.peachStage5WithFruit);
        base = 0;
        for(Map.Entry<Season, TextureRegion> entry : GamePictureManager.peachStage5.entrySet()){
            ids.put(base + 145  ,entry.getValue());
            base++;
        }
        ids.put(150, GamePictureManager.peachTexture);

        ids.put(160 , GamePictureManager.appleSaplingTexture);
        ids.put(161 , GamePictureManager.appleStage1Texture);
        ids.put(162 , GamePictureManager.appleStage2Texture);
        ids.put(163 , GamePictureManager.appleStage3Texture);
        ids.put(164 , GamePictureManager.appleStage4Texture);
        ids.put(165 , GamePictureManager.appleStage5WithFruit);
        base = 0;
        for(Map.Entry<Season, TextureRegion> entry : GamePictureManager.appleStage5.entrySet()){
            ids.put(base + 165  ,entry.getValue());
            base++;
        }
        ids.put(170, GamePictureManager.appleTexture);

        ids.put(180 , GamePictureManager.pomegranateSaplingTexture);
        ids.put(181 , GamePictureManager.pomegranateStage1Texture);
        ids.put(182 , GamePictureManager.pomegranateStage2Texture);
        ids.put(183 , GamePictureManager.pomegranateStage3Texture);
        ids.put(184 , GamePictureManager.pomegranateStage4Texture);
        ids.put(185 , GamePictureManager.pomegranateStage5WithFruit);
        base = 0;
        for(Map.Entry<Season , TextureRegion> entry : GamePictureManager.pomegranateStage5.entrySet()){
            ids.put(base + 185  ,entry.getValue());
            base++;
        }
        ids.put(190, GamePictureManager.pomegranateTexture);


        ids.put(200 , GamePictureManager.acornTexture);
        ids.put(201 , GamePictureManager.oakStage1Texture);
        ids.put(202 , GamePictureManager.oakStage2Texture);
        ids.put(203 , GamePictureManager.oakStage3Texture);
        ids.put(204 , GamePictureManager.oakStage4Texture);
        base = 0;
        for(Map.Entry<Season , TextureRegion> entry : GamePictureManager.oakStage5.entrySet()){
            ids.put(base + 204  ,entry.getValue());
            base++;
        }

        ids.put(209, GamePictureManager.oakResinTexture);

        ids.put(210 , GamePictureManager.mapleSeedsTexture);
        ids.put(211 , GamePictureManager.mapleStage1Texture);
        ids.put(212 , GamePictureManager.mapleStage2Texture);
        ids.put(213 , GamePictureManager.mapleStage3Texture);
        ids.put(214 , GamePictureManager.mapleStage4Texture);
        base = 0;
        for(Map.Entry<Season , TextureRegion> entry : GamePictureManager.mapleStage5.entrySet()){
            ids.put(base + 214  ,entry.getValue());
            base++;
        }
        ids.put(219 , GamePictureManager.mapleSyrupTexture);






    }
}
