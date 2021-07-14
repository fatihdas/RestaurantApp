package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.Address;
import com.restaurantapp.restapp.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        return new ResponseEntity(addressService.getAll(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> getAddress(@PathVariable long id){

        return new ResponseEntity<Address>(addressService.getById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address){

        return new ResponseEntity<Address>(addressService.save(address),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable long id){

        return new ResponseEntity<Address>(addressService.update(address, id),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Address> deleteAddress(@RequestBody long id){

        return new ResponseEntity<Address>(addressService.delete(id),HttpStatus.OK);
    }

}
