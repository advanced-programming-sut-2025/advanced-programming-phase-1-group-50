package controller.ToolsControllers;

import models.Result;
import models.app.App;
import models.manuFactor.Ingredient;
import models.stores.Blacksmith;
import models.tools.FishingPole;
import models.tools.Tool;
import models.userInfo.Coin;

import java.util.Map;

public class ToolController {
    public Result ToolsEquip(String input) {
        for(Tool t : App.getGame().getCurrentPlayingPlayer().getBackpack().getTools()){
            if(t.getClass().getName().equals(input)){
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
            sb.append(t.getClass().getName());
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


}
