package com.stardew.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands implements Command {
    Exit("\\s*menu\\s+exit\\s*"),
    Register("\\s*register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s+-rp\\s+(?<passwordConfirm>\\S+)\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\s*"),
    ShowSecurityQuestions("pick\\s+question\\s+-q\\s+(?<questionNumber>\\d+)\\s+-a\\s+(?<answer>\\S+)\\s+(?<answerConfirm>\\S+)"),
    Login("\\s*login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s*(stay-logged-in)?"),
    ForgetPassword("\\s*forget\\s+password\\s+-u\\s+(?<password>\\S+)\\s*"),
    ShowMenu("\\s*show\\s+current\\s+menu\\s*");


    private final String pattern;

    LoginMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String regex) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(regex);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
