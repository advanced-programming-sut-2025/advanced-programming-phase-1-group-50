package com.stardew.view.Stores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.reflect.TypeToken;
import com.stardew.controller.StoreController;
import com.stardew.model.PlaceableDTO;
import com.stardew.model.StoreGoodDTO;
import com.stardew.model.TileDTO;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.network.Event;
import com.stardew.network.Message;
import com.stardew.network.MessageType;
import com.stardew.network.NetworkManager;
import com.stardew.view.windows.CloseableWindow;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class StoreWindow extends CloseableWindow {
    private final String assistantName;
    private final Table productTable;
    private final SelectBox<String> filterBox;
    private final ArrayList<StoreGoodDTO> goods = new ArrayList<>();
    private final int gameId;

    public StoreWindow(int gameId, Stage stage, String assistantName) {
        super(assistantName + "'s store", stage);
        this.assistantName = assistantName;
        this.gameId = gameId;

        pad(40);
        defaults().space(15);

        filterBox = new SelectBox<>(GamePictureManager.skin);
        filterBox.setItems("All products", "Only available products");
        filterBox.setSelected("All products");
        filterBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                refreshProducts();
            }
        });

        productTable = new Table();
        productTable.defaults().space(10);

        ScrollPane scrollPane = new ScrollPane(productTable, GamePictureManager.skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);

        add(filterBox).left().row();

        Table headerTable = new Table();
        headerTable.add(new Label("Name", GamePictureManager.skin)).width(200);
        headerTable.add(new Label("Price", GamePictureManager.skin)).width(100);
        headerTable.add(new Label("Qty", GamePictureManager.skin)).width(80);
        add(headerTable).row();

        add(scrollPane).width(400).height(400).row();

        refreshProducts();

        pack();
        setPosition(
            stage.getCamera().position.x - getWidth() / 2,
            stage.getCamera().position.y - getHeight() / 2
        );
    }

    protected void refreshProducts() {
        goods.clear();
        new Thread(() -> {
            HashMap<String, Object> body = new HashMap<>();
            body.put("id", gameId);
            body.put("assistant", assistantName);
            body.put("onlyAvailable", filterBox.getSelected().equals("Only available products"));
            body.put("event", Event.GetStoreGoods);
            Message message = new Message(body, MessageType.EVENT_IN_GAME);
            Message response = NetworkManager.getConnection().sendAndWaitForResponse(message, 500);
            if (response != null && response.getType().equals(MessageType.GET_STORES_GOODS_INFO)) {
                Type type = new TypeToken<ArrayList<StoreGoodDTO>>() {
                }.getType();
                ArrayList<StoreGoodDTO> newGoods = response.getFromBody("goods", type);
                Gdx.app.postRunnable(() -> {
                    goods.addAll(newGoods);
                    createUI();
                });
            }
        }).start();
    }

    private void createUI() {
        productTable.clear();
        for (StoreGoodDTO item : goods) {
            final String productName = item.getProductName();
            final int price = item.getPrice();
            final int quantity = item.getQuantity();

            TextButton nameButton = new TextButton(productName, GamePictureManager.skin);
            nameButton.pad(5);
            nameButton.getLabel().setFontScale(0.9f);
            nameButton.getLabelCell().padLeft(10);

            if (quantity == 0) {
                nameButton.setColor(Color.DARK_GRAY);
                nameButton.setDisabled(true);
            } else {
                nameButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (item.isInstanceOfMarnieRanchLiveStockItem()) {
                            openAnimalPurchaseWindow(productName, price);
                        } else if (item.isInstanceOfCarpenterShopFarmBuildingsItem() && item.getProductName().equalsIgnoreCase("Shipping Bin")) {
                            openPurchaseShippingBinWindow();
                        } else if (item.isInstanceOfCarpenterShopFarmBuildingsItem()) {
                            openPurchaseBuildingWindow(productName);
                        } else {
                            openPurchaseWindow(productName, quantity, price);
                        }
                    }
                });
            }

            Label priceLabel = new Label("$" + price, GamePictureManager.skin);
            Label qtyLabel = new Label("x" + (quantity > 10000 ? "infinity" : quantity), GamePictureManager.skin);

            if (quantity == 0) {
                priceLabel.setColor(Color.DARK_GRAY);
                qtyLabel.setColor(Color.DARK_GRAY);
            }

            productTable.add(nameButton).width(200).height(50);
            productTable.add(priceLabel).width(100);
            productTable.add(qtyLabel).width(80);
            productTable.row();
        }

        if (assistantName.equalsIgnoreCase("Clint")) {
            TextButton upgradeToolButton = new TextButton("Upgrade Tool", GamePictureManager.skin);
            upgradeToolButton.pad(5);
            upgradeToolButton.getLabel().setFontScale(0.9f);
            upgradeToolButton.getLabelCell().padLeft(10);
            upgradeToolButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ToolUpgradeWindow toolWindow = new ToolUpgradeWindow(gameId,stage);
                    stage.addActor(toolWindow);
                    closeWindow();
                }
            });

            TextButton upgradeTrashButton = new TextButton("Upgrade Trash Can", GamePictureManager.skin);
            upgradeTrashButton.pad(5);
            upgradeTrashButton.getLabel().setFontScale(0.9f);
            upgradeTrashButton.getLabelCell().padLeft(10);
            upgradeTrashButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    StoreController.upgradeTrashCan(gameId,result -> {
                        TrashCanUpgradeWindow trashWindow = new TrashCanUpgradeWindow(result,stage);
                        stage.addActor(trashWindow);
                        closeWindow();
                    });
                }
            });

            productTable.add(upgradeToolButton).colspan(3).width(380).height(50).row();
            productTable.add(upgradeTrashButton).colspan(3).width(380).height(50).row();
        }
    }


    private void openPurchaseWindow(String productName, int quantity, int price) {
        PurchaseWindow purchaseWindow = new PurchaseWindow(stage, this, gameId, assistantName, productName, quantity,
            price);
        stage.addActor(purchaseWindow);
    }

    private void openAnimalPurchaseWindow(String productName, int price) {
        PurchaseAnimalWindow purchaseAnimalWindowWindow = new PurchaseAnimalWindow(gameId, stage, this, productName,
            price);
        stage.addActor(purchaseAnimalWindowWindow);
    }

    private void openPurchaseBuildingWindow(String productName) {
        int width = getHabitatWidth(productName);
        int height = getHabitatHeight(productName);

        StoreController.canPurchaseBuilding(gameId, productName, result -> {
            if (!result.getSuccessful()) {
                showResult(result);
                return;
            }

            new Thread(() -> {
                HashMap<String, Object> body = new HashMap<>();
                body.put("id", gameId);
                body.put("event", Event.GetMyFarmInfo);
                Message infoMessage = new Message(body, MessageType.EVENT_IN_GAME);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(infoMessage, 500);
                if (response != null && response.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                    ArrayList<TileDTO> tileDTOS = response.getFromBody(
                        "tiles", new TypeToken<ArrayList<TileDTO>>() {
                        }.getType());
                    ArrayList<PlaceableDTO> placeableDTOS = response.getFromBody(
                        "placeables", new TypeToken<ArrayList<PlaceableDTO>>() {
                        }.getType());
                    SelectTileForHabitatWindow tileSelectionWindow =
                        new SelectTileForHabitatWindow(stage, width, height, tileDTOS, placeableDTOS);
                    Gdx.app.postRunnable(() -> stage.addActor(tileSelectionWindow));

                    tileSelectionWindow.setOnOKCallback((selectedX, selectedY) -> {
                        StoreController.purchaseBuilding(gameId, productName, selectedX, selectedY, result1 -> {
                            Gdx.app.postRunnable(() -> {
                                refreshProducts();
                                showResult(result1);
                            });
                        });
                    });

                }
            }).start();
        });
    }

    private void openPurchaseShippingBinWindow() {
        StoreController.canPurchaseBuilding(gameId, "Shipping Bin", result -> {
            if (!result.getSuccessful()) {
                showResult(result);
                return;
            }

            new Thread(() -> {
                HashMap<String, Object> body = new HashMap<>();
                body.put("id", gameId);
                body.put("event", Event.GetMyFarmInfo);
                Message infoMessage = new Message(body, MessageType.EVENT_IN_GAME);
                Message response = NetworkManager.getConnection().sendAndWaitForResponse(infoMessage, 500);
                if (response != null && response.getType() == MessageType.EVENT_IN_GAME_RESULT) {
                    ArrayList<TileDTO> tileDTOS = response.getFromBody(
                        "tiles", new TypeToken<ArrayList<TileDTO>>() {
                        }.getType());
                    ArrayList<PlaceableDTO> placeableDTOS = response.getFromBody(
                        "placeables", new TypeToken<ArrayList<PlaceableDTO>>() {
                        }.getType());
                    SelectTileForShippingBinWindow tileSelectionWindow =
                        new SelectTileForShippingBinWindow(stage, 1, 1, tileDTOS, placeableDTOS);
                    Gdx.app.postRunnable(() -> stage.addActor(tileSelectionWindow));

                    tileSelectionWindow.setOnOKCallback((selectedX, selectedY) -> {
                        StoreController.purchaseShippingBin(gameId, selectedX, selectedY, result1 -> {
                            Gdx.app.postRunnable(() -> showResult(result1));
                        });
                    });

                }
            }).start();
        });
    }

    private int getHabitatWidth(String habitat) {
        return switch (habitat) {
            case "Barn", "Big Barn", "Deluxe Barn" -> 7;
            case "Coop", "Big Coop", "Deluxe Coop" -> 6;
            default -> 0;
        };
    }

    private int getHabitatHeight(String habitat) {
        return switch (habitat) {
            case "Barn", "Big Barn", "Deluxe Barn" -> 4;
            case "Coop", "Big Coop", "Deluxe Coop" -> 3;
            default -> 0;
        };
    }
}
