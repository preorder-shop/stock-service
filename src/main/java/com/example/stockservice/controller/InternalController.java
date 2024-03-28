package com.example.stockservice.controller;

import com.example.stockservice.domain.dto.DecreaseStockReq;
import com.example.stockservice.domain.dto.PostStockReq;
import com.example.stockservice.domain.dto.StockDto;
import com.example.stockservice.domain.dto.StockRes;
import com.example.stockservice.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/internal/stocks")
@RestController
public class InternalController {

    private final StockService stockService;

    /**
     * 새로 생성된 상품의 재고 수량 정보 update
     */
    @PostMapping("/new")
    public void postStock(@RequestBody PostStockReq postStockReq){

        stockService.createProductStock(postStockReq);

    }

    @GetMapping("/{productId}")
    public StockDto getProductStock(@PathVariable("productId") String productId){

       StockDto stock = stockService.getProductStock(productId);

       return stock;

    }
//
//    /**
//     * 재고 수량 감소
//     */
//    @PostMapping("/decrease")
//    public StockRes decreaseStock(@RequestBody DecreaseStockReq decreaseStockReq){
//
//        int stock = stockService.decreaseStock(decreaseStockReq);
//
//        return new StockRes(stock);
//
//
//    }
//
//    @PostMapping("/decrease")
//    public StockRes increaseStock(@RequestBody DecreaseStockReq decreaseStockReq){
//
//        int stock = stockService.decreaseStock(decreaseStockReq);
//
//        return new StockRes(stock);
//
//    }
}
