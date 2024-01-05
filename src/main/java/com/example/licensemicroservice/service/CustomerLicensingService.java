package com.example.licensemicroservice.service;

import com.example.licensemicroservice.domain.License;
import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.Tournament;
import com.example.licensemicroservice.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerLicensingService {

    final LicenseService licenseService;

    final MatchService matchService;

    final TournamentService tournamentService;

    final CustomerService customerService;

    final CustomerLicensingRepository customerLicensingRepository;

    public CustomerLicensingService(LicenseService licenseService, MatchService matchService, TournamentService tournamentService, CustomerService customerService, CustomerLicensingRepository customerLicensingRepository) {
        this.licenseService = licenseService;
        this.matchService = matchService;
        this.tournamentService = tournamentService;
        this.customerService = customerService;
        this.customerLicensingRepository = customerLicensingRepository;
    }


    public License getLicenseById(int licenseId){
        return licenseService.getLicenseById(licenseId);
    }


    public List<Integer> getLicenseIdsByCustomerId(int customerId) {
        return customerLicensingRepository.findLicenseIdsByCustomerId(customerId);
    }

    // getTournament()).collect(Collectors.toList());

    public List<Match> getMatchesByCustomerId(int customerId) {
        // return Tournament from license
        List<Match> listOfLicensedSingleMatchesIfAny = getLicenseIdsByCustomerId(customerId).stream().map(
                licenseId -> licenseService.getLicenseById(licenseId).getSingleMatchId()).map(
                        matchId -> matchService.getMatchById(matchId)).toList();
        List<Match> listOfMatchesFromLicensedTournaments = getLicenseIdsByCustomerId(customerId).stream().map(
                licenseId -> licenseService.getLicenseById(licenseId).getTournamentId()).map(
                        tournamentId -> matchService.getMatchesByTournamentId(tournamentId)).findAny().orElseThrow(
                () ->
                        new EntityNotFoundException(
                                "No matches found for Customer ID %s".formatted(customerId)));

        List<Match> allLicensedMatches = Stream.concat(listOfLicensedSingleMatchesIfAny.stream(), listOfMatchesFromLicensedTournaments.stream()).toList();
        return allLicensedMatches;
    }
}
