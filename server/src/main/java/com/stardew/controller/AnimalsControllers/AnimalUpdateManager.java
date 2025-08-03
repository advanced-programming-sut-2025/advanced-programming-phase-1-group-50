package com.stardew.controller.AnimalsControllers;

import com.stardew.model.animals.Animal;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AnimalUpdateManager {
    private static AnimalUpdateManager instance;
    private final long deltaMilliSeconds = 20;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private AnimalUpdateManager() {
        startUpdating();
    }

    public static synchronized AnimalUpdateManager getInstance() {
        if (instance == null) {
            instance = new AnimalUpdateManager();
        }
        return instance;
    }


    public void addAnimal(Animal animal) {
        synchronized (animals) {
            animals.add(animal);
        }
    }

    public void updateAnimals(float delta) {
        synchronized (animals) {
            for (Animal animal : animals) {
                animal.update(delta);
            }
        }
    }

    private void startUpdating() {
        float delta = deltaMilliSeconds / 1000f;
        executor.scheduleAtFixedRate(() -> updateAnimals(delta), 1000, deltaMilliSeconds, TimeUnit.MILLISECONDS);
    }

    public void stopUpdating() {
        executor.shutdown();
    }

}
