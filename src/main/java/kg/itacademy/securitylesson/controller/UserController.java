package kg.itacademy.securitylesson.controller;

import kg.itacademy.securitylesson.entity.User;
import kg.itacademy.securitylesson.model.ResponseMessage;
import kg.itacademy.securitylesson.model.UserAuthModel;
import kg.itacademy.securitylesson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/get-current")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PostMapping("/sing-in")
    public ResponseEntity<String> signIn(@RequestBody UserAuthModel userAuthModel) {
        return ResponseEntity.ok(userService.getAuthorizationToken(userAuthModel));
    }


}
