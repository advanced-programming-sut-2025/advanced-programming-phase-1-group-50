package com.stardew.view.modelsManager;

import com.stardew.models.app.App;
import com.stardew.models.manuFactor.ArtisanMachine;

import java.util.ArrayList;

public class ArtisanMachinesManager {
    private ArrayList<ArtisanMachine> artisanMachines;
    private int previousHour;

    public ArtisanMachinesManager() {
        artisanMachines = App.getGame().getCurrentPlayingPlayer().getBackpack().getArtisanMachines();
        previousHour = App.getGame().getTime().getHour();
    }

    private void updateList() {
        artisanMachines = App.getGame().getCurrentPlayingPlayer().getBackpack().getArtisanMachines();
    }

    private boolean isChangedTime() {
        int now = App.getGame().getTime().getHour();
        return now != previousHour;
    }

    public void update() {
        if (isChangedTime()) {
            previousHour = App.getGame().getTime().getHour();
            updateList();
            for (ArtisanMachine machine : artisanMachines) {
                machine.updateMachine();
            }
        }
    }
}
