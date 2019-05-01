package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.utils.ImportNetListener;
import sk.stuba.fei.oop.projekt2.utils.ExportNetListener;

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
        // Added export button
        Button exportButton = new Button("Export net");
        exportButton.addActionListener(new ExportNetListener(petriNetCanvas));
        // Added transition button
        Button transitionButton = new ActionSettingButton("Add transition",petriNetCanvas);
        // Added place button
        Button placeButton = new ActionSettingButton("Add place",petriNetCanvas);
        // Added arc button
        Button arcButton = new ActionSettingButton("Add arc",petriNetCanvas);
        // Added reset arc button
        Button resetArcButton = new ActionSettingButton("Add reset arc",petriNetCanvas);
        // Added play button
        Button playButton = new ActionSettingButton("Play",petriNetCanvas);
        // Added delete button
        Button deleteButton = new ActionSettingButton("Delete",petriNetCanvas);

        panel.add(importButton);
        panel.add(exportButton);
        panel.add(transitionButton);
        panel.add(placeButton);
        panel.add(arcButton);
        panel.add(resetArcButton);
        panel.add(playButton);
        panel.add(deleteButton);

        this.add("North",panel);
        this.add("Center",petriNetCanvas);
        // Setting frame to visible
        this.setVisible(true);
    }
}
