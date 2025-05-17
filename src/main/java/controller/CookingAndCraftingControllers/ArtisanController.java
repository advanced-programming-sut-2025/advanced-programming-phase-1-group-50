package controller.CookingAndCraftingControllers;

import models.Result;
import models.app.App;
import models.manuFactor.ArtisanMachine;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.userInfo.Player;

public class ArtisanController {

    public Result artisanUse(String artisanName, String itemName) {
        Player player = App.getGame().getCurrentPlayingPlayer();
        ArtisanMachine artisanMachine = player.getBackpack().getArtisanMachineByName(artisanName);

        if (artisanMachine == null)
            return new Result(false, "Artisan Machine not found!");
        if (!App.getGame().getMap().isAroundPlaceable(player, player.getFarm().getCottage()))
            return new Result(false, "You are not around your cottage!");

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

}
