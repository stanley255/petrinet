package sk.stuba.fei.oop.projekt2.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionButton extends Button {

    private PetriNetCanvas canvas;
    private MouseListener mouseListener;

    public ActionButton(String label, PetriNetCanvas canvas, MouseListener mouseListener) throws HeadlessException {
        super(label);
        this.canvas = canvas;
        this.mouseListener = mouseListener;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                canvas.removeMouseListeners();
                canvas.addMouseListener(mouseListener);
                canvas.setStartPointPlace(null);
                canvas.setStartPointTransition(null);
            }
        });
    }



}
