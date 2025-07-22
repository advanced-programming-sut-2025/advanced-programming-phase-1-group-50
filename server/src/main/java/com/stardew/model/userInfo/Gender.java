package com.stardew.model.userInfo;

public enum Gender {
    Male("male"),
    Female("female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
}
