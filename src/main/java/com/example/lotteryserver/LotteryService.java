package com.example.lotteryserver;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LotteryService {
    @Value("${lottery.number.count}")
    private final Integer lotteryNumberCount;
    @Value("${lottery.number.min}")
    private final Integer lotteryNumberMin;
    @Value("${lottery.number.max}")
    private final Integer lotteryNumberMax;
    private final LotteryRepository lotteryRepository;

    public boolean register(Long lotteryRoundId, List<Integer> lotteryNumbers) {
        validate(lotteryNumbers);
        List<Integer> sortedLotteryNumbers = lotteryNumbers.stream().sorted().toList();
        String joinedLotteryNumbers = formatLotteryNumbersToString(sortedLotteryNumbers);
        Lottery lottery = new Lottery();
        lottery.setLotteryNumbers(joinedLotteryNumbers);
        lottery.setLotteryRoundId(lotteryRoundId);
        lotteryRepository.save(lottery);
        return true;
    }

    public List<Integer> createRandomLotteryNumbers() {
        List<Integer> randomLotteryNumbers = getDistinctRandomNumbers();
        validate(randomLotteryNumbers);

        return randomLotteryNumbers;
    }

    private void validate(List<Integer> lotteryNumbers) {
        if (lotteryNumbers.stream().distinct().count() != lotteryNumberCount) {
            throw new DuplicatedNumbersInLotteryNumberException();
        }
    }

    private List<Integer> getDistinctRandomNumbers() {
        Random random = new Random();
        Set<Integer> result = new HashSet<>();
        while(result.size() < lotteryNumberCount) {
            int number = random.nextInt(lotteryNumberMax-1) + lotteryNumberMin;
            result.add(number);
        }
        return result.stream().toList();
    }

    private String formatLotteryNumbersToString(List<Integer> lotteryNumbers) {
        Assert.notEmpty(lotteryNumbers, "lottery numbers should not be empty");
        Assert.isTrue(lotteryNumbers.size() == lotteryNumberCount, "lottery numbers should be fixed 6");

        return lotteryNumbers
                .stream()
                .map(s -> String.format("%02d", s))
                .collect(Collectors.joining("/"));
    }
}
