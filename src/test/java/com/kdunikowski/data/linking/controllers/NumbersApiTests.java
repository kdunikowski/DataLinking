package com.kdunikowski.data.linking.controllers;


import io.restassured.response.Response;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumbersApiTests {

    protected static final String EXPECTED = "108.0";
    protected static final String HTTP_LOCALHOST_8080_NUMBERS_ADDITION_FIRST_AUGEND_50_SECOND_AUGEND_58 = "http://localhost:8080/numbers/addition?firstAugend=50&secondAugend=58";
    protected static final String HTTP_LOCALHOST_8080_NUMBERS_ADDITION_REST_RANDOM_DATABASE = "http://localhost:8080/numbers/addition/rest/random/database";
    protected static final int SUCCESS_STATUS = 200;

    @Test
    public void shouldReturnNumberSumForArgumentsInRequest() {
        Response response = when().get(HTTP_LOCALHOST_8080_NUMBERS_ADDITION_FIRST_AUGEND_50_SECOND_AUGEND_58);
        response.then().statusCode(SUCCESS_STATUS);
        String result = response.getBody().asString();
        assertEquals(EXPECTED, result);
    }

    @Test
    public void shouldReturnNumberSumFromRestAndDatabase() {
        Response response = when().get(HTTP_LOCALHOST_8080_NUMBERS_ADDITION_REST_RANDOM_DATABASE);
        response.then().statusCode(SUCCESS_STATUS);
        String result = response.getBody().asString();
        assertTrue(NumberUtils.isParsable(result));
    }

}