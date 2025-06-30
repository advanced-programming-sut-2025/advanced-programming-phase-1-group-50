package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.controller.SelectSecurityQuestionController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.app.SecurityQuestion;
import com.stardew.models.userInfo.Gender;

public class SelectSecurityQuestionMenu implements Screen {
    private Stage stage;
    private final SelectSecurityQuestionController controller;
    private final SelectBox<String> securityQuestionSelectBox;
    private final Label inputYourAnswerHereLabel;
    private final TextField answerTextField;
    private final TextButton applyAnswer;

    private String username;
    private String password;
    private String nickname;
    private String email;
    private Gender gender;

    public SelectSecurityQuestionMenu(SelectSecurityQuestionController controller , String username, String password,
                                      String nickname, String email, Gender gender) {
        this.controller = controller;
        stage = new Stage();
        securityQuestionSelectBox = new SelectBox<>(GamePictureManager.skin);
        Array<String> securityQuestions = new Array<String>();
        for(SecurityQuestion sq : App.securityQuestions){
            securityQuestions.add(sq.getQuestion());
        }
        securityQuestionSelectBox.setItems(securityQuestions);
        inputYourAnswerHereLabel = new Label("input your answer here", GamePictureManager.skin);
        answerTextField = new TextField("", GamePictureManager.skin);
        applyAnswer = new TextButton("apply", GamePictureManager.skin);
        applyAnswer.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleRegister(username, password, nickname, email, gender);
            }
        });

        controller.setView(this);





    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

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


    public Stage getStage() {
        return stage;
    }

    public SelectSecurityQuestionController getController() {
        return controller;
    }

    public SelectBox<String> getSecurityQuestionSelectBox() {
        return securityQuestionSelectBox;
    }

    public TextField getAnswerTextField() {
        return answerTextField;
    }

    public Label getInputYourAnswerHereLabel() {
        return inputYourAnswerHereLabel;
    }

    public TextButton getApplyAnswer() {
        return applyAnswer;
    }
}
