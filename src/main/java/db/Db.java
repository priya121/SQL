package db;

import java.io.*;
import java.util.*;

public class Db {

    private final ByteArrayInputStream in;
    private final ByteArrayOutputStream out;
    private Relation relation;

    public Db(ByteArrayInputStream in, ByteArrayOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void executeNextCommand() {
       byte[] input = new byte[1000];
       int current;
       int count = 0;
       while((current = in.read()) != -1) {
           input[count++] = (byte)current;
       }
       String command = new String(input, 0, count);

       try {
           out.write(displayFirstTable().getBytes());
       } catch(Exception ex) {
       }
    }

    private List<Relation> buildExample() {
        Relation simple = new Relation("simple",
                new String[] { "id", "name" },
                new Tuple(1, "Diedre"),
                new Tuple(2, "Barbara"),
                new Tuple(3, "Fred"),
                new Tuple(4, "Derek")
                );

        Relation birthdays = new Relation("birthdays",
                new String[] {"name", "birthday", "favourite_colour"},
                new Tuple("John", "12/01/1965", "Blue"),
                new Tuple("Fiona", "14/02/1980","Green")
                );

        List<Relation> relations = new ArrayList<>();
        relations.add(simple);
        relations.add(birthdays);
        return relations;
    }

    public String displayFirstTable() {
        List<Relation> relations = buildExample();
        return displayRelation(relations.get(0));
    }

    public String displayRelation(Relation relation) {
        String table = "";

        table += columnNames(relation);

        int numColumns = relation.getColumns().length;

        for (Tuple tuple : relation.getTuples()) {
            List<String> valuesList = new ArrayList<>();
            for (int i = 0; i < numColumns; i++) {
                valuesList.add(tuple.getValue(i).toString());
            }
            table += " " + String.join(" | ", valuesList) + "\n";
        }
        return table;
    }

    public String columnNames(Relation relation) {
        String header = String.join(" | ", relation.getColumns()) + "\n";
        String separator = String.join("-|-", toSeparators(relation.getColumns())) +"\n";
        return header + separator;
    }

    private List<String> toSeparators(String[] items) {

        List<String> allStrings = new ArrayList<>();
        String output;
        for(String item : items) {
            output = "";
            for(int i = 0; i < item.length(); i++) {
                output += '-';
            }
            allStrings.add(output);
        }

        return allStrings;
    }
}
