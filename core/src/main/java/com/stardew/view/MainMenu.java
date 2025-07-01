package com.stardew.view;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.controller.MainMenuController;
import com.stardew.models.GameAssetManagers.GamePictureManager;

public class MainMenu implements AppMenu , Screen {
    private final MainMenuController controller = new MainMenuController();
    private Stage stage;
    private final Label mainMenu;
    private final TextButton profileButton;
    private final TextButton gameButton;
    private final TextButton logoutButton;
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();


    if (com.stardew.models.enums.MainMenuCommands.ShowCurrentMenu.getMatcher(input)!=null){
        System.out.println(controller.shewCurrentMenu());
    }
    else if(com.stardew.models.enums.MainMenuCommands.UserLogout.getMatcher(input)!=null){
        System.out.println(controller.logout());
    }
    else if(com.stardew.models.enums.MainMenuCommands.EnterProfileMenu.getMatcher(input)!=null){
        System.out.println(controller.enterProfileMenu());
    }
    else if(com.stardew.models.enums.MainMenuCommands.EnterGameMenu.getMatcher(input)!=null){
        System.out.println(controller.enterGameMenu());
    }
    else if(com.stardew.models.enums.MainMenuCommands.ExitMenu.getMatcher(input)!=null){
        System.out.println(controller.exitMainMenu());
    }
    else{
        System.out.println("invalid command");
    }

    }

    public MainMenu() {
        stage = new Stage();
        mainMenu = new Label("Main Menu" , GamePictureManager.skin);
        profileButton = new TextButton("Profile" , GamePictureManager.skin);
        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.goToProfileMenu();
            }
        });
        gameButton = new TextButton("Game" , GamePictureManager.skin);
        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        logoutButton = new TextButton("Logout" , GamePictureManager.skin);

        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        controller.setView(this);


    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        mainMenu.setPosition(centerX - mainMenu.getWidth() / 2, centerY + 150);

        profileButton.setSize(200, 40);
        profileButton.setPosition(centerX - 100, centerY + 60);

        gameButton.setSize(200, 40);
        gameButton.setPosition(centerX - 100, centerY);

        logoutButton.setSize(200, 40);
        logoutButton.setPosition(centerX - 100, centerY - 60);

        stage.addActor(mainMenu);
        stage.addActor(profileButton);
        stage.addActor(gameButton);
        stage.addActor(logoutButton);


        MenuAnimationController.addHoverAnimation(profileButton);
        MenuAnimationController.addHoverAnimation(gameButton);
        MenuAnimationController.addHoverAnimation(logoutButton);
    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(0 , 0  , 0 , 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
