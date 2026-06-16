package FactoryMethodPatternExample;

public abstract class DocumentFactory {

    public abstract Document createDocument();

    public void openDocument() {
        Document doc = createDocument();
        System.out.println("=== Created: " + doc.getType() + " ===");
        doc.open();
        doc.save();
        doc.close();
        System.out.println();
    }
}