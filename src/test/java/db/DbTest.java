package db;

import java.io.*;
import org.junit.*;

public class DbTest {

    @Test
    public void outputsSimpleTable() {

        String command = "SELECT * FROM simple;";

        String expected = "id | name\n" +
                          "---|-----\n" +
                          " 1 | Diedre\n" +
                          " 2 | Barbara\n" +
                          " 3 | Fred\n" +
                          " 4 | Derek\n";

        ByteArrayInputStream in = new ByteArrayInputStream(command.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Db(in, out).executeNextCommand();

        Assert.assertEquals(expected, out.toString());
    }
}
