public enum WardRobeElement {
    Fifty(50, 59), SeventyFive(75, 62), OneHundred(100, 90), OneHundredTwenty(120, 111);

    public final int size;
    public final int value;

    WardRobeElement(int size, int value) {
        this.size = size;
        this.value = value;
    }
}
