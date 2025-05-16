package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {

    GameMap("\\s*game\\s+map\\s+(?<numberOfMMap>\\d+)\\s*"),
    LoadGame("\\s*load\\s+game\\s*"),
    ExitGame("\\s*exit\\s+game\\s*"),
    DeleteGame("\\s*delete\\s+game\\s*"),
    NextTurn("\\s*next\\s+turn\\s*"),
    GetHour("\\s*get\\s+hour\\s*"),
    GetDate("\\s*get\\s+date\\s*"),
    DateTime("\\s*date\\s+time\\s*"),
    DayOfWeek("\\s*day\\s+of\\s+of\\s+the\\s+week\\s*"),
    GetSeason("\\s*get\\s+season\\s*"),
    GetTodayWeather("\\s*get\\s+weather\\s*"),
    WeatherForecast("\\s*weather\\s*forecast\\s*"),
    GreenhouseBuild("\\s*greenhouse\\s+build\\s*"),
    EnergyShow("\\s*energy\\s+show\\s*"),
    ExitMenu("\\s*exit\\s+menu\\s*"),
    PrintMap("\\s*print\\s+map\\s*"),
    Walk("\\s*walk\\s+-l\\s+<(?<x>\\d+)\\s*,\\s*(?<y>\\d+)>\\s*"),
    HelpReadingMap("\\s*help\\s+map\\s+read\\s*"),
    InventoryShow("\\s*inventory\\s+show\\s*"),
    InventoryTrash("\\s*inventory\\s+trash\\s+-i\\s+(?<itemName>\\S+)\\s+(-n\\s+(?<number>\\d+))?\\s*"),
    ToolsEquip("\\s*tools\\s+equip\\s+-n\\s+(?<name>\\S+)\\s*"),
    ShowCurrentTool("\\s*show\\s+current\\s+tool\\s*"),
    ToolsShowAvailable("\\s*tools\\s+show\\s+available\\s*"),
    ToolUpgrade("\\s*tools\\s+upgrade\\s+-n\\s+(?<name>\\S+)\\s*"),
    ToolUse("\\s*tools\\s+use\\s+-d\\s+(?<direction>\\S+)\\s*"),
    UseGreenHouseForWatering("use\\s+greenhouse\\s+for\\s+watering\\s*"),
    UseGreenHouseForHarvesting("use\\s+greenhouse\\s+for\\s+harvesting\\s*"),
    AddPlantToGreenHouse("\\s*add\\s+plant\\s+to\\s+greenhouse\\s*"),
    StoreMenu("\\s*store\\s+menu\\s*"),
    SellProduct("\\s*sell\\s+(?<productName>.+?)(\\s+-n\\s+(?<amount>\\d+))?\\s*"), // TODO
    FriendShips("\\s*friendships\\s*"),
    TalkToPlayer("\\s*talk\\s+-u\\s+(?<username>.+?)\\s+-m\\s+(?<message>.+?)\\s*"),
    TalkHistory("\\s*talk\\s+history\\s+-u\\s+(?<username>.+?)\\s*"),
    GiftToPLayer("\\s*gift\\s+-u\\s+(?<username>.+?)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+(?<amount>\\d+)\\s*"),
    GiftList("\\s*gift\\s+list\\s*"),
    GiftRate("\\s*gift\\s+rate\\s+-i\\s+(?<id>\\d+)\\s+-r\\s+(?<rate>\\d+)\\s*"),
    GiftHistory("\\s*gift\\s+history\\s+-u\\s+(?<username>.+?)\\s*"),
    Hug("\\s*hug\\s+-u\\s+(?<username>.+?)\\s*"),
    FlowerTOPlayer("\\s*flower\\s+-u\\s+(?<username>.+?)\\s*"),
    AskMarriage("\\s*ask\\s+marriage\\s+-u\\s+(?<username>.+?)\\s*"),
    RespondMarriageRequest("\\s*respond\\s+-(?<state>accept|reject)\\s+-u\\s+(?<username>.+?)\\s*"),
    StartTrade("\\s*start\\s+trade\\s*"),//TODO
    PrintMapWithSize("\\s*print\\s+map\\s+-x\\s+(?<X>\\d+)\\s+-y\\s+(?<Y>\\d+)\\s+-size\\s+(?<size>\\d+)\\s*"),


    // cheat codes :
    AdvancedTimeCheatCode("\\s*advanced\\s+time\\s+(?<advancedTime>-?\\d+)\\s*"),
    AdvancedDateCheatCode("\\s*advanced\\s+date\\s+(?<advancedDate>-?\\d+)\\s*"),
    CheatWeatherSetCode("\\s*cheat\\s+weather\\s+set\\s+(?<weather>\\S+)\\s*"),
    CheatThunder("\\s*cheat\\s+thor\\s+-l\\s+<(?<thunderX>-?\\d+)\\s*,\\s*(?<thunderY>-?\\d+)\\s*"),
    CheatSetEnergy("\\s*energy\\s+set\\s+-v\\s+(?<energy>-?\\d+)\\s*"),
    CheatUnlimitedEnergy("\\s*energy\\s+unlimited\\s*"),
    CheatAddDollars("\\s*cheat\\s+add\\s+(?<amount>-?\\d+)\\s*"),

    NewGame(".+"),

    CraftInfo("craftInfo\\s+" +
            "-n\\s+(?<craftName>\\S+)\\s*"),
    CropInfo("cropInfo\\s+" +
            "-n\\s+(?<cropName>\\S+)\\s*"),
    TreeInfo("treeInfo\\s+" +
            "-n\\s+(?<treeName>\\S+)\\s*"),
    ForagingCropInfo("foragingCropInfo\\s+" +
            "-n\\s+(?<cropName>\\S+)\\s*"),
    ForagingTreeInfo("foragingTreeInfo\\s+" +
            "-n\\s+(?<treeName>\\S+)\\s*"),
    Plant("plant\\s+" +
            "-s\\s+(?<seed>\\S+)\\s+" +
            "-d\\s+(?<direction>\\S+)\\s*"),
    Fertilize("plant\\s+" +
            "-f\\s+(?<fertilizer>\\S+)\\s+" +
            "-d\\s+(?<direction>\\S+)\\s*"),
    ShowPlant("show\\s+plant\\s+" +
            "-l\\s+(?<X>\\d+)\\s*,\\s*(?<Y>\\d+)\\s*"),
    HowMuchWater("howMuch\\s+water\\s*"),
    CraftingShowRecipes("crafting\\s+show\\s+recipes\\s*"),
    CraftingCraft("crafting\\s+craft\\s+" +
            "(?<itemName>\\S+)\\s*"),
    CheatAddItem("cheat\\s+add\\s+item\\s+" +
            "-n\\s+(?<itemName>.+?)\\s+" +
            "-c\\s+(?<count>-?\\d+)\\s*"),
    CookingRefrigeratorPutPick("cooking\\s+refrigerator\\s+" +
            "(?<action>put|pick)\\s+" +
            "(?<itemName>\\S+)\\s*"),
    CookingShowRecipes("cooking\\s+show\\s+recipes\\s*"),
    CookingPrepare("cooking\\s+prepare\\s+" +
            "(?<itemName>\\S+)\\s*"),
    Eat("eat\\s+" +
            "(?<foodName>\\S+)\\s*"),
    Build("build\\s+" +
            "-a\\s+(?<buildingName>.+?)\\s+" +
            "-l\\s+(?<X>\\d+)\\s*,\\s*(?<Y>\\d+)\\s*"),
    BuyAnimal("buy\\s+animal\\s+" +
            "-a\\s+(?<animal>\\S+)\\s+" +
            "-n\\s+(?<name>.+?)\\s*"),
    Pet("pet\\s+" +
            "-n\\s+(?<name>.+?)\\s*"),
    CheatSetFriendship("cheat\\s+set\\s+friendship\\s+" +
            "-n\\s+(?<animalName>.+?)\\s+" +
            "-c\\s+(?<amount>-?\\d+)\\s*"),
    Animals("animals\\s*"),
    ShepherdAnimal("shepherd\\s+animal\\s+" +
            "-n\\s+(?<animalName>.+?)\\s+" +
            "-l\\s+(?<X>\\d+)\\s*,\\s*(?<Y>\\d+)\\s*"),
    FeedHay("feed\\s+hay\\s+" +
            "-n\\s+(?<animalName>.+?)\\s*"),
    AnimalsProduces("produces\\s*"),
    CollectProduce("collect\\s+produce\\s+" +
            "-n\\s+(?<animalName>.+?)\\s*"),
    SellAnimal("sell\\s+animal\\s+" +
            "-n\\s+(?<animalName>.+?)\\s*"),
    ArtisanUse("artisan\\s+use\\s+" +
            "(?<artisanName>\\S+)\\s+" +
            "(?<itemName>\\S+)\\s*"),
    ArtisanGet("artisan\\s+get\\s+" +
            "(?<artisanName>\\S+)\\s*");


    private final String pattern;

    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String regex) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(regex);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
