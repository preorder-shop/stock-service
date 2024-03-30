package com.example.stockservice.common.data;


import com.example.stockservice.domain.ProductType;
import com.example.stockservice.domain.entity.Stock;
import com.example.stockservice.repository.StockRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {


    private final StockRepository stockRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Stock stockOne = Stock.builder()
                .productId("100")
                .quantity(10)
                .productType(ProductType.RESERVATION)
                .build();

        Stock stockTwo = Stock.builder()
                .productId("110")
                .quantity(10)
                .productType(ProductType.RESERVATION)
                .build();

        Stock stockThree = Stock.builder()
                .productId("120")
                .quantity(100)
                .productType(ProductType.GENERAL)
                .build();

        Stock stockFour = Stock.builder()
                .productId("130")
                .quantity(200)
                .productType(ProductType.GENERAL)
                .build();

        Stock stockFive = Stock.builder()
                .productId("140")
                .quantity(300)
                .productType(ProductType.GENERAL)
                .build();

        stockRepository.save(stockOne);
        stockRepository.save(stockTwo);
        stockRepository.save(stockThree);
        stockRepository.save(stockFour);
        stockRepository.save(stockFive);

        log.info("stock data 초기화");

    }




}
