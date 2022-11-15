package com.example.lotteryserver;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LotteryRepository extends JpaRepository<Lottery, Long> {
    Lottery findByLotteryRoundIdAndLotteryNumbers(Long roundId, String lotteryNumbers);
}
