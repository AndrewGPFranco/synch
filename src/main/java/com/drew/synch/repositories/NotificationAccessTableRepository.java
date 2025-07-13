package com.drew.synch.repositories;

import com.drew.synch.entities.NotificationAccessTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationAccessTableRepository extends JpaRepository<NotificationAccessTable, UUID> {

    @Query(value = "select count(*) from notification_access_users where user_id = :idUser", nativeQuery = true)
    Integer checkIfContainsNewNotifications(@Param("idUser") UUID id);

    @Modifying
    @Transactional
    @Query("update NotificationAccessTable nau set nau.wasExpired = true where nau.id = :idNotification")
    void markAsExpiredNotification(@Param("idNotification") UUID idNotification);
}
