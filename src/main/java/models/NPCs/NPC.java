package models.NPCs;

import models.animals.AnimalGood;
import models.animals.AnimalGoodType;
import models.app.App;
import models.date.Season;
import models.date.Weather;
import models.foraging.ForagingCrop;
import models.foraging.ForagingMineral;
import models.manuFactor.Ingredient;
import models.manuFactor.artisanGoods.ArtisanGood;
import models.manuFactor.artisanGoods.ArtisanGoodType;
import models.mapInfo.Stone;
import models.mapInfo.Wood;
import models.recipes.CookingRecipe;

public class NPC {

    private final NPCType type;
    private boolean isFirstQuestDone = false;
    private boolean isSecondQuestDone = false;
    private boolean isThirdQuestDone = false;

    public NPC(NPCType type) {
        this.type = type;
    }

    public NPCType getType() {
        return type;
    }

    public boolean isFirstQuestDone() {
        return isFirstQuestDone;
    }

    public void setFirstQuestDone(boolean firstQuestDone) {
        isFirstQuestDone = firstQuestDone;
    }

    public boolean isSecondQuestDone() {
        return isSecondQuestDone;
    }

    public void setSecondQuestDone(boolean secondQuestDone) {
        isSecondQuestDone = secondQuestDone;
    }

    public boolean isThirdQuestDone() {
        return isThirdQuestDone;
    }

    public void setThirdQuestDone(boolean thirdQuestDone) {
        isThirdQuestDone = thirdQuestDone;
    }

    public boolean doFirstQuest(boolean isRewardTwice) {

        if (this.type.equals(NPCType.Abigail)) {

            return AbigailQuests.doFirstQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Sebastian)) {

            return SebastianQuests.doFirstQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Harvey)) {

             return HarveyQuests.doFirstQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Leah)) {

            return LeahQuests.doFirstQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Robin)) {

            return RobinQuests.doFirstQuest(isRewardTwice);

        }
    }
    public boolean doSecondQuest(boolean isRewardTwice) {

        if (this.type.equals(NPCType.Abigail)) {

             return AbigailQuests.doSecondQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Sebastian)) {

            return SebastianQuests.doSecondQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Harvey)) {

            return HarveyQuests.doSecondQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Leah)) {

            return LeahQuests.doSecondQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Robin)) {

            return RobinQuests.doSecondQuest(isRewardTwice);

        }

    }
    public boolean doThirdQuest(boolean isRewardTwice) {

        if (this.type.equals(NPCType.Abigail)) {

            return AbigailQuests.doThirdQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Sebastian)) {

            return SebastianQuests.doThirdQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Harvey)) {

           return HarveyQuests.doThirdQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Leah)) {

            return LeahQuests.doThirdQuest(isRewardTwice);

        } else if (this.type.equals(NPCType.Robin)) {

            return RobinQuests.doThirdQuest(isRewardTwice);

        }

    }

    public boolean isFavoriteGift(Ingredient gift) {

        if (this.type.equals(NPCType.Abigail)) {

            if (gift instanceof Stone) {
                return true;
            }
            if (gift.equals(ForagingMineral.Iron)) {
                return true;
            }
            if (gift instanceof ArtisanGood) {
                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.Coffee);
            }

        } else if (this.type.equals(NPCType.Sebastian)) {
            if (gift instanceof AnimalGood) {
                if (((AnimalGood) gift).getType().equals(AnimalGoodType.Wool)) {
                    return true;
                }
            }
            if (gift.equals(CookingRecipe.PumpkinPie)) {
                return true;
            }
            return gift.equals(CookingRecipe.Pizza);

        } else if (this.type.equals(NPCType.Harvey)) {

            if (gift instanceof ArtisanGood) {
                if (((ArtisanGood) gift).getType().equals(ArtisanGoodType.Coffee)) {
                    return true;
                }
            }
            if (gift instanceof ArtisanGood) {
                if (((ArtisanGood) gift).getType().equals(ArtisanGoodType.Pickles)) {
                    return true;
                }
            }
            if (gift instanceof ArtisanGood) {
                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.Wine);
            }

        } else if (this.type.equals(NPCType.Leah)) {

            if (gift.equals(CookingRecipe.Salad)) {
                return true;
            }
            if (gift.equals(ForagingCrop.Grape)) {
                return true;
            }
            if (gift instanceof ArtisanGood) {
                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.Wine);
            }

        } else if (this.type.equals(NPCType.Robin)) {

            if (gift.equals(CookingRecipe.Spaghetti)) {
                return true;
            }
            if (gift instanceof Wood) {
                return true;
            }
            if (gift instanceof ArtisanGood) {
                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.IronBar);
            }

        }

        return false;
    }

    public String getDialogue(NPCFriendshipLevel level) {

        int index = 6;

        if (App.getGame().getTime().getSeason().equals(Season.Summer) || App.getGame().getTime().getWeather().equals(Weather.Sunny)) {
            index = 0;
        } else if (App.getGame().getTime().getWeather().equals(Weather.Rainy)) {
            index = 1;
        } else if (App.getGame().getTime().getWeather().equals(Weather.Sunny) && App.getGame().getTime().getSeason().equals(Season.Spring)) {
            index = 2;
        } else if (App.getGame().getTime().getSeason().equals(Season.Fall) && App.getGame().getTime().getHour() > 18) {
            index = 3;
        } else if (App.getGame().getTime().getWeather().equals(Weather.Snowy)) {
            index = 4;
        } else if (App.getGame().getTime().getHour() > 18 && level.equals(NPCFriendshipLevel.LevelThree)) {
            index = 5;
        }

        return this.type.getDialogues().get(index);
    }

    public char getSymbol() {
        return this.type.getSymbol();
    }

}