package com.emre.mapper;

import com.emre.dto.request.CreateUserForAuthToUserDto;
import com.emre.dto.request.RegisterRequestDto;
import com.emre.dto.request.UpdateUserFromUserToAuth;
import com.emre.rabbitmq.model.RegisterMailModel;
import com.emre.repository.entity.Auth;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-25T23:46:12+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.6 (Amazon.com Inc.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth registerFromDtoToAuth(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder<?, ?> auth = Auth.builder();

        auth.email( dto.getEmail() );
        auth.username( dto.getUsername() );
        auth.password( dto.getPassword() );

        return auth.build();
    }

    @Override
    public CreateUserForAuthToUserDto createUserFromAuthServiceToUserService(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        CreateUserForAuthToUserDto.CreateUserForAuthToUserDtoBuilder createUserForAuthToUserDto = CreateUserForAuthToUserDto.builder();

        createUserForAuthToUserDto.authId( auth.getAuthId() );
        createUserForAuthToUserDto.name( auth.getName() );
        createUserForAuthToUserDto.surname( auth.getSurname() );
        createUserForAuthToUserDto.email( auth.getEmail() );
        createUserForAuthToUserDto.username( auth.getUsername() );
        createUserForAuthToUserDto.password( auth.getPassword() );

        return createUserForAuthToUserDto.build();
    }

    @Override
    public Auth updateUserFromUserToAuth(UpdateUserFromUserToAuth dto, Auth auth) {
        if ( dto == null ) {
            return auth;
        }

        if ( dto.getAuthId() != null ) {
            auth.setAuthId( dto.getAuthId() );
        }
        if ( dto.getName() != null ) {
            auth.setName( dto.getName() );
        }
        if ( dto.getSurname() != null ) {
            auth.setSurname( dto.getSurname() );
        }
        if ( dto.getEmail() != null ) {
            auth.setEmail( dto.getEmail() );
        }
        if ( dto.getUsername() != null ) {
            auth.setUsername( dto.getUsername() );
        }

        return auth;
    }

    @Override
    public RegisterMailModel fromRegisterMailModelToAuth(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterMailModel.RegisterMailModelBuilder registerMailModel = RegisterMailModel.builder();

        registerMailModel.email( auth.getEmail() );
        registerMailModel.activationCode( auth.getActivationCode() );
        registerMailModel.username( auth.getUsername() );

        return registerMailModel.build();
    }
}
