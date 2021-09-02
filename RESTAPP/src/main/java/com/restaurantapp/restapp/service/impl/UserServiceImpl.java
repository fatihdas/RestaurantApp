package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.UserNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateAddressRequestConverter;
import com.restaurantapp.restapp.model.converter.create.request.CreateUserRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.Address;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.entity.enumerated.Roles;
import com.restaurantapp.restapp.model.entity.enumerated.RolesEnumConverter;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;
import com.restaurantapp.restapp.repository.UserRepository;
import com.restaurantapp.restapp.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final AddressServiceImpl addressService;
    private final UserEntityToDtoConverter userEntityToDtoConverter;
    private final CreateUserRequestConverter createUserRequestConverter;
    private final CreateAddressRequestConverter createAddressRequestConverter;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RolesEnumConverter rolesEnumConverter;

    public UserServiceImpl(UserRepository userRepository, AddressServiceImpl addressService,
                           UserEntityToDtoConverter userEntityToDtoConverter,
                           CreateUserRequestConverter createUserRequestConverter,
                           CreateAddressRequestConverter createAddressRequestConverter,
                           BCryptPasswordEncoder bCryptPasswordEncoder, RolesEnumConverter rolesEnumConverter) {
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.userEntityToDtoConverter = userEntityToDtoConverter;
        this.createUserRequestConverter = createUserRequestConverter;
        this.createAddressRequestConverter = createAddressRequestConverter;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.rolesEnumConverter = rolesEnumConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        });
        return new org.springframework.security.core.userdetails.User(user.getName(), bCryptPasswordEncoder.encode(user.getPassword()), authorities);

    }

    public UserDto createUser(CreateUserRequest request) {

        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        return userEntityToDtoConverter.convert(userRepository.save(createUserRequestConverter.convert(request)));
    }

    @Override
    public UserDto updateUserRole(String role, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        if (user.getRoles().contains(rolesEnumConverter.convertToDatabaseColumn(role))) {
            throw new IllegalArgumentException("the role already exists:" + role);
        }
        List<Roles> rolesList = user.getRoles();
        rolesList.add(rolesEnumConverter.convertToDatabaseColumn(role));
        user.setRoles(rolesList);
        return userEntityToDtoConverter.convert(user);
    }

    public User getUserById(long id) {

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
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

    public String updateUser(UpdateUserRequest request, long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return "User has been updated! id:" + id;
    }

    public void deleteUser(long id) {

        userRepository.deleteById(id);
    }

    public UserDto addAddress(CreateAddressRequest request, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        addressService.createAddress(request);
        List<Address> addressList = user.getAddressList();
        addressList.add(createAddressRequestConverter.convert(request));
        user.setAddressList(addressList);
        return userEntityToDtoConverter.convert(userRepository.save(user));
    }


    public List<AddressDto> getUserAdresses(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        return userEntityToDtoConverter.convert(user).getAddressDtoList();
    }


}
