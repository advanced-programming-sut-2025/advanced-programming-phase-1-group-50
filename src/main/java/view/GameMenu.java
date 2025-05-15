package view;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

import controller.AbilityAndEnergyController.EnergyController;
import controller.AnimalsControllers.AnimalsController;
import controller.CookingAndCraftingControllers.ArtisanController;
import controller.CookingAndCraftingControllers.CookingController;
import controller.CookingAndCraftingControllers.CraftingController;
import controller.ForagingControllers.ForagingController;
import controller.GameDateAndWeatherController.DateController;
import controller.GameDateAndWeatherController.WeatherController;
import controller.NPCController.NPCController;
import controller.PlayersRealtionController.PlayersRelationController;
import controller.ToolsControllers.ToolController;
import models.app.App;
import models.app.Menus;
import models.enums.GameMenuCommands;
import models.enums.NPCsCommands;
import models.mapInfo.Position;
import models.userInfo.*;
import models.Result;
import controller.GameMenuController;

public class GameMenu implements AppMenu {
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

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        input = input.trim();
        Matcher matcher;

        if (models.enums.GameMenuCommands.LoadGame.getMatcher(input) != null) {
            System.out.println(controller.loadGame());
        } else if (models.enums.GameMenuCommands.ExitGame.getMatcher(input) != null) {
            System.out.println(controller.exitGame());
        } else if (models.enums.GameMenuCommands.DeleteGame.getMatcher(input) != null) {
            System.out.println(controller.deleteGame());
        } else if (models.enums.GameMenuCommands.NextTurn.getMatcher(input) != null) {
            System.out.println(controller.nextTurn());
        } else if (models.enums.GameMenuCommands.GetHour.getMatcher(input) != null) {
            System.out.println(dateController.getHour());
        } else if (models.enums.GameMenuCommands.GetDate.getMatcher(input) != null) {
            System.out.println(dateController.getDate());
        } else if (models.enums.GameMenuCommands.GetDate.getMatcher(input) != null) {
            System.out.println(dateController.getDateTime());
        } else if (GameMenuCommands.DayOfWeek.getMatcher(input) != null) {
            System.out.println(dateController.getDayOfTheWeek());
        } else if (GameMenuCommands.AdvancedTimeCheatCode.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.AdvancedTimeCheatCode.getMatcher(input);
            int hour = Integer.parseInt(matcher.group(1));
            System.out.println(dateController.advancedTimeCheatCode(hour));
        } else if (GameMenuCommands.AdvancedDateCheatCode.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.AdvancedDateCheatCode.getMatcher(input);
            int day = Integer.parseInt(matcher.group(1));
            System.out.println(dateController.advancedDateCheatCode(day));
        } else if (GameMenuCommands.GetSeason.getMatcher(input) != null) {
            System.out.println(dateController.getSeason());
        } else if (GameMenuCommands.GetTodayWeather.getMatcher(input) != null) {
            System.out.println(weatherController.getWeather());
        } else if (GameMenuCommands.WeatherForecast.getMatcher(input) != null) {
            System.out.println(weatherController.weatherForecast());
        } else if (GameMenuCommands.CheatWeatherSetCode.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.CheatWeatherSetCode.getMatcher(input);
            String weather = matcher.group(1).trim();
            System.out.println(weatherController.cheatWeatherSetCode(weather));
        } else if (GameMenuCommands.CheatThunder.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.CheatThunder.getMatcher(input);
            int thunderX = Integer.parseInt(matcher.group(1));
            int thunderY = Integer.parseInt(matcher.group(2));
            System.out.println(weatherController.cheatThunder(thunderX, thunderY));
        } else if (GameMenuCommands.GreenhouseBuild.getMatcher(input) != null) {
            System.out.println(controller.buildGreenhouse());
        } else if (GameMenuCommands.EnergyShow.getMatcher(input) != null) {
            System.out.println(energyController.showEnergy());
        } else if (GameMenuCommands.CheatSetEnergy.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.CheatSetEnergy.getMatcher(input);
            int energyValue = Integer.parseInt(matcher.group(1));
            System.out.println(energyController.setEnergy(energyValue));
        } else if (GameMenuCommands.CheatUnlimitedEnergy.getMatcher(input) != null) {
            System.out.println(energyController.setUnlimitedEnergy());
        } else if (GameMenuCommands.ExitMenu.getMatcher(input) != null) {
            App.setMenu(Menus.MainMenu);
        } else if (GameMenuCommands.PrintMap.getMatcher(input) != null) {
            System.out.println(controller.printMapAll());
        } else if (GameMenuCommands.Walk.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.Walk.getMatcher(input);
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
        } else if (GameMenuCommands.HelpReadingMap.getMatcher(input) != null) {
            System.out.println(controller.helpReadingMap());
        } else if (GameMenuCommands.InventoryShow.getMatcher(input) != null) {
            System.out.println(controller.inventoryShow());
        } else if (GameMenuCommands.InventoryTrash.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.InventoryTrash.getMatcher(input);
            boolean hasNumber = false;

            String name = matcher.group(1);
            if (matcher.group(3) != null) {
                hasNumber = true;
            }
            int number = Integer.parseInt(matcher.group(3));
            System.out.println(controller.inventoryTrash(name, number, hasNumber));
        } else if (GameMenuCommands.ToolsEquip.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.ToolsEquip.getMatcher(input);
            String toolName = matcher.group(1);
            System.out.println(toolController.ToolsEquip(toolName));
        } else if (GameMenuCommands.ShowCurrentTool.getMatcher(input) != null) {
            System.out.println(toolController.showCurrentTool());
        } else if (GameMenuCommands.ToolsShowAvailable.getMatcher(input) != null) {
            System.out.println(toolController.showAvailableTool());
        } else if (GameMenuCommands.ToolUpgrade.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.ToolUpgrade.getMatcher(input);
            String toolName = matcher.group(1);
            System.out.println(toolController.upgradeTool(toolName));
        } else if (GameMenuCommands.ToolUse.getMatcher(input) != null) {
            matcher = models.enums.GameMenuCommands.ToolUse.getMatcher(input);
            String direction = matcher.group(1);
            System.out.println(toolController.useTool(direction));
        } else if ((matcher = GameMenuCommands.CraftInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.craftInfo(matcher.group("craftName")));
        } else if ((matcher = GameMenuCommands.CropInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.cropInfo(matcher.group("cropName")));
        } else if ((matcher = GameMenuCommands.TreeInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.treeInfo(matcher.group("treeName")));
        } else if ((matcher = GameMenuCommands.ForagingCropInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.foragingCropInfo(matcher.group("cropName")));
        } else if ((matcher = GameMenuCommands.ForagingTreeInfo.getMatcher(input)) != null) {
            System.out.println(foragingController.foragingTreeInfo(matcher.group("treeName")));
        } else if ((matcher = GameMenuCommands.Plant.getMatcher(input)) != null) {
            System.out.println(foragingController.plant(matcher.group("seed"), matcher.group("direction")));
        } else if ((matcher = GameMenuCommands.Fertilize.getMatcher(input)) != null) {
            System.out.println(foragingController.fertilize(matcher.group("fertilizer"), matcher.group("direction")));
        } else if ((matcher = GameMenuCommands.ShowPlant.getMatcher(input)) != null) {
            System.out.println(foragingController.showPlant(
                    Integer.parseInt(matcher.group("X")), Integer.parseInt(matcher.group("Y"))));
        } else if (GameMenuCommands.CraftingShowRecipes.getMatcher(input) != null) {
            System.out.println(craftingController.craftingShowRecipes());
        } else if ((matcher = GameMenuCommands.CraftingCraft.getMatcher(input)) != null) {
            System.out.println(craftingController.craftingCraft(matcher.group("itemName")));
        } else if ((matcher = GameMenuCommands.CheatAddItem.getMatcher(input)) != null) {
            System.out.println(craftingController.addItem(
                    matcher.group("itemName"), Integer.parseInt(matcher.group("count"))));
        } else if ((matcher = GameMenuCommands.CookingRefrigeratorPutPick.getMatcher(input)) != null) {
            System.out.println(cookingController.cookingRefrigeratorPutPick(
                    matcher.group("action"), matcher.group("itemName")));
        } else if (GameMenuCommands.CookingShowRecipes.getMatcher(input) != null) {
            System.out.println(cookingController.cookingShowRecipes());
        } else if ((matcher = GameMenuCommands.CookingPrepare.getMatcher(input)) != null) {
            System.out.println(cookingController.cookingPrepare(matcher.group("itemName")));
        } else if ((matcher = GameMenuCommands.Eat.getMatcher(input)) != null) {
            System.out.println(cookingController.eat(matcher.group("foodName")));
        } else if ((matcher = GameMenuCommands.Build.getMatcher(input)) != null) {
            System.out.println(animalsController.build(
                    matcher.group("buildingName"), Integer.parseInt(matcher.group("X")),
                    Integer.parseInt(matcher.group("Y"))));
        } else if ((matcher = GameMenuCommands.BuyAnimal.getMatcher(input)) != null) {
            System.out.println(animalsController.buyAnimal(
                    matcher.group("animal"), matcher.group("name")));
        } else if ((matcher = GameMenuCommands.Pet.getMatcher(input)) != null) {
            System.out.println(animalsController.pet(matcher.group("name")));
        } else if ((matcher = GameMenuCommands.CheatSetFriendship.getMatcher(input)) != null) {
            System.out.println(animalsController.setFriendship(
                    matcher.group("animalName"), Integer.parseInt(matcher.group("amount"))));
        } else if (GameMenuCommands.Animals.getMatcher(input) != null) {
            System.out.println(animalsController.animalsInfo());
        } else if ((matcher = GameMenuCommands.ShepherdAnimal.getMatcher(input)) != null) {
            System.out.println(animalsController.shepherdAnimal(
                    matcher.group("animalName"), Integer.parseInt(matcher.group("X")),
                    Integer.parseInt(matcher.group("Y"))));
        } else if ((matcher = GameMenuCommands.FeedHay.getMatcher(input)) != null) {
            System.out.println(animalsController.feedHay(matcher.group("animalName")));
        } else if (GameMenuCommands.AnimalsProduces.getMatcher(input) != null) {
            System.out.println(animalsController.animalProduces());
        } else if ((matcher = GameMenuCommands.CollectProduce.getMatcher(input)) != null) {
            System.out.println(animalsController.collectProduce(matcher.group("animalName")));
        } else if ((matcher = GameMenuCommands.SellAnimal.getMatcher(input)) != null) {
            System.out.println(animalsController.sellAnimal(matcher.group("animalName")));
        } else if ((matcher = GameMenuCommands.ArtisanUse.getMatcher(input)) != null) {
            System.out.println(artisanController.artisanUse(matcher.group("artisanName"), matcher.group("itemName")));
        } else if ((matcher = GameMenuCommands.ArtisanGet.getMatcher(input)) != null) {
            System.out.println(artisanController.artisanGet(matcher.group("artisanName")));
        } else if ((matcher = GameMenuCommands.CheatAddDollars.getMatcher(input)) != null) {
            System.out.println(controller.cheatAddDollars(matcher));
        } else if ((matcher = GameMenuCommands.UseGreenHouseForHarvesting.getMatcher(input)) != null) {
            System.out.println(controller.useGreenHouseForWatering());
        } else if ((matcher = GameMenuCommands.UseGreenHouseForHarvesting.getMatcher(input)) != null) {
            System.out.println(controller.useGreenHouseForHarvesting());
        } else if (GameMenuCommands.StoreMenu.getMatcher(input) != null) {
            System.out.println(controller.storeMenu());
        } else if ((matcher = NPCsCommands.MeetNPC.getMatcher(input)) != null) {
            System.out.println(npcController.meetNPC(matcher));
        } else if ((matcher = NPCsCommands.GiftToNPC.getMatcher(input)) != null) {
            System.out.println(npcController.giftToNPC(matcher));
        } else if (NPCsCommands.FriendShipNPCList.getMatcher(input) != null) {
            System.out.println(npcController.friendShipNPCList());
        } else if (NPCsCommands.QuestsList.getMatcher(input) != null) {
            System.out.println(npcController.questsList());
        } else if ((matcher = NPCsCommands.FinishingQuest.getMatcher(input)) != null) {
            System.out.println(npcController.finishingQuest(matcher));
        } else if((matcher = GameMenuCommands.PrintMapWithSize.getMatcher(input)) !=null) {
            int startX = Integer.parseInt(matcher.group("X"));
            int startY = Integer.parseInt(matcher.group("Y"));
            int sizeX = Integer.parseInt(matcher.group("size"));
            System.out.println(controller.printMap(startX, startY, sizeX));
        } else if ((matcher = GameMenuCommands.SellProduct.getMatcher(input)) != null) {
            //TODO
        } else if (GameMenuCommands.FriendShips.getMatcher(input) != null) {
            System.out.println(relationController.friendships());
        } else if ((matcher = GameMenuCommands.TalkToPlayer.getMatcher(input)) != null) {
            System.out.println(relationController.TalkToPlayer(matcher));
        } else if ((matcher = GameMenuCommands.TalkHistory.getMatcher(input)) != null) {
            System.out.println(relationController.talkHistory(matcher));
        } else if ((matcher = GameMenuCommands.GiftToPLayer.getMatcher(input)) != null) {
            //TODO
        } else if (GameMenuCommands.GiftList.getMatcher(input) != null) {
            System.out.println(relationController.GiftList());
        } else if ((matcher = GameMenuCommands.GiftRate.getMatcher(input)) != null) {

        } else if ((matcher = GameMenuCommands.GiftHistory.getMatcher(input)) != null) {

        } else if ((matcher = GameMenuCommands.Hug.getMatcher(input)) != null) {

        } else if ((matcher = GameMenuCommands.FlowerTOPlayer.getMatcher(input)) != null) {

        } else if ((matcher = GameMenuCommands.AskMarriage.getMatcher(input)) != null) {

        } else if ((matcher = GameMenuCommands.RespondMarriageRequest.getMatcher(input)) != null) {

        } else if ((matcher = GameMenuCommands.StartTrade.getMatcher(input)) != null ) {

        } else if (models.enums.GameMenuCommands.NewGame.getMatcher(input) != null) {
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
                        if (models.enums.GameMenuCommands.GameMap.getMatcher(command) != null) {
                            matcher = models.enums.GameMenuCommands.GameMap.getMatcher(command);
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

    public void doNights() {
        System.out.println(controller.walkPlayersToTheirHome());
    }

}
