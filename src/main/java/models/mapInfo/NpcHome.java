package models.mapInfo;

import models.ColorPrinter;
import models.NPCs.NPC;
import models.Placeable;

import java.awt.*;

public class NpcHome implements Placeable {
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
}
