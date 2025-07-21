package com.stardew.view.RefrigeratorView;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.CookingAndCraftingControllers.CookingController;
import com.stardew.controller.CookingAndCraftingControllers.RefrigeratorGridController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.InventoryItem;
import com.stardew.models.Result;
import com.stardew.models.cooking.Eatable;
import com.stardew.view.InventoryWindows.BackpackGridActor;
import com.stardew.view.windows.CloseableWindow;

public class RefrigeratorWindow extends CloseableWindow {
    private final RefrigeratorGridActor refrigeratorGridActor;
    private final BackpackGridActor backpackGridActor;
    private final ScrollPane backpackScrollPane;
    private final TextButton deliverToBackpackButton;
    private final TextButton deliverToRefrigeratorButton;
    private final TextButton eatButton;
    private final RefrigeratorGridController refrigeratorGridController;
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
        backpackScrollPane = new ScrollPane(backpackGridActor, GamePictureManager.skin);
        deliverToBackpackButton = new TextButton("DeliverToBackpack", GamePictureManager.skin);
        deliverToRefrigeratorButton = new TextButton("DeliverToRefrigerator", GamePictureManager.skin);
        eatButton = new TextButton("Eat Item", GamePictureManager.skin);
        refrigeratorGridController = new RefrigeratorGridController(refrigeratorGridActor);
        cookingController = new CookingController();

        add(refrigeratorGridActor).expand().fill().pad(60, 40, 10, 20);
        Table table = new Table();
        table.add(deliverToBackpackButton).row();
        table.add(eatButton);
        add(table).expand().fill().pad(60, 20, 10, 20);
        row();


        backpackScrollPane.setFadeScrollBars(false);
        backpackScrollPane.setScrollingDisabled(true, false);
        backpackScrollPane.setOverscroll(true, true);

        add(backpackScrollPane).height(180).width(505).expand().fill().pad(20, 40, 80, 20);
        backpackScrollPane.layout();
        backpackScrollPane.setScrollPercentY(1f);


        add(deliverToRefrigeratorButton).pad(20, 20, 80, 20);
        row();

        deliverToBackpackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Eatable selectedItem = refrigeratorGridController.getSelectedItem();
                if (selectedItem == null)
                    showResult(new Result(false, "Please select an item!!"));
                else {
                    showResult(cookingController.pickFromRefrigerator(selectedItem));
                    refrigeratorGridActor.update();
                    backpackGridActor.update();
                }
            }
        });

        deliverToRefrigeratorButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InventoryItem selectedItem = backpackGridActor.getInventoryItemByXAndY(
                    backpackGridActor.getSelectedX(), backpackGridActor.getSelectedY());
                if (selectedItem == null)
                    showResult(new Result(false, "Please select a food"));
                else {
                    showResult(cookingController.putInRefrigerator(selectedItem));
                    refrigeratorGridActor.update();
                    backpackGridActor.update();
                }
            }
        });

        eatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Eatable selectedItem = refrigeratorGridController.getSelectedItem();
                if (selectedItem == null)
                    showResult(new Result(false, "Please select an item!!"));
                else {
                    showResult(cookingController.eat(selectedItem));
                    refrigeratorGridActor.update();
                }
            }
        });

    }
}
