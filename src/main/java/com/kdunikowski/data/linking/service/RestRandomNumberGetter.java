package com.kdunikowski.data.linking.service;

import com.kdunikowski.data.linking.exceptions.CannotGetRestRandom;
import com.kdunikowski.data.linking.utils.CommonValues;
import com.kdunikowski.data.linking.wrappers.RandomNumberResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestRandomNumberGetter {

    private CommonValues commonValues;
    private RestTemplate restTemplate;
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    RestRandomNumberGetter(CommonValues commonValues) {
        this.commonValues = commonValues;
        this.restTemplate = new RestTemplate();
    }

    public Double getRandomNumber() throws CannotGetRestRandom {
        RandomNumberResponse[] numbers = restTemplate.getForObject(
                commonValues.RANDOM_NUMBER_URL_WITH_PARAMS, RandomNumberResponse[].class);
        if (numbers.length == 0 || !numbers[0].getStatus().equals(CommonValues.SUCCESS_STATUS)) {
            LOGGER.error("Cannot receive number from REST. Length of response:" + numbers.length);
            throw new CannotGetRestRandom(CommonValues.CANNOT_RECEIVE_VALUE_FROM_REST_API);
        }
        return Double.valueOf(numbers[0].getRandom());
    }

}
