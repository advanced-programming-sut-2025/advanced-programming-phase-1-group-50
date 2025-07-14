package com.stardew.controller.CookingAndCraftingControllers;

import com.stardew.models.cooking.Eatable;
import com.stardew.view.RefrigeratorView.RefrigeratorGridActor;
import com.stardew.view.RefrigeratorView.RefrigeratorItem;

public class RefrigeratorGridController {
    private final RefrigeratorGridActor refrigeratorGridActor;

    public RefrigeratorGridController(RefrigeratorGridActor refrigeratorGridActor) {
        this.refrigeratorGridActor = refrigeratorGridActor;
    }

    public Eatable getSelectedItem() {
        RefrigeratorItem[][] items = refrigeratorGridActor.getItems();
        int selectedX = refrigeratorGridActor.getSelectedX();
        int selectedY = refrigeratorGridActor.getSelectedY();

        if (selectedX == -1 || selectedY == -1)
            return null;

        if (items[selectedX][selectedY].isOccupied())
            return items[selectedX][selectedY].getEatableItem();

        return null;
    }
}
