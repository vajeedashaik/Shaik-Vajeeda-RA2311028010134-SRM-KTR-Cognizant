// Product interface
interface Document {
    void open();
    void save();
}

// Concrete products
class WordDocument implements Document {
    public void open() { System.out.println("Opening Word document (.docx)"); }
    public void save() { System.out.println("Saving Word document (.docx)"); }
}

class PdfDocument implements Document {
    public void open() { System.out.println("Opening PDF document (.pdf)"); }
    public void save() { System.out.println("Saving PDF document (.pdf)"); }
}

class ExcelDocument implements Document {
    public void open() { System.out.println("Opening Excel document (.xlsx)"); }
    public void save() { System.out.println("Saving Excel document (.xlsx)"); }
}

// Abstract factory
abstract class DocumentFactory {
    public abstract Document createDocument();

    // Template method: create + open
    public Document openDocument() {
        Document doc = createDocument();
        doc.open();
        return doc;
    }
}

// Concrete factories
class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new WordDocument(); }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new PdfDocument(); }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new ExcelDocument(); }
}

public class Exercise2_FactoryMethod {
    public static void main(String[] args) {
        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            Document doc = factory.openDocument();
            doc.save();
            System.out.println();
        }
    }
}
