package org.example.Model;

import javafx.scene.paint.Color;

public class Line extends Shapes {
    public Line(Color penColor, int lineWeight) {
        this.penColor = penColor;
        this.lineWeight = lineWeight;
    }
    @Override
    public void draw(int startX, int startY, int endX, int endY) {

    }
}
