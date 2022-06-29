package coordinates;

import java.util.Objects;

public class Column {
    private final int value;

    public Column(int value) {
        if (value > 10 || value < 1) {
            throw new RuntimeException("Error! Wrong ship location! Try again:");
        } else
            this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return value == column.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
