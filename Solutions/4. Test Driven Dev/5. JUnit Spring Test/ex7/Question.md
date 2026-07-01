Exercise 7: Test Custom Repository Query 
Task: Add and test a custom query method. 
Repository: 
public interface UserRepository extends JpaRepository<User, Long> { 
List<User> findByName(String name); 
} 
Test: 
Write code for this.