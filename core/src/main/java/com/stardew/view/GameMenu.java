package com.stardew.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stardew.Main;
import com.stardew.controller.AbilityAndEnergyController.AbilityController;
import com.stardew.controller.AbilityAndEnergyController.EnergyController;
import com.stardew.controller.AnimalsControllers.AnimalsController;
import com.stardew.controller.CookingAndCraftingControllers.ArtisanController;
import com.stardew.controller.CookingAndCraftingControllers.CookingController;
import com.stardew.controller.CookingAndCraftingControllers.CraftingController;
import com.stardew.controller.ForagingControllers.ForagingController;
import com.stardew.controller.GameDateAndWeatherController.DateController;
import com.stardew.controller.GameDateAndWeatherController.WeatherController;
import com.stardew.controller.NPCController.NPCController;
import com.stardew.controller.PlayersRealtionController.PlayersRelationController;
import com.stardew.controller.ToolsControllers.ToolController;
import com.stardew.models.GameAssetManagers.GamePictureManager;
import com.stardew.models.app.App;
import com.stardew.models.app.Menus;
import com.stardew.models.enums.GameMenuCommands;
import com.stardew.models.enums.NPCsCommands;
import com.stardew.models.mapInfo.Position;
import com.stardew.models.stores.Blacksmith;
import com.stardew.models.userInfo.*;
import com.stardew.models.Result;
import com.stardew.controller.GameMenuController;

public class GameMenu implements AppMenu , Screen {
    private final GameMenuController controller = new GameMenuController();
    private final DateController dateController = new DateController();
    private final WeatherController weatherController = new WeatherController();
    private final EnergyController energyController = new EnergyController();
    private final ToolController toolController = new ToolController();
    private final ForagingController foragingController = new ForagingController();
    private final AnimalsController animalsController = new AnimalsController();
    private final CookingController cookingController = new CookingController();
    private final CraftingController craftingController = new CraftingController();
    private final ArtisanController artisanController = new ArtisanController();
    private final NPCController npcController = new NPCController();
    private final PlayersRelationController relationController = new PlayersRelationController();
    private final AbilityController abilityController = new AbilityController();
    private Stage stage;
    private final TextButton createNewGame;
    private final TextButton loadGame;
    private final TextButton showCurrentGame;
    private final TextButton exitGame;
    private final TextButton back;


    public GameMenu() {
        this.stage = new Stage();
        controller.setView(this);
        createNewGame = new TextButton("create game" , GamePictureManager.skin);
        createNewGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.handleCreateNewGame();
            }
        });
        loadGame = new TextButton("load game" , GamePictureManager.skin);
        loadGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        showCurrentGame = new TextButton("show current game" , GamePictureManager.skin);
        showCurrentGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        exitGame = new TextButton("exit game" , GamePictureManager.skin);
        exitGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        back = new TextButton("back" , GamePictureManager.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen screen = Main.getMain().getScreen();
                MainMenu mainMenu = new MainMenu();
                Main.getMain().setScreen(mainMenu);
                screen.dispose();

            }
        });
    }


    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher;

        if (com.stardew.models.enums.GameMenuCommands.LoadGame.getMatcher(input) != null) {
            System.out.println(controller.loadGame());
        }
        else if (com.stardew.models.enums.GameMenuCommands.ExitGame.getMatcher(input) != null) {
            System.out.println(controller.exitGame());
        }
        else if (com.stardew.models.enums.GameMenuCommands.DeleteGame.getMatcher(input) != null) {
            System.out.println(controller.deleteGame());
        }
        else if (com.stardew.models.enums.GameMenuCommands.NextTurn.getMatcher(input) != null) {
            System.out.println(controller.nextTurn());
        }


        else if (com.stardew.models.enums.GameMenuCommands.GetHour.getMatcher(input) != null) {
            System.out.println(dateController.getHour());
        }
        else if (com.stardew.models.enums.GameMenuCommands.GetDate.getMatcher(input) != null) {
            System.out.println(dateController.getDate());
        }
        else if (com.stardew.models.enums.GameMenuCommands.GetDate.getMatcher(input) != null) {
            System.out.println(dateController.getDateTime());
        }
        else if (GameMenuCommands.DayOfWeek.getMatcher(input) != null) {
            System.out.println(dateController.getDayOfTheWeek());
        }
        else if (GameMenuCommands.AdvancedTimeCheatCode.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.AdvancedTimeCheatCode.getMatcher(input);
            int hour = Integer.parseInt(matcher.group(1));
            System.out.println(dateController.advancedTimeCheatCode(hour));
        }
        else if (GameMenuCommands.AdvancedDateCheatCode.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.AdvancedDateCheatCode.getMatcher(input);
            int day = Integer.parseInt(matcher.group(1));
            System.out.println(dateController.advancedDateCheatCode(day));
        }
        else if (GameMenuCommands.GetSeason.getMatcher(input) != null) {
            System.out.println(dateController.getSeason());
        }


        else if (GameMenuCommands.GetTodayWeather.getMatcher(input) != null) {
            System.out.println(weatherController.getWeather());
        }
        else if (GameMenuCommands.WeatherForecast.getMatcher(input) != null) {
            System.out.println(weatherController.weatherForecast());
        }
        else if (GameMenuCommands.CheatWeatherSetCode.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.CheatWeatherSetCode.getMatcher(input);
            String weather = matcher.group(1).trim();
            System.out.println(weatherController.cheatWeatherSetCode(weather));
        }
        else if (GameMenuCommands.CheatThunder.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.CheatThunder.getMatcher(input);
            int thunderX = Integer.parseInt(matcher.group(1));
            int thunderY = Integer.parseInt(matcher.group(2));
            System.out.println(weatherController.cheatThunder(thunderX, thunderY));
        }


        else if (GameMenuCommands.GreenhouseBuild.getMatcher(input) != null) {
            System.out.println(controller.buildGreenhouse());
        }
        else if (GameMenuCommands.EnergyShow.getMatcher(input) != null) {
            System.out.println(energyController.showEnergy());
        }
        else if (GameMenuCommands.CheatSetEnergy.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.CheatSetEnergy.getMatcher(input);
            int energyValue = Integer.parseInt(matcher.group(1));
            System.out.println(energyController.setEnergy(energyValue));
        }
        else if (GameMenuCommands.CheatUnlimitedEnergy.getMatcher(input) != null) {
            System.out.println(energyController.setUnlimitedEnergy());
        }
        else if (GameMenuCommands.ExitMenu.getMatcher(input) != null) {
            App.setMenu(Menus.MainMenu);
        }
        else if (GameMenuCommands.PrintMap.getMatcher(input) != null) {
            System.out.println(controller.printMapAll());
        }
        else if (GameMenuCommands.Walk.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.Walk.getMatcher(input);
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            List<Position> positions = new LinkedList<Position>();
            Result result = controller.findPath(x, y, positions);
            System.out.println(result.getMessage());
            if (result.getSuccessful()) {
                System.out.println("do you want to continue?");
                String message = scanner.nextLine();
                if (message.equals("yes")) {
                    System.out.println(controller.walk(x, y, positions));
                }


            }
        }
        else if (GameMenuCommands.HelpReadingMap.getMatcher(input) != null) {
            System.out.println(controller.helpReadingMap());
        }
        else if (GameMenuCommands.InventoryShow.getMatcher(input) != null) {
            System.out.println(controller.inventoryShow());
        }
        else if (GameMenuCommands.InventoryTrash.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.InventoryTrash.getMatcher(input);
            boolean hasNumber = false;

            String name = matcher.group(1);
            if (matcher.group(3) != null) {
                hasNumber = true;
            }
            int number = Integer.parseInt(matcher.group(3));
            System.out.println(controller.inventoryTrash(name, number, hasNumber));
        }


        else if (GameMenuCommands.ToolsEquip.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.ToolsEquip.getMatcher(input);
            String toolName = matcher.group(1);
            System.out.println(toolController.ToolsEquip(toolName));
        }
        else if (GameMenuCommands.ShowCurrentTool.getMatcher(input) != null) {
            System.out.println(toolController.showCurrentTool());
        }
        else if (GameMenuCommands.ToolsShowAvailable.getMatcher(input) != null) {
            System.out.println(toolController.showAvailableTool());
        }
        else if (GameMenuCommands.ToolUpgrade.getMatcher(input) != null) {
            matcher = com.stardew.models.enums.GameMenuCommands.ToolUpgrade.getMatcher(input);
            String toolName = matcher.group(1);
            System.out.println(App.getGame().getMap().getNpcVillage().getBlacksmith().upgradeTool(toolName));
        }
//        else if (GameMenuCommands.ToolUse.getMatcher(input) != null) {
//            matcher = com.stardew.models.enums.GameMenuCommands.ToolUse.getMatcher(input);
//            String direction = matcher.group(1);
//            System.out.println(toolController.useTool(direction));
//        }


        else if ((matcher = GameMenuCommands.CraftInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.craftInfo(matcher.group("craftName")));
        }
        else if ((matcher = GameMenuCommands.CropInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.cropInfo(matcher.group("cropName")));
        }
        else if ((matcher = GameMenuCommands.TreeInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.treeInfo(matcher.group("treeName")));
        }
        else if ((matcher = GameMenuCommands.ForagingCropInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.foragingCropInfo(matcher.group("cropName")));
        }
        else if ((matcher = GameMenuCommands.ForagingTreeInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.foragingTreeInfo(matcher.group("treeName")));
        }
//        else if ((matcher = GameMenuCommands.Plant.getMatcher(input)) != null) {
//            System.out.println(foragingController.plant(matcher.group("seed"), matcher.group("direction")));
//        }
//        else if ((matcher = GameMenuCommands.Fertilize.getMatcher(input)) != null) {
//            System.out.println(foragingController.fertilize(matcher.group("fertilizer"), matcher.group("direction")));
//        }
//        else if ((matcher = GameMenuCommands.ShowPlant.getMatcher(input)) != null) {
//            System.out.println(foragingController.showPlant(
//                    Integer.parseInt(matcher.group("X")), Integer.parseInt(matcher.group("Y"))));
//        }


//        else if (GameMenuCommands.CraftingShowRecipes.getMatcher(input) != null) {
//            System.out.println(craftingController.craftingShowRecipes());
//        }
//        else if ((matcher = GameMenuCommands.CraftingCraft.getMatcher(input)) != null) {
//            System.out.println(craftingController.craftingCraft(matcher.group("itemName")));
//        }
//        else if ((matcher = GameMenuCommands.CheatAddItem.getMatcher(input)) != null) {
//            System.out.println(craftingController.addItem(
//                    matcher.group("itemName"), Integer.parseInt(matcher.group("count"))));
//        }


//        else if ((matcher = GameMenuCommands.CookingRefrigeratorPutPick.getMatcher(input)) != null) {
//            System.out.println(cookingController.cookingRefrigeratorPutPick(
//                    matcher.group("action"), matcher.group("itemName")));
//        }
//        else if (GameMenuCommands.CookingShowRecipes.getMatcher(input) != null) {
//            System.out.println(cookingController.cookingShowRecipes());
//        }
//        else if ((matcher = GameMenuCommands.CookingPrepare.getMatcher(input)) != null) {
//            System.out.println(cookingController.cookingPrepare(matcher.group("itemName")));
//        }
//        else if ((matcher = GameMenuCommands.Eat.getMatcher(input)) != null) {
//            System.out.println(cookingController.eat(matcher.group("foodName")));
//        }


//        else if ((matcher = GameMenuCommands.Build.getMatcher(input)) != null) {
//            System.out.println(animalsController.build(
//                    matcher.group("buildingName"), Integer.parseInt(matcher.group("X")),
//                    Integer.parseInt(matcher.group("Y"))));
//        }
//        else if ((matcher = GameMenuCommands.BuyAnimal.getMatcher(input)) != null) {
//            System.out.println(animalsController.buyAnimal(
//                    matcher.group("animal"), matcher.group("name")));
//        }
//        else if ((matcher = GameMenuCommands.Pet.getMatcher(input)) != null) {
//            System.out.println(animalsController.pet(matcher.group("name")));
//        }
//        else if ((matcher = GameMenuCommands.CheatSetFriendship.getMatcher(input)) != null) {
//            System.out.println(animalsController.setFriendship(
//                    matcher.group("animalName"), Integer.parseInt(matcher.group("amount"))));
//        }
//        else if (GameMenuCommands.Animals.getMatcher(input) != null) {
//            System.out.println(animalsController.animalsInfo());
//        }
//        else if ((matcher = GameMenuCommands.ShepherdAnimal.getMatcher(input)) != null) {
//            System.out.println(animalsController.shepherdAnimal(
//                    matcher.group("animalName")));
//        }
//        else if ((matcher = GameMenuCommands.FeedHay.getMatcher(input)) != null) {
//            System.out.println(animalsController.feedHay(matcher.group("animalName")));
//        }
//        else if (GameMenuCommands.AnimalsProduces.getMatcher(input) != null) {
//            System.out.println(animalsController.animalProduces());
//        }
//        else if ((matcher = GameMenuCommands.CollectProduce.getMatcher(input)) != null) {
//            System.out.println(animalsController.collectProduce(matcher.group("animalName")));
//        }
//        else if ((matcher = GameMenuCommands.SellAnimal.getMatcher(input)) != null) {
//            System.out.println(animalsController.sellAnimal(matcher.group("animalName")));
//        }


//        else if ((matcher = GameMenuCommands.ArtisanUse.getMatcher(input)) != null) {
//            System.out.println(artisanController.artisanUse(matcher.group("artisanName"), matcher.group("itemName")));
//        }
//        else if ((matcher = GameMenuCommands.ArtisanGet.getMatcher(input)) != null) {
//            System.out.println(artisanController.artisanGet(matcher.group("artisanName")));
//        }



        else if ((matcher = GameMenuCommands.CheatAddDollars.getMatcher(input)) != null) {
            System.out.println(controller.cheatAddDollars(matcher));
        }
        else if ((matcher = GameMenuCommands.UseGreenHouseForHarvesting.getMatcher(input)) != null) {
            System.out.println(controller.useGreenHouseForWatering());
        }
        else if ((matcher = GameMenuCommands.UseGreenHouseForHarvesting.getMatcher(input)) != null) {
            System.out.println(controller.useGreenHouseForHarvesting());
        }
        else if (GameMenuCommands.StoreMenu.getMatcher(input) != null) {
            System.out.println(controller.storeMenu());
        }
        else if ((matcher = NPCsCommands.MeetNPC.getMatcher(input)) != null) {
            System.out.println(npcController.meetNPC(matcher));
        }
        else if ((matcher = NPCsCommands.GiftToNPC.getMatcher(input)) != null) {
            System.out.println(npcController.giftToNPC(matcher));
        }
        else if (NPCsCommands.FriendShipNPCList.getMatcher(input) != null) {
            System.out.println(npcController.friendShipNPCList());
        }
        else if (NPCsCommands.QuestsList.getMatcher(input) != null) {
            System.out.println(npcController.questsList());
        }
        else if ((matcher = NPCsCommands.FinishingQuest.getMatcher(input)) != null) {
            System.out.println(npcController.finishingQuest(matcher));
        }
        else if((matcher = GameMenuCommands.PrintMapWithSize.getMatcher(input)) !=null) {
            int startX = Integer.parseInt(matcher.group("X"));
            int startY = Integer.parseInt(matcher.group("Y"));
            int sizeX = Integer.parseInt(matcher.group("size"));
            System.out.println(controller.printMap(startX, startY, sizeX));
        }
        else if (GameMenuCommands.FriendShips.getMatcher(input) != null) {
            //System.out.println(relationController.friendships());
        }
        else if ((matcher = GameMenuCommands.TalkToPlayer.getMatcher(input)) != null) {
            System.out.println(relationController.TalkToPlayer(matcher));
        }
        else if ((matcher = GameMenuCommands.TalkHistory.getMatcher(input)) != null) {
            System.out.println(relationController.talkHistory(matcher));
        }
        else if ((matcher = GameMenuCommands.GiftToPLayer.getMatcher(input)) != null) {
                     //System.out.println(relationController.GiftToPLayer(matcher));
                }
        else if (GameMenuCommands.GiftList.getMatcher(input) != null) {
                    System.out.println(relationController.GiftList());
                }
        else if ((matcher = GameMenuCommands.GiftRate.getMatcher(input)) != null) {
                        //System.out.println(relationController.giftRate(matcher));
                }
        else if ((matcher = GameMenuCommands.GiftHistory.getMatcher(input)) != null) {
                        //System.out.println(relationController.GiftHistory(matcher));
                }
        else if ((matcher = GameMenuCommands.Hug.getMatcher(input)) != null) {
                        //System.out.println(relationController.hug(matcher));
                }
        else if ((matcher = GameMenuCommands.FlowerTOPlayer.getMatcher(input)) != null) {
                        //System.out.println(relationController.giveFlower(matcher));
                }
        else if ((matcher = GameMenuCommands.AskMarriage.getMatcher(input)) != null) {
                        //System.out.println(relationController.askMarriage(matcher));
                }
        else if ((matcher = GameMenuCommands.RespondMarriageRequest.getMatcher(input)) != null) {
                        //System.out.println(relationController.respondMarriage(matcher));
                }
        else if ((matcher = GameMenuCommands.StartTrade.getMatcher(input)) != null ) {
                        System.out.println(controller.startTrade());
                }

        else if(GameMenuCommands.ShowFishingAbility.getMatcher(input) != null) {
                    System.out.println(abilityController.showFishingAbility());
                }

        else if(GameMenuCommands.ShowForagingAbility.getMatcher(input) != null) {
                    System.out.println(abilityController.showForagingAbility());
                }

        else if(GameMenuCommands.ShowMiningAbility.getMatcher(input) != null) {
                    System.out.println(abilityController.showMiningAbility());
                }

        else if(GameMenuCommands.ShowFarmingAbility.getMatcher(input) != null) {
                    System.out.println(abilityController.showFarmingAbility());
                }

        else if (com.stardew.models.enums.GameMenuCommands.NewGame.getMatcher(input) != null) {
            ArrayList<Player> players = new ArrayList<>();
            Player currentPlayer = new Player(App.getLoggedInUser().getUsername(),
                    App.getLoggedInUser().getNickname(), App.getLoggedInUser());
            players.add(currentPlayer);

            Result result1 = controller.createNewGamePlayers(input, players);
            System.out.println(result1);
            if (result1.getSuccessful()) {

                int x = players.size();
                System.out.println("number of created players: " + x);
                for (Player p : players) {
                    System.out.println(p.getUsername());
                }
                int[] startXForMap = {0, 150, 0, 150};
                int[] startYForMap = {0, 0, 125, 125};
                for (int i = 0; i < x; i++) {
                    String command;
                    while ((command = scanner.nextLine()) != null) {
                        if (com.stardew.models.enums.GameMenuCommands.GameMap.getMatcher(command) != null) {
                            matcher = com.stardew.models.enums.GameMenuCommands.GameMap.getMatcher(command);
                            String Map = matcher.group(1);
                            int mapNumber = Integer.parseInt(matcher.group(1));
                            Result result = controller.selectMapForCreateNewGame(mapNumber, players.get(i),
                                    startXForMap[i], startYForMap[i]);
                            System.out.println(result);
                            if (result.getSuccessful()) {

                                break;
                            }


                        } else {
                            System.out.println("invalid command , please try again!");
                        }


                    }


                }
                System.out.println(controller.createNewGame(players));

            }
        } else {
            System.out.println("invalid command");
        }

    }

    @Override
    public Stage getStage() {
        return null;
    }

    public void doNights() {
        System.out.println(controller.walkPlayersToTheirHome());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        TextureRegionDrawable bgTex = GamePictureManager.menuBackground;
        Image background = new Image(bgTex);
        background.setFillParent(true);
        stage.addActor(background);

        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;



        createNewGame.setSize(200, 40);
        createNewGame.setPosition(centerX - 100, centerY + 60);

        loadGame.setSize(200, 40);
        loadGame.setPosition(centerX - 100, centerY);

        showCurrentGame.setSize(200, 40);
        showCurrentGame.setPosition(centerX - 100, centerY - 60);

        exitGame.setSize(200, 40);
        exitGame.setPosition(centerX - 100, centerY - 120);

        back.setSize(200, 40);
        back.setPosition(centerX - 100, centerY - 180);

        stage.addActor(createNewGame);
        stage.addActor(loadGame);
        stage.addActor(showCurrentGame);
        stage.addActor(exitGame);
        stage.addActor(back);


        MenuAnimationController.addHoverAnimation(createNewGame);
        MenuAnimationController.addHoverAnimation(loadGame);
        MenuAnimationController.addHoverAnimation(exitGame);
        MenuAnimationController.addHoverAnimation(back);
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
}
