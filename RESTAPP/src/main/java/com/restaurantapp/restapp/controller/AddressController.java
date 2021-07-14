package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.entity.Addresses;
import com.restaurantapp.restapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Addresses>> getAll(){
        return new ResponseEntity(addressService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Addresses> getAddress(@PathVariable long id){

        return new ResponseEntity<Addresses>(addressService.getById(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Addresses> addAddress(@RequestBody Addresses addresses){

        return new ResponseEntity<Addresses>(addressService.saveAddress(),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Addresses> updateAddress(@RequestBody Addresses addresses){

        return new ResponseEntity<Addresses>(addressService.updateAddress(),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Addresses> deleteAddress(@RequestBody long id){

        return new ResponseEntity<Addresses>(addressService.deleteById(),HttpStatus.OK);
    }

}
