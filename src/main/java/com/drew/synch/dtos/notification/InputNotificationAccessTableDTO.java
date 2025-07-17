package com.drew.synch.dtos.notification;

import com.drew.synch.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Builder
public class InputNotificationAccessTableDTO {

    private User userOwner;

    @NotBlank
    private String message;

    @NotNull
    @NotEmpty
    private Set<String> destinationUsers;

}
