package com.restaurantapp.restapp.controller;

import com.restaurantapp.restapp.model.dto.AddressDto;
import com.restaurantapp.restapp.model.request.create.CreateAddressRequest;
import com.restaurantapp.restapp.model.request.update.UpdateAddressRequest;
import com.restaurantapp.restapp.service.impl.AddressServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    private final AddressServiceImpl addressServiceImpl;

    public AddressController(@Lazy AddressServiceImpl addressServiceImpl) {
        this.addressServiceImpl = addressServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        return new ResponseEntity<>(addressServiceImpl.getAllAddresses(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable long id) {

        return new ResponseEntity<>(addressServiceImpl.getAddress(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody CreateAddressRequest request) {

        return new ResponseEntity<>(addressServiceImpl.createAddress(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody UpdateAddressRequest request,@PathVariable long id) {

        return new ResponseEntity<>(addressServiceImpl.updateAddress(request,id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable long id) {

        addressServiceImpl.deleteAddress(id);
        return ResponseEntity.ok().build();
    }

}
