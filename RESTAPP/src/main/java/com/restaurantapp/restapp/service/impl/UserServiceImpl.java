package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.UserNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.toentity.CreateUserRequestConverter;
import com.restaurantapp.restapp.model.converter.update.toentity.UpdateUserRequestConverter;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;
import com.restaurantapp.restapp.repository.UserRepository;
import com.restaurantapp.restapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEntityToDtoConverter userEntityToDtoConverter;
    private final CreateUserRequestConverter createUserRequestConverter;
    private final UpdateUserRequestConverter updateUserRequestConverter;

    public UserServiceImpl(UserRepository userRepository, UserEntityToDtoConverter userEntityToDtoConverter, CreateUserRequestConverter createUserRequestConverter, UpdateUserRequestConverter updateUserRequestConverter) {
        this.userRepository = userRepository;
        this.userEntityToDtoConverter = userEntityToDtoConverter;
        this.createUserRequestConverter = createUserRequestConverter;
        this.updateUserRequestConverter = updateUserRequestConverter;
    }

    public UserDto createUser(CreateUserRequest request) {

        return userEntityToDtoConverter.convert(userRepository.save(createUserRequestConverter.convert(request)));
    }

    public List<UserDto> getAllUsers() {

        return userRepository.findAll().stream().map(userEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUser(long id) {

        return userEntityToDtoConverter.convert(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    public UserDto getUserByName(String name) {

        return userEntityToDtoConverter.convert(userRepository.findByName(name));
    }

    public UserDto updateUser(UpdateUserRequest request, long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        User updatedFields = updateUserRequestConverter.convert(request);

        user.setAddressList(updatedFields.getAddressList());
        user.setEmail(updatedFields.getEmail());
        user.setPassword(updatedFields.getPassword());
        user.setName(updatedFields.getName());
        user.setRoles(updatedFields.getRoles());

        return userEntityToDtoConverter.convert(userRepository.save(user));
    }

    public void deleteUser(long id) {

        userRepository.deleteById(id);
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
