package pl.gatomek.life.indextree;

import pl.gatomek.life.Cell;

import java.util.LinkedList;
import java.util.List;

public class RowFile {
    public final List<Row> rows = new LinkedList<>();

    public void add(ColumnFile columnFile, Double minY, Double maxY) {
        rows.add( Row.of(columnFile, minY, maxY));
    }

    private Row selectRow( double y) {
        for( Row r : rows)
            if( y > r.minY && y < r.maxY)
                return r;

        return null;
    }

    public Cell select(double x, double y) {
        Row r = selectRow( y);
        if( r != null){
            Column c = r.selectColumn( x);
            if( c != null)
                return c.getCell();
        }

        return null;
    }
}
