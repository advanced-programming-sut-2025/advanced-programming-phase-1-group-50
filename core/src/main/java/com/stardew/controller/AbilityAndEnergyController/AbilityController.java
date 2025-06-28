package com.stardew.controller.AbilityAndEnergyController;

import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.Ability;

public class AbilityController {
    public Result showFarmingAbility() {
        Ability ability = App.getGame().getCurrentPlayingPlayer().getAbility();
        return new Result(true , String.format("your farming ability details is: \nLevel: %d \nRate: %d"
                , ability.getFarmingLevel(), ability.getFarmingRate()));
    }

    public Result showForagingAbility() {
        Ability ability = App.getGame().getCurrentPlayingPlayer().getAbility();
        return new Result(true , String.format("your foraging ability details is: \nLevel: %d \nRate: %d"
                , ability.getForagingLevel(), ability.getForagingRate()));
    }

    public Result showMiningAbility() {
        Ability ability = App.getGame().getCurrentPlayingPlayer().getAbility();
        return new Result(true , String.format("your farming ability details is: \nLevel: %d \nRate: %d"
                , ability.getMiningLevel(), ability.getMiningRate()));
    }

    public Result showFishingAbility() {
        Ability ability = App.getGame().getCurrentPlayingPlayer().getAbility();
        return new Result(true , String.format("your farming ability details is: \nLevel: %d \nRate: %d"
                , ability.getFishingLevel(), ability.getFishingRate()));
    }
}
