package org.example.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.example.MyColor;
import java.util.List;

public class Polyline extends Shape {

    public Polyline(List<Double> points, MyColor penColor, double lineWeight) {
        this.penColor = penColor;
        this.lineWeight = lineWeight;
        this.points.addAll(points);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(lineWeight);
        gc.setStroke(penColor.getFXColor());
        for(int i = 0; i < points.size(); i += 4) {
            gc.strokeLine(points.get(i), points.get(i + 1), points.get(i + 2), points.get(i + 3));
        }

    }

}
