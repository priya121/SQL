package db;

public class Relation {

    private final Tuple[] values;
    private final String name;
    private String[] columns;

    // create table simple (id int, name varchar(50))
    public Relation(String name, String[] columns, Tuple... values) {
        this.name = name;
        this.columns = columns;
        this.values = values;
    }

    public Tuple[] getTuples() {
        return values;
    }

    public String[] getColumns() {
        return columns;
    }
}
