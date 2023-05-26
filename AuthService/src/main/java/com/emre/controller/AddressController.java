package com.emre.controller;

import com.emre.dto.request.UpdateUserFromUserToAuth;
import com.emre.repository.entity.Address;
import com.emre.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.emre.constant.EndPoints.*;

@RestController
@RequestMapping(ADDRESS)
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Address>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }
}
