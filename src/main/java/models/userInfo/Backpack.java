package models.userInfo;

import models.manuFactor.Ingredient;
import models.tools.Tool;

import java.util.ArrayList;
import java.util.HashMap;

public class Backpack {


    private BackpackType type;
    private int capacity = 12;
    private final ArrayList<Tool> tools=  new ArrayList<>();

    private final HashMap<Ingredient, Integer> ingredientQuantity = new HashMap<>();
    public Backpack(BackpackType type){
        this.type = type;
        switch(type){
            case Primary:
                break;
            case Big:
                this.capacity = 24;
                break;
            case Deluxe:
                this.capacity = Integer.MAX_VALUE;
        }
    }

    public void changeType(BackpackType type) {
        switch(type){
            case Big:
                this.type = BackpackType.Big;
                this.capacity = 24;
                 break;
            case Primary:
                this.type = BackpackType.Primary;
                this.capacity = 12;
                break;
            case Deluxe:
                this.capacity = Integer.MAX_VALUE;
                this.type = BackpackType.Deluxe;
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
    public BackpackType getType() {
        return type;
    }
}
