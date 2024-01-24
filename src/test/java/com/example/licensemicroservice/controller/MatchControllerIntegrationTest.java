package com.example.licensemicroservice.controller;

import com.example.licensemicroservice.LicenseMicroserviceApplication;
import com.example.licensemicroservice.domain.Match;
import com.example.licensemicroservice.domain.Player;
import com.example.licensemicroservice.domain.ResponseEntity;
import com.example.licensemicroservice.service.MatchService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(controllers = MatchController.class)
// used when ? you only want to test the web api part of Spring (not going as far as checking the DB)
// eg user not found >> mock the user not fond response
@SpringBootTest
@AutoConfigureMockMvc
class MatchControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    /*
    Initially used MockBean because I was learning how to test a Spring application for the first time and was very
    confused about the different annotations. Clarified via a session with Noe and at the same time, team discussions
    on the incorrect use of MockBeans, so have started refactoring - but did not complete the actual integration part
    with the Service
     */
    @Autowired
    private MatchController matchController;


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void shouldReturnOk(int matchId) throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/matches/{matchId}", matchId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /*
    This fails because I am still returning an empty response even when the match is not found, and therefore a
    200 instead of a 404
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 4})
    void shouldReturnNotFound(int matchId) throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/matches/{matchId}", matchId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}