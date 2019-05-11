package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.*;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicInputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;

import java.awt.event.MouseEvent;

public final class ArcListener extends ActionButtonListener {

    public ArcListener(PetriNetCanvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        addArc(e);
    }

    public void addArc(MouseEvent e) {
        for (Drawable drawable : canvas.getDrawables()) {
            if (drawable.contains(e.getX(),e.getY())) {
                // Verify if clicked drawable is vertex => can be connected
                if (!isVertex(drawable)) {
                    canvas.setStartPointPlace(null);
                    canvas.setStartPointTransition(null);
                    return;
                }
                // If First time clicked Else Second time clicked
                if (canvas.getStartPointPlace()==null && canvas.getStartPointTransition()==null) {
                    serveFirstBasicArcClick(drawable);
                    return;
                } else {
                    serveSecondBasicArcClick(drawable);
                    return;
                }
            }
        }
        canvas.setStartPointPlace(null);
        canvas.setStartPointTransition(null);
    }

    private void serveFirstBasicArcClick(Drawable drawable) {
        try {
            canvas.setStartPointPlace((Place2D) drawable);
            canvas.setStartPointTransition(null);
            System.out.println("(Arc Click First) Place was set as input");
        } catch (ClassCastException ex) {
            canvas.setStartPointTransition((Transition2D) drawable);
            canvas.setStartPointPlace(null);
            System.out.println("(Arc Click First) Transition was set as input");
        }
    }

    private void serveSecondBasicArcClick(Drawable drawable) {
        if (canvas.getStartPointPlace() != null) {
            // Create BasicInputArc
            addBasicInputArc(drawable);
        } else if (canvas.getStartPointTransition() != null) {
            // Create BasicOutputArc
            addBasicOutputArc(drawable);
        }
        canvas.setStartPointPlace(null);
        canvas.setStartPointTransition(null);
    }

    private void addBasicInputArc(Drawable drawable) {
        try {
            Place2D startPointPlace = canvas.getStartPointPlace();
            Transition2D transition = (Transition2D) drawable;
            BasicInputArc arc = new BasicInputArc(startPointPlace.getPlace(),transition.getTransition());
            canvas.getPetriNet().add(arc);
            BasicInputArc2D arc2D = new BasicInputArc2D(startPointPlace.getX()+RADIUS,startPointPlace.getY()+RADIUS,transition.getX()+RADIUS,transition.getY()+RADIUS,startPointPlace.getId(),transition.getId(),arc);
            canvas.getDrawables().add(arc2D);
            canvas.repaint();
            System.out.println("(Arc Click Second) Place and Transition connected");
        } catch (ClassCastException e) {
            System.out.println("(Arc Click Second) Cannot connect these two objects");
        }
    }

    private void addBasicOutputArc(Drawable drawable) {
        try {
            Transition2D startPointTransition = canvas.getStartPointTransition();
            Place2D place = (Place2D) drawable;
            BasicOutputArc arc = new BasicOutputArc(startPointTransition.getTransition(),place.getPlace());
            canvas.getPetriNet().add(arc);
            BasicOutputArc2D arc2D = new BasicOutputArc2D(startPointTransition.getX()+RADIUS,startPointTransition.getY()+RADIUS,place.getX()+RADIUS,place.getY()+RADIUS,startPointTransition.getId(),place.getId(),arc);
            canvas.getDrawables().add(arc2D);
            canvas.repaint();
            System.out.println("(Arc Click Second) Transition and Place connected");
        } catch (ClassCastException e) {
            System.out.println("(Arc Click Second) Cannot connect these two objects");
        }
    }

}
