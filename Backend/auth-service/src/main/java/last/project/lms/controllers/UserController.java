package last.project.lms.controllers;

import last.project.lms.entities.Tokens;
import last.project.lms.entities.Users;
import last.project.lms.exceptions.InvalidAuthException;
import last.project.lms.repo.TokenRepo;
import last.project.lms.repo.UserRepo;
import last.project.lms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    AuthService authService;

    @GetMapping("")
    public ResponseEntity getUser(@RequestHeader("Authorization") @DefaultValue("XXX") String authorizationHeader) throws InvalidAuthException {
        return ResponseEntity.ok(authService.getUsersResponseEntity(authorizationHeader));
    }
}
