package com.stardew.view.ArtisanMachine;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.GameAssetManagers.ArtisanAsset;

import java.util.ArrayList;

public class ArtisanMachinesManager {
    private static ArtisanMachinesManager instance;
    private final Stage stage;
    private final ArrayList<ArtisanMachineUI> machines = new ArrayList<>();

    private ArtisanMachinesManager(Stage stage) {
        this.stage = stage;
    }

    public static synchronized void initialize(Stage stage) {
        instance = new ArtisanMachinesManager(stage);
    }

    public static ArtisanMachinesManager getInstance() {
        return instance;
    }



    public void addMachine(ArtisanAsset artisanAsset, String machineID, int gameID, int x, int y) {
        synchronized (machines) {
            machines.add(new ArtisanMachineUI(stage, artisanAsset, machineID, gameID, x, y));
        }
    }

    public void updateMachines() {
        synchronized (machines) {
            for (ArtisanMachineUI machine : machines) {
                machine.updateMachine();
            }
        }
    }

    public void updateMachine(String machineID) {
        synchronized (machines) {
            for (ArtisanMachineUI machine : machines) {
                if (machine.getMachineID().equals(machineID)) {
                    machine.updateMachine();
                    break;
                }
            }
        }
    }
}
