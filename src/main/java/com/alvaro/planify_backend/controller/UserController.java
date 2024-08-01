package com.alvaro.planify_backend.controller;

import com.alvaro.planify_backend.entity.User;
import com.alvaro.planify_backend.repository.UserRepository;
import com.alvaro.planify_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/searchById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User User = userService.getUserById(id);
        if (User != null) {
            return ResponseEntity.ok(User);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByEmail/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/search/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> Users = userService.getAllUsers();
        return ResponseEntity.ok(Users);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // Verifica si el usuario ya existe
        User existingUser = userService.getUserByEmail(user.getEmail());

        if (existingUser != null) {
            // El usuario ya existe
            return ResponseEntity.status(HttpStatus.OK).body("User already exists");
        } else {
            // Crea el nuevo usuario
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
