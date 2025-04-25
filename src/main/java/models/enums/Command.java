package models.enums;

import java.util.regex.Matcher;

public interface Command {
    abstract Matcher getMatcher(String regex);
}
