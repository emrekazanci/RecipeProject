package com.emre.mapper;

import com.emre.dto.request.CreateUserForAuthToUserDto;
import com.emre.dto.request.UpdateUserFromUserToAuth;
import com.emre.dto.request.UpdateUserRequestDto;
import com.emre.repository.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-25T23:46:01+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User createUserAuthServiceToUserService(CreateUserForAuthToUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.authId( dto.getAuthId() );
        user.name( dto.getName() );
        user.surname( dto.getSurname() );
        user.email( dto.getEmail() );
        user.password( dto.getPassword() );
        user.username( dto.getUsername() );

        return user.build();
    }

    @Override
    public User fromUpdateUserDtoToUser(UpdateUserRequestDto dto, User user) {
        if ( dto == null ) {
            return user;
        }

        if ( dto.getName() != null ) {
            user.setName( dto.getName() );
        }
        if ( dto.getSurname() != null ) {
            user.setSurname( dto.getSurname() );
        }
        if ( dto.getAvatar() != null ) {
            user.setAvatar( dto.getAvatar() );
        }
        if ( dto.getStreet() != null ) {
            user.setStreet( dto.getStreet() );
        }
        if ( dto.getNeighborhood() != null ) {
            user.setNeighborhood( dto.getNeighborhood() );
        }
        if ( dto.getDistrict() != null ) {
            user.setDistrict( dto.getDistrict() );
        }
        if ( dto.getProvince() != null ) {
            user.setProvince( dto.getProvince() );
        }
        if ( dto.getCountry() != null ) {
            user.setCountry( dto.getCountry() );
        }
        if ( dto.getBuildingNumber() != null ) {
            user.setBuildingNumber( dto.getBuildingNumber() );
        }
        if ( dto.getApartmentNumber() != null ) {
            user.setApartmentNumber( dto.getApartmentNumber() );
        }
        if ( dto.getZipCode() != null ) {
            user.setZipCode( dto.getZipCode() );
        }

        return user;
    }

    @Override
    public UpdateUserFromUserToAuth updateUserFromUserToAuth(User user) {
        if ( user == null ) {
            return null;
        }

        UpdateUserFromUserToAuth.UpdateUserFromUserToAuthBuilder updateUserFromUserToAuth = UpdateUserFromUserToAuth.builder();

        updateUserFromUserToAuth.authId( user.getAuthId() );
        updateUserFromUserToAuth.name( user.getName() );
        updateUserFromUserToAuth.surname( user.getSurname() );
        updateUserFromUserToAuth.email( user.getEmail() );
        updateUserFromUserToAuth.username( user.getUsername() );
        updateUserFromUserToAuth.avatar( user.getAvatar() );
        updateUserFromUserToAuth.street( user.getStreet() );
        updateUserFromUserToAuth.neighborhood( user.getNeighborhood() );
        updateUserFromUserToAuth.district( user.getDistrict() );
        updateUserFromUserToAuth.province( user.getProvince() );
        updateUserFromUserToAuth.country( user.getCountry() );
        updateUserFromUserToAuth.buildingNumber( user.getBuildingNumber() );
        updateUserFromUserToAuth.apartmentNumber( user.getApartmentNumber() );
        updateUserFromUserToAuth.zipCode( user.getZipCode() );

        return updateUserFromUserToAuth.build();
    }
}
