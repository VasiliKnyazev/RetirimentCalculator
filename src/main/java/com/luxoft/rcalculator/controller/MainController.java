package com.luxoft.rcalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.luxoft.rcalculator.model.Role;
import com.luxoft.rcalculator.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import com.luxoft.rcalculator.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public ModelAndView userPage(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userPage");
        return modelAndView;
    }

    @GetMapping(value = "/admin")
    public String adminPage(Model model) {
        List<User> users = userService.findAll();
        users.sort(Comparator.comparing(User::getId));
        model.addAttribute("userList", users);
        return "adminPage";
    }

    @PostMapping(value = "/admin/add")
    public ModelAndView addUser(@ModelAttribute("user") User user, @RequestParam("role") String role) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        Set<Role> roles = new HashSet<>();
        roles.add(userService.getRole(role));
        user.setRoles(roles);
        userService.add(user);
        return modelAndView;
    }

    @PostMapping(value = "/admin/edit/{id}+{login}+{name}+{pass}+{role}")
    public ModelAndView editUser(@PathVariable("id") String id, @PathVariable("login") String login, @PathVariable("name") String name, @PathVariable("pass") String pass, @PathVariable("role") String role) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        int uid = Integer.parseInt(id);
        if(pass.contains("|")){
            pass = pass.replace("|", "/");
        }
        User editUser = new User(uid, name, login, pass);
        Set<Role> roles = new HashSet<>();
        roles.add(userService.getRole(role));
        editUser.setRoles(roles);
        userService.edit(editUser);
        return modelAndView;
    }

    @PostMapping(value="/admin/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        User user = userService.findById(id);
        userService.deleteById(user.getId());
        return modelAndView;
    }

    @RequestMapping(value = { "/", "/login" }, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loginPost(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        if(principal != null) {
            modelAndView.setViewName("redirect:/user");
            return modelAndView;
        }

        if (error != null) {
            modelAndView.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            modelAndView.addObject("msg", "You've been logged out successfully.");
        }

        modelAndView.setViewName("loginPage");
        return modelAndView;
    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid login and password!";
        } else if (exception instanceof LockedException) {
            error = "Invalid login and password!";
        } else {
            error = "Invalid login and password!";
        }
        return error;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                UserDetails userDetail = (UserDetails) auth.getPrincipal();
                System.out.println(userDetail);
                model.addObject("login", userDetail.getUsername());
            }
        } catch (ClassCastException e) {
            String name = auth.getName();
            System.out.println(name);
            model.addObject("login", name);
        }
        model.setViewName("errorPage");
        return model;
    }
}
