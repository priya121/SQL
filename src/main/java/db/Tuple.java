package db;

public class Tuple {
    private final Object[] values;

    public Tuple(Object... values) {
        this.values = values;
    }

    public Object getValue(int index) {
        return values[index];
    }
}
