package com.kdunikowski.data.linking.service;

import org.springframework.stereotype.Service;

@Service
public class NumberLinking {
    public Double addition(Double firstAugend, Double secondAugend) {
        return firstAugend + secondAugend;
    }
}
