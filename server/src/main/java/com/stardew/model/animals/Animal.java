package com.stardew.model.animals;

import com.stardew.model.AnimalDTO;
import com.stardew.model.TextureID;
import com.stardew.model.gameApp.TimeProvider;
import com.stardew.model.gameApp.date.Time;
import com.stardew.model.mapInfo.Placeable;

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
    private final Vec2 position;
    private Vec2 targetPosition;
    private float stateTime = 0f;
    private final float PET_TIME = 5f;
    private final float speed = 1f;
    private final static int maxFriendShip = 1000;
    private final TimeProvider timeProvider;
    private final Object lock = new Object();


    public Animal(AnimalType type, TimeProvider timeProvider, String name, Habitat habitat) {
        this.type = type;
        this.name = name;
        this.timeProvider = timeProvider;
        this.friendShip = 0;
        this.lastPetTime = timeProvider.getTime().clone();
        this.lastFeedTime = timeProvider.getTime().clone();
        this.lastProductTime = timeProvider.getTime().clone();
        this.habitat = habitat;
        this.state = AnimalState.IN_HABITAT;
        this.position = new Vec2(habitat.getPosition().x + 1, habitat.getPosition().y + 1);
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
        synchronized (lock) {
            lastPetTime = timeProvider.getTime().clone();
            state = AnimalState.IS_PETTING;
            stateTime = 0;
            incrementFriendShip(15);
        }
    }

    public boolean hasPettedYesterday() {
        return lastPetTime.getDate() == timeProvider.getTime().getDate() - 1;

    }

    public boolean hasPettedToday() {
        return lastPetTime.getDate() == timeProvider.getTime().getDate();

    }

    public void feed() {
        lastFeedTime = timeProvider.getTime().clone();
    }

    public boolean hasFedYesterday() {
        return lastFeedTime.getDate() == timeProvider.getTime().getDate() - 1;
    }

    public boolean hasFedToday() {
        return lastFeedTime.getDate() == timeProvider.getTime().getDate();
    }

    public boolean isReadyProduct() {
        Time today = timeProvider.getTime().clone();
        int dayOfToday = today.getDate();
        if (!today.getSeason().equals(lastProductTime.getSeason()))
            dayOfToday += Math.abs(lastProductTime.getSeason().ordinal() - today.getSeason().ordinal()) * 28;
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

        lastProductTime = timeProvider.getTime().clone();

        double qualityValue = ((double) friendShip / 1000) * (0.5 + 0.5 * Math.random());
        Quality quality = Quality.getQualityByValue(qualityValue);

        return new AnimalGood(type.getAnimalGoods().get(whichProduct), quality);

    }

    public Habitat getHabitat() {
        return habitat;
    }

    public boolean isOutOfHabitat() {
        return state != AnimalState.IN_HABITAT;
    }

    private void moveTo(Vec2 destination) {
        targetPosition = destination;

        Vec2 diff = targetPosition.cpy().sub(position);
        synchronized (lock) {
            if (Math.abs(diff.x) > Math.abs(diff.y)) {
                state = diff.x > 0 ? AnimalState.MOVING_RIGHT : AnimalState.MOVING_LEFT;
            }
            else {
                state = diff.y > 0 ? AnimalState.MOVING_UP : AnimalState.MOVING_DOWN;
            }

            stateTime = 0f;
        }
    }

    public void goToHabitat() {
        moveTo(new Vec2(habitat.getPosition().x + 1, habitat.getPosition().y + 1));
        nextState = AnimalState.IN_HABITAT;
    }

    public void shepherdAnimal(float x, float y) {
        moveTo(new Vec2(x, y));
        nextState = AnimalState.IN_FARM_EATING;
    }

    public boolean isMoving() {
        return switch (state) {
            case MOVING_RIGHT, MOVING_LEFT, MOVING_UP, MOVING_DOWN -> true;
            default -> false;
        };
    }

    public Vec2 getPosition() {
        return position;
    }

    public void update(float delta) {
        if (state == AnimalState.IN_HABITAT) return;

        synchronized (lock) {
            stateTime += delta;
            if (isMoving()) {
                float distance = position.dst(targetPosition);
                if (distance <= 0.1f) {
                    position.set(targetPosition);
                    state = nextState;
                    stateTime = 0f;
                } else {
                    Vec2 direction = targetPosition.cpy().sub(position).nor();
                    position.add(direction.scl(speed * delta));
                }
            } else if (state == AnimalState.IS_PETTING) {
                if (stateTime >= PET_TIME)
                    state = AnimalState.IN_FARM_EATING;
            }
        }

    }

//    public void render(Batch batch) {
//        if (state == AnimalState.IN_HABITAT) return;
//
//        Animation<TextureRegion> animation = type.getAnimationID(state);
//        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
//
//        batch.draw(currentFrame,
//            position.x * GamePictureManager.TILE_SIZE,
//            position.y * GamePictureManager.TILE_SIZE,
//            type.getAnimalHabitat() == HabitatType.Coop ? 40 : 65, //TODO if need, I must send size of rendering
//            type.getAnimalHabitat() == HabitatType.Coop ? 40 : 65);
//    }

    private String getDescription() {
        return "\n\n" +
            String.format("     Name: %s   \n\n", name) +
            String.format("     Type: %s   \n\n", type) +
            String.format("     LevelOfFriendship: %d   \n\n", friendShip) +
            String.format("     hasPettedToday: %s   \n\n", hasPettedToday()) +
            String.format("     hasFedToday: %s   \n\n", hasFedToday());
    }

    public AnimalDTO toDTO() {
        synchronized (lock) {
            return new AnimalDTO(
                position.x,
                position.y,
                stateTime,
                name,
                getDescription(),
                type.getAnimationID(state),
                type.getNormalTexture()
            );
        }
    }

    @Override
    public Rectangle getBounds() {
        synchronized (lock) {
            return new Rectangle(((int) position.x), (int) position.y, 1, 1);
        }
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
