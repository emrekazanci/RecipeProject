package com.emre.mapper;

import com.emre.dto.request.CreateUserForAuthToUserDto;
import com.emre.dto.request.RegisterRequestDto;
import com.emre.dto.request.UpdateUserFromUserToAuth;
import com.emre.rabbitmq.model.RegisterMailModel;
import com.emre.repository.entity.Auth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth registerFromDtoToAuth(final RegisterRequestDto dto);

    CreateUserForAuthToUserDto createUserFromAuthServiceToUserService(final Auth auth);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Auth updateUserFromUserToAuth(final UpdateUserFromUserToAuth dto, @MappingTarget Auth auth);

    RegisterMailModel fromRegisterMailModelToAuth(final Auth auth);

}
