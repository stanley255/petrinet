package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.utils.ImportNetListener;

import javax.swing.*;
import java.awt.*;

public class PetriNetFrame extends JFrame {

    public PetriNetFrame(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        Panel panel = new Panel();

        PetriNetCanvas petriNetCanvas = new PetriNetCanvas();
        petriNetCanvas.addMouseListener(petriNetCanvas);

        Button importButton = new Button("Import net");
        importButton.addActionListener(new ImportNetListener(petriNetCanvas));

        panel.add(importButton);
        this.add("North",panel);
        this.add("Center",petriNetCanvas);

        this.setVisible(true);
    }
}
