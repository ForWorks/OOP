package org.example.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;

public class Polygon extends Shapes {
    public Polygon(List<Double> points, Color penColor, Color brushColor, double lineWeight) {
        this.penColor = penColor;
        this.brushColor = brushColor;
        this.lineWeight = lineWeight;
        this.points.addAll(points);
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWeight);
        gc.setStroke(penColor);
        gc.setFill(brushColor);
        double[] pointsX = new double[points.size() / 2];
        double[] pointsY = new double[points.size() / 2];
        for(int i = 0; i < points.size(); i += 2) {
            pointsX[i / 2] = points.get(i);
            pointsY[i / 2] = points.get(i + 1);
        }
        gc.fillPolygon(pointsX, pointsY, points.size()/ 2);
        gc.strokePolygon(pointsX, pointsY, points.size() / 2);
    }
}
