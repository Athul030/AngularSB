package com.example.backendspringang.controller;

import com.example.backendspringang.entity.User;
import com.example.backendspringang.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        List<User> user= userService.getAllUsers();
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return userService.getUser(userName);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{userName}")
    public void deleteUser(@PathVariable("userName") String userName){
        userService.deleteUser(userName);
    }
}
