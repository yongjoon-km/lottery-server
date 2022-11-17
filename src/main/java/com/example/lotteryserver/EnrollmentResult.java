package com.example.lotteryserver;

import lombok.Data;

import java.util.List;

@Data
public class EnrollmentResult {
    private Long lotteryId;
    private Long lotteryRoundId;
    private List<Integer> lotteryNumbers;
}
