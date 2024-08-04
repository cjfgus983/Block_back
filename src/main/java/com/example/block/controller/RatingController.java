package com.example.block.controller;

import com.example.block.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contest/{contestId}")
public class RatingController {

    private final RatingService ratingService;

    //  userId는 토큰 정보로 대체
    @PostMapping("/challenger/{challengerId}/score")
    public void rateUser(@PathVariable Integer contestId,
                         @PathVariable Integer challengerId,
                         @RequestParam Float score,
                         @RequestParam(name = "userId") Integer userId){

        ratingService.rateUser(userId, challengerId, contestId, score);
    }
}
