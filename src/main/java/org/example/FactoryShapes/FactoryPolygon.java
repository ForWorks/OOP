package org.example.FactoryShapes;

import org.example.MyColor;
import org.example.Shapes.Polygon;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryPolygon implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, MyColor penColor, MyColor brushColor, double lineWeight) {
        return new Polygon(points, penColor, brushColor, lineWeight);
    }
}

