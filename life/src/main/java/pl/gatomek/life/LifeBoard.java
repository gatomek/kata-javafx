package pl.gatomek.life;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.HashMap;
import java.util.Map;

public class LifeBoard {

    public LifeBoard( int rs, int cs) {
        rows.setValue( rs);
        cols.setValue( cs);
        cells = new Cell[ rows.getValue()][cols.getValue()];

        for( int r = 0; r < rows.getValue(); r ++)
            for( int c = 0; c < cols.getValue(); c ++) {
                Cell cell = new Cell();
                cells[r][c] = cell;
                cellToPositionIndex.put( cell, new Position( r,c));
            }
    }

    protected IntegerProperty rows = new SimpleIntegerProperty();
    protected IntegerProperty cols = new SimpleIntegerProperty();

    protected Cell[][] cells = null;
    private Map<Cell, Position> cellToPositionIndex = new HashMap<>();

    public Cell at( int row, int col) {
        return cells[row][col];
    }

    public void calcAliveNeighbours() {
        for( int r = 0; r < rows.getValue(); r ++)
            for( int c = 0; c < cols.getValue(); c ++)
                cells[r][c].aliveNeighbourCount = calcCellAliveNeighbourCount( r, c);
    }

    public void updateNeighbours(Cell cell) {
        Position pos = cellToPositionIndex.get( cell);
        int row = pos.row();
        int col = pos.col();

        for( int i = -1; i < 2; i ++) {
            for (int j = -1; j < 2; j++) {
                if( i == 0 && j == 0)
                    continue;

                int c = col + i;
                int r = row + j;
                if( c >= 0 && c < cols.getValue()) {
                    if (r >= 0 && r < rows.getValue()) {
                        Cell nc = cells[r][c];
                        if( cell.alive)
                            nc.aliveNeighbourCount ++;
                        else
                            nc.aliveNeighbourCount --;

                        assert nc.aliveNeighbourCount >= 0;
                        assert nc.aliveNeighbourCount < 9;
                    }
                }
            }
        }
    }

    private int calcCellAliveNeighbourCount(int row, int col) {
        int aliveNeighbourCount = 0;

        for( int i = -1; i < 2; i ++) {
            for (int j = -1; j < 2; j++) {
                if( i == 0 && j == 0)
                    continue;

                int c = col + i;
                int r = row + j;
                if( c >= 0 && c < cols.getValue()) {
                    if (r >= 0 && r < rows.getValue()) {
                        if( cells[r][c].alive)
                            aliveNeighbourCount ++;
                    }
                }
            }
        }

        return aliveNeighbourCount;
    }

    public int calcAliveCells() {
        int aliveCells = 0;
        for( int r = 0; r < rows.getValue(); r ++)
            for( int c = 0; c < cols.getValue(); c ++) {
                Cell cell = cells[r][c];
                cell.alive = isCellAlive( cell);
                if( cell.alive)
                    ++ aliveCells;
            }

        return aliveCells;
    }

    private boolean isCellAlive( Cell cell) {
        if( ! cell.alive && cell.aliveNeighbourCount == 3)
            return true;

        if( cell.alive && (cell.aliveNeighbourCount == 2 || cell.aliveNeighbourCount == 3))
            return true;

        return false;
    }

    public void clear() {
        for( int r = 0; r < rows.getValue(); r ++)
            for( int c = 0; c < cols.getValue(); c ++) {
                Cell cell = cells[r][c];
                cell.alive = false;
                cell.aliveNeighbourCount = 0;
            }
    }

    record Position( int row, int col){};
}


