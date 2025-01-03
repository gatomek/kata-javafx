package pl.gatomek.life;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pl.gatomek.life.indextree.ColumnFile;
import pl.gatomek.life.indextree.IndexTree;
import pl.gatomek.life.indextree.RowFile;

public class LifeCanvas extends Canvas {

    private final LifeBoard lifeBoard;
    protected IntegerProperty rows = new SimpleIntegerProperty();
    protected IntegerProperty cols = new SimpleIntegerProperty();
    protected BooleanProperty diagnView = new SimpleBooleanProperty(false);

    private double margin = 10.;
    private IndexTree indexTree = null;

    public LifeCanvas(LifeBoard lifeBoard) {
        this.lifeBoard = lifeBoard;
    }

    @Override
    public double minHeight(double width) {
        return 0;
    }

    @Override
    public double maxHeight(double width) {
        return 10000;
    }

    @Override
    public double prefHeight(double width) {
        return maxHeight(width);
    }

    @Override
    public double minWidth(double height) {
        return 0;
    }

    @Override
    public double maxWidth(double height) {
        return 10000;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        super.setWidth(width);
        super.setHeight(height);
        draw();
    }

    public void draw() {

        double width = getWidth() - 2. * margin;
        double height = getHeight() - 2. * margin;
        double xSize = width / cols.getValue();
        double ySize = height / rows.getValue();
        double size = Math.min(xSize, ySize);
        double xBoardSize = size * cols.getValue();
        double yBoardSize = size * rows.getValue();
        double xOffset = (width - xBoardSize) / 2.;
        double yOffset = (height - yBoardSize) / 2.;

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0., 0., getWidth(), getHeight());

        indexTree = new IndexTree();

        RowFile rowFile = new RowFile();
        for (int r = 0; r < rows.getValue(); ++r) {
            double minY = r * size + margin + yOffset;
            double maxY = minY + size;
            indexTree.calcMinY(minY);
            indexTree.calcMaxY(maxY);

            ColumnFile columnFile = new ColumnFile();
            for (int c = 0; c < cols.getValue(); c++) {
                double minX = c * size + margin + xOffset;
                double maxX = minX + size;
                indexTree.calcMinX(minX);
                indexTree.calcMaxX(maxX);

                Cell cell = lifeBoard.at(r, c);
                gc.setFill(cell.alive ? Color.GRAY : Color.GAINSBORO);
                gc.fillRect(minX + 1, minY + 1, size - 2, size - 2);
                columnFile.add(cell, minX, maxX);

                if (Boolean.TRUE.equals( diagnView.get())) {
                    gc.setFill(Color.BLUE);
                    gc.fillText(String.valueOf(cell.aliveNeighbourCount), (minX + maxX) / 2., (minY + maxY) / 2.);
                }
            }

            rowFile.add(columnFile, minY, maxY);
        }

        indexTree.setRowFile(rowFile);

        if (Boolean.TRUE.equals( diagnView.get())) {
            gc.setStroke(Color.BLUE);
            gc.strokeRect(indexTree.minX, indexTree.minY, indexTree.maxX - indexTree.minX, indexTree.maxY - indexTree.minY);
        }
    }

    public Cell findCell(double x, double y) {
        return indexTree.select(x, y);
    }
}
