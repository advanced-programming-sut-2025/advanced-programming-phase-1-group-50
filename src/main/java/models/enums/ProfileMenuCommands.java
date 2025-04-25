package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands implements Command {

    ChangePassowrd("\\s*change\\s+password\\s+-p\\s+(?<newPassword>\\S+)\\s+-o\\s+(?<oldPassword>\\S+)\\s*"),
    ChangeUsername("\\s*change\\s+username\\s+-u\\s+(?<username>\\S+)\\s*"),
    ChangeEmail("\\s*change\\s+email\\s+(?<email>\\S+)\\s*"),
    UserInfo("\\s*user\\s+info\\s*"),
    ChangeNickname("\\s*change\\s+nickname\\s+-n\\s+(?<nickname>\\S+)\\s*"),
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*");

    private final  String pattern;
    ProfileMenuCommands(String pattern) {
        this.pattern = pattern;

    }
    public Matcher  getMatcher(String regex) {
        Matcher m = Pattern.compile(this.pattern).matcher(regex);
        if(m.matches()) {
            return m;
        }
        return null;
    }
}
