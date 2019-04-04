package sk.stuba.fei.oop.projekt2.utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileImporter {

    public File loadFile() throws IllegalArgumentException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("xml files (*.xml)", "xml"));
        int returnValue = fileChooser.showOpenDialog(null);
        if ((returnValue == JFileChooser.APPROVE_OPTION)) {
            return fileChooser.getSelectedFile();
        }
        throw new IllegalArgumentException("File was not loaded!");
    }

}
