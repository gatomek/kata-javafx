package pl.gatomek.life;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ChessboardCanvas extends Canvas {
    private static final double MARGIN = 10.;

    @Override
    public double minHeight(double width)
    {
        return 64;
    }

    @Override
    public double maxHeight(double width)
    {
        return 10000;
    }

    @Override
    public double prefHeight(double width)
    {
        return maxHeight(width);
    }

    @Override
    public double minWidth(double height)
    {
        return 0;
    }

    @Override
    public double maxWidth(double height)
    {
        return 10000;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height)
    {
        super.setWidth(width);
        super.setHeight(height);
        draw();
    }

    public void draw() {
        double width = getWidth() - 2. * MARGIN;
        double height = getHeight() - 2. * MARGIN;
        double effectiveSize = Math.min( width, height);
        double size = effectiveSize / 8.;
        double xOffset = (width - effectiveSize) / 2.;
        double yOffset = (height - effectiveSize) / 2.;

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill( Color.LIGHTGRAY);
        gc.clearRect(0., 0., getWidth(), getHeight());
        for (int r = 0; r < 8; ++r) {
            double y = r * size + yOffset + MARGIN;
            for (int c = 0; c < 8; ++c) {
                if( (r + c) % 2 == 0) {
                    double x = c * size + xOffset + MARGIN;
                    gc.fillRect(x, y, size, size);
                }
            }
        }

        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeRect(xOffset + MARGIN, yOffset + MARGIN, effectiveSize, effectiveSize);
    }
}
