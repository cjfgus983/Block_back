package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.converter.TeamMatchConverter;
import com.example.block.domain.User;
import com.example.block.domain.mapping.Applicant;
import com.example.block.dto.TeamMatchRequestDTO;
import com.example.block.dto.TeamMatchResponseDTO;
import com.example.block.service.TeamMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contest/{contestId}")
public class TeamMatchRestController {

    private final TeamMatchService teamMatchService;

    //  토큰 구현되면 userId를 토큰 정보로 대체
    @PostMapping("/apply")
    public void apply(@PathVariable Integer contestId,
                      @RequestBody TeamMatchRequestDTO.ApplyDTO request,
                      @RequestParam(name = "userId") Long userId){
        teamMatchService.applyToContest(request, contestId, userId);
    }

    @GetMapping("/challenger")
    public ApiResponse<List<TeamMatchResponseDTO.ChallengerListResultDTO>> challengerList(@PathVariable Integer contestId){
        List<Applicant> challengerList = teamMatchService.getChallengerList(contestId);
        return ApiResponse.onSuccess(TeamMatchConverter.toChallengerListResultDTO(challengerList));
    }

    //  토큰 구현되면 userId를 토큰 정보로 대체
    //  userId는 유저 ID
    //  challengerId는 유저 ID가 아니라 지원자 ID
    @GetMapping("/challenger/{challengerId}")
    public ApiResponse<TeamMatchResponseDTO.ChallengerResultDTO> challenger(@PathVariable Integer contestId,
                                                                            @PathVariable Integer challengerId,
                                                                            @RequestParam(name = "userId") Long userId){
        Applicant challenger = teamMatchService.getChallenger(contestId, challengerId);
        Boolean hasUserLiked = teamMatchService.hasUserLiked(userId, challenger.getUser().getId(), contestId);
        return ApiResponse.onSuccess(TeamMatchConverter.toChallengerResultDTO(challenger, hasUserLiked));
    }

    @PostMapping("/challenger/{challengerId}")
    public void like(@PathVariable Integer contestId, @PathVariable Integer challengerId){

    }

    @GetMapping("/member")
    public void member(@PathVariable Integer contestId){

    }
}
