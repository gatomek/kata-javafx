package pl.gatomek.life.indextree;

import pl.gatomek.life.Cell;

public class Column {
    private Cell cell = null;
    protected double minX;
    protected double maxX;

    private Column(Cell cell, double minX, double maxX ) {
        this.cell = cell;
        this.minX = minX;
        this.maxX = maxX;
    }

    public static Column of(Cell cell, double minX, double maxX) {
        return new Column( cell, minX, maxX);
    }

    public Cell getCell(){
        return cell;
    }
}
