Exercise 8: Test Controller Exception Handling 
Task: Add and test a @ControllerAdvice for handling exceptions. 
Exception Handler: 
@ControllerAdvice 
public class GlobalExceptionHandler { 
@ExceptionHandler(NoSuchElementException.class) 
public ResponseEntity<String> handleNotFound(NoSuchElementException ex) { 
return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"); 
} 
} 
Test: 
Write code for this.