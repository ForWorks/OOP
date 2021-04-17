package org.example;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {

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

    private Model model;
    private UndoRedo undoRedo;

    @FXML
    void initialize() {
        model = new Model(canvas);
        undoRedo = new UndoRedo();
        model.setBrushColor(brushColor.getValue());
        model.setPenColor(penColor.getValue());
        model.setLineWeight(3);
        penWeight.setText("3");
        penWeight.setStyle("-fx-display-caret: false");
        undo.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/undo.png"))));
        redo.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/redo.png"))));
        cursor.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/cursor.png"))));
        line.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/line.png"))));
        ellipse.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/ellipse.png"))));
        rectangle.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/rectangle.png"))));
        polyline.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/polyline.png"))));
        polygon.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/polygon.png"))));

    }

    public void setBrushColor() {
        model.setBrushColor(brushColor.getValue());
    }

    public void setPenColor() {
        model.setPenColor(penColor.getValue());
    }

    public void setLineWeight() {
        try {
            double value = -1;
            if(!penWeight.getText().equals("")) {
                value = Double.parseDouble(penWeight.getText());
                if (value > 0)
                    model.setLineWeight(Double.parseDouble(penWeight.getText()));
                else {
                    model.setLineWeight(1);
                    penWeight.setText("1");
                }
            }
        }
        catch(Exception err ){
            model.setLineWeight(1);
            penWeight.setText("1");
        }
    }

    public void selectedLine() {
        model.drawSimpleShapes(0);
    }

    public void selectedEllipse() {
        model.drawSimpleShapes(1);
    }

    public void selectedRectangle() {
        model.drawSimpleShapes(2);
    }

    public void selectedPolyline() {
        model.drawComplicatedShapes(3);
    }

    public void selectedPolygon() {
        model.drawComplicatedShapes(4);
    }

    public void selectedClear() {
        model.clearCanvas();
        undoRedo.shapes.clear();
    }

    public void selectedUndo() {
        undoRedo.previousStep(model);
    }

    public void selectedRedo() {
        undoRedo.nextStep(model);
    }

    public void selectedCursor() {
        model.clearMouseEvents();
    }
}

