package com.kdunikowski.data.linking.services;

import org.springframework.stereotype.Service;

@Service
public class NumberLinkingService implements DataLinkable<Double> {
    public Double linkData(Double firstAugend, Double secondAugend) {
        return firstAugend + secondAugend;
    }
}
