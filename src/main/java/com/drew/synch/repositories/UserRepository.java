package com.drew.synch.repositories;

import com.drew.synch.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickname(String username);

    User findByEmail(String email);

}
