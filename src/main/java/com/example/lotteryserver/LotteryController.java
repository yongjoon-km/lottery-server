package com.example.lotteryserver;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryController {

    @PostMapping("/api/enroll")
    public EnrollmentResult enroll(@RequestBody LotteryEnrollmentForm lotteryEnrollmentForm) {
        if (lotteryEnrollmentForm.getLotteryNumbers().size() != 6) {
            throw new RuntimeException();
        }
        return new EnrollmentResult();
    }
}
