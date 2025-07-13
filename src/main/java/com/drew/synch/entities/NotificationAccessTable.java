package com.drew.synch.entities;

import com.drew.synch.dtos.user.UserDTO;
import com.drew.synch.interfaces.NotificationBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification_access_table")
public class NotificationAccessTable extends NotificationBase {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "user_owner_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User userOwner;

    @NotBlank
    @Column(name = "content_message", nullable = false)
    private String contentMessage;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "was_expired")
    private boolean wasExpired = false;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NotificationAccessUser> users;

    @Override
    public String nameNotification() {
        return "Access Table";
    }

    @Override
    public List<UserDTO> listUsers() {
        return this.users.stream()
                .map(u -> UserDTO.builder()
                        .id(u.getId())
                        .name(u.getUser().getName())
                        .email(u.getUser().getEmail())
                        .nickname(u.getUser().getNickname())
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
                ", contentMessage='" + contentMessage + '\'' +
                ", createdAt=" + createdAt +
                ", wasExpired=" + wasExpired +
                ", users=" + users +
                '}';
    }
}
