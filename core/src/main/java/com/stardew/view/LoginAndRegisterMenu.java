package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.controller.LoginAndRegisterController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.app.SecurityQuestion;
import com.stardew.models.enums.LoginMenuCommands;
import com.stardew.Main;

import java.util.Scanner;
import java.util.regex.Matcher;

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
        usernameTextField = new TextField("Enter username here", skin);
        passwordTextField = new TextField("Enter password here", skin);
        confirmPasswordTextField = new TextField("Enter confirm password here", skin);
        nicknameTextField = new TextField("Enter nickname here", skin);
        emailTextField = new TextField("Enter email here", skin);
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
        usernameInputTextField = new TextField("Enter username here", skin);
        passwordInputTextField = new TextField("Enter password here", skin);
        loginButton = new TextButton("Login", skin);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });


        forgetPasswordButton = new TextButton("Forget Password", skin);
        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        controller.setView(this);
    }




    public void check(Scanner scanner) {
//        String input = scanner.nextLine();
//        input = input.trim();
//        Matcher matcher;
//        if(LoginMenuCommands.Register.getMatcher(input) != null) {
//            matcher = LoginMenuCommands.Register.getMatcher(input);
//            String username = matcher.group(1).trim();
//            String password = matcher.group(2).trim();
//            String passwordConfirm = matcher.group(3).trim();
//            String nickname = matcher.group(4).trim();
//            String email = matcher.group(5).trim();
//            String gender = matcher.group(6).trim();
//            System.out.println(controller.register(username , password , passwordConfirm , nickname , email , gender ));
//            if(controller.register(username , password , passwordConfirm , nickname , email , gender ).getSuccessful()){
//                int counter =0 ;
//                for(SecurityQuestion sq : App.securityQuestions){
//                    System.out.println(counter + ". " + sq.getQuestion());
//                    counter++;
//                }
//                String numberOfSecurityQuestions = scanner.nextLine();
//
//                String[] parts = numberOfSecurityQuestions.split("\\s+");
//                parts[0] = parts[0].trim();
//                parts[1] = parts[1].trim();
//
//                System.out.println(controller.selectSecurityQuestion(username , password , passwordConfirm , nickname , email , gender , parts[0] , parts[1]));
//
//            }
//
//
//
//
//        }
//        else if(LoginMenuCommands.Login.getMatcher(input) != null) {
//            matcher = LoginMenuCommands.Login.getMatcher(input);
//            String username = matcher.group(1).trim();
//            String password = matcher.group(2).trim();
//            System.out.println(controller.login(username , password));
//
//        }
//        else if(LoginMenuCommands.ForgetPassword.getMatcher(input) != null) {
//            matcher = LoginMenuCommands.ForgetPassword.getMatcher(input);
//            String username = matcher.group(1).trim();
//
//            if(controller.askSecurityQuestion(username).getSuccessful()) {
//                System.out.println("answer the question");
//                System.out.println(controller.findUser(username).getSecurityQuestion().getQuestion());
//                String answer = scanner.nextLine();
//                answer = answer.trim();
//                System.out.println(controller.checkAnswerQuestion(username , answer));
//                if(controller.checkAnswerQuestion(username , answer).getSuccessful()){
//                    System.out.println("now enter a new password");
//                    String newPassword = scanner.nextLine();
//                    newPassword = newPassword.trim();
//                    System.out.println(controller.setNewPasswordAfterForgetPassword(username, newPassword));
//                }
//            }
//
//
//
//        }
//        else if(LoginMenuCommands.ShowMenu.getMatcher(input) != null) {
//            System.out.println(controller.showMenu());
//        }
//        else {
//            System.out.println("invalid command");
//        }





    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

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
