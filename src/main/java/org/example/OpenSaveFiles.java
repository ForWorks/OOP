package org.example;

import javafx.stage.FileChooser;
import java.io.File;

public class OpenSaveFiles {

    public File openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("binary file (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            return  file;
        }
        return  null;

    }

    public File saveFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save image");
        fileChooser.setInitialFileName("image");
        FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("binary file (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(extension);
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            return  file;
        }
        return  null;
    }

}
