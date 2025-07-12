package com.drew.synch.mappers;

import com.drew.synch.dtos.user.UserInputDTO;
import com.drew.synch.dtos.user.UserOutputDTO;
import com.drew.synch.entities.User;
import com.drew.synch.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final BCryptPasswordEncoder encoder;

    public User dtoInputToEntity(UserInputDTO dto) {
        return User.builder().name(dto.name()).email(dto.email()).fullname(dto.fullname()).nickname(dto.nickname())
                .password(encoder.encode(dto.password())).pathImage("").birthDate(dto.birthDate())
                .roles(Set.of(RoleType.USER)).updateDate(null).registrationDate(LocalDateTime.now()).build();
    }

    public UserOutputDTO entityToDtoOutput(User user) {
        return UserOutputDTO.builder().name(user.getName()).email(user.getEmail()).fullname(user.getFullname())
                .nickname(user.getNickname()).birthDate(user.getBirthDate()).roles(user.getRoles()).build();
    }

}

