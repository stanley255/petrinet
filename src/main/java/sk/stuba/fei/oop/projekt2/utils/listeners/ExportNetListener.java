package sk.stuba.fei.oop.projekt2.utils.listeners;

import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;
import sk.stuba.fei.oop.projekt2.utils.FileImporter;
import sk.stuba.fei.oop.projekt2.utils.FileLoadException;
import sk.stuba.fei.oop.projekt2.utils.converters.DocumentConverter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ExportNetListener implements ActionListener {

    private PetriNetCanvas canvas;

    public ExportNetListener(PetriNetCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DocumentConverter documentConverter = new DocumentConverter();
            JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(documentConverter.convert(canvas.getDrawables()), new FileImporter().loadFile());
            System.out.println("File was exported successfully!");
        } catch (FileLoadException | IllegalArgumentException | JAXBException exc) {
            System.out.println("File was not exported!");
        }
    }
}
