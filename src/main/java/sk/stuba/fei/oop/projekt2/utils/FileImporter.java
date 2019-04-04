package sk.stuba.fei.oop.projekt2.utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileImporter {

    public File loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("xml files (*.xml)", "xml"));
        int returnValue = fileChooser.showOpenDialog(null);
        return (returnValue == JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile() : null;
    }

}
