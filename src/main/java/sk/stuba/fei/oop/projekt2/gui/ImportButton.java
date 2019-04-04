package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.utils.FileImporter;
import sk.stuba.fei.oop.projekt2.utils.PetriNetParser;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class ImportButton extends Button {

    ImportButton(String label) throws HeadlessException {
        super(label);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    File loadedXML = new FileImporter().loadFile();
                    System.out.println("File was loaded successfully!");
                    PetriNetParser petriNetParser = new PetriNetParser(loadedXML);
                } catch(IllegalArgumentException e) {
                    System.out.println("File was not loaded!");
                } catch (JAXBException e) {
                    System.out.println("XML file could not be loaded!");
                }
            }
        });
    }
}
