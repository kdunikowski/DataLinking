package com.kdunikowski.data.linking.services.data.receivers;

import com.kdunikowski.data.linking.exceptions.CannotGetRestRandomExceptions;
import com.kdunikowski.data.linking.services.data.receivers.RestRandomNumberGetterService;
import com.kdunikowski.data.linking.utils.CommonValues;
import com.kdunikowski.data.linking.wrappers.RandomNumberResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.reflect.Whitebox;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RestRandomNumberGetterServiceTests {

    protected static final String RANDOM_NUMBER_URL_WITH_PARAMS = "RANDOM_NUMBER_URL_WITH_PARAMS";
    protected static final String HTTPS_CSRNG_NET_CSRNG_CSRNG_PHP_MIN_0_MAX_100 = "https://csrng.net/csrng/csrng.php?min=0&max=100";
    protected static final String WRONG_STATUS = "WRONG STATUS";
    protected static final String COMMON_VALUES = "commonValues";
    protected static final String REST_TEMPLATE = "restTemplate";
    @InjectMocks
    RestRandomNumberGetterService restRandomNumberGetter;

    @Mock
    CommonValues commonValues;

    @Mock
    RestTemplate restTemplate;

    @Test
    void shouldTestReceivingNumber() throws Exception {
        RandomNumberResponse randomNumberResponse = new RandomNumberResponse();
        randomNumberResponse.setStatus(CommonValues.SUCCESS_STATUS);
        randomNumberResponse.setRandom(CommonValues.ZERO);
        RandomNumberResponse[] responses = new RandomNumberResponse[]{randomNumberResponse};
        Mockito.when(restTemplate.getForObject(HTTPS_CSRNG_NET_CSRNG_CSRNG_PHP_MIN_0_MAX_100, RandomNumberResponse[].class)).thenReturn(responses);
        Whitebox.setInternalState(commonValues, RANDOM_NUMBER_URL_WITH_PARAMS, HTTPS_CSRNG_NET_CSRNG_CSRNG_PHP_MIN_0_MAX_100);
        Whitebox.setInternalState(restRandomNumberGetter, COMMON_VALUES, commonValues);
        Whitebox.setInternalState(restRandomNumberGetter, REST_TEMPLATE, restTemplate);
        Double receivedRandom = restRandomNumberGetter.getRandomNumber();
        assertEquals(CommonValues.ZERO, Double.toString(receivedRandom));
    }

    @Test
    void shouldThrowReceivingNumber() throws Exception {
        RandomNumberResponse randomNumberResponse = new RandomNumberResponse();
        randomNumberResponse.setStatus(WRONG_STATUS);
        randomNumberResponse.setRandom(CommonValues.ZERO);
        RandomNumberResponse[] responses = new RandomNumberResponse[]{randomNumberResponse};
        Mockito.when(restTemplate.getForObject(HTTPS_CSRNG_NET_CSRNG_CSRNG_PHP_MIN_0_MAX_100, RandomNumberResponse[].class)).thenReturn(responses);
        Whitebox.setInternalState(commonValues, RANDOM_NUMBER_URL_WITH_PARAMS, HTTPS_CSRNG_NET_CSRNG_CSRNG_PHP_MIN_0_MAX_100);
        Whitebox.setInternalState(restRandomNumberGetter, COMMON_VALUES, commonValues);
        Whitebox.setInternalState(restRandomNumberGetter, REST_TEMPLATE, restTemplate);
        assertThrows(CannotGetRestRandomExceptions.class, () -> {
            restRandomNumberGetter.getRandomNumber();
        });
    }

}