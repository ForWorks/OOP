package org.example.FactoryShapes;

import org.example.MyColor;
import org.example.Shapes.Polyline;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryPolyline implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, MyColor penColor, MyColor brushColor, double lineWeight) {
        return new Polyline(points, penColor, lineWeight);
    }
}

