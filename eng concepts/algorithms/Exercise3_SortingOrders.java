import java.util.Arrays;

class Order {
    int orderId;
    String customerName;
    double totalPrice;

    Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order#" + orderId + " [" + customerName + "] $" + totalPrice;
    }
}

public class Exercise3_SortingOrders {

    // O(n^2) time, O(1) space â€” stable sort
    static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order tmp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break; // already sorted
        }
    }

    // O(n log n) avg, O(n^2) worst, O(log n) space â€” not stable
    static void quickSort(Order[] orders, int lo, int hi) {
        if (lo < hi) {
            int pi = partition(orders, lo, hi);
            quickSort(orders, lo, pi - 1);
            quickSort(orders, pi + 1, hi);
        }
    }

    private static int partition(Order[] orders, int lo, int hi) {
        double pivot = orders[hi].totalPrice;
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order tmp = orders[i]; orders[i] = orders[j]; orders[j] = tmp;
            }
        }
        Order tmp = orders[i + 1]; orders[i + 1] = orders[hi]; orders[hi] = tmp;
        return i + 1;
    }

    static void print(Order[] orders) {
        for (Order o : orders) System.out.println("  " + o);
    }

    public static void main(String[] args) {
        Order[] base = {
            new Order(1, "Alice",   250.00),
            new Order(2, "Bob",     89.50),
            new Order(3, "Charlie", 540.75),
            new Order(4, "Diana",   130.00),
            new Order(5, "Eve",     310.20),
        };

        Order[] forBubble = base.clone();
        bubbleSort(forBubble);
        System.out.println("=== Bubble Sort (asc by totalPrice) ===");
        print(forBubble);

        Order[] forQuick = base.clone();
        quickSort(forQuick, 0, forQuick.length - 1);
        System.out.println("\n=== Quick Sort (asc by totalPrice) ===");
        print(forQuick);

        /*
         * Time Complexity:
         *   Bubble Sort -> O(n^2) avg/worst, O(n) best (early-exit optimization)
         *   Quick Sort  -> O(n log n) avg, O(n^2) worst (bad pivot choice)
         *
         * Quick Sort preferred: in-place, cache-friendly, faster in practice.
         * For production: use Arrays.sort() (TimSort, O(n log n) guaranteed).
         */
    }
}
