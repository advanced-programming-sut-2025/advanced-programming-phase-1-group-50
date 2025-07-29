package com.stardew.model.userInfo;

import com.stardew.model.Tools.Tool;
import com.stardew.model.mapInfo.manuFactor.ArtisanMachine;
import com.stardew.model.mapInfo.Ingredient;
import com.stardew.model.recipes.CookingRecipe;
import com.stardew.model.recipes.CraftingRecipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Backpack {

    private BackpackType type;
    private int capacity = 12;
    private final ArrayList<Tool> tools=  new ArrayList<>();
    private final Hay hay = new Hay();
    private final HashSet<CookingRecipe> cookingRecipes = new HashSet<>();
    private final HashSet<CraftingRecipes> craftingRecipes = new HashSet<>();
    private final ArrayList<ArtisanMachine> artisanMachines = new ArrayList<>();
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Refrigerator refrigerator = new Refrigerator();
    private final TrashCan trashCan = new TrashCan();
    private  Player player;


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
        cookingRecipes.add(CookingRecipe.FriedEgg);
        cookingRecipes.add(CookingRecipe.BakedFish);
        cookingRecipes.add(CookingRecipe.Salad);
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
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

    public boolean hasCapacity() {
        return capacity > ingredientQuantity.size();
    }

    public void addTool(Tool tool) {
        tools.add(tool);
        player.addInventoryItem(tool);

    }

    public ArrayList<Tool> getTools() {
        return tools;
    }

    public void removeTool(Tool tool) {
        tools.remove(tool);
        player.removeInventoryItem(tool);

    }



    public void addIngredients(Ingredient ingredient, int quantity) {

        if(capacity > ingredientQuantity.size()){

            int value = ingredientQuantity.getOrDefault(ingredient , 0);
            ingredientQuantity.put(ingredient, value + quantity);

            if (ingredientQuantity.get(ingredient) == 0) {
                ingredientQuantity.remove(ingredient);
            }

        }
        player.addInventoryItem(ingredient);



    }

    public void removeIngredients(Ingredient ingredient, int quantity) {
        int value = ingredientQuantity.getOrDefault(ingredient , 0);
        if(value == quantity){
            ingredientQuantity.remove(ingredient);
            player.removeInventoryItem(ingredient);
        } else {
            ingredientQuantity.put(ingredient, value - quantity);
        }


    }
    public void inventoryTrash(Ingredient ingredient , int quantity)
    {
        int value = ingredientQuantity.getOrDefault(ingredient , 0);
        if(value == quantity){
            ingredientQuantity.remove(ingredient);


        } else {

            ingredientQuantity.put(ingredient, value - quantity);
        }
        int returnPercentage = trashCan.getReturnValuePercentage();

        int refund = 0;
        if(ingredient instanceof AnimalGood ag)
            refund = ag.getSellPrice() * quantity * returnPercentage / 100;
        else if(ingredient instanceof Fish f)
            refund = f.getSellPrice() * quantity * returnPercentage / 100;
        else if(ingredient instanceof Crop c)
            refund = c.getSellPrice() * quantity * returnPercentage / 100;
        else if(ingredient instanceof Fruit fr)
            refund = fr.getSellPrice() * quantity * returnPercentage / 100;
        else if(ingredient instanceof Food fd)
            refund = fd.getSellPrice() * quantity * returnPercentage / 100;
        else if(ingredient instanceof ForagingMineral fm)
            refund = fm.getSellPrice() * quantity * returnPercentage / 100;

        if (refund > 0) {
            Coin coin = new Coin();
            ingredientQuantity.put(coin, ingredientQuantity.getOrDefault(coin, 0) + refund);
        }
    }



    public HashMap<Ingredient, Integer> getIngredientQuantity() {
        return ingredientQuantity;
    }

    public BackpackType getType() {
        return type;
    }

    public void addArtisanMachine(ArtisanMachine artisanMachine) {
        artisanMachines.add(artisanMachine);
    }

    public ArrayList<ArtisanMachine> getArtisanMachines() {
        return artisanMachines;
    }

    public ArtisanMachine getArtisanMachineByName(String name) {
        ArtisanMachine machineIns = ArtisanMachine.getArtisanMachineByRecipe(CraftingRecipes.getRecipeByName(name));

        if (machineIns == null) return null;

        for (ArtisanMachine machine : artisanMachines) {
            if (machine.getClass() == machineIns.getClass())
                return machine;
        }
        return null;
    }

    public void addRecipe(CraftingRecipes craftingRecipe) {
        craftingRecipes.add(craftingRecipe);
    }

    public HashSet<CraftingRecipes> getCraftingRecipes() {
        return craftingRecipes;
    }

    public void addRecipe(CookingRecipe cookingRecipe) {
        cookingRecipes.add(cookingRecipe);
    }

    public HashSet<CookingRecipe> getCookingRecipes() {
        return cookingRecipes;
    }

    public boolean containRecipe(CookingRecipe recipe) {
        return cookingRecipes.contains(recipe);
    }

    public boolean containRecipe(CraftingRecipes recipe) {
        return craftingRecipes.contains(recipe);
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public ArrayList<Animal> getAllAnimals() {
        return animals;
    }

    public Animal getAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        return null;
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public void increaseHay(int number) {
        hay.increaseNumber(number);
    }

    public void decreaseHay(int number) {
        hay.decreaseNumber(number);
        hay.setNumber(Math.max(hay.getNumber(), 0));
    }

    public boolean hasEnoughHay(int number) {
        return hay.getNumber() >= number;
    }

    public int getNumberOfHay() {
        return hay.getNumber();
    }
    public TrashCan getTrashCan() {
        return trashCan;
    }
}
