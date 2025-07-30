package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.AnimationID;
import com.stardew.model.TextureID;
import com.stardew.models.date.Season;
import com.stardew.view.GameMenu;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class GameAssetIDManager {
    public static HashMap<TextureID, TextureRegion> ids = new HashMap<>();
    static {
        for (TextureID id : TextureID.values()) {
            try {
                Field field = GamePictureManager.class.getField(id.name());
                Object value = field.get(null);
                if (value instanceof TextureRegion) {
                    ids.put(id, (TextureRegion) value);
                }
            } catch (Exception e) {
                System.err.println("Failed to load asset for ID: " + id.name());
                e.printStackTrace();
            }
        }
    }

    public static TextureRegion getTextureRegion(TextureID id) {
        return ids.get(id);
    }
    private static final HashMap<AnimationID, Animation<TextureRegion>> animations = new HashMap<>();
    static {
        TextureRegion[][] chicken = TextureRegion.split(new Texture("Animals_animation/Chicken_Brown.png"), 16, 16);
        animations.put(AnimationID.chicken_move_right , new Animation<>(0.15f, chicken[1]));
        animations.put(AnimationID.chicken_move_left , new Animation<>(0.15f, chicken[3]));
        animations.put(AnimationID.chicken_move_up , new Animation<>(0.15f, chicken[2]));
        animations.put(AnimationID.chicken_move_down , new Animation<>(0.15f, chicken[0]));
        animations.put(AnimationID.chicken_in_farm_eating , new Animation<>(0.15f, chicken[6]));
        animations.put(AnimationID.chicken_is_petting , new Animation<>(0.15f, chicken[4]));

        TextureRegion[][] duck = TextureRegion.split(new Texture("Animals_animation/Duck.png") , 16, 16);
        animations.put(AnimationID.duck_move_right , new Animation<>(0.15f, duck[1]));
        animations.put(AnimationID.duck_move_left , new Animation<>(0.15f, duck[3]));
        animations.put(AnimationID.duck_move_up , new Animation<>(0.15f, duck[2]));
        animations.put(AnimationID.duck_move_down , new Animation<>(0.15f, duck[0]));
        animations.put(AnimationID.duck_in_farm_eating , new Animation<>(0.15f, duck[6]));
        animations.put(AnimationID.duck_is_petting , new Animation<>(0.15f, duck[4]));

        TextureRegion[][] rabbit = TextureRegion.split(new Texture("Animals_animation/Rabbit.png"), 16, 16);
        animations.put(AnimationID.rabbit_move_right , new Animation<>(0.15f, rabbit[1]));
        animations.put(AnimationID.rabbit_move_left , new Animation<>(0.15f, rabbit[3]));
        animations.put(AnimationID.rabbit_move_up , new Animation<>(0.15f, rabbit[2]));
        animations.put(AnimationID.rabbit_move_down , new Animation<>(0.15f, rabbit[0]));
        animations.put(AnimationID.rabbit_in_farm_eating , new Animation<>(0.15f, rabbit[6]));
        animations.put(AnimationID.rabbit_is_petting , new Animation<>(0.15f, rabbit[4]));


        TextureRegion[][] dinosaur = TextureRegion.split(new Texture("Animals_animation/Dinosaur.png"), 16, 16);
        animations.put(AnimationID.dinosaur_move_right , new Animation<>(0.15f, dinosaur[1]));
        animations.put(AnimationID.dinosaur_move_left , new Animation<>(0.15f, dinosaur[3]));
        animations.put(AnimationID.dinosaur_move_up , new Animation<>(0.15f, dinosaur[2]));
        animations.put(AnimationID.dinosaur_move_down , new Animation<>(0.15f, dinosaur[0]));
        animations.put(AnimationID.dinosaur_in_farm_eating , new Animation<>(0.15f, dinosaur[6]));
        animations.put(AnimationID.dinosaur_is_petting , new Animation<>(0.15f, dinosaur[4]));


        TextureRegion[][] cow = TextureRegion.split(new Texture("Animals_animation/Cow_White.png"), 32, 32);
        animations.put(AnimationID.cow_move_right , new Animation<>(0.15f, cow[1]));
        animations.put(AnimationID.cow_move_left , new Animation<>(0.15f, cow[3]));
        animations.put(AnimationID.cow_move_up , new Animation<>(0.15f, cow[2]));
        animations.put(AnimationID.cow_move_down , new Animation<>(0.15f, cow[0]));
        animations.put(AnimationID.cow_in_farm_eating , new Animation<>(0.15f, cow[6]));
        animations.put(AnimationID.cow_is_petting , new Animation<>(0.15f, cow[4]));


        TextureRegion[][] goat = TextureRegion.split(new Texture("Animals_animation/Goat.png"), 32, 32);
        animations.put(AnimationID.goat_move_right , new Animation<>(0.15f, goat[1]));
        animations.put(AnimationID.goat_move_left , new Animation<>(0.15f, goat[3]));
        animations.put(AnimationID.goat_move_up , new Animation<>(0.15f, goat[2]));
        animations.put(AnimationID.goat_move_down , new Animation<>(0.15f, goat[0]));
        animations.put(AnimationID.goat_in_farm_eating , new Animation<>(0.15f, goat[6]));
        animations.put(AnimationID.goat_is_petting , new Animation<>(0.15f, goat[4]));

        TextureRegion[][] sheep = TextureRegion.split(new Texture("Animals_animation/Sheep.png"), 32, 32);
        animations.put(AnimationID.sheep_move_right , new Animation<>(0.15f, sheep[1]));
        animations.put(AnimationID.sheep_move_left , new Animation<>(0.15f, sheep[3]));
        animations.put(AnimationID.sheep_move_up , new Animation<>(0.15f, sheep[2]));
        animations.put(AnimationID.sheep_move_down , new Animation<>(0.15f, sheep[0]));
        animations.put(AnimationID.sheep_in_farm_eating , new Animation<>(0.15f, sheep[6]));
        animations.put(AnimationID.sheep_is_petting , new Animation<>(0.15f, sheep[4]));

        TextureRegion[][] pig = TextureRegion.split(new Texture("Animals_animation/Pig.png"), 32, 32);
        animations.put(AnimationID.pig_move_right , new Animation<>(0.15f, pig[1]));
        animations.put(AnimationID.pig_move_left , new Animation<>(0.15f, pig[3]));
        animations.put(AnimationID.pig_move_up , new Animation<>(0.15f, pig[2]));
        animations.put(AnimationID.pig_move_down , new Animation<>(0.15f, pig[0]));
        animations.put(AnimationID.pig_in_farm_eating , new Animation<>(0.15f, pig[6]));
        animations.put(AnimationID.pig_is_petting , new Animation<>(0.15f, pig[4]));
    }

    public static Animation<TextureRegion> getAnimation(AnimationID id) {
        return animations.get(id);
    }

}
