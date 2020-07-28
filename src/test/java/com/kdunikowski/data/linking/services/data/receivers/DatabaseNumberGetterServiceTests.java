package com.kdunikowski.data.linking.services.data.receivers;

import com.kdunikowski.data.linking.models.DatabaseNumber;
import com.kdunikowski.data.linking.repositories.DatabaseNumberRepository;
import com.kdunikowski.data.linking.services.data.receivers.DatabaseNumberGetterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest
class DatabaseNumberGetterServiceTests {


    @Mock
    private DatabaseNumberRepository databaseNumberRepository;

    @InjectMocks
    DatabaseNumberGetterService sut;

    @Test
    void shouldGetZeroBecauseEmptyDatabase() {
        Mockito.when(databaseNumberRepository.count()).thenReturn(0l);
        Double databaseNumber = sut.getDatabaseNumber();
        assertEquals("", 0.0, databaseNumber);
    }

    @Test
    void shouldGetZeroBecauseWrongIndexDatabase() {
        Mockito.when(databaseNumberRepository.count()).thenReturn(1l);
        Mockito.when(databaseNumberRepository.findById(1)).thenReturn(Optional.empty());
        Double databaseNumber = sut.getDatabaseNumber();
        assertEquals("", 0.0, databaseNumber);
    }

    @Test
    void shouldGetNumberFromDatabase() {
        Mockito.when(databaseNumberRepository.count()).thenReturn(1l);
        DatabaseNumber expectedDatabaseNumber = new DatabaseNumber();
        expectedDatabaseNumber.setId(1);
        expectedDatabaseNumber.setRandomNumber(50d);
        Mockito.when(databaseNumberRepository.findById(1)).thenReturn(Optional.of(expectedDatabaseNumber));
        Double databaseNumber = sut.getDatabaseNumber();
        assertEquals("", expectedDatabaseNumber.getRandomNumber(), databaseNumber);
    }

}