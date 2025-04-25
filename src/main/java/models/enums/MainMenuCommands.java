package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands implements Command {
    UserLogout("\\s*user\\s+logout\\s*"),
    EnterGameMenu("\\s*enter\\s+menu\\s+game\\s+menu\\s*"),
    EnterProfileMenu("\\s*enter\\s+menu\\s+profile\\s+menu\\s*"),
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*");


    private final String pattern;
    MainMenuCommands(String pattern) {
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
