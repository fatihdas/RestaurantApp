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

    public User save(User user){

        return userRepository.save(user);
    }

    public List<User> getAll(){

        return userRepository.findAll();
    }

    public User getById(long id){

        return userRepository.findById(id).orElse(null);
    }

    public User getUserByName(String name){

        return userRepository.findByName(name);
    }

    public User update(User user, long id){

        User user1 = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id:" + id));

        user1.setId(user.getId());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setRoles(user.getRoles());
        user1.setAddressList(user.getAddressList());
        user1.setRestaurants(user.getRestaurants());
        user1.setCommentList(user.getCommentList());

        userRepository.save(user1);

        return user1;


    }

    public User delete(long id){

         userRepository.deleteById(id);

         return userRepository.findById(id).orElse(null);
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
