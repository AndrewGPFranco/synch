package com.drew.synch.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification_access_users")
public class NotificationAccessUser {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "notification_id", nullable = false)
    private NotificationAccessTable notification;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "was_read")
    private boolean wasRead = false;

    @Column(name = "was_answered")
    private boolean wasAnswered = false;

    @Override
    public String toString() {
        return "NotificationAccessUser{" +
                "id=" + id +
                ", wasRead=" + wasRead +
                ", wasAnswered=" + wasAnswered +
                '}';
    }
}
