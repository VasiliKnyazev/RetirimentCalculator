package com.luxoft.rcalculator.controller.rest;

import com.luxoft.rcalculator.model.User;
import com.luxoft.rcalculator.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/rest")
    public ModelAndView restPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("restPage");
        return modelAndView;
    }

    @GetMapping("/rest/users")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/rest/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/rest/users/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable int userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/rest/users")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/rest/users")
    public ResponseEntity<User> editUser(@Valid @RequestBody User user) {
        userService.edit(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
