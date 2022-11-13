package com.example.lotteryserver;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LotteryServiceTest {

    private LotteryService lotteryService;

    @Mock
    private LotteryRepository lotteryRepository;


    @BeforeEach
    public void setup() {
        lotteryService = new LotteryService(lotteryRepository);
    }

    @Test
    public void enroll_succeeded_returns_true() {
        List<Integer> lotteryNumbers = List.of(1, 2, 3, 4, 5, 6);
        Long lotteryRoundId = 60L;
        Lottery lottery = new Lottery();
        lottery.setLotteryNumbers("01/02/03/04/05/06");
        lottery.setLotteryRoundId(60L);
        when(lotteryRepository.save(any(Lottery.class))).thenReturn(any());
        boolean result = lotteryService.register(lotteryRoundId, lotteryNumbers);
        assertTrue(result);
        verify(lotteryRepository).save(refEq(lottery));
    }

    @Test
    public void lottery_number_is_sorted_before_saving() {
        List<Integer> lotteryNumbers = List.of(10, 3, 2, 4, 5, 6);
        Long lotteryRoundId = 60L;
        boolean result = lotteryService.register(lotteryRoundId, lotteryNumbers);
        assertTrue(result);

        Lottery lottery = new Lottery();
        lottery.setLotteryNumbers("02/03/04/05/06/10");
        lottery.setLotteryRoundId(60L);
        verify(lotteryRepository).save(refEq(lottery));
    }

    @Test
    public void throw_an_exception_if_duplicated_numbers_in_lottery_numbers() {
        List<Integer> lotteryNumbers = List.of(1, 1, 2, 3, 4, 5);
        Long lotteryRoundId = 60L;

        assertThrows(DuplicatedNumbersInLotteryNumberException.class, () -> {
            lotteryService.register(lotteryRoundId, lotteryNumbers);
        });

        verify(lotteryRepository, times(0)).save(any());
    }
}