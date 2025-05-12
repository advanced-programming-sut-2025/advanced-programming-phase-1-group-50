package controller.ToolsControllers;

import models.Placeable;
import models.Result;
import models.app.App;
import models.foraging.ForagingMineral;
import models.manuFactor.Ingredient;
import models.mapInfo.Direction;
import models.mapInfo.Quarry;
import models.mapInfo.Stone;
import models.mapInfo.Tile;
import models.stores.Blacksmith;
import models.tools.FishingPole;
import models.tools.Hoe;
import models.tools.Pickaxe;
import models.tools.Tool;
import models.userInfo.Coin;
import models.userInfo.Player;

import java.util.Map;
import java.util.Random;

public class ToolController {
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

        if (!App.isAroundPlaceable(player, App.getGame().getMap().getNpcVillage().getBlacksmith())) {
            return new Result(false, "You are not allowed to upgrade this tool");
        }

        for (Tool t : tools) {
            if (t.getClass().getSimpleName().equals(input)) {
                int price = 0;
                if (!(t instanceof FishingPole)) {
                    if (t.getToolType() == null) return new Result(false, "Tool type is null");
                    price = t.getToolType().getPriceForUpgrade();
                } else {

                    price = t.getPoleType().getPriceForUpgrade();
                }

                for (Map.Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
                    if (entry.getKey() instanceof Coin) {
                        if (entry.getValue() >= price) {
                            t.upgradeTool();
                            player.getBackpack().getIngredientQuantity().put(entry.getKey(), entry.getValue() - price);
                            return new Result(true, t instanceof FishingPole ? "Fishing pole upgraded" : "Tool upgraded");
                        } else {
                            return new Result(false, t instanceof FishingPole ?
                                    "Fishing pole upgrade failed, not enough coins" :
                                    "Tool upgrade failed, not enough coins");
                        }
                    }
                }

                return new Result(false, "You don't have any coins");
            }
        }

        return new Result(false, "This tool is not available");
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

        return new Result(false, "you don't have a tool , please set your current tool");

    }


}
