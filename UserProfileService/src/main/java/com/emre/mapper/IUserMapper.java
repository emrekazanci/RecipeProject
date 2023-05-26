package com.emre.mapper;

import com.emre.dto.request.CreateUserForAuthToUserDto;
import com.emre.dto.request.UpdateUserFromUserToAuth;
import com.emre.dto.request.UpdateUserRequestDto;
import com.emre.dto.response.GetRecipeIdAndCategoryIdResponseDto;
import com.emre.dto.response.GetUserIdAndUsernameDto;
import com.emre.repository.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User createUserAuthServiceToUserService(final CreateUserForAuthToUserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User fromUpdateUserDtoToUser(final UpdateUserRequestDto dto,@MappingTarget User user);

    UpdateUserFromUserToAuth updateUserFromUserToAuth(final User user);

}
