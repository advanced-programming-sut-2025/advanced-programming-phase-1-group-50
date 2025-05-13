package models.tools;

import models.app.App;
import models.date.Weather;
import models.userInfo.Ability;

public class WateringCan extends Tool {
    private ToolType type = ToolType.Primary;
    private int capacity = 40;
    private int waterCapacity = capacity;
    public void upgradeTool() {
        if (this.type == ToolType.Primary) {
            this.type = ToolType.Coppery;
            this.capacity = 55;
        } else if (this.type == ToolType.Coppery) {
            this.type = ToolType.Metal;
            this.capacity = 70;
        } else if (this.type == ToolType.Metal) {
            this.type = ToolType.Golden;
            this.capacity = 85;
        } else if (this.type == ToolType.Golden) {
            this.type = ToolType.Iridium;
            this.capacity = 100;
        }
    }

    @Override
    public int getConsumptionEnergy() {
        return 0;
    }

    @Override
    public void useTool() {
        Weather weather = App.getGame().getTime().getWeather();
        int multiple = switch (weather) {
            case Rainy -> 2;
            case Snowy -> 3;
            default -> 1;
        };
        int consumedEnergy;
        if(App.getGame().getCurrentPlayingPlayer().getAbility().getFarmingLevel() == Ability.getMaxLevel()){
            consumedEnergy = switch (type) {
                case Primary -> 4 * multiple;
                case Coppery -> 3 * multiple;
                case Metal -> 2 * multiple;
                case Golden -> 1;
                default -> 0;
            };
        }
        else {
            consumedEnergy = switch (type) {
                case Primary -> 5 * multiple;
                case Coppery -> 4 * multiple;
                case Metal -> 3  * multiple;
                case Golden -> 2 * multiple;
                case Iridium -> 1;
                default -> 0;
            };
        }
        App.getGame().getCurrentPlayingPlayer().consumeEnergy(consumedEnergy);
        waterCapacity--;
    }
    public ToolType getToolType() {
        return type;
    }
    public PoleType getPoleType() {
        return null;
    }
    public int getCapacity() {
        return capacity;
    }


    public int getWaterCapacity() {
        return waterCapacity;
    }

    public void makeFull(){
        waterCapacity = capacity;
    }
}
