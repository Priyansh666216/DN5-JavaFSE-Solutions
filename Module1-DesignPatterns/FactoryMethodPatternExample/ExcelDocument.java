package FactoryMethodPatternExample;

public class ExcelDocument implements Document {

    @Override
    public void open() {
        System.out.println("[Excel] Opening Excel document (.xlsx)");
    }

    @Override
    public void save() {
        System.out.println("[Excel] Saving Excel document (.xlsx)");
    }

    @Override
    public void close() {
        System.out.println("[Excel] Closing Excel document (.xlsx)");
    }

    @Override
    public String getType() {
        return "Excel Document";
    }
}