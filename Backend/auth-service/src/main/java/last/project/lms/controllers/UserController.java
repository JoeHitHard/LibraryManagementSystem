package last.project.lms.controllers;

import last.project.lms.exceptions.InvalidAuthException;
import last.project.lms.service.AuthService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    AuthService authService;

    @GetMapping("")
    public ResponseEntity getUser(@RequestHeader("Authorization") @DefaultValue("XXX") String authorizationHeader) throws InvalidAuthException {
        return ResponseEntity.ok(authService.getUsersResponseEntity(authorizationHeader));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody String userDataString, @RequestHeader("Authorization") @DefaultValue("XXX") String authorizationHeader) throws InvalidAuthException {
        JSONObject userDetails = new JSONObject(userDataString);
        return ResponseEntity.ok(authService.login(userDetails.getString("userId"), userDetails.getString("password")));
    }
}
