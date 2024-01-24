package com.example.licensemicroservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/*
Requirements for Summary:
summary: An optional parameter called summaryType can be set to any of:
AvB - return string "<playerA> vs <playerB>"
AvBTime - use start time to return string
    "<playerA> vs <playerB>, starts in x minutes" for future start times, OR
    "<playerA> vs <playerB>, started x minutes ago" for start times in the past.
 */

public class Match {
    private int matchId;
    private int tournamentId; // a match cannot be created without being associated with a Tournament, to not end up
    // with 'orphaned' matches
    private Timestamp startTime; // zoneDateTime would have been better here but didn't want to 'cheat' and change it
    private Timestamp endTime; // decided not to use @JsonIgnore here because there are scenarios where we might need end time
    private Player playerA; // used Players rather than just player names (as per requirement) - because there are
    // situations where we might need more than just the playerName (e.g. player id or player rank)
    private Player playerB;
    @JsonIgnore
    private String summaryType; // currently no use case to return the summaryType
    private String summary;


    public Match(int matchId, int tournamentId, Timestamp startTime, Timestamp endTime, Player playerA, Player playerB, String summaryType) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playerA = playerA;
        this.playerB = playerB;
        this.summaryType = summaryType;
        this.summary = createSummary(summaryType);
    }

    public Match(int matchId, int tournamentId, Timestamp startTime, Timestamp endTime, Player playerA, Player playerB) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public String getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(String summaryType) {
        this.summaryType = summaryType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    /*
    An optional parameter called summaryType can be set to any of:
        AvB - return string "<playerA> vs <playerB>"
        AvBTime - use start time to return string
        - "<playerA> vs <playerB>, starts in x minutes" when start time in future
        - "<playerA> vs <playerB>, started x minutes ago" when start time is in the past.
     */
    private String createSummary(String summaryType) {
        String summary = "";
        try {
            if (summaryType.equals("AvB")) {
                summary =  "%s %s vs %s %s".formatted(this.getPlayerA().getPlayerFirstName(), this.getPlayerA().getPlayerLastName(),
                        this.getPlayerB().getPlayerFirstName(), this.getPlayerB().getPlayerLastName());
            } else if (summaryType.equals("AvBTime")) {
                long durationInMinutes = Duration.between(this.startTime.toLocalDateTime(), LocalDateTime.now()).toMinutes();
                long durationInHours = Duration.between(this.startTime.toLocalDateTime(), LocalDateTime.now()).toHours();
                long durationInDays = Duration.between(this.startTime.toLocalDateTime(), LocalDateTime.now()).toDays();
                if (this.startTime.toLocalDateTime().toLocalDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))) {
                    if (Math.abs(durationInDays) > 1) {
                        summary = "%s %s vs %s %s, starts in %s days".formatted(this.getPlayerA().getPlayerFirstName(), this.getPlayerA().getPlayerLastName(),
                                this.getPlayerB().getPlayerFirstName(), this.getPlayerB().getPlayerLastName(), durationInDays * -1);
                    } else if (Math.abs(durationInHours) > 1) {
                        summary = "%s %s vs %s %s, starts in %s hours".formatted(this.getPlayerA().getPlayerFirstName(), this.getPlayerA().getPlayerLastName(),
                                this.getPlayerB().getPlayerFirstName(), this.getPlayerB().getPlayerLastName(), durationInMinutes * -1);
                    } else {
                        summary = "%s %s vs %s %s, starts in %s minutes".formatted(this.getPlayerA().getPlayerFirstName(), this.getPlayerA().getPlayerLastName(),
                                this.getPlayerB().getPlayerFirstName(), this.getPlayerB().getPlayerLastName(), durationInHours * -1);
                    }
                } else {
                    if (durationInDays > 1) {
                        summary = "%s %s vs %s %s, started %s days ago".formatted(this.getPlayerA().getPlayerFirstName(), this.getPlayerA().getPlayerLastName(),
                                this.getPlayerB().getPlayerFirstName(), this.getPlayerB().getPlayerLastName(), durationInDays);
                    } else if (durationInHours > 1) {
                        summary = "%s %s vs %s %s, started %s hours ago".formatted(this.getPlayerA().getPlayerFirstName(), this.getPlayerA().getPlayerLastName(),
                                this.getPlayerB().getPlayerFirstName(), this.getPlayerB().getPlayerLastName(), durationInMinutes);
                    } else {
                        summary = "%s %s vs %s %s, started %s minutes ago".formatted(this.getPlayerA().getPlayerFirstName(), this.getPlayerA().getPlayerLastName(),
                                this.getPlayerB().getPlayerFirstName(), this.getPlayerB().getPlayerLastName(), durationInHours);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return summary;
    }


}
