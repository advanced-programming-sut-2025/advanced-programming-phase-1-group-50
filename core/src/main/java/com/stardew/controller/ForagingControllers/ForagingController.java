package com.stardew.controller.ForagingControllers;

import com.stardew.models.InventoryItem;
import com.stardew.models.Placeable;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.date.Season;
import com.stardew.models.foraging.*;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.userInfo.Player;

import java.util.ArrayList;
import java.util.Random;

public class ForagingController {
    public Result craftInfo(String craftName) {
        CropType crop = CropType.getCropTypeByName(craftName);

        if (crop == null)
            return new Result(false, "Craft <" + craftName + "> not found");

        String output =
                String.format("Name:             %s\n", crop.getName()) +
                String.format("Source:           %s\n", crop.getSource()) +
                String.format("Stages:           %s\n", crop.getStages()) +
                String.format("TotalHarvestTime: %d\n", crop.getTotalHarvestTime()) +
                String.format("One Time:         %s\n", crop.isOneTime()) +
                String.format("Regrowth Time:    %d\n", crop.getRegrowthTime()) +
                String.format("Base Sell Price:  %d\n", crop.getBaseSellPrice()) +
                String.format("Is Edible:        %s\n", crop.isEdible()) +
                String.format("Base Energy:      %d\n", crop.getEnergy()) +
                String.format("Season:           %s\n", crop.getSeasons()) +
                String.format("Can Become Giant: %s\n", crop.CanBecomeGiant());

        return new Result(true, output);
    }

    public Result cropInfo(String craftName) {
        return craftInfo(craftName);
    }

    public Result treeInfo(String treeName) {
        TreeType tree = TreeType.getTreeTypeByName(treeName);

        if (tree == null)
            return new Result(false, "Tree <" + treeName + "> not found");
        String output =
                String.format("Name:               %s\n", tree.getName()) +
                String.format("Source:             %s\n", tree.getSource()) +
                String.format("Stages:             %s\n", tree.getStages()) +
                String.format("Total Harvest Time: %d\n", tree.getTotalHarvestTime()) +
                String.format("Fruit:              %s\n", tree.getFruit()) +
                String.format("HarvestCycle:       %d\n", tree.getHarvestCycle()) +
                String.format("FruitBaseSellPrice: %d\n", tree.getFruitBaseSellPrice()) +
                String.format("IsFruitEdible:      %s\n", tree.isFruitEdible()) +
                String.format("FruitEnergy:        %d\n", tree.getFruitEnergy()) +
                String.format("Season:             %s\n", tree.getSeason());

        return new Result(true, output);
    }

    public Result foragingCropInfo(String cropName) {
        ForagingCrop foragingCrop = ForagingCrop.getForagingCropByName(cropName);

        if (foragingCrop == null)
            return new Result(false, "Foraging <" + cropName + "> not found");

        String output =
                String.format("Name:          %s\n", foragingCrop.getName()) +
                String.format("Season:        %s\n", foragingCrop.getSeason()) +
                String.format("BaseSellPrice: %d\n", foragingCrop.getBaseSellPrice()) +
                String.format("Energy:        %d\n", foragingCrop.getEnergy());

        return new Result(true, output);
    }

    public Result foragingTreeInfo(String treeName) {
        ForagingTreeSource foragingTreeSource = ForagingTreeSource.getForagingTreeSourceByName(treeName);

        if (foragingTreeSource == null)
            return new Result(false, "Foraging <" + treeName + "> not found");

        String output =
                String.format("Name:   %s\n", foragingTreeSource.getName()) +
                String.format("Season: %s\n", foragingTreeSource.getSeason());

        return new Result(true, output);
    }

    public Result harvestWithScythe(Growable plant, Tile targetTile) {
        Player player = App.getGame().getCurrentPlayingPlayer();

        if (!plant.isComplete())
            return new Result(false, "Plant hasn't grown completely!");

        if (plant.canGrowAgain()) {
            if (plant.isCompleteAgain()) {
                if (plant instanceof Crop crop)
                    player.getBackpack().addIngredients(crop, new Random().nextInt(2) + 2);
                else if (plant instanceof Tree tree) {
                    if (tree.getType().getSeason() != Season.Special &&
                        tree.getType().getSeason() != App.getGame().getTime().getSeason())
                        return new Result(false, "You can't get fruit in this season!");
                    player.getBackpack().addIngredients(tree.getType().getFruit(), new Random().nextInt(3) + 3);
                }

                plant.doAgainHarvesting();
                player.getAbility().increaseFarmingRate(5);

                return new Result(true,
                        String.format("You picked up %s\nThis plant can grow again! Wait please!", plant.getNameOfProduct()));
            }
            else {
                return new Result(false, "The plant hasn't grown again completely!");
            }
        }
        else {
            player.getBackpack().addIngredients(((Crop) plant), 1);
            player.getFarm().getCrops().removeIf(a -> a == plant);
            player.getFarm().getPlaceables().removeIf(a -> a == plant);
            targetTile.setPlaceable(null);
            targetTile.setWalkable(true);
            targetTile.setSymbol('.');
            targetTile.setPlowed(false);
            targetTile.setFertilizer(null);
            player.getAbility().increaseFarmingRate(5);
            return new Result(true,
                    String.format("You picked up %s\nThis plant cannot grow again!", plant.getNameOfProduct()));
        }
    }

    public void foragingRandom() {
        //this method should call everyday
        //TODO find an empty tile
        if (Math.random() <= 0.01) {
            ArrayList<ForagingCrop> foragingCrops = ForagingCrop.getCropsBySeason(App.getGame().getTime().getSeason());
            ForagingCrop foragingCrop = foragingCrops.get(new Random().nextInt(foragingCrops.size()));
            //TODO put it in backpack and in map
        }
    }

    public Result plant(InventoryItem itemSeed, Tile targetTile) {
        if (targetTile == null)
            return new Result(false, "Selected tile is null!");

        Player player = App.getGame().getCurrentPlayingPlayer();
        Tile myTile = App.getGame().getMap().findTile(player.getPosition());

        if (!myTile.isAroundMe(targetTile))
            return new Result(false, "Selected Tile is not near you! ");


        if (targetTile.getPlaceable() != null) {
            return new Result(false, "You cannot plant in this tile! it isn't free.");
        }
        if (!targetTile.isPlowed()) {
            return new Result(false, "Tile is not plowed!");
        }


        if (itemSeed instanceof Seeds seed) {
            if (!player.getBackpack().getIngredientQuantity().containsKey(seed))
                return new Result(false, "You don't have this seed!");

            if (!seed.getSeason().equals(Season.Special) &&
                    !seed.getSeason().equals(App.getGame().getTime().getSeason()))
                return new Result(false, "You can't plant this seed in this season!");

            player.getBackpack().removeIngredients(seed, 1);
            CropType cropType = plantCrop(seed);
            Crop crop = new Crop(cropType, App.getGame().getTime(), targetTile.getFertilizer(),
                    targetTile.getPosition().getX(), targetTile.getPosition().getY());
            player.getFarm().getCrops().add(crop);
            player.getFarm().getPlaceables().add(crop);
            targetTile.setPlaceable(crop);
            targetTile.setWalkable(false);
            targetTile.setSymbol(crop.getSymbol());
            player.getAbility().increaseFarmingRate(5);
            return new Result(true, "You plant <" + crop.getType() + "> successfully!");

        }
        else if (itemSeed instanceof TreeSource treeSource) {
            if (!player.getBackpack().getIngredientQuantity().containsKey(treeSource))
                return new Result(false, "You don't have this seed!");

            player.getBackpack().removeIngredients(treeSource, 1);
            Tree tree = new Tree(treeSource.getTreeType(), App.getGame().getTime(), targetTile.getFertilizer(),
                    targetTile.getPosition().getX(), targetTile.getPosition().getY(), 1, 1);
            player.getFarm().getTrees().add(tree);
            player.getFarm().getPlaceables().add(tree);
            targetTile.setPlaceable(tree);
            targetTile.setWalkable(false);
            targetTile.setSymbol(tree.getSymbol());
            player.getAbility().increaseFarmingRate(5);
            return new Result(true, "You successfully plant.");

        }
        else {
            return new Result(false, "Tree_source/seed not found");
        }
    }

    private CropType plantCrop(Seeds seed) {
        CropType cropType;

        if (seed.equals(Seeds.MixedSeeds)) {
            ArrayList<CropType> possibleCrops = MixedSeeds.getSeasonCrops(App.getGame().getTime().getSeason());
            cropType = possibleCrops.get(new Random().nextInt(possibleCrops.size()));
        }
        else
            cropType = seed.getCrop();

        return cropType;
    }

    public Result waterPlantWithUseTool(Growable plant) {
        plant.watering();
        return new Result(true, "You water this plant successfully!");
    }

    public Result fertilize(Fertilizer fertilizer, Tile targetTile) {
        if (targetTile == null)
            return new Result(false, "Selected tile is null!");

        Player player = App.getGame().getCurrentPlayingPlayer();
        Tile myTile = App.getGame().getMap().findTile(player.getPosition());

        if (!myTile.isAroundMe(targetTile))
            return new Result(false, "Selected Tile is not near you! ");

        if (targetTile.getPlaceable() != null)
            return new Result(false, "You cannot fertilize this tile! it isn't free.");
        if (!targetTile.isPlowed())
            return new Result(false, "This tile hasn't plowed yet!");

        if (!player.getBackpack().getIngredientQuantity().containsKey(fertilizer)) {
            return new Result(false, "You don't have this fertilizer in the backpack!");
        }

        player.getBackpack().removeIngredients(fertilizer, 1);
        targetTile.setFertilizer(fertilizer);
        return new Result(true, "You fertilize this tile successfully!");
    }

    public Result showPlant(Tile tile) {
        if (tile == null)
            return new Result(false, "Tile not found!");

        Placeable content = tile.getPlaceable();
        if (!(content instanceof Growable plant))
            return new Result(false, "Here is no Plant!");

        return new Result(true,
                        String.format("Name:              %s\n", plant.getName()) +
                        String.format("Days to complete:  %d\n", plant.getNumberOfDaysToComplete()) +
                        String.format("Current stage:     %d\n", plant.getCurrentStage()) +
                        String.format("Has Watered Today: %s\n", plant.hasWateredToday()) +
                        String.format("Has Fertilized:    %s\n", plant.hasFertilized()) +
                        String.format("Fertilizer:        %s\n", plant.getFertilizer() != null ? plant.getFertilizer() : "No"));
    }
}
