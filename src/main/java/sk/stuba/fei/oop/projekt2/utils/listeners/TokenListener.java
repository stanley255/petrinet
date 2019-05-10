package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.Drawable;
import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;
import sk.stuba.fei.oop.projekt2.gui.Place2D;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.NegativeTokenCount;

import java.awt.event.MouseEvent;

public class TokenListener extends ActionButtonListener {

    public TokenListener(PetriNetCanvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        for (Drawable drawable : canvas.getDrawables()) {
            if (drawable.contains(e.getX(),e.getY())) {
                try {
                    Place2D place = (Place2D) drawable;
                    if (e.getButton() == 1) {
                        // Left mouse button pressed
                        place.getPlace().setTokenCount(place.getTokens()+1);
                        canvas.repaint();
                        return;
                    } else if (e.getButton() == 3) {
                        // Right mouse button pressed
                        try {
                            place.getPlace().setTokenCount(place.getTokens()-1);
                        } catch (NegativeTokenCount ex) {
                            ex.getMessage(); // Ignored
                        }
                        canvas.repaint();
                        return;
                    }
                } catch (ClassCastException ex) {
                    ex.getMessage(); // Ignore
                }
            }
        }
    }

}
