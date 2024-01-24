package com.example.licensemicroservice.service;

import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.repository.MatchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MatchService {
    private static final Logger LOG = LoggerFactory.getLogger(MatchService.class);
    final MatchRepository matchRepository;

    final LicenseService licenseService;

    final CustomerLicensingService customerLicensingService;

    public MatchService(MatchRepository matchRepository, LicenseService licenseService, CustomerLicensingService customerLicensingService) {
        this.matchRepository = matchRepository;
        this.licenseService = licenseService;
        this.customerLicensingService = customerLicensingService;
    }

    /*
    Rationale: Added logic in Service so that Controller only receives what we want (requirements) to pass on to the 'client'.
    However, I struggled (in stages LOL) as follows:
    1. I was initially passing the whole json response object back
    2. Noe mentioned that it was best practice to pass a response object (is that a DTO? Still getting my head around terms)
    3. So I found a model for a response object/entity.
        - Not even sure if response object and response entity usually refer to the same concept?
        - however, I was still tsruggling with only passing those desired fields defined by the requirements
    4. I ended up circumventing it, but I don't think that's the right way!
        - Initially used a HashMap because I didn't realise it doesn't preserve insertion order
        - then switched to LinkedHashMap
    5. matchRepository was initially returning a Match, rather than an Optional
        - I realised I didn't have a way of handling scenarios where the id doesn't exist - and changed to Optional
        - again circumvented it by passing a 'not found' message as data below. I think ideally this should
     */
    public LinkedHashMap<String, String> getMatchInfoById(int matchId) {
        Optional<Match> matchResponse = matchRepository.findMatchById(matchId);
        LinkedHashMap<String, String> formattedResponse = new LinkedHashMap<>();
        if (matchResponse.isPresent()) {
            Match match = matchResponse.get();
            formattedResponse.put("matchId", Integer.toString(match.getMatchId()));
            formattedResponse.put("startDate", match.getStartTime().toString());
            formattedResponse.put("playerA", String.format("%s %s", match.getPlayerA().getPlayerFirstName(), match.getPlayerA().getPlayerLastName()));
            formattedResponse.put("playerB", String.format("%s %s", match.getPlayerB().getPlayerFirstName(), match.getPlayerB().getPlayerLastName()));
            formattedResponse.put("summary", match.getSummary());
        }
        return formattedResponse; // conscious this is passing an empty LinkedHashMap if the Optional is empty
    }

    /*
    Not refactored
     */
    public Optional<Match> getMatchById(int matchId) {
        return matchRepository.findMatchById(matchId);
    }

    /*
    Not refactored to Optional
     */
    public List<Match> getMatchesByCustomerId(int customerId) {
        // Requirements: a customer can have multiple licenses, some for single matches, some for whole tournaments
        List<Match> listOfLicensedSingleMatchesIfAny = customerLicensingService.getLicenseIdsByCustomerId(customerId).stream().map(
                        licenseId -> licenseService.getLicenseById(licenseId).getSingleMatchId())
                .findAny().map(matchId -> getMatchById(matchId))
                .orElseThrow( // Not sure what this does in practice or whether I should return an Optional
                        () ->
                                new EntityNotFoundException(
                                        "No single licensed matches found for customer ID %s".formatted(customerId)))
                .stream().toList();
        LOG.info("I don't know how to use this");
        List<Match> listOfMatchesFromLicensedTournaments = customerLicensingService.getLicenseIdsByCustomerId(customerId).stream().map(
                licenseId -> licenseService.getLicenseById(licenseId).getTournamentId()).map( // what happens if there is no tournament?
                tournamentId -> getMatchesByTournamentId(tournamentId)).findAny().orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "No licensed matches found at tournament level for customer ID %s".formatted(customerId))); // this method takes a
        // customer id as input, so is tournament Id relevant here?

        List<Match> allLicensedMatches = new ArrayList<>();
        if (!listOfLicensedSingleMatchesIfAny.isEmpty()) {
            allLicensedMatches= Stream.concat(listOfLicensedSingleMatchesIfAny.parallelStream(),
                            listOfMatchesFromLicensedTournaments.parallelStream())
                    .collect(Collectors.toList());
        }
        System.out.println(allLicensedMatches);
        return allLicensedMatches;
    }

    /*
    Not refactored
     */
    public List<Match> getMatchesByTournamentId(int tournamentId) {
        return matchRepository.findMatchesByTournamentId(tournamentId);
    }
}
