package sk.stuba.fei.oop.projekt2.utils;

import sk.stuba.fei.oop.projekt2.gui.*;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicInputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.NegativeTokenCount;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class GuiObjectsManipulator {

    private PetriNetCanvas canvas;
    private Place2D startPointPlace;
    private Transition2D startPointTransition;

    private final int DIAMETER = 30;
    private final int RADIUS = 15;

    public GuiObjectsManipulator(PetriNetCanvas canvas) {
        this.canvas = canvas;
        this.startPointPlace = null;
        this.startPointTransition = null;
    }

    public void setStartPointPlace(Place2D startPointPlace) {
        this.startPointPlace = startPointPlace;
    }

    public void setStartPointTransition(Transition2D startPointTransition) {
        this.startPointTransition = startPointTransition;
    }

    public void execute(MouseEvent e) {
        if (canvas.getDrawables() == null) { return; }
        for (Drawable drawable : canvas.getDrawables()) {
            if (!drawable.contains(e.getX(),e.getY())) { continue; }
            if (drawable instanceof Executable) {
                ((Executable) drawable).onClick();
                canvas.repaint();
            }
        }
    }

    public void addTransition(MouseEvent e) {
        Transition transition = new Transition();
        canvas.getPetriNet().add(transition);
        Transition2D transition2D = new Transition2D(e.getX()-DIAMETER/2,e.getY()-DIAMETER/2,DIAMETER,DIAMETER,transition,canvas.getPetriNet());
        canvas.getDrawables().add(transition2D);
        canvas.repaint();
    }

    public void placeAction(MouseEvent e) {
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
        addPlace(e);
    }

    public void addPlace(MouseEvent e) {
        Place place = new Place();
        canvas.getPetriNet().add(place);
        Place2D place2D = new Place2D(e.getX()-DIAMETER/2,e.getY()-DIAMETER/2,DIAMETER,DIAMETER,place);
        canvas.getDrawables().add(place2D);
        canvas.repaint();
    }

    private boolean isVertex(Drawable drawable) {
        try {
            Place2D place = (Place2D) drawable;
            return true;
        } catch (ClassCastException ex1) {
            try {
                Transition2D transition = (Transition2D) drawable;
                return true;
            } catch (ClassCastException ex2) {
                return false;
            }
        }
    }

    private void serveFirstBasicArcClick(Drawable drawable) {
        System.out.println("1. click");
        try {
            startPointPlace = (Place2D) drawable;
            startPointTransition = null;
            System.out.println("Place was set as input");
        } catch (ClassCastException ex) {
            startPointTransition = (Transition2D) drawable;
            startPointPlace = null;
            System.out.println("Transition was set as input");
        }
    }

    private void serveSecondBasicArcClick(Drawable drawable) {
        System.out.println("2. click");
        if (startPointPlace != null) {
            // Create BasicInputArc
            addBasicInputArc(drawable);
        } else if (startPointTransition != null) {
            // Create BasicOutputArc
            addBasicOutputArc(drawable);
        }
        startPointTransition = null;
        startPointPlace = null;
    }

    private void addBasicInputArc(Drawable drawable) {
        try {
            Transition2D transition = (Transition2D) drawable;
            BasicInputArc arc = new BasicInputArc(startPointPlace.getPlace(),transition.getTransition());
            canvas.getPetriNet().add(arc);
            BasicInputArc2D arc2D = new BasicInputArc2D(startPointPlace.getX()+RADIUS,startPointPlace.getY()+RADIUS,transition.getX()+RADIUS,transition.getY()+RADIUS,startPointPlace.getId(),transition.getId(),arc);
            canvas.getDrawables().add(arc2D);
            canvas.repaint();
        } catch (ClassCastException e) {
            e.getMessage(); // Ignored
        }
    }

    private void addBasicOutputArc(Drawable drawable) {
        try {
            Place2D place = (Place2D) drawable;
            BasicOutputArc arc = new BasicOutputArc(startPointTransition.getTransition(),place.getPlace());
            canvas.getPetriNet().add(arc);
            BasicOutputArc2D arc2D = new BasicOutputArc2D(startPointTransition.getX()+RADIUS,startPointTransition.getY()+RADIUS,place.getX()+RADIUS,place.getY()+RADIUS,startPointTransition.getId(),place.getId(),arc);
            canvas.getDrawables().add(arc2D);
            canvas.repaint();
        } catch (ClassCastException e) {
            e.getMessage(); // Ignored
        }
    }

    public void addArc(MouseEvent e) {
        for (Drawable drawable : canvas.getDrawables()) {
            if (drawable.contains(e.getX(),e.getY())) {
                // Verify if clicked drawable is vertex => can be connected
                if (!isVertex(drawable)) {
                    startPointPlace = null;
                    startPointTransition = null;
                    return;
                }
                // If First time clicked Else Second time clicked
                if (startPointPlace==null && startPointTransition==null) {
                    serveFirstBasicArcClick(drawable);
                    return;
                } else {
                    serveSecondBasicArcClick(drawable);
                    return;
                }
            }
        }
        startPointPlace = null;
        startPointTransition = null;
    }

    public void addResetArc(MouseEvent e) {
        for (Drawable drawable : canvas.getDrawables()) {
            if (drawable.contains(e.getX(),e.getY())) {
                // Verify if clicked drawable is vertex => can be connected
                if (!isVertex(drawable)) {
                    startPointPlace = null;
                    startPointTransition = null;
                    break;
                }
                // If First time clicked Else Second time clicked
                if (startPointPlace==null) {
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
            startPointTransition = null;
            startPointPlace = (Place2D) drawable;
            System.out.println("Place was set as reset input");
        } catch (ClassCastException e) {
            startPointPlace = null;
        }
    }

    private void serveSecondResetArcClick(Drawable drawable) {
        try {
            Transition2D transition2D = (Transition2D) drawable;
            ResetArc resetArc = new ResetArc(startPointPlace.getPlace(),transition2D.getTransition());
            canvas.getPetriNet().add(resetArc);
            ResetArc2D resetArc2D = new ResetArc2D(startPointPlace.getX()+RADIUS,startPointPlace.getY()+RADIUS,transition2D.getX()+RADIUS,transition2D.getY()+RADIUS,resetArc.getId(),startPointPlace.getId(),transition2D.getId(),resetArc);
            canvas.getDrawables().add(resetArc2D);
            canvas.repaint();
        } catch (ClassCastException e) {
            e.getMessage(); // Ignored
        }
        startPointPlace = null;
        startPointTransition = null;
    }

    private void deleteArc(Drawable drawable) {
        // Delete from Drawables
        canvas.getDrawables().remove(drawable);
        // Delete from PetriNet
        Arc2D arc2D = (Arc2D) drawable;
        canvas.getPetriNet().deleteArc(arc2D.getId());
    }

    private void deleteVertex(Drawable drawableToDelete) {
        for (Drawable drawable : getDrawableArcsToDelete(drawableToDelete)) {
            deleteArc(drawable);
        }
        canvas.getDrawables().remove(drawableToDelete);
        canvas.getPetriNet().deleteJustVertex(((Identifiable)drawableToDelete).getId());
    }

    private List<Drawable> getDrawableArcsToDelete(Drawable drawableToDelete) {
        List<Drawable> drawableArcsToDelete = new ArrayList<>();
        for (Drawable drawable : canvas.getDrawables()) {
            try {
                Arc2D arc = (Arc2D) drawable;
                if (arc.getDestinationId().equals(((Identifiable) drawableToDelete).getId()) || arc.getSourceId().equals(((Identifiable) drawableToDelete).getId())) {
                    drawableArcsToDelete.add(drawable);
                }
            } catch(ClassCastException e) {
                e.getMessage(); // Ignored
            }
        }
        return drawableArcsToDelete;
    }

    public void delete(MouseEvent e) {
        Rectangle2D.Double deleteLineRectangle = new Rectangle2D.Double(e.getX()-5,e.getY()-5,10,10);
        for (Drawable drawable : canvas.getDrawables()) {
            if (isVertex(drawable)) {
                // Drawable is Vertex
                if (drawable.contains(e.getX(),e.getY())) {
                    deleteVertex(drawable);
                    canvas.repaint();
                    break;
                }
            } else {
                if (drawable.intersects(deleteLineRectangle)) {
                    // Drawable is Arc
                    deleteArc(drawable);
                    canvas.repaint();
                    break;
                }
            }

        }
    }

}
