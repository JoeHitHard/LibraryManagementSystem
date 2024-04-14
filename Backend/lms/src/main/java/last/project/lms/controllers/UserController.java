package last.project.lms.controllers;

import last.project.lms.repo.UserRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public ResponseEntity getUser() {
        return ResponseEntity.ok(userRepo.findAll());
    }
}
