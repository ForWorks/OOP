package org.example.FactoryShapes;

import javafx.scene.paint.Color;
import org.example.Shapes.Rectangle;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryRectangle implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, Color penColor, Color brushColor, double lineWeight) {
        return new Rectangle(points, penColor, brushColor, lineWeight);
    }
}

