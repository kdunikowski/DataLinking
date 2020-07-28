package com.kdunikowski.data.linking.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CommonValues {
    @Value("${restrandom.min:0}")
    public String min;
    @Value("${restrandom.max:100}")
    public String max;
    @Value("${restrandom.url:https://csrng.net/csrng/csrng.php?}")
    public String randomNumberURL;

    public String RANDOM_NUMBER_URL_WITH_PARAMS;
    public static final String AMPERSAND = "&";
    public static final String MAX = "max";
    public static final String ASSIGNMENT = "=";
    public static final String MIN = "min";
    public static final String SUCCESS_STATUS = "success";
    public static final String FIRST_AUGEND = "firstAugend";
    public static final String SECOND_AUGEND = "secondAugend";
    public static final String ZERO = "0.0";
    public static final String NUMBERS = "/numbers";
    public static final String ADDITION = "/addition";
    public static final String ADDITION_REST_RANDOM_AND_DATABASE = "/addition/rest/random/database";
    public static final String CANNOT_RECEIVE_VALUE_FROM_REST_API = "Cannot receive value from rest api.";

    @PostConstruct
    public void init() {
        RANDOM_NUMBER_URL_WITH_PARAMS = randomNumberURL + MIN + ASSIGNMENT +
                min + AMPERSAND + MAX + ASSIGNMENT + max;
    }

}
