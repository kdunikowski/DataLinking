package com.kdunikowski.data.linking.service;

import com.kdunikowski.data.linking.models.DatabaseNumber;
import com.kdunikowski.data.linking.repositories.DatabaseNumberRepository;
import com.kdunikowski.data.linking.utils.CommonValues;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class DatabaseNumberGetter {

    DatabaseNumberRepository databaseNumberRepository;
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    public DatabaseNumberGetter(DatabaseNumberRepository databaseNumberRepository) {
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
            LOGGER.error("CANNOT GET NUMBER FOR INDEX: " + count + ". Returned 0");
            return 0.0;
        }
        return foundNumber.get().getRandomNumber();
    }
}
