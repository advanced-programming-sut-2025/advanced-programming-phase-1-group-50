package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.date.Season;
import com.stardew.view.GameMenu;

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
            ids.put(base + 66  ,entry.getValue());
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
            ids.put(base + 86  ,entry.getValue());
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
            ids.put(base + 106  ,entry.getValue());
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
            ids.put(base + 126  ,entry.getValue());
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
            ids.put(base + 146  ,entry.getValue());
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
            ids.put(base + 166  ,entry.getValue());
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
            ids.put(base + 186  ,entry.getValue());
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
            ids.put(base + 205  ,entry.getValue());
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
            ids.put(base + 215  ,entry.getValue());
            base++;
        }
        ids.put(219 , GamePictureManager.mapleSyrupTexture);

        ids.put(220 , GamePictureManager.pineConeTexture);
        ids.put(221 , GamePictureManager.pineStage1Texture);
        ids.put(222 , GamePictureManager.pineStage2Texture);
        ids.put(223 , GamePictureManager.pineStage3Texture);
        ids.put(224 , GamePictureManager.pineStage4Texture);
        base = 0;
        for(Map.Entry<Season , TextureRegion> entry : GamePictureManager.pineStage5.entrySet()){
            ids.put(base + 225  ,entry.getValue());
            base ++;
        }


        ids.put(229 , GamePictureManager.pineTarTexture);
        ids.put(230 , GamePictureManager.mahoganySeedTexture);
        ids.put(231 , GamePictureManager.mahoganyStage1Texture);
        ids.put(232 , GamePictureManager.mahoganyStage2Texture);
        ids.put(233 , GamePictureManager.mahoganyStage3Texture);
        ids.put(234 , GamePictureManager.mahoganyStage4Texture);
        base = 0;
        for(Map.Entry<Season , TextureRegion> entry : GamePictureManager.mahoganyStage5.entrySet()){
            ids.put(235 + base , entry.getValue());
            base ++;
        }

        ids.put(239 , GamePictureManager.sapTexture);

        ids.put(240 , GamePictureManager.mushroomTreeSeedTexture);
        ids.put(241 , GamePictureManager.mushroomTreeStage1Texture);
        ids.put(242 , GamePictureManager.mushroomTreeStage2Texture);
        ids.put(243 , GamePictureManager.mushroomTreeStage3Texture);
        ids.put(244 , GamePictureManager.mushroomTreeStage4Texture);
        ids.put(245 , GamePictureManager.mushroomTreeStage5Texture);
        ids.put(246 , GamePictureManager.commonMushroom);


        ids.put(250 , GamePictureManager.mysticTreeSeedTexture);
        ids.put(251 , GamePictureManager.mysticTreeStage1Texture);
        ids.put(252 , GamePictureManager.mysticTreeStage2Texture);
        ids.put(253 , GamePictureManager.mysticTreeStage3Texture);
        ids.put(254 , GamePictureManager.mysticTreeStage4Texture);
        ids.put(255 , GamePictureManager.mysticTreeStage5Texture);
        ids.put(256  , GamePictureManager.mysticSyrupTexture);


        ids.put(260 , GamePictureManager.jazzSeedsTexture);
        ids.put(261, GamePictureManager.blueJazzStage1Texture);
        ids.put(262 , GamePictureManager.blueJazzStage2Texture);

        ids.put(263 , GamePictureManager.blueJazzStage3Texture);
        ids.put(264 , GamePictureManager.blueJazzStage4Texture);
        ids.put(265 , GamePictureManager.blueJazzStage5Texture);
        ids.put(266 , GamePictureManager.blueJazzTexture);

        ids.put(270 , GamePictureManager.carrotSeedsTexture);
        ids.put(271 , GamePictureManager.carrotStage1Texture);
        ids.put(272 , GamePictureManager.carrotStage2Texture);
        ids.put(273 , GamePictureManager.carrotStage3Texture);
        ids.put(274 , GamePictureManager.carrotStage4Texture);
        ids.put(275 , GamePictureManager.carrotTexture);


        ids.put(280 , GamePictureManager.cauliflowerSeedsTexture);
        ids.put(281 , GamePictureManager.cauliflowerStage1Texture);
        ids.put(282 , GamePictureManager.cauliflowerStage2Texture);
        ids.put(283 , GamePictureManager.cauliflowerStage3Texture);
        ids.put(284 , GamePictureManager.cauliflowerStage4Texture);
        ids.put(285 , GamePictureManager.cauliflowerStage5Texture);
        ids.put(286 , GamePictureManager.cauliflowerStage6Texture);
        ids.put(287 , GamePictureManager.cauliflowerTexture);


        ids.put(290 , GamePictureManager.coffeeBeanTexture);
        ids.put(291 , GamePictureManager.coffeeStage1Texture);
        ids.put(292 , GamePictureManager.coffeeStage2Texture);
        ids.put(293 , GamePictureManager.coffeeStage3Texture);
        ids.put(294 , GamePictureManager.coffeeStage4Texture);
        ids.put(295 , GamePictureManager.coffeeStage5Texture);
        ids.put(296 , GamePictureManager.coffeeStage6Texture);
        ids.put(297 , GamePictureManager.coffeeStage7Texture);


        ids.put(300 , GamePictureManager.garlicSeedsTexture);
        ids.put(301 , GamePictureManager.garlicStage1Texture);
        ids.put(302 , GamePictureManager.garlicStage2Texture);
        ids.put(303 , GamePictureManager.garlicStage3Texture);
        ids.put(304 , GamePictureManager.garlicStage4Texture);
        ids.put(305 , GamePictureManager.garlicStage5Texture);
        ids.put(306 , GamePictureManager.garlicTexture);


        ids.put(310 , GamePictureManager.greenBeanTexture);
        ids.put(311 , GamePictureManager.beanStarterTexture);
        ids.put(312 , GamePictureManager.greenBeanStage1Texture);
        ids.put(313 , GamePictureManager.greenBeanStage2Texture);
        ids.put(314 , GamePictureManager.greenBeanStage3Texture);
        ids.put(315 , GamePictureManager.greenBeanStage4Texture);
        ids.put(316 , GamePictureManager.greenBeanStage5Texture);

        ids.put(317 , GamePictureManager.greenBeanStage6Texture);
        ids.put(318 , GamePictureManager.greenBeanStage7Texture);

        ids.put(320 , GamePictureManager.kaleTexture);
        ids.put(321 , GamePictureManager.kaleSeedsTexture);
        ids.put(322 , GamePictureManager.kaleStage1Texture);
        ids.put(323 , GamePictureManager.kaleStage2Texture);
        ids.put(324 , GamePictureManager.kaleStage3Texture);
        ids.put(325 , GamePictureManager.kaleStage4Texture);
        ids.put(326 , GamePictureManager.kaleStage5Texture);


        ids.put(330 , GamePictureManager.parsnipSeedsTexture);
        ids.put(331 , GamePictureManager.parsnipStage1Texture);
        ids.put(332 , GamePictureManager.parsnipStage2Texture);
        ids.put(333 , GamePictureManager.parsnipStage3Texture);
        ids.put(334 , GamePictureManager.parsnipStage4Texture);
        ids.put(335 , GamePictureManager.parsnipStage5Texture);
        ids.put(336 , GamePictureManager.parsnipTexture);

        ids.put(340 , GamePictureManager.potatoSeedsTexture);
        ids.put(341 , GamePictureManager.potatoStage1Texture);
        ids.put(342 , GamePictureManager.potatoStage2Texture);
        ids.put(343 , GamePictureManager.potatoStage3Texture);
        ids.put(344 , GamePictureManager.potatoStage4Texture);
        ids.put(345 , GamePictureManager.potatoStage5Texture);
        ids.put(346 , GamePictureManager.potatoStage6Texture);
        ids.put(347  , GamePictureManager.potatoTexture);

        ids.put(350 , GamePictureManager.rhubarbSeedsTexture);
        ids.put(351 , GamePictureManager.rhubarbStage1Texture);
        ids.put(352 , GamePictureManager.rhubarbStage2Texture);
        ids.put(353 , GamePictureManager.rhubarbStage3Texture);
        ids.put(354 , GamePictureManager.rhubarbStage4Texture);
        ids.put(355 , GamePictureManager.rhubarbStage5Texture);
        ids.put(356 , GamePictureManager.rhubarbStage6Texture);
        ids.put(357 , GamePictureManager.rhubarbTexture);

        ids.put(360 , GamePictureManager.strawberrySeedsTexture);
        ids.put(361 , GamePictureManager.strawberryStage1Texture);
        ids.put(362 , GamePictureManager.strawberryStage2Texture);
        ids.put(363 , GamePictureManager.strawberryStage3Texture);
        ids.put(364 , GamePictureManager.strawberryStage4Texture);
        ids.put(365 , GamePictureManager.strawberryStage5Texture);
        ids.put(366 , GamePictureManager.strawberryStage6Texture);
        ids.put(367 , GamePictureManager.strawberryStage7Texture);
        ids.put(368 , GamePictureManager.strawberryTexture);



        ids.put(370 , GamePictureManager.tulipBulbTexture);
        ids.put(371 , GamePictureManager.tulipStage1Texture);
        ids.put(372 , GamePictureManager.tulipStage2Texture);
        ids.put(373 , GamePictureManager.tulipStage3Texture);
        ids.put(374 , GamePictureManager.tulipStage4Texture);
        ids.put(375 , GamePictureManager.tulipStage5Texture);
        ids.put(376, GamePictureManager.tulipTexture);


        ids.put(380 , GamePictureManager.riceShootTexture);
        ids.put(381 , GamePictureManager.unMilledRiceStage1Texture);
        ids.put(382 , GamePictureManager.unMilledRiceStage2Texture);
        ids.put(383 , GamePictureManager.unMilledRiceStage3Texture);
        ids.put(384 , GamePictureManager.unMilledRiceStage4Texture);
        ids.put(385 , GamePictureManager.unMilledRiceStage5Texture);
        ids.put(386 , GamePictureManager.unMilledRiceTexture);


        ids.put(390, GamePictureManager.blueberrySeedsTexture);
        ids.put(391 , GamePictureManager.blueberryStage1Texture);
        ids.put(392 , GamePictureManager.blueberryStage2Texture);
        ids.put(393 , GamePictureManager.blueberryStage3Texture);
        ids.put(394 , GamePictureManager.blueberryStage4Texture);
        ids.put(395 , GamePictureManager.blueberryStage5Texture);
        ids.put(396 , GamePictureManager.blueberryStage6Texture);
        ids.put(397 , GamePictureManager.blueberryStage7Texture);
        ids.put(398 , GamePictureManager.blueberryTexture);

        ids.put(400 , GamePictureManager.cornSeedsTexture);
        ids.put(401 , GamePictureManager.cornStage1Texture);
        ids.put(402 , GamePictureManager.cornStage2Texture);
        ids.put(403 , GamePictureManager.cornStage3Texture);
        ids.put(404 , GamePictureManager.cornStage4Texture);
        ids.put(405 , GamePictureManager.cornStage5Texture);
        ids.put(406 , GamePictureManager.cornStage6Texture);
        ids.put(407 , GamePictureManager.cornStage7Texture);
        ids.put(408 , GamePictureManager.cornTexture);


        ids.put(410 , GamePictureManager.hopsStarterTexture);
        ids.put(411 , GamePictureManager.hopsStage1Texture);
        ids.put(412 , GamePictureManager.hopsStage2Texture);
        ids.put(413 , GamePictureManager.hopsStage3Texture);
        ids.put(414 , GamePictureManager.hopsStage4Texture);
        ids.put(415 , GamePictureManager.hopsStage5Texture);
        ids.put(416 , GamePictureManager.hopsStage6Texture);
        ids.put(417 , GamePictureManager.hopsStage7Texture);
        ids.put(418 , GamePictureManager.hopsTexture);


        ids.put(420 , GamePictureManager.hotPepperTexture);
        ids.put( 421, GamePictureManager.hopsStage1Texture);
        ids.put(422 , GamePictureManager.hopsStage2Texture);
        ids.put(423 , GamePictureManager.hopsStage3Texture);
        ids.put(424 , GamePictureManager.hopsStage4Texture);
        ids.put(425 , GamePictureManager.hopsStage5Texture);
        ids.put(426 , GamePictureManager.hopsStage6Texture);
        ids.put(427 , GamePictureManager.hopsStage7Texture);

        ids.put(428 , GamePictureManager.pepperSeedsTexture);
        ids.put(430 , GamePictureManager.melonSeedsTexture);
        ids.put(431 , GamePictureManager.melonStage1Texture);
        ids.put(432 , GamePictureManager.melonStage2Texture);
        ids.put(433 , GamePictureManager.melonStage3Texture);
        ids.put(434 , GamePictureManager.melonStage4Texture);
        ids.put(435 , GamePictureManager.melonStage5Texture);
        ids.put(436 , GamePictureManager.melonStage6Texture);
        ids.put(437 , GamePictureManager.melonTexture);

        ids.put(440 , GamePictureManager.poppySeedsTexture);
        ids.put(441 , GamePictureManager.poppyStage1Texture);
        ids.put(442 , GamePictureManager.poppyStage2Texture);
        ids.put(443 , GamePictureManager.poppyStage3Texture);
        ids.put(444 , GamePictureManager.poppyStage4Texture);
        ids.put(445 , GamePictureManager.poppyStage5Texture);
        ids.put(446 , GamePictureManager.poppyTexture);


        ids.put(450 , GamePictureManager.radishSeedsTexture);
        ids.put(451 , GamePictureManager.radishStage1Texture);
        ids.put(452 , GamePictureManager.radishStage2Texture);
        ids.put(453 , GamePictureManager.radishStage3Texture);
        ids.put(454 , GamePictureManager.radishStage4Texture);
        ids.put(455 , GamePictureManager.radishStage5Texture);
        ids.put(456 ,GamePictureManager.radishTexture );

        ids.put(460 , GamePictureManager.redCabbageSeedsTexture);
        ids.put(461 , GamePictureManager.redCabbageStage1Texture);
        ids.put(462 , GamePictureManager.redCabbageStage2Texture);
        ids.put(463 , GamePictureManager.redCabbageStage3Texture);
        ids.put(464 , GamePictureManager.redCabbageStage4Texture);
        ids.put(465 , GamePictureManager.redCabbageStage5Texture);
        ids.put(466 , GamePictureManager.redCabbageStage6Texture);
        ids.put(467 , GamePictureManager.redCabbageTexture);


        ids.put(470 , GamePictureManager.starfruitSeedsTexture);
        ids.put(471 , GamePictureManager.starfruitStage1Texture);
        ids.put(472 , GamePictureManager.starfruitStage2Texture);
        ids.put(473 , GamePictureManager.starfruitStage3Texture);
        ids.put(474 , GamePictureManager.starfruitStage4Texture);
        ids.put(475 , GamePictureManager.starfruitStage5Texture);
        ids.put(477 , GamePictureManager.starfruitTexture);
        ids.put(476 , GamePictureManager.starfruitStage6Texture);


        ids.put(480 , GamePictureManager.spangleSeedsTexture);
        ids.put(481 , GamePictureManager.summerSpangleStage1Texture);
        ids.put(482 , GamePictureManager.summerSpangleStage2Texture);
        ids.put(483 , GamePictureManager.summerSpangleStage3Texture);
        ids.put(484 , GamePictureManager.summerSpangleStage4Texture);
        ids.put(485 , GamePictureManager.summerSpangleStage5Texture);
        ids.put(486 , GamePictureManager.summerSpangleTexture);

        ids.put(490 , GamePictureManager.summerSquashSeedsTexture);
        ids.put(491 , GamePictureManager.summerSquashStage1Texture);
        ids.put(492 , GamePictureManager.summerSquashStage2Texture);
        ids.put(493 , GamePictureManager.summerSquashStage3Texture);
        ids.put(494 , GamePictureManager.summerSquashStage4Texture);
        ids.put(495 , GamePictureManager.summerSquashStage5Texture);
        ids.put(496 , GamePictureManager.summerSquashStage6Texture);
        ids.put(497 , GamePictureManager.summerSquashStage7Texture);
        ids.put(498 , GamePictureManager.summerSquashTexture);

        ids.put(500 , GamePictureManager.sunflowerSeedsTexture);
        ids.put(501 , GamePictureManager.sunflowerStage1Texture);
        ids.put(502 , GamePictureManager.sunflowerStage2Texture);
        ids.put(503 , GamePictureManager.sunflowerStage3Texture);
        ids.put(504 , GamePictureManager.sunflowerStage4Texture);
        ids.put(505 , GamePictureManager.sunflowerStage5Texture);
        ids.put(506 , GamePictureManager.sunflowerTexture);


        ids.put(510 , GamePictureManager.tomatoSeedsTexture);
        ids.put(511 , GamePictureManager.tomatoStage1Texture);
        ids.put(512 , GamePictureManager.tomatoStage2Texture);
        ids.put(513 , GamePictureManager.tomatoStage3Texture);
        ids.put(514 , GamePictureManager.tomatoStage4Texture);
        ids.put(515 , GamePictureManager.tomatoStage5Texture);
        ids.put(516 , GamePictureManager.tomatoStage6Texture);
        ids.put(517 , GamePictureManager.tomatoStage7Texture);
        ids.put(518 , GamePictureManager.tomatoTexture);

        ids.put(520 , GamePictureManager.wheatSeedsTexture);
        ids.put(521 , GamePictureManager.wheatStage1Texture);
        ids.put(522 , GamePictureManager.wheatStage2Texture);
        ids.put(523 , GamePictureManager.wheatStage3Texture);
        ids.put(524 , GamePictureManager.wheatStage4Texture);
        ids.put(525 , GamePictureManager.wheatStage5Texture);
        ids.put(526 , GamePictureManager.wheatTexture);


        ids.put(530 , GamePictureManager.amaranthSeedsTexture);
        ids.put(531 , GamePictureManager.amaranthStage1Texture);
        ids.put(532 , GamePictureManager.amaranthStage2Texture);
        ids.put(533 , GamePictureManager.amaranthStage3Texture);
        ids.put(534 , GamePictureManager.amaranthStage4Texture);
        ids.put(535 , GamePictureManager.amaranthStage5Texture);
        ids.put(536 , GamePictureManager.amaranthTexture);

        ids.put(540,GamePictureManager.artichokeSeedsTexture);
        ids.put(541 , GamePictureManager.artichokeStage1Texture);
        ids.put(542 , GamePictureManager.artichokeStage2Texture);
        ids.put(543 , GamePictureManager.artichokeStage3Texture);
        ids.put(544 , GamePictureManager.artichokeStage4Texture);

        ids.put(545 , GamePictureManager.artichokeStage5Texture);
        ids.put(547 , GamePictureManager.artichokeTexture);
        ids.put(546 , GamePictureManager.artichokeStage6Texture);

        ids.put(550 , GamePictureManager.beetSeedsTexture);
        ids.put(551 , GamePictureManager.beetStage1Texture);
        ids.put(552 , GamePictureManager.beetStage2Texture);
        ids.put(553 , GamePictureManager.beetStage3Texture);
        ids.put(554 , GamePictureManager.beetStage4Texture);
        ids.put(555 , GamePictureManager.beetStage5Texture);
        ids.put(556, GamePictureManager.beetTexture);

        ids.put(570 , GamePictureManager.bokChoySeedsTexture);
        ids.put(571 , GamePictureManager.bokChoyStage1Texture);
        ids.put(572 , GamePictureManager.bokChoyStage2Texture);
        ids.put(573 , GamePictureManager.bokChoyStage3Texture);
        ids.put(574 , GamePictureManager.bokChoyStage4Texture);
        ids.put(575 , GamePictureManager.bokChoyStage5Texture);
        ids.put(576 , GamePictureManager.bokChoyTexture);

        ids.put(560 , GamePictureManager.broccoliSeedsTexture);
        ids.put(561 , GamePictureManager.broccoliStage1Texture);
        ids.put(562 , GamePictureManager.broccoliStage2Texture);
        ids.put(563 , GamePictureManager.broccoliStage3Texture);
        ids.put(564 , GamePictureManager.broccoliStage4Texture);
        ids.put(565 , GamePictureManager.broccoliStage5Texture);
        ids.put(566 , GamePictureManager.broccoliStage6Texture);
        ids.put(567 , GamePictureManager.broccoliTexture);


        ids.put(580 , GamePictureManager.cranberrySeedsTexture);
        ids.put(581 , GamePictureManager.cranberriesStage1Texture);
        ids.put(582 , GamePictureManager.cranberriesStage2Texture);
        ids.put(583 , GamePictureManager.cranberriesStage3Texture);
        ids.put(584 , GamePictureManager.cranberriesStage4Texture);
        ids.put(585 , GamePictureManager.cranberriesStage5Texture);
        ids.put(586 , GamePictureManager.cranberriesStage6Texture);
        ids.put(587 , GamePictureManager.cranberriesStage7Texture);
        ids.put(588 , GamePictureManager.cranberriesTexture);


        ids.put(590 , GamePictureManager.eggplantSeedsTexture);
        ids.put(591 , GamePictureManager.eggplantStage1Texture);
        ids.put(592 , GamePictureManager.eggplantStage2Texture);
        ids.put(593 , GamePictureManager.eggplantStage3Texture);
        ids.put(594 , GamePictureManager.eggplantStage4Texture);
        ids.put(595 , GamePictureManager.eggplantStage5Texture);
        ids.put(596 , GamePictureManager.eggplantStage6Texture);
        ids.put(597 , GamePictureManager.eggplantStage7Texture);
        ids.put(598 , GamePictureManager.eggplantTexture);


        ids.put(600 , GamePictureManager.fairySeedsTexture);
        ids.put(601, GamePictureManager.fairyRoseStage1Texture);
        ids.put(602 , GamePictureManager.fairyRoseStage2Texture);
        ids.put(603 , GamePictureManager.fairyRoseStage3Texture);
        ids.put(604 , GamePictureManager.fairyRoseStage4Texture);
        ids.put(605 , GamePictureManager.fairyRoseStage5Texture);
        ids.put(606 , GamePictureManager.fairyRoseTexture);


        ids.put(610 , GamePictureManager.grapeStarterTexture);
        ids.put(611 , GamePictureManager.grapeStage1Texture);
        ids.put(612 , GamePictureManager.grapeStage2Texture);
        ids.put(613 , GamePictureManager.grapeStage3Texture);
        ids.put(614 , GamePictureManager.grapeStage4Texture);
        ids.put(615 , GamePictureManager.grapeStage5Texture);
        ids.put(616 , GamePictureManager.grapeStage6Texture);
        ids.put(617 , GamePictureManager.grapeStage7Texture);
        ids.put(618 , GamePictureManager.grapeTexture);

        ids.put(620 , GamePictureManager.pumpkinSeedsTexture);
        ids.put(621 , GamePictureManager.pumpkinStage1Texture);
        ids.put(622 , GamePictureManager.pumpkinStage2Texture);
        ids.put(623 , GamePictureManager.pumpkinStage3Texture);
        ids.put(624 , GamePictureManager.pumpkinStage4Texture);
        ids.put(625 , GamePictureManager.pumpkinStage5Texture);
        ids.put(626 , GamePictureManager.pumpkinStage6Texture);
        ids.put(627 , GamePictureManager.pumpkinTexture);


        ids.put(630 , GamePictureManager.yamSeedsTexture);
        ids.put(631 , GamePictureManager.yamStage1Texture);
        ids.put(632 , GamePictureManager.yamStage2Texture);
        ids.put(633 , GamePictureManager.yamStage3Texture);
        ids.put(634 , GamePictureManager.yamStage4Texture);
        ids.put(635 , GamePictureManager.yamStage5Texture);
        ids.put(636 , GamePictureManager.yamTexture);

        ids.put(640 , GamePictureManager.rareSeedTexture);
        ids.put(641 , GamePictureManager.sweetGemBerryStage1Texture);
        ids.put(642 , GamePictureManager.sweetGemBerryStage2Texture);
        ids.put(643 , GamePictureManager.sweetGemBerryStage3Texture);
        ids.put(644 , GamePictureManager.sweetGemBerryStage4Texture);
        ids.put(645 , GamePictureManager.sweetGemBerryStage5Texture);
        ids.put(646 , GamePictureManager.sweetGemBerryStage6Texture);
        ids.put(647 , GamePictureManager.sweetGemBerryTexture);


        ids.put(650 , GamePictureManager.powdermelonSeedsTexture);
        ids.put(651 , GamePictureManager.powdermelonStage1Texture);
        ids.put(652 , GamePictureManager.powdermelonStage2Texture);
        ids.put(653 , GamePictureManager.powdermelonStage3Texture);
        ids.put(654 , GamePictureManager.powdermelonStage4Texture);
        ids.put(655 , GamePictureManager.powdermelonStage5Texture);
        ids.put(656 , GamePictureManager.powdermelonStage6Texture);
        ids.put(657 , GamePictureManager.powdermelonTexture);

        ids.put(660 , GamePictureManager.ancientSeedsTexture);
        ids.put(661 , GamePictureManager.ancientFruitStage1Texture);
        ids.put(662 , GamePictureManager.ancientFruitStage2Texture);
        ids.put(663 , GamePictureManager.ancientFruitStage3Texture);
        ids.put(664 , GamePictureManager.ancientFruitStage4Texture);
        ids.put(665 , GamePictureManager.ancientFruitStage5Texture);
        ids.put(666 , GamePictureManager.ancientFruitStage6Texture);
        ids.put(667 , GamePictureManager.ancientFruitStage7Texture);
        ids.put(668 , GamePictureManager.ancientFruitTexture);

        ids.put(670 , GamePictureManager.mixedSeedsTexture);
        ids.put(671 , GamePictureManager.daffodil);
        ids.put(672 , GamePictureManager.dandelion);
        ids.put(673 , GamePictureManager.leek);
        ids.put(674 , GamePictureManager.morel);
        ids.put(675 , GamePictureManager.salmonBerry);
        ids.put(676 , GamePictureManager.springOnion);
        ids.put(677 , GamePictureManager.wildHorseradish);
        ids.put(678 , GamePictureManager.fiddleHeadFern);
        ids.put(679 , GamePictureManager.grape);
        ids.put(680 , GamePictureManager.redMushroom);
        ids.put(681 , GamePictureManager.spiceBerry);
        ids.put(682 , GamePictureManager.sweetPea);
        ids.put(683 , GamePictureManager.blackberry);
        ids.put(684 , GamePictureManager.chanterelle);
        ids.put(685 , GamePictureManager.hazelnut);
        ids.put(686 , GamePictureManager.purpleMushroom);
        ids.put(687 , GamePictureManager.wildPlum);
        ids.put(688 , GamePictureManager.crocus);
        ids.put(689 , GamePictureManager.crystalFruit);
        ids.put(690 , GamePictureManager.holly);
        ids.put(691 , GamePictureManager.snowYam);
        ids.put(692 , GamePictureManager.winterRoot);

// Fertilizer
        ids.put(693 , GamePictureManager.waterFertilizer);
        ids.put(694 , GamePictureManager.growthFertilizer);

// Minerals
        ids.put(695 , GamePictureManager.quartz);
        ids.put(696 , GamePictureManager.earthCrystal);
        ids.put(697 , GamePictureManager.frozenTear);
        ids.put(698 , GamePictureManager.fireQuartz);
        ids.put(699 , GamePictureManager.emerald);
        ids.put(700 , GamePictureManager.aquamarine);
        ids.put(701 , GamePictureManager.ruby);
        ids.put(702 , GamePictureManager.amethyst);
        ids.put(703 , GamePictureManager.topaz);
        ids.put(704 , GamePictureManager.jade);
        ids.put(705 , GamePictureManager.diamond);
        ids.put(706 , GamePictureManager.prismaticShard);
        ids.put(707 , GamePictureManager.copperOre);
        ids.put(708 , GamePictureManager.ironOre);
        ids.put(709 , GamePictureManager.goldOre);
        ids.put(710 , GamePictureManager.iridiumOre);
        ids.put(711 , GamePictureManager.coalMineral);

// Fish
        ids.put(712 , GamePictureManager.salmon);
        ids.put(713 , GamePictureManager.sardine);
        ids.put(714 , GamePictureManager.shad);
        ids.put(715 , GamePictureManager.blueDiscus);
        ids.put(716 , GamePictureManager.midnightCarp);
        ids.put(717 , GamePictureManager.squid);
        ids.put(718 , GamePictureManager.tuna);
        ids.put(719 , GamePictureManager.perch);
        ids.put(720 , GamePictureManager.flounder);
        ids.put(721 , GamePictureManager.lionfish);
        ids.put(722 , GamePictureManager.herring);
        ids.put(723 , GamePictureManager.ghostfish);
        ids.put(724 , GamePictureManager.tilapia);
        ids.put(725 , GamePictureManager.dorado);
        ids.put(726 , GamePictureManager.sunfish);
        ids.put(727 , GamePictureManager.rainbowTrout);
        ids.put(728 , GamePictureManager.legend);
        ids.put(729 , GamePictureManager.glacierfish);
        ids.put(730 , GamePictureManager.angler);
        ids.put(731 , GamePictureManager.crimsonfish);















    }
}
