package com.drew.synch.interfaces;

import com.drew.synch.dtos.user.UserDTO;

import java.util.List;

/**
 * Classe base para ser utilizada nos tipos de notificações disponíveis no sistema.
 *
 * @author andrewgo
 */
public abstract class NotificationBase {

    public abstract String nameNotification();

    public abstract List<UserDTO> listUsers();

    public abstract boolean isForAllUsers();

    public abstract boolean wasExpired();

}
