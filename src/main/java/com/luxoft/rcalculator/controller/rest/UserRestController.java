package com.luxoft.rcalculator.controller.rest;

import com.luxoft.rcalculator.model.User;
import com.luxoft.rcalculator.model.dto.RetirementResultDTO;
import com.luxoft.rcalculator.service.UserService;
import com.luxoft.rcalculator.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;
    private MailService mailService;

    @Autowired
    public UserRestController(UserService userService, MailService mailService) {
        this.userService = userService;
        this.mailService = mailService;
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

    @GetMapping("/rest/users/calculate/{userAge}+{userRetirementAge}+{userEmail}")
    public ResponseEntity<RetirementResultDTO> calculateUserCanRetirementYear(@PathVariable(value = "userAge", required = false) String userAge,
                                                                              @PathVariable(value = "userRetirementAge", required = false) String userRetirementAge,
                                                                              @PathVariable(value = "userEmail", required = false) String userEmail) {
        RetirementResultDTO retirementResultDTO = new RetirementResultDTO();
        try {
            retirementResultDTO = userService.calculateUserCanRetirementYear(Integer.parseInt(userAge), Integer.parseInt(userRetirementAge));
            mailService.sendCanRetirementYearMessage("Retirement Calculator Project", "rcproject@mail.ru", userEmail, retirementResultDTO.getCanRetireMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return new ResponseEntity<>(retirementResultDTO, HttpStatus.OK);
        }
    }
}
