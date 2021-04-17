package org.example;

import org.example.Shapes.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndoRedo {

    public static Stack<Shape> elements = new Stack();
    public static List<Shape> shapes = new ArrayList<>();

    public void previousStep(Model model) {
        if (shapes.size() > 0) {
            elements.push(shapes.get(shapes.size() - 1));
            shapes.remove(shapes.size() - 1);
            model.clearCanvas();
            for (Shape shape : shapes) {
                shape.draw(model.gc);
            }
        }
    }

    public void nextStep(Model model) {
        if (elements.size() > 0){
            shapes.add(elements.pop());
            model.clearCanvas();
            for (Shape shape : shapes) {
                shape.draw(model.gc);
            }
        }
    }
}
