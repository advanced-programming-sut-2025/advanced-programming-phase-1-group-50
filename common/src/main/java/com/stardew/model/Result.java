package com.stardew.model;

public class Result {
    private boolean isSuccessful;
    private String message;

    public Result() {}

    public Result(boolean isSuccessful, String message) {
        this.message = message;
        this.isSuccessful = isSuccessful;
    }

    public boolean getSuccessful() {
        return isSuccessful;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
