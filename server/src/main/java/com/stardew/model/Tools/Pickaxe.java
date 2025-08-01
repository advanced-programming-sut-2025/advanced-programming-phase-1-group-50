package com.stardew.model.Tools;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.App;
import com.stardew.model.gameApp.date.Weather;
import com.stardew.model.userInfo.Ability;

import java.util.UUID;

public class Pickaxe extends Tool {
    private ToolType type = ToolType.Primary;
    private TextureID texture = TextureID.pickaxeTexture;
    private final String id ;

    public Pickaxe() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool() {
//        Weather weather = App.getGame().getTime().getWeather();
//        int multiple = switch (weather) {
//            case Rainy -> 2;
//            case Snowy -> 3;
//            default -> 1;
//        };
//        int consumedEnergy;
//        if (App.getGame().getCurrentPlayingPlayer().getAbility().getMiningLevel() == Ability.getMaxLevel()) {
//            consumedEnergy = switch (type) {
//                case Primary -> 4 * multiple;
//                case Coppery -> 3 * multiple;
//                case Metal -> 2 * multiple;
//                case Golden -> 1;
//                default -> 0;
//            };
//        } else {
//            consumedEnergy = switch (type) {
//                case Primary -> 5 * multiple;
//                case Coppery -> 4 * multiple;
//                case Metal -> 3 * multiple;
//                case Golden -> 2 * multiple;
//                case Iridium -> 1;
//                default -> 0;
//            };
//        }
//
//        Result energyConsumptionResult = App.getGame().getCurrentPlayingPlayer().consumeEnergy(consumedEnergy);
//        if (!energyConsumptionResult.getSuccessful())
//            return energyConsumptionResult;
//
//        return new Result(true, "");
        return null;
    }

    public void upgradeTool() {
        if (this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
            texture = TextureID.copperPickaxeTexture;
        } else if (this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
            texture = TextureID.steelPickaxeTexture;
        } else if (this.type == ToolType.Metal) {

            this.type = ToolType.Golden;
            texture = TextureID.goldPickaxeTexture;
        } else if (this.type == ToolType.Golden) {
            this.type = ToolType.Iridium;
            texture = TextureID.iridiumPickaxeTexture;
        }
    }

    public ToolType getToolType() {
        return type;
    }

    public PoleType getPoleType() {
        return null;
    }

    @Override
    public TextureID getInventoryTexture() {
        return texture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , true , 1 , ItemInventoryType.pickaxe , id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Pickaxe " + type.name();
    }
}
