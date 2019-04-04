package sk.stuba.fei.oop.projekt2;

import sk.stuba.fei.oop.projekt2.generated.Document;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Document document = (Document) unmarshaller.unmarshal(new File("newmodel.xml"));

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(document, System.out);
            //document.getPlace()

            /*JFileChooser choice = new JFileChooser();
            int option = choice.showOpenDialog();
            if (option == JFileChooser.APPROVE_OPTION) {
                try{
                    Scanner scan = new Scanner(new FileReader(choice.getSelectedFile().getPath()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }*/
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
