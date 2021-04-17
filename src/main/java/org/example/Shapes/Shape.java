package org.example.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Shape {
    protected double lineWeight;
    protected Color brushColor;
    protected Color penColor;
    protected List<Double> points = new ArrayList<>();
    public  void draw(GraphicsContext gc) {

    };
}
