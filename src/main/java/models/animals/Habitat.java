package models.animals;

import java.util.ArrayList;

public class Habitat {
    private final HabitatType type;
    private final HabitatSize size;
    private final ArrayList<Animal> animals;


    public Habitat(HabitatType type, HabitatSize size) {
        this.type = type;
        this.size = size;
        animals = new ArrayList<>();
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

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public boolean addAnimal(Animal animal) {
        if (getEmptyCapacity() > 0) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

}
