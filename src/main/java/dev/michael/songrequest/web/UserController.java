package dev.michael.songrequest.web;

import dev.michael.songrequest.document.User;
import dev.michael.songrequest.dto.UserDTO;
import dev.michael.songrequest.repository.UserRepository;
import dev.michael.songrequest.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserManager userManager;

    @GetMapping("/{id}")
    @PreAuthorize("#user.id == #id")
    public ResponseEntity user(@AuthenticationPrincipal User user, @PathVariable String id) {
        return ResponseEntity.ok(UserDTO.from(userRepository.findById(id).orElseThrow()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#user.id == #id")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userManager.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
