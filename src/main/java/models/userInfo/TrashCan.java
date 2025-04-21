package models.userInfo;

public class TrashCan {
    enum Type {
        Primary(0),
        Copper(15),
        Iron(30),
        Gold(45),
        Iridium(60);

        private final int returnValuePercentage;
        Type(int returnValuePercentage) {
            this.returnValuePercentage = returnValuePercentage;
        }
    }

    private Type type = Type.Primary;

    public int getReturnValuePercentage() {
        return this.type.returnValuePercentage;
    }

    public void changeType() {

    }
}
