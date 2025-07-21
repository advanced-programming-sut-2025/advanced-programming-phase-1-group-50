package com.stardew.controller.AbilityAndEnergyController;

import com.stardew.models.Result;
import com.stardew.models.app.App;

public class EnergyController {
    public Result showEnergy(){
        if(App.getGame().getCurrentPlayingPlayer().isInfinite()){
            return new Result(false, "your energy is infinite");
        }
        return new Result(true , "your Energy : " + App.getGame().getCurrentPlayingPlayer()
                .getEnergy());
    }
    public Result setEnergy(int energy){
        App.getGame().getCurrentPlayingPlayer().setEnergy(energy);
        return new Result(true , "your Energy : " + App.getGame().getCurrentPlayingPlayer()
                .getEnergy());
    }
    public Result setUnlimitedEnergy(){
        App.getGame().getCurrentPlayingPlayer().setEnergyInfinite();
        return new Result(true , "your Energy is infinite now!");
    }
}
