package com.example.lotteryserver;

import javax.persistence.*;

@Entity
@Table(name = "lottery")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lottery_id", nullable = false)
    private Long lotteryId;

    @Column(name = "lottery_numbers", nullable = false, length = 17)
    private String lotteryNumbers;

    @Column(name = "lottery_round_id", nullable = false)
    private Long lotteryRoundId;

    public Long getLotteryRoundId() {
        return lotteryRoundId;
    }

    public void setLotteryRoundId(Long lotteryRoundId) {
        this.lotteryRoundId = lotteryRoundId;
    }

    public String getLotteryNumbers() {
        return lotteryNumbers;
    }

    public void setLotteryNumbers(String lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
    }
}