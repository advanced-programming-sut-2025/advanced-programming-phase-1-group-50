package models.userInfo;

import models.manuFactor.Ingredient;
import models.tools.Tool;

import java.util.ArrayList;

public class Backpack {
    enum Type {
        Primary,
        //capacity = 12
        Big,
        // capacity = 24
        Deluxe;
        // capacity = infinite
    }
    private Type type;
    private int capacity = 12;
    private ArrayList<Tool> tools;
    private ArrayList<Ingredient> ingredients;


    public void changeType() {
        //changeCapacity
    }

    public int getCapacity() {
        return capacity;
    }

    public void addTool(Tool tool) {

    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void removeTool(Tool tool) {

    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredients(Ingredient ingredient) {

    }

    public void removeIngredients(Ingredient ingredient) {

    }
}
