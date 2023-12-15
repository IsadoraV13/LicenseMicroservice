package com.example.licensemicroservice.service;

import com.example.licensemicroservice.domain.Tournament;
import com.example.licensemicroservice.repository.TournamentRepository;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {

    final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public Tournament getTournamentById(int tournamentId) {
        return tournamentRepository.findTournamentById(tournamentId);
    }
}
