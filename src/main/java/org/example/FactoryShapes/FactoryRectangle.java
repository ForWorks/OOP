package org.example.FactoryShapes;

import org.example.MyColor;
import org.example.Shapes.Rectangle;
import org.example.Shapes.Shape;
import java.util.List;

public class FactoryRectangle implements FactoryShape {
    @Override
    public Shape createShape(List<Double> points, MyColor penColor, MyColor brushColor, double lineWeight) {
        return new Rectangle(points, penColor, brushColor, lineWeight);
    }
}

