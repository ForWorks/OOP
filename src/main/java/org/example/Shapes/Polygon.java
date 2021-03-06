package org.example.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.example.MyColor;
import java.util.List;

public class Polygon extends Shape {

    public Polygon(List<Double> points, MyColor penColor, MyColor brushColor, double lineWeight) {
        this.penColor = penColor;
        this.brushColor = brushColor;
        this.lineWeight = lineWeight;
        this.points.addAll(points);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWeight);
        gc.setStroke(penColor.getFXColor());
        gc.setFill(brushColor.getFXColor());
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
