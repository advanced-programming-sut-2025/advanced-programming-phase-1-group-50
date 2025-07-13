package com.stardew.controller.CookingAndCraftingControllers;

import com.stardew.models.Result;
import com.stardew.models.app.App;
import com.stardew.models.manuFactor.ArtisanMachine;
import com.stardew.models.manuFactor.artisanGoods.ArtisanGood;
import com.stardew.models.userInfo.Player;

public class ArtisanController {

    public Result artisanUse(String artisanName, String itemName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByName(artisanName);

        if (artisanMachine == null)
            return new Result(false, "Artisan Machine not found!");

        Result result = artisanMachine.canUse(player, itemName);
        if (result.getSuccessful()) {
            artisanMachine.use();
        }
        return result;
    }

    public Result artisanGet(String artisanName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByName(artisanName);

        if (artisanMachine == null)
            return new Result(false, "Artisan Machine not found!");

        Result result = artisanMachine.isReady();
        if (result.getSuccessful()) {
            ArtisanGood artisanGood = artisanMachine.get();
            player.getBackpack().addIngredients(artisanGood, 1);
            artisanMachine.reset();
            return new Result(true, "You got <"+ artisanGood +"> good successfully!");
        }
        return result;
    }

    public Result cancelProcess(String artisanName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByName(artisanName);

        if (artisanMachine == null)
            return new Result(false, "Artisan Machine not found!");

        if (!artisanMachine.isAnyProducing())
            return new Result(false, "Artisan Machine is not producing!");

        artisanMachine.reset();
        return new Result(true, "You cancelled the Artisan machine process!");
    }

    public boolean isReadyProduct(String artisanName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByName(artisanName);

        if (artisanMachine == null)
            return false;

        Result result = artisanMachine.isReady();
        return result.getSuccessful();
    }

    public void cheatFinishProcess(String artisanName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByName(artisanName);

        if (artisanMachine == null)
            return;

        artisanMachine.setCheatReady(true);
    }

}
