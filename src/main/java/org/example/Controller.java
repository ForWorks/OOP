package org.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.example.Shapes.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private MenuBar menu;

    @FXML
    private Canvas canvas;

    @FXML
    private Button undo;

    @FXML
    private Button redo;

    @FXML
    private TextField penWeight;

    @FXML
    private ColorPicker penColor;

    @FXML
    private ColorPicker brushColor;

    @FXML
    private Button cursor;

    @FXML
    private Button line;

    @FXML
    private Button ellipse;

    @FXML
    private Button rectangle;

    @FXML
    private Button polyline;

    @FXML
    private Button polygon;

    GraphicsContext gc;

    @FXML
    void initialize() {
        gc = canvas.getGraphicsContext2D();
        undo.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/undo.png"))));
        redo.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/redo.png"))));
        cursor.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/cursor.png"))));
        line.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/line.png"))));
        ellipse.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/ellipse.png"))));
        rectangle.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/rectangle.png"))));
        polyline.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/polyline.png"))));
        polygon.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/polygon.png"))));
        penWeight.setText("1");
    }

    private List<Shapes> shapes = new ArrayList();
    private List<Double> points = new ArrayList<>();
    private List<Shapes> intermediateShapes = new ArrayList<>();

    public void clearMouseEvents() {
        canvas.setOnMousePressed(event -> {});
        canvas.setOnMouseReleased(event -> {});
        canvas.setOnMouseDragged(event -> {});
        canvas.setOnMouseMoved(event -> {});
    }

    public void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void preparationForSimpleShapes() {
        clearMouseEvents();
        canvas.setOnMousePressed(event -> {
            points.add(0, event.getX());
            points.add(1, event.getY());
        });
    }

    public void preparationForComplicatedShapes() {
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
                shapes.add(intermediateShapes.get(0));
                points.clear();
            }
        });
    }

    public void conclusionForSimpleShapes() {
        canvas.setOnMouseReleased(event -> {
            shapes.add(intermediateShapes.get(0));
        });
    }

    public void intermediaryFoSimpleShapes(MouseEvent event) {
        points.add(2, event.getX());
        points.add(3, event.getY());
        clearCanvas();
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw(gc);
        }
        intermediateShapes.clear();
    }

    public void intermediaryForComplicatedShapes(MouseEvent event) {
        clearCanvas();
        for(Shapes shape :shapes) {
            shape.draw(gc);
        }
        points.set(points.size()-1,event.getY());
        points.set(points.size()-2,event.getX());
        intermediateShapes.clear();
    }

    public void selectedLine() {
        preparationForSimpleShapes();
        canvas.setOnMouseDragged(event -> {
            intermediaryFoSimpleShapes(event);
            intermediateShapes.add(new Line(points, penColor.getValue(), Double.parseDouble(penWeight.getText())));
            intermediateShapes.get(0).draw(gc);
        });
        conclusionForSimpleShapes();
    }

    public void selectedEllipse() {
        preparationForSimpleShapes();
        canvas.setOnMouseDragged(event -> {
            intermediaryFoSimpleShapes(event);
            intermediateShapes.add(new Ellipse(points, penColor.getValue(), brushColor.getValue(), Double.parseDouble(penWeight.getText())));
            intermediateShapes.get(0).draw(gc);
        });
        conclusionForSimpleShapes();
    }

    public void selectedRectangle() {
        preparationForSimpleShapes();
        canvas.setOnMouseDragged(event -> {
            intermediaryFoSimpleShapes(event);
            intermediateShapes.add(new Rectangle(points, penColor.getValue(), brushColor.getValue(), Double.parseDouble(penWeight.getText())));
            intermediateShapes.get(0).draw(gc);
        });
        conclusionForSimpleShapes();
    }

    public void selectedPolyline() {
        preparationForComplicatedShapes();
        canvas.setOnMouseMoved(event -> {
            if(points.size() > 0) {
                intermediaryForComplicatedShapes(event);
                intermediateShapes.add(new Polyline(points, penColor.getValue(), Double.parseDouble(penWeight.getText())));
                intermediateShapes.get(0).draw(gc);
            }
        });
    }

    public void selectedPolygon() {
        preparationForComplicatedShapes();
        canvas.setOnMouseMoved(event -> {
            if(points.size() > 0) {
                intermediaryForComplicatedShapes(event);
                intermediateShapes.add(new Polygon(points, penColor.getValue(), brushColor.getValue(), Double.parseDouble(penWeight.getText())));
                intermediateShapes.get(0).draw(gc);
            }
        });
    }

    public void selectedRedo() {
        clearMouseEvents();
    }

    public void selectedUndo() {
        clearMouseEvents();
    }

    public void selectedCursor() {
        clearMouseEvents();
    }
}

