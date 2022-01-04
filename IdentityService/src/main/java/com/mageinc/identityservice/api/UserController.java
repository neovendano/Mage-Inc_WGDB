package com.mageinc.identityservice.api;


import com.mageinc.identityservice.api.dto.UserDto;
import com.mageinc.identityservice.repo.model.User;
import com.mageinc.identityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> index(){
        final List<User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable long id){
        try {
            final User user = userService.fetchById(id);
            return ResponseEntity.ok(user);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fetchName/{id}")
    public ResponseEntity<String> throwName(@PathVariable long id){
        try {
            final String user = userService.fetchNameById(id);
            return ResponseEntity.ok(user);
        } catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User newUser){
        final long id = userService.create(newUser);
        String location = String.format("/users/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody User newUser){
        try{
            userService.update(id, newUser);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

