package com.example.lotteryserver;

import lombok.Data;

import java.util.List;

@Data
public class LotteryEnrollmentForm {
    private List<Integer> lotteryNumbers;
}
