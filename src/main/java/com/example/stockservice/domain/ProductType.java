package com.example.stockservice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    GENERAL("일반 상품"),
    RESERVATION("예약 상품");

    private final String text;
}
