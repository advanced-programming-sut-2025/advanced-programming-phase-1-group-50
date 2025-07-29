package com.stardew.model.cooking;

import com.stardew.model.mapInfo.Eatable;

import java.util.HashMap;

public class Refrigerator {
    private final HashMap<Eatable, Integer> itemsQuantity = new HashMap<>();
    private final int capacity = 36;


    public void addItem(Eatable eatable, int quantity) {
        int oldQuantity = itemsQuantity.getOrDefault(eatable, 0);
        itemsQuantity.put(eatable, oldQuantity + quantity);
    }

    public void removeItem(Eatable eatable, int quantity) {
        int oldQuantity = itemsQuantity.getOrDefault(eatable, 0);
        if (oldQuantity == 0)
            return;
        if (oldQuantity == quantity) {
            itemsQuantity.remove(eatable);
            return;
        }

        itemsQuantity.put(eatable, oldQuantity - quantity);
    }

    public HashMap<Eatable, Integer> getItemsQuantity() {
        return itemsQuantity;
    }

    public int getQuantity(Eatable eatable) {
        return itemsQuantity.getOrDefault(eatable, 0);
    }

    public boolean containEatable(Eatable eatable) {
        return itemsQuantity.getOrDefault(eatable, 0) > 0;
    }

    public boolean hasCapacity() {
        return capacity > itemsQuantity.size();
    }
}
