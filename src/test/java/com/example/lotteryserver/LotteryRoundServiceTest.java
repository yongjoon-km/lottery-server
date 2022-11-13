package com.example.lotteryserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LotteryRoundServiceTest {

    private LotteryRoundService lotteryRoundService;

    @BeforeEach
    public void setup() {
        lotteryRoundService = new LotteryRoundService();
    }

    @Test
    public void it_should_be_10th_game_on_NOV_14th_2022() {
        LocalDateTime currentDateTime = LocalDateTime.of(2022, 11, 14, 1, 0);
        Long lotteryRoundId = lotteryRoundService.getLotteryRoundId(currentDateTime);
        assertEquals(lotteryRoundId, 10L);
    }

    @Test
    public void it_should_be_11th_game_on_NOV_21th_2022() {
        LocalDateTime currentDateTime = LocalDateTime.of(2022, 11, 21, 1, 0);
        Long lotteryRoundId = lotteryRoundService.getLotteryRoundId(currentDateTime);
        assertEquals(lotteryRoundId, 11L);
    }

    @Test
    public void should_throw_an_exception_if_current_date_time_is_before_lottery_starting_date() {
        LocalDateTime currentDateTime = LocalDateTime.of(2021, 1, 1, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> {
            lotteryRoundService.getLotteryRoundId(currentDateTime);
        });
    }

    @Test
    public void throw_an_exception_if_current_date_time_is_null() {
        assertThrows(AssertionError.class, () -> {
            lotteryRoundService.getLotteryRoundId(null);
        });
    }
}