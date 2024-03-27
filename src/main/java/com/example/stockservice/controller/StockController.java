package com.example.stockservice.controller;

import com.example.stockservice.domain.dto.GetStockRes;
import com.example.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    /**
     * 상품 재고 수량 조회
     */
    @GetMapping("/{productId}")
    public ResponseEntity<GetStockRes> getProductStock(@PathVariable(name = "productId") String productId){

        GetStockRes result = stockService.getStock(productId);

        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/update")
    public ResponseEntity<String> updatePreOrderProductStockInRedis(){
        stockService.updatePreOrderProductStockInRedis();
        return ResponseEntity.ok().body("수량 업데이트 완료");
    }




}
