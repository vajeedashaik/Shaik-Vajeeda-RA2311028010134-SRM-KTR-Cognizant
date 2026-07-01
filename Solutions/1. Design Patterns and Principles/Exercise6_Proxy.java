// Subject interface
interface Image {
    void display();
}

// Real subject: expensive to construct (simulates remote load)
class RealImage implements Image {
    private final String filename;

    RealImage(String filename) {
        this.filename = filename;
        loadFromServer();
    }
     
    private void loadFromServer() {
        System.out.println("[RealImage] Loading '" + filename + "' from remote server...");
    }

    public void display() {
        System.out.println("[RealImage] Displaying '" + filename + "'");
    }
}

// Proxy: lazy init + cache â€” RealImage created only on first display()
class ProxyImage implements Image {
    private final String filename;
    private RealImage realImage; // null until first access

    ProxyImage(String filename) {
        this.filename = filename;
        System.out.println("[ProxyImage] Proxy created for '" + filename + "' (not loaded yet)");
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // lazy init
        } else {
            System.out.println("[ProxyImage] Serving '" + filename + "' from cache");
        }
        realImage.display();
    }
}

public class Exercise6_Proxy {
    public static void main(String[] args) {
        Image img1 = new ProxyImage("photo1.jpg");
        Image img2 = new ProxyImage("photo2.jpg");

        System.out.println("\n--- First display of img1 (triggers load) ---");
        img1.display();

        System.out.println("\n--- Second display of img1 (from cache) ---");
        img1.display();

        System.out.println("\n--- First display of img2 (triggers load) ---");
        img2.display();

        // img2 is loaded only when needed, not at proxy creation
    }
}
