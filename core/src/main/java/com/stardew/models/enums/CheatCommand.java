package com.stardew.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheatCommand implements Command {
    AdvancedTimeCheatCode("\\s*advanced\\s+time\\s+(?<advancedTime>-?\\d+)\\s*"),
    AdvancedDateCheatCode("\\s*advanced\\s+date\\s+(?<advancedDate>-?\\d+)\\s*"),
    CheatWeatherSetCode("\\s*cheat\\s+weather\\s+set\\s+(?<weather>\\S+)\\s*"),
    CheatThunder("\\s*cheat\\s+thor\\s+-l\\s+<(?<thunderX>-?\\d+)\\s*,\\s*(?<thunderY>-?\\d+)>\\s*"),
    CheatSetEnergy("\\s*energy\\s+set\\s+-v\\s+(?<energy>-?\\d+)\\s*"),
    CheatUnlimitedEnergy("\\s*energy\\s+unlimited\\s*"),
    CheatAddDollars("\\s*cheat\\s+add\\s+(?<amount>-?\\d+)\\s+dollars\\s*"),
    CheatAddItem("cheat\\s+add\\s+item\\s+-n\\s+(?<itemName>.+?)\\s+-c\\s+(?<count>-?\\d+)\\s*"),
    CheatSetFriendship("cheat\\s+set\\s+friendship\\s+-n\\s+(?<animalName>.+?)\\s+-c\\s+(?<amount>-?\\d+)\\s*"),
    NextTurn("\\s*next\\s+turn\\s*");


    private final String command;

    CheatCommand(String command) {
        this.command = command;
    }


    @Override
    public Matcher getMatcher(String regex) {
        Matcher matcher = Pattern.compile(this.command).matcher(regex);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
