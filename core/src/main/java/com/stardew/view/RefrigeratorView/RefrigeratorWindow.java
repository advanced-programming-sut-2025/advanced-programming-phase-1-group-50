package com.stardew.view.RefrigeratorView;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.CookingController;
import com.stardew.controller.CookingAndCraftingControllers.RefrigeratorGridController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.cooking.Food;
import com.stardew.view.InventoryWindows.BackpackGridActor;
import com.stardew.view.windows.CloseableWindow;

public class RefrigeratorWindow extends CloseableWindow {
    private final RefrigeratorGridActor refrigeratorGridActor;
    private final BackpackGridActor backpackGridActor;
    private final TextButton deliverToBackpackButton;
    private final TextButton deliverToRefrigeratorButton;
    private final RefrigeratorGridController refrigeratorGridController;
    //TODO put BackpackGridController here and initialize with its own actor in constructor
    private final CookingController cookingController;


    public RefrigeratorWindow(Stage stage) {
        super("Refrigerator ", stage);

        pad(25, 5, 20, 0);
        pack();
        setSize(900, 700);
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2);


        refrigeratorGridActor = new RefrigeratorGridActor();
        backpackGridActor = new BackpackGridActor();
        deliverToBackpackButton = new TextButton("DeliverToBackpack", GamePictureManager.skin);
        deliverToRefrigeratorButton = new TextButton("DeliverToRefrigerator", GamePictureManager.skin);
        refrigeratorGridController = new RefrigeratorGridController(refrigeratorGridActor);
        //TODO initialize controller for backpack
        cookingController = new CookingController();

        add(refrigeratorGridActor).expand().fill().pad(60, 40, 20, 20);
        add(deliverToBackpackButton).pad(60, 20, 20, 20);
        row();

        add(backpackGridActor).expand().fill().pad(20, 20, 20, 20);
        add(deliverToRefrigeratorButton).pad(20, 20, 20, 20);
        row();

        deliverToBackpackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Food selectedFood = refrigeratorGridController.getSelectedItem();
                if (selectedFood == null)
                    showResult(new Result(false, "Please select a food"));
                else {
                    showResult(cookingController.cookingRefrigeratorPutPick("pick", selectedFood.name()));
                    refrigeratorGridActor.update();
                    //TODO update backpackGridActor
                }
            }
        });

        deliverToRefrigeratorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO get selected by backpack controller
                //TODO check to be not null
                //TODO check to be eatable
                //TODO call cookingController
                //TODO update 2 grid
            }
        });

    }
}
