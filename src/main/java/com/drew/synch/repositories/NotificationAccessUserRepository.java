package com.drew.synch.repositories;

import com.drew.synch.entities.NotificationAccessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationAccessUserRepository extends JpaRepository<NotificationAccessUser, UUID> {

}
