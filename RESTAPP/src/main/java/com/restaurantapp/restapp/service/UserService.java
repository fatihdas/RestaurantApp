package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.UserNotFoundException;
import com.restaurantapp.restapp.model.User;
import org.springframework.stereotype.Service;
import com.restaurantapp.restapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    public List<User> getAll() {

        return userRepository.findAll();
    }

    public User getById(long id) {

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByName(String name) {

        return userRepository.findByName(name);
    }

    public User update(User user) {

        userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(user.getId()));
        return userRepository.save(user);
    }

    public String delete(long id) {

        userRepository.deleteById(id);
        return "SUCCESS";
    }


//    @Override
//    public User save(User user) {
//        return null;
//    }
//
//    @Override
//    public User delete(long id) {
//        return null;
//    }
//
//    @Override
//    public List<User> getAll() {
//        return null;
//    }
//
//    @Override
//    public User getById(long id) {
//        return null;
//    }
//
//    @Override
//    public User update(User user) {
//        return null;
//    }
//
//    public User getUserByName(String name){
//        return userRepository.findByName(name);
//    }


//    public User saveUser(User user){
//        return userRepository.save(user);
//    }
//
//    public List<User> getAllUsers(){
//        return userRepository.findAll();
//    }
//    public User getUserById(long id){
//        return userRepository.getById(id);
//    }
//
//    public String deleteUser(long id){
//        userRepository.deleteById(id);
//        return "user removed" + id;
//    }
//    public User updateUser(User user){
//        User existingUser = userRepository.findById(user.getId()).orElse(null);
//        existingUser.setName(user.getName());
//        existingUser.setPassword(user.getPassword());
//        return userRepository.save(existingUser);
//    }
}
