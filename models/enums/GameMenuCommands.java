package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Command {

    GameMap("\\s*game\\s+map\\s+(?<numberOfMMap>\\S+)\\s*"),
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
