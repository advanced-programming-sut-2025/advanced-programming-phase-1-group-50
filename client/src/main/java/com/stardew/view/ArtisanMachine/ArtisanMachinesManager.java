package com.stardew.view.ArtisanMachine;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.network.Message;

import java.util.ArrayList;

public class ArtisanMachinesManager {
    private static ArtisanMachinesManager instance;
    private final Stage stage;
    private final int gameID;
    private final ArrayList<ArtisanMachineUI> machines = new ArrayList<>();

    private ArtisanMachinesManager(Stage stage, int gameID) {
        this.stage = stage;
        this.gameID = gameID;
    }

    public static synchronized void initialize(Stage stage, int gameID) {
        instance = new ArtisanMachinesManager(stage, gameID);
    }

    public static ArtisanMachinesManager getInstance() {
        return instance;
    }


    public void handleAddNewMachine(Message message) {
        String machineName = message.getFromBody("machineName");
        String machineID = message.getFromBody("machineID");
        int x = message.getIntFromBody("x");
        int y = message.getIntFromBody("y");

        ArtisanAsset artisanAsset = ArtisanAsset.getArtisanAssetByName(machineName);
        if (artisanAsset == null) { System.err.println("ArtisanAsset not found, Couldn't add new machine"); return; }

        addMachine(artisanAsset, machineID, gameID, x, y);
    }

    private void addMachine(ArtisanAsset artisanAsset, String machineID, int gameID, int x, int y) {
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
