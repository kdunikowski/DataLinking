package com.kdunikowski.data.linking.controllers;

import com.kdunikowski.data.linking.exceptions.CannotGetRestRandom;
import com.kdunikowski.data.linking.exceptions.NotNumberException;
import com.kdunikowski.data.linking.service.DatabaseNumberGetter;
import com.kdunikowski.data.linking.service.NumberLinking;
import com.kdunikowski.data.linking.service.RestRandomNumberGetter;
import com.kdunikowski.data.linking.utils.CommonValues;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(CommonValues.NUMBERS)
public class Numbers {

    NumberLinking numberLinking;

    RestRandomNumberGetter restRandomNumberGetter;

    DatabaseNumberGetter databaseNumberGetter;

    protected static final Logger LOGGER = LogManager.getLogger();


    @Autowired
    public Numbers(NumberLinking numberLinking, RestRandomNumberGetter restRandomNumberGetter, DatabaseNumberGetter databaseNumberGetter) {
        this.numberLinking = numberLinking;
        this.restRandomNumberGetter = restRandomNumberGetter;
        this.databaseNumberGetter = databaseNumberGetter;
    }

    @GetMapping(CommonValues.ADDITION)
    public String joinNumbers(@RequestParam(value = CommonValues.FIRST_AUGEND, defaultValue = CommonValues.ZERO) String firstAugend,
                              @RequestParam(value = CommonValues.SECOND_AUGEND, defaultValue = CommonValues.ZERO) String secondAugend)
            throws NotNumberException {

        LOGGER.info("Controller runs at " + CommonValues.ADDITION);
        LOGGER.info("First augend received from API: " + firstAugend);
        LOGGER.info("Second augend received from API: " + secondAugend);
        if (!NumberUtils.isParsable(firstAugend)) {
            LOGGER.error("FIRST AUGEND IS IN WRONG FORMAT");
            throw new NotNumberException(CommonValues.ERROR_MESSAGE_FIRST_AUGEND + firstAugend);
        }
        if (!NumberUtils.isParsable(secondAugend)) {
            LOGGER.error("SECOND AUGEND IS IN WRONG FORMAT");
            throw new NotNumberException(CommonValues.ERROR_MESSAGE_SECOND_AUGEND + secondAugend);
        }
        return String.valueOf(numberLinking.addition(NumberUtils.toDouble(firstAugend), NumberUtils.toDouble(secondAugend)));
    }

    @GetMapping(CommonValues.ADDITION_REST_RANDOM_AND_DATABASE)
    public String joinNumbers()
            throws NotNumberException, CannotGetRestRandom {
        LOGGER.info("Controller runs at " + CommonValues.ADDITION_REST_RANDOM_AND_DATABASE);
        Double restRandomNumber = restRandomNumberGetter.getRandomNumber();
        LOGGER.info("First augend received from REST: " + restRandomNumber);
        Double databaseNumber = databaseNumberGetter.getDatabaseNumber();
        LOGGER.info("Second augend from database: " + databaseNumber);
        return String.valueOf(numberLinking.addition(restRandomNumber,
                databaseNumber));
    }

}
