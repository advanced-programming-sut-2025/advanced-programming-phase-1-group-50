package com.stardew.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StoreMenuCommands implements Command {

    ShowAllProducts("^\\s*show\\s+all\\s+products\\s*$"),
    ShowAvailableProducts("^\\s*show\\s+all\\s+available\\s+products\\s*$"),
    PurchaseProduct("^\\s*purchase\\s+(?<productName>.+?)(?:\\s+-n\\s+(?<count>\\d+))?\\s*$"),
    ShowCurrentMenu("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit("^\\s*menu\\s+exit\\s*$");

    private final String pattern;

    StoreMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
