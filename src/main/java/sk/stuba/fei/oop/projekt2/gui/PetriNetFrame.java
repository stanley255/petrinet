package sk.stuba.fei.oop.projekt2.gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PetriNetFrame extends Frame {

    public PetriNetFrame(String title) throws HeadlessException {
        super(title);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        this.setSize(500, 500);
        this.setVisible(true);

        ImportButton importButton = new ImportButton("Import net");

        Panel panel = new Panel();
        panel.add(importButton);
        this.add("North",panel);
    }
}
