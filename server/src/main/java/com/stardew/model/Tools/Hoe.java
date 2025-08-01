package com.stardew.model.Tools;

import com.stardew.model.InventoryItemDTO;
import com.stardew.model.ItemInventoryType;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Weather;
import com.stardew.model.userInfo.Player;

import java.util.UUID;

public class Hoe extends Tool {
    private ToolType type = ToolType.Primary;
    private TextureID hoeTexture = TextureID.hoeTexture;
    private String id;
    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    public Hoe() {
        id= UUID.randomUUID().toString();
    }

    @Override
    public Result useTool(Weather weather , Player player) {
        int consumedEnergy = switch (type) {
            case Primary -> 5;
            case Coppery -> 4;
            case Metal -> 3;
            case Golden -> 2;
            case Iridium -> 1;
            default -> 0;
        };

        Result energyConsumptionResult = player.consumeEnergy(consumedEnergy);
        if (!energyConsumptionResult.getSuccessful())
            return energyConsumptionResult;

        return new Result(true, "");
    }

    public void upgradeTool() {
        if(this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
            this.hoeTexture = TextureID.copperHoeTexture;
        }
        else if(this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
            this.hoeTexture = TextureID.steelHoeTexture;
        }
        else if(this.type == ToolType.Metal ){
            this.type = ToolType.Golden;
            this.hoeTexture = TextureID.goldHoeTexture;
        }
        else if(this.type == ToolType.Golden){
            this.type = ToolType.Iridium;
            this.hoeTexture = TextureID.iridiumHoeTexture;
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
        return hoeTexture;
    }

    @Override
    public InventoryItemDTO toDTO() {
        return new InventoryItemDTO(getInventoryTexture() , true , 1 , ItemInventoryType.hoe , id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Hoe " + type.name();
    }
}
