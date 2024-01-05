package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lms")
public class MatchController {
    final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("match/{matchId}")
    public Match viewMatchById(@PathVariable int matchId) {
        return matchService.getMatchById(matchId);
    }

    @GetMapping("matches/{tournamentId}")
    public List<Match> viewMatchesByTournamentId(@PathVariable int tournamentId) {
        return matchService.getMatchesByTournamentId(tournamentId);
    }
}
