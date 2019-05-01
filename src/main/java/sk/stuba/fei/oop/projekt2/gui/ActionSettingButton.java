package sk.stuba.fei.oop.projekt2.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSettingButton extends Button {

    PetriNetCanvas canvas;

    public ActionSettingButton(String label, PetriNetCanvas canvas) throws HeadlessException {
        super(label);
        this.canvas = canvas;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setCurrentAction(e.getActionCommand());
            }
        });
    }

}
