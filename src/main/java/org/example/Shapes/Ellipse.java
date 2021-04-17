package org.example.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Ellipse extends Shape {
    public Ellipse(List<Double> points, Color penColor, Color brushColor, double lineWeight) {
        this.brushColor = brushColor;
        this.penColor = penColor;
        this.lineWeight = lineWeight;
        this.points.addAll(points);
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWeight);
        gc.setStroke(penColor);
        gc.setFill(brushColor);
        gc.fillOval(Math.min(points.get(0), points.get(2)), Math.min(points.get(1), points.get(3)), Math.abs(points.get(0) - points.get(2)), Math.abs(points.get(1) - points.get(3)));
        gc.strokeOval(Math.min(points.get(0), points.get(2)), Math.min(points.get(1), points.get(3)), Math.abs(points.get(0) - points.get(2)), Math.abs(points.get(1) - points.get(3)));
    }
}
