package com.kdunikowski.data.linking.controllers;

import com.kdunikowski.data.linking.exceptions.CannotGetRestRandomExceptions;
import org.springframework.http.ResponseEntity;

public interface DataConsumable<T> {
    ResponseEntity<T> consumeData() throws CannotGetRestRandomExceptions;
    ResponseEntity<T> consumeData(T firstAugend, T secondAugend);
}
