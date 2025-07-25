package com.stardew.view.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Result;
import com.stardew.models.animals.Animal;
import com.stardew.models.animals.Habitat;

import java.util.ArrayList;

public class HabitatWindow extends CloseableWindow {
    private final AnimalsController controller = new AnimalsController();


    public HabitatWindow(Stage stage, Habitat habitat, float x, float y) {
        super(habitat.getSize().name() + " " + habitat.getType().name(), stage);

        pad(25, 5, 20, 0);
        defaults().space(5);
        pack();
        setPosition(x, y);

        ArrayList<Animal> animals = habitat.getAnimals();

        setSize(180, 120 + 65 * animals.size());// change dynamically

        TextButton showAnimalsProductsButton = new TextButton("Animals_Products", GamePictureManager.skin);
        showAnimalsProductsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.animalProduces(habitat);
                showResult(result);
            }
        });
        add(showAnimalsProductsButton).row();

        for (Animal animal : animals) {
            Image animalImage = new Image(animal.getType().getNormalTexture());

            SmartTooltip tooltip = SmartTooltip.getInstance();

            animalImage.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.addActor(new AnimalOptionWindow(stage, animal, x + getX(), y + getY()));
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(controller.animalInfo(animal).getMessage());
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
            add(animalImage).row();
        }
    }
}
