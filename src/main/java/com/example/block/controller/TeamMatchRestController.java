package com.example.block.controller;

import com.example.block.ApiResponse;
import com.example.block.converter.TeamMatchConverter;
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

    @PostMapping("/apply")
    public void apply(@RequestBody TeamMatchRequestDTO.ApplyDTO request){

    }

    @GetMapping("/challenger")
    public ApiResponse<List<TeamMatchResponseDTO.ChallengerListResultDTO>> challengerList(@PathVariable Long contestId){
        List<Applicant> challengerList = teamMatchService.getChallengerList(contestId);
        return ApiResponse.onSuccess(TeamMatchConverter.toChallengerListResultDTO(challengerList));
    }

    @GetMapping("/challenger/{challengerId}")
    public void challenger(@PathVariable Integer contestId, @PathVariable Integer challengerId){

    }

    @PostMapping("/challenger/{challengerId}")
    public void like(@PathVariable Integer contestId, @PathVariable Integer challengerId){

    }

    @GetMapping("/member")
    public void member(@PathVariable Integer contestId){

    }
}
