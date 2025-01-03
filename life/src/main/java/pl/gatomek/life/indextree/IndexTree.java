package pl.gatomek.life.indextree;

import pl.gatomek.life.Cell;

public class IndexTree {
    private RowFile rowFile;
    public double minX = Double.MAX_VALUE;
    public double maxX = Double.MIN_VALUE;
    public double minY = Double.MAX_VALUE;
    public double maxY = Double.MIN_VALUE;

    public void calcMinX( double x)
    {
        minX = Math.min( minX, x);
    }

    public void calcMaxX( double x)
    {
        maxX = Math.max( maxX, x);
    }

    public void calcMinY( double y)
    {
        minY = Math.min( minY, y);
    }

    public void calcMaxY( double y)
    {
        maxY = Math.max( maxY, y);
    }

    public void setRowFile( RowFile rowFile) {
        this.rowFile = rowFile;
    }

    public Cell select( double x, double y) {
        if( x < minX)
            return null;

        if( x > maxX)
            return null;

        if( y < minY)
            return null;

        if( y > maxY)
            return null;

        return rowFile.select( x, y);
    }
}
