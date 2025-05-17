package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands implements Command {

    trade("^\\s*trade\\s+-u\\s+(?<username>.+?)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+(?<amount>\\d+)\\s+-p\\s+(?<price>\\d+)\\s*$"),
    tradeList("^\\s*trade\\s+list\\s*$"),
    tradeResponse("^\\s*trade\\s+response\\s+-(?<state>accept|reject)\\s+-i\\s+(?<id>\\d+)\\s*$"),
    tradeHistory("\\s*trade\\s+history\\s*"),
    ShowCurrentMenu("^\\s*show\\s+current\\s+menu\\s*$"),
    Exit("^\\s*exit\\s*$");

    private final String pattern;

    TradeMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }

}
