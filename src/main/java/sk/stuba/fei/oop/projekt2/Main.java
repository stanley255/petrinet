package sk.stuba.fei.oop.projekt2;

import sk.stuba.fei.oop.projekt2.gui.PetriNetFrame;

public class Main {
    public static void main(String[] args) {
        PetriNetFrame frame = new PetriNetFrame("PetriNet");

        /*

        // JAXB
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Document document = (Document) unmarshaller.unmarshal(new File("newmodel.xml"));
            System.out.println(document.getArc().size());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        * */

    }
}
