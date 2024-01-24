package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.ResponseEntity;
import com.example.licensemicroservice.service.MatchService;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private static final Logger LOG = LoggerFactory.getLogger(MatchController.class);
    final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }


    /*
    This works
     */
    @GetMapping("/{matchId}")
//    @ResponseStatus(HttpStatus.FOUND) // when is this used?
    public ResponseEntity<LinkedHashMap<String, String>> viewMatchInfoById(@PathVariable int matchId) throws JsonProcessingException {
        ResponseEntity<LinkedHashMap<String, String>> response = new ResponseEntity<>();
        response.setData(matchService.getMatchInfoById(matchId));
        if (response.getData().isEmpty()) {
            response.setResponse_code(HttpStatus.NOT_FOUND.value());
            response.setMessage("match id %s not found".formatted(matchId));
            LOG.error("An exception occurred", new EntityNotFoundException(response.getData().get("message")));
        } else {
            response.setResponse_code(HttpStatus.OK.value());
            response.setMessage("Success"); // is this needed for a 200 if the response code is there anyway? what is
            // best practice for what to put in the message?
        }
        return response; // still returning an empty data set and an actual Http status code of 200 for notFound, so my
        // test is not working as accepted
    }

    /*
    This does not work ... yet!
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<LinkedHashMap<String, String>>> viewMatchesByCustomerId(@PathVariable int customerId) throws JsonProcessingException {
        ResponseEntity<List<LinkedHashMap<String, String>>> response = new ResponseEntity<>();
        response.setData(matchService.getMatchesByCustomerId(customerId).stream().map(
                match -> matchService.getMatchInfoById(match.getMatchId()))
                .collect(Collectors.toList()));
        return response;
    }


    /*
    Not refactored.
     */
    @GetMapping("tournament/{tournamentId}")
    public List<Match> viewMatchesByTournamentId(@PathVariable int tournamentId) {
        return matchService.getMatchesByTournamentId(tournamentId);
    }
}
