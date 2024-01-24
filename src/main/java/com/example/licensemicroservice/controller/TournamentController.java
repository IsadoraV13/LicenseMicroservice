package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.domain.Tournament;
import com.example.licensemicroservice.service.TournamentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/{tournamentId}")
    public Tournament viewTournamentById(@PathVariable int tournamentId) {
        return tournamentService.getTournamentById(tournamentId);
    }
}
