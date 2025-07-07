package com.stardew.models.mapInfo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Placeable;
import com.stardew.models.manuFactor.Ingredient;

import java.awt.*;

public class Stone implements Ingredient , Placeable {
    private final String backgroundCode = BackgroundColors.BLACK;
    private final String colorCode = ColorPrinter.GRAY;
    private Rectangle bounds;
    private Texture texture = GamePictureManager.farmBoulderTexture;

    public Stone() {

    }

    public Stone(int x, int y) {
        this.bounds = new Rectangle(x, y, 1, 1);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public char getSymbol() {
        return 'S';
    }

    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Stone;
    }

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String toString() {
        return "Stone";
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

    @Override
    public TextureRegion getTexture() {
        return new TextureRegion(texture);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
