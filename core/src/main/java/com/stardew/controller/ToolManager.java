package com.stardew.controller;

import com.stardew.controller.ForagingControllers.ForagingController;
import com.stardew.controller.ToolsControllers.ToolController;
import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.foraging.Growable;
import com.stardew.models.foraging.Tree;
import com.stardew.models.mapInfo.Stone;
import com.stardew.models.mapInfo.Tile;
import com.stardew.models.mapInfo.Wood;
import com.stardew.models.tools.*;
import com.stardew.models.userInfo.Player;
import com.stardew.view.GameMenuInputAdapter;

import java.util.Random;

public class ToolManager {
    private Player player = App.getGame().getCurrentPlayingPlayer();
    private Tile[][] tiles = App.getGame().getMap().getTiles();
    private GameMenuInputAdapter adapter;
    private final ForagingController foragingController = new ForagingController();

    public void setAdapter(GameMenuInputAdapter adapter) {
        this.adapter = adapter;
    }


    public void useTool(){
        Tile currentTile = tiles[adapter.getIndexTileX()][adapter.getIndexTileY()];
        if(player.getCurrentInventoryItem() instanceof Tool ){

            if(player.getCurrentInventoryItem() instanceof Hoe hoe){
                if(currentTile != null && currentTile.getPlaceable() == null){

                    Result energyConsumptionResult = hoe.useTool();
                    if(energyConsumptionResult.getSuccessful()){
                        currentTile.setPlowed(true);
                    }
                }
            }

            else if(player.getCurrentInventoryItem() instanceof Pickaxe pickaxe){
                Result energyConsumptionResult = pickaxe.useTool();
                if(energyConsumptionResult.getSuccessful()){
                    if(currentTile.getPlaceable() != null ) {
                        if (currentTile.getPlaceable() instanceof Stone stone) {
                            currentTile.setPlaceable(null);
                            currentTile.setWalkable(true);
                            player.getFarm().getStones().remove(stone);
                            player.getFarm().getPlaceables().remove(stone);
                            player.getBackpack().addIngredients(stone, 1);
                            player.getAbility().increaseMiningRate(10);
                        }
                    }
                    else{
                        currentTile.setPlowed(false);
                    }
                }
            }

            else if(player.getCurrentInventoryItem() instanceof Axe axe){
                if(currentTile.getPlaceable() != null && currentTile.getPlaceable() instanceof Tree tree){
                    Result energyConsumptionResult = axe.useTool();
                    if(energyConsumptionResult.getSuccessful()){
                        currentTile.setWalkable(true);
                        currentTile.setPlaceable(null);
                        player.getFarm().getTrees().remove(tree);
                        player.getFarm().getPlaceables().remove(tree);
                        currentTile.setPlowed(false);
                        currentTile.setFertilizer(null);

                        int numberOfWoods = tree.getCurrentStage();
                        if (numberOfWoods > 0) {
                            Wood wood = new Wood();
                            player.getBackpack().addIngredients(wood, numberOfWoods);
                        }
                        int numberOfSeeds = new Random().nextInt(2) + 1;
                        player.getBackpack().addIngredients(tree.getType().getSource(), numberOfSeeds);
                    }
                }

            }
            else if(player.getCurrentInventoryItem() instanceof Scythe scythe){
                if(currentTile.getPlaceable() instanceof Growable plant){
                    Result energyConsumptionResult = scythe.useTool();
                    if(energyConsumptionResult.getSuccessful()){
                        player.getAbility().increaseForagingRate(10);
                        foragingController.harvestWithScythe(plant , currentTile );
                    }
                }
            }
        }
    }

}
