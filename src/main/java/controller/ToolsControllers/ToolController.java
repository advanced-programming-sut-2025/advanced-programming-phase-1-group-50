package controller.ToolsControllers;

import controller.AnimalsControllers.AnimalsController;
import controller.ForagingControllers.ForagingController;
import models.Placeable;
import models.Result;
import models.app.App;
import models.foraging.Crop;
import models.foraging.ForagingMineral;
import models.foraging.Tree;
import models.manuFactor.Ingredient;
import models.mapInfo.*;
import models.stores.Blacksmith;
import models.tools.*;
import models.userInfo.Coin;
import models.userInfo.Player;
import models.userInfo.TrashCan;
import models.waterBodies.Lake;

import java.util.Map;
import java.util.Random;

public class ToolController {
    private final AnimalsController animalsController = new AnimalsController();
    private final ForagingController foragingController = new ForagingController();
    public boolean haveEnoughCoins(int price , Player player) {
        int coinValue = player.getBackpack().getIngredientQuantity().getOrDefault(new Coin() , 0);
        return coinValue >= price;
    }
    public void minusCoinForUpgradeTool(int price , Player player) {
        Coin coin = new Coin();
        int value = player.getBackpack().getIngredientQuantity().getOrDefault(coin , 0);
        player.getBackpack().getIngredientQuantity().put(coin, value - price);
    }
    public Result ToolsEquip(String input) {
        for(Tool t : App.getGame().getCurrentPlayingPlayer().getBackpack().getTools()){
            if(t.getClass().getSimpleName().equals(input)){
                App.getGame().getCurrentPlayingPlayer().setCurrentTool(t);
                return new Result(true,"Tool equipped");
            }
        }
        return new Result(false,"Tool not found");
    }
    public Result showCurrentTool(){
        return new Result(true,"Current Tool : " + App.getGame().getCurrentPlayingPlayer()
                .getCurrentTool().getClass().getSimpleName());
    }
    public Result showAvailableTool(){
        StringBuilder sb = new StringBuilder();
        for(Tool t : App.getGame().getCurrentPlayingPlayer().getBackpack().getTools()){
            sb.append(t.getClass().getSimpleName());
            sb.append("\n");

        }
        return new Result(true,"Available Tools : " + sb);
    }
    public Result upgradeTool(String input) {
        var player = App.getGame().getCurrentPlayingPlayer();
        var tools = player.getBackpack().getTools();
        var ingredients = player.getBackpack().getIngredientQuantity();
        int price;
        if(input.equals("TrashCan")){
            TrashCan trashCan = player.getBackpack().getTrashCan();
            price = trashCan.getPriceForUpgrade();
            if(haveEnoughCoins(price, player)){
                trashCan.upgradeTool();
                minusCoinForUpgradeTool(price, player);
                return new Result(true,"Tool upgraded");
            }
            else {
                return new Result(false,"you don't have enough coins");
            }
        }
        for(Tool t : tools){
            if(t.getClass().getSimpleName().equals(input)){

                if(t instanceof FishingPole){
                    if(App.isAroundPlaceable(player , App.getGame().getMap().getNpcVillage().getFishShop())){
                        price = t.getPoleType().getPriceForUpgrade();
                        if(haveEnoughCoins(price, player)){
                            t.upgradeTool();
                            minusCoinForUpgradeTool(price, player);
                            return new Result(true,"Tool upgraded");
                        }
                        else {
                            return new Result(false,"you don't have enough coins");
                        }
                    }
                    else {
                        return new Result(false,"you should be near fish shop");
                    }
                }

                else {
                    if(App.isAroundPlaceable(player , App.getGame().getMap().getNpcVillage().getBlacksmith())){
                        price = t.getToolType().getPriceForUpgrade();
                        if(t.getToolType() != null){
                            if(haveEnoughCoins(price, player)){
                                t.upgradeTool();
                                minusCoinForUpgradeTool(price, player);
                                return new Result(true,"Tool upgraded");
                            }
                            else {
                                return new Result(false,"you don't have enough coins");
                            }
                        }
                        else{
                            return new Result(false,"you can't upgrade this tool");
                        }
                    }
                    else{
                        return new Result(false,"you should be near blacksmith");
                    }
                }
            }


        }
        return new Result(false,"Tool not found");


    }
    public Result useTool(String direction){
        Direction d = Direction.getDirectionByInput(direction);
        Player p = App.getGame().getCurrentPlayingPlayer();
        Tile tile = App.getGame().getMap().findTile(p.getPosition());

        if(d == null){
            return new Result(false, "Invalid direction");
        }
        Tile targetTile = App.getGame().getMap().getTileByDirection(tile, d);





        if (App.getGame().getCurrentPlayingPlayer().getCurrentTool() instanceof Hoe hoe) {
            if (App.getGame().getMap().getTileByDirection(tile, d) != null &&
                    App.getGame().getMap().getTileByDirection(tile, d).getPlaceable() == null) {
                App.getGame().getMap().getTileByDirection(tile, d).setPlowed(true);
                hoe.useTool();
                return new Result(true, "tile plowed successfully!");
            }
            else
                return new Result(false, "You can't plow this tile with Hoe!");
        }
        if(App.getGame().getCurrentPlayingPlayer().getCurrentTool() instanceof Pickaxe pickaxe) {
            if(targetTile.getPlaceable() != null){
                if(targetTile.getPlaceable() instanceof Stone stone){
                    targetTile.setPlaceable(null);
                    pickaxe.useTool();
                    p.getFarm().getStones().remove(stone);
                    p.getFarm().getPlaceables().remove(stone);
                    targetTile.setSymbol('.');
                    p.getBackpack().addIngredients(stone , 1);
                    return  new Result(true, "stone broken");


                }
                else if(targetTile.getPlaceable() instanceof Quarry quarry){
                    Random rand = new Random();
                    if(!quarry.getForagingMinerals().isEmpty()){

                        ForagingMineral fg = quarry.getForagingMinerals()
                                .get(rand.nextInt(quarry.getForagingMinerals().size()));
                        quarry.getForagingMinerals().remove(fg);
                        p.getBackpack().addIngredients(fg, 1);
                        pickaxe.useTool();
                        return new Result(true, "you add a foraging mineral to the backpack");


                    }
                    return new Result(false, "this quarry is empty");
                }

            }
            else {
                targetTile.setPlowed(false);

                pickaxe.useTool();
                return new Result(true , "target tile unPlowed successfully!");
            }
        }
        if(App.getGame().getCurrentPlayingPlayer().getCurrentTool() instanceof Axe axe){
            if(targetTile.getPlaceable() != null){
                if(targetTile.getPlaceable() instanceof Tree tree){
                    targetTile.setPlaceable(null);
                    axe.useTool();
                    p.getFarm().getTrees().remove(tree);
                    p.getFarm().getPlaceables().remove(tree);
                    targetTile.setSymbol('.');
                    int numberOfWoods = tree.getCurrentStage();
                    if(numberOfWoods > 0){
                        Wood wood  = new Wood();
                        p.getBackpack().addIngredients(wood,numberOfWoods);
                    }
                    p.getBackpack().addIngredients(tree.getType().getSource() , 2);
                }
            }
            return new Result(false, "there is no tree for cut!");
        }
        if(App.getGame().getCurrentPlayingPlayer().getCurrentTool() instanceof FishingPole fishingPole){
            if(targetTile.getPlaceable() != null){
                if(targetTile.getPlaceable() instanceof Lake lake){
                    fishingPole.useTool();
                    return animalsController.fishing(fishingPole);
                }
            }
            return new Result(false, "there is no lake for fishing!");
        }
        if(App.getGame().getCurrentPlayingPlayer().getCurrentTool() instanceof Scythe scythe){
            if(targetTile.getPlaceable() != null){
                if(targetTile.getPlaceable() instanceof Crop crop){
                    scythe.useTool();
                    return foragingController.harvestWithScythe(crop , targetTile);
                }
                else if(targetTile.getPlaceable() instanceof Tree tree){
                    scythe.useTool();
                    return foragingController.harvestWithScythe(tree , targetTile);
                }
            }
            return new Result(false, "there is no tree or crop fo harvest!");
        }

        return new Result(false, "you don't have a tool , please set your current tool");

    }


}
