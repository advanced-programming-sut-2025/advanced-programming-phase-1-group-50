package com.stardew.controller.CookingCraftingControllers;

import com.stardew.model.PlaceableDTO;
import com.stardew.model.TileDTO;
import com.stardew.model.mapInfo.Farm;
import com.stardew.model.mapInfo.GameMap;
import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.mapInfo.Tile;
import com.stardew.model.recipes.CookingRecipe;
import com.stardew.model.recipes.CraftingRecipes;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CookingCraftingInfoController {
    private static CookingCraftingInfoController instance;

    private CookingCraftingInfoController() {}

    public static synchronized CookingCraftingInfoController getInstance() {
        if (instance == null) {
            instance = new CookingCraftingInfoController();
        }
        return instance;
    }

    public void handleGetInfo(Message message, Player player, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        String cookingOrCrafting = message.getFromBody("cookingOrCrafting");
        if (cookingOrCrafting == null) return;

        HashMap<String, String> descriptions = new HashMap<>();
        HashSet<String> ownRecipes = new HashSet<>();
        if (cookingOrCrafting.equalsIgnoreCase("cooking")) {
            handleCookingInfo(descriptions, ownRecipes, player);
        } else if (cookingOrCrafting.equalsIgnoreCase("crafting")) {
            handleCraftingInfo(descriptions, ownRecipes, player);
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("descriptions", descriptions);
        body.put("ownRecipes", ownRecipes);
        Message response = new Message(body, MessageType.GET_COOKING_CRAFTING_INFO_RESULT);
        response.setRequestID(message.getRequestID());
        connection.sendMessage(response);
    }

    private void handleCookingInfo(HashMap<String, String> descriptions, HashSet<String> ownRecipes, Player player) {
        for (CookingRecipe recipe : CookingRecipe.values()) {
            descriptions.put(recipe.name(), CookingRecipe.getDescription(recipe));
        }
        for (CookingRecipe recipe : player.getBackpack().getCookingRecipes()) {
            ownRecipes.add(recipe.name());
        }
    }

    private void handleCraftingInfo(HashMap<String, String> descriptions, HashSet<String> ownRecipes, Player player) {
        for (CraftingRecipes recipe : CraftingRecipes.values()) {
            descriptions.put(recipe.name(), CraftingRecipes.getDescription(recipe));
        }
        for (CraftingRecipes recipe : player.getBackpack().getCraftingRecipes()) {
            ownRecipes.add(recipe.name());
        }
    }



    public void handleGetFarmInfo(Message message, Player player, GameMap map, ClientConnectionThread connection) {
        if (message == null) return;
        if (player == null) return;

        int farmStartX = GameMap.getFarmStartX(player.getIndexOfFarmRegion());
        int farmStartY = GameMap.getFarmStartY(player.getIndexOfFarmRegion());

        ArrayList<TileDTO> tiles = new ArrayList<>();
        ArrayList<PlaceableDTO> placeables = new ArrayList<>();

        for (int i = farmStartX; i < farmStartX + Farm.WIDTH; i++) {
            for (int j = farmStartY; j < farmStartY + Farm.HEIGHT; j++) {
                Tile tile = map.findTile(i, j);
                if (tile == null) continue;
                tiles.add(tile.toDTO());
                Placeable placeable = tile.getPlaceable();
                if (placeable != null)
                    placeables.add(new PlaceableDTO(i, j, 1, 1, placeable.getTexture()));
            }
        }

        HashMap<String, Object> body = new HashMap<>();
        body.put("tiles", tiles);
        body.put("placeables", placeables);

        Message response = new Message(body, MessageType.EVENT_IN_GAME_RESULT);
        response.setRequestID(message.getRequestID());
        connection.sendMessage(response);
    }
}
