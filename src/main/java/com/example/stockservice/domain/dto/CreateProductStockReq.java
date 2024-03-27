package com.example.stockservice.domain.dto;

import com.example.stockservice.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CreateProductStockReq {
    private String productId;
    private ProductType productType;
    private int stock;
}
