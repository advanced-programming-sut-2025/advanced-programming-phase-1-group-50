package com.stardew.models.mapInfo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.NPCs.NPC;
import com.stardew.models.Placeable;

import java.awt.*;

public class NpcHome implements Placeable {
    private final  String backgroundCode = BackgroundColors.CYAN;
    private final Rectangle rectangle;
    private final  String colorCode = ColorPrinter.BRIGHT_PURPLE;
    private final NPC npc;
    public NpcHome(int x, int y, int width, int height , NPC npc) {
        rectangle = new Rectangle(x, y, width, height);
        this.npc = npc;
    }

    public Rectangle getBounds() {
        return rectangle;
    }

    public NPC getNpc() {
        return npc;
    }

    public char getSymbol() {
        return npc.getSymbol();
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
        return null;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.WHITE;
    }

}
