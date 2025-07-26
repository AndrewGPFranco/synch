package com.drew.synch.services;

import com.drew.synch.dtos.notification.OutputNotificationAccessTableDTO;
import com.drew.synch.entities.NotificationAccessTable;
import com.drew.synch.fixtures.NotificationFixture;
import com.drew.synch.mappers.notification.NotificationMapper;
import com.drew.synch.repositories.NotificationAccessTableRepository;
import com.drew.synch.repositories.NotificationAccessUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

class NotificationServiceTest {

    @Spy
    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private FinanceTableService financeService;

    @Mock
    private NotificationMapper notificationMapper;

    @Mock
    private NotificationAccessUserRepository notificationAccessUserRepository;

    @Mock
    private NotificationAccessTableRepository notificationAccessTableRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckIfContainsNewNotifications() {
        UUID idUser = getIdUser();
        List<NotificationAccessTable> fixtureData = NotificationFixture.createFixtureData();

        Mockito.when(notificationAccessTableRepository.checkIfContainsNewNotifications(idUser)).thenReturn(2);
        Mockito.when(notificationAccessTableRepository.findAll()).thenReturn(fixtureData);

        Set<OutputNotificationAccessTableDTO> result = notificationService.checkIfContainsNewNotifications(idUser);

        Assertions.assertEquals(6, result.size());
    }

    private UUID getIdUser() {
        return UUID.fromString(NotificationFixture.UUID_STRING);
    }
}