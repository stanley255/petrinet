package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.exceptions.FailedNetConversion;
import sk.stuba.fei.oop.projekt2.utils.FileImporter;
import sk.stuba.fei.oop.projekt2.utils.PetriNetParser;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class PetriNetFrame extends Frame {

    public PetriNetFrame(String title) throws HeadlessException {
        super(title);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        this.setSize(700, 700); // TODO

        final PetriNetCanvas petriNetCanvas = new PetriNetCanvas();

        petriNetCanvas.addMouseListener(petriNetCanvas);

        Button importButton = new Button("Import net");
        importButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    File loadedXML = new FileImporter().loadFile();
                    System.out.println("File was loaded successfully!");
                    PetriNetParser petriNetParser = new PetriNetParser(loadedXML);
                    petriNetCanvas.setPetriNet(petriNetParser.getPetriNet());
                    petriNetCanvas.repaint();
                } catch(IllegalArgumentException e) {
                    System.out.println("File was not loaded!");
                } catch (JAXBException e) {
                    System.out.println("XML file could not be loaded!");
                } catch (FailedNetConversion e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        Panel panel = new Panel();
        panel.add(importButton);
        this.add("North",panel);
        this.add("Center",petriNetCanvas);

        this.setVisible(true);

    }
}
