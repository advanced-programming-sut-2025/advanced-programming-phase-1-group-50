package com.stardew.models.waterBodies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Placeable;
import com.stardew.models.stores.CarpenterShop;

import java.awt.*;

public class Lake extends WaterBody implements Placeable {
    private final String backgroundCode = BackgroundColors.BLUE;
    private final String colorCode = ColorPrinter.BLUE;
    private final Rectangle bounds;
    private final TextureRegion texture = new TextureRegion(GamePictureManager.lakeTexture);
    public Lake(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);

    }
    public Rectangle getBounds() {
        return bounds;
    }
    public char getSymbol() {
        return 'L';
    }

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.BLUE;
    }

}
