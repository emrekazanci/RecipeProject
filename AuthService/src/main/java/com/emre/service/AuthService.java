package com.emre.service;

import com.emre.dto.request.*;
import com.emre.dto.response.ForgotPasswordToMailResponseDto;
import com.emre.exception.AuthManagerException;
import com.emre.exception.ErrorType;
import com.emre.manager.IEmailManager;
import com.emre.manager.IUserProfileManager;
import com.emre.mapper.IAddressMapper;
import com.emre.mapper.IAuthMapper;
import com.emre.rabbitmq.producer.MailProducer;
import com.emre.repository.IAuthRepository;
import com.emre.repository.entity.Address;
import com.emre.repository.entity.Auth;
import com.emre.repository.entity.enums.EStatus;
import com.emre.utility.CodeGenerator;
import com.emre.utility.JwtTokenProvider;
import com.emre.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserProfileManager userProfileManager;
    private final PasswordEncoder passwordEncoder;
    private final AddressService addressService;
    private final MailProducer mailProducer;
    private final IEmailManager emailManager;

    public AuthService(IAuthRepository authRepository,
                       JwtTokenProvider jwtTokenProvider,
                       IUserProfileManager userProfileManager,
                       PasswordEncoder passwordEncoder,
                       AddressService addressService,
                       MailProducer mailProducer, IEmailManager emailManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userProfileManager = userProfileManager;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.mailProducer = mailProducer;
        this.emailManager = emailManager;
    }

    public Auth register(RegisterRequestDto dto) {
        Auth auth = IAuthMapper.INSTANCE.registerFromDtoToAuth(dto);
        Optional<Auth> authWithEmail = authRepository.findOptionalByEmail(auth.getEmail());
        if (authWithEmail.isPresent()) throw new AuthManagerException(ErrorType.EXIST_EMAIL);
        if (!dto.getRePassword().equals(dto.getPassword())) throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        auth.setActivationCode(CodeGenerator.generateCode());
        auth.setPassword(passwordEncoder.encode(dto.getPassword()));
        save(auth);
        userProfileManager.createUser(IAuthMapper.INSTANCE.createUserFromAuthServiceToUserService(auth));
        mailProducer.sendActivationCode(IAuthMapper.INSTANCE.fromRegisterMailModelToAuth(auth));
        return auth;
    }

    public String activateStatus(ActivateStatusRequestDto dto) {
        Optional<Auth> auth = authRepository.findById(dto.getAuthId());
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        if (!auth.get().getActivationCode().equals(dto.getActivateCode()))
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        auth.get().setStatus(EStatus.ACTIVE);
        update(auth.get());
        return "Hesabınız aktif olmuştur!";
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findByUsername(dto.getUsername());
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        if (!passwordEncoder.matches(dto.getPassword(), auth.get().getPassword()))
            throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        if (auth.get().getStatus().equals(EStatus.PENDING))
            throw new AuthManagerException(ErrorType.USER_NOT_ACTIVE);
        return "Token: " + jwtTokenProvider.createToken(auth.get().getAuthId(),auth.get().getRole()).orElseThrow(() -> {
                    throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);
                }
        );
    }

    public Boolean updateUserFromUserToAuth(UpdateUserFromUserToAuth dto) {
        Optional<Auth> auth = authRepository.findById(dto.getAuthId());
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        if (auth.get().getAddressId() != null) {
            Optional<Address> address = addressService.findById(auth.get().getAddressId());
            if (address.isEmpty()) throw new AuthManagerException(ErrorType.ADDRES_NOT_FOUND);
            addressService.update(IAddressMapper.INSTANCE.updateUserFromUserToAddress(dto, address.get()));
        } else {
            Address address = IAddressMapper.INSTANCE.saveAddress(dto);
            addressService.save(address);
            auth.get().setAddressId(address.getAddressId());
        }
        update(IAuthMapper.INSTANCE.updateUserFromUserToAuth(dto, auth.get()));
        return true;
    }

    public Boolean changePassword(ChangePassForUserToAuthDto dto) {
        Optional<Auth> auth = findById(dto.getAuthId());
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        auth.get().setPassword(dto.getPassword());
        update(auth.get());
        return true;
    }

    public String forgotPassword(String email, String username) {
        Optional<Auth> auth = authRepository.findOptionalByEmail(email);
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        if (!auth.get().getUsername().equals(username))
            throw new RuntimeException("Bu kullanıcı adına ait kullanıcı yok");
        if (auth.get().getStatus().equals(EStatus.PENDING)) throw new AuthManagerException(ErrorType.USER_NOT_ACTIVE);
        String randomPassword = UUID.randomUUID().toString();
        auth.get().setPassword(passwordEncoder.encode(randomPassword));
        save(auth.get());
        ForgotPasswordToMailResponseDto dto = ForgotPasswordToMailResponseDto.builder()
                .email(auth.get().getEmail())
                .password(randomPassword)
                .build();
        emailManager.forgotPasswordToMail(dto);
        UpdatePassToUserProfileForForgotPass toUser = UpdatePassToUserProfileForForgotPass.builder()
                .authId(auth.get().getAuthId())
                .password(auth.get().getPassword())
                .build();
        userProfileManager.updatePassForForgotPass(toUser);
        return "Şifreniz maile gönderilmiştir!";
    }
}
