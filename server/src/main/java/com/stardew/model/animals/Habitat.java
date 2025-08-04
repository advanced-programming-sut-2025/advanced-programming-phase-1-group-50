package com.stardew.model.animals;

import com.stardew.model.AnimalDTO;
import com.stardew.model.HabitatDTO;
import com.stardew.model.TextureID;
import com.stardew.model.mapInfo.Placeable;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class Habitat implements Placeable {

    private final HabitatType type;
    private final HabitatSize size;
    private final ArrayList<Animal> animals;
    private final Rectangle bounds;
    private final Vec2 position;
    private final TextureID textureRegion;
    private final String id;


    public Habitat(HabitatType type, HabitatSize size, int x, int y) {
        this.type = type;
        this.size = size;
        animals = new ArrayList<>();
        this.bounds = new Rectangle(x, y, type.getLengthX(), type.getLengthY());
        this.position = new Vec2(x, y);
        this.id = UUID.randomUUID().toString();

        if (type == HabitatType.Barn) {
            switch (size) {
                case Regular -> textureRegion = TextureID.barnTexture;
                case Big -> textureRegion = TextureID.bigBarnTexture;
                case Deluxe -> textureRegion = TextureID.deluxeBarnTexture;
                default -> textureRegion = null;
            }
        } else if (type == HabitatType.Coop) {
            switch (size) {
                case Regular -> textureRegion = TextureID.coopTexture;
                case Big -> textureRegion = TextureID.bigCoopTexture;
                case Deluxe -> textureRegion = TextureID.deluxeCoopTexture;
                default -> textureRegion = null;
            }
        } else textureRegion = null;
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
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public Vec2 getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public HabitatDTO toHabitatDTO() {
        ArrayList<AnimalDTO> animalDTOs = new ArrayList<>();
        for (Animal animal : animals) animalDTOs.add(animal.toDTO());
        return new HabitatDTO(((int) position.x), ((int) position.y), id, size.name(), type.name(), animalDTOs, textureRegion);
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
    public TextureID getTexture() {
        return textureRegion;
    }


//    public void render(Batch batch) {
//        batch.draw(textureRegion, position.x * TextureID.TILE_SIZE, position.y * TextureID.TILE_SIZE);
//    }
}
