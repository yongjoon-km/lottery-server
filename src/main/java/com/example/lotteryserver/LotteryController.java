package com.example.lotteryserver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        boolean isLotteryRegistered = lotteryService.register(currentRoundId, lotteryNumbersToRegister);

        if (!isLotteryRegistered) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }

        EnrollmentResult enrollmentResult = new EnrollmentResult();
        enrollmentResult.setLotteryNumbers(lotteryNumbersToRegister.stream().sorted().collect(Collectors.toList()));
        enrollmentResult.setLotteryRoundId(currentRoundId);
        return ResponseEntity.ok(enrollmentResult);
    }

    @PostMapping("/api/auto")
    public ResponseEntity<EnrollmentResult> auto() {
        long currentRoundId = lotteryRoundService.getLotteryRoundId(LocalDateTime.now());
        List<Integer> lotteryNumbersToRegister = lotteryService.createRandomLotteryNumbers(currentRoundId);

        boolean isLotteryRegistered = lotteryService.register(currentRoundId, lotteryNumbersToRegister);

        if (!isLotteryRegistered) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }

        EnrollmentResult enrollmentResult = new EnrollmentResult();
        enrollmentResult.setLotteryNumbers(lotteryNumbersToRegister.stream().sorted().collect(Collectors.toList()));
        enrollmentResult.setLotteryRoundId(currentRoundId);
        return ResponseEntity.ok(enrollmentResult);
    }
}
