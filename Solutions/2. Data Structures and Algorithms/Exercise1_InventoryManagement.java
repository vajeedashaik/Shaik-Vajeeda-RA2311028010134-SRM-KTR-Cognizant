import java.util.HashMap;
import java.util.Map;

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" + productId + "] " + productName + " qty=" + quantity + " price=" + price;
    }
}

// HashMap chosen: O(1) average for add/update/delete by productId key
class Inventory {
    private Map<Integer, Product> store = new HashMap<>();

    // O(1) average
    public void add(Product p) {
        store.put(p.productId, p);
    }

    // O(1) average
    public void update(int productId, int newQty, double newPrice) {
        Product p = store.get(productId);
        if (p == null) throw new IllegalArgumentException("Product not found: " + productId);
        p.quantity = newQty;
        p.price = newPrice;
    }

    // O(1) average
    public void delete(int productId) {
        store.remove(productId);
    }

    public Product get(int productId) {
        return store.get(productId);
    }

    public void printAll() {
        store.values().forEach(System.out::println);
    }
}

public class Exercise1_InventoryManagement {
    public static void main(String[] args) {
        Inventory inv = new Inventory();

        inv.add(new Product(1, "Laptop", 50, 999.99));
        inv.add(new Product(2, "Mouse", 200, 29.99));
        inv.add(new Product(3, "Keyboard", 150, 49.99));

        System.out.println("=== Initial Inventory ===");
        inv.printAll();

        inv.update(1, 45, 949.99);
        System.out.println("\n=== After Update (Laptop) ===");
        System.out.println(inv.get(1));

        inv.delete(2);
        System.out.println("\n=== After Delete (Mouse) ===");
        inv.printAll();

        /*
         * Time Complexity (HashMap):
         *   add    -> O(1) avg, O(n) worst (hash collision)
         *   update -> O(1) avg
         *   delete -> O(1) avg
         *
         * Optimization: use a good hash function to minimize collisions.
         * For range queries (price range), augment with TreeMap O(log n).
         */
    }
}
