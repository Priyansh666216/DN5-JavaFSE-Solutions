package FactoryMethodPatternExample;

public class DocumentTest {

    public static void main(String[] args) {

        System.out.println("===== Factory Method Pattern - Document Management =====\n");
        DocumentFactory wordFactory  = new WordDocumentFactory();
        DocumentFactory pdfFactory   = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        wordFactory.openDocument();
        pdfFactory.openDocument();
        excelFactory.openDocument();

        System.out.println("===== Polymorphism Demo =====\n");

        DocumentFactory[] factories = {
                new WordDocumentFactory(),
                new PdfDocumentFactory(),
                new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            Document doc = factory.createDocument();
            System.out.println("Document type : " + doc.getType());
            doc.open();
        }
    }
}