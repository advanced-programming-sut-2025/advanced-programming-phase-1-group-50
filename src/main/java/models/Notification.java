package models;

public class Notification {
    private final String message;
    private boolean isChecked;

    public Notification(String message) {
        this.message = message;
        this.isChecked = false;
    }

    public String getMessage() {
        return message;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
