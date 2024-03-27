package com.example.stockservice.repository;

import static com.example.stockservice.common.response.BaseResponseStatus.UNEXPECTED_ERROR;

import com.example.stockservice.common.exceptions.BaseException;
import com.example.stockservice.domain.ProductType;
import com.example.stockservice.domain.entity.Stock;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class StockRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private final StockRepository stockRepository;

    public void saveStock(String productNumber, int quantity) { // redis 에 상품 재고 정보 저장
        redisTemplate.opsForValue().set(productNumber, String.valueOf(quantity));
    }

    public int getStock(String productNumber){
        String quantity = redisTemplate.opsForValue().get(productNumber);
        return quantity!=null?Integer.parseInt(quantity):0;
    }

    public Long increaseOne(String productId) { // 수량 + 1
        return redisTemplate.opsForValue().increment(productId);
    }

   public Long decreaseOne(String productId){ // 수량 - 1

       Integer decrement = Math.toIntExact(redisTemplate.opsForValue().decrement(productId));

       if(decrement==0){
           Stock stock = stockRepository.findByProductId(productId)
                   .orElseThrow(() -> new IllegalStateException("유효하지 않은 상품 번호입니다."));
           stock.updateStock(0);
       }

       if(decrement<0){
           log.error("재고는 음수가 될 수 없습니다. : {}",decrement);
           throw new BaseException(UNEXPECTED_ERROR);
       }
       return decrement;
   }


    public void initStockInfo() { // redis 에 수량 정보 초기화
        List<Stock> preOrderProductStockList = stockRepository.findAllByProductType(ProductType.RESERVATION);// 예약 상품목록만 가져옴.
        for (Stock stock : preOrderProductStockList) {
            saveStock(stock.getProductId(), stock.getQuantity());
        }
    }

    public void updateStockInDB(){ // db 에 수량 정보 update
        List<Stock> preOrderProductStockList = stockRepository.findAllByProductType(ProductType.RESERVATION);// 예약 상품목록만 가져옴.
        for (Stock stock : preOrderProductStockList) {
            String productId = stock.getProductId();

            int quantity = getStock(productId);
            stock.updateStock(quantity);

        }
    }
}
