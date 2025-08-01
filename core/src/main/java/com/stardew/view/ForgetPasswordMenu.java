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
    private final ForgetPasswordController controller;
    private String usernameForShowSecurityQuestion;

    private User findUser(String username){
        for(User u : App.users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }


    public ForgetPasswordMenu(ForgetPasswordController controller , String usernameForShowSecurityQuestion) {
        this.controller = controller;
        this.usernameForShowSecurityQuestion = usernameForShowSecurityQuestion;
        stage = new Stage();
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

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        usernameTextField.setMessageText("Username");
        answerTextField.setMessageText("Answer");
        enterNewPasswordTextField.setMessageText("Enter New Password");

        forgetPasswordLabel.setFontScale(2);


        table.add(forgetPasswordLabel).padBottom(40).colspan(2).row();

        table.add(new Label("Username:", skin)).left().pad(10);
        table.add(usernameTextField).width(250).height(40).pad(10).row();

        table.add(new Label("Security Question:", skin)).left().pad(10);
        table.add(showSecurityQuestionLabel).left().pad(10).row();

        table.add(new Label("Answer:", skin)).left().pad(10);
        table.add(answerTextField).width(250).height(40).pad(10).row();

        table.add(new Label("New Password:", skin)).left().pad(10);
        table.add(enterNewPasswordTextField).width(250).height(40).pad(10).row();

        table.add().colspan(2).height(20).row(); // فاصلهٔ عمودی

        table.add(forgetPasswordButton).width(200).height(50).colspan(2).padTop(20).center();
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

    public Label getForgetPasswordLabel() {
        return forgetPasswordLabel;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public Label getShowSecurityQuestionLabel() {
        return showSecurityQuestionLabel;
    }

    public TextField getAnswerTextField() {
        return answerTextField;
    }

    public TextButton getForgetPasswordButton() {
        return forgetPasswordButton;
    }

    public TextField getEnterNewPasswordTextField() {
        return enterNewPasswordTextField;
    }


}
