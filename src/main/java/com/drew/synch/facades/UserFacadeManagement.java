package com.drew.synch.facades;

import com.drew.synch.entities.User;
import com.drew.synch.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacadeManagement {

    private final UserRepository userRepository;

    /**
     * Com base em uma lista de IDS, retornamos os usuários com base na mesma.
     *
     * @param usersId a ser procurado no banco.
     * @return lista com os Usuários.
     */
    public List<User> returningListUsers(List<Long> usersId) {
        List<User> list = new ArrayList<>(usersId.size());
        list.addAll(userRepository.findAllById(usersId));

        return list;
    }

}
