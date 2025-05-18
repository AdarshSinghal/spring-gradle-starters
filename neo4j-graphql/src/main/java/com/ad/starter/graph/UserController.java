package com.ad.starter.graph;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    // Get all users
    @QueryMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create 3 users
    @MutationMapping
    public List<User> createUsers() {
        User user1 = new User(null, "Alice", null);   // Alice -> X
        User user2 = new User(null, "Bob", user1);          // Bob -> Alice
        User user3 = new User(null, "Charlie", user2);      // Charlie -> Bob

        return userRepository.saveAll(List.of(user1, user2, user3));
    }

    @MutationMapping
    public String deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return "All users deleted successfully.";
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete all users: " + e.getMessage());
        }
    }

    @MutationMapping
    public String deleteUserById(@Argument Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return "User with id " + id + " deleted successfully.";
            } else {
                throw new RuntimeException("User with id " + id + " not found.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user with id " + id + ": " + e.getMessage());
        }
    }
}
