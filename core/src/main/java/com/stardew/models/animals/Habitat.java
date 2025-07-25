package com.stardew.models.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.stardew.models.BackgroundColors;
import com.stardew.models.ColorPrinter;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.Placeable;
import com.stardew.view.windows.HabitatWindow;

import java.awt.*;
import java.util.ArrayList;

public class Habitat implements Placeable {
    private final String backgroundCode = BackgroundColors.YELLOW;
    private final  String colorCode = ColorPrinter.BRIGHT_RED;
    private final HabitatType type;
    private final HabitatSize size;
    private final ArrayList<Animal> animals;
    private final Rectangle bounds;
    private final Vector2 position;
    private final TextureRegion textureRegion;
    private final Image image;


    public Habitat(HabitatType type, HabitatSize size, int x, int y) {
        this.type = type;
        this.size = size;
        animals = new ArrayList<>();
        this.bounds = new Rectangle(x, y, type.getLengthX(), type.getLengthY());
        this.position = new Vector2(x, y);

        if (type == HabitatType.Barn) {
            switch (size) {
                case Regular -> textureRegion = GamePictureManager.barnTexture;
                case Big -> textureRegion = GamePictureManager.bigBarnTexture;
                case Deluxe -> textureRegion = GamePictureManager.deluxeBarnTexture;
                default -> textureRegion = null;
            }
        } else if (type == HabitatType.Coop) {
            switch (size) {
                case Regular -> textureRegion = GamePictureManager.coopTexture;
                case Big -> textureRegion = GamePictureManager.bigCoopTexture;
                case Deluxe -> textureRegion = GamePictureManager.deluxeCoopTexture;
                default -> textureRegion = null;
            }
        } else textureRegion = null;

        image = new Image(textureRegion);
        image.setPosition(position.x * GamePictureManager.TILE_SIZE, position.y * GamePictureManager.TILE_SIZE);
    }

    public void prepareWindow(Stage stage) {
        stage.addActor(image);
        Habitat thisHabitat = this;
        image.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(new HabitatWindow(stage, thisHabitat, (x + image.getX()), y + image.getY() - 50));
                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });
    }

    public HabitatType getType() {
        return type;
    }

    public HabitatSize getSize() {
        return size;
    }

    public int getTotalCapacity() {
        return size.getCapacity();
    }

    public int getEmptyCapacity() {
        return size.getCapacity() - animals.size();
    }

    public boolean hasEmptyCapacity() {
        return getEmptyCapacity() > 0;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
            animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public Vector2 getPosition() {
        return position;
    }

    public static HabitatType getHabitatTypeByInput(String input) {
        if (input.equalsIgnoreCase("barn") ||
                input.equalsIgnoreCase("big_barn") ||
                input.equalsIgnoreCase("deluxe_barn"))
            return HabitatType.Barn;
        if (input.equalsIgnoreCase("coop") ||
                input.equalsIgnoreCase("big_coop") ||
                input.equalsIgnoreCase("deluxe_coop"))
            return HabitatType.Coop;

        return null;
    }

    public static HabitatSize getHabitatSizeByInput(String input) {
        if (input.equalsIgnoreCase("barn") || input.equalsIgnoreCase("coop"))
            return HabitatSize.Regular;
        if (input.equalsIgnoreCase("big_barn") || input.equalsIgnoreCase("big_coop"))
            return HabitatSize.Big;
        if (input.equalsIgnoreCase("deluxe_barn") || input.equalsIgnoreCase("deluxe_coop"))
            return HabitatSize.Deluxe;

        return null;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    public char getSymbol() {
        if (type == HabitatType.Barn) {
            return 'b';
        }
        return 'c';
    }

    @Override
    public String getColor(){
        return colorCode;
    }

    @Override
    public String getBackground(){
        return backgroundCode;
    }

    @Override
    public TextureRegion getTexture() {
        return textureRegion;
    }

    @Override
    public Color getMiniMapColor() {
        return Color.ORANGE;
    }

    public void render(Batch batch) {
        batch.draw(textureRegion, position.x * GamePictureManager.TILE_SIZE, position.y * GamePictureManager.TILE_SIZE);
    }

}
