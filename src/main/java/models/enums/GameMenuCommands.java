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
    Walk("\\s*walk\\s+-l\\s+<(?<x>\\d+)\\s+,\\s+(?<y>\\d+)>\\s*"),
    HelpReadingMap("\\s*help\\s+map\\s+read\\s*"),
    InventoryShow("\\s*inventory\\s+show\\s*"),
    InventoryTrash("\\s*inventory\\s+trash\\s+-i\\s+(?<itemName>\\S+)\\s+(-n\\s+(?<number>\\d+))?\\s*"),
    ToolsEquip("\\s*tools\\s+equip\\s+-n\\s+(?<name>\\S+)\\s*"),
    ShowCurrentTool("\\s*show\\s+current\\s+tool\\s*"),
    ToolsShowAvailable("\\s*tools\\s+show\\s+available\\s*"),

    // cheat codes :
    AdvancedTimeCheatCode("\\s*advanced\\s+time\\s+(?<advancedTime>-?\\d+)\\s*"),
    AdvancedDateCheatCode("\\s*advanced\\s+date\\s+(?<advancedDate>-?\\d+)\\s*"),
    CheatWeatherSetCode("\\s*cheat\\s+weather\\s+set\\s+(?<weather>\\S+)\\s*"),
    CheatThunder("\\s*cheat\\s+thor\\s+-l\\s+<(?<thunderX>-?\\d+)\\s+,(?<thunderY>-?\\d+)\\s*"),
    CheatSetEnergy("\\s*energy\\s+set\\s+-v\\s+(?<energy>-?\\d+)\\s*"),
    CheatUnlimitedEnergy("\\s*energy\\s+unlimited\\s*"),
    NewGame(".+");

    private final String pattern;
    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }
    public Matcher getMatcher(String regex){
       Matcher matcher = Pattern.compile(this.pattern).matcher(regex);
       if(matcher.matches()){
           return matcher;
       }
       return null;
    }
}
