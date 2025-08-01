package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.date.Season;
import com.stardew.model.mapInfo.InventoryItem;
import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.mapInfo.foraging.*;
import com.stardew.model.userInfo.Player;
import com.stardew.model.mapInfo.Tile;

import java.util.ArrayList;
import java.util.Random;

public class ForagingController {


    public Result harvestWithScythe(Growable plant, Tile targetTile , Player player , Season season) {


        if (!plant.isComplete())
            return new Result(false, "Plant hasn't grown completely!");

        if (plant.canGrowAgain()) {
            if (plant.isCompleteAgain()) {
                if (plant instanceof Crop crop)
                    player.getBackpack().addIngredients(crop, new Random().nextInt(2) + 2);
                else if (plant instanceof Tree tree) {
                    if (tree.getType().getSeason() != Season.Special &&
                        tree.getType().getSeason() != season)
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

//    public void foragingRandom(Sea) {
//        //this method should call everyday
//        //TODO find an empty tile
//        if (Math.random() <= 0.01) {
//            ArrayList<ForagingCrop> foragingCrops = ForagingCrop.getCropsBySeason(App.getGame().getTime().getSeason());
//            ForagingCrop foragingCrop = foragingCrops.get(new Random().nextInt(foragingCrops.size()));
//            //TODO put it in backpack and in map
//        }
//    }

    public Result plant(InventoryItem itemSeed, Tile targetTile , Player player , TimeProvider timeProvider ) {
        if (targetTile == null)
            return new Result(false, "Selected tile is null!");

//        Player player = App.getGame().getCurrentPlayingPlayer();
//        Tile myTile = App.getGame().getMap().findTile(player.getPosition());
//
//        if (!myTile.isAroundMe(targetTile))
//            return new Result(false, "Selected Tile is not near you! ");


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
                !seed.getSeason().equals(timeProvider.getTime().getSeason()))
                return new Result(false, "You can't plant this seed in this season!");

            player.getBackpack().removeIngredients(seed, 1);
            CropType cropType = plantCrop(seed , timeProvider);
            Crop crop = new Crop(cropType, timeProvider, targetTile.getFertilizer(),
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
            Tree tree = new Tree(treeSource.getTreeType(), timeProvider, targetTile.getFertilizer(),
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

    private CropType plantCrop(Seeds seed , TimeProvider timeProvider ) {
        CropType cropType;

        if (seed.equals(Seeds.MixedSeeds)) {
            ArrayList<CropType> possibleCrops = MixedSeeds.getSeasonCrops(timeProvider.getTime().getSeason());
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

    public Result fertilize(Fertilizer fertilizer, Tile targetTile , Player player) {
        if (targetTile == null)
            return new Result(false, "Selected tile is null!");

//        Player player = App.getGame().getCurrentPlayingPlayer();
//        Tile myTile = App.getGame().getMap().findTile(player.getPosition());
//
//        if (!myTile.isAroundMe(targetTile))
//            return new Result(false, "Selected Tile is not near you! ");

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
