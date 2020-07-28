package com.kdunikowski.data.linking.services;

public interface DataLinkable<T> {
    T linkData(T firstAugend, T secondAugend);
}
