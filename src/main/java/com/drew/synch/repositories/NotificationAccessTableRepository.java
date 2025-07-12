package com.drew.synch.repositories;

import com.drew.synch.entities.NotificationAccessTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotificationAccessTableRepository extends JpaRepository<NotificationAccessTable, UUID> {
}
