package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.controller.LoginAndRegisterController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.Main;

import java.util.Scanner;

public class LoginAndRegisterMenu implements AppMenu , Screen {
    private  LoginAndRegisterController controller = new LoginAndRegisterController();
    private  Stage stage = new Stage(new ScreenViewport());
    private  Skin skin ;
    private  Label register ;
    private  TextField usernameTextField ;
    private  TextField passwordTextField ;
    private  TextField confirmPasswordTextField;
    private  TextField nicknameTextField;
    private  TextField emailTextField ;

    private  SelectBox<String> genderSelectBox ;
    private  Label randomPasswordLabel;
    private  TextButton randomPasswordButton;
    private  TextButton registerButton;
    private  Label loginLabel;
    private  TextField usernameInputTextField;
    private  TextField passwordInputTextField;
    private  TextButton loginButton;
    private  TextButton forgetPasswordButton;

    public LoginAndRegisterMenu() {

    }



    public LoginAndRegisterMenu(Skin skin) {
        Gdx.input.setInputProcessor(stage);
        this.skin = GamePictureManager.skin;
        register = new Label("Register", skin);
        usernameTextField = new TextField("", skin);
        passwordTextField = new TextField("", skin);
        confirmPasswordTextField = new TextField("", skin);
        nicknameTextField = new TextField("", skin);
        emailTextField = new TextField("", skin);
        genderSelectBox = new SelectBox<>(skin);
        Array<String> genders = new Array<>();
        genders.add("Male");
        genders.add("Female");
        genderSelectBox.setItems(genders);
        randomPasswordLabel = new Label("random Password", skin);
        randomPasswordButton = new TextButton("Random Password", skin);
        randomPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleRandomPassword();
            }
        });

        registerButton = new TextButton("sign up", skin);
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleRegister();
            }
        });


        loginLabel = new Label("Login", skin);
        usernameInputTextField = new TextField("", skin);
        passwordInputTextField = new TextField("", skin);
        loginButton = new TextButton("Login", skin);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleLogin();
            }
        });


        forgetPasswordButton = new TextButton("Forget Password", skin);
        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleForgetPassword();
            }
        });

        controller.setView(this);
    }




    public void check(Scanner scanner) {

    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {

        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);


        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Table registerTable = new Table(skin);
        //registerTable.setBackground("default-round");
        registerTable.pad(20).defaults().pad(10);

        registerTable.add(new Label("Register", skin)).colspan(2).center().row();
        registerTable.add("Username:");
        registerTable.add(usernameTextField).row();
        registerTable.add("Password:");
        registerTable.add(passwordTextField).row();
        registerTable.add("Confirm Password:");
        registerTable.add(confirmPasswordTextField).row();
        registerTable.add("Nickname:");
        registerTable.add(nicknameTextField).row();
        registerTable.add("Email:");
        registerTable.add(emailTextField).row();
        registerTable.add("Gender:");
        registerTable.add(genderSelectBox).row();
        registerTable.add(randomPasswordButton).colspan(2).center().row();
        registerTable.add(registerButton).colspan(2).center().row();

        Table loginTable = new Table(skin);
        //loginTable.setBackground("default-round");
        loginTable.pad(20).defaults().pad(10);

        loginTable.add(new Label("Login", skin)).colspan(2).center().row();
        loginTable.add("Username:");
        loginTable.add(usernameInputTextField).row();
        loginTable.add("Password:");
        loginTable.add(passwordInputTextField).row();
        loginTable.add(loginButton).colspan(2).center().row();
        loginTable.add(forgetPasswordButton).colspan(2).center().row();


        root.add(registerTable).expand().fill();
        root.add(loginTable).expand().fill();

        MenuAnimationController.addHoverAnimation(registerButton);
        MenuAnimationController.addHoverAnimation(loginButton);
        MenuAnimationController.addHoverAnimation(forgetPasswordButton);
        MenuAnimationController.addHoverAnimation(randomPasswordButton);
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



    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public TextField getConfirmPasswordTextField() {
        return confirmPasswordTextField;
    }

    public TextField getNicknameTextField() {
        return nicknameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public SelectBox<String> getGenderSelectBox() {
        return genderSelectBox;
    }

    public TextButton getRandomPasswordButton() {
        return randomPasswordButton;
    }

    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextField getUsernameInputTextField() {
        return usernameInputTextField;
    }

    public TextField getPasswordInputTextField() {
        return passwordInputTextField;
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }
}
