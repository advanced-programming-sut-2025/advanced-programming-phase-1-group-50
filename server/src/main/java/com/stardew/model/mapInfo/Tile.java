package com.stardew.model.mapInfo;

import com.stardew.model.TextureID;
import com.stardew.model.TileDTO;
import com.stardew.model.mapInfo.foraging.Fertilizer;

import java.util.Random;

public class Tile {
    private Position position;
    private boolean gotThunder;
    private char symbol = '#';
    private boolean walkable ;
    private Placeable placeable;
    private boolean isPlowed = false;
    private Fertilizer fertilizer = null;
    private boolean watered = false;
    private TextureID textureID ;
    private TextureID backgroundTextureID ;
    private TextureID pastTextureID;

    private float wateredTimeTexture = 0.0f;


    public Tile(Position position) {
        this.position = position;
        this.gotThunder = false;
        this.walkable = true;
        textureID =  TextureID.defaultTileTexture3;
        backgroundTextureID = TextureID.defaultTileTexture3;
        pastTextureID = backgroundTextureID;
//        typeTextureRegion = TileTypeTextureRegion.Normal;

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
    public void setWatered(boolean watered) {
        this.watered = watered;
        if (watered) {
            backgroundTextureID = TextureID.wateredTileTexture;

        }
        else {
            backgroundTextureID = TextureID.plowedTile;

        }
    }

    public boolean isWatered(){
        return watered;
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
//        if (placeable != null)
//            setTexture(placeable.getTexture());
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
        if(plowed){
            this.backgroundTextureID = TextureID.plowedTile;

        }
        else {
            this.backgroundTextureID = pastTextureID;

        }
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
        if (fertilizer != null) {
            this.backgroundTextureID = (fertilizer == Fertilizer.WaterFertilizer) ?
                TextureID.waterFertilizedTile : TextureID.growthFertilizedTile;

        }
        else {
            this.backgroundTextureID = pastTextureID;

        }
    }

//    public TextureRegion getTexture() {
//        return texture;
//    }

//    public void setTexture(TextureRegion texture) {
//        this.texture = texture;
//    }




    public TextureID getBackgroundTexture() {
        return backgroundTextureID;
    }

    public TextureID getRandomDefaultTexture() {
        TextureID[] defaultTiles = new TextureID[] {
            TextureID.defaultTileTexture,
            TextureID.defaultTileTexture2,
            TextureID.defaultTileTexture3,
        };
        return defaultTiles[new Random().nextInt(defaultTiles.length)];
    }

    public void checkSeasonIsWinter() {
        backgroundTextureID = getRandomSnowyTexture();
        pastTextureID = backgroundTextureID;
    }

    public TextureID getRandomSnowyTexture(){
        TextureID[] snowyTiles = new TextureID[] {
            TextureID.snowyTile,
            TextureID.snowyTile2,
        };
        return snowyTiles[new Random().nextInt(snowyTiles.length)];
    }

    public void checkIsSeasonSpring(){
        backgroundTextureID = TextureID.defaultTileTexture3;
        pastTextureID = backgroundTextureID;
    }

    public void checkIsSeasonSummer(){
        backgroundTextureID = TextureID.defaultTileTexture2;
        pastTextureID = backgroundTextureID;
    }

    public void checkIsSeasonFall(){
        backgroundTextureID = TextureID.fallTexture;
        pastTextureID = backgroundTextureID;
    }
    public void setWateredTimeTexture(float wateredTimeTexture) {
        this.wateredTimeTexture = wateredTimeTexture;
    }

    public float getWateredTimeTexture() {
        return wateredTimeTexture;
    }

    public TileDTO toDTO(){
        return new TileDTO(position.getX(), position.getY(), backgroundTextureID);
    }

    public void setTextureStoned(){
        this.backgroundTextureID = getRandomStonedTexture();
        pastTextureID = backgroundTextureID;
    }

    public TextureID getRandomStonedTexture(){
        TextureID[] stonedTiles = new TextureID[] {
            TextureID.flooringStone,
            TextureID.flooringStone2,
        };
        return stonedTiles[new Random().nextInt(stonedTiles.length)];
    }
}
