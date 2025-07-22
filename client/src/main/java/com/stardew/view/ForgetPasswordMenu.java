package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.controller.ForgetPasswordController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.userInfo.User;

public class ForgetPasswordMenu implements Screen {
    private Stage stage;
    private Skin skin;
    private final Label forgetPasswordLabel;
    private final TextField usernameTextField;
    private final Label showSecurityQuestionLabel;
    private final TextField answerTextField;
    private final TextButton forgetPasswordButton;
    private final TextField enterNewPasswordTextField;

    private User findUser(String username){
        for(User u : App.users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }


    public ForgetPasswordMenu(ForgetPasswordController controller , String usernameForShowSecurityQuestion) {
        skin = GamePictureManager.skin;
        forgetPasswordLabel = new Label("Forget Password", skin);
        usernameTextField = new TextField("", skin);
        String show = null;
        if(findUser(usernameForShowSecurityQuestion) != null){
            try {
                show = findUser(usernameForShowSecurityQuestion).getSecurityQuestion().getQuestion();
            }catch(NullPointerException e){
                e.printStackTrace();
            }
        }
        showSecurityQuestionLabel = new Label(show, skin);
        answerTextField = new TextField("", skin);
        enterNewPasswordTextField = new TextField("", skin);
        forgetPasswordButton = new TextButton("Forget Password", skin);

        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleForgetPassword();
            }
        });

        controller.setView(this);

    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);


        forgetPasswordLabel.setPosition(300, 400);
        usernameTextField.setPosition(300, 350);
        usernameTextField.setMessageText("Username");

        showSecurityQuestionLabel.setPosition(300, 300);
        answerTextField.setPosition(300, 250);
        answerTextField.setMessageText("Answer");

        forgetPasswordButton.setPosition(300, 200);
        forgetPasswordButton.setSize(200, 40);

        enterNewPasswordTextField.setPosition(300, 250);
        enterNewPasswordTextField.setSize(200, 40);
        enterNewPasswordTextField.setMessageText("enter new password");


        stage.addActor(forgetPasswordLabel);
        stage.addActor(enterNewPasswordTextField);
        stage.addActor(usernameTextField);
        stage.addActor(showSecurityQuestionLabel);
        stage.addActor(answerTextField);
        stage.addActor(forgetPasswordButton);
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
