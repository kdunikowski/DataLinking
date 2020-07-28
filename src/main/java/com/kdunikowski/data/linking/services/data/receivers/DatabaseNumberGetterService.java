package com.kdunikowski.data.linking.services.data.receivers;

import com.kdunikowski.data.linking.models.DatabaseNumber;
import com.kdunikowski.data.linking.repositories.DatabaseNumberRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseNumberGetterService {

    private static final Logger LOGGER = LogManager.getLogger();
    private DatabaseNumberRepository databaseNumberRepository;

    @Autowired
    public DatabaseNumberGetterService(DatabaseNumberRepository databaseNumberRepository) {
        this.databaseNumberRepository = databaseNumberRepository;
    }

    public Double getDatabaseNumber() {
        int count = (int) databaseNumberRepository.count();
        if (count == 0) {
            LOGGER.error("EMPTY DATABASE. RETURNED 0");
            return 0.0;
        }
        Optional<DatabaseNumber> foundNumber = databaseNumberRepository.findById(count);
        if (!foundNumber.isPresent()) {
            LOGGER.error("CANNOT GET NUMBER FOR INDEX: {}. Returned 0", count);
            return 0.0;
        }
        return foundNumber.get().getRandomNumber();
    }
}
