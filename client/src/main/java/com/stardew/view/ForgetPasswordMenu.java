package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.controller.ForgetPasswordController;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.HashMap;

public class ForgetPasswordMenu implements AppMenu, Screen {
    private Stage stage;
    private Skin skin;
    private final Label forgetPasswordLabel;
    private final TextField usernameTextField;
    private final TextButton findSecurityQuestionButton;
    private final Label showSecurityQuestionLabel;
    private final TextField answerTextField;
    private final TextField enterNewPasswordTextField;
    private final TextButton setNewPasswordButton;
    private final TextButton backButton;


    public ForgetPasswordMenu(ForgetPasswordController controller , String usernameForShowSecurityQuestion) {
        skin = GamePictureManager.skin;
        forgetPasswordLabel = new Label("Forget Password", skin);
        findSecurityQuestionButton = new TextButton("Find Security Question", skin);
        usernameTextField = new TextField("", skin);
        showSecurityQuestionLabel = new Label("No Security Question", skin);
        answerTextField = new TextField("", skin);
        enterNewPasswordTextField = new TextField("", skin);
        setNewPasswordButton = new TextButton("set New Password", skin);
        backButton = new TextButton("Back", skin);

        findSecurityQuestionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (usernameTextField.getText().isEmpty()) {
                    showResult(new Result(false, "Please enter your username"));
                    return;
                }

                usernameTextField.setDisabled(true);
                findSecurityQuestionButton.setDisabled(true);

                Message message = prepareGetSecurityQuestionMessage();
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                if (response == null || response.getType() != MessageType.GET_SECURITY_QUESTION_RESULT) {
                    showResult(new Result(false, "Connection failed or timed out!"));
                    usernameTextField.setDisabled(false);
                    findSecurityQuestionButton.setDisabled(false);
                    return;
                }
                Boolean success = response.getFromBody("success");
                if (!success) {
                    showResult(new Result(false, response.getFromBody("message")));
                    usernameTextField.setDisabled(false);
                    findSecurityQuestionButton.setDisabled(false);
                    return;
                }
                String securityQuestion = response.getFromBody("securityQuestion");
                showSecurityQuestionLabel.setText(securityQuestion);
            }
        });

        setNewPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (answerTextField.getText().isEmpty() || enterNewPasswordTextField.getText().isEmpty()) {
                    showResult(new Result(false, "Please fill all fields"));
                    return;
                }
                if (!usernameTextField.isDisabled() || !findSecurityQuestionButton.isDisabled()) {
                    showResult(new Result(false, "Please enter username and get security question"));
                    return;
                }

                setNewPasswordButton.setDisabled(true);
                Message message = prepareSetNewPasswordMessage();
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                if (response == null || response.getType() != MessageType.FORGET_PASSWORD_RESULT) {
                    showResult(new Result(false, "Connection failed or timed out!"));
                    setNewPasswordButton.setDisabled(false);
                    return;
                }

                Result result = response.getFromBody("result", Result.class);
                showResult(result);
                setNewPasswordButton.setDisabled(false);
                if (result.getSuccessful()) {
                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            Screen currentScreen = Main.getMain().getScreen();
                            LoginAndRegisterMenu loginRegister = new LoginAndRegisterMenu(GamePictureManager.skin);
                            Main.getMain().setScreen(loginRegister);
                            currentScreen.dispose();
                        }
                    }, 2f);
                }

            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen currentScreen = Main.getMain().getScreen();
                LoginAndRegisterMenu loginRegister = new LoginAndRegisterMenu(GamePictureManager.skin);
                Main.getMain().setScreen(loginRegister);
                currentScreen.dispose();
            }
        });

    }


    private Message prepareGetSecurityQuestionMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", usernameTextField.getText());
        return new Message(body, MessageType.GET_SECURITY_QUESTION);
    }

    private Message prepareSetNewPasswordMessage() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", usernameTextField.getText());
        body.put("answer", answerTextField.getText());
        body.put("newPassword", enterNewPasswordTextField.getText());
        return new Message(body, MessageType.FORGET_PASSWORD);
    }


    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);

        usernameTextField.setMessageText("Username");
        answerTextField.setMessageText("Answer");
        enterNewPasswordTextField.setMessageText("enter new password");

        forgetPasswordLabel.setAlignment(Align.center);
        forgetPasswordLabel.setColor(Color.BLACK);
        showSecurityQuestionLabel.setAlignment(Align.center);
        showSecurityQuestionLabel.setColor(Color.BLACK);

        Table table = new Table();
        table.setFillParent(true);
        table.defaults().pad(30);
        table.pad(400, 700, 400, 700);

        table.add(forgetPasswordLabel).expandX().fillX().colspan(2).row();
        table.add(usernameTextField).left().expandX().fillX().padRight(10);
        table.add(findSecurityQuestionButton).right().row();
        table.add(showSecurityQuestionLabel).expandX().fillX().colspan(2).row();
        table.add(answerTextField).expandX().fillX().padRight(1).row();
        table.add(enterNewPasswordTextField).expandX().fillX().padRight(1).row();
        table.add(setNewPasswordButton).left();
        table.add(backButton).right();

        stage.addActor(table);
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

    public Stage getStage() {
        return stage;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public TextField getAnswerTextField() {
        return answerTextField;
    }

    public TextField getEnterNewPasswordTextField() {
        return enterNewPasswordTextField;
    }
}
