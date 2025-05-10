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
import models.mapInfo.Position;

public class NPC {

    private FriendshipLevel friendshipLevel;
    private int numericalFriendShipLevel;
    private  final Position position;
    private final NPCType type;
    private boolean isSecondQuestLocked = true;
    private boolean isThirdQuestLocked = true;
    private int numOfDaysAfterUnlockingSecondQuest = 0;
    //TODO
    //placing NPCs on map
    public NPC(NPCType type) {
        this.type = type;
        this.position = type.getInitialPosition();
        this.friendshipLevel = FriendshipLevel.LevelZero;
        this.numericalFriendShipLevel = 0;
    }

    public void increaseFriendShipLevel(int level) {
        this.numericalFriendShipLevel += level;
        this.numericalFriendShipLevel = Math.min(this.numericalFriendShipLevel, this.type.getMaxFriendShipLevel());

        int tempLevel = this.numericalFriendShipLevel / 200;

        switch (tempLevel) {
            case 0:
                this.friendshipLevel = FriendshipLevel.LevelZero;
                break;
            case 1:
            {
                this.friendshipLevel = FriendshipLevel.LevelOne;
                this.isSecondQuestLocked = false;
            }
            break;
            case 2:
                this.friendshipLevel = FriendshipLevel.LevelTwo;
                break;
            case 3:
                this.friendshipLevel = FriendshipLevel.LevelThree;
                break;
        }
    }

    public FriendshipLevel getFriendshipLevel() {
        return friendshipLevel;
    }

    public NPCType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isSecondQuestLocked() {
        return isSecondQuestLocked;
    }

    public boolean isThirdQuestLocked() {
        return isThirdQuestLocked;
    }

    public void increaseNumOfDaysAfterUnlockingSecondQuest() {
        if (!this.isSecondQuestLocked) {
            this.numOfDaysAfterUnlockingSecondQuest++;
        }
    }

    public void checkUnlockingThirdQuest() {
        if (this.type.equals(NPCType.Abigail)) {
            if (this.numOfDaysAfterUnlockingSecondQuest >= 100) {this.isThirdQuestLocked = false;}
        }

        else if (this.type.equals(NPCType.Sebastian)) {
            if (this.numOfDaysAfterUnlockingSecondQuest >= 110) {this.isThirdQuestLocked = false;}
        }

        else if (this.type.equals(NPCType.Harvey)) {
            if (this.numOfDaysAfterUnlockingSecondQuest >= 120) {this.isThirdQuestLocked = false;}
        }

        else if (this.type.equals(NPCType.Leah)) {
            if (this.numOfDaysAfterUnlockingSecondQuest >= 130) {this.isThirdQuestLocked = false;}
        }

        else if (this.type.equals(NPCType.Robin)) {
            if (this.numOfDaysAfterUnlockingSecondQuest >= 140) {this.isThirdQuestLocked = false;}
        }

    }

    public int getNumericalFriendShipLevel() {
        return numericalFriendShipLevel;
    }

    public void doFirstQuest() {

        if (this.type.equals(NPCType.Abigail)) {

            AbigailQuests.doFirstQuest();

        } else if (this.type.equals(NPCType.Sebastian)) {

            SebastianQuests.doFirstQuest();

        } else if (this.type.equals(NPCType.Harvey)) {

            HarveyQuests.doFirstQuest();

        } else if (this.type.equals(NPCType.Leah)) {

            LeahQuests.doFirstQuest();

        } else if (this.type.equals(NPCType.Robin)) {

            RobinQuests.doFirstQuest();

        }
    }
    public void doSecondQuest() {

        if (this.type.equals(NPCType.Abigail)) {

            AbigailQuests.doSecondQuest();

        } else if (this.type.equals(NPCType.Sebastian)) {

            SebastianQuests.doSecondQuest();

        } else if (this.type.equals(NPCType.Harvey)) {

            HarveyQuests.doSecondQuest();

        } else if (this.type.equals(NPCType.Leah)) {

            LeahQuests.doSecondQuest();

        } else if (this.type.equals(NPCType.Robin)) {

            RobinQuests.doSecondQuest();

        }

    }
    public void doThirdQuest() {

        if (this.type.equals(NPCType.Abigail)) {

            AbigailQuests.doThirdQuest();

        } else if (this.type.equals(NPCType.Sebastian)) {

            SebastianQuests.doThirdQuest();

        } else if (this.type.equals(NPCType.Harvey)) {

            HarveyQuests.doThirdQuest();

        } else if (this.type.equals(NPCType.Leah)) {

            LeahQuests.doThirdQuest();

        } else if (this.type.equals(NPCType.Robin)) {

            RobinQuests.doThirdQuest();

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

    public String getDialogue(FriendshipLevel level) {

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
        } else if (App.getGame().getTime().getHour() > 18 && level.equals(FriendshipLevel.LevelThree)) {
            index = 5;
        }

        return this.type.getDialogues().get(index);
    }

    public void NPCInitialization() {
        //TODO
    }
}