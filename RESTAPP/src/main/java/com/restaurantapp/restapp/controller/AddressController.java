package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.service.impl.AddressServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressServiceImpl addressService;

    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody CreateAddressRequest request) {
        return new ResponseEntity<>(addressService.createAddress(request), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AddressDto>> getUserAddresses(@PathVariable long userId) {
        return new ResponseEntity<>(addressService.getUserAdresses(userId), HttpStatus.OK);
    }
}
