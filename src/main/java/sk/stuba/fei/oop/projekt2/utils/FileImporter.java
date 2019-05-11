package sk.stuba.fei.oop.projekt2.utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileImporter {

    public File loadFile() throws FileLoadException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("xml files (*.xml)", "xml"));
        int returnValue = fileChooser.showOpenDialog(null);
        if ((returnValue == JFileChooser.APPROVE_OPTION)) {
            return appendSuffixToFile(fileChooser.getSelectedFile(),".xml");
        }
        throw new FileLoadException();
    }

    // Function that appends suffix to file if it is necessary
    private File appendSuffixToFile(File file, String suffix) {
        if (!file.getAbsolutePath().endsWith(suffix)) {
            return new File(file.getAbsolutePath()+suffix);
        }
        return file;
    }

}
