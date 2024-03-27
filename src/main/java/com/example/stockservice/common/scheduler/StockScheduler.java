package com.example.stockservice.common.scheduler;

import com.example.stockservice.repository.StockRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@EnableScheduling
public class StockScheduler {

    private final StockRedisRepository stockRedisRepository;

    @Scheduled(cron = "0 0 12 * * *")
    public void updatePreOrderProductStockInRedis(){
        log.info("스케줄러 실행");
        stockRedisRepository.initStockInfo();
    }

    @Scheduled(cron = "0 1 19 * * *")
    public void updatePreOrderProductStockInDB(){
        stockRedisRepository.updateStockInDB();
    }
}
