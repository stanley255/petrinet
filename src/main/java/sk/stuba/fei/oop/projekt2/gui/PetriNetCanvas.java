package sk.stuba.fei.oop.projekt2.gui;

import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicInputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.BasicOutputArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.arcs.ResetArc;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Place;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Transition;
import sk.stuba.fei.oop.projekt2.petrinet.components.vertices.Vertex;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PetriNetCanvas extends Canvas implements MouseListener {

    private PetriNet petriNet;
    private List<Drawable> drawables;
    private String currentAction;

    private Place2D startPointPlace;
    private Transition2D startPointTransition;

    private final int DIAMETER = 30;

    public PetriNetCanvas() {
        this.petriNet = new PetriNet();
        this.drawables = new ArrayList<>();
        this.currentAction = "play";
        this.startPointPlace = null;
        this.startPointTransition = null;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public void setDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }

    private void execute(MouseEvent e) {
        if (drawables == null) { return; }
        for (Drawable drawable : drawables) {
            if (!drawable.contains(e.getX(),e.getY())) { continue; }
            if (drawable instanceof Executable) {
                ((Executable) drawable).onClick();
                repaint();
            }
        }
    }

    private void addTransition(MouseEvent e) {
        Transition transition = new Transition();
        petriNet.add(transition);
        Transition2D transition2D = new Transition2D(e.getX()-DIAMETER/2,e.getY()-DIAMETER/2,DIAMETER,DIAMETER,transition,petriNet);
        drawables.add(transition2D);
        repaint();
    }

    private void addPlace(MouseEvent e) {
        Place place = new Place();
        petriNet.add(place);
        Place2D place2D = new Place2D(e.getX()-DIAMETER/2,e.getY()-DIAMETER/2,DIAMETER,DIAMETER,place);
        drawables.add(place2D);
        repaint();
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
            petriNet.add(arc);
            BasicInputArc2D arc2D = new BasicInputArc2D(startPointPlace.getX(),startPointPlace.getY(),transition.getX(),transition.getY(),startPointPlace.getId(),transition.getId(),arc);
            drawables.add(arc2D);
            repaint();
        } catch (ClassCastException e) {
            e.getMessage(); // Ignored
        }
    }

    private void addBasicOutputArc(Drawable drawable) {
        try {
            Place2D place = (Place2D) drawable;
            BasicOutputArc arc = new BasicOutputArc(startPointTransition.getTransition(),place.getPlace());
            petriNet.add(arc);
            BasicOutputArc2D arc2D = new BasicOutputArc2D(startPointTransition.getX(),startPointTransition.getY(),place.getX(),place.getY(),startPointTransition.getId(),place.getId(),arc);
            drawables.add(arc2D);
            repaint();
        } catch (ClassCastException e) {
            e.getMessage(); // Ignored
        }
    }

    private void addArc(MouseEvent e) {
        for (Drawable drawable : drawables) {
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

    private void addResetArc(MouseEvent e) {
        for (Drawable drawable : drawables) {
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
            petriNet.add(resetArc);
            ResetArc2D resetArc2D = new ResetArc2D(startPointPlace.getX(),startPointPlace.getY(),transition2D.getX(),transition2D.getY(),resetArc.getId(),startPointPlace.getId(),transition2D.getId(),resetArc);
            drawables.add(resetArc2D);
            repaint();
        } catch (ClassCastException e) {
            e.getMessage(); // Ignored
        }
        startPointPlace = null;
        startPointTransition = null;
    }

    private void deleteArc(Drawable drawable) {
        // Delete from Drawables
        drawables.remove(drawable);
        // Delete from PetriNet
        Arc2D arc2D = (Arc2D) drawable;
        petriNet.deleteArc(arc2D.getId());
    }

    private void delete(MouseEvent e) {
        for (Drawable drawable : drawables) {
            if (isVertex(drawable)) {
                // Drawable is Vertex
                if (drawable.contains(e.getX(),e.getY())) {
                    System.out.println("Delete Vertex");
                    repaint();
                    break;
                }
            } else {
                if (drawable.intersects(new Rectangle2D.Double(e.getX()-10,e.getY()-10,10,10))) {
                    // Drawable is Arc
                    System.out.println("Delete Arc");
                    deleteArc(drawable);
                    repaint();
                    break;
                } else {
                    System.out.println("No intersection");
                }
            }

        }
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
        switch(this.currentAction.toLowerCase()) {
            case "add transition":
                startPointPlace = null;
                startPointTransition = null;
                addTransition(e);
                break;
            case "add place":
                startPointPlace = null;
                startPointTransition = null;
                addPlace(e);
                break;
            case "add arc":
                addArc(e);
                break;
            case "add reset arc":
                addResetArc(e);
                break;
            case "delete":
                startPointPlace = null;
                startPointTransition = null;
                delete(e);
                break;
            case "play":
                startPointPlace = null;
                startPointTransition = null;
                execute(e);
                break;
            default:
                startPointPlace = null;
                startPointTransition = null;
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
