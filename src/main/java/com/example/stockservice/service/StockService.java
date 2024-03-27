package com.example.stockservice.service;

import static com.example.stockservice.common.response.BaseResponseStatus.STOCK_NOT_FOUND;

import com.example.stockservice.common.exceptions.BaseException;
import com.example.stockservice.domain.dto.DecreaseStockReq;
import com.example.stockservice.domain.dto.IncreaseStockReq;
import com.example.stockservice.domain.entity.Stock;
import com.example.stockservice.domain.dto.CreateProductStockReq;
import com.example.stockservice.domain.dto.GetStockRes;
import com.example.stockservice.repository.StockRedisRepository;
import com.example.stockservice.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockRedisRepository stockRedisRepository;

    public GetStockRes getStock(String productId) { // 실시간 재고 가져옴.

        // todo : redis 에 있으면 redis 에서 가져오고
        // 없으면 db에서 조회

        Stock stockInDB = stockRepository.findByProductId(productId)
                .orElseThrow(()-> new BaseException(STOCK_NOT_FOUND));


        int quantity = stockInDB.getQuantity();
        GetStockRes getStockRes = new GetStockRes(productId,quantity);


    }

    public void createProductStock(CreateProductStockReq createProductStockReq) {

        Stock stock = Stock.builder()
                .productId(createProductStockReq.getProductId())
                .productType(createProductStockReq.getProductType())
                .quantity(createProductStockReq.getStock())
                .build();

        stockRepository.save(stock);
    }

    public Long decreaseStock(DecreaseStockReq decreaseStockReq) {
        Long remainStock = stockRedisRepository.decreaseOne(decreaseStockReq.getProductId());
        return remainStock;
    }

    public Long increaseStock(IncreaseStockReq increaseStockReq){
        Long remainStock = stockRedisRepository.increaseOne(increaseStockReq.getProductId());
        return remainStock;
    }

    public void updatePreOrderProductStockInRedis() {
        stockRedisRepository.initStockInfo();
    }
}
