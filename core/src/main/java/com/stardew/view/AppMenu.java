package com.stardew.view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.Scanner;

public interface AppMenu {
    void check(Scanner scanner);
    Stage getStage();

}
