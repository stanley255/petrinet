package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.utils.FileImporter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImportButton extends Button {

    public ImportButton(String label) throws HeadlessException {
        super(label);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                FileImporter fileImporter = new FileImporter();
                File loadedXML = fileImporter.loadFile();
            }
        });
    }
}
