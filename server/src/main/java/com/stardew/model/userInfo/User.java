package com.stardew.model.userInfo;

import com.stardew.model.gameApp.SecurityQuestion;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private final Gender gender;
    private int numberOfGames = 0;
    private SecurityQuestion securityQuestion;
    private int highestScore;
    private final PasswordUtil passwordUtil = new PasswordUtil();

    public User(String username, String password, String nickname, String email, Gender gender , SecurityQuestion securityQuestion) {
        this.username = username;
        this.password = passwordUtil.hashPassword(password);
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.securityQuestion = securityQuestion;
    }

    public String getUsername() {
        return username;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public synchronized int getHighestScore() {
        return highestScore;
    }

    public synchronized void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }
    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }
    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
    public void setPassword(String password){
        this.password = passwordUtil.hashPassword(password);
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public void setEmail(String email){
        this.email = email;
    }

}
