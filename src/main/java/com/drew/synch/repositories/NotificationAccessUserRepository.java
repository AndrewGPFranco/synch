package com.drew.synch.repositories;

import com.drew.synch.entities.NotificationAccessUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationAccessUserRepository extends JpaRepository<NotificationAccessUser, UUID> {

    @Modifying
    @Transactional
    @Query("update NotificationAccessUser nau set nau.wasRead = true where nau.user.id = :idUser and nau.notification.id = :idNotification")
    void markNotificationAsRead(@Param("idUser") UUID idUser, @Param("idNotification") UUID idNotification);
}
