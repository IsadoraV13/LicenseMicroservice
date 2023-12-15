package com.example.licensemicroservice.repository;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.domain.Tournament;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TournamentRepository {

    private final List<Tournament> tournamentList;

    public TournamentRepository() {
        this.tournamentList = createTournamentList();
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    private List<Tournament> createTournamentList() {
        Tournament tournament1 = new Tournament(1, "Wimbledon2023");
        Tournament tournament2 = new Tournament(2, "RollandGarros2023");
        Tournament tournament3 = new Tournament(3, "Wimbledon2024");

        return List.of(tournament1, tournament2);
    }


    public Tournament findTournamentById(int tournamentId) {
        TournamentRepository tournamentRepo = new TournamentRepository();
        Tournament tournament = tournamentRepo.getTournamentList().stream().filter(s -> s.getTournamentId() == tournamentId).findAny().orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "Tournament ID %s not found".formatted(tournamentId)));
        return tournament;
    }

    public Tournament findTournamentByName(String tournamentName) {
        TournamentRepository tournamentRepo = new TournamentRepository();
        Tournament tournament = tournamentRepo.getTournamentList().stream().filter(s -> s.getTournamentName().equals(tournamentName)).findFirst().orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "Tournament with name '%s' not found".formatted(tournamentName)));
        return tournament;
    }


}
