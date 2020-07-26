package com.kdunikowski.data.linking.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomNumberResponse {
    private String min;
    private String max;
    private String status;
    private String random;
}
