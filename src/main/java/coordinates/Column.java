package coordinates;

public class Column {
    private final int value;

    public Column(int value) {
        if (value > 10 || value < 1) {
            throw new RuntimeException("Error! Wrong ship location! Try again:");
        } else
            this.value = value - 1;
    }

    public int getValue() {
        return value;
    }
}
