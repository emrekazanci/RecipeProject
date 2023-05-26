package com.emre.mapper;

import com.emre.dto.request.UpdateUserFromUserToAuth;
import com.emre.repository.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-23T23:47:47+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class IAddressMapperImpl implements IAddressMapper {

    @Override
    public Address updateUserFromUserToAddress(UpdateUserFromUserToAuth dto, Address address) {
        if ( dto == null ) {
            return address;
        }

        if ( dto.getStreet() != null ) {
            address.setStreet( dto.getStreet() );
        }
        if ( dto.getNeighborhood() != null ) {
            address.setNeighborhood( dto.getNeighborhood() );
        }
        if ( dto.getDistrict() != null ) {
            address.setDistrict( dto.getDistrict() );
        }
        if ( dto.getProvince() != null ) {
            address.setProvince( dto.getProvince() );
        }
        if ( dto.getCountry() != null ) {
            address.setCountry( dto.getCountry() );
        }
        if ( dto.getBuildingNumber() != null ) {
            address.setBuildingNumber( dto.getBuildingNumber() );
        }
        if ( dto.getApartmentNumber() != null ) {
            address.setApartmentNumber( dto.getApartmentNumber() );
        }
        if ( dto.getZipCode() != null ) {
            address.setZipCode( dto.getZipCode() );
        }

        return address;
    }

    @Override
    public Address saveAddress(UpdateUserFromUserToAuth dto) {
        if ( dto == null ) {
            return null;
        }

        Address.AddressBuilder<?, ?> address = Address.builder();

        address.street( dto.getStreet() );
        address.neighborhood( dto.getNeighborhood() );
        address.district( dto.getDistrict() );
        address.province( dto.getProvince() );
        address.country( dto.getCountry() );
        address.buildingNumber( dto.getBuildingNumber() );
        address.apartmentNumber( dto.getApartmentNumber() );
        address.zipCode( dto.getZipCode() );

        return address.build();
    }
}
