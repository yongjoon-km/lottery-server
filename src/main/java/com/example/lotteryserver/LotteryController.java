package com.example.lotteryserver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryService lotteryService;

    private final LotteryRoundService lotteryRoundService;

    @PostMapping("/api/enroll")
    public ResponseEntity<EnrollmentResult> enroll(@Valid @RequestBody LotteryEnrollmentForm lotteryEnrollmentForm) {
        List<Integer> lotteryNumbersToRegister = lotteryEnrollmentForm.getLotteryNumbers();
        long currentRoundId = lotteryRoundService.getLotteryRoundId(LocalDateTime.now());

        Lottery lottery = lotteryService.register(currentRoundId, lotteryNumbersToRegister);

        EnrollmentResult enrollmentResult = new EnrollmentResult();
        enrollmentResult.setLotteryNumbers(lotteryNumbersToRegister.stream().sorted().collect(Collectors.toList()));
        enrollmentResult.setLotteryRoundId(currentRoundId);
        enrollmentResult.setLotteryId(lottery.getLotteryId());
        return ResponseEntity.ok(enrollmentResult);
    }

    @PostMapping("/api/auto")
    public ResponseEntity<EnrollmentResult> auto() {
        long currentRoundId = lotteryRoundService.getLotteryRoundId(LocalDateTime.now());
        List<Integer> lotteryNumbersToRegister = lotteryService.createRandomLotteryNumbers();

        Lottery lottery = lotteryService.register(currentRoundId, lotteryNumbersToRegister);

        EnrollmentResult enrollmentResult = new EnrollmentResult();
        enrollmentResult.setLotteryNumbers(lotteryNumbersToRegister.stream().sorted().collect(Collectors.toList()));
        enrollmentResult.setLotteryRoundId(currentRoundId);
        enrollmentResult.setLotteryId(lottery.getLotteryId());
        return ResponseEntity.ok(enrollmentResult);
    }
}
