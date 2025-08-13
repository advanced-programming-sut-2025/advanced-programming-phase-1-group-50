package com.stardew.view.Stores;

import java.util.ArrayList;
import java.util.List;

public interface UpdatableWindow {

    List<UpdatableWindow> allWindows = new ArrayList<>();

    void refresh();

    default void addWindowToList() {
        allWindows.add(this);
    }

    default void removeWindowFromList() {
        allWindows.remove(this);
    }

    static void refreshAll() {
        for (UpdatableWindow window : new ArrayList<>(allWindows)) {
            window.refresh();
        }
    }
}

