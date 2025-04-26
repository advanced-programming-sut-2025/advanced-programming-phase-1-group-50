package models.foraging;

import models.Placeable;

import java.awt.*;

public class Tree implements Placeable {
    private TreeType type;
    private Rectangle bounds;
    public Tree(int x, int y, int width, int height){
        this.bounds = new Rectangle(x, y, width, height);
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public char getSymbol() {
        return 'T';
    }
}
