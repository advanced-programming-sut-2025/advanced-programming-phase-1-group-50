package com.stardew.model.Tools;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Weather;
import com.stardew.model.userInfo.Ability;
import com.stardew.model.userInfo.Player;

import java.util.UUID;

public class Axe extends Tool{
    private ToolType type = ToolType.Primary;
    private TextureID texture = TextureID.axeTexture;
    private final String id;

    public void upgradeTool() {
        if (this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
            texture = TextureID.copperAxeTexture;
        } else if (this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
            texture = TextureID.steelAxeTexture;
        } else if (this.type == ToolType.Metal) {
            this.type = ToolType.Golden;
            texture = TextureID.goldAxeTexture;
        } else if (this.type == ToolType.Golden) {

            this.type = ToolType.Iridium;
            texture = TextureID.iridiumAxeTexture;
        }
    }

    public Axe(){
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public Result useTool(Weather weather , Player player) {

        int multiple = switch (weather) {
            case Rainy -> 2;
            case Snowy -> 3;
            default -> 1;
        };
        int consumedEnergy;
        if (player.getAbility().getFarmingLevel() == Ability.getMaxLevel()) {
            consumedEnergy = switch (type) {
                case Primary -> 4 * multiple;
                case Coppery -> 3 * multiple;
                case Metal -> 2 * multiple;
                case Golden -> 1;
                default -> 0;
            };
        } else {
            consumedEnergy = switch (type) {
                case Primary -> 5 * multiple;
                case Coppery -> 4 * multiple;
                case Metal -> 3 * multiple;
                case Golden -> 2 * multiple;
                case Iridium -> 1;
                default -> 0;
            };
        }

        Result energyConsumptionResult = player.consumeEnergy(consumedEnergy);
        if (!energyConsumptionResult.getSuccessful())
            return energyConsumptionResult;

        return new Result(true, "");


   }

    public ToolType getToolType() {
        return type;
    }

    public PoleType getPoleType() {
        return null;
    }

    @Override
    public TextureID getInventoryTexture() {
        //type.getTextureRegion : Todo
        return texture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , true , 1 , ItemInventoryType.axe , id);
    }

    @Override
    public String getId() {
        return id;
    }

    public String toString(){
        return "Axe " + type.name();
    }

}
