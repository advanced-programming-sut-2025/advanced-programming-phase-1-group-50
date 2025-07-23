package com.stardew.models.userInfo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum Avatar {
    abigail("Villagers/Abigail.png"),
    harvey("Villagers/Harvey.png"),
    robin("Villagers/Robin.png"),
    sebastian("Villagers/Sebastian.png"),
    leah("Villagers/Leah.png"),;


    private final String path;
    private TextureRegion avatarRegion;
    Avatar(String path) {
        this.path = path;
    }

    public TextureRegion getAvatar() {
        if (avatarRegion == null) {
            avatarRegion = new TextureRegion(new Texture(Gdx.files.internal(path)));
        }
        return avatarRegion;
    }
}
