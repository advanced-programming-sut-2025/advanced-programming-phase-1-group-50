package models.animals;

import models.app.App;
import models.date.Time;


public class Animal {
    private final AnimalType type;
    private final String name;
    private int friendShip;
    private Time lastPetTime;
    private Time lastFeedTime;
    private Time lastProductTime;
    private final static int maxFriendShip = 1000;

    public Animal(AnimalType type, String name) {
        this.type = type;
        this.name = name;
        this.friendShip = 0;
        this.lastPetTime = null;
        this.lastFeedTime = null;
        this.lastProductTime = App.getGame().getTime().clone();
    }

    public AnimalType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getFriendShip() {
        return friendShip;
    }

    public void setFriendShip(int friendShip) {
        this.friendShip = friendShip;
    }

    public void incrementFriendShip(int increment) {
        this.friendShip += increment;
        this.friendShip = Math.min(this.friendShip, maxFriendShip);
    }

    public void decrementFriendShip(int decrement) {
        this.friendShip -= decrement;
    }

    public void pet() {
        lastPetTime = App.getGame().getTime().clone();
        incrementFriendShip(15);
    }

    public boolean hasPettedToday() {
        return lastPetTime.getDate() == App.getGame().getTime().getDate();
    }

    public void feed() {
        lastFeedTime = App.getGame().getTime().clone();
    }

    public boolean hasFedToday() {
        return lastFeedTime.getDate() == App.getGame().getTime().getDate();
    }

    public boolean isReadyProduct() {
        //TODO -> for pig is different
        Time today = App.getGame().getTime().clone();
        int dayOfToday = today.getDate();
        if (today.getSeason().equals(lastProductTime.getSeason()))
            dayOfToday += 28;
        return dayOfToday >= lastProductTime.getDate() + type.getDaysToGetProduct();
    }

    public AnimalGood getProduct() {
        if (!isReadyProduct())
            return null;

        int whichProduct = 0;
        if (type.getAnimalGoods().size() == 2 && friendShip >= 100) {
            double random = Math.random() + 0.5;
            double chance = (friendShip + (150 * random)) / 1500;
            if (Math.random() <= chance)
                whichProduct = 1;
        }

        lastProductTime = App.getGame().getTime().clone();

        double qualityValue = ((double) friendShip / 1000) * (0.5 + 0.5 * Math.random());
        Quality quality = Quality.getQualityByValue(qualityValue);

        return new AnimalGood(type.getAnimalGoods().get(whichProduct), quality);
    }
}
