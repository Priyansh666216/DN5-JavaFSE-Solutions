package FactoryMethodPatternExample;

public class WordDocument implements Document {

    @Override
    public void open() {
        System.out.println("[Word] Opening Word document (.docx)");
    }

    @Override
    public void save() {
        System.out.println("[Word] Saving Word document (.docx)");
    }

    @Override
    public void close() {
        System.out.println("[Word] Closing Word document (.docx)");
    }

    @Override
    public String getType() {
        return "Word Document";
    }
}