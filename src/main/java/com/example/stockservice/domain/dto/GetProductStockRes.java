package com.example.stockservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetProductStockRes {
    private String productId;
    private int stock;
}
