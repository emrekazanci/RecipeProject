package com.emre.service;

import com.emre.repository.IAddressRepository;
import com.emre.repository.entity.Address;
import com.emre.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends ServiceManager<Address, Long> {
    private final IAddressRepository addressRepository;
    public AddressService(IAddressRepository addressRepository) {
        super(addressRepository);
        this.addressRepository = addressRepository;
    }

}
