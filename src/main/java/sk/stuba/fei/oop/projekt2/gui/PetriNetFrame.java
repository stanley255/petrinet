package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.utils.listeners.*;

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
        // Added import button with import net action listener
        Button importButton = new Button("Import net");
        importButton.addActionListener(new ImportNetListener(petriNetCanvas));
        // Added export button
        Button exportButton = new Button("Export net");
        exportButton.addActionListener(new ExportNetListener(petriNetCanvas));

        Button playButton = new ActionButton("Play",petriNetCanvas,new PlayListener(petriNetCanvas));
        Button deleteButton = new ActionButton("Delete",petriNetCanvas,new DeleteListener(petriNetCanvas));
        Button transitionButton = new ActionButton("Transition",petriNetCanvas,new TransitionListener(petriNetCanvas));
        Button placeButton = new ActionButton("Place",petriNetCanvas,new PlaceListener(petriNetCanvas));
        Button arcButton = new ActionButton("Arc",petriNetCanvas,new ArcListener(petriNetCanvas));
        Button resetArcButton = new ActionButton("ResetArc",petriNetCanvas,new ResetArcListener(petriNetCanvas));
        Button tokenButton = new ActionButton("Token",petriNetCanvas, new TokenListener(petriNetCanvas));

        panel.add(importButton);
        panel.add(exportButton);
        panel.add(playButton);
        panel.add(deleteButton);
        panel.add(transitionButton);
        panel.add(placeButton);
        panel.add(tokenButton);
        panel.add(arcButton);
        panel.add(resetArcButton);

        this.add("North",panel);
        this.add("Center",petriNetCanvas);
        this.setVisible(true);
    }
}
