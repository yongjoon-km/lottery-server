package com.example.lotteryserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public boolean register(Long lotteryRoundId, List<Integer> lotteryNumbers) {
        validate(lotteryNumbers);
        List<Integer> sortedLotteryNumbers = lotteryNumbers.stream().sorted().toList();
        String joinedLotteryNumbers = sortedLotteryNumbers
                            .stream()
                            .map(s -> String.format("%02d", s))
                            .collect(Collectors.joining("/"));
        Lottery lottery = new Lottery();
        lottery.setLotteryNumbers(joinedLotteryNumbers);
        lottery.setLotteryRoundId(lotteryRoundId);
        lotteryRepository.save(lottery);
        return true;
    }

    private void validate(List<Integer> lotteryNumbers) {
        if (lotteryNumbers.stream().distinct().count() != 6) {
            throw new DuplicatedNumbersInLotteryNumberException();
        }
    }
}
