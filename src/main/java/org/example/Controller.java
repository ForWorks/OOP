package org.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;

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
    private OpenSaveFiles openSaveFiles;

    @FXML
    void initialize() {
        model = new Model(canvas);
        undoRedo = new UndoRedo();
        openSaveFiles = new OpenSaveFiles();
        model.setBrushColor(brushColor.getValue());
        model.setPenColor(penColor.getValue());
        model.setLineWeight(1);
        penWeight.setText("1");
        penWeight.setStyle("-fx-display-caret: false");
        undo.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/undo.png"))));
        redo.setGraphic(new ImageView(new Image(App.class.getResourceAsStream("icons/redo.png"))));
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
            double value = 0;
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
        catch(Exception e) {
            model.setLineWeight(1);
            penWeight.setText("1");
        }
    }

    public void selectedLine() {
        model.drawShapes(0);
    }

    public void selectedEllipse() {
        model.drawShapes(1);
    }

    public void selectedRectangle() {
        model.drawShapes(2);
    }

    public void selectedPolyline() {
        model.drawShapes(3);
    }

    public void selectedPolygon() {
        model.drawShapes(4);
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

    public void openImage() {
        File file = openSaveFiles.openFile();
        model.clearCanvas();
        undoRedo.shapes.clear();
        undoRedo.elements.clear();
        model.deserialize(file);
        model.drawFull();
    }

    public void saveImage() {
        File file = openSaveFiles.saveFile();
        model.serialize(file);
    }

}

