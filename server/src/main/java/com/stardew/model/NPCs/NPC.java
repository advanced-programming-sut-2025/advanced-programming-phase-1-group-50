package com.stardew.model.NPCs;

import com.stardew.model.NPC.NPCType;
import com.stardew.model.Result;
import com.stardew.model.TextureID;
import com.stardew.model.animals.AnimalGood;
import com.stardew.model.animals.AnimalGoodType;
import com.stardew.model.cooking.Food;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.mapInfo.Stone;
import com.stardew.model.mapInfo.Wood;
import com.stardew.model.mapInfo.foraging.ForagingCrop;
import com.stardew.model.mapInfo.foraging.ForagingMineral;
import com.stardew.model.mapInfo.foraging.Fruit;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGood;
import com.stardew.model.mapInfo.manuFactor.ArtisanGoods.ArtisanGoodType;
import com.stardew.model.userInfo.Coin;
import com.stardew.model.userInfo.Player;

import java.util.Random;

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

    public Result doFirstQuest(int gameId,Player player,boolean isRewardTwice) {

        if (this.type.equals(NPCType.Abigail)) {

            return AbigailQuests.doFirstQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Sebastian)) {

            return SebastianQuests.doFirstQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Harvey)) {

            return HarveyQuests.doFirstQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Leah)) {

            return LeahQuests.doFirstQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Robin)) {

            return RobinQuests.doFirstQuest(gameId,player,isRewardTwice);

        }

        return new Result(false,"Invalid NPC type.");
    }

    public Result doSecondQuest(int gameId,Player player,boolean isRewardTwice) {

        if (this.type.equals(NPCType.Abigail)) {

            return AbigailQuests.doSecondQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Sebastian)) {

            return SebastianQuests.doSecondQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Harvey)) {

            return HarveyQuests.doSecondQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Leah)) {

            return LeahQuests.doSecondQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Robin)) {

            return RobinQuests.doSecondQuest(gameId,player,isRewardTwice);

        }

        return new Result(false,"Invalid NPC type.");

    }

    public Result doThirdQuest(int gameId,Player player,boolean isRewardTwice) {

        if (this.type.equals(NPCType.Abigail)) {

            return AbigailQuests.doThirdQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Sebastian)) {

            return SebastianQuests.doThirdQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Harvey)) {

            return HarveyQuests.doThirdQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Leah)) {

            return LeahQuests.doThirdQuest(gameId,player,isRewardTwice);

        } else if (this.type.equals(NPCType.Robin)) {

            return RobinQuests.doThirdQuest(gameId,player,isRewardTwice);

        }
        return new Result(false,"Invalid NPC type.");

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
            if (gift.equals(Food.PumpkinPie)) {
                return true;
            }
            return gift.equals(Food.Pizza);

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

            if (gift.equals(Food.Salad)) {
                return true;
            }
            if (gift.equals(ForagingCrop.Grape)) {
                return true;
            }
            if (gift instanceof ArtisanGood) {
                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.Wine);
            }

        } else if (this.type.equals(NPCType.Robin)) {

            if (gift.equals(Food.Spaghetti)) {
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

    public void giveRandomGiftToPlayer(Player player) {

        Random rand = new Random();
        int randomNumber = rand.nextInt(2);

        if (randomNumber == 0) {
            return;
        }

        int secondRandomNumber = rand.nextInt(2);

        if (this.type.equals(NPCType.Abigail)) {

            if (secondRandomNumber == 0) {

                player.getBackpack().addIngredients(ForagingMineral.Diamond,1);

            } else {

                player.getBackpack().addIngredients(ForagingMineral.Quartz,5);
            }

        } else if (this.type.equals(NPCType.Leah)) {

            if (secondRandomNumber == 0) {

                player.getBackpack().addIngredients(ForagingMineral.Emerald,2);

            } else {

                player.getBackpack().addIngredients(new Coin(),200);

            }

        } else if (this.type.equals(NPCType.Robin)) {

            if (secondRandomNumber == 0) {
               player.getBackpack().addIngredients(ForagingMineral.Iron,50);
            } else {
               player.getBackpack().addIngredients(new Wood(),100);
            }

        } else if (this.type.equals(NPCType.Harvey)) {

            if (secondRandomNumber == 0) {

                player.getBackpack().addIngredients(Fruit.Orange,10);

            } else {
                player.getBackpack().addIngredients(Fruit.Banana,10);
            }

        } else if (this.type.equals(NPCType.Sebastian)) {

            if (secondRandomNumber == 0) {
               player.getBackpack().addIngredients(ForagingMineral.Gold,10);
            } else {
                player.getBackpack().addIngredients(ForagingMineral.Ruby,2);
            }

        }


    }

    public TextureID getHomeTextureByType(NPCType type){

        return switch (type) {
            case Leah -> TextureID.npcHome1Region;
            case Robin -> TextureID.npcHome2Region;
            case Harvey -> TextureID.npcHome3Region;
            case Sebastian -> TextureID.npcHome4Region;
            case Abigail -> TextureID.npcHome5Region;
        };
    }
}
