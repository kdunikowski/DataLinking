package com.kdunikowski.data.linking.services.data.receivers;

import com.kdunikowski.data.linking.exceptions.CannotGetRestRandomExceptions;
import com.kdunikowski.data.linking.utils.CommonValues;
import com.kdunikowski.data.linking.wrappers.RandomNumberResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestRandomNumberGetterService {

    private static final Logger LOGGER = LogManager.getLogger();
    private final RestTemplate restTemplate;
    private final CommonValues commonValues;

    @Autowired
    RestRandomNumberGetterService(CommonValues commonValues) {
        this.restTemplate = new RestTemplate();
        this.commonValues = commonValues;
    }

    public Double getRandomNumber() throws CannotGetRestRandomExceptions {
        RandomNumberResponse[] numbers = restTemplate.getForObject(
              commonValues.RANDOM_NUMBER_URL_WITH_PARAMS, RandomNumberResponse[].class);
        if (numbers.length == 0 || !numbers[0].getStatus().equals(CommonValues.SUCCESS_STATUS)) {
            LOGGER.error("Cannot receive number from REST. Length of response: {}",  numbers.length);
            throw new CannotGetRestRandomExceptions(CommonValues.CANNOT_RECEIVE_VALUE_FROM_REST_API);
        }
        return Double.valueOf(numbers[0].getRandom());
    }

}
