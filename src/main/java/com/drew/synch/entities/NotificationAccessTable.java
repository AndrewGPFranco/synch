package com.drew.synch.entities;

import com.drew.synch.dtos.user.UserDTO;
import com.drew.synch.interfaces.NotificationBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification_access_table")
public class NotificationAccessTable extends NotificationBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "user_owner_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User userOwner;

    @Transient
    private List<User> users;

    @NotBlank
    @Column(name = "content_message", nullable = false)
    private String contentMessage;

    @Column(name = "was_read_destination")
    private boolean wasReadDestination;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "was_expired")
    private boolean wasExpired = false;

    @Override
    public String nameNotification() {
        return "Access Table";
    }

    @Override
    public List<UserDTO> listUsers() {
        return this.users.stream()
                .map(u -> UserDTO.builder()
                        .name(u.getName())
                        .email(u.getEmail())
                        .nickname(u.getNickname())
                        .build())
                .toList();
    }

    @Override
    public boolean isForAllUsers() {
        return false;
    }

    @Override
    public boolean wasExpired() {
        return this.wasExpired;
    }

    @Override
    public String toString() {
        return "NotificationAccessTable{" +
                "id=" + id +
                ", userOwner=" + userOwner +
                ", users=" + users +
                ", contentMessage='" + contentMessage + '\'' +
                ", wasReadDestination=" + wasReadDestination +
                ", createdAt=" + createdAt +
                ", wasExpired=" + wasExpired +
                '}';
    }
}
