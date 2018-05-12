package com.team.mybook.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.team.mybook.data.entity.Statistic;
import com.team.mybook.data.entity.User;
import com.team.mybook.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public void addNewUser (@RequestBody User requestUser){
        User user = new User(requestUser.getName(), requestUser.getPassword(), requestUser.getEmail(),
                                requestUser.getGender(), requestUser.getAge());
        userRepository.save(user);
    }

    @GetMapping(path="/{userName}")
    public @ResponseBody User getUser(HttpServletResponse response, @PathVariable String userName) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return userRepository.findByName(userName);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        return userRepository.findAll();
    }

    @DeleteMapping("/delete/{userName}")
    public void deleteUser(@PathVariable String userName) {
        userRepository.deleteUserByName(userName);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update")
    public void updateUser (@RequestBody User requestUser){
        User user = userRepository.findUserByUserID(requestUser.getUserID());

        user.setName(requestUser.getName());
        user.setPassword(requestUser.getPassword());
        user.setAge(requestUser.getAge());
        user.setEmail(requestUser.getEmail());
        user.setGender(requestUser.getGender());

        userRepository.save(user);
    }
}
