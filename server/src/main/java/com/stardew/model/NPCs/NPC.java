package com.stardew.model.NPCs;

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

//    public Result doFirstQuest(boolean isRewardTwice) {
//
//        if (this.type.equals(NPCType.Abigail)) {
//
//            return AbigailQuests.doFirstQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Sebastian)) {
//
//            return SebastianQuests.doFirstQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Harvey)) {
//
//            return HarveyQuests.doFirstQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Leah)) {
//
//            return LeahQuests.doFirstQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Robin)) {
//
//            return RobinQuests.doFirstQuest(isRewardTwice);
//
//        }
//
//        return new Result(false,"Invalid NPC type.");
//    }
//
//    public Result doSecondQuest(boolean isRewardTwice) {
//
//        if (this.type.equals(NPCType.Abigail)) {
//
//            return AbigailQuests.doSecondQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Sebastian)) {
//
//            return SebastianQuests.doSecondQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Harvey)) {
//
//            return HarveyQuests.doSecondQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Leah)) {
//
//            return LeahQuests.doSecondQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Robin)) {
//
//            return RobinQuests.doSecondQuest(isRewardTwice);
//
//        }
//
//        return new Result(false,"Invalid NPC type.");
//
//    }
//
//    public Result doThirdQuest(boolean isRewardTwice) {
//
//        if (this.type.equals(NPCType.Abigail)) {
//
//            return AbigailQuests.doThirdQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Sebastian)) {
//
//            return SebastianQuests.doThirdQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Harvey)) {
//
//            return HarveyQuests.doThirdQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Leah)) {
//
//            return LeahQuests.doThirdQuest(isRewardTwice);
//
//        } else if (this.type.equals(NPCType.Robin)) {
//
//            return RobinQuests.doThirdQuest(isRewardTwice);
//
//        }
//        return new Result(false,"Invalid NPC type.");
//
//    }
//
//    public boolean isFavoriteGift(Ingredient gift) {
//
//        if (this.type.equals(NPCType.Abigail)) {
//
//            if (gift instanceof Stone) {
//                return true;
//            }
//            if (gift.equals(ForagingMineral.Iron)) {
//                return true;
//            }
//            if (gift instanceof ArtisanGood) {
//                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.Coffee);
//            }
//
//        } else if (this.type.equals(NPCType.Sebastian)) {
//            if (gift instanceof AnimalGood) {
//                if (((AnimalGood) gift).getType().equals(AnimalGoodType.Wool)) {
//                    return true;
//                }
//            }
//            if (gift.equals(Food.PumpkinPie)) {
//                return true;
//            }
//            return gift.equals(Food.Pizza);
//
//        } else if (this.type.equals(NPCType.Harvey)) {
//
//            if (gift instanceof ArtisanGood) {
//                if (((ArtisanGood) gift).getType().equals(ArtisanGoodType.Coffee)) {
//                    return true;
//                }
//            }
//            if (gift instanceof ArtisanGood) {
//                if (((ArtisanGood) gift).getType().equals(ArtisanGoodType.Pickles)) {
//                    return true;
//                }
//            }
//            if (gift instanceof ArtisanGood) {
//                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.Wine);
//            }
//
//        } else if (this.type.equals(NPCType.Leah)) {
//
//            if (gift.equals(Food.Salad)) {
//                return true;
//            }
//            if (gift.equals(ForagingCrop.Grape)) {
//                return true;
//            }
//            if (gift instanceof ArtisanGood) {
//                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.Wine);
//            }
//
//        } else if (this.type.equals(NPCType.Robin)) {
//
//            if (gift.equals(Food.Spaghetti)) {
//                return true;
//            }
//            if (gift instanceof Wood) {
//                return true;
//            }
//            if (gift instanceof ArtisanGood) {
//                return ((ArtisanGood) gift).getType().equals(ArtisanGoodType.IronBar);
//            }
//
//        }
//
//        return false;
//    }
//
//    public String getDialogue(NPCFriendshipLevel level) {
//
//        int index = 6;
//
//        if (App.getGame().getTime().getSeason().equals(Season.Summer) || App.getGame().getTime().getWeather().equals(Weather.Sunny)) {
//            index = 0;
//        } else if (App.getGame().getTime().getWeather().equals(Weather.Rainy)) {
//            index = 1;
//        } else if (App.getGame().getTime().getWeather().equals(Weather.Sunny) && App.getGame().getTime().getSeason().equals(Season.Spring)) {
//            index = 2;
//        } else if (App.getGame().getTime().getSeason().equals(Season.Fall) && App.getGame().getTime().getHour() > 18) {
//            index = 3;
//        } else if (App.getGame().getTime().getWeather().equals(Weather.Snowy)) {
//            index = 4;
//        } else if (App.getGame().getTime().getHour() > 18 && level.equals(NPCFriendshipLevel.LevelThree)) {
//            index = 5;
//        }
//
//        return this.type.getDialogues().get(index);
//    }
//
//    public char getSymbol() {
//        return this.type.getSymbol();
//    }
//
//    public void giveRandomGiftToPlayer(Player player) {
//
//        Random rand = new Random();
//        int randomNumber = rand.nextInt(2);
//
//        if (randomNumber == 0) {
//            return;
//        }
//
//        int playerIndex = App.getGame().getPlayers().indexOf(player);
//        int secondRandomNumber = rand.nextInt(2);
//
//        if (this.type.equals(NPCType.Abigail)) {
//
//            if (secondRandomNumber == 0) {
//
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(ForagingMineral.Diamond,1);
//
//            } else {
//
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(ForagingMineral.Quartz,5);
//            }
//
//        } else if (this.type.equals(NPCType.Leah)) {
//
//            if (secondRandomNumber == 0) {
//
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(ForagingMineral.Emerald,2);
//
//            } else {
//
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(new Coin(),200);
//
//            }
//
//        } else if (this.type.equals(NPCType.Robin)) {
//
//            if (secondRandomNumber == 0) {
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(ForagingMineral.Iron,50);
//            } else {
//
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(new Wood(),100);
//
//            }
//
//        } else if (this.type.equals(NPCType.Harvey)) {
//
//            if (secondRandomNumber == 0) {
//
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(Fruit.Orange,10);
//
//            } else {
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(Fruit.Banana,10);
//            }
//
//        } else if (this.type.equals(NPCType.Sebastian)) {
//
//            if (secondRandomNumber == 0) {
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(ForagingMineral.Gold,10);
//            } else {
//                App.getGame().getPlayers().get(playerIndex).getBackpack().addIngredients(ForagingMineral.Ruby,2);
//            }
//
//        }
//
//
//    }
//
//    public TextureRegion[][] getHomeRegions (NPCType type) {
//        return switch (type) {
//            case Leah -> GamePictureManager.npcHome1Regions;
//            case Robin -> GamePictureManager.npcHome2Regions;
//            case Harvey -> GamePictureManager.npcHome3Regions;
//            case Sebastian -> GamePictureManager.npcHome4Regions;
//            case Abigail -> GamePictureManager.npcHome5Regions;
//        };
//    }
//
//    public Texture getHomeTextureByType(NPCType type){
//
//        return switch (type) {
//            case Leah -> GamePictureManager.npcHome1Texture;
//            case Robin -> GamePictureManager.npcHome2Texture;
//            case Harvey -> GamePictureManager.npcHome3Texture;
//            case Sebastian -> GamePictureManager.npcHome4Texture;
//            case Abigail -> GamePictureManager.npcHome5Texture;
//        };
//    }
}
