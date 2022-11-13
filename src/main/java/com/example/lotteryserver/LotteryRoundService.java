package com.example.lotteryserver;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class LotteryRoundService {
    private final LocalDateTime LOTTERY_STARTING_DATE_TIME = LocalDateTime.of(2022, 9, 5, 0, 0);
    public Long getLotteryRoundId(LocalDateTime currentDateTime) {
        long currentRoundWeek = ChronoUnit.WEEKS.between(LOTTERY_STARTING_DATE_TIME, currentDateTime);
        if (currentRoundWeek < 0) {
            throw new IllegalArgumentException();
        }
        return currentRoundWeek;
    }
}
