package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws NullPointerException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new NullPointerException("User not found");
        }

        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() -> new NullPointerException("User not found"));
    }

    public User create(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        Optional<Role> role = roleRepository.findById(user.getRoles().getId());

        if (userFromDB != null) {
            throw new NullPointerException("User Already exists");
        }
        if (role.isEmpty()) {
            throw new NullPointerException("Role not exists");
        }

        user.setRoles(role.get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User update(Long id, User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {

        }

        user.setRoles(new Role());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public void delete(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
        }
    }

}