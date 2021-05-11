package org.example.FactoryShapes;

import org.example.MyColor;
import org.example.Shapes.Shape;
import java.util.List;

public interface FactoryShape  {
    Shape createShape(List<Double> points, MyColor penColor, MyColor brushColor, double lineWeight);
}

