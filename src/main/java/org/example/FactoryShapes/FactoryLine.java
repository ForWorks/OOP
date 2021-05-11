package org.example.FactoryShapes;

import org.example.MyColor;
import org.example.Shapes.Line;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryLine implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, MyColor penColor, MyColor brushColor, double lineWeight) {
        return new Line(points, penColor, lineWeight);
    }
}
