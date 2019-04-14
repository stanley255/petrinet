package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.utils.ImportNetListener;

import javax.swing.*;
import java.awt.*;

public class PetriNetFrame extends JFrame {

    public PetriNetFrame(String title) throws HeadlessException {
        super(title);
        // Adding close window functionality
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Added to minimize to 0x0
        setSize(new Dimension(700,700));
        // Set frame to full windowed fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Creates new panel
        Panel panel = new Panel();
        // Added canvas with mouse listener
        PetriNetCanvas petriNetCanvas = new PetriNetCanvas();
        petriNetCanvas.addMouseListener(petriNetCanvas);
        // Added import button with import net action listener
        Button importButton = new Button("Import net");
        importButton.addActionListener(new ImportNetListener(petriNetCanvas));
        // Added components to panel
        panel.add(importButton);
        this.add("North",panel);
        this.add("Center",petriNetCanvas);
        // Setting frame to visible
        this.setVisible(true);
    }
}
