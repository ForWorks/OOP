package org.example.Model;

import javafx.scene.paint.Color;

public class Ellipse extends Shapes {
    public Ellipse(Color penColor, Color brushColor, int lineWeight) {
        this.penColor = penColor;
        this.brushColor = brushColor;
        this.lineWeight = lineWeight;
    }
    @Override
    public void draw(int startX, int startY, int endX, int endY) {

    }
}
