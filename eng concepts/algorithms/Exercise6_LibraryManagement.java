import java.util.Arrays;
import java.util.Comparator;

class Book {
    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "[" + bookId + "] \"" + title + "\" by " + author;
    }
}

public class Exercise6_LibraryManagement {

    // O(n) â€” no prerequisite on order
    static Book linearSearchByTitle(Book[] books, String title) {
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    // O(log n) â€” array must be sorted alphabetically by title
    static Book binarySearchByTitle(Book[] sorted, String title) {
        int lo = 0, hi = sorted.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int cmp = sorted[mid].title.compareToIgnoreCase(title);
            if (cmp == 0) return sorted[mid];
            else if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book(3, "Clean Code",             "Robert Martin"),
            new Book(1, "Design Patterns",        "Gang of Four"),
            new Book(5, "Effective Java",         "Joshua Bloch"),
            new Book(2, "Pragmatic Programmer",   "Hunt & Thomas"),
            new Book(4, "Refactoring",            "Martin Fowler"),
        };

        System.out.println("=== Linear Search: 'Refactoring' ===");
        Book r1 = linearSearchByTitle(books, "Refactoring");
        System.out.println(r1 != null ? "Found: " + r1 : "Not found");

        // Sort for binary search
        Book[] sorted = books.clone();
        Arrays.sort(sorted, Comparator.comparing(b -> b.title.toLowerCase()));

        System.out.println("\n=== Binary Search: 'Refactoring' ===");
        Book r2 = binarySearchByTitle(sorted, "Refactoring");
        System.out.println(r2 != null ? "Found: " + r2 : "Not found");

        /*
         * Time Complexity:
         *   Linear Search -> O(n) all cases
         *   Binary Search -> O(log n) avg/worst, requires sorted collection
         *
         * Small or unsorted collections: linear search â€” no overhead.
         * Large, sorted, read-heavy collections: binary search â€” dramatic speedup.
         * Dynamic collections: consider a balanced BST or trie for O(log n) inserts + search.
         */
    }
}
