package models.tools;

import models.app.App;
import models.date.Weather;
import models.userInfo.Ability;

public class FishingPole extends Tool {

    private final PoleType type;

    public FishingPole(PoleType poleType) {
        this.type = poleType;
    }

    @Override
    public  int getConsumptionEnergy() {
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
                case Training, Bamboo -> 7 * multiple;
                case Fiberglass -> 5 * multiple;
                case Iridium -> 3 * multiple;
            };
        }
        else {
            consumedEnergy = switch (type) {
                case Training, Bamboo -> 8 * multiple;
                case Fiberglass -> 6  * multiple;
                case Iridium -> 4 * multiple;


            };
        }
        App.getGame().getCurrentPlayingPlayer().consumeEnergy(consumedEnergy);
    }

    @Override
    public void upgradeTool() {}

    public PoleType getType() {
        return type;
    }

    public PoleType getPoleType() {
        return type;
    }
    public ToolType getToolType() {
        return null;
    }
}
