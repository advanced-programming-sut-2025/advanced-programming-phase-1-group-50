package com.stardew.view;

import java.util.Scanner;
import java.util.regex.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.controller.ProfileMenuController;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.models.GameAssetManagers.GamePictureManager;



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
    private final AppMenu menu = this;
//    private final SelectBox<String> avatar;
//    private Image avatarImage;
    public void check(Scanner scanner) {


    }

    public ProfileMenu() {
        stage = new Stage();
        profileMenu = new Label("Profile Menu" , GamePictureManager.skin);



        changePassword = new TextButton("Change Password", GamePictureManager.skin);
        changePassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //controller.handleChangePassword();
                stage.addActor(new ChangeInfoWindow(stage , "Change Password" , menu));

            }
        });
        changeUsername = new TextButton("Change Username", GamePictureManager.skin);
        changeUsername.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new ChangeInfoWindow(stage , "Change Username" , menu));
                //controller.handleChangeUsername();
            }
        });
        changeEmail = new TextButton("Change Email", GamePictureManager.skin);
        changeEmail.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addActor(new ChangeInfoWindow(stage , "Change Email" , menu));
                //controller.handleChangeEmail();
            }
        });
        changeNickname = new TextButton("Change Nickname", GamePictureManager.skin);
        changeNickname.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //controller.handleChangeNickname();
                stage.addActor(new ChangeInfoWindow(stage , "nickname" , menu));
            }
        });
        showUserInfo = new TextButton("Show UserInfo", GamePictureManager.skin);

        showUserInfo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //controller.handleShowUserInfo();
                stage.addActor(new ChangeInfoWindow(stage , "Show UserInfo" , menu));
            }
        });
        back = new TextButton("Back", GamePictureManager.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Screen screen = Main.getMain().getScreen();
                MainMenu mainMenu = new MainMenu(LoggedInUser.getUser().getNickname());
                Main.getMain().setScreen(mainMenu);
                screen.dispose();

            }
        });
        Array<String> avatars = new Array<>();
        avatars.add("Abigail");
        avatars.add("Robin");
        avatars.add("Leah");
        avatars.add("Sebastian");
        avatars.add("Harvey");



//        avatar = new SelectBox<>(GamePictureManager.skin);
//        avatar.setItems(avatars);
//
//        if(App.getLoggedInUser()!=null){
//            avatarImage = new Image(App.getLoggedInUser().getAvatar().getAvatar());
//        }
//        else {
//            avatarImage = new Image(Avatar.abigail.getAvatar());
//        }
//
//
//
//        avatar.addListener(new ChangeListener() {
//            public void changed(ChangeEvent event, Actor actor) {
////                String result = avatar.getSelected();
////                App.getLoggedInUser().setAvatar(getAvatarByName(result));
////
////                avatarImage.setDrawable(new TextureRegionDrawable(getAvatarByName(result).getAvatar()));
//
//            }
//        });

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
//        table.add(avatar).width(250).height(50).pad(10).row();
//        table.add(avatarImage).width(250).height(250).pad(10).row();

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

//    public Avatar getAvatarByName(String name) {
//        return switch (name) {
//            case "Abigail" -> Avatar.abigail;
//            case "Robin" -> Avatar.robin;
//            case "Leah" -> Avatar.leah;
//            case "Sebastian" -> Avatar.sebastian;
//            case "Harvey" -> Avatar.harvey;
//            default -> null;
//        };
//    }
}
