package models;

public class Result {
    private final boolean isSuccessful;
    private final String message;

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
