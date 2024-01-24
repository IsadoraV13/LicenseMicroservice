package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.LicenseMicroserviceApplication;
import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.Player;
import com.example.licensemicroservice.domain.ResponseEntity;
import com.example.licensemicroservice.repository.PlayerRepository;
import com.example.licensemicroservice.service.MatchService;
import org.junit.jupiter.api.Test;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MatchController.class)
@ContextConfiguration(classes = LicenseMicroserviceApplication.class)
class MatchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MatchService mockMatchService;

    @MockBean
    PlayerRepository mockPlayerRepository;

    private final MatchController matchController;

    @Autowired
    MatchControllerTest(MatchController matchController) {
        this.matchController = matchController;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4}) //ToDo why does 4 pass?
    void shouldReturnOk(int matchId) throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/matches/{matchId}", matchId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void viewMatchById() throws Exception {
        final Timestamp startTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 12, 0));
        final Timestamp endTimeDay3_2024 = Timestamp.valueOf(LocalDateTime.of(2024, 1, 13, 18, 0));
        Player rafa = new Player(1, "Rafael", "Nadal", "1");
        Player serena = new Player(2, "Serena", "Williams", "1");
        Mockito.when(mockPlayerRepository.findPlayerById(1)).thenReturn(rafa);
        Mockito.when(mockPlayerRepository.findPlayerById(2)).thenReturn(serena);
        LinkedHashMap<String, String> formattedMockedResponse = new LinkedHashMap<>();
        formattedMockedResponse.put("matchId", Integer.toString(match.getMatchId()));
        formattedMockedResponse.put("startDate", match.getStartTime().toString());
        formattedMockedResponse.put("playerA", String.format("%s %s",match.getPlayerA().getPlayerFirstName(), match.getPlayerA().getPlayerLastName()));
        formattedMockedResponse.put("playerB", String.format("%s %s",match.getPlayerB().getPlayerFirstName(), match.getPlayerB().getPlayerLastName()));
        formattedMockedResponse.put("summary", match.getSummary());
        final Match mockedMatch = new Match(3, 3, startTimeDay3_2024, endTimeDay3_2024, mockPlayerRepository.findPlayerById(1),
                mockPlayerRepository.findPlayerById(2), "AvBTime");
        Mockito.when(mockMatchService.getMatchById(3)).thenReturn(mockedMatch);
        ResponseEntity<LinkedHashMap<String, String>> actualMatch = matchController.viewMatchById(3);

        assertEquals(mockedMatch, actualMatch);

    }

    @Test
    void viewMatchByIdTest() {
    }
}