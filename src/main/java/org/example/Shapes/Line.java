package org.example.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Line extends Shape {
    public Line(List<Double> points, Color penColor, double lineWeight) {
        this.penColor = penColor;
        this.lineWeight = lineWeight;
        this.points.addAll(points);
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWeight);
        gc.setStroke(penColor);
        gc.strokeLine(points.get(0), points.get(1), points.get(2), points.get(3));
    }
}
