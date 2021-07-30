package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.exception.AddressNotFoundException;
import com.restaurantapp.restapp.model.Address;
import com.restaurantapp.restapp.model.City;
import com.restaurantapp.restapp.model.County;
import com.restaurantapp.restapp.model.User;
import com.restaurantapp.restapp.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address address) {

        return addressRepository.save(address);
    }

    public List<Address> getAll() {

        return addressRepository.findAll();
    }

    public Address getById(long id) {

        return addressRepository.findById(id).orElseThrow(()-> new AddressNotFoundException(id));
    }

    public Address update(Address address) {

        addressRepository.findById(address.getId()).orElseThrow(() -> new AddressNotFoundException(address.getId()));
        return addressRepository.save(address);


    }

    public String delete(long id) {

        addressRepository.deleteById(id);
        return "SUCCESS";
    }

}
