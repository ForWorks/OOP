package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import org.example.FactoryShapes.*;
import org.example.Shapes.Shape;
import java.io.*;
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
    private MyColor brushColor;
    private MyColor penColor;
    private double lineWeight;
    private UndoRedo undoRedo = new UndoRedo();

    public Model(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void setBrushColor(Color color) {
        gc.setFill(color);
        brushColor = new MyColor(color);
    }

    public void setPenColor(Color color) {
        gc.setStroke(color);
        penColor = new MyColor(color);
    }

    public void setLineWeight(double size) {
        gc.setLineWidth(size);
        lineWeight = size;
    }

    public void clearCanvas() {
        gc.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    public void paint() {
        clearCanvas();
        for(Shape shape : undoRedo.shapes) {
            shape.draw(gc);
        }
    }

    public void drawShapes(int factoryIndex){
        points.clear();
        paint();
        canvas.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                points.add(event.getX());
                points.add(event.getY());
                points.add(event.getX());
                points.add(event.getY());
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if(factoryIndex == 3 || factoryIndex == 4) {
                    points.set(points.size() - 1, event.getY());
                    points.set(points.size() - 2, event.getX());
                }
                else {
                    points.set(2, event.getX());
                    points.set(3, event.getY());
                }
                undoRedo.shapes.add(intermediateShapes.get(0));
                UndoRedo.elements.clear();
                points.clear();
            }
        });
        canvas.setOnMouseMoved(event -> {
            if(points.size() > 0) {
                paint();
                if(factoryIndex == 3 || factoryIndex == 4) {
                    points.set(points.size() - 1, event.getY());
                    points.set(points.size() - 2, event.getX());
                }
                else {
                    points.set(2, event.getX());
                    points.set(3, event.getY());
                }
                intermediateShapes.clear();
                FactoryShape factoryShape = FactoryShapesList.get(factoryIndex);
                intermediateShapes.add(factoryShape.createShape(points, penColor, brushColor, lineWeight));
                intermediateShapes.get(0).draw(gc);
            }
        });
    }

    public void serialize(File file){

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oos.writeInt(undoRedo.shapes.size());
            for (Shape shape : UndoRedo.shapes) {
                oos.writeObject(shape);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void deserialize(File file){

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            int length = ois.readInt();
            for(int i = 0; i < length; i++) {
                undoRedo.shapes.add((Shape) ois.readObject());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void drawFull() {
        undoRedo.drawFull(gc);
    }

}


