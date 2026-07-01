Exercise 3: Testing a REST Controller with MockMvc 
Task: Test a controller endpoint that returns a user. 
Controller: 
 
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
 
Test: 
 
Write code for this.