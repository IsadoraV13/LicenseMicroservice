package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.ResponseEntity;
import com.example.licensemicroservice.service.MatchService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private static final Logger LOG = LoggerFactory.getLogger(MatchController.class);
    final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<LinkedHashMap<String, String>> viewMatchById(@PathVariable int matchId) throws JsonProcessingException {
        ResponseEntity<LinkedHashMap<String, String>> response = new ResponseEntity<>();
        response.setData(matchService.getMatchById(matchId));
//        LOG.error("An exception occurred!", new Exception("Custom exception"));
        return response;
    }


    /*
    Not in the requirements per se but this would be useful if building an actual application and we would want to
    see which matches are linked to a tournament
     */
    @GetMapping("tournament/{tournamentId}")
    public List<Match> viewMatchesByTournamentId(@PathVariable int tournamentId) {
        return matchService.getMatchesByTournamentId(tournamentId);
    }
}
