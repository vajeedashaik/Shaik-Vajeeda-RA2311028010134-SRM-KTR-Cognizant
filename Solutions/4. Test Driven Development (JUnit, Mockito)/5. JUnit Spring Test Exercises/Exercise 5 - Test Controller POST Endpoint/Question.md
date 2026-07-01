Exercise 5: Test Controller POST Endpoint 
Task: Test a POST endpoint that creates a user. 
Controller: 
@PostMapping 
public ResponseEntity<User> createUser(@RequestBody User user) { 
return ResponseEntity.ok(userService.saveUser(user)); 
} 
Test: 
Write code for this.