package com.stardew.model;

import java.util.ArrayList;

public class HabitatDTO {
    private int x;
    private int y;
    private String id;
    private String size;
    private String type;
    private ArrayList<AnimalDTO> animals;
    private TextureID textureID;

    public HabitatDTO() {}

    public HabitatDTO(int x, int y, String id, String size, String type, ArrayList<AnimalDTO> animals, TextureID textureID) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.size = size;
        this.type = type;
        this.animals = animals;
        this.textureID = textureID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public ArrayList<AnimalDTO> getAnimals() {
        return animals;
    }

    public TextureID getTextureID() {
        return textureID;
    }
}
