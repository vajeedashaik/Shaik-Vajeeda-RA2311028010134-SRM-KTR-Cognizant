package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Spring Test - Exercise 2: Mocking a Repository in a Service Test
// Spring Test - Exercise 6: Test Service Exception Handling
// Spring Test - Exercise 7: Test Custom Repository Query
// Mock Dependencies - Exercise 2: Mocking a Repository in a Service Test
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // Exercise 2: getUserById - happy path
    @Test
    void testGetUserById_returnsUser() {
        User mockUser = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
        verify(userRepository).findById(1L);
    }

    // Exercise 6: getUserById - user not found throws exception
    @Test
    void testGetUserById_notFound_throwsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.getUserById(99L));
        verify(userRepository).findById(99L);
    }

    // saveUser
    @Test
    void testSaveUser() {
        User user = new User(null, "Bob");
        User saved = new User(2L, "Bob");
        when(userRepository.save(user)).thenReturn(saved);

        User result = userService.saveUser(user);

        assertEquals(2L, result.getId());
        assertEquals("Bob", result.getName());
        verify(userRepository).save(user);
    }

    // Exercise 7: findByName custom query
    @Test
    void testFindByName_returnsMatchingUsers() {
        List<User> users = List.of(new User(1L, "Alice"), new User(3L, "Alice"));
        when(userRepository.findByName("Alice")).thenReturn(users);

        List<User> result = userService.findByName("Alice");

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(u -> "Alice".equals(u.getName())));
        verify(userRepository).findByName("Alice");
    }
}
