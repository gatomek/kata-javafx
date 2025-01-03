package pl.gatomek.life.indextree;

import java.util.LinkedList;
import java.util.List;
import pl.gatomek.life.Cell;

public class ColumnFile {
    private List<Column> cols = new LinkedList<>();

    public void add( Cell cell, double minX, double maxX) {
        cols.add( Column.of( cell, minX, maxX));
    }

    public Column select( double x) {
        for( Column c : cols)
            if( x > c.minX && x < c.maxX)
                return c;

        return null;
    }
}
