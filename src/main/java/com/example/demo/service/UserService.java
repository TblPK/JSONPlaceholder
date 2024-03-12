package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling user-related operations.
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UserNotFoundException("User not found by username"));
    }

    /**
     * Retrieves all users.
     *
     * @return List of User entities.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     * @param userId The ID of the user to retrieve.
     * @return The User entity.
     */
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new UserNotFoundException("User not found by userID"));
    }

    /**
     * Creates a new user.
     * @param user The User entity to create.
     * @return The created User entity.
     */
    public User create(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
            throw new UserAlreadyExistsException("User Already exists");
        });

        user.setRoles(getRole(user.getRoles().getId()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    /**
     * Updates an existing user.
     * @param id   The ID of the user to update.
     * @param user The updated User entity.
     * @return The updated User entity.
     */
    public User update(Long id, User user) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not exist"));

        user.setId(id);
        user.setRoles(getRole(user.getRoles().getId()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    /**
     * Deletes a user by their ID.
     * @param userId The ID of the user to delete.
     */
    public void delete(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException("User not found by username");
        }
    }

    /**
     * Retrieves a role by its ID.
     * @param id The ID of the role to retrieve.
     * @return The Role entity.
     * @throws RoleNotFoundException If the role with the specified ID does not exist.
     */
    private Role getRole(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElseThrow(() -> new RoleNotFoundException("Role not exists"));
    }
}
