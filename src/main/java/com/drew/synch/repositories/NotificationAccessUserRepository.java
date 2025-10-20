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

    @Modifying
    @Transactional
    @Query("update NotificationAccessUser nau set nau.wasAnswered = true where nau.user.id = :idUser and nau.notification.id = :idNotification")
    void markNotificationAsAnswered(@Param("idUser") UUID idUser, @Param("idNotification") UUID idNotification);

    @Modifying
    @Transactional
    @Query("update NotificationAccessUser nau set nau.wasRead = true where nau.user.id = :idUser")
    void markAllAsReadByUser(@Param("idUser") UUID idUser);

    @Modifying
    @Transactional
    @Query("delete from NotificationAccessUser nau where nau.user.id = :idUser")
    void deleteAllByUser(@Param("idUser") UUID idUser);

    @Modifying
    @Transactional
    @Query("delete from NotificationAccessUser nau where nau.user.id = :idUser and nau.notification.id = :idNotification")
    void deleteNotificationByUser(@Param("idUser") UUID idUser, @Param("idNotification") UUID idNotification);

    @Query(value = "select count(*) from notification_access_users nau where nau.notification_id = :idNotification and nau.user_id != :idUser", nativeQuery = true)
    Integer checkIfItHasUserNotifications(@Param("idNotification") UUID idNotification, @Param("idUser") UUID idUser);

}