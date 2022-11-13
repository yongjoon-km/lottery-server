package com.example.lotteryserver;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryService lotteryService;

    @PostMapping("/api/enroll")
    public EnrollmentResult enroll(@RequestBody LotteryEnrollmentForm lotteryEnrollmentForm) {
        if (lotteryEnrollmentForm.getLotteryNumbers().size() != 6) {
            throw new RuntimeException();
        }
        // TODO: fetch lotteryRoundId
        lotteryService.register(1L, lotteryEnrollmentForm.getLotteryNumbers());
        return new EnrollmentResult();
    }
}
