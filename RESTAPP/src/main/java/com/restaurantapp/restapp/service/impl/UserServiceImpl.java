package com.restaurantapp.restapp.service.impl;

import com.restaurantapp.restapp.exception.InvalidOwnerException;
import com.restaurantapp.restapp.exception.UserNotFoundException;
import com.restaurantapp.restapp.model.converter.create.request.CreateUserRequestConverter;
import com.restaurantapp.restapp.model.converter.entity.todto.UserEntityToDtoConverter;
import com.restaurantapp.restapp.model.dto.UserDto;
import com.restaurantapp.restapp.model.entity.User;
import com.restaurantapp.restapp.model.entity.enumerated.RolesEnumConverter;
import com.restaurantapp.restapp.model.entity.enumerated.UserRoles;
import com.restaurantapp.restapp.model.request.create.CreateUserRequest;
import com.restaurantapp.restapp.model.request.update.UpdateUserRequest;
import com.restaurantapp.restapp.repository.UserRepository;
import com.restaurantapp.restapp.security.JwtUtil;
import com.restaurantapp.restapp.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserEntityToDtoConverter userEntityToDtoConverter;
    private final CreateUserRequestConverter createUserRequestConverter;
    private final RolesEnumConverter rolesEnumConverter;
    private final HttpServletRequest httpServletRequest;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository,
                           UserEntityToDtoConverter userEntityToDtoConverter,
                           CreateUserRequestConverter createUserRequestConverter,
                           RolesEnumConverter rolesEnumConverter,
                           HttpServletRequest httpServletRequest, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userEntityToDtoConverter = userEntityToDtoConverter;
        this.createUserRequestConverter = createUserRequestConverter;
        this.rolesEnumConverter = rolesEnumConverter;
        this.httpServletRequest = httpServletRequest;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);

    }

    @Override
    @Transactional
    public UserDto createUser(CreateUserRequest request) {

        User user = userRepository.save(createUserRequestConverter.convert(request));

//        request.getCreateAddressRequest().setUserId(user.getId());
//        addressService.createAddress(request.getCreateAddressRequest());

        return userEntityToDtoConverter.convert(user);
    }

    @Override
    @Transactional
    public UserDto updateUserRole(String role, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        if (user.getRoles().contains(rolesEnumConverter.convertToDatabaseColumn(role))) {
            throw new IllegalArgumentException("the role already exists:" + role);
        }
        List<UserRoles> userRolesList = new ArrayList<>(user.getRoles());
        userRolesList.add(rolesEnumConverter.convertToDatabaseColumn(role));
        user.setRoles(userRolesList);
        return userEntityToDtoConverter.convert(userRepository.save(user));
    }

    public List<UserDto> getAllUsers() {

        List<User> userList = userRepository.findAll();
        return userList.stream().map(userEntityToDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userEntityToDtoConverter.convert(user);
    }

    public UserDto getUserDtoByName(String name) {

        User user = userRepository.findByName(name);
        return userEntityToDtoConverter.convert(user);
    }

    public User getUserByName(String name) {

        return userRepository.findByName(name);
    }

    public String updateUser(UpdateUserRequest request, long id) {

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return "User has been updated! id:" + id;
    }

    public void deleteUser(long id) {

        userRepository.deleteById(id);
    }

    @Override
    public boolean hasRole(UserRoles userRoles) {
        String token = httpServletRequest.getHeader("Authorization");
        String username = jwtUtil.extractUsername(token);
        User user = userRepository.findByName(username);
        return user.getRoles().contains(userRoles);
    }

    @Override
    public boolean isOwner(long ownerId) {
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) {
            String username = jwtUtil.extractUsername(token);
            User user = userRepository.findByName(username);
            if (Objects.equals(user.getId(), ownerId)) {
                return true;
            } else {
                throw new InvalidOwnerException("Invalid user request!");
            }
        } else {
            throw new RuntimeException("token is null");
        }
    }

}
