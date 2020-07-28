package com.kdunikowski.data.linking.controllers;

import com.kdunikowski.data.linking.exceptions.CannotGetRestRandomExceptions;
import com.kdunikowski.data.linking.services.NumberLinkingService;
import com.kdunikowski.data.linking.services.data.receivers.DatabaseNumberGetterService;
import com.kdunikowski.data.linking.services.data.receivers.RestRandomNumberGetterService;
import com.kdunikowski.data.linking.utils.CommonValues;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(CommonValues.NUMBERS)
@RequiredArgsConstructor
public class DoublesController implements DataConsumable<Double> {

    protected static final Logger LOGGER = LogManager.getLogger();

    private final NumberLinkingService numberLinkingService;
    private final RestRandomNumberGetterService restRandomNumberGetterService;
    private final DatabaseNumberGetterService databaseNumberGetterService;


    @GetMapping(CommonValues.ADDITION)
    public ResponseEntity<Double> consumeData(@RequestParam(value = CommonValues.FIRST_AUGEND, defaultValue = CommonValues.ZERO) Double firstAugend,
                                              @RequestParam(value = CommonValues.SECOND_AUGEND, defaultValue = CommonValues.ZERO) Double secondAugend) {
        LOGGER.info("Controller runs at {} ", CommonValues.ADDITION);
        LOGGER.info("First augend received from API: {} ", firstAugend);
        LOGGER.info("Second augend received from API: {}", secondAugend);
        return new ResponseEntity<>(numberLinkingService.linkData(firstAugend, secondAugend), HttpStatus.OK);
    }

    @GetMapping(CommonValues.ADDITION_REST_RANDOM_AND_DATABASE)
    public ResponseEntity<Double> consumeData() {
        LOGGER.info("Controller runs at {}", CommonValues.ADDITION_REST_RANDOM_AND_DATABASE);
        Double restRandomNumber = 0d;
        try {
            restRandomNumber = restRandomNumberGetterService.getRandomNumber();
        } catch (CannotGetRestRandomExceptions cannotGetRestRandomExceptions) {
            LOGGER.error(cannotGetRestRandomExceptions);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("First augend received from REST: {}", restRandomNumber);
        Double databaseNumber = databaseNumberGetterService.getDatabaseNumber();
        LOGGER.info("Second augend from database: {}", databaseNumber);
        return new ResponseEntity<>(numberLinkingService.linkData(restRandomNumber, databaseNumber), HttpStatus.OK);
    }

}
