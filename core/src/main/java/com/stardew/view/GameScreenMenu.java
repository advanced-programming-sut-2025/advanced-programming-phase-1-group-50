package com.stardew.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.stardew.Main;
import com.stardew.controller.PlayerController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.animals.GameModel;
import com.stardew.models.app.App;
import com.stardew.models.date.Time;
import com.stardew.models.userInfo.Coin;
import com.stardew.view.windows.SmartTooltip;

public class GameScreenMenu implements Screen {

    private GameModel gameModel;
    private GameRenderer gameRenderer;
    private GameMenuInputAdapter gameMenuInputAdapter;
    private Stage stage;
    private Stage uiStage;
    private SpriteBatch batch;
    private Label timeLabel ;
    private Label seasonAndDayLabel;
    private Label playerGoldLabel;
    private Image blackFadeImage;
    private boolean isFaded = false;
    private Image nightOverlay;

    private TextureRegion clockTexture = GamePictureManager.clockTexture;
    private Image clockImage = new Image(clockTexture);



    public GameScreenMenu(){
        initializeGame();
        initializeTime();
        initializeFadeImage();
        initializeOverlayImage();
    }

    public void initializeGame(){

        gameModel = new GameModel(App.getGame().getMap() , 250 , 200);
        gameModel.setPlayerController(new PlayerController(App.getGame().getCurrentPlayingPlayer(), gameModel));
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel);
        batch = Main.getBatch();
        gameRenderer = new GameRenderer(gameModel, gameMenuInputAdapter, batch);

        stage = new Stage(new ScreenViewport(gameModel.getCamera()));
        uiStage = new Stage(new ScreenViewport());
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(gameMenuInputAdapter);
        Gdx.input.setInputProcessor(inputMultiplexer);

        SmartTooltip.initialize(stage, GamePictureManager.skin);
        startTimer();
        gameMenuInputAdapter.setStage(stage);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateTimeUi();




        batch.setProjectionMatrix(gameModel.getCamera().combined);
        batch.begin();

        gameModel.update(v);
        gameRenderer.render();
        gameMenuInputAdapter.update(v);

        batch.end();

        stage.act(v);
        stage.draw();

        uiStage.act(v);
        uiStage.draw();

        checkForDayTransition();
        updateNightOverlay();
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

    public void startTimer(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                App.getGame().getTime().advancedHour(1);
            }
        }, 60, 60);
    }

    public void updateTimeUi(){
        Time time = App.getGame().getTime();
        int hour = time.getHour();
        int day = time.getDate();
        String dayOfTheWeek = time.getDayOfWeek().getDayOfWeek();
        String season = time.getSeason().name();
        String timeText = hour + ":00" + " " + day;
        String seasonAndDayOfTheWeek = season + " " + dayOfTheWeek;
        int playerGold = App.getGame().getCurrentPlayingPlayer().getBackpack().getIngredientQuantity()
            .getOrDefault(new Coin() , 0);
        timeLabel.setText(timeText);
        seasonAndDayLabel.setText(seasonAndDayOfTheWeek);
        playerGoldLabel.setText(playerGold + "");

    }

    public void initializeTime(){


        clockImage.setSize(300, 200);
        clockImage.setPosition(Gdx.graphics.getWidth() - 330, Gdx.graphics.getHeight() - 220);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLACK;


        timeLabel = new Label("", style);
        timeLabel.setPosition(clockImage.getX() + 163, clockImage.getY() + 180);

        timeLabel.setFontScale(1.4f);

        seasonAndDayLabel = new Label("", style);
        seasonAndDayLabel.setPosition(clockImage.getX() + 135, clockImage.getY() + 110);

        seasonAndDayLabel.setFontScale(1.2f);

        playerGoldLabel = new Label("", style);
        playerGoldLabel.setPosition(clockImage.getX() + 135, clockImage.getY() + 50);

        playerGoldLabel.setFontScale(1.2f);

        uiStage.addActor(clockImage);
        uiStage.addActor(timeLabel);
        uiStage.addActor(seasonAndDayLabel);
        uiStage.addActor(playerGoldLabel);
    }

    public void initializeFadeImage(){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        Texture blackTexture = new Texture(pixmap);
        pixmap.dispose();

        blackFadeImage = new Image(new TextureRegion(blackTexture));
        blackFadeImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        blackFadeImage.getColor().a = 0f;

        uiStage.addActor(blackFadeImage);
    }

    public void checkForDayTransition(){
        Time time = App.getGame().getTime();
        int hour = time.getHour();
        if(hour == 21 && !isFaded){
            isFaded = true;
            blackFadeImage.getColor().a = 0f;
            blackFadeImage.addAction(Actions.sequence(
                Actions.fadeIn(1f),
                Actions.delay(3f),
                Actions.fadeOut(1f)
            ));

        }

        isFaded = false;
    }


    public void loadBlackTexture(){

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        Texture blackTexture = new Texture(pixmap);
        nightOverlay = new Image(blackTexture);

    }

    public void initializeOverlayImage(){
        loadBlackTexture();
        nightOverlay.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        nightOverlay.setColor(0, 0, 0, 0f);
        stage.addActor(nightOverlay);
    }


    public void updateNightOverlay(){
        int hour = App.getGame().getTime().getHour();
        float targetAlpha;

        if (hour >= 18 && hour < 22) {

            targetAlpha = (hour - 18) / 4f * 0.5f;
        } else if (hour >= 22 || hour < 6) {

            targetAlpha = 0.5f;
        } else {

            targetAlpha = 0f;
        }


        float currentAlpha = nightOverlay.getColor().a;

        nightOverlay.getColor().a = currentAlpha + (targetAlpha - currentAlpha) * 0.05f;

    }
}
