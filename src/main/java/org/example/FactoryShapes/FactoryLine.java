package org.example.FactoryShapes;

import javafx.scene.paint.Color;
import org.example.Shapes.Line;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryLine implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, Color penColor, Color brushColor, double lineWeight) {
        return new Line(points, penColor, lineWeight);
    }
}
