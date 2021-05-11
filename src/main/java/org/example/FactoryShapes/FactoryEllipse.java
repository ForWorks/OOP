package org.example.FactoryShapes;

import org.example.MyColor;
import org.example.Shapes.Ellipse;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryEllipse implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, MyColor penColor, MyColor brushColor, double lineWeight) {
        return new Ellipse(points, penColor, brushColor, lineWeight);
    }
}
