package com.stardew.models.animals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.stardew.models.animals.FishBehavior.FishBehavior;
import com.stardew.models.manuFactor.Ingredient;
import com.stardew.models.stores.Sellable;

import java.util.Objects;

public class Fish implements Ingredient, Sellable {
    private final FishType type;
    private final int sellPrice;
    private Quality quality;
    private final TextureRegion texture;
    private final FishBehavior behavior;
    private final Vector2 position;
    private static final float MAX_Y = 690;
    private static final float MIN_Y = 60;

    public Fish(FishType type, Quality quality) {
        this.type = type;
        this.quality = quality;
        this.sellPrice = (int) (type.getPrice() * quality.getRatio());
        texture = type.getInventoryTexture();
        behavior = this.type.createBehavior();
        position = new Vector2(0, 0);
    }

    public FishType getType() {
        return type;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public Quality getQuality() {
        return quality;
    }

    public void developQuality() {
        if (quality == Quality.Regular)
            quality = Quality.Silver;
        else if (quality == Quality.Silver)
            quality = Quality.Gold;
        else if (quality == Quality.Gold)
            quality = Quality.Iridium;
    }

    public String getInfo() {
        return String.format("Fish  ->  type: %-22s quality: %-10s", type, quality);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void update(float delta) {
        behavior.update(this, delta);
    }

    @Override
    public String toString() {
        return quality + " " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return type == fish.type && quality == fish.quality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, quality);
    }

    @Override
    public TextureRegion getInventoryTexture() {
        return texture;
    }

    public void moveY(float deltaY) {
        position.y = Math.max(MIN_Y, Math.min(MAX_Y, position.y + deltaY));
    }
}
