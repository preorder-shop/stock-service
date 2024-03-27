package com.example.stockservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class GetStockRes {

    private String productId;
    private int stock;
}
