package com.stardew.models.mapInfo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Placeable;
import com.stardew.models.foraging.Fertilizer;

import java.util.ArrayList;
import java.util.Random;

public class Tile {
    private Position position;
    private boolean gotThunder;
    private char symbol = '#';
    private boolean walkable ;
    private Placeable placeable;
    private boolean isPlowed = false;
    private Fertilizer fertilizer = null;
    private TextureRegion texture ;
    private TextureRegion backgroundTexture ;
    public Tile(Position position) {
        this.position = position;
        this.gotThunder = false;
        this.walkable = true;
        texture = new TextureRegion(getRandomDefaultTexture());
        backgroundTexture = new TextureRegion(getRandomDefaultTexture());

    }
    public void setPosition(Position position) {
        this.position = position;
    }


    public Position getPosition() {
        return position;

    }
    public char getSymbol() {
        return symbol;
    }
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public boolean isGotThunder() {
        return gotThunder;
    }
    public void setGotThunder(boolean gotThunder) {
        this.gotThunder = gotThunder;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
    public Placeable getPlaceable() {
        return placeable;
    }
    public void setPlaceable(Placeable placeable) {
        this.placeable = placeable;
        if (placeable != null)
            setTexture(placeable.getTexture());
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }


    public void setBackgroundTexture(TextureRegion backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public TextureRegion getBackgroundTexture() {
        return backgroundTexture;
    }

    public Texture getRandomDefaultTexture() {
        ArrayList<Texture> textures  = new ArrayList<>();
        textures.add(GamePictureManager.defaultTileTexture);
        textures.add(GamePictureManager.defaultTileTexture2);
        textures.add(GamePictureManager.defaultTileTexture3);

        Random rand = new Random();
        return textures.get(rand.nextInt(textures.size()));
    }
}
