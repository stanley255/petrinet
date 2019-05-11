package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.Drawable;
import sk.stuba.fei.oop.projekt2.gui.Executable;
import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;

import java.awt.event.MouseEvent;

public final class PlayListener extends ActionButtonListener {

    public PlayListener(PetriNetCanvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        execute(e);
    }

    public void execute(MouseEvent e) {
        if (canvas.getDrawables() == null) { return; }
        for (Drawable drawable : canvas.getDrawables()) {
            if (!drawable.contains(e.getX(),e.getY())) { continue; }
            if (drawable instanceof Executable) {
                ((Executable) drawable).onClick();
                canvas.repaint();
                return;
            }
        }
    }

}
