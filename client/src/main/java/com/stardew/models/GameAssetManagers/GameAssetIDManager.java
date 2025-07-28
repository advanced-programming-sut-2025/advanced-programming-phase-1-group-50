package com.stardew.models.GameAssetManagers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

}
