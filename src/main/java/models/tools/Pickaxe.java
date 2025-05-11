package models.tools;

import models.app.App;
import models.userInfo.Ability;

public class Pickaxe extends Tool {
    private ToolType type = ToolType.Primary;

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public  void useTool() {
        int consumedEnergy;
        if(App.getGame().getCurrentPlayingPlayer().getAbility().getMiningLevel() == Ability.getMaxLevel()){
            consumedEnergy = switch (type) {
                case Primary -> 4;
                case Coppery -> 3;
                case Metal -> 2;
                case Golden -> 1;
                default -> 0;
            };
        }
        else {
            consumedEnergy = switch (type) {
                case Primary -> 5;
                case Coppery -> 4;
                case Metal -> 3;
                case Golden -> 2;
                case Iridium -> 1;
                default -> 0;
            };
        }
        App.getGame().getCurrentPlayingPlayer().consumeEnergy(consumedEnergy);
    }

    public void upgradeTool() {
        if (this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
        } else if (this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
        } else if (this.type == ToolType.Metal) {
            this.type = ToolType.Golden;
        } else if (this.type == ToolType.Golden) {
            this.type = ToolType.Iridium;
        }
    }
    public ToolType getToolType() {
        return type;
    }
    public PoleType getPoleType() {
        return null;
    }
}
