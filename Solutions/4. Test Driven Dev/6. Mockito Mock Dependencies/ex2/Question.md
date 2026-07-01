Exercise 2: Mocking a Repository in a Service Test 
**Task:** Write a unit test for a Spring service that uses a repository to fetch data. Mock the 
repository dependency using Mockito. 
### Step-by-Step Solution: 
1. **Create the User Entity:** 
@Entity 
public class User { 
    @Id 
    private Long id; 
    private String name; 
    // getters and setters 
} 
2. **Create the UserRepository:** 
public interface UserRepository extends JpaRepository<User, Long> { 
} 
3. **Create the UserService:** 
@Service 
public class UserService { 
    @Autowired 
    private UserRepository userRepository; 
    public User getUserById(Long id) { 
        return userRepository.findById(id).orElse(null); 
    } 
} 
4. **Create the UserServiceTest:** 
Write code for this. 