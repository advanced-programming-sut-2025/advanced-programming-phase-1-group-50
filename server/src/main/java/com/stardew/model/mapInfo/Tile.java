package com.stardew.model.mapInfo;

//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.stardew.model.GameAssetManagers.GamePictureManager;
//import com.stardew.model.Placeable;
import com.stardew.model.gameApp.App;
//import com.stardew.model.foraging.Fertilizer;

import java.util.ArrayList;
import java.util.Random;

public class Tile {
    private Position position;
    private boolean gotThunder;
    private char symbol = '#';
    private boolean walkable ;
//    private Placeable placeable;
    private boolean isPlowed = false;
//    private Fertilizer fertilizer = null;
    private boolean watered = false;
//    private TextureRegion texture ;
//    private TextureRegion backgroundTexture ;
//    private TextureRegion pastTexture;
//    private TileTypeTextureRegion typeTextureRegion;
    private float wateredTimeTexture = 0.0f;


    public Tile(Position position) {
        this.position = position;
        this.gotThunder = false;
        this.walkable = true;
//        texture = new TextureRegion(getRandomDefaultTexture());
//        backgroundTexture = new TextureRegion(getRandomDefaultTexture());
//        pastTexture = backgroundTexture;
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
//        if (watered) {
//            backgroundTexture = GamePictureManager.wateredTileTexture;
//            typeTextureRegion = TileTypeTextureRegion.Watered;
//        }
//        else {
//            backgroundTexture = GamePictureManager.plowedTile;
//            typeTextureRegion = TileTypeTextureRegion.Normal;
//        }
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
//    public Placeable getPlaceable() {
//        return placeable;
//    }
//    public void setPlaceable(Placeable placeable) {
//        this.placeable = placeable;
//        if (placeable != null)
//            setTexture(placeable.getTexture());
//    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
//        if(plowed){
//            this.backgroundTexture = GamePictureManager.plowedTile;
//            typeTextureRegion = TileTypeTextureRegion.Plowed;
//        }
//        else {
//            this.backgroundTexture = pastTexture;
//            typeTextureRegion = TileTypeTextureRegion.Normal;
//        }
    }

//    public Fertilizer getFertilizer() {
//        return fertilizer;
//    }

//    public void setFertilizer(Fertilizer fertilizer) {
//        this.fertilizer = fertilizer;
//        if (fertilizer != null) {
//            this.backgroundTexture = (fertilizer == Fertilizer.WaterFertilizer) ?
//                GamePictureManager.waterFertilizedTile : GamePictureManager.growthFertilizedTile;
//            typeTextureRegion = TileTypeTextureRegion.Fertilizer;
//        }
//        else {
//            this.backgroundTexture = pastTexture;
//            typeTextureRegion = TileTypeTextureRegion.Normal;
//        }
//    }

//    public TextureRegion getTexture() {
//        return texture;
//    }

//    public void setTexture(TextureRegion texture) {
//        this.texture = texture;
//    }

//    public boolean isAroundMe(Tile tile) {
//        for (Direction direction : Direction.values()) {
//            Tile inDirectionTile = App.getGame().getMap().getTileByDirection(this, direction);
//            if (inDirectionTile != null && inDirectionTile.getPosition().equals(tile.getPosition())) {
//                return true;
//            }
//        }
//        return false;
//    }


//    public void setBackgroundTexture(TextureRegion backgroundTexture) {
//        this.backgroundTexture = backgroundTexture;
//    }

//    public TextureRegion getBackgroundTexture() {
//        return backgroundTexture;
//    }

//    public Texture getRandomDefaultTexture() {
//        ArrayList<Texture> textures  = new ArrayList<>();
//        textures.add(GamePictureManager.defaultTileTexture);
//        textures.add(GamePictureManager.defaultTileTexture2);
//        textures.add(GamePictureManager.defaultTileTexture3);
//
//        Random rand = new Random();
//        return textures.get(rand.nextInt(textures.size()));
//    }

//    public void checkSeasonIsWinter() {
//        backgroundTexture = getRandomSnowyTexture();
//        pastTexture = backgroundTexture;
//    }

//    public TextureRegion getRandomSnowyTexture(){
//        ArrayList<TextureRegion> textures = new ArrayList<>();
//        textures.add(GamePictureManager.snowyTile);
//        textures.add(GamePictureManager.snowyTile2);
//
//        Random rand = new Random();
//
//        return textures.get(rand.nextInt(textures.size()));
//    }

//    public void checkIsSeasonSpring(){
//        backgroundTexture = new TextureRegion(getRandomDefaultTexture());
//        pastTexture = backgroundTexture;
//    }

    public void setWateredTimeTexture(float wateredTimeTexture) {
        this.wateredTimeTexture = wateredTimeTexture;
    }

    public float getWateredTimeTexture() {
        return wateredTimeTexture;
    }
}
