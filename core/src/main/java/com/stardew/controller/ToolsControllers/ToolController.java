package com.stardew.controller.ToolsControllers;

import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.controller.ForagingControllers.ForagingController;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.foraging.ForagingMineral;
import com.stardew.models.foraging.Growable;
import com.stardew.models.foraging.Tree;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGoodType;
import com.stardew.models.mapInfo.*;
import com.stardew.models.tools.*;
import com.stardew.models.userInfo.Coin;
import com.stardew.models.userInfo.Player;
import com.stardew.models.userInfo.TrashCan;
import com.stardew.models.waterBodies.Lake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ToolController {
    private final AnimalsController animalsController = new AnimalsController();
    private final ForagingController foragingController = new ForagingController();

    public boolean haveEnoughCoins(int price, Player player) {
        int coinValue = player.getBackpack().getIngredientQuantity().getOrDefault(new Coin(), 0);
        return coinValue >= price;
    }

    public void minusCoinForUpgradeTool(int price, Player player) {
        Coin coin = new Coin();
        int value = player.getBackpack().getIngredientQuantity().getOrDefault(coin, 0);
        player.getBackpack().getIngredientQuantity().put(coin, value - price);
    }

    public Result ToolsEquip(String input) {
        for (Tool t : App.getGame().getCurrentPlayingPlayer().getBackpack().getTools()) {
            if (t.getClass().getSimpleName().equals(input)) {
                App.getGame().getCurrentPlayingPlayer().setCurrentTool(t);
                return new Result(true, "Tool equipped");
            }
        }
        return new Result(false, "Tool not found");
    }

    public Result showCurrentTool() {
        return new Result(true, "Current Tool : " + App.getGame().getCurrentPlayingPlayer()
                .getCurrentTool().getClass().getSimpleName());
    }

    public Result showAvailableTool() {
        StringBuilder sb = new StringBuilder();
        for (Tool t : App.getGame().getCurrentPlayingPlayer().getBackpack().getTools()) {
            sb.append(t.getClass().getSimpleName());
            sb.append("\n");

        }
        return new Result(true, "Available Tools : \n" + sb);
    }

    public Result upgradeTool(String input) {

        Player player = App.getGame().getCurrentPlayingPlayer();
        ArrayList<Tool> tools = player.getBackpack().getTools();
        HashMap<Ingredient, Integer> ingredients = player.getBackpack().getIngredientQuantity();
        Map map = App.getGame().getMap();
        int price;

        if (input.equals("TrashCan")) {

            if (!map.isAroundPlaceable(player, map.getNpcVillage().getBlacksmith())) {
                return new Result(false, "you should be near blacksmith");
            }

            if (!map.getNpcVillage().getBlacksmith().isOpen()) {
                return new Result(false, "this store is currently closed");
            }

            TrashCan trashCan = player.getBackpack().getTrashCan();
            price = trashCan.getPriceForUpgrade();


            if (!haveEnoughCoins(price, player)) {
                return new Result(false, "you don't have enough coins");
            }

            switch (price) {
                case 0: {
                    return new Result(false, "This tool is at the highest level");
                }
                case 1000: {
                    if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.CopperBar), 0) < 5) {
                        return new Result(false, "you don't have enough Copper bar");
                    }
                    if (!map.getNpcVillage().getBlacksmith().canUpgradeTool("Copper Trash Can")) {
                        return new Result(false, "Insufficient remaining upgrades for today");
                    }
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.CopperBar), -5);

                    break;
                }
                case 2500: {
                    if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.IronBar), 0) < 5) {
                        return new Result(false, "you don't have enough Iron bar");
                    }
                    if (!map.getNpcVillage().getBlacksmith().canUpgradeTool("Steel Trash Can")) {
                        return new Result(false, "Insufficient remaining upgrades for today");
                    }
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.IronBar), -5);
                    break;
                }
                case 5000: {
                    if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.GoldBar), 0) < 5) {
                        return new Result(false, "you don't have enough Gold bar");
                    }
                    if (!map.getNpcVillage().getBlacksmith().canUpgradeTool("Gold Trash Can")) {
                        return new Result(false, "Insufficient remaining upgrades for today");
                    }
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.GoldBar), -5);
                    break;
                }
                case 12500: {
                    if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.IridiumBar), 0) < 5) {
                        return new Result(false, "you don't have enough Iridium bar");
                    }
                    if (!map.getNpcVillage().getBlacksmith().canUpgradeTool("Iridium Trash Can")) {
                        return new Result(false, "Insufficient remaining upgrades for today");
                    }
                    App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.IridiumBar), -5);
                    break;
                }

            }

            trashCan.upgradeTool();
            minusCoinForUpgradeTool(price, player);
            return new Result(true, "Tool upgraded");

        }

        for (Tool t : tools) {

            if (t.getClass().getSimpleName().equals(input)) {

                if (t instanceof FishingPole) {

                    return new Result(false, "you can't upgrade fishing pole");

                } else {

                    if (!map.isAroundPlaceable(player, map.getNpcVillage().getBlacksmith())) {
                        return new Result(false, "you should be near blacksmith");
                    }

                    if (!map.getNpcVillage().getBlacksmith().isOpen()) {
                        return new Result(false, "this store is currently closed");
                    }

                    if (t.getToolType() == null) {
                        return new Result(false, "you can't upgrade this tool");
                    }

                    price = t.getToolType().getPriceForUpgrade();
                    if (!haveEnoughCoins(price, player)) {
                        return new Result(false, "you don't have enough coins");
                    }

                    switch (price) {
                        case 0: {
                            return new Result(false, "This tool is at the highest level");
                        }
                        case 2000: {
                            if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.CopperBar), 0) < 5) {
                                return new Result(false, "you don't have enough Copper bar");
                            }
                            if (!map.getNpcVillage().getBlacksmith().canUpgradeTool("Copper Tool")) {
                                return new Result(false, "Insufficient remaining upgrades for today");
                            }
                            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.CopperBar), -5);

                            break;
                        }
                        case 5000: {
                            if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.IronBar), 0) < 5) {
                                return new Result(false, "you don't have enough Iron bar");
                            }
                            if (!map.getNpcVillage().getBlacksmith().canUpgradeTool("Steel Tool")) {
                                return new Result(false, "Insufficient remaining upgrades for today");
                            }
                            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.IronBar), -5);
                            break;
                        }
                        case 10000: {
                            if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.GoldBar), 0) < 5) {
                                return new Result(false, "you don't have enough Gold bar");
                            }
                            if (!map.getNpcVillage().getBlacksmith().canUpgradeTool("Gold Tool")) {
                                return new Result(false, "Insufficient remaining upgrades for today");
                            }
                            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.GoldBar), -5);
                            break;
                        }
                        case 25000: {
                            if (ingredients.getOrDefault(new ArtisanGood(ArtisanGoodType.IridiumBar), 0) < 5) {
                                return new Result(false, "you don't have enough Iridium bar");
                            }
                            if (!map.getNpcVillage().getBlacksmith().canUpgradeTool("Iridium Tool")) {
                                return new Result(false, "Insufficient remaining upgrades for today");
                            }
                            App.getGame().getCurrentPlayingPlayer().getBackpack().addIngredients(new ArtisanGood(ArtisanGoodType.IridiumBar), -5);
                            break;
                        }

                    }

                    t.upgradeTool();
                    minusCoinForUpgradeTool(price, player);
                    return new Result(true, "Tool upgraded");

                }
            }


        }

        return new Result(false, "Tool not found");


    }

    public Result useTool(String direction) {
        Direction d = Direction.getDirectionByInput(direction);
        Player p = App.getGame().getCurrentPlayingPlayer();
        Tile tile = App.getGame().getMap().findTile(p.getPosition());
        Tool tool = p.getCurrentTool();

        if (d == null) {
            return new Result(false, "Invalid direction");
        }
        Tile targetTile = App.getGame().getMap().getTileByDirection(tile, d);


        if (tool instanceof Hoe hoe) {
            if (App.getGame().getMap().getTileByDirection(tile, d) != null &&
                    App.getGame().getMap().getTileByDirection(tile, d).getPlaceable() == null) {
                Result energyConsumptionResult = hoe.useTool();
                if (!energyConsumptionResult.getSuccessful())
                    return energyConsumptionResult;
                App.getGame().getMap().getTileByDirection(tile, d).setPlowed(true);
                return new Result(true, "tile plowed successfully!");
            } else
                return new Result(false, "You can't plow this tile with Hoe!");
        }
        if (tool instanceof Pickaxe pickaxe) {
            Result energyConsumptionResult = pickaxe.useTool();
            if (!energyConsumptionResult.getSuccessful())
                return energyConsumptionResult;

            if (targetTile.getPlaceable() != null) {
                if (targetTile.getPlaceable() instanceof Stone stone) {
                    targetTile.setPlaceable(null);
                    p.getFarm().getStones().remove(stone);
                    p.getFarm().getPlaceables().remove(stone);
                    targetTile.setSymbol('.');
                    p.getBackpack().addIngredients(stone, 1);
                    p.getAbility().increaseMiningRate(10);
                    return new Result(true, "stone broken");

                } else if (targetTile.getPlaceable() instanceof Quarry quarry) {
                    Random rand = new Random();
                    if (!quarry.getForagingMinerals().isEmpty()) {

                        ForagingMineral fg = quarry.getForagingMinerals()
                                .get(rand.nextInt(quarry.getForagingMinerals().size()));
                        quarry.getForagingMinerals().remove(fg);
                        int q  = 1;
                        if(p.getAbility().getMiningLevel() > 2) q = 2;
                        p.getBackpack().addIngredients(fg, q);
                        p.getAbility().increaseMiningRate(10);
                        return new Result(true, "you add a foraging mineral to the backpack");

                    }
                    return new Result(false, "this quarry is empty");
                }

            } else {
                targetTile.setPlowed(false);
                return new Result(true, "target tile unPlowed successfully!");
            }
        }
        if (tool instanceof Axe axe) {

            if (targetTile.getPlaceable() instanceof Tree tree) {
                Result energyConsumptionResult = axe.useTool();
                if (!energyConsumptionResult.getSuccessful())
                    return energyConsumptionResult;
                p.getFarm().getTrees().remove(tree);
                p.getFarm().getPlaceables().remove(tree);
                targetTile.setPlaceable(null);
                targetTile.setSymbol('.');
                targetTile.setPlowed(false);
                targetTile.setFertilizer(null);
                targetTile.setWalkable(true);
                int numberOfWoods = tree.getCurrentStage();
                if (numberOfWoods > 0) {
                    Wood wood = new Wood();
                    p.getBackpack().addIngredients(wood, numberOfWoods);
                }
                int numberOfSeeds = new Random().nextInt(2) + 1;
                p.getBackpack().addIngredients(tree.getType().getSource(), numberOfSeeds);
                return new Result(true, String.format(
                        "tree cut successfully! You got %d woods and %d Seeds!", numberOfWoods, numberOfSeeds));
            }

            return new Result(false, "there is no tree for cut!");
        }
        if (tool instanceof FishingPole fishingPole) {

            if (targetTile.getPlaceable() instanceof Lake lake) {
                Result energyConsumptionResult = fishingPole.useTool();
                if (!energyConsumptionResult.getSuccessful())
                    return energyConsumptionResult;
                return animalsController.fishing(fishingPole);
            }

            return new Result(false, "there is no lake for fishing!");
        }
        if (tool instanceof Scythe scythe) {

            if (targetTile.getPlaceable() instanceof Growable plant) {
                Result energyConsumptionResult = scythe.useTool();
                if (!energyConsumptionResult.getSuccessful())
                    return energyConsumptionResult;
                p.getAbility().increaseForagingRate(10);
                return foragingController.harvestWithScythe(plant, targetTile);
            }

            return new Result(false, "there is no tree or crop for harvest!");
        }
        if (tool instanceof WateringCan wateringCan) {

            if (targetTile.getPlaceable() instanceof Growable plant) {
                if (wateringCan.getWaterCapacity() <= 0) {
                    return new Result(false, "your wateringCan is empty");
                }
                Result energyConsumptionResult = wateringCan.useTool();
                if (!energyConsumptionResult.getSuccessful())
                    return energyConsumptionResult;
                return foragingController.waterPlantWithUseTool(plant);
            }
            if (targetTile.getPlaceable() instanceof Lake) {
                Result energyConsumptionResult = wateringCan.useTool();
                if (!energyConsumptionResult.getSuccessful())
                    return energyConsumptionResult;
                wateringCan.makeFull();
            }

            return new Result(false, "there is no plant or lake!");

        }
        if (tool instanceof MilkPail || tool instanceof Shear) {
            return new Result(false, "please use command : collect produce -n <nameOfAnimal>");
        }

        return new Result(false, "you don't have a tool , please set your current tool");

    }


}
