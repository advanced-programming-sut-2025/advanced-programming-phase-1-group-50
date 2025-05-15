    package models.Notification;

public class Notification {
    protected final String message;
    protected boolean isChecked;

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
