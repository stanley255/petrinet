package sk.stuba.fei.oop.projekt2.utils;

import sk.stuba.fei.oop.projekt2.generated.Document;
import sk.stuba.fei.oop.projekt2.gui.PetriNetCanvas;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ExportNetListener implements ActionListener {

    private PetriNetCanvas canvas;

    public ExportNetListener(PetriNetCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);
            if (!(returnValue == JFileChooser.APPROVE_OPTION)) {
                return;
            }
            // TODO Nahrad aktualnou petri sietou
            File loadedXML = new FileImporter().loadFile();
            Document document = new DocumentLoader().loadDocumentFromXML(loadedXML);

            Document exportDocument = new Document();
            exportDocument.setArc(document.getArc());
            exportDocument.setPlace(document.getPlace());
            exportDocument.setTransition(document.getTransition());
            JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(exportDocument, fileChooser.getSelectedFile().getAbsoluteFile());
        } catch (FileLoadException | JAXBException excp) {
            System.out.println("File was not exported!");
        }
    }
}