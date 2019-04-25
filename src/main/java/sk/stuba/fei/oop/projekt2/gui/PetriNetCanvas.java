package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.util.List;

public class PetriNetCanvas extends Canvas implements MouseListener {

    private PetriNet petriNet;
    private List<Drawable> drawables;

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }

    public void setDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (petriNet == null) { return; }
        for (Drawable drawable : drawables) {
            drawable.draw(g2);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (drawables == null) { return; }
        for (Drawable drawable : drawables) {
            if (!drawable.contains(e.getX(),e.getY())) { continue; }
            if (drawable instanceof Executable) {
                ((Executable) drawable).onClick();
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
