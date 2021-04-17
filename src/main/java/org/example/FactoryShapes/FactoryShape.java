package org.example.FactoryShapes;

import javafx.scene.paint.Color;
import org.example.Shapes.Shape;
import java.util.List;

public interface FactoryShape {
    Shape createShape(List<Double> points, Color penColor, Color brushColor, double lineWeight);
}

