package org.example.FactoryShapes;

import javafx.scene.paint.Color;
import org.example.Shapes.Polygon;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryPolygon implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, Color penColor, Color brushColor, double lineWeight) {
        return new Polygon(points, penColor, brushColor, lineWeight);
    }
}

