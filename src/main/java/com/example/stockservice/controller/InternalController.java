package com.example.stockservice.controller;

import com.example.stockservice.domain.dto.DecreaseStockReq;
import com.example.stockservice.domain.dto.CreateProductStockReq;
import com.example.stockservice.domain.dto.StockRes;
import com.example.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/internal")
public class InternalController {

    private final StockService stockService;

    /**
     * 새로 생성된 상품의 재고 수량 정보 update
     */
    @PostMapping("/product-stock")
    public void createProductStock(@RequestBody CreateProductStockReq createProductStockReq){

        stockService.createProductStock(createProductStockReq);

    }

//    @GetMapping("/product-stock/{productId}")
//    public GetProductStockRes getProductStock(@PathVariable("productId") String productId){
//
//        stockService.getStock()
//    }

    /**
     * 재고 수량 감소
     */
    @PostMapping("/decrease")
    public StockRes decreaseStock(@RequestBody DecreaseStockReq decreaseStockReq){

        Long stock = stockService.decreaseStock(decreaseStockReq);

        return new StockRes(stock);


    }

    @PostMapping("/decrease")
    public StockRes increaseStock(@RequestBody DecreaseStockReq decreaseStockReq){

        Long stock = stockService.decreaseStock(decreaseStockReq);

        return new StockRes(stock);

    }
}
