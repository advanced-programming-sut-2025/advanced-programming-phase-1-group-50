package com.stardew.controller;

import com.stardew.model.Result;
import com.stardew.model.userInfo.Player;

public class EnergyController {
    public Result setEnergy(int energy , Player player) {
        player.setEnergy(energy);
        return new Result(true , "your Energy : " + player
            .getEnergy());
    }

    public Result setUnlimitedEnergy(Player player) {
        player.setEnergyInfinite();
        return new Result(true , "your Energy is infinite now!");
    }
}
