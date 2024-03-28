package com.example.stockservice.domain.dto;

import com.example.stockservice.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostStockReq {
    private String productId;
    private ProductType productType;
    private int stock;
}
