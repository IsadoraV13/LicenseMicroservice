package com.example.licensemicroservice.service;

import com.example.licensemicroservice.controller.MatchController;
import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.ResponseEntity;
import com.example.licensemicroservice.repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class MatchService {
    private static final Logger LOG = LoggerFactory.getLogger(MatchService.class);
    final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    /*
    Rationale: Added logic in Service so that the controller only receives what we want to pass on to the 'client'.
    Initially used a HashMap because I didn't realise the items are unordered and then refactored
     */
    public LinkedHashMap<String, String> getMatchById(int matchId) {
        Match match = matchRepository.findMatchById(matchId);
        LinkedHashMap<String, String> formattedResponse = new LinkedHashMap<>();
        formattedResponse.put("matchId", Integer.toString(match.getMatchId()));
        formattedResponse.put("startDate", match.getStartTime().toString());
        formattedResponse.put("playerA", String.format("%s %s",match.getPlayerA().getPlayerFirstName(), match.getPlayerA().getPlayerLastName()));
        formattedResponse.put("playerB", String.format("%s %s",match.getPlayerB().getPlayerFirstName(), match.getPlayerB().getPlayerLastName()));
        formattedResponse.put("summary", match.getSummary());
//        LOG.error("An exception occurred!", new Exception("Custom exception"));

        return formattedResponse;
    }

    public List<Match> getMatchesByTournamentId(int tournamentId) {
        return matchRepository.findMatchesByTournamentId(tournamentId);
    }
}
