package com.kdunikowski.data.linking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataLinkingTests {

    @Autowired
    NumberLinking numberLinking;

    @Test
    void shouldTestAddingTwoNumbers() {
        assertEquals(18, numberLinking.addition(9d, 9d));
    }

}
