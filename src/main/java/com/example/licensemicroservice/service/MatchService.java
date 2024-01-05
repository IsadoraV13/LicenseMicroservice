package com.example.licensemicroservice.service;

import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getMatchById(int matchId) {
        return matchRepository.findMatchById(matchId);
    }

    public List<Match> getMatchesByTournamentId(int tournamentId) {
        return matchRepository.findMatchesByTournamentId(tournamentId);
    }
}
