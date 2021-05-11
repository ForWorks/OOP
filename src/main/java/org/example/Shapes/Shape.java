package org.example.Shapes;

import javafx.scene.canvas.GraphicsContext;
import org.example.MyColor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shape implements Serializable {

    protected double lineWeight;
    public MyColor brushColor;
    public MyColor penColor;
    protected List<Double> points = new ArrayList<>();

    public  void draw(GraphicsContext gc) {

    }

}
