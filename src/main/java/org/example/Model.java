package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import org.example.FactoryShapes.*;
import org.example.Shapes.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {

    private Canvas canvas;
    public static GraphicsContext gc;
    private List<Double> points = new ArrayList<>();
    private List<Shape> intermediateShapes = new ArrayList<>();
    private List<FactoryShape> FactoryShapesList = Arrays.asList(new FactoryLine(), new FactoryEllipse(),
                                                                       new FactoryRectangle(), new FactoryPolyline(),
                                                                       new FactoryPolygon());
    private Color brushColor;
    private Color penColor;
    private double lineWeight;
    private UndoRedo undoRedo = new UndoRedo();

    public Model(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void setBrushColor(Color color) {
        gc.setFill(color);
        brushColor = color;
    }

    public void setPenColor(Color color) {
        gc.setStroke(color);
        penColor = color;
    }

    public void setLineWeight(double size) {
        gc.setLineWidth(size);
        lineWeight = size;
    }

    public void clearMouseEvents() {
        canvas.setOnMousePressed(event -> {});
        canvas.setOnMouseReleased(event -> {});
        canvas.setOnMouseDragged(event -> {});
        canvas.setOnMouseMoved(event -> {});
    }

    public void clearCanvas() {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawSimpleShapes(int factoryIndex){
        clearMouseEvents();
        canvas.setOnMousePressed(event -> {
            points.clear();
            points.add(event.getX());
            points.add(event.getY());
        });
        canvas.setOnMouseDragged(event -> {
            points.add(2, event.getX());
            points.add(3, event.getY());
            clearCanvas();
            for (Shape shape : undoRedo.shapes) {
                shape.draw(gc);
            }
            intermediateShapes.clear();
            FactoryShape factoryShape = FactoryShapesList.get(factoryIndex);
            intermediateShapes.add(factoryShape.createShape(points, penColor, brushColor, lineWeight));
            intermediateShapes.get(0).draw(gc);
        });
        canvas.setOnMouseReleased(event -> {
            undoRedo.shapes.add(intermediateShapes.get(0));
            UndoRedo.elements.clear();
        });
    }

    public void drawComplicatedShapes(int factoryIndex){
        clearMouseEvents();
        points.clear();
        canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                points.add(event.getX());
                points.add(event.getY());
                points.add(event.getX());
                points.add(event.getY());
            } else if (event.getButton() == MouseButton.SECONDARY) {
                points.set(points.size() - 1, event.getY());
                points.set(points.size() - 2, event.getX());
                undoRedo.shapes.add(intermediateShapes.get(0));
                UndoRedo.elements.clear();
                points.clear();
            }
        });
        canvas.setOnMouseMoved(event -> {
            if(points.size() > 0) {
                clearCanvas();
                for(Shape shape : undoRedo.shapes) {
                    shape.draw(gc);
                }
                points.set(points.size() - 1,event.getY());
                points.set(points.size() - 2,event.getX());
                intermediateShapes.clear();
                FactoryShape factoryShape = FactoryShapesList.get(factoryIndex);
                intermediateShapes.add(factoryShape.createShape(points, penColor, brushColor, lineWeight));
                intermediateShapes.get(0).draw(gc);
            }
        });
    }
}


