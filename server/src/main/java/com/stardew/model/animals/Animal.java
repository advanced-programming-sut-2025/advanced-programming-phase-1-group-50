package com.stardew.model.animals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.date.Time;
import com.stardew.model.mapInfo.Placeable;
import com.stardew.model.gameApp.App;

import java.awt.*;

public class Animal implements Placeable {
    private final AnimalType type;
    private final String name;
    private int friendShip;
    private Time lastPetTime;
    private Time lastFeedTime;
    private Time lastProductTime;
    private final Habitat habitat;
    private AnimalState state;
    private AnimalState nextState;
    private Vector2 position;
    private Vector2 targetPosition;
    private float stateTime = 0f;
    private final float PET_TIME = 5f;
    private final float speed = 1f;
    private final static int maxFriendShip = 1000;
    private Rectangle bounds;


    public Animal(AnimalType type, String name, Habitat habitat) {
        this.type = type;
        this.name = name;
        this.friendShip = 0;
//        this.lastPetTime = App.getGame().getTime().clone();
//        this.lastFeedTime = App.getGame().getTime().clone();
//        this.lastProductTime = App.getGame().getTime().clone();
        this.habitat = habitat;
        this.state = AnimalState.IN_HABITAT;
        this.position = new Vector2(habitat.getPosition().x + 1, habitat.getPosition().y + 1);
        bounds =  new Rectangle(1, 1);
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
//        lastPetTime = App.getGame().getTime().clone();
//        state = AnimalState.IS_PETTING;
//        stateTime = 0;
//        incrementFriendShip(15);
    }

    public boolean hasPettedYesterday() {
//        return lastPetTime.getDate() == App.getGame().getTime().getDate() - 1;
        return true;
    }

    public boolean hasPettedToday() {
//        return lastPetTime.getDate() == App.getGame().getTime().getDate();
        return true;
    }

    public void feed() {
//        lastFeedTime = App.getGame().getTime().clone();
    }

    public boolean hasFedYesterday() {
//        return lastFeedTime.getDate() == App.getGame().getTime().getDate() - 1;
        return true;
    }

    public boolean hasFedToday() {
//        return lastFeedTime.getDate() == App.getGame().getTime().getDate();
            return true;
    }

    public boolean isReadyProduct() {
        //TODO -> for pig is different
//        Time today = App.getGame().getTime().clone();
//        int dayOfToday = today.getDate();
//        if (!today.getSeason().equals(lastProductTime.getSeason()))
//            dayOfToday += Math.abs(lastProductTime.getSeason().ordinal() - today.getSeason().ordinal()) * 28;
//        return dayOfToday >= lastProductTime.getDate() + type.getDaysToGetProduct();
        return true;
    }

    public AnimalGood getProduct() {
//        if (!isReadyProduct())
//            return null;
//
//        int whichProduct = 0;
//        if (type.getAnimalGoods().size() == 2 && friendShip >= 100) {
//            double random = Math.random() + 0.5;
//            double chance = (friendShip + (150 * random)) / 1500;
//            if (Math.random() <= chance)
//                whichProduct = 1;
//        }
//
//        lastProductTime = App.getGame().getTime().clone();
//
//        double qualityValue = ((double) friendShip / 1000) * (0.5 + 0.5 * Math.random());
//        Quality quality = Quality.getQualityByValue(qualityValue);
//
//        return new AnimalGood(type.getAnimalGoods().get(whichProduct), quality);
        return null;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public boolean isOutOfHabitat() {
        return state != AnimalState.IN_HABITAT;
    }

    private void moveTo(Vector2 destination) {
        targetPosition = destination;

        Vector2 diff = targetPosition.cpy().sub(position);
        if (Math.abs(diff.x) > Math.abs(diff.y)) {
            state = diff.x > 0 ? AnimalState.MOVING_RIGHT : AnimalState.MOVING_LEFT;
        }
        else {
            state = diff.y > 0 ? AnimalState.MOVING_UP : AnimalState.MOVING_DOWN;
        }

        stateTime = 0f;
    }

    public void goToHabitat() {
        moveTo(new Vector2(habitat.getPosition().x + 1, habitat.getPosition().y + 1));
        nextState = AnimalState.IN_HABITAT;
    }

    public void shepherdAnimal(float x, float y) {
        moveTo(new Vector2(x, y));
        nextState = AnimalState.IN_FARM_EATING;
    }

    public boolean isMoving() {
        return switch (state) {
            case MOVING_RIGHT, MOVING_LEFT, MOVING_UP, MOVING_DOWN -> true;
            default -> false;
        };
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta) {
        if (state == AnimalState.IN_HABITAT) return;

        stateTime += delta;
        if (isMoving()) {
            float distance = position.dst(targetPosition);
            if (distance <= 0.1f) {
                position.set(targetPosition);
                state = nextState;
                stateTime = 0f;
            } else {
                Vector2 direction = targetPosition.cpy().sub(position).nor();
                position.add(direction.scl(speed * delta));
            }
        }
        else if (state == AnimalState.IS_PETTING) {
            if (stateTime >= PET_TIME)
                state = AnimalState.IN_FARM_EATING;
        }

    }

//    public void render(Batch batch) {
//        if (state == AnimalState.IN_HABITAT) return;
//
//        Animation<TextureRegion> animation = type.getAnimation(state);
//        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
//
//        batch.draw(currentFrame,
//            position.x * GamePictureManager.TILE_SIZE,
//            position.y * GamePictureManager.TILE_SIZE,
//            type.getAnimalHabitat() == HabitatType.Coop ? 40 : 65,
//            type.getAnimalHabitat() == HabitatType.Coop ? 40 : 65);
//    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public char getSymbol() {
        return 0;
    }



    @Override
    public TextureID getTexture() {
        return type.getNormalTexture();
    }


}
