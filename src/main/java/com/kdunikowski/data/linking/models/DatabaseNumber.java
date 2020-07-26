package com.kdunikowski.data.linking.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class DatabaseNumber {
    @Id
    @GeneratedValue
    private Integer id;
    private  Double randomNumber;
}
