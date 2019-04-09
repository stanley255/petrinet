package sk.stuba.fei.oop.projekt2.utils;

import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.FailedNetConversion;

import javax.xml.bind.JAXBException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImportNetListener implements ActionListener {

    private PetriNetCanvas canvas;

    public ImportNetListener(PetriNetCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File loadedXML = new FileImporter().loadFile();
            System.out.println("File was loaded successfully!");
            PetriNetParser petriNetParser = new PetriNetParser(loadedXML);
            canvas.setPetriNet(petriNetParser.getPetriNet());
            canvas.repaint();
        } catch(IllegalArgumentException exception) {
            System.out.println("File was not loaded!");
        } catch (JAXBException exception) {
            System.out.println("XML file could not be loaded!");
        } catch (FailedNetConversion exception) {
            System.out.println(exception.getMessage());
        }
    }
}
