package pl.gatomek.life.indextree;

public class Row {
    private ColumnFile columnFile;
    protected double minY;
    protected double maxY;

    private Row(ColumnFile columnFile, double minY, double maxY) {
        this.columnFile = columnFile;
        this.minY = minY;
        this.maxY = maxY;
    }

    public static Row of(ColumnFile columnFile, double minY, double maxY) {
        return new Row(columnFile, minY, maxY);
    }

    public Column selectColumn( double x) {
        return columnFile.select( x);
    }
}
