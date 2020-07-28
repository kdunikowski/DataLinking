package com.kdunikowski.data.linking.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataLinkingTests {

    @Autowired
    NumberLinkingService numberLinking;

    @Test
    void shouldTestAddingTwoNumbers() {
        assertEquals(18, numberLinking.linkData(9d, 9d));
    }

}
