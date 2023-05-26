package com.emre.mapper;

import com.emre.dto.request.UpdateUserFromUserToAuth;
import com.emre.repository.entity.Address;
import com.emre.repository.entity.Auth;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAddressMapper {
    IAddressMapper INSTANCE = Mappers.getMapper(IAddressMapper.class);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address updateUserFromUserToAddress(final UpdateUserFromUserToAuth dto, @MappingTarget Address address);

    Address saveAddress(final UpdateUserFromUserToAuth dto);
}
