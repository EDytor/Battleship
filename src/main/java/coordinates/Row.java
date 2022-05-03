package coordinates;

public enum Row {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),
    I(9),
    J(10);
    private final int value;

    Row(int value) {
        this.value = value;
    }

    public int getValue() {
        return value - 1;
    }
}


