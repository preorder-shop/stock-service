package com.example.stockservice.service;

import static com.example.stockservice.common.response.BaseResponseStatus.STOCK_NOT_FOUND;

import com.example.stockservice.common.exceptions.BaseException;
import com.example.stockservice.domain.ProductType;
import com.example.stockservice.domain.dto.DecreaseStockReq;
import com.example.stockservice.domain.dto.IncreaseStockReq;
import com.example.stockservice.domain.dto.StockDto;
import com.example.stockservice.domain.entity.Stock;
import com.example.stockservice.domain.dto.PostStockReq;
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

    public void createProductStock(PostStockReq postStockReq) {
        Stock stock = Stock.builder()
                .productId(postStockReq.getProductId())
                .productType(postStockReq.getProductType())
                .quantity(postStockReq.getStock())
                .build();

        stockRepository.save(stock);
    }

    public StockDto decreaseStock(String productId) {
        int remainStock = stockRedisRepository.decreaseOne(productId);

        return new StockDto(productId,remainStock);
    }

    public StockDto increaseStock(String productId){
        int remainStock = stockRedisRepository.increaseOne(productId);
        return new StockDto(productId,remainStock);
    }

    public void updatePreOrderProductStockInRedis() {
        stockRedisRepository.initStockInfo();
    }

    public StockDto getProductStock(String productId) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new BaseException(STOCK_NOT_FOUND));

        ProductType productType = stock.getProductType();

        // 일반 상품의 경우
        if(productType.equals(ProductType.GENERAL)){
            return new StockDto(stock.getProductId(),stock.getQuantity());
        }
        // 예약 상품의 경우
        StockDto preOrderProductStock = getPreOrderProductStock(productId);
        if(preOrderProductStock==null){
            return new StockDto(productId,stock.getQuantity());
        }
        return preOrderProductStock;

    }

    private StockDto getPreOrderProductStock(String productId){
        Integer stock = stockRedisRepository.getStock(productId);
        if(stock==null){
            return null;
        }
        return new StockDto(productId,stock);

    }
}
