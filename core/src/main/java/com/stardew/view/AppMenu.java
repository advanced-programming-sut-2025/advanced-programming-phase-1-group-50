package com.stardew.view;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Scanner;

public interface AppMenu {
    void check(Scanner scanner);
    Stage getStage();
}
