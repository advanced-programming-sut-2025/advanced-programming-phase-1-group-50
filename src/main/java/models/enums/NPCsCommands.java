package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum NPCsCommands  implements Command {

    MeetNPC("^\\s*meet\\s+NPC\\s+(?<NPCname>.+?)\\s*$"),
    GiftToNPC("^\\s*gift\\s+NPC\\s+(?<NPCname>.+?)\\s+-i\\s+(?<item>.+?)\\s*$"),
    FriendShipNPCList("^\\s*friendship\\s+NPC\\s+list\\s*$"),
    QuestsList("^\\s*quests\\s+list\\s*$"),
    FinishingQuest("^\\s*quests\\s+finish\\s+-i\\s+(?<index>.+?)\\s*$");

    private final String pattern;
    NPCsCommands(String pattern) {
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
