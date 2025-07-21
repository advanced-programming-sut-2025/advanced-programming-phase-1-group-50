package com.stardew.view.modelsManager;

import com.stardew.models.app.App;
import com.stardew.models.manuFactor.ArtisanMachine;

import java.util.ArrayList;

public class ArtisanMachinesManager {
    private ArrayList<ArtisanMachine> artisanMachines;
    private int previousUpdateHour;
    private int previousUpdateDay;

    public ArtisanMachinesManager() {
        artisanMachines = App.getGame().getCurrentPlayingPlayer().getBackpack().getArtisanMachines();
        previousUpdateHour = App.getGame().getTime().getHour();
        previousUpdateDay = App.getGame().getTime().getDate();
    }

    private void updateList() {
        artisanMachines = App.getGame().getCurrentPlayingPlayer().getBackpack().getArtisanMachines();
    }

    private boolean isChangedTime() {
        int nowHour = App.getGame().getTime().getHour();
        int nowDay = App.getGame().getTime().getDate();
        return nowHour != previousUpdateHour || nowDay != previousUpdateDay;
    }

    public void update() {
        if (isChangedTime()) {
            previousUpdateHour = App.getGame().getTime().getHour();
            previousUpdateDay = App.getGame().getTime().getDate();
            updateList();
            for (ArtisanMachine machine : artisanMachines) {
                machine.updateMachine();
            }
        }
    }
}
