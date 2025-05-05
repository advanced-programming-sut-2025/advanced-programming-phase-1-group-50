package models.userInfo;

import models.manuFactor.Ingredient;
import models.tools.Tool;

import java.util.ArrayList;
import java.util.HashMap;

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
    private ArrayList<Ingredient> ingredients; //should delete!!
    private HashMap<Ingredient, Integer> ingredientQuantity = new HashMap<>();
    public Backpack(Type type){
        this.type = type;
        switch(type){
            case Primary:
                this.type = Type.Primary;
                this.capacity = 12;
                break;
            case Big:
                this.type = Type.Big;
                this.capacity = 24;
                break;
            case Deluxe:
                this.type = Type.Deluxe;
                this.capacity = Integer.MAX_VALUE;
        }
    }

    public void changeType(Type type) {
        switch(type){
            case Big:
                this.type = Type.Big;
                this.capacity = 24;
                 break;
            case Primary:
                this.type = Type.Primary;
                this.capacity = 12;
                break;
            case Deluxe:
                this.capacity = Integer.MAX_VALUE;
                this.type = Type.Deluxe;
        }

    }

    public int getCapacity() {
        return capacity;
    }

    public void addTool(Tool tool) {
        tools.add(tool);

    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void removeTool(Tool tool) {
        tools.remove(tool);

    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredients(Ingredient ingredient, int quantity) {
        ingredientQuantity.put(ingredient, quantity);

    }

    public void removeIngredients(Ingredient ingredient, int quantity) {
        int value = ingredientQuantity.getOrDefault(ingredient , 0);
        if(value == quantity){
            ingredientQuantity.remove(ingredient);
        }
        else {
            ingredientQuantity.put(ingredient, value - quantity);
        }

    }

    public HashMap<Ingredient, Integer> getIngredientQuantity() {
        return ingredientQuantity;
    }
}
