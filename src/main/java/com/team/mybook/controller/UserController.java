package com.team.mybook.controller;

import com.team.mybook.data.entity.User;
import com.team.mybook.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewUser (@RequestBody User requestUser){
        User user = new User(requestUser.getName(), requestUser.getPassword(), requestUser.getEmail(),
                                requestUser.getGender(), requestUser.getAge());
        userRepository.save(user);
    }

    @GetMapping(path="/{userName}")
    public @ResponseBody User getUserByName(HttpServletResponse response, @PathVariable String userName) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return userRepository.findByName(userName);
    }

    @GetMapping(path="/id/{userID}")
    public @ResponseBody User getUserById(HttpServletResponse response, @PathVariable long userID) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return userRepository.findById(userID).get();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return userRepository.findAll();
    }

    @DeleteMapping("/delete/{userID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long userID) {
        userRepository.deleteById(userID);
    }
}
