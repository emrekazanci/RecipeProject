package com.emre.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserForAuthToUserDto {
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
}
