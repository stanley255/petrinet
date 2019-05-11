package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.gui.Drawable;
import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;
import sk.stuba.fei.oop.projekt2.petrinet.PetriNet;
import sk.stuba.fei.oop.projekt2.petrinet.exceptions.FailedNetConversion;
import sk.stuba.fei.oop.projekt2.utils.DocumentLoader;
import sk.stuba.fei.oop.projekt2.utils.FileImporter;
import sk.stuba.fei.oop.projekt2.utils.FileLoadException;
import sk.stuba.fei.oop.projekt2.utils.converters.DrawableConverter;
import sk.stuba.fei.oop.projekt2.utils.converters.PetriNetConverter;

import javax.xml.bind.JAXBException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public final class ImportNetListener implements ActionListener {

    private PetriNetCanvas canvas;

    public ImportNetListener(PetriNetCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Loading XML
            File loadedXML = new FileImporter().loadFile();
            // Importing Document from XML
            Document document = new DocumentLoader().loadDocumentFromXML(loadedXML);
            // Parsing Document to PetriNet
            PetriNet petriNet = new PetriNetConverter().convert(document);
            // Parsing Document to Drawables
            DrawableConverter drawableConverter = new DrawableConverter();
            drawableConverter.setPetriNet(petriNet);
            List<Drawable> drawables = drawableConverter.convert(document);
            // Import converted variables to canvas
            canvas.setPetriNet(petriNet);
            canvas.setDrawables(drawables);
            canvas.repaint();
        } catch(FileLoadException exception) {
            System.out.println("File was not loaded!");
        } catch (JAXBException exception) {
            System.out.println("XML file could not be loaded!");
        } catch (FailedNetConversion exception) {
            System.out.println(exception.getMessage());
        }
    }
}
