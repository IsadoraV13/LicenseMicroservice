package com.example.licensemicroservice.repository;

import com.example.licensemicroservice.domain.Match;
import jakarta.persistence.EntityNotFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
        Timestamp startTimeDay2_2023 = Timestamp.valueOf(LocalDateTime.of(2023, 12, 22, 12, 0));
        Timestamp endTimeDay2_2023 = Timestamp.valueOf(LocalDateTime.of(2023, 12, 22, 18, 0));
        Timestamp startTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 12, 0));
        Timestamp endTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 18, 0));
        Match mensWimbledonFinal2023 = new Match(1, startTimeDay1_2023, endTimeDay1_2023,
                playerRepo.findPlayerById(1), playerRepo.findPlayerById(3), false);
        Match womensWimbledonFinal2023 = new Match(2, startTimeDay2_2023, endTimeDay2_2023,
                playerRepo.findPlayerById(2), playerRepo.findPlayerById(4), true);
        Match matchOfTheCentury = new Match(3, startTimeDay3_2024, endTimeDay3_2024, playerRepo.findPlayerById(1),
                playerRepo.findPlayerById(2), true);

        return List.of(mensWimbledonFinal2023, womensWimbledonFinal2023, matchOfTheCentury);
    }

    private String createSummary(Match match) {
        String summary = "";
        if (match.getHasSummary()) {
            summary = "%s %s vs %s %s".formatted(match.getPlayerA().getPlayerFirstName(), match.getPlayerA().getPlayerLastName(),
                    match.getPlayerB().getPlayerFirstName(), match.getPlayerB().getPlayerLastName());
        }

        return summary;
    }

    public Match findMatchById(int matchId) {
        MatchRepository matchRepo = new MatchRepository();
        Match match = matchRepo.getMatchList().stream().filter(s -> s.getMatchId() == matchId).findAny().orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "Match ID %s not found".formatted(matchId)));
        return match;
    }

}
