package org.example.FactoryShapes;

import javafx.scene.paint.Color;
import org.example.Shapes.Polyline;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryPolyline implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, Color penColor, Color brushColor, double lineWeight) {
        return new Polyline(points, penColor, lineWeight);
    }
}

