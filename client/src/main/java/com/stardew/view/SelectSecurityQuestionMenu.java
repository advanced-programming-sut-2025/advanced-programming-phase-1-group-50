package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.model.Result;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;

import java.util.HashMap;

public class SelectSecurityQuestionMenu implements AppMenu, Screen {
    private Stage stage;
    private final SelectBox<String> securityQuestionSelectBox;
    private final Label inputYourAnswerHereLabel;
    private final TextField answerTextField;
    private final TextButton applyAnswer;
    private final String[] securityQuestions = new String[] {
        "what is your favorite color?",
        "what is your favorite animal?",
        "what is your favorite football club?",
        "what is your favorite food?"
    };


    public SelectSecurityQuestionMenu(String username, String password, String nickname,
                                      String email,    String gender) {

        securityQuestionSelectBox = new SelectBox<>(GamePictureManager.skin);
        securityQuestionSelectBox.setItems(securityQuestions);
        inputYourAnswerHereLabel = new Label("input your answer here", GamePictureManager.skin);
        answerTextField = new TextField("", GamePictureManager.skin);
        applyAnswer = new TextButton("apply", GamePictureManager.skin);
        applyAnswer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (answerTextField.getText().isEmpty()) {
                    showResult(new Result(false, "Please enter a valid answer"));
                    return;
                }

                Message message = prepareMessage(username, password, nickname, email, gender);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                if (response == null || response.getType() != MessageType.REGISTER_RESULT) {
                    showResult(new Result(false, "Connection failed or timed out!"));
                    return;
                }
                Result result = response.getFromBody("result", Result.class);
                showResult(result);
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
        });


    }


    private Message prepareMessage(String username, String password, String nickname, String email, String gender) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("question", securityQuestionSelectBox.getSelected());
        body.put("answer", answerTextField.getText());
        body.put("username", username);
        body.put("password", password);
        body.put("nickname", nickname);
        body.put("email", email);
        body.put("gender", gender);
        return new Message(body, MessageType.REGISTER_FINALLY);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);

        Table table = new Table(GamePictureManager.skin);
        table.setFillParent(true);
        table.pad(20);
        table.defaults().pad(15);

        Label selectLabel = new Label("Select your security question:", GamePictureManager.skin);

        table.add(selectLabel).center().colspan(2).row();
        table.add(securityQuestionSelectBox).width(400).row();
        table.add(inputYourAnswerHereLabel).colspan(2).row();
        table.add(answerTextField).width(400).row();
        table.add(applyAnswer).width(150).height(50).row();

        stage.addActor(table);

        MenuAnimationController.addHoverAnimation(applyAnswer);
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
}
