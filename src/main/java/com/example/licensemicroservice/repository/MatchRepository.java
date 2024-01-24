package com.example.licensemicroservice.repository;

import com.example.licensemicroservice.domain.Match;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MatchRepository {

    private final List<Match> matchList;

    public MatchRepository() {
        this.matchList = createMatchList();
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    private List<Match> createMatchList() {
        PlayerRepository playerRepo = new PlayerRepository();
        Timestamp startTimeDay1_2023 = Timestamp.valueOf(LocalDateTime.of(2023, 12, 23, 14, 0));
        Timestamp endTimeDay1_2023 = Timestamp.valueOf(LocalDateTime.of(2023, 12, 23, 20, 0));
        Timestamp startTimeDay2_2023 = Timestamp.valueOf(LocalDateTime.of(2023, 12, 18, 12, 0));
        Timestamp endTimeDay2_2023 = Timestamp.valueOf(LocalDateTime.of(2023, 12, 18, 18, 0));
        Timestamp startTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 12, 0));
        Timestamp endTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 18, 0));
        Match mensWimbledonFinal2023 = new Match(1, 1, startTimeDay1_2023, endTimeDay1_2023,
                playerRepo.findPlayerById(1), playerRepo.findPlayerById(3), "AvB");
        Match womensWimbledonFinal2023 = new Match(2, 1, startTimeDay2_2023, endTimeDay2_2023,
                playerRepo.findPlayerById(2), playerRepo.findPlayerById(4));
        Match matchOfTheCentury = new Match(3, 3, startTimeDay3_2024, endTimeDay3_2024, playerRepo.findPlayerById(1),
                playerRepo.findPlayerById(2), "AvBTime");

        return List.of(mensWimbledonFinal2023, womensWimbledonFinal2023, matchOfTheCentury);
    }

//    private String createSummary(Match match) {
//        String summary = "";
//        if (match.getHasSummary()) {
//            summary = "%s %s vs %s %s".formatted(match.getPlayerA().getPlayerFirstName(), match.getPlayerA().getPlayerLastName(),
//                    match.getPlayerB().getPlayerFirstName(), match.getPlayerB().getPlayerLastName());
//        }
//
//        return summary;
//    }

    public Optional<Match> findMatchById(int matchId) {
        return getMatchList().stream().filter(s -> s.getMatchId() == matchId)
                .findFirst();
    }

    public List<Match> findMatchesByTournamentId(int tournamentId){
        return getMatchList().stream().filter(match -> match.getTournamentId() == tournamentId).collect(Collectors.toList());
    }

}
