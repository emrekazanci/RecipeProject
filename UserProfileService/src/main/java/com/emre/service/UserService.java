package com.emre.service;

import com.emre.dto.request.*;
import com.emre.dto.response.GetRecipeIdAndCategoryIdResponseDto;
import com.emre.dto.response.GetUserForFavCategory;
import com.emre.dto.response.GetUserIdAndUsernameDto;
import com.emre.exception.ErrorType;
import com.emre.exception.UserManagerException;
import com.emre.manager.IAuthManager;
import com.emre.manager.IRecipeManager;
import com.emre.mapper.IUserMapper;
import com.emre.repository.IUserRepository;
import com.emre.repository.entity.User;
import com.emre.utility.JwtTokenProvider;
import com.emre.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService extends ServiceManager<User, String> {
    private final IUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IAuthManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final IRecipeManager recipeManager;

    public UserService(IUserRepository userRepository,
                       JwtTokenProvider jwtTokenProvider,
                       IAuthManager authManager,
                       PasswordEncoder passwordEncoder,
                       IRecipeManager recipeManager) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.recipeManager = recipeManager;
    }

    public Boolean createUser(CreateUserForAuthToUserDto dto) {
        User user = IUserMapper.INSTANCE.createUserAuthServiceToUserService(dto);
        save(user);
        return true;
    }

    public User update(String token, UpdateUserRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
        Optional<User> user = userRepository.findByAuthId(authId.get());
        if (user.isEmpty()) throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        update(IUserMapper.INSTANCE.fromUpdateUserDtoToUser(dto, user.get()));
        UpdateUserFromUserToAuth updateUser = IUserMapper.INSTANCE.updateUserFromUserToAuth(user.get());
        authManager.updateUserFromUserToAuth(updateUser);
        return user.get();
    }

    public String changePassword(String token, ChangePasswordRequestDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
        Optional<User> user = userRepository.findByAuthId(authId.get());
        if (!passwordEncoder.matches(dto.getOldPassword(), user.get().getPassword()))
            throw new UserManagerException(ErrorType.PASSWORD_ERROR);
        user.get().setPassword(passwordEncoder.encode(dto.getNewPassword()));
        save(user.get());
        authManager.changePassword(ChangePassForUserToAuthDto.builder()
                .authId(authId.get())
                .password(user.get().getPassword())
                .build());
        return "Şifreniz değiştirilmiştir.";
    }

    public Boolean forgotPassword(UpdatePassToUserProfileForForgotPass updatePassToUserProfileForForgotPass) {
        Optional<User> user = userRepository.findByAuthId(updatePassToUserProfileForForgotPass.getAuthId());
        if (user.isEmpty()) throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        user.get().setPassword(updatePassToUserProfileForForgotPass.getPassword());
        update(user.get());
        return true;
    }

    //=========================

    public GetUserIdAndUsernameDto getUserIdAndUserNameWithAuthId(Long authId) {
        Optional<User> user = userRepository.findByAuthId(authId);
        if (user.isEmpty()) throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        GetUserIdAndUsernameDto dto = GetUserIdAndUsernameDto.builder()
                .userId(user.get().getUserId())
                .username(user.get().getUsername()).
                build();
        return dto;
    }
    // ======================

    public Boolean saveFavoriteRecipe(String token, String recipeId) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) throw new UserManagerException(ErrorType.INVALID_TOKEN);
        Optional<User> user = userRepository.findByAuthId(authId.get());
        if (user.isEmpty()) throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        user.get().getFavoriteRecipe().add(recipeId);
        GetRecipeIdAndCategoryIdResponseDto dto = recipeManager.getRecipeIdAndCategoryId(recipeId).getBody();
        dto.getCategoryId().forEach(x -> {
            if (!user.get().getFavoriteCategory().contains(x)) {
                user.get().getFavoriteCategory().add(x);
            }
        });
        update(user.get());
        return true;
    }

    public List<User> findUserByCategoryId(String categoryId) {
        List<User> users = userRepository.findByFavoriteCategory(categoryId);
        return users;
    }

    public Set<GetUserForFavCategory> getUserWithFavCategory(List<String> categoryId) {
        List<User> userList = userRepository.findAll();
        Set<GetUserForFavCategory> userSet = new HashSet<>();
        userList.forEach(x -> {
            categoryId.forEach(y -> {
                if (x.getFavoriteCategory().contains(y)) {
                    userSet.add(GetUserForFavCategory.builder()
                            .userId(x.getUserId())
                            .email(x.getEmail())
                            .username(x.getUsername())
                            .build());
                }
            });
        });
        return userSet;
    }
}
