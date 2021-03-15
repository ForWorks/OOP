package org.example.Model;

import javafx.scene.paint.Color;

public class Rectangle extends Shapes {
    public Rectangle(Color penColor, Color brushColor, int lineWeight) {
        this.penColor = penColor;
        this.brushColor = brushColor;
        this.lineWeight = lineWeight;
    }
    @Override
    public void draw(int startX, int startY, int endX, int endY) {

    }
}
