package controller.AbilityAndEnergyController;

import models.Result;
import models.app.App;

public class EnergyController {
    public Result showEnergy(){
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
