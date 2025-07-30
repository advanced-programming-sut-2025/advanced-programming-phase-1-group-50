package com.stardew.model.mapInfo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.stardew.model.NPCs.NPC;
import com.stardew.model.TextureID;

import java.awt.*;

public class NpcHome implements Placeable {

    private final Rectangle rectangle;

    private final NPC npc;
    private TextureRegion[][] regions;
    private TextureID texture ;
    public NpcHome(int x, int y, int width, int height , NPC npc) {
        rectangle = new Rectangle(x, y, width, height);
        this.npc = npc;
//        regions = npc.getHomeRegions(npc.getType());
        texture = npc.getHomeTextureByType(npc.getType());

    }

    public Rectangle getBounds() {
        return rectangle;
    }

    public NPC getNpc() {
        return npc;
    }

    public char getSymbol() {
        return 'h';
    }



    @Override
    public TextureID getTexture() {
        return texture;
    }



    public TextureRegion[][] getRegions() {
        return regions;
    }
}
