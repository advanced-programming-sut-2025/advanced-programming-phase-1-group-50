package com.stardew.controller;

import com.stardew.model.GrowableDTO;
import com.stardew.model.Result;
import com.stardew.model.gameApp.Game;
import com.stardew.model.mapInfo.GreenHouse;
import com.stardew.model.mapInfo.Wood;
import com.stardew.model.mapInfo.foraging.Growable;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;
import com.stardew.network.ClientConnectionThread;
import com.stardew.network.Message;
import com.stardew.network.MessageType;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildingController {
    private static BuildingController instance;
    private BuildingController() {

    }
    public static BuildingController getInstance() {
        if(instance == null) {
            instance = new BuildingController();
        }
        return instance;
    }

    public void handleBuildGreenhouse(Message msg , ClientConnectionThread clientConnection , Player player) {
        GreenHouse gh = player.getFarm().getGreenHouse();
        Result result;
        if(!gh.isBroken()){
            result = new Result(false , "greenhouse is already build");
        }
        else{
            int coin = player.getBackpack().getIngredientQuantity().getOrDefault(new Coin() , 0);
            int wood = player.getBackpack().getIngredientQuantity().getOrDefault(new Wood() , 0);
            if(coin < 500 || wood < 600){
                result = new Result(false ,"you don't have enough coin or wood");
            }
            else{
                player.getBackpack().removeIngredients(new Coin() , 500);
                player.getBackpack().removeIngredients(new Wood() , 600);
                gh.setBroken(false);
                result = new Result(true , "congratulations! You are now building!");
            }
        }

        HashMap<String , Object> body = new HashMap<>();
        body.put("result", result);
        Message m = new Message(body , MessageType.GREENHOUSE_BUILDING_RESULT);
        m.setRequestID(msg.getRequestID());
        clientConnection.sendMessage(m);
    }


    public void greenHouseGrowableRequest(Message msg , ClientConnectionThread clientConnection , Player player) {
        GreenHouse gh = player.getFarm().getGreenHouse();
        if(gh.isBroken()) return;

        ArrayList<GrowableDTO> dtoS = new ArrayList<>();
        for(Growable g : gh.getGrowables()){
            dtoS.add(new GrowableDTO(g.getNameOfProduct() , g.getCurrentStage()));
        }
        HashMap<String , Object> body = new HashMap<>();
        body.put("DTO", dtoS);
        Message m = new Message(body , MessageType.GREENHOUSE_GROWABLE_RESULT);
        m.setRequestID(msg.getRequestID());
        clientConnection.sendMessage(m);
    }


}
