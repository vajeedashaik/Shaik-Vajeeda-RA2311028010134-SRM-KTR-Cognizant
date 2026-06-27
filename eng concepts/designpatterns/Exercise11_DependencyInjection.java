import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Domain model
class Customer {
    final String id;
    final String name;
    final String email;

    Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }
}

// Repository interface â€” abstraction the service depends on
interface CustomerRepository {
    Optional<Customer> findCustomerById(String id);
    void save(Customer customer);
}

// Concrete repository implementation (in-memory)
class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<String, Customer> db = new HashMap<>();

    CustomerRepositoryImpl() {
        // Seed data
        db.put("C001", new Customer("C001", "Alice Smith", "alice@example.com"));
        db.put("C002", new Customer("C002", "Bob Jones",  "bob@example.com"));
    }

    public Optional<Customer> findCustomerById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    public void save(Customer customer) {
        db.put(customer.id, customer);
        System.out.println("Saved: " + customer);
    }
}

// Service: receives its dependency via constructor injection
class CustomerService {
    private final CustomerRepository repository;

    // Constructor injection â€” dependency explicit, testable, no hidden coupling
    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer findById(String id) {
        return repository.findCustomerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
    }

    public void registerCustomer(String id, String name, String email) {
        repository.save(new Customer(id, name, email));
    }
}

public class Exercise11_DependencyInjection {
    public static void main(String[] args) {
        // Wire dependencies manually (no framework needed for demonstration)
        CustomerRepository repo = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repo);

        System.out.println("=== Find existing customers ===");
        System.out.println(service.findById("C001"));
        System.out.println(service.findById("C002"));

        System.out.println("\n=== Register new customer ===");
        service.registerCustomer("C003", "Charlie Brown", "charlie@example.com");
        System.out.println(service.findById("C003"));

        System.out.println("\n=== Not found ===");
        try {
            service.findById("C999");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
