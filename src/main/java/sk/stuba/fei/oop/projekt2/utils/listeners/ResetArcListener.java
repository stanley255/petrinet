package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.*;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;

import java.awt.event.MouseEvent;

public final class ResetArcListener extends ActionButtonListener {

    public ResetArcListener(PetriNetCanvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        addResetArc(e);
    }

    public void addResetArc(MouseEvent e) {
        for (Drawable drawable : canvas.getDrawables()) {
            if (drawable.contains(e.getX(),e.getY())) {
                // Verify if clicked drawable is vertex => can be connected
                if (!isVertex(drawable)) {
                    canvas.setStartPointPlace(null);
                    canvas.setStartPointTransition(null);
                    break;
                }
                // If First time clicked Else Second time clicked
                if (canvas.getStartPointPlace()==null) {
                    serveFirstResetArcClick(drawable);
                    break;
                } else {
                    serveSecondResetArcClick(drawable);
                    break;
                }
            }
        }
    }

    private void serveFirstResetArcClick(Drawable drawable) {
        try {
            canvas.setStartPointTransition(null);
            canvas.setStartPointPlace((Place2D) drawable);
            System.out.println("(Reset Click First) Place was set as reset input");
        } catch (ClassCastException e) {
            System.out.println("(Reset Click First) Cannot be set as input");
            canvas.setStartPointPlace(null);
        }
    }

    private void serveSecondResetArcClick(Drawable drawable) {
        try {
            Place2D startPointPlace = canvas.getStartPointPlace();
            Transition2D transition2D = (Transition2D) drawable;
            ResetArc resetArc = new ResetArc(startPointPlace.getPlace(),transition2D.getTransition());
            canvas.getPetriNet().add(resetArc);
            ResetArc2D resetArc2D = new ResetArc2D(startPointPlace.getX()+RADIUS,startPointPlace.getY()+RADIUS,transition2D.getX()+RADIUS,transition2D.getY()+RADIUS,resetArc.getId(),startPointPlace.getId(),transition2D.getId(),resetArc);
            canvas.getDrawables().add(resetArc2D);
            canvas.repaint();
            System.out.println("(Reset Click Second) Place and transition connected");
        } catch (ClassCastException e) {
            System.out.println("(Reset Click Second) Cannot be connected");
            e.getMessage(); // Ignored
        }
        canvas.setStartPointPlace(null);
        canvas.setStartPointTransition(null);
    }

}
