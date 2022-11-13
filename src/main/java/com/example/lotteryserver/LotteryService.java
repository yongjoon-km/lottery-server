package com.example.lotteryserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotteryService {
    private final LotteryRepository lotteryRepository;

}
