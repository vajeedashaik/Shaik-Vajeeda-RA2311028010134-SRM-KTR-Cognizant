Exercise 1: Mocking a Service Dependency in a Controller Test 
**Task:** Write a unit test for a Spring controller that uses a service to fetch data. Mock the 
service dependency using Mockito. 
### Step-by-Step Solution: 
1. **Create the User Entity:** 
@Entity 
public class User { 
    @Id 
    private Long id; 
    private String name; 
    // getters and setters 
} 
2. **Create the UserService:** 
@Service 
public class UserService { 
    @Autowired 
    private UserRepository userRepository; 
    public User getUserById(Long id) { 
        return userRepository.findById(id).orElse(null); 
    } 
} 
3. **Create the UserController:** 
@RestController 
@RequestMapping("/users") 
public class UserController { 
    @Autowired 
    private UserService userService; 
    @GetMapping("/{id}") 
    public ResponseEntity<User> getUser(@PathVariable Long id) { 
        return ResponseEntity.ok(userService.getUserById(id)); 
    } 
} 
4. **Create the UserControllerTest:** 
Write code for this.