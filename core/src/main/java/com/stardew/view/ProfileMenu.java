package com.stardew.view;

import java.util.Scanner;
import java.util.regex.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.controller.ProfileMenuController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;
import com.stardew.models.enums.ProfileMenuCommands;


public class ProfileMenu implements AppMenu , Screen {
    private final  ProfileMenuController controller = new ProfileMenuController();
    private Stage stage;
    private final Label profileMenu;
    private final TextButton changePassword;
    private final TextButton changeUsername;
    private final TextButton changeNickname;
    private final TextButton changeEmail;
    private final TextButton showUserInfo;
    private final TextButton back;
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher ;


        if(com.stardew.models.enums.ProfileMenuCommands.ShowCurrentMenu.getMatcher(input)!=null){
            System.out.println("profile menu");
        }
        else if(ProfileMenuCommands.ChangePassword.getMatcher(input)!=null){
            matcher = com.stardew.models.enums.ProfileMenuCommands.ChangePassword.getMatcher(input);
            String newPassword = matcher.group(1).trim();
            String oldPassword = matcher.group(2).trim();
            System.out.println(controller.changePassword(oldPassword, newPassword));

        }
        else if(com.stardew.models.enums.ProfileMenuCommands.ChangeUsername.getMatcher(input)!=null){
            matcher = com.stardew.models.enums.ProfileMenuCommands.ChangeUsername.getMatcher(input);
            String newUsername = matcher.group(1).trim();;
            System.out.println(controller.changeUsername(newUsername));
        }
        else if(com.stardew.models.enums.ProfileMenuCommands.ChangeNickname.getMatcher(input)!=null){
            matcher = com.stardew.models.enums.ProfileMenuCommands.ChangeNickname.getMatcher(input);
            String newNickname = matcher.group(1).trim();
            System.out.println(controller.changeNickname(newNickname));
        }
        else if(com.stardew.models.enums.ProfileMenuCommands.ChangeEmail.getMatcher(input)!=null){
            matcher = com.stardew.models.enums.ProfileMenuCommands.ChangeEmail.getMatcher(input);
            String newEmail = matcher.group(1).trim();
            System.out.println(controller.changeEmail(newEmail));

        }
        else if(com.stardew.models.enums.ProfileMenuCommands.UserInfo.getMatcher(input)!=null){
          System.out.println(controller.showUserInfo());
        }
        else if(com.stardew.models.enums.ProfileMenuCommands.ExitMenu.getMatcher(input)!=null){
            App.setMenu(Menus.MainMenu);
        }
        else {
            System.out.println("invalid command");
        }

    }

    public ProfileMenu() {
        stage = new Stage();
        profileMenu = new Label("Profile Menu" , GamePictureManager.skin);

        changePassword = new TextButton("Change Password", GamePictureManager.skin);
        changePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleChangePassword();
            }
        });
        changeUsername = new TextButton("Change Username", GamePictureManager.skin);
        changeUsername.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleChangeUsername();
            }
        });
        changeEmail = new TextButton("Change Email", GamePictureManager.skin);
        changeEmail.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleChangeEmail();
            }
        });
        changeNickname = new TextButton("Change Nickname", GamePictureManager.skin);
        changeNickname.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleChangeNickname();
            }
        });
        showUserInfo = new TextButton("Show UserInfo", GamePictureManager.skin);

        showUserInfo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleShowUserInfo();
            }
        });
        back = new TextButton("Back", GamePictureManager.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Screen screen = Main.getMain().getScreen();
                MainMenu mainMenu = new MainMenu();
                Main.getMain().setScreen(mainMenu);
                screen.dispose();

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
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);

        Table table = new Table();
        table.setFillParent(true);
        table.center();

        profileMenu.setFontScale(2);

        table.add(profileMenu).padBottom(40).row();
        table.add(changePassword).width(250).height(50).pad(10).row();
        table.add(changeUsername).width(250).height(50).pad(10).row();
        table.add(changeNickname).width(250).height(50).pad(10).row();
        table.add(changeEmail).width(250).height(50).pad(10).row();
        table.add(showUserInfo).width(250).height(50).pad(10).row();
        table.add(back).width(250).height(50).pad(10).row();

        stage.addActor(table);
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
