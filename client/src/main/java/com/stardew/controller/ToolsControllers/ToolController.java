package com.stardew.controller.ToolsControllers;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.controller.ForagingControllers.ForagingController;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.foraging.ForagingMineral;
import com.stardew.models.foraging.Growable;
import com.stardew.models.foraging.Tree;
import com.stardew.models.mapInfo.*;
import com.stardew.models.tools.*;
import com.stardew.models.userInfo.Player;
import com.stardew.models.waterBodies.Lake;

import java.util.Random;

public class ToolController {
    private final AnimalsController animalsController = new AnimalsController();
    private final ForagingController foragingController = new ForagingController();

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

    public Result useTool(Tile targetTile, Stage stage) {
        if (targetTile == null)
            return new Result(false, "Selected tile is null!");

        Player p = App.getGame().getCurrentPlayingPlayer();
        Tile myTile = App.getGame().getMap().findTile(p.getPosition());

        if (!myTile.isAroundMe(targetTile)) {
            return new Result(false, "Selected Tile is not near you!");
        }

        Tool tool = p.getCurrentTool();


        if (tool instanceof Hoe hoe) {
            if (targetTile.getPlaceable() == null) {
                Result energyConsumptionResult = hoe.useTool();
                if (!energyConsumptionResult.getSuccessful())
                    return energyConsumptionResult;
                targetTile.setPlowed(true);
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
                    targetTile.setPlowed(false);
                    targetTile.setFertilizer(null);
                    targetTile.setWalkable(true);
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
                } else {
                    return new Result(false, "You can't use this tool on this tile!");
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
                return animalsController.fishing(fishingPole, stage);
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
                targetTile.setWatered(true);
                return foragingController.waterPlantWithUseTool(plant);
            }
            else if (targetTile.getPlaceable() instanceof Lake) {
                if (wateringCan.isFull())
                    return new Result(false, "your wateringCan is Full!");
                Result energyConsumptionResult = wateringCan.useTool();
                if (!energyConsumptionResult.getSuccessful())
                    return energyConsumptionResult;
                wateringCan.makeFull();
                return new Result(true, "You made full wateringCan.");
            }

            return new Result(false, "there is no plant or lake!");

        }
        if (tool instanceof MilkPail || tool instanceof Shear) {
            return new Result(false, "please use this Tool for <collect produce>");
        }

        return new Result(false, "you don't have a tool , please set your current tool");

    }


}
