Exercise 2: Mocking a Repository in a Service Test 
Task: Test a service that uses a repository to fetch data. 
Entity: 
 
@Entity 
public class User { 
    @Id 
    private Long id; 
    private String name; 
    // getters and setters 
} 
 
Repository: 
 
public interface UserRepository extends JpaRepository<User, Long> { 
} 
 
Service: 
 
@Service 
public class UserService { 
    @Autowired 
    private UserRepository userRepository; 
 
    public User getUserById(Long id) { 
        return userRepository.findById(id).orElse(null); 
    } 
} 
 
Test: 
 
Write code for this. 