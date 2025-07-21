package com.stardew.models;

public class BackgroundColors {
    public static final String RESET = "\u001B[0m";

    public static final String BLACK   = "\u001B[40m";
    public static final String RED     = "\u001B[41m";
    public static final String GREEN   = "\u001B[42m";
    public static final String YELLOW  = "\u001B[43m";
    public static final String BLUE    = "\u001B[44m";
    public static final String PURPLE  = "\u001B[45m";
    public static final String CYAN    = "\u001B[46m";
    public static final String WHITE   = "\u001B[47m";

    public static final String BRIGHT_BLACK   = "\u001B[100m";
    public static final String BRIGHT_RED     = "\u001B[101m";
    public static final String BRIGHT_GREEN   = "\u001B[102m";
    public static final String BRIGHT_YELLOW  = "\u001B[103m";
    public static final String BRIGHT_BLUE    = "\u001B[104m";
    public static final String BRIGHT_PURPLE  = "\u001B[105m";
    public static final String BRIGHT_CYAN    = "\u001B[106m";
    public static final String BRIGHT_WHITE   = "\u001B[107m";
    public static final String BROWN = color256(130);


    public static String color256(int code) {
        return "\u001B[48;5;" + code + "m";
    }
}
