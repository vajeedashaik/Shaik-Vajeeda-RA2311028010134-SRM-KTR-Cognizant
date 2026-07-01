import java.util.Arrays;
import java.util.Comparator;

class ProductSearch {
    int productId;
    String productName;
    String category;

    ProductSearch(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "[" + productId + "] " + productName + " (" + category + ")";
    }
}

public class Exercise2_EcommerceSearch {

    // O(n) â€” scans every element
    static ProductSearch linearSearch(ProductSearch[] products, String name) {
        for (ProductSearch p : products) {
            if (p.productName.equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    // O(log n) â€” array must be sorted by productName
    static ProductSearch binarySearch(ProductSearch[] sorted, String name) {
        int lo = 0, hi = sorted.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int cmp = sorted[mid].productName.compareToIgnoreCase(name);
            if (cmp == 0) return sorted[mid];
            else if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        ProductSearch[] products = {
            new ProductSearch(3, "Tablet",  "Electronics"),
            new ProductSearch(1, "Laptop",  "Electronics"),
            new ProductSearch(4, "Monitor", "Electronics"),
            new ProductSearch(2, "Mouse",   "Accessories"),
            new ProductSearch(5, "Webcam",  "Accessories"),
        };

        System.out.println("=== Linear Search ===");
        ProductSearch r1 = linearSearch(products, "Mouse");
        System.out.println(r1 != null ? "Found: " + r1 : "Not found");

        // Sort for binary search
        ProductSearch[] sorted = products.clone();
        Arrays.sort(sorted, Comparator.comparing(p -> p.productName.toLowerCase()));

        System.out.println("\n=== Binary Search ===");
        ProductSearch r2 = binarySearch(sorted, "Mouse");
        System.out.println(r2 != null ? "Found: " + r2 : "Not found");

        /*
         * Time Complexity:
         *   Linear Search  -> O(n) best/avg/worst
         *   Binary Search  -> O(log n) avg/worst, O(1) best
         *
         * Binary search requires sorted data (O(n log n) upfront sort cost).
         * For large, frequently queried, mostly-static catalogs: binary search wins.
         * For small or unsorted/dynamic data: linear search is simpler.
         */
    }
}
