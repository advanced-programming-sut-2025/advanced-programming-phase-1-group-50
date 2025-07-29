package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.AnimationID;

import java.util.HashMap;

public class GameAnimationIDManager {
    private static final HashMap<AnimationID , Animation<TextureRegion>> animations = new HashMap<>();
    static {
        TextureRegion[][] chicken = TextureRegion.split(new Texture("Animals_animation/Chicken_Brown.png"), 16, 16);
        animations.put(AnimationID.chicken_move_right , new Animation<>(0.15f, chicken[1]));
        animations.put(AnimationID.chicken_move_left , new Animation<>(0.15f, chicken[3]));
        animations.put(AnimationID.chicken_move_up , new Animation<>(0.15f, chicken[2]));
        animations.put(AnimationID.chicken_move_down , new Animation<>(0.15f, chicken[0]));
        animations.put(AnimationID.chicken_in_farm_eating , new Animation<>(0.15f, chicken[6]));
        animations.put(AnimationID.chicken_is_petting , new Animation<>(0.15f, chicken[4]));
    }

    public static Animation<TextureRegion> getAnimation(AnimationID id) {
        return animations.get(id);
    }
}
