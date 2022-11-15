package com.example.lotteryserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LotteryRepositoryTest {

    @Autowired
    private LotteryRepository lotteryRepository;

    @Test
    public void find_lottery_by_round_id_and_lottery_numbers() {
        Lottery lottery = lotteryRepository.findByLotteryRoundIdAndLotteryNumbers(10L, "01/02/03/04/05/06");
        assertNotNull(lottery);
    }

}