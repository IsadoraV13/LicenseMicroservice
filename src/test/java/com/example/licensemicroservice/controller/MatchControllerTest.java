package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.LicenseMicroserviceApplication;
import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.Player;
import com.example.licensemicroservice.domain.ResponseEntity;
import com.example.licensemicroservice.service.MatchService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class MatchControllerTest {

    /*
    Initially used MockBean because I was learning how to test a Spring application for the first time and was very
    confused about the different annotations. Clarified via a session with Noe and at the same time, team discussions
    on the incorrect use of MockBeans, so have refactored
     */
    @Mock
    private MatchService matchService;

    private MatchController matchController;

    @BeforeEach
    public void setUp() {
        matchController = new MatchController(matchService);
    }


    @Test
    void testviewMatchInfoById() throws Exception {
        final Timestamp startTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 12, 0));
        final Timestamp endTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 18, 0));
        Player rafa = new Player(1, "Rafael", "Nadal", "1");
        Player serena = new Player(2, "Serena", "Williams", "1");
        LinkedHashMap<String, String> expectedFormattedResponse = new LinkedHashMap<>();
        final Match expectedMatch = new Match(3, 3, startTimeDay3_2024, endTimeDay3_2024, rafa,
                serena, "AvBTime");

        expectedFormattedResponse.put("matchId", Integer.toString(expectedMatch.getMatchId()));
        expectedFormattedResponse.put("startDate", expectedMatch.getStartTime().toString());
        expectedFormattedResponse.put("playerA", String.format("%s %s",expectedMatch.getPlayerA().getPlayerFirstName(), expectedMatch.getPlayerA().getPlayerLastName()));
        expectedFormattedResponse.put("playerB", String.format("%s %s",expectedMatch.getPlayerB().getPlayerFirstName(), expectedMatch.getPlayerB().getPlayerLastName()));
        expectedFormattedResponse.put("summary", expectedMatch.getSummary());

        ResponseEntity<LinkedHashMap<String, String>> expectedResponseEntity = new ResponseEntity<>();
        expectedResponseEntity.setData(expectedFormattedResponse);
        expectedResponseEntity.setResponse_code(HttpStatus.OK.value());
        expectedResponseEntity.setMessage("Success");

        ResponseEntity<LinkedHashMap<String, String>> actualMatchInfo = matchController.viewMatchInfoById(3);
        List<String> actualFormattedResponse = actualMatchInfo.getData().values().stream().toList();

        actualMatchInfo.getData().forEach((key, value) -> System.out.println(key + "=" + value));

        //{matchId=3, startDate=2024-01-13 12:00:00.0, playerA=Rafael Nadal, playerB=Serena Williams, summary=Rafael Nadal vs Serena Williams, started 11 days ago}

        assertEquals(expectedResponseEntity, actualFormattedResponse);

    }

    @Test
    void viewMatchByIdTest() {
    }
}