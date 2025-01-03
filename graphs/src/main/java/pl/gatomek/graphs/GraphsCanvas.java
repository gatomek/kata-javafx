package pl.gatomek.graphs;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphsCanvas extends Canvas {

    private final double minX = -5;
    private final double maxX = 15;
    private final double minY = -5;
    private final double maxY = 15;

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
        paint();
    }

    public void paint() {
        clear();

        drawAxisX();
        drawAxisY();

        drawPoint(GraphPoint.of(-4., -2.));
        drawPoint(GraphPoint.of(-2., -1.), Color.BLUE);
        drawPoint(GraphPoint.of(0., 0.), Color.RED);
        drawPoint(GraphPoint.of(1., 2.), Color.BROWN);
        drawPoint(GraphPoint.of(2., 4.), Color.ORANGE);
        drawPoint(GraphPoint.of(3., 8.), Color.GREEN);
    }

    private void clear() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0., 0., getWidth(), getHeight());
    }

    private void drawPoint(GraphPoint gp) {
        drawPoint( gp, Color.BLACK);
    }

    private void drawPoint(GraphPoint gp, Color color) {
        DrawPoint dp = translate(gp);
        double radius = 3.;
        double diameter = 2. * radius;

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(color);
        gc.fillOval(dp.x() - radius, dp.y() - radius, diameter, diameter);
    }

    private DrawPoint translate(GraphPoint gp) {
        // x
        double distanceX = distance( maxX, minX);
        double pointDistanceX = distance( gp.x(), minX);
        double x = getWidth() * pointDistanceX / distanceX;

        // y
        double distanceY = distance( maxY, minY);
        double pointDistanceY = distance( maxY, gp.y());
        double y = getHeight() * pointDistanceY / distanceY;

        return DrawPoint.of(x, y);
    }

    private double distance( double x, double y) {
        return Math.abs( x - y);
    }

    private void drawAxisY() {
        DrawPoint min = translate(GraphPoint.of(0, minY));
        DrawPoint max = translate(GraphPoint.of(0, maxY));

        drawAxis( min, max);
    }

    private void drawAxisX() {
        DrawPoint min = translate(GraphPoint.of(minX, 0));
        DrawPoint max = translate(GraphPoint.of(maxX, 0));

        drawAxis( min, max);
    }

    private void drawAxis( DrawPoint min, DrawPoint max) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeLine(min.x(), min.y(), max.x(), max.y());
    }
}
