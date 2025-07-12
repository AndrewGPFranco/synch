package com.drew.synch.services;

import com.drew.synch.dtos.user.UserInputDTO;
import com.drew.synch.dtos.user.UserOutputDTO;
import com.drew.synch.entities.User;
import com.drew.synch.exceptions.NotFoundException;
import com.drew.synch.mappers.UserMapper;
import com.drew.synch.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserOutputDTO register(UserInputDTO userInputDTO) {
        try {
            User user = userMapper.dtoInputToEntity(userInputDTO);
            User savedUser = userRepository.save(user);

            return getUserOutputById(savedUser.getId());
        } catch (DataIntegrityViolationException | IllegalArgumentException e) {
            log.error(e.getMessage());
            throw new DataIntegrityViolationException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Ocorreu um erro ao registrar o usuário");
        }
    }

    public UserOutputDTO getUserOutputById(UUID id) {
        User user = getUserById(id);
        return userMapper.entityToDtoOutput(user);
    }

    protected User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Nenhum registro encontrado com o ID: " + id));
    }

}
