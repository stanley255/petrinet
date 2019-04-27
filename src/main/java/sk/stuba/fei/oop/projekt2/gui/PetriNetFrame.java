package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.utils.ImportNetListener;
import sk.stuba.fei.oop.projekt2.utils.ExportNetListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        // Added export button
        Button exportButton = new Button("Export net");
        exportButton.addActionListener(new ExportNetListener(petriNetCanvas));
        panel.add(exportButton);
        // Added transition button
        Button transitionButton = new Button("Add transition");
        transitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                petriNetCanvas.setCurrentAction(e.getActionCommand());
            }
        });
        // Added place button
        Button placeButton = new Button("Add place");
        placeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                petriNetCanvas.setCurrentAction(e.getActionCommand());
            }
        });
        // Added arc button
        Button arcButton = new Button("Add arc");
        arcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                petriNetCanvas.setCurrentAction(e.getActionCommand());
            }
        });
        // Added reset arc button
        Button resetArcButton = new Button("Add reset arc");
        resetArcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                petriNetCanvas.setCurrentAction(e.getActionCommand());
            }
        });
        // Added play button
        Button playButton = new Button("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                petriNetCanvas.setCurrentAction(e.getActionCommand());
            }
        });
        // Added delete button
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                petriNetCanvas.setCurrentAction(e.getActionCommand());
            }
        });
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
