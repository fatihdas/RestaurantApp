package com.restaurantapp.restapp.service;

import com.restaurantapp.restapp.model.Address;
import com.restaurantapp.restapp.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;


    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address save(Address address){

        return addressRepository.save(address);
    }

    public List<Address> getAll(){

        return addressRepository.findAll();
    }

    public Address getById(long id){

        return addressRepository.findById(id).orElse(null);
    }

    public Address update(Address address, long id){

        Address address1 = addressRepository.findById(id).orElse(null);

        address1.setBranch(address.getBranch());
        address1.setCity(address.getCity());
        address1.setId(address.getId());
        address1.setContent(address.getContent());
        address1.setCounty(address.getCounty());
        address1.setUser(address.getUser());
        addressRepository.save(address1);

        return address1;

    }

    public Address delete(long id){

        addressRepository.deleteById(id);

        return addressRepository.findById(id).orElse(null);
    }
}
