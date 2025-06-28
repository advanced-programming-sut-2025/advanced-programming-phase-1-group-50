package com.stardew.models.animals;

import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.Placeable;

import java.awt.*;
import java.util.ArrayList;

public class Habitat implements Placeable {
    private final String backgroundCode = BackgroundColors.YELLOW;
    private final  String colorCode = ColorPrinter.BRIGHT_RED;
    private final HabitatType type;
    private final HabitatSize size;
    private final ArrayList<Animal> animals;
    private final Rectangle bounds;


    public Habitat(HabitatType type, HabitatSize size, int x, int y) {
        this.type = type;
        this.size = size;
        animals = new ArrayList<>();
        this.bounds = new Rectangle(x, y, type.getLengthX(), type.getLengthY());
    }

    public HabitatType getType() {
        return type;
    }

    public HabitatSize getSize() {
        return size;
    }

    public int getTotalCapacity() {
        return size.getCapacity();
    }

    public int getEmptyCapacity() {
        return size.getCapacity() - animals.size();
    }

    public boolean hasEmptyCapacity() {
        return getEmptyCapacity() > 0;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
        if (hasEmptyCapacity()) {
            animals.add(animal);
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public static HabitatType getHabitatTypeByInput(String input) {
        if (input.equalsIgnoreCase("barn") ||
                input.equalsIgnoreCase("big_barn") ||
                input.equalsIgnoreCase("deluxe_barn"))
            return HabitatType.Barn;
        if (input.equalsIgnoreCase("coop") ||
                input.equalsIgnoreCase("big_coop") ||
                input.equalsIgnoreCase("deluxe_coop"))
            return HabitatType.Coop;

        return null;
    }

    public static HabitatSize getHabitatSizeByInput(String input) {
        if (input.equalsIgnoreCase("barn") || input.equalsIgnoreCase("coop"))
            return HabitatSize.Regular;
        if (input.equalsIgnoreCase("big_barn") || input.equalsIgnoreCase("big_coop"))
            return HabitatSize.Big;
        if (input.equalsIgnoreCase("deluxe_barn") || input.equalsIgnoreCase("deluxe_coop"))
            return HabitatSize.Deluxe;

        return null;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    public char getSymbol() {
        if (type == HabitatType.Barn) {
            return 'b';
        }
        return 'c';
    }

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

}
