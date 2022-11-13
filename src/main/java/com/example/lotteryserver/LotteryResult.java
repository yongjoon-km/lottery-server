package com.example.lotteryserver;

import javax.persistence.*;

@Entity
@Table(name = "lottery_result")
public class LotteryResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lottery_result_id", nullable = false)
    private Long lotteryResultId;

    @Column(name = "lottery_numbers", nullable = false, length = 17)
    private String lotteryNumbers;

    @Column(name = "lottery_round_id", nullable = false)
    private Long lotteryRoundId;

    @Column(name = "bonus_number", nullable = false, length = 2)
    private String bonusNumber;

    public String getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(String bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

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

    public Long getLotteryResultId() {
        return lotteryResultId;
    }

    public void setLotteryResultId(Long lotteryResultId) {
        this.lotteryResultId = lotteryResultId;
    }
}