package com.stardew.view.modelsManager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.stardew.models.animals.Animal;
import com.stardew.models.app.App;

import java.util.ArrayList;

public class AnimalsManager {
    private ArrayList<Animal> animals;

    private void updateList() {
        animals = App.getGame().getCurrentPlayingPlayer().getBackpack().getAllAnimals();
    }

    public void update(float delta) {
        updateList();
        for (Animal animal : animals) {
            animal.update(delta);
        }
    }

    public void render(Batch batch) {
        for (Animal animal : animals) {
            animal.render(batch);
//            System.out.println("animal name: " + animal.getName());
        }
    }

}
