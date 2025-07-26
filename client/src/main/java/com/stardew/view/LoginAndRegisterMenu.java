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
import com.stardew.model.UserDTO;
import com.stardew.models.ClientInfo.LoggedInUser;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.Main;
import com.stardew.model.Result;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.security.SecureRandom;
import java.util.HashMap;

public class LoginAndRegisterMenu implements AppMenu , Screen {
    private final LoginAndRegisterController controller = new LoginAndRegisterController();
    private final Stage stage = new Stage(new ScreenViewport());
    private  Skin skin ;
    private  TextField usernameTextField ;
    private  TextField passwordTextField ;
    private  TextField confirmPasswordTextField;
    private  TextField nicknameTextField;
    private  TextField emailTextField ;
    private  SelectBox<String> genderSelectBox ;
    private  TextButton randomPasswordButton;
    private  TextButton registerButton;

    private  TextField usernameInputTextField;
    private  TextField passwordInputTextField;
    private  TextButton loginButton;
    private  TextButton forgetPasswordButton;
    private  CheckBox stayLoggedIn;


    public LoginAndRegisterMenu() {

    }



    public LoginAndRegisterMenu(Skin skin) {
        Gdx.input.setInputProcessor(stage);
        this.skin = GamePictureManager.skin;
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
        randomPasswordButton = new TextButton("Random Password", skin);
        randomPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String password = generateRandomPassword();
                passwordTextField.setText(password);
            }
        });

        registerButton = new TextButton("sign up", skin);
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() ||
                    confirmPasswordTextField.getText().isEmpty() || nicknameTextField.getText().isEmpty() ||
                    genderSelectBox.getSelected().isEmpty() || emailTextField.getText().isEmpty()) {
                    showResult(new Result(false, "Please fill all fields!"));
                    return;
                }
                if (!passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
                    showResult(new Result(false, "Password and Confirm does not match!"));
                    return;
                }

                registerButton.setDisabled(true);

                Message message = prepareRegisterMessage();
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);

                if (response == null || response.getType() != MessageType.REGISTER_RESULT) {
                    showResult(new Result(false, "Connection failed or timed out!"));
                    registerButton.setDisabled(false);
                    return;
                }
                Result result = response.getFromBody("result", Result.class);
                if (result.getSuccessful()) {
                    Screen currentScreen = Main.getMain().getScreen();
                    SelectSecurityQuestionMenu sqMenu = new SelectSecurityQuestionMenu(
                            usernameTextField.getText(), passwordTextField.getText(), nicknameTextField.getText(),
                            emailTextField.getText(),    genderSelectBox.getSelected());
                    Main.getMain().setScreen(sqMenu);
                    currentScreen.dispose();
                    return;
                }
                showResult(result);

                registerButton.setDisabled(false);
            }
        });


        usernameInputTextField = new TextField("", skin);
        passwordInputTextField = new TextField("", skin);
        loginButton = new TextButton("Login", skin);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (usernameInputTextField.getText().isEmpty() || passwordInputTextField.getText().isEmpty()) {
                    showResult(new Result(false, "Please fill all fields!"));
                    return;
                }

                loginButton.setDisabled(true);
                Message message = prepareLoginMessage();
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                if (response == null || response.getType() != MessageType.LOGIN_RESULT) {
                    showResult(new Result(false, "Connection failed or timed out!"));
                    loginButton.setDisabled(false);
                    return;
                }
                Result result = response.getFromBody("result", Result.class);
                if (result.getSuccessful()) {
                    Screen currentScreen = Main.getMain().getScreen();
                    MainMenu mainMenu = new MainMenu();
                    Main.getMain().setScreen(mainMenu);
                    currentScreen.dispose();
                    String username = response.getFromBody("username");
                    String nickname = response.getFromBody("nickname");
                    LoggedInUser.setUser(new UserDTO(username, nickname));
                    return;
                }
                showResult(result);
                loginButton.setDisabled(false);
            }
        });


        forgetPasswordButton = new TextButton("Forget Password", skin);
        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleForgetPassword();
            }
        });

        stayLoggedIn = new CheckBox("", skin);

        controller.setView(this);
    }


    private Message prepareRegisterMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", usernameTextField.getText());
        body.put("password", passwordTextField.getText());
        body.put("confirmPassword", confirmPasswordTextField.getText());
        body.put("nickname", nicknameTextField.getText());
        body.put("gender", genderSelectBox.getSelected());
        body.put("email", emailTextField.getText());
        return new Message(body, MessageType.REGISTER_INITIAL);
    }

    private Message prepareLoginMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", usernameInputTextField.getText());
        body.put("password", passwordInputTextField.getText());
        return new Message(body, MessageType.LOGIN);
    }


    private String generateRandomPassword() {
        String Characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789?><,\"';:\\/|][}{+=)(*&^%$#!";
        SecureRandom rand = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(Characters.charAt(rand.nextInt(Characters.length())));
        }
        return sb.toString();
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
        loginTable.add("stayLoggedIn:");
        loginTable.add(stayLoggedIn).row();


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



    public TextField getUsernameInputTextField() {
        return usernameInputTextField;
    }

    public TextField getPasswordInputTextField() {
        return passwordInputTextField;
    }
}
