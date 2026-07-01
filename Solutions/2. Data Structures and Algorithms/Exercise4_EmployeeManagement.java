class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "[" + employeeId + "] " + name + " | " + position + " | $" + salary;
    }
}

// Fixed-capacity array-backed store; resize manually when full
class EmployeeArray {
    private Employee[] data;
    private int size;

    EmployeeArray(int capacity) {
        data = new Employee[capacity];
        size = 0;
    }

    // O(1) amortized â€” appends at end
    public void add(Employee e) {
        if (size == data.length) {
            // double capacity (like ArrayList)
            Employee[] bigger = new Employee[data.length * 2];
            System.arraycopy(data, 0, bigger, 0, size);
            data = bigger;
        }
        data[size++] = e;
    }

    // O(n) â€” linear scan by employeeId
    public Employee search(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (data[i].employeeId == employeeId) return data[i];
        }
        return null;
    }

    // O(n) â€” visits all elements
    public void traverse() {
        for (int i = 0; i < size; i++) System.out.println("  " + data[i]);
    }

    // O(n) â€” find + shift remaining elements left
    public boolean delete(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (data[i].employeeId == employeeId) {
                System.arraycopy(data, i + 1, data, i, size - i - 1);
                data[--size] = null;
                return true;
            }
        }
        return false;
    }
}

public class Exercise4_EmployeeManagement {
    public static void main(String[] args) {
        EmployeeArray emp = new EmployeeArray(4);

        emp.add(new Employee(101, "Alice",   "Engineer",  85000));
        emp.add(new Employee(102, "Bob",     "Manager",  110000));
        emp.add(new Employee(103, "Charlie", "Analyst",   72000));

        System.out.println("=== All Employees ===");
        emp.traverse();

        System.out.println("\n=== Search ID=102 ===");
        System.out.println(emp.search(102));

        emp.delete(102);
        System.out.println("\n=== After Delete ID=102 ===");
        emp.traverse();

        /*
         * Time Complexity:
         *   add      -> O(1) amortized (O(n) on resize, rare)
         *   search   -> O(n) â€” no index on employeeId
         *   traverse -> O(n)
         *   delete   -> O(n) â€” find + shift
         *
         * Arrays: cache-friendly, O(1) random access by index.
         * Limitation: fixed order, slow unsorted search, costly insertion/deletion mid-array.
         * Use HashMap<Integer,Employee> for O(1) search when id-keyed access dominates.
         */
    }
}
