package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurantapp.restapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserById(long id){
        return userRepository.getById(id);
    }
    public User getUserByName(String name){
        return userRepository.findByName(name);
    }
    public String deleteUser(long id){
        userRepository.deleteById(id);
        return "user removed" + id;
    }
    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }
}
