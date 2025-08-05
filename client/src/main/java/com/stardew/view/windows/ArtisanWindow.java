package com.stardew.view.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.stardew.models.GameAssetManagers.ArtisanAsset;
import com.stardew.models.GameAssetManagers.ArtisanGoodAsset;
import com.stardew.model.Result;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.ArtisanMachine.ArtisanMachinesManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ArtisanWindow extends CloseableWindow {


    public ArtisanWindow(Stage stage, ArtisanAsset artisanAsset, String machineID, int gameID, float x, float y) {
        super("Artisan Menu", stage);

        pad(25, 5, 20, 0);
        defaults().space(10);

        ArrayList<ArtisanGoodAsset> products = artisanAsset.getProducts();
        for (ArtisanGoodAsset asset : products) {
            Image product = asset.getImage();
            add(product).row();

            SmartTooltip tooltip = SmartTooltip.getInstance();
            product.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    new Thread(() -> {
                        HashMap<String, Object> body = new HashMap<>();
                        body.put("id", gameID);
                        body.put("event", Event.UseArtisanMachine);
                        body.put("machineID", machineID);
                        body.put("itemName", asset.name());
                        Message message = new Message(body, MessageType.EVENT_IN_GAME);
                        Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
                        if (response != null) {
                            Result result = response.getFromBody("result", Result.class);
                            Gdx.app.postRunnable(() -> showResult(result));
                            if (result.getSuccessful()) {
                                ArtisanMachinesManager.getInstance().updateMachine(machineID);
                            }
                        }
                    }).start();
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltip.show(asset.getDescription());
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltip.hide();
                }
            });
        }

        pack();
        setSize(150, 45 + products.size() * 65);
        setPosition(x, y);

    }
}
