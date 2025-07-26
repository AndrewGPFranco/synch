package com.drew.synch.fixtures;

import com.drew.synch.entities.NotificationAccessTable;
import com.drew.synch.entities.NotificationAccessUser;
import com.drew.synch.entities.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class NotificationFixture {

    public static final String UUID_STRING = "550e8400-e29b-41d4-a716-446655440001";

    public static List<NotificationAccessTable> createFixtureData() {
        return Arrays.asList(
                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440001"),
                        createUser(UUID.fromString(UUID_STRING), "João Silva"),
                        "Sua conta será debitada em R$ 1.500,00 em 3 dias",
                        LocalDateTime.of(2024, 7, 20, 10, 30),
                        false,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440001")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440002"),
                        createUser(UUID.fromString(UUID_STRING), "Maria Santos"),
                        "Pagamento de R$ 850,00 foi processado com sucesso",
                        LocalDateTime.of(2024, 7, 21, 14, 15),
                        false,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440002")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440003"),
                        createUser(UUID.fromString(UUID_STRING), "Carlos Oliveira"),
                        "Vencimento da fatura em 5 dias - R$ 2.300,00",
                        LocalDateTime.of(2024, 7, 19, 9, 45),
                        true,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440003")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440004"),
                        createUser(UUID.fromString("110e8400-e29b-41d4-a716-446655440004"), "Ana Paula"),
                        "Transferência de R$ 500,00 realizada para conta corrente",
                        LocalDateTime.of(2024, 7, 22, 16, 20),
                        false,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440004")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440005"),
                        createUser(UUID.fromString("110e8400-e29b-41d4-a716-446655440005"), "Roberto Lima"),
                        "Limite de crédito aumentado para R$ 5.000,00",
                        LocalDateTime.of(2024, 7, 18, 11, 10),
                        false,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440005")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440006"),
                        createUser(UUID.fromString("110e8400-e29b-41d4-a716-446655440006"), "Fernanda Costa"),
                        "Cobrança de R$ 320,00 em atraso há 15 dias",
                        LocalDateTime.of(2024, 7, 17, 8, 30),
                        true,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440006")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440007"),
                        createUser(UUID.fromString("110e8400-e29b-41d4-a716-446655440007"), "Pedro Rocha"),
                        "Investimento de R$ 1.200,00 aplicado em CDB",
                        LocalDateTime.of(2024, 7, 23, 13, 45),
                        false,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440007")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440008"),
                        createUser(UUID.fromString("110e8400-e29b-41d4-a716-446655440008"), "Juliana Mendes"),
                        "Saldo insuficiente para débito automático de R$ 780,00",
                        LocalDateTime.of(2024, 7, 16, 7, 15),
                        false,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440008")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440009"),
                        createUser(UUID.fromString("110e8400-e29b-41d4-a716-446655440009"), "Eduardo Alves"),
                        "Rendimento de poupança: R$ 45,20 creditados",
                        LocalDateTime.of(2024, 7, 24, 12, 0),
                        false,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440009")
                ),

                createNotification(
                        UUID.fromString("550e8400-e29b-41d4-a716-446655440010"),
                        createUser(UUID.fromString("110e8400-e29b-41d4-a716-446655440010"), "Camila Ferreira"),
                        "Cartão de crédito bloqueado por suspeita de fraude",
                        LocalDateTime.of(2024, 7, 15, 18, 30),
                        true,
                        UUID.fromString("990e8400-e29b-41d4-a716-446655440010")
                )
        );
    }

    private static NotificationAccessTable createNotification(UUID id, User userOwner, String contentMessage,
                                                              LocalDateTime createdAt, boolean wasExpired, UUID idFinanceTable) {
        NotificationAccessTable notification = new NotificationAccessTable();
        notification.setId(id);
        notification.setUserOwner(userOwner);
        notification.setContentMessage(contentMessage);
        notification.setCreatedAt(createdAt);
        notification.setWasExpired(wasExpired);
        notification.setIdFinanceTable(idFinanceTable);

        notification.setNotificationUsers(createNotificationUsers(notification, userOwner));

        return notification;
    }

    private static User createUser(UUID id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    private static List<NotificationAccessUser> createNotificationUsers(NotificationAccessTable notification, User mainUser) {
        return Arrays.asList(
                createNotificationUser(notification, mainUser, true),
                createNotificationUser(notification, createUser(UUID.randomUUID(), "Admin User"), false)
        );
    }

    private static NotificationAccessUser createNotificationUser(NotificationAccessTable notification, User user, boolean wasRead) {
        NotificationAccessUser notificationUser = new NotificationAccessUser();
        notificationUser.setId(UUID.randomUUID());
        notificationUser.setNotification(notification);
        notificationUser.setUser(user);
        notificationUser.setWasRead(wasRead);
        return notificationUser;
    }
}