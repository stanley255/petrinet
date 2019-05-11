package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.gui.*;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public final class DeleteListener extends ActionButtonListener {

    public DeleteListener(PetriNetCanvas canvas) {
        super(canvas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        delete(e);
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

}
