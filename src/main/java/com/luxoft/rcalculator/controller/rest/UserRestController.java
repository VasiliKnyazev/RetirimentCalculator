package com.luxoft.rcalculator.controller.rest;

import com.luxoft.rcalculator.model.User;
import com.luxoft.rcalculator.model.dto.RetirementResultDTO;
import com.luxoft.rcalculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;

    @Autowired
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
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/rest/users")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        userService.edit(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/rest/users/calculate/{userAge}+{userRetirementAge}")
    public ResponseEntity<RetirementResultDTO> calculateUserCanRetirementYear(@PathVariable(value = "userAge", required = false) String userAge,
                                                                              @PathVariable(value = "userRetirementAge", required = false) String userRetirementAge) {
        RetirementResultDTO retirementResultDTO = new RetirementResultDTO(0, 0, 0);
        try {
            retirementResultDTO = userService.calculateUserCanRetirementYear(Integer.parseInt(userAge), Integer.parseInt(userRetirementAge));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return new ResponseEntity<>(retirementResultDTO, HttpStatus.OK);
        }
    }
}
