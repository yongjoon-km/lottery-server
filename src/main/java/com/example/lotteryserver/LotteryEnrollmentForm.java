package com.example.lotteryserver;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class LotteryEnrollmentForm {
    @Size(min=6, max=6)
    private List<Integer> lotteryNumbers;
}
