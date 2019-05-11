package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.Drawable;
import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;
import sk.stuba.fei.oop.projekt2.gui.Place2D;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.NegativeTokenCount;

import java.awt.event.MouseEvent;

public final class TokenListener extends ActionButtonListener {

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
                        leftButtonTokenAction(place);
                        return;
                    } else if (e.getButton() == 3) {
                        rightButtonTokenAction(place);
                        return;
                    }
                } catch (ClassCastException ex) {
                    System.out.println("Tokens can be accessed only in 'Place'");
                }
            }
        }
    }

    private void leftButtonTokenAction(Place2D place) {
        place.getPlace().setTokenCount(place.getTokens()+1);
        canvas.repaint();
        System.out.println("Token added to place");
    }

    private void rightButtonTokenAction(Place2D place) {
        try {
            place.getPlace().setTokenCount(place.getTokens()-1);
            System.out.println("Token removed from place");
        } catch (NegativeTokenCount ex) {
            System.out.println("Cannot remove token from place");
        }
        canvas.repaint();
    }

}
